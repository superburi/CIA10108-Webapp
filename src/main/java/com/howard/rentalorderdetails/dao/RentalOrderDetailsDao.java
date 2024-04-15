package com.howard.rentalorderdetails.dao;

import com.howard.rentalorderdetails.vo.RentalOrderDetails;

import java.util.List;

public interface RentalOrderDetailsDao {

	void insert(RentalOrderDetails rentalOrderDetails);

	void delete(Integer rOrdNo, Integer rNo);

	void update(RentalOrderDetails rentalOrderDetails);

	RentalOrderDetails findByPK(Integer rOrdNo, Integer rNo);

	List<RentalOrderDetails> getAll();

}
