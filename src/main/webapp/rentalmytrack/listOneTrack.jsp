<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.howard.rentalmytrack.vo.RentalMyTrackVo"%>

<%
	RentalMyTrackVo rentalMyTrackVO = (RentalMyTrackVo) request.getAttribute("trackVO");
    pageContext.setAttribute("rentalMyTrackVO", rentalMyTrackVO);
%>

<html>
<head>
    <title>租借品資料 - listOneTrack.jsp</title>

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
            width: 600px;
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
<body bgcolor='white'>

<table id="table-1">
    <tr><td>
        <h3>租借品資料 - listOneTrack.jsp</h3>
        <h4><a href="${pageContext.request.contextPath}/rentalmytrack/select_page.jsp"><img src="../images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
    </td></tr>
</table>

<table>
    <tr>
        <th>租借品編號</th>
        <th>會員編號</th>
        <th>加入商品追蹤時間</th>
        <th>期望租借日期</th>
    </tr>

    <tr>
        <td>${rentalMyTrackVO.rNo}</td>
        <td>${rentalMyTrackVO.memNo}</td>
        <td>${rentalMyTrackVO.rTrackTime}</td>
        <td>${rentalMyTrackVO.expRentalDate}</td>
    </tr>
</table>

</body>
</html>