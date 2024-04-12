package com.howard.rentalmytrack.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.howard.rentalmytrack.dao.RentalMyTrackDao;
import com.howard.rentalmytrack.dao.RentalMyTrackDaoImpl;
import com.howard.rentalmytrack.vo.RentalMyTrackVo;

public class RentalMyTrackService {
	
	private RentalMyTrackDao dao;
	
	
	/*
	 * 共有5個方法(照順序由上而下) : 
	 * 
	 * 1. addTrack -> 新增資料
	 * 		參數有3 : rNo(租借品編號)、memNo(會員編號)、expRentalDate(期望租借日期)
	 * 		回傳值 : 該筆新增資料
	 * 
	 * 2. deleteTrack -> 刪除資料，
	 * 		參數有2 : rNo(租借品編號)、memNo(會員編號)
	 * 		無回傳值
	 * 		
	 * 3. updateTrack -> 修改資料
	 * 		參數有3 : rNo(租借品編號)、memNo(會員編號)、expRentalDate(期望租借日期)
	 * 		回傳值 : 該筆修改的資料
	 * 		
	 * 4. getOneTrack -> 查詢單筆資料
	 * 		參數有2 : rNo(租借品編號)、memNo(會員編號)
	 * 		回傳值 : 該筆查詢的資料
	 * 		
	 * 5. getAll -> 查詢全部資料，回傳值為裝著所有資料的 ArrayList
	 * 		參數有0
	 * 		回傳值 : 裝著所有資料的 ArrayList
	*/

	public RentalMyTrackService() {
		dao = new RentalMyTrackDaoImpl();
	}

	public RentalMyTrackVo addTrack(Integer rNo, Integer memNo,
									Date expRentalDate) {

		RentalMyTrackVo rmt = new RentalMyTrackVo();

		rmt.setrNo(rNo);
		rmt.setmemNo(memNo);
		rmt.setexpRentalDate(expRentalDate);
		dao.insert(rmt);
		Timestamp timestamp = dao.findByPK(rNo, memNo).getrTrackTime();
		rmt.setrTrackTime(timestamp);

		return rmt;
	}

	
	public void deleteTrack(Integer rNo, Integer memNo) {
		dao.delete(rNo, memNo);
	}
	
	
	public RentalMyTrackVo updateTrack(Integer rNo, Integer memNo,
									   Date expRentalDate) {

		RentalMyTrackVo rmt = new RentalMyTrackVo();

		rmt.setrNo(rNo);
		rmt.setmemNo(memNo);
		rmt.setexpRentalDate(expRentalDate);
		
		Timestamp timestamp = dao.findByPK(rNo, memNo).getrTrackTime();
		rmt.setrTrackTime(timestamp);

		dao.update(rmt);

		return rmt;
	}
	

	public RentalMyTrackVo getOneTrack(Integer rNo, Integer memNo) {
		return dao.findByPK(rNo, memNo);
	}

	
	public List<RentalMyTrackVo> getAll() {
		return dao.getAll();
	}
	

}