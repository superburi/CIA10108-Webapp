package com.howard.rentalorder.service;

import com.howard.rentalorder.dao.RentalOrderDao;
import com.howard.rentalorder.dao.RentalOrderDaoImpl;
import com.howard.rentalorder.vo.RentalOrderVo;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class RentalOrderService {

	/*
	 * 共有6個方法(照 增、刪、改、查 順序由上而下) :
	 *
	 * 1. addOrder -> 新增訂單
	 * 		參數有20 : memNo(會員編號)、rByrName(訂購人姓名)、rByrPhone(訂購人手機號碼)、rByrEmail(訂購人Email)、
	 *                rRcvName(收件人姓名)、rRcvPhone(收件人手機號碼)、rTakeMethod(取貨方式)、rAddr(宅配住址)、
	 *                rPayMethod(付款方式)、rAllPrice(訂單總金額)、rAllDepPrice(押金總金額)、rOrdTime(下單時間)、
	 *                rDate(預計租借日期)、rBackDate(預計歸還日期)、rRealBackDate(實際歸還日期)、rPayStat(付款狀態)、
	 *                rOrdStat(訂單狀態)、rtnStat(歸還狀態)、rtnRemark(歸還註記)、rtnCompensation(賠償金額)
	 * 		回傳值 : 該筆新增訂單
	 *
	 * 2. deleteOrder -> 刪除訂單
	 * 		參數有1 : rOrdNo(租借品訂單編號)
	 * 		無回傳值
	 *
	 * 3. updateOrder -> 修改訂單資料
	 * 		參數有21 : rOrdNo(租借品訂單編號)、memNo(會員編號)、rByrName(訂購人姓名)、rByrPhone(訂購人手機號碼)、
	 *                rByrEmail(訂購人Email)、rRcvName(收件人姓名)、rRcvPhone(收件人手機號碼)、rTakeMethod(取貨方式)、
	 *                rAddr(宅配住址)、rPayMethod(付款方式)、rAllPrice(訂單總金額)、rAllDepPrice(押金總金額)、
	 *                rOrdTime(下單時間)、rDate(預計租借日期)、rBackDate(預計歸還日期)、rRealBackDate(實際歸還日期)、
	 *                rPayStat(付款狀態)、rOrdStat(訂單狀態)、rtnStat(歸還狀態)、rtnRemark(歸還註記)、
	 *                rtnCompensation(賠償金額)
	 * 		回傳值 : 該筆被修改的訂單
	 *
	 * 4. getOneOrder -> 用 PK 查詢單筆訂單
	 * 		參數有1 : rOrdNo(租借品訂單編號)
	 * 		回傳值 : 該筆查詢的訂單
	 *
	 * 5. getOneOrderByName -> 用 訂購人姓名 查詢訂單
	 *      參數有1 : rByrName(訂購人姓名)
     * 		回傳值 : 裝著所有訂單的 ArrayList
	 *
	 * 6. getAll -> 查詢所有訂單
	 * 		參數有0
	 * 		回傳值 : 裝著所有資料的 ArrayList
	*/

	private RentalOrderDao dao;
	public RentalOrderService() {
		dao = new RentalOrderDaoImpl();
	}

	public void addOrder(Integer memNo, String rByrName, String rByrPhone, String rByrEmail,
                  String rRcvName, String rRcvPhone, Byte rTakeMethod, String rAddr, Byte rPayMethod,
                  BigDecimal rAllPrice, BigDecimal rAllDepPrice, Timestamp rOrdTime, Timestamp rDate,
                  Timestamp rBackDate, Timestamp rRealBackDate, Byte rPayStat, Byte rOrdStat, Byte rtnStat,
                  String rtnRemark, BigDecimal rtnCompensation) {

		RentalOrderVo rentalOrderVo = new RentalOrderVo();

		rentalOrderVo.setMemNo(memNo);
		rentalOrderVo.setrByrName(rByrName);
		rentalOrderVo.setrByrPhone(rByrPhone);
		rentalOrderVo.setrByrEmail(rByrEmail);
		rentalOrderVo.setrRcvName(rRcvName);
		rentalOrderVo.setrRcvPhone(rRcvPhone);
		rentalOrderVo.setrTakeMethod(rTakeMethod);
		rentalOrderVo.setrAddr(rAddr);
		rentalOrderVo.setrPayMethod(rPayMethod);
		rentalOrderVo.setrAllPrice(rAllPrice);
		rentalOrderVo.setrAllDepPrice(rAllDepPrice);
		rentalOrderVo.setrOrdTime(rOrdTime);
		rentalOrderVo.setrDate(rDate);
		rentalOrderVo.setrBackDate(rBackDate);
		rentalOrderVo.setrRealBackDate(rRealBackDate);
		rentalOrderVo.setrPayStat(rPayStat);
		rentalOrderVo.setrOrdStat(rOrdStat);
		rentalOrderVo.setRtnStat(rtnStat);
		rentalOrderVo.setRtnRemark(rtnRemark);
		rentalOrderVo.setRtnCompensation(rtnCompensation);

//		int rOrdNo = dao.insert(rentalOrderVo);
		dao.insert(rentalOrderVo);
//		RentalOrderVo rentalOrderVo1 = dao.findByPK(rOrdNo);

//		if (rentalOrderVo1 != null) {
//			return rentalOrderVo1;
//		} else {
//			return null;
//		}

	}


	public void deleteOrder(Integer rOrdNo) {
		dao.delete(rOrdNo);
	}


	public RentalOrderVo updateOrder(Integer rOrdNo, Integer memNo, String rByrName, String rByrPhone,
									 String rByrEmail, String rRcvName, String rRcvPhone, Byte rTakeMethod,
									 String rAddr, Byte rPayMethod, BigDecimal rAllPrice, BigDecimal rAllDepPrice,
									 Timestamp rOrdTime, Timestamp rDate, Timestamp rBackDate,
									 Timestamp rRealBackDate, Byte rPayStat, Byte rOrdStat, Byte rtnStat,
									 String rtnRemark, BigDecimal rtnCompensation) {

		RentalOrderVo rentalOrderVo = new RentalOrderVo();

		rentalOrderVo.setrOrdNo(rOrdNo);
		rentalOrderVo.setMemNo(memNo);
		rentalOrderVo.setrByrName(rByrName);
		rentalOrderVo.setrByrPhone(rByrPhone);
		rentalOrderVo.setrByrEmail(rByrEmail);
		rentalOrderVo.setrRcvName(rRcvName);
		rentalOrderVo.setrRcvPhone(rRcvPhone);
		rentalOrderVo.setrTakeMethod(rTakeMethod);
		rentalOrderVo.setrAddr(rAddr);
		rentalOrderVo.setrPayMethod(rPayMethod);
		rentalOrderVo.setrAllPrice(rAllPrice);
		rentalOrderVo.setrAllDepPrice(rAllDepPrice);
		rentalOrderVo.setrOrdTime(rOrdTime);
		rentalOrderVo.setrDate(rDate);
		rentalOrderVo.setrBackDate(rBackDate);
		rentalOrderVo.setrRealBackDate(rRealBackDate);
		rentalOrderVo.setrPayStat(rPayStat);
		rentalOrderVo.setrOrdStat(rOrdStat);
		rentalOrderVo.setRtnStat(rtnStat);
		rentalOrderVo.setRtnRemark(rtnRemark);
		rentalOrderVo.setRtnCompensation(rtnCompensation);

		dao.update(rentalOrderVo);

		return dao.findByPK(rOrdNo);

	}


	public RentalOrderVo getOneOrder(Integer rOrdNo) {
		return dao.findByPK(rOrdNo);
	}

	public List<RentalOrderVo> getOneOrderByName(String rByrName) {
		return dao.findByName(rByrName);
	}


	public List<RentalOrderVo> getAll() {
		return dao.getAll();
	}


}