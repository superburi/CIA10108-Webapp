<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>
<%@ page import="java.util.*"%>
<%@ page import="com.howard.rentalmytrack.service.RentalMyTrackService" %>
<%@ page import="com.howard.rentalmytrack.vo.RentalMyTrackVo" %>
<%@ page import="com.howard.rentalmytrack.vo.RentalMyTrackVo" %>

<%
	RentalMyTrackService rentalMyTrackService = new RentalMyTrackService();
    List<RentalMyTrackVo> list = rentalMyTrackService.getAll();
    pageContext.setAttribute("list", list);
%>
<html>
<head>
    <title>查詢全部追蹤租借品 - listAllTrack.jsp</title>

    <style>
        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
        }
        table#table-1 h4 {
            color: red;
            display: block;
            margin-bottom: 1px;
        }
        h4 {
            color: blue;
            display: inline;
        }
    </style>

    <style>
        table {
            width: 800px;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }
        table, th, td {
            border: 1px solid #CCCCFF;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>

</head>
<body bgcolor="white">

<table id="table-1">
    <tr>
        <td>
            <h3>所有追蹤商品的資料 - listAllTrack.jsp</h3>
            <h4><a href="${pageContext.request.contextPath}/rentalmytrack/select_page.jsp"><img src="../images/back1.gif" width="100" height="32" border="0">回首頁</a> </h4>
        </td>
    </tr>
</table>



<table>
    <tr>
        <th>租借品編號</th>
        <th>會員編號</th>
        <th>加入商品追蹤時間</th>
        <th>期望租借日期</th>
    </tr>

    <c:forEach var="rentalMyTrackVO" items="${list}">
        <tr>
            <td>${rentalMyTrackVO.rNo}</td>
            <td>${rentalMyTrackVO.memNo}</td>
            <td>${rentalMyTrackVO.rTrackTime}</td>
            <td>${rentalMyTrackVO.expRentalDate}</td>

            <td>
                <form method="post" action="${pageContext.request.contextPath}/rentalmytrack/TrackController" style="margin-bottom: 0px;">
                    <input type="submit" value="修改">
                    <input type="hidden" name="rNo"  value="${rentalMyTrackVO.rNo}">
                    <input type="hidden" name="memNo"  value="${rentalMyTrackVO.memNo}">
                    <input type="hidden" name="action"	value="getOne_For_Update"></form>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/rentalmytrack/TrackController" style="margin-bottom: 0px;">
                    <input type="submit" value="刪除">
                    <input type="hidden" name="rNo"  value="${rentalMyTrackVO.rNo}">
                    <input type="hidden" name="memNo"  value="${rentalMyTrackVO.memNo}">
                    <input type="hidden" name="action"	value="delete"></form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>