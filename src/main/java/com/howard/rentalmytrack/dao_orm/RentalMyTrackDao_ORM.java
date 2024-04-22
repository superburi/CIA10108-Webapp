package com.howard.rentalmytrack.dao_orm;

import com.howard.rentalmytrack.vo.RentalMyTrackVo_ORM;

import java.util.List;
import java.util.Map;

public interface RentalMyTrackDao_ORM {

    Map<String, Integer> insert(RentalMyTrackVo_ORM entity);

    Map<String, Integer> update(RentalMyTrackVo_ORM entity);

    int delete(Integer rNo, Integer memNo);

    RentalMyTrackVo_ORM getById(Integer rNo, Integer memNo);

    List<RentalMyTrackVo_ORM> getAll();

    List<RentalMyTrackVo_ORM> getByCompositeQuery(Map<String, String> map);

    List<RentalMyTrackVo_ORM> getAll(int currentPage);

    long getTotal();

}
