package com.howard.rentalorder.controller;

import com.howard.rentalorder.service.RentalOrderService;
import com.howard.rentalorder.vo.RentalOrderVo;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.currentTimeMillis;


@WebServlet("/rentalorder/RentalOrderController")
public class RentalOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
//        res.setContentType("application/json");
//        PrintWriter out = res.getWriter();

        // 來自新增頁面(addTrack.jsp)，新增追蹤品的請求
        if ("insert".equals(action)) {

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

            // 檢查會員編號
            Integer memNo = null;
            try {
                memNo = Integer.valueOf(req.getParameter("memNo").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("memNo", "會員編號 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("memNo", "會員編號 : 不能空白!");
            }

            // 檢查訂購人姓名
            String nameReg = "^[\\u4e00-\\u9fa5_a-zA-Z]+$";
            String rByrName = req.getParameter("rByrName");
            if (rByrName == null || rByrName.trim().isEmpty()) {
                errorMsgs.put("rByrName", "訂購人姓名 : 請勿空白!");
            } else if (!rByrName.matches(nameReg)) {
                errorMsgs.put("rByrName", "訂購人姓名 : 只能是中文或英文!");
            }

            // 檢查訂購人手機號碼
            String phoneReg = "^[0][9][0-9]{8}$";
            String rByrPhone = req.getParameter("rByrPhone");
            if (rByrPhone == null || rByrPhone.trim().isEmpty()) {
                errorMsgs.put("rByrPhone", "訂購人手機號碼 : 請勿空白!");
            } else if (!rByrPhone.matches(phoneReg)) {
                errorMsgs.put("rByrPhone", "訂購人手機號碼 : 請輸入09開頭的10位數字!");
            }

            // 檢查訂購人 Email
//            String emailReg = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
            String emailReg = "^[a-zA-z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+$";
            String rByrEmail = req.getParameter("rByrEmail");
            if (rByrEmail == null || rByrEmail.trim().isEmpty()) {
                errorMsgs.put("rRcvName", "Email : 請勿空白!");
            } else if (!rByrEmail.matches(emailReg)) {
                errorMsgs.put("rByrEmail", "Email : 格式不正確喔!");
            }

            // 檢查收件人姓名 (直接拿 訂購人姓名 那邊的正則表達式來用)
            String rRcvName = req.getParameter("rRcvName");
            if (rRcvName == null || rRcvName.trim().isEmpty()) {
                errorMsgs.put("rRcvName", "收件人姓名 : 請勿空白!");
            } else if (!rRcvName.matches(nameReg)) {
                errorMsgs.put("rRcvName", "收件人姓名 : 只能是中文或英文!");
            }

            // 檢查收件人手機號碼 (直接拿 訂購人手機號碼 那邊的正則表達式來用)
            String rRcvPhone = req.getParameter("rRcvPhone");
            if (rByrPhone == null || rByrPhone.trim().isEmpty()) {
                errorMsgs.put("rByrPhone", "收件人手機號碼 : 請勿空白!");
            } else if (!rByrPhone.matches(phoneReg)) {
                errorMsgs.put("rByrPhone", "收件人手機號碼 : 請輸入09開頭的10位數字!");
            }

            // 檢查取貨方式
            Byte rTakeMethod = null;
            try {
                rTakeMethod = Byte.valueOf(req.getParameter("rTakeMethod"));
                if (rTakeMethod == 0) {
                    errorMsgs.put("rTakeMethod", "取貨方式 : 請選擇一種取貨方式!");
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }

            // 檢查宅配住址
            String addrReg = "^[\\u4e00-\\u9fa5a0-9]{1,100}$";
            String rAddr = req.getParameter("rAddr");
            if (rAddr == null || rAddr.trim().isEmpty()) {
                errorMsgs.put("rAddr", "宅配地址 : 請勿空白!");
            } else if (!rAddr.matches(addrReg)) {
                errorMsgs.put("rAddr", "宅配地址 : 格式不正確喔!");
            }

            // 檢查付款方式
            Byte rPayMethod = null;
            try {
                rPayMethod = Byte.valueOf(req.getParameter("rPayMethod"));
                if (rPayMethod == 0) {
                    errorMsgs.put("rTakeMethod", "付款方式 : 請選擇一種付款方式!");
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }

            // 檢查訂單總金額
            BigDecimal rAllPrice = null;
            try {
                rAllPrice = new BigDecimal(req.getParameter("rAllPrice"));
            } catch (NumberFormatException e) {
                errorMsgs.put("rAllPrice", "訂單總金額 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rAllPrice", "訂單總金額 : 不能空白!");
            }

            // 檢查押金總金額
            BigDecimal rAllDepPrice = null;
            try {
                rAllDepPrice = new BigDecimal(req.getParameter("rAllDepPrice"));
            } catch (NumberFormatException e) {
                errorMsgs.put("rAllDepPrice", "押金總金額 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rAllDepPrice", "押金總金額 : 不能空白!");
            }

            // 檢查下單時間 (不需要檢查)
            Timestamp rOrdTime = new Timestamp(currentTimeMillis());

            // 檢查預計租借日期
            Timestamp rDate = null;
            try {

                String rDate_str = req.getParameter("rDate");
                String str2 = rDate_str + ":00.00";
                rDate = Timestamp.valueOf(str2.replace("T", " "));
                System.out.println(rDate);

            } catch (IllegalArgumentException e) {
                errorMsgs.put("rDate", "預計租借日期 : 請輸入日期!");
            }

            // 檢查所選方案(應該要有方案參數傳遞來這，在這驗證)

            // 檢查預計歸還日期
            Timestamp rBackDate = null;
            try {

                String rBackDate_str = req.getParameter("rBackDate");
                String str2 = rBackDate_str + ":00.00";
                rBackDate = Timestamp.valueOf(str2.replace("T", " "));
                System.out.println(rBackDate);


            } catch (IllegalArgumentException e) {
                errorMsgs.put("rBackDate", "預計歸還日期 : 請輸入日期!");
            }
            int result = 0;
            try {
                result = rBackDate.compareTo(rDate);
            } catch (NullPointerException e) {
                System.out.println("吃到exception了");
            }

            if (result < 0) {
                errorMsgs.put("rBackDate", "預計歸還日期 : 不可早於預計租借日期!");
            } else if (result == 0) {
                errorMsgs.put("rBackDate", "預計歸還日期 : 不可和預計租借日期相同!");
            }
            // 這裡應該要是依照所選擇的方案來決定預計歸還日期，而不是手動調整，故以上 預計歸還日期 作法僅demo

            // 檢查實際歸還日期 (不須要檢查，創建訂單時，先放入當下時間，等到實際歸還時再用currentTimeStamp來更新訂單)
            Timestamp rRealBackDate = new Timestamp(currentTimeMillis());

            /* 檢查付款狀態 (不需要檢查，應該是依據訂購人在訂購當下付款與否而定)
            *
            * 若當下付款，則自動設為 1
            * 若當下沒有付款，則自動設為 0 ，待付款後自動更新訂單，設為 1
            *
            */
            Byte rPayStat = 0;

            // 檢查訂單狀態 (不需要檢查，初始狀態就是 40(訂單成立))
            Byte rOrdStat = 40;

            // 檢查歸還狀態 (不需要檢查，初始狀態就是 0(未歸還)，歸還後更新訂單為 1)
            Byte rtnStat = 0;

            /* 檢查歸還註記
            * 創建訂單時，預設前端傳送過來的請求，帶有參數"尚未歸還"
            * 實際歸還時才需檢查，由員工手動輸入歸還註記
            */
            String rtnRemark = "尚未歸還";

            /* 檢查賠償金額
            * 創建訂單時，預設前端傳送過來的請求，帶有參數 0
            * 實際歸還時才需檢查，由員工手動輸入賠償金額
            * 若有損壞，則必須有賠償金額
            */
            BigDecimal rtnCompensation = null;
            try {
                rtnCompensation = new BigDecimal(0);
            } catch (NumberFormatException e) {
                errorMsgs.put("rtnCompensation", "賠償金額 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rtnCompensation", "賠償金額 : 不能空白!");
            }

            RentalOrderVo rentalOrderVo = new RentalOrderVo();
            rentalOrderVo.setrOrdNo(0);
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

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("rentalOrderVo", rentalOrderVo); // 含有輸入格式錯誤的rentalorderVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("addOrder.jsp");
				failureView.forward(req, res);
				return;
			}

			/***************************2.開始新增資料***************************************/
			RentalOrderService rentalMyTrackService = new RentalOrderService();
			rentalMyTrackService.addOrder(memNo, rByrName, rByrPhone, rByrEmail, rRcvName, rRcvPhone,
                    rTakeMethod, rAddr, rPayMethod, rAllPrice, rAllDepPrice, rOrdTime, rDate, rBackDate,
                    rRealBackDate, rPayStat, rOrdStat, rtnStat, rtnRemark, rtnCompensation);

			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/rentalorder/listAllOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllOrder.jsp
			successView.forward(req, res);

		} // 新增結束

		// 來自所有訂單頁面(listAllOrder.jsp)、刪除訂單頁面(deleteOrder.jsp)，刪除單筆的請求
        if ("delete".equals(action)) {

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

			/***********************1.接收請求參數 - 輸入格式的錯誤處理************************/

            // 檢查訂單編號
			Integer rOrdNo = null;
			try {
				rOrdNo = Integer.valueOf(req.getParameter("rOrdNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("rOrdNo", "訂單編號請填數字");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rentalorder/deleteOrder.jsp");
				failureView.forward(req, res);
				return;
			}

            /***************************2.開始刪除資料***************************************/
            RentalOrderService rentalOrderService = new RentalOrderService();
            rentalOrderService.deleteOrder(rOrdNo);

            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
            String url = "/rentalorder/listAllOrder.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功，轉交回送出刪除的來源網站
            successView.forward(req, res);

        } // 刪除結束


        // 來自修改頁面(update_order_input.jsp)，修改租借品訂單資料的請求
		if ("update".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            // 檢查訂單編號
            Integer rOrdNo = null;
            try {
                rOrdNo = Integer.valueOf(req.getParameter("rOrdNo").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("rOrdNo", "訂單編號 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rOrdNo", "訂單編號 : 不能空白!");
            }

            // 檢查會員編號
            Integer memNo = null;
            try {
                memNo = Integer.valueOf(req.getParameter("memNo").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("memNo", "會員編號 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("memNo", "會員編號 : 不能空白!");
            }

            // 檢查訂購人姓名
            String nameReg = "^[\\u4e00-\\u9fa5_a-zA-Z]+$";
            String rByrName = req.getParameter("rByrName");
            if (rByrName == null || rByrName.trim().isEmpty()) {
                errorMsgs.put("rByrName", "訂購人姓名 : 請勿空白!");
            } else if (!rByrName.matches(nameReg)) {
                errorMsgs.put("rByrName", "訂購人姓名 : 只能是中文或英文!");
            }

            // 檢查訂購人手機號碼
            String phoneReg = "^[0][9][0-9]{8}$";
            String rByrPhone = req.getParameter("rByrPhone");
            if (rByrPhone == null || rByrPhone.trim().isEmpty()) {
                errorMsgs.put("rByrPhone", "訂購人手機號碼 : 請勿空白!");
            } else if (!rByrPhone.matches(phoneReg)) {
                errorMsgs.put("rByrPhone", "訂購人手機號碼 : 請輸入09開頭的10位數字!");
            }

            // 檢查訂購人 Email
            String emailReg = "^[a-zA-z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+$";
            String rByrEmail = req.getParameter("rByrEmail");
            if (rByrEmail == null || rByrEmail.trim().isEmpty()) {
                errorMsgs.put("rRcvName", "Email : 請勿空白!");
            } else if (!rByrEmail.matches(emailReg)) {
                errorMsgs.put("rByrEmail", "Email : 格式不正確喔!");
            }

            // 檢查收件人姓名 (直接拿 訂購人姓名 那邊的正則表達式來用)
            String rRcvName = req.getParameter("rRcvName");
            if (rRcvName == null || rRcvName.trim().isEmpty()) {
                errorMsgs.put("rRcvName", "收件人姓名 : 請勿空白!");
            } else if (!rRcvName.matches(nameReg)) {
                errorMsgs.put("rRcvName", "收件人姓名 : 只能是中文或英文!");
            }

            // 檢查收件人手機號碼 (直接拿 訂購人手機號碼 那邊的正則表達式來用)
            String rRcvPhone = req.getParameter("rRcvPhone");
            if (rByrPhone == null || rByrPhone.trim().isEmpty()) {
                errorMsgs.put("rByrPhone", "收件人手機號碼 : 請勿空白!");
            } else if (!rByrPhone.matches(phoneReg)) {
                errorMsgs.put("rByrPhone", "收件人手機號碼 : 請輸入09開頭的10位數字!");
            }

            // 檢查取貨方式
            Byte rTakeMethod = null;
            try {
                System.out.println(req.getParameter("rTakeMethod"));
                rTakeMethod = Byte.valueOf(req.getParameter("rTakeMethod"));
                System.out.println(rTakeMethod);
                if (rTakeMethod == 0) {
                    errorMsgs.put("rTakeMethod", "取貨方式 : 請選擇一種取貨方式!");
                }
            } catch (NumberFormatException e) {
                errorMsgs.put("rTakeMethod", "取貨方式 : 請選擇一種取貨方式!");
            }

            // 檢查宅配住址
            String addrReg = "^[\\u4e00-\\u9fa5a0-9]{1,100}$";
            String rAddr = req.getParameter("rAddr");
            if (rAddr == null || rAddr.trim().isEmpty()) {
                errorMsgs.put("rAddr", "宅配地址 : 請勿空白!");
            } else if (!rAddr.matches(addrReg)) {
                errorMsgs.put("rAddr", "宅配地址 : 格式不正確喔!");
            }

            // 檢查付款方式
            Byte rPayMethod = null;
            try {
                rPayMethod = Byte.valueOf(req.getParameter("rPayMethod"));
                if (rPayMethod == 0) {
                    errorMsgs.put("rTakeMethod", "付款方式 : 請選擇一種付款方式!");
                }
            } catch (NumberFormatException e) {
                errorMsgs.put("rTakeMethod", "付款方式 : 請選擇一種付款方式!");
            }

            // 檢查訂單總金額
            BigDecimal rAllPrice = null;
            try {
                rAllPrice = new BigDecimal(req.getParameter("rAllPrice"));
            } catch (NumberFormatException e) {
                errorMsgs.put("rAllPrice", "訂單總金額 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rAllPrice", "訂單總金額 : 不能空白!");
            }

            // 檢查押金總金額
            BigDecimal rAllDepPrice = null;
            try {
                rAllDepPrice = new BigDecimal(req.getParameter("rAllDepPrice"));
            } catch (NumberFormatException e) {
                errorMsgs.put("rAllDepPrice", "押金總金額 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rAllDepPrice", "押金總金額 : 不能空白!");
            }

            // 檢查下單時間 (不需要檢查)
            Timestamp rOrdTime = new Timestamp(currentTimeMillis());

            // 檢查預計租借日期
            Timestamp rDate = null;
            try {

                String rDate_str = req.getParameter("rDate");
                String str2 = rDate_str + ":00.00";
                rDate = Timestamp.valueOf(str2.replace("T", " "));
                System.out.println(rDate);

            } catch (IllegalArgumentException e) {
                errorMsgs.put("rDate", "預計租借日期 : 請輸入日期!");
            }

            // 檢查所選方案(應該要有方案參數傳遞來這，在這驗證)

            // 檢查預計歸還日期
            Timestamp rBackDate = null;
            try {

                String rBackDate_str = req.getParameter("rBackDate");
                String str2 = rBackDate_str + ":00.00";
                rBackDate = Timestamp.valueOf(str2.replace("T", " "));
                System.out.println(rBackDate);


            } catch (IllegalArgumentException e) {
                errorMsgs.put("rBackDate", "預計歸還日期 : 請輸入日期!");
            }
            int result = 0;
            try {
                result = rBackDate.compareTo(rDate);
            } catch (NullPointerException e) {
                System.out.println("吃到exception了");
            }

            if (result < 0) {
                errorMsgs.put("rBackDate", "預計歸還日期 : 不可早於預計租借日期!");
            } else if (result == 0) {
                errorMsgs.put("rBackDate", "預計歸還日期 : 不可和預計租借日期相同!");
            }
            // 這裡應該要是依照所選擇的方案來決定預計歸還日期，而不是手動調整，故以上 預計歸還日期 作法僅demo

            // 檢查實際歸還日期 (不須要檢查，創建訂單時，先放入當下時間，等到實際歸還時再用currentTimeStamp來更新訂單)
            Timestamp rRealBackDate = new Timestamp(currentTimeMillis());

            /* 檢查付款狀態 (不需要檢查，應該是依據訂購人在訂購當下付款與否而定)
             * 若當下付款，則自動設為 1
             * 若當下沒有付款，則自動設為 0 ，待付款後自動更新訂單，設為 1
             */
            Byte rPayStat = Byte.valueOf(req.getParameter("rPayStat"));



            // 檢查訂單狀態 (不需要檢查，初始狀態就是 40(訂單成立))
            Byte rOrdStat = Byte.valueOf(req.getParameter("rOrdStat"));
            // 檢查歸還狀態 (不需要檢查，初始狀態就是 0(未歸還)，歸還後更新訂單為 1)
            Byte rtnStat = Byte.valueOf(req.getParameter("rtnStat"));
            /* 檢查歸還註記
             * 創建訂單時，預設前端傳送過來的請求，帶有參數"尚未歸還"
             * 實際歸還時才需檢查，由員工手動輸入歸還註記
             */
            String rtnRemark = null;
            rtnRemark = req.getParameter("rtnRemark");
            if (rtnRemark == null || rtnRemark.trim().isEmpty()) {
                errorMsgs.put("rtnRemark", "歸還註記 : 請勿空白!");
            }

            /* 檢查賠償金額
             * 創建訂單時，預設前端傳送過來的請求，帶有參數 0
             * 實際歸還時才需檢查，由員工手動輸入賠償金額
             * 若有損壞，則必須有賠償金額
             */
            BigDecimal rtnCompensation = null;
            try {
                rtnCompensation = new BigDecimal(req.getParameter("rtnCompensation"));
            } catch (NumberFormatException e) {
                errorMsgs.put("rtnCompensation", "賠償金額 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("rtnCompensation", "賠償金額 : 不能空白!");
            }

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

            if (!errorMsgs.isEmpty()) {
                req.setAttribute("rentalOrderVo", rentalOrderVo); // 含有輸入格式錯誤的rentalorderVO物件,也存入req
                RequestDispatcher failureView = req
                        .getRequestDispatcher("update_order_input.jsp");
                failureView.forward(req, res);
                return;
            }
			/***************************2.開始修改資料*****************************************/
			RentalOrderService rentalOrderService1 = new RentalOrderService();
			RentalOrderVo rentalOrderVo1 = rentalOrderService1.updateOrder(rOrdNo, memNo, rByrName, rByrPhone,
                    rByrEmail, rRcvName, rRcvPhone, rTakeMethod, rAddr, rPayMethod, rAllPrice, rAllDepPrice,
                    rOrdTime, rDate, rBackDate, rRealBackDate, rPayStat, rOrdStat, rtnStat, rtnRemark,
                    rtnCompensation);

			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("rentalOrderVo", rentalOrderVo1); // 資料庫update成功後,正確的的orderVO物件,存入req
			String url = "listOneOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

		} // 修改結束

		// 來自所有追蹤租借品頁面(listAllNO.jsp)，修改追蹤品資料的請求
        if ("getOne_For_Update".equals(action)) { //

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數****************************************/
            // 租借訂單號碼
            Integer rOrdNo = Integer.valueOf(req.getParameter("rOrdNo"));
            // 會員編號
//            Integer memNo = Integer.valueOf(req.getParameter("memNo"));
//            // 訂購人姓名
//            String rByrName = req.getParameter("rByrName");
//            // 訂購人手機號碼
//            String rByrPhone = req.getParameter("rByrPhone");
//            // 訂購人 Email
//            String rByrEmail = req.getParameter("rByrEmail");
//            // 收件人姓名
//            String rRcvName = req.getParameter("rRcvName");
//            // 收件人手機號碼
//            String rRcvPhone = req.getParameter("rRcvPhone");
//            // 取貨方式
//            Byte rTakeMethod = Byte.valueOf(req.getParameter("rTakeMethod"));
//            // 宅配地址
//            String rAddr = req.getParameter("rAddr");
//            // 付款方式
//            Byte rPayMethod = Byte.valueOf(req.getParameter("rPayMethod"));
//            // 訂單總金額
//            BigDecimal rAllPrice = new BigDecimal(req.getParameter("rAllPrice"));
//            // 押金總金額
//            BigDecimal rAllDepPrice = new BigDecimal(req.getParameter("rAllDepPrice"));
//            // 下單日期
//            Timestamp rOrdTime = Timestamp.valueOf(req.getParameter("rOrdTime"));
//            // 預計租借日期
//            Timestamp rDate = Timestamp.valueOf(req.getParameter("rDate"));
//            // 預計歸還日期
//            Timestamp rBackDate = Timestamp.valueOf(req.getParameter("rBackDate"));
//            // 付款狀態
//            Byte rPayStat = Byte.valueOf(req.getParameter("rPayStat"));
//            // 實際歸還日期
//            Timestamp rRealBackDate = Timestamp.valueOf(req.getParameter("rRealBackDate"));
//            // 訂單狀態
//            Byte rOrdStat = Byte.valueOf(req.getParameter("rOrdStat"));
//            // 歸還狀態
//            Byte rtnStat = Byte.valueOf(req.getParameter("rtnStat"));
//            // 檢查歸還註記
//            String rtnRemark = req.getParameter("rtnRemark");
//            // 賠償金額
//            BigDecimal rtnCompensation = new BigDecimal(req.getParameter("rtnCompensation"));

            /***************************2.開始查詢資料****************************************/
            RentalOrderService rentalOrderService = new RentalOrderService();
            RentalOrderVo rentalOrderVo = rentalOrderService.getOneOrder(rOrdNo);

            /***************************3.查詢完成,準備轉交(Send the Success view)************/

            String url = "update_order_input.jsp";
        	req.setAttribute("rentalOrderVo", rentalOrderVo);
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
            successView.forward(req, res);

        } // 所有訂單頁面的修改結束

//
		// 來自首頁(select_page.jsp)，用訂單號碼查詢單筆的請求
		if ("getOne_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數、輸入格式的錯誤處理**********************/

			// 檢查租借品訂單編號
			Integer rOrdNo = null;
			try {
				rOrdNo = Integer.valueOf(req.getParameter("rOrdNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("rOrdNo", "租借品訂單編號 : 請填數字!");
			} catch (NullPointerException nullPointerException) {
				errorMsgs.put("rOrdNo", "租借品訂單編號 : 不能空白!");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

			/***************************2.開始查詢資料*****************************************/
			RentalOrderService rentalOrderService = new RentalOrderService();
			RentalOrderVo rentalOrderVo = rentalOrderService.getOneOrder(rOrdNo);

            /*========實驗========*/
//            System.out.println(rentalOrderVo);
//            Gson gson = new Gson();
//            String jsonRentalOrder = gson.toJson(rentalOrderVo);
//            System.out.println(jsonRentalOrder);
//            out.print(jsonRentalOrder);

            /*========實驗========*/

			if (rentalOrderVo == null) {
				errorMsgs.put("noData", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}



			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("rentalOrderVo", rentalOrderVo); // 資料庫取出的empVO物件,存入req
			String url = "listOneOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

		} // 用訂單號碼查詢單筆結束

        // 來自首頁(select_page.jsp)，用訂購人姓名查詢訂單的請求
        if ("getAll_For_Display_ByName".equals(action)) {

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數、輸入格式的錯誤處理**********************/

            // 檢查訂購人姓名
            String nameReg = "^[\\u4e00-\\u9fa5aa-zA-Z]{1,100}$";
            String rByrName = req.getParameter("rByrName");
            if (rByrName == null || rByrName.trim().isEmpty()) {
                errorMsgs.put("rByrName", "訂購人姓名 : 請勿空白!");
            } else if (!rByrName.matches(nameReg)) {
                errorMsgs.put("rByrName", "訂購人姓名 : 請填中文或英文!");
            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************2.開始查詢資料*****************************************/
            RentalOrderService rentalOrderService = new RentalOrderService();
            List<RentalOrderVo> rentalOrderVoList = rentalOrderService.getOrderByName(rByrName);
            if (rentalOrderVoList == null || rentalOrderVoList.isEmpty()) {
                errorMsgs.put("noData", "查無資料");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("rentalOrderVoList", rentalOrderVoList); // 資料庫取出的empVO物件,存入req
            String url = "/rentalorder/listOrdersSearchOn.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);

        } // 用訂購人姓名查詢訂單結束

        // 來自首頁(select_page.jsp)，用會員編號查詢訂單的請求
        if ("getAll_For_Display_ByMemNo".equals(action)) {

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數、輸入格式的錯誤處理**********************/

            // 檢查會員編號
            Integer memNo = null;
            try {
                memNo = Integer.valueOf(req.getParameter("memNo"));
            } catch (NumberFormatException e) {
                errorMsgs.put("memNo", "會員編號 : 請填數字!");
            } catch (NullPointerException nullPointerException) {
                errorMsgs.put("memNo", "會員編號 : 不能空白!");
            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************2.開始查詢資料*****************************************/
            RentalOrderService rentalOrderService = new RentalOrderService();
            List<RentalOrderVo> rentalOrderVoList = rentalOrderService.getOrderByMemNo(memNo);
            if (rentalOrderVoList == null || rentalOrderVoList.isEmpty()) {
                errorMsgs.put("noData", "查無資料");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("rentalOrderVoList", rentalOrderVoList); // 資料庫取出的empVO物件,存入req
            String url = "/rentalorder/listOrdersSearchOn.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);

        } // 用會員編號查詢訂單結束

    } // doPost 結束

}
