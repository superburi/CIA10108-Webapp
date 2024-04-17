<%--
  Created by IntelliJ IDEA.
  User: TMP-214
  Date: 2024/4/17
  Time: 下午 06:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.howard.rentalorderdetails.vo.RentalOrderDetails" %>

<%
    RentalOrderDetails rentalOrderDetails = (RentalOrderDetails) request.getAttribute("detailVo");
    pageContext.setAttribute("rentalOrderDetails", rentalOrderDetails);
%>

<html>
<head>
    <title>明細資料 - listOneDetail.jsp</title>

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
        <h3>訂單明細資料 - listOneDetail.jsp</h3>
        <h4><a href="${pageContext.request.contextPath}/rentalorderdetails/select_page.jsp"><img src="../images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
    </td></tr>
</table>

<table>
    <tr>
        <th>租借品訂單編號</th>
        <th>租借品編號</th>
        <th>單價</th>
        <th>押金</th>
    </tr>

    <tr>
        <td>${rentalOrderDetails.rOrdNo}</td>
        <td>${rentalOrderDetails.rNo}</td>
        <td>${rentalOrderDetails.rPrice}</td>
        <td>${rentalOrderDetails.rDesPrice}</td>
    </tr>
</table>

</body>
</html>
