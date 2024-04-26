package com.yu.rental.model;

import java.util.List;
import java.util.Map;

public interface RentalHibernateDAO {

    int insert(Rental rentalVO);  //若是使用Boolean，即可判斷是否有新增成功

    int update(Rental rentalVO); //修改

    int delete(Integer rNo); //刪除

    Rental getByPK(Integer rNo); //使用PK去搜尋處理

//    List<RentalVO> getByName(String rCatName);

    List<Rental> getAll(); //萬用複合查詢

    List<Rental> getByCompositeQuery(Map<String, String> map); //複合查詢

    List<Rental> getAllRentals(int currentPage); //查詢當前頁面

    long getPageTotal();

}
