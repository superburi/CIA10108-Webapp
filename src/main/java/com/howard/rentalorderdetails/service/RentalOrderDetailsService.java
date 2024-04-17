package com.howard.rentalorderdetails.service;

import com.howard.rentalorderdetails.dao.RentalOrderDetailsDao;
import com.howard.rentalorderdetails.dao.RentalOrderDetailsDaoImpl;
import com.howard.rentalorderdetails.vo.RentalOrderDetails;
import java.math.BigDecimal;
import java.util.List;

public class RentalOrderDetailsService {

	private  final RentalOrderDetailsDao dao;


	/*
	 * 共有5個方法(照順序由上而下) :
	 *
	 * 1. addDetail -> 新增明細
	 * 		參數有4 : rOrdNo(租借品訂單編號)、rNo(租借品編號)、rPrice(單價)、rDesPrice(押金)
	 * 		回傳值 : 該筆新增明細
	 *
	 * 2. deleteDetail -> 刪除明細，
	 * 		參數有2 : rOrdNo(租借品訂單編號)、rNo(租借品編號)
	 * 		無回傳值
	 *
	 * 3. updateDetail -> 修改明細
	 * 		參數有4 : rOrdNo(租借品訂單編號)、rNo(租借品編號)、rPrice(單價)、rDesPrice(押金)
	 * 		回傳值 : 該筆修改的明細
	 *
	 * 4. getOneDetail -> 查詢單筆明細
	 * 		參數有2 : rOrdNo(租借品訂單編號)、rNo(租借品編號)
	 * 		回傳值 : 該筆查詢的明細
	 *
	 * 5. getAll -> 查詢全部資料
	 * 		參數有0
	 * 		回傳值 : 裝著所有明細的 ArrayList
	*/

	public RentalOrderDetailsService() {
		dao = new RentalOrderDetailsDaoImpl();
	}

	public RentalOrderDetails addDetail(Integer rOrdNo, Integer rNo,
									   BigDecimal rPrice, BigDecimal rDesPrice) {

		RentalOrderDetails rentalOrderDetails = new RentalOrderDetails();

        rentalOrderDetails.setrOrdNo(rOrdNo);
        rentalOrderDetails.setrNo(rNo);
        rentalOrderDetails.setrPrice(rPrice);
        rentalOrderDetails.setrDesPrice(rDesPrice);
		dao.insert(rentalOrderDetails);
        RentalOrderDetails rentalOrderDetails1 = dao.findByPK(rOrdNo, rNo);

		if (rentalOrderDetails1 != null) {
            return rentalOrderDetails1;
        }
        return null;

	}


	public void deleteDetail(Integer rOrdNo, Integer rNo) {
		dao.delete(rOrdNo, rNo);
	}


	public RentalOrderDetails updateDetail(Integer rOrdNo, Integer rNo,
                                          BigDecimal rPrice, BigDecimal rDesPrice) {

		RentalOrderDetails rentalOrderDetails = new RentalOrderDetails();

        rentalOrderDetails.setrOrdNo(rOrdNo);
        rentalOrderDetails.setrNo(rNo);
        rentalOrderDetails.setrPrice(rPrice);
        rentalOrderDetails.setrDesPrice(rDesPrice);

		dao.update(rentalOrderDetails);

        RentalOrderDetails rentalOrderDetails1 = dao.findByPK(rOrdNo, rNo);

		return rentalOrderDetails1;
	}


	public RentalOrderDetails getOneDetail(Integer rOrdNo, Integer rNo) {
		return dao.findByPK(rOrdNo, rNo);
	}


	public List<RentalOrderDetails> getAll() {
		return dao.getAll();
	}


}