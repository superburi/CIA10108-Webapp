package com.howard.rentalorderdetails.controller;

import com.howard.rentalorderdetails.service.RentalOrderDetailsService;
import com.howard.rentalorderdetails.vo.RentalOrderDetails;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;


@WebServlet("/rentalorderdetails/RentalOrderDetailsController")
public class RentalOrderDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 來自新增頁面(addDetail.jsp)，新增訂單明細的請求
        if ("insert".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

			// 檢查租借品訂單編號
			Integer rOrdNo = null;
			try {
				rOrdNo = Integer.valueOf(req.getParameter("rOrdNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("rOrdNo", "租借品訂單編號 : 請填數字!");
			}
			// 檢查租借品編號
			Integer rNo = null;
			try {
				rNo = Integer.valueOf(req.getParameter("rNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("rNo", "租借品編號 : 請填數字!");
			} catch (NullPointerException nullPointerException) {
				errorMsgs.put("rNo", "租借品編號 : 不能空白!");
			}
			// 檢查單價
			BigDecimal rPrice = null;
			try {
                rPrice = new BigDecimal(req.getParameter("rPrice"));
			} catch (IllegalArgumentException e) {
				errorMsgs.put("rPrice", "單價 : 請輸入數字!");
			}
			// 檢查押金
			BigDecimal rDesPrice = null;
			try {
				rDesPrice = new BigDecimal(req.getParameter("rDesPrice"));
			} catch (IllegalArgumentException e) {
				errorMsgs.put("rDesPrice", "押金 : 請輸入數字!");
			}


			RentalOrderDetails rentalOrderDetails = new RentalOrderDetails();
			rentalOrderDetails.setrOrdNo(rOrdNo);
			rentalOrderDetails.setrNo(rNo);
			rentalOrderDetails.setrPrice(rPrice);
			rentalOrderDetails.setrDesPrice(rDesPrice);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("detailVo", rentalOrderDetails); // 含有輸入格式錯誤的trackVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("addDetail.jsp");
				failureView.forward(req, res);
				return;
			}

			/***************************2.開始新增資料***************************************/
			RentalOrderDetailsService rentalOrderDetailsService = new RentalOrderDetailsService();
			RentalOrderDetails rentalOrderDetails1 = rentalOrderDetailsService.addDetail(rOrdNo, rNo,
																rPrice, rDesPrice);

			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/rentalorderdetails/listAllDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllDetail.jsp
			successView.forward(req, res);

		} // 新增結束


		// 來自所有明細頁面(listAllDetail.jsp)、刪除明細頁面(deleteOrder.jsp)，刪除單筆的請求
        if ("delete".equals(action)) {

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

			/***********************1.接收請求參數 - 輸入格式的錯誤處理************************/
			// 檢查租借品訂單編號
			Integer rOrdNo = null;
			try {
				rOrdNo = Integer.valueOf(req.getParameter("rOrdNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("rOrdNo", "租借品訂單編號 : 請填數字!");
			}
			// 檢查租借品編號
			Integer rNo = null;
			try {
				rNo = Integer.valueOf(req.getParameter("rNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("rNo", "租借品編號 : 請填數字!");
			} catch (NullPointerException nullPointerException) {
				errorMsgs.put("rNo", "租借品編號 : 不能空白!");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rentalorderdetails/deleteDetail.jsp");
				failureView.forward(req, res);
				return;
			}

            /***************************2.開始刪除資料***************************************/
			RentalOrderDetailsService rentalOrderDetailsService = new RentalOrderDetailsService();
			rentalOrderDetailsService.deleteDetail(rOrdNo, rNo);

            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
            String url = "/rentalorderdetails/listAllDetail.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功，轉交回送出刪除的來源網站
            successView.forward(req, res);

        } // 刪除結束


        // 來自修改頁面(update_emp_input.jsp)，修改追蹤品資料的請求
		if ("update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 檢查租借品訂單編號
			Integer rOrdNo = null;
			try {
				rOrdNo = Integer.valueOf(req.getParameter("rOrdNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("rOrdNo", "租借品訂單編號 : 請填數字!");
			}
			// 檢查租借品編號
			Integer rNo = null;
			try {
				rNo = Integer.valueOf(req.getParameter("rNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("rNo", "租借品編號 : 請填數字!");
			} catch (NullPointerException nullPointerException) {
				errorMsgs.put("rNo", "租借品編號 : 不能空白!");
			}
			// 檢查單價
			BigDecimal rPrice = null;
			try {
				rPrice = new BigDecimal(req.getParameter("rPrice"));
			} catch (IllegalArgumentException e) {
				errorMsgs.put("rPrice", "單價 : 請輸入數字!");
			}
			// 檢查押金
			BigDecimal rDesPrice = null;
			try {
				rDesPrice = new BigDecimal(req.getParameter("rDesPrice"));
			} catch (IllegalArgumentException e) {
				errorMsgs.put("rDesPrice", "押金 : 請輸入數字!");
			}

			RentalOrderDetails rentalOrderDetails = new RentalOrderDetails();
			rentalOrderDetails.setrOrdNo(rOrdNo);
			rentalOrderDetails.setrNo(rNo);
			rentalOrderDetails.setrPrice(rPrice);
			rentalOrderDetails.setrDesPrice(rDesPrice);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("DetailVo", rentalOrderDetails); // 含有輸入格式錯誤的trackVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rentalorderdetails/update_detail_input.jsp");
				failureView.forward(req, res);
				return;
			}

			/***************************2.開始修改資料*****************************************/
			RentalOrderDetailsService rentalOrderDetailsService = new RentalOrderDetailsService();
			RentalOrderDetails rentalOrderDetails1 = rentalOrderDetailsService.updateDetail(
					rOrdNo, rNo, rPrice, rDesPrice);

			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("detailVo", rentalOrderDetails1); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "listOneDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

		} // 修改結束

		// 來自所有追蹤租借品頁面(listAllNO.jsp)，修改追蹤品資料的請求
        if ("getOne_For_Update".equals(action)) { //

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數****************************************/
            Integer rOrdNo = Integer.valueOf(req.getParameter("rOrdNo"));
            Integer rNo = Integer.valueOf(req.getParameter("rNo"));

            /***************************2.開始查詢資料****************************************/
            RentalOrderDetailsService rentalOrderDetailsService = new RentalOrderDetailsService();
            RentalOrderDetails rentalOrderDetails = rentalOrderDetailsService.getOneDetail(rOrdNo, rNo);

            /***************************3.查詢完成,準備轉交(Send the Success view)************/

            String url = "update_detail_input.jsp";
        	req.setAttribute("detailVo", rentalOrderDetails);
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
            successView.forward(req, res);
        }


		// 來自首頁(select_page.jsp)，查詢單筆的請求
		if ("getOne_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數、輸入格式的錯誤處理**********************/

			// 檢查租借品訂單編號
			Integer rOrdNo = null;
			try {
				rOrdNo = Integer.valueOf(req.getParameter("rOrdNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("rOrdNo", "租借品訂單編號 : 請填數字");
			} catch (NullPointerException nullPointerException) {
				errorMsgs.put("rOrdNo", "租借品訂單編號 : 不能空白");
			}
			// 檢查租借品編號
			Integer rNo = null;
			try {
				rNo = Integer.valueOf(req.getParameter("rNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("rNo", "租借品編號 : 請填數字");
			} catch (NullPointerException nullPointerException) {
				errorMsgs.put("rNo", "租借品編號 : 不能空白");
			}


			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

			/***************************2.開始查詢資料*****************************************/
			RentalOrderDetailsService rentalOrderDetailsService = new RentalOrderDetailsService();
			RentalOrderDetails rentalOrderDetails = rentalOrderDetailsService.getOneDetail(rOrdNo, rNo);
			if (rentalOrderDetails == null) {
				errorMsgs.put("noData", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("detailVo", rentalOrderDetails); // 資料庫取出的empVO物件,存入req
			String url = "listOneDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

		} // 查詢單筆結束

	} // doPost 結束

}
