package com.howard.rentalmytrack.dao;

import java.util.List;

import com.howard.rentalmytrack.vo.RentalMyTrackVo;

public interface RentalMyTrackDao {
	
	void insert(RentalMyTrackVo rmt);

	void delete(Integer rNo, Integer memNo);

	void update(RentalMyTrackVo rmt);
	
	RentalMyTrackVo findByPK(Integer rNo, Integer memNo);
	
	List<RentalMyTrackVo> getAll();

}
