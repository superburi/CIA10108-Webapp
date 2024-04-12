//package com.howard.rentalorderdetails.controller;
//
//import com.howard.rentalmytrack.service.RentalMyTrackService;
//import com.howard.rentalmytrack.vo.RentalMyTrackVo;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Date;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//
//@WebServlet("/rentalorderdetails/RentalOrderDetailsController")
//public class RentalOrderDetailsController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//
//
//		// 來自新增頁面(addTrack.jsp)，新增追蹤品的請求
//        if ("insert".equals(action)) {
//
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//
//			// 檢查租借品編號
//			Integer rNo = null;
//			try {
//				rNo = Integer.valueOf(req.getParameter("rNo").trim());
//			} catch (NumberFormatException e) {
////				if (String.valueOf(rNo).trim() == "") {
////					errorMsgs.put("rNo", "租借品編號不能空白");
////				}
//				errorMsgs.put("rNo", "租借品編號請填數字");
//			}
//			// 檢查會員編號
//			Integer memNo = null;
//			try {
//				memNo = Integer.valueOf(req.getParameter("memNo").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("memNo", "會員編號請填數字");
//			} catch (NullPointerException nullPointerException) {
//				errorMsgs.put("memNo", "會員編號不能空白");
//			}
//			// 檢查期望租借日期
//			Date expRentalDate = null;
//			try {
//				expRentalDate = Date.valueOf(req.getParameter("expRentalDate"));
//			} catch (IllegalArgumentException e) {
//				expRentalDate = new Date(System.currentTimeMillis());
//				errorMsgs.put("expRentalDate", "請輸入日期!");
//			}
//
//			RentalMyTrackVo rentalMyTrackVO = new RentalMyTrackVo();
//
//			rentalMyTrackVO.setrNo(rNo == null? null : Integer.valueOf(rNo));
//			rentalMyTrackVO.setmemNo(memNo == null? null : Integer.valueOf(memNo));
//			rentalMyTrackVO.setexpRentalDate(expRentalDate);
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("trackVO", rentalMyTrackVO); // 含有輸入格式錯誤的trackVO物件,也存入req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("addTrack.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/***************************2.開始新增資料***************************************/
//			RentalMyTrackService tSvc = new RentalMyTrackService();
//			rentalMyTrackVO = tSvc.addTrack(Integer.valueOf(rNo), Integer.valueOf(memNo),
//					expRentalDate);
//
//			/***************************3.新增完成,準備轉交(Send the Success view)***********/
//			String url = "/rentalmytrack/listAllTrack.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
//
//		} // 新增結束
//
//
//		// 來自所有追蹤品頁面(listAllTrack.jsp)、刪除追蹤品頁面(deleteTrack.jsp)，刪除單筆的請求
//        if ("delete".equals(action)) {
//
//            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
////            Integer rNo = Integer.valueOf(req.getParameter("rNo"));
////            Integer memNo = Integer.valueOf(req.getParameter("memNo"));
//
//			/***********************1.接收請求參數 - 輸入格式的錯誤處理************************/
//
//			Integer rNo = null;
//			try {
//				rNo = Integer.valueOf(req.getParameter("rNo").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("rNo", "租借品編號請填數字");
//			}
//			// 檢查會員編號
//			Integer memNo = null;
//			try {
//				memNo = Integer.valueOf(req.getParameter("memNo").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("memNo", "會員編號請填數字");
//			} catch (NullPointerException nullPointerException) {
//				errorMsgs.put("memNo", "會員編號不能空白");
//			}
//
//			if (!errorMsgs.isEmpty()) {
////				req.setAttribute("trackVO", trackVO); // 含有輸入格式錯誤的trackVO物件,也存入req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/rentalmytrack/deleteTrack.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//
//            /***************************2.開始刪除資料***************************************/
//            RentalMyTrackService rentalMyTrackService = new RentalMyTrackService();
//            rentalMyTrackService.deleteTrack(rNo, memNo);
//
//            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
//            String url = "/rentalmytrack/listAllTrack.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功，轉交回送出刪除的來源網站
//            successView.forward(req, res);
//
//        } // 刪除結束
//
//
//        // 來自修改頁面(update_emp_input.jsp)，修改追蹤品資料的請求
//		if ("update".equals(action)) {
//
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			String rNoReg = "^([1-9]|[1-3][0-9]|40)$";
//
//			// 檢查租借品編號
//			Integer rNo = null;
//			try {
//				rNo = Integer.valueOf(req.getParameter("rNo").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("rNo", "租借品編號請填數字");
//			} catch (NullPointerException nullPointerException) {
//				errorMsgs.put("rNo", "租借品編號不能空白");
//			}
//			// 檢查會員編號
//			Integer memNo = null;
//			try {
//				memNo = Integer.valueOf(req.getParameter("memNo").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("memNo", "會員編號請填數字");
//			} catch (NullPointerException nullPointerException) {
//				errorMsgs.put("memNo", "會員編號不能空白");
//			}
//			// 檢查期望租借日期
//			Date expRentalDate = null;
//			try {
//				expRentalDate = Date.valueOf(req.getParameter("expRentalDate"));
//			} catch (IllegalArgumentException e) {
//				expRentalDate = new Date(System.currentTimeMillis());
//				errorMsgs.put("expRentalDate", "請輸入日期!");
//			}
//
//			RentalMyTrackVo rentalMyTrackVO = new RentalMyTrackVo();
//
//			rentalMyTrackVO.setrNo(rNo == null ? null : Integer.valueOf(rNo));
//			rentalMyTrackVO.setmemNo(memNo == null ? null : Integer.valueOf(memNo));
//			rentalMyTrackVO.setexpRentalDate(expRentalDate);
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("trackVO", rentalMyTrackVO); // 含有輸入格式錯誤的trackVO物件,也存入req
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/rentalmytrack/update_mytrack_input.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/***************************2.開始修改資料*****************************************/
//			RentalMyTrackService tSvc = new RentalMyTrackService();
//			rentalMyTrackVO = tSvc.updateTrack(Integer.valueOf(rNo), Integer.valueOf(memNo),
//					expRentalDate);
//
//			/***************************3.修改完成,準備轉交(Send the Success view)*************/
//			req.setAttribute("trackVO", rentalMyTrackVO); // 資料庫update成功後,正確的的empVO物件,存入req
//			String url = "listOneTrack.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//			successView.forward(req, res);
//
//		} // 修改結束
//
//		// 來自所有追蹤租借品頁面(listAllNO.jsp)，修改追蹤品資料的請求
//        if ("getOne_For_Update".equals(action)) { //
//
//            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//            req.setAttribute("errorMsgs", errorMsgs);
//
//            /***************************1.接收請求參數****************************************/
//            Integer rNo = Integer.valueOf(req.getParameter("rNo"));
//            Integer memNo = Integer.valueOf(req.getParameter("memNo"));
//
//            /***************************2.開始查詢資料****************************************/
//            RentalMyTrackService rentalMyTrackService = new RentalMyTrackService();
//            RentalMyTrackVo rentalMyTrackVO = rentalMyTrackService.getOneTrack(rNo, memNo);
//
//            /***************************3.查詢完成,準備轉交(Send the Success view)************/
//
//            String url = "update_mytrack_input.jsp";
//        	req.setAttribute("trackVO", rentalMyTrackVO);
//            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
//            successView.forward(req, res);
//        }
//
//
//		// 來自首頁(select_page.jsp)，查詢單筆的請求
//		if ("getOne_For_Display".equals(action)) {
//
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/***************************1.接收請求參數、輸入格式的錯誤處理**********************/
//
//			// 檢查租借品編號
//			Integer rNo = null;
//			try {
//				rNo = Integer.valueOf(req.getParameter("rNo").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("rNo", "租借品編號請填數字");
//			} catch (NullPointerException nullPointerException) {
//				errorMsgs.put("rNo", "租借品編號不能空白");
//			}
//			// 檢查會員編號
//			Integer memNo = null;
//			try {
//				memNo = Integer.valueOf(req.getParameter("memNo").trim());
//			} catch (NumberFormatException e) {
//				errorMsgs.put("memNo", "會員編號請填數字");
//			} catch (NullPointerException nullPointerException) {
//				errorMsgs.put("memNo", "會員編號不能空白");
//			}
//
//
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("select_page.jsp");
//				failureView.forward(req, res);
//				return;//程式中斷
//			}
//
//			/***************************2.開始查詢資料*****************************************/
//			RentalMyTrackService tSvc = new RentalMyTrackService();
//			RentalMyTrackVo rentalMyTrackVO = tSvc.getOneTrack(rNo, memNo);
//			if (rentalMyTrackVO == null) {
//				errorMsgs.put("noData", "查無資料");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("select_page.jsp");
//				failureView.forward(req, res);
//				return;//程式中斷
//			}
//
//			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//			req.setAttribute("trackVO", rentalMyTrackVO); // 資料庫取出的empVO物件,存入req
//			String url = "listOneTrack.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
//
//		} // 查詢單筆結束
//
//	} // doPost 結束
//
//}
