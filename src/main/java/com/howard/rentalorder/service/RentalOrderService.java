package com.howard.rentalorder.service;

import com.howard.rentalorder.dao.RentalOrderDao;
import com.howard.rentalorder.dao.RentalOrderDaoImpl;
import com.howard.rentalorder.vo.RentalOrderVo;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class RentalOrderService {

	private RentalOrderDao dao;


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

	public RentalOrderService() {
		dao = new RentalOrderDaoImpl();
	}

//	public RentalOrderVo addOrder(Integer memNo, String rByrName, String rByrPhone, String rByrEmail,
//                  String rRcvName, String rRcvPhone, Byte rTakeMethod, String rAddr, Byte rPayMethod,
//                  BigDecimal rAllPrice, BigDecimal rAllDepPrice, Timestamp rOrdTime, Timestamp rDate,
//                  Timestamp rBackDate, Timestamp rRealBackDate, Byte rPayStat, Byte rOrdStat, Byte rtnStat,
//                  String rtnRemark, BigDecimal rtnCompensation) {
//
//		RentalOrder rmt = new RentalOrder();
//
//		rmt.setrNo(rNo);
//		rmt.setmemNo(memNo);
//		rmt.setexpRentalDate(expRentalDate);
//		dao.insert(rmt);
//		Timestamp timestamp = dao.findByPK(rNo, memNo).getrTrackTime();
//		rmt.setrTrackTime(timestamp);
//
//		return rmt;
//	}
//
//
//	public void deleteOrder(Integer rNo, Integer memNo) {
//		dao.delete(rNo, memNo);
//	}
//
//
//	public RentalOrder updateOrder(Integer rNo, Integer memNo,
//								   Date expRentalDate) {
//
//		RentalOrder rmt = new RentalOrder();
//
//		rmt.setrNo(rNo);
//		rmt.setmemNo(memNo);
//		rmt.setexpRentalDate(expRentalDate);
//
//		Timestamp timestamp = dao.findByPK(rNo, memNo).getrTrackTime();
//		rmt.setrTrackTime(timestamp);
//
//		dao.update(rmt);
//
//		return rmt;
//	}
//
//
//	public RentalOrder getOneOrder(Integer rNo, Integer memNo) {
//		return dao.findByPK(rNo, memNo);
//	}
//
//
//	public List<RentalOrder> getAll() {
//		return dao.getAll();
//	}


}