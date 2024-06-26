package com.yu.rental.model;

import com.howard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.howard.util.Constants.PAGE_MAX_RESULT;
import static java.lang.Byte.valueOf;

public class RentalDAOHibernateImpl implements RentalHibernateDAO{

    // SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
    private SessionFactory factory;

    public RentalDAOHibernateImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    // Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
    // 以避免請求執行緒共用了同個 Session
    private Session getSession() {
        return factory.getCurrentSession();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// add
    @Override
    public int insert(Rental rentalVO) {
        // 回傳給 service 剛新增成功的自增主鍵值
        return (Integer) getSession().save(rentalVO);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// update
    @Override
    public int update(Rental rentalVO) {
        try {
            getSession().update(rentalVO);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// delete
    @Override
    public int delete(Integer rNo) {
        Rental rentalVO = getSession().get(Rental.class, rNo);
        if (rentalVO != null) {
            getSession().delete(rentalVO);

            return 1; // 回傳給 service，1代表刪除成功
        } else {
            return -1; // 回傳給 service，-1代表刪除失敗
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getByPK
    @Override
    public Rental getByPK(Integer rNo) {
        return getSession().get(Rental.class, rNo);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getAll
    @Override //查詢全部(回傳List集合)
    public List<Rental> getAll() {
        return getSession().createQuery("from Rental", Rental.class).list();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getByCompositeQuery
    // 複合查詢
    @Override
    public List<Rental> getByCompositeQuery(Map<String, String> map) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            if (map.size() == 0) { //如果陣列為空
                return getAll(); //回傳所有租借品類別的資料
            }

            // 使用CriteriaQuery(標準查詢)，將查詢條件封裝成一個物件//
            CriteriaBuilder builder = session.getCriteriaBuilder(); // 建立Session 物件後，再CriteriaBuilder建立查詢條件
            CriteriaQuery<Rental> criteria = builder.createQuery(Rental.class); // 創建一個新的CriteriaQuery，並指定返回的實體類型為<RentalVO>
            Root<Rental> root = criteria.from(Rental.class); //Root是查詢的實體型別，並指定查詢的起始位置(RentalVO.class)

            List<Predicate> predicates = new ArrayList<>(); //Predicate是JPA套件。創建存儲查詢條件的空list，條件將添加到 predicates 中

            // 使用for-each迴圈，檢查map集合中的每一個鍵值
            // map.entrySet()返回Set<Map.Entry<String, String>>的值。透過getKey & getValue取得鍵&值
            for (Map.Entry<String, String> row : map.entrySet()) {
                if ("rCatNo".equals(row.getKey())) { //租借品類別編號
                    predicates.add(builder.equal(root.get("rStockQty"), row.getValue()));
                }

                if ("rName".equals(row.getKey())) { //租借品名稱
                    predicates.add(builder.like(root.get("rCatName"), "%" + row.getValue() + "%")); //使用like方法來建立模糊查詢
                }

                if ("rPrice".equals(row.getKey())) { //租借品單價
                    if (!map.containsKey("rPrice"))
                        predicates.add(builder.lessThanOrEqualTo(root.get("rPrice"), new BigDecimal(row.getValue())));
                }

                if ("rSize".equals(row.getKey())) { //尺寸
                    predicates.add(builder.equal(root.get("rSize"), row.getValue()));
                }

                if ("rColor".equals(row.getKey())) { //顏色
                    predicates.add(builder.like(root.get("rColor"), "%" + row.getValue() + "%")); //使用like方法來建立模糊查詢
                }

                if ("rInfo".equals(row.getKey())) { //租借品資訊
                    predicates.add(builder.like(root.get("rInfo"), "%" + row.getValue() + "%")); //使用like方法來建立模糊查詢
                }

                if ("rStat".equals(row.getKey())) { //租借品狀態
                    predicates.add(builder.equal(root.get("rStat"), valueOf(row.getValue()))); //valueOf轉成byte物件
                }
            }

            // 將前面建立的條件列表 predicates 轉換為 Predicate 陣列，並將它們用 AND 邏輯連接起來，作為查詢的 WHERE 條件。
            criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            criteria.orderBy(builder.asc(root.get("rNo"))); // 按照rNo由小到大排列

            //把上述的內容(criteria) 傳入TypedQuery取得結果
            TypedQuery<Rental> query = session.createQuery(criteria);

            List<Rental> list = query.getResultList(); //使用"分頁"方法

            session.getTransaction().commit();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown();
        }
        return null;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getPageTotal
    @Override
    public long getPageTotal() {
        return getSession().createQuery("select count(*) from Rental", Long.class).uniqueResult();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// getAllRentals
    @Override
    public List<Rental> getAllRentals(int currentPage) {  //設定分頁
        int first = (currentPage - 1) * PAGE_MAX_RESULT;
        return getSession().createQuery("from Rental", Rental.class)
                .setFirstResult(first)
                .setMaxResults(PAGE_MAX_RESULT)
                .list();
    }

}
