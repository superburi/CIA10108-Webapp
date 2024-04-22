package com.howard.rentalmytrack.service_orm;

import com.howard.rentalmytrack.vo.RentalMyTrackVo_ORM;

import java.util.List;
import java.util.Map;

public interface RentalMyTrackService_ORM {

    RentalMyTrackVo_ORM addTrack(RentalMyTrackVo_ORM emp);

    RentalMyTrackVo_ORM updateTrack(RentalMyTrackVo_ORM emp);

    void deleteTrack(Integer rNo, Integer memNo);

    RentalMyTrackVo_ORM getById(Integer rNo, Integer memNo);

    List<RentalMyTrackVo_ORM> getAllTracks(int currentPage);

    int getPageTotal();

    List<RentalMyTrackVo_ORM> getTracksByCompositeQuery(Map<String, String[]> map);

}
