<%--
  Created by IntelliJ IDEA.
  User: TMP-214
  Date: 2024/4/17
  Time: 下午 06:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>
<%@ page import="java.util.*"%>
<%@ page import="com.howard.rentalorderdetails.service.RentalOrderDetailsService" %>
<%@ page import="com.howard.rentalorderdetails.vo.RentalOrderDetails" %>

<%
    RentalOrderDetailsService rentalOrderDetailsService = new RentalOrderDetailsService();
    List<RentalOrderDetails> list = rentalOrderDetailsService.getAll();
    pageContext.setAttribute("list", list);
%>
<html>
<head>
    <title>查詢全部租借品訂單 - listAllDetail.jsp</title>

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
            <h3>所有訂單的資料 - listAllDetail.jsp</h3>
            <h4><a href="${pageContext.request.contextPath}/rentalorderdetails/select_page.jsp"><img src="../images/back1.gif" width="100" height="32" border="0">回首頁</a> </h4>
        </td>
    </tr>
</table>



<table>
    <tr>
        <th>租借品訂單編號</th>
        <th>租借品編號</th>
        <th>單價</th>
        <th>押金</th>
    </tr>

    <c:forEach var="rentalOrderDetailsVO" items="${list}">
        <tr>
            <td>${rentalOrderDetailsVO.rOrdNo}</td>
            <td>${rentalOrderDetailsVO.rNo}</td>
            <td>${rentalOrderDetailsVO.rPrice}</td>
            <td>${rentalOrderDetailsVO.rDesPrice}</td>

            <td>
                <form method="post" action="${pageContext.request.contextPath}/rentalorderdetails/RentalOrderDetailsController" style="margin-bottom: 0px;">
                    <input type="submit" value="修改">
                    <input type="hidden" name="rOrdNo"  value="${rentalOrderDetailsVO.rOrdNo}">
                    <input type="hidden" name="rNo"  value="${rentalOrderDetailsVO.rNo}">
                    <input type="hidden" name="action"	value="getOne_For_Update"></form>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/rentalorderdetails/RentalOrderDetailsController" style="margin-bottom: 0px;">
                    <input type="submit" value="刪除">
                    <input type="hidden" name="rOrdNo"  value="${rentalOrderDetailsVO.rOrdNo}">
                    <input type="hidden" name="rNo"  value="${rentalOrderDetailsVO.rNo}">
                    <input type="hidden" name="action"	value="delete"></form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
