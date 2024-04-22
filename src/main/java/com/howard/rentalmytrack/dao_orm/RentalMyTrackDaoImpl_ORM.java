package com.howard.rentalmytrack.dao_orm;

import com.howard.rentalmytrack.vo.RentalMyTrackVo_ORM;
import com.howard.util.HibernateUtil;
import com.roger.member.vo.MemberVO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.yu.rental.model.Rental;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.howard.util.Constants.PAGE_MAX_RESULT;

public class RentalMyTrackDaoImpl_ORM implements RentalMyTrackDao_ORM{

    private SessionFactory factory;

    public RentalMyTrackDaoImpl_ORM() {
        factory = HibernateUtil.getSessionFactory();
    }

    public Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public Map<String, Integer> insert(RentalMyTrackVo_ORM entity) {

        getSession().save(entity);
        Map<String, Integer> map = new HashMap<>();
        map.put("rNo", entity.getRental().getrNo());
        map.put("memNo", entity.getMemberVO().getMemNo());

        return map;

    }

    @Override
    public Map<String, Integer> update(RentalMyTrackVo_ORM entity) {

        Map<String, Integer> map = new HashMap<>();
        try {
            getSession().update(entity);
            map.put("rNo", entity.getRental().getrNo());
            map.put("memNo", entity.getMemberVO().getMemNo());
            return map;
        } catch (Exception e) {
            return map; // 如果更新有問題，就回傳空的 map
        }

    }

    @Override
    public int delete(Integer rNo, Integer memNo) {

        Rental rental = getSession().get(Rental.class, rNo);
        MemberVO memberVO = getSession().get(MemberVO.class, memNo);
        RentalMyTrackVo_ORM rentalMyTrackVoOrm = getSession().get(RentalMyTrackVo_ORM.class,
                new RentalMyTrackVo_ORM.CompositeTrack(rental, memberVO));

        if (rentalMyTrackVoOrm != null) {
            getSession().delete(rentalMyTrackVoOrm);
            return 1;
        } else {
            return -1;
        }

    }

    @Override
    public RentalMyTrackVo_ORM getById(Integer rNo, Integer memNo) {

        Rental rental = getSession().get(Rental.class, rNo);
        MemberVO memberVO = getSession().get(MemberVO.class, memNo);

        return getSession().get(RentalMyTrackVo_ORM.class,
                new RentalMyTrackVo_ORM.CompositeTrack(rental, memberVO));

    }

    @Override
    public List<RentalMyTrackVo_ORM> getAll() {
        return getSession().createQuery("FROM RentalMyTrackVo_ORM", RentalMyTrackVo_ORM.class).list();
    }

    @Override
    public List<RentalMyTrackVo_ORM> getByCompositeQuery(Map<String, String> map) {

        if (map.isEmpty())
            return getAll();

        List<RentalMyTrackVo_ORM> trackList = new ArrayList<>();
        String query = "FROM RentalMyTrackVo_ORM WHERE 1=1";

        if (map.containsKey("rNo")) {
            query += " AND rNo LIKE " + "'%" + map.get("rNo") + "%'";
        }
        if (map.containsKey("memNo")) {
            query += " AND memNo LIKE " + "'%" + map.get("memNo") + "%'";
        }
        if (map.containsKey("rTrackTime")) {
            query += " AND rTrackTime LIKE " + "'%" + map.get("rTrackTime") + "%'";
        }
        if (map.containsKey("expRentalDate")) {
            query += " AND expRentalDate = " + map.get("expRentalDate");
        }

        return getSession().createQuery(query, RentalMyTrackVo_ORM.class).list();

    }

    @Override
    public List<RentalMyTrackVo_ORM> getAll(int currentPage) {

        int first = (currentPage - 1) * PAGE_MAX_RESULT;
        return getSession().createQuery("FROM RentalMyTrackVo_ORM", RentalMyTrackVo_ORM.class)
                .setFirstResult(first) // 這頁從第幾筆資料起算
                .setMaxResults(PAGE_MAX_RESULT) // 一頁最多幾筆資料
                .list();

    }

    @Override
    public long getTotal() {
        return getSession().createQuery("SELECT count(*) FROM RentalMyTrackVo_ORM", Long.class)
                            .uniqueResult();
    }

}

