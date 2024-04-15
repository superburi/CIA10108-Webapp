<%--
  Created by IntelliJ IDEA.
  User: TMP-214
  Date: 2024/4/15
  Time: 上午 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>
<%@ page import="java.util.*"%>
<%@ page import="com.howard.rentalorder.service.RentalOrderService" %>
<%@ page import="com.howard.rentalorder.vo.RentalOrderVo" %>


<%
    RentalOrderService rentalOrderService = new RentalOrderService();
    List<RentalOrderVo> list = rentalOrderService.getAll();
    pageContext.setAttribute("list", list);
%>
<html>
<head>
    <title>查詢全部租借品訂單 - listAllOrder.jsp</title>

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
            <h3>所有租借品訂單的資料 - listAllOrder.jsp</h3>
            <h4><a href="${pageContext.request.contextPath}/rentalorder/select_page.jsp"><img src="../images/back1.gif" width="100" height="32" border="0">回首頁</a> </h4>
        </td>
    </tr>
</table>



<table>
    <tr>
        <th>租借品訂單編號</th>
        <th>會員編號</th>
        <th>訂購人姓名</th>
        <th>訂購人手機號碼</th>
        <th>訂購人Email</th>
        <th>收件人姓名</th>
        <th>收件人手機號碼</th>
        <th>取貨方式</th>
        <th>宅配住址</th>
        <th>付款方式</th>
        <th>訂單總金額</th>
        <th>押金總金額</th>
        <th>下單時間</th>
        <th>預計租借日期</th>
        <th>預計歸還日期</th>
        <th>實際歸還日期</th>
        <th>付款狀態</th>
        <th>訂單狀態</th>
        <th>歸還狀態</th>
        <th>歸還註記</th>
        <th>賠償金額</th>
    </tr>

    <c:forEach var="rentalOrderVO" items="${list}">
        <tr>
            <td>${rentalOrderVO.rOrdNo}</td>
            <td>${rentalOrderVO.memNo}</td>
            <td>${rentalOrderVO.rByrName}</td>
            <td>${rentalOrderVO.rByrPhone}</td>
            <td>${rentalOrderVO.rByrEmail}</td>
            <td>${rentalOrderVO.rRcvName}</td>
            <td>${rentalOrderVO.rRcvPhone}</td>
            <td>${rentalOrderVO.rTakeMethod}</td>
            <td>${rentalOrderVO.rAddr}</td>
            <td>${rentalOrderVO.rPayMethod}</td>
            <td>${rentalOrderVO.rAllPrice}</td>
            <td>${rentalOrderVO.rAllDepPrice}</td>
            <td>${rentalOrderVO.rOrdTime}</td>
            <td>${rentalOrderVO.rDate}</td>
            <td>${rentalOrderVO.rBackDate}</td>
            <td>${rentalOrderVO.rRealBackDate}</td>
            <td>${rentalOrderVO.rPayStat}</td>
            <td>${rentalOrderVO.rOrdStat}</td>
            <td>${rentalOrderVO.rtnStat}</td>
            <td>${rentalOrderVO.rtnRemark}</td>
            <td>${rentalOrderVO.rtnCompensation}</td>

            <td>
                <form method="post" action="${pageContext.request.contextPath}/rentalorder/RentalOrderController" style="margin-bottom: 0px;">
                    <input type="submit" value="修改">
                    <input type="hidden" name="rOrdNo"  value="${rentalOrderVO.rOrdNo}">
                    <input type="hidden" name="action"	value="getOne_For_Update"></form>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/rentalorder/RentalOrderController" style="margin-bottom: 0px;">
                    <input type="submit" value="刪除">
                    <input type="hidden" name="rOrdNo"  value="${rentalOrderVO.rOrdNo}">
                    <input type="hidden" name="action"	value="delete"></form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
