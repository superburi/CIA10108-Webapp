<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="RentalMyTrack.trackvo.*"%>
<%@ page import="RentalMyTrack.trackdao.*"%>
<%@ page import="java.sql.Date"%>



<%
    // 註冊 mysql driver
    Class.forName("com.mysql.cj.jdbc.Driver");
    
    // 創建資料庫連接
    String URL = "jdbc:mysql://localhost:3306/mytest?serverTimezone=Asia/Taipei&characterEncoding=utf-8";
    String USER = "root";
    String PASSWORD = "Yuhan1207";
%>

<% 

// 	查詢
// 		RentalDao rentalDaoImpl = new RentalDaoImpl();
// 		RentalMyTrack rmt = rentalDaoImpl.get(1, 5);
// 		pageContext.setAttribute("rmt", rmt);
	
// 	新增
// 		RentalDao rentalDaoImpl = new RentalDaoImpl();
// 		RentalMyTrack rmt = new RentalMyTrack();
// 		rmt.setrNo(23);
// 		rmt.setmemNo(10);

// 		Date currentDate = new Date(System.currentTimeMillis());
// 		rmt.setrTrackTime(currentDate);
// 		rmt.setexpRentalDate(currentDate);

// 		int ans1 = rentalDaoImpl.set(rmt);
// 		pageContext.setAttribute("ans1", ans);

// 	更新
// 		RentalDao rentalDaoImpl = new RentalDaoImpl();
// 		RentalMyTrack rmt = new RentalMyTrack();
// 		rmt.setrNo(17);
// 		rmt.setmemNo(6);

// 		Date currentDate = new Date(System.currentTimeMillis());
// 		rmt.setrTrackTime(currentDate);

// 		int ans2 = rentalDaoImpl.update(rmt);
// 		pageContext.setAttribute("ans2", ans2);
	
%>


<html>
<body>
<h2>Hello World!</h2>

<h3>回傳1表示更新或是新增成功</h3>
<h3>回傳物件資料表示查詢成功</h3>

<p>${rmt}</p>
<p>${ans1}</p>
<p>${ans2}</p>


</body>
</html>
