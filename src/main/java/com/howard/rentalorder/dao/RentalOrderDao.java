package com.howard.rentalorder.dao;

import com.howard.rentalorder.vo.RentalOrderVo;

import java.util.List;

public interface RentalOrderDao {

	void insert(RentalOrderVo rentalOrderVo);

	void delete(Integer rOrdNo);

	void update(RentalOrderVo rentalOrderVo);

	RentalOrderVo findByPK(Integer rOrdNo);

    List<RentalOrderVo> findByName(String rByrName);

	List<RentalOrderVo> getAll();

}
