<%--
  Created by IntelliJ IDEA.
  User: TMP-214
  Date: 2024/4/15
  Time: 上午 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>新增租借品訂單 - addOrder.jsp</title>

    <style>
        table#table-1 {
            width: 450px;
            background-color: #CCCCFF;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 3px ridge Gray;
            height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
    <tr>
        <td>
            <h3>新增租借品訂單 - addOrder.jsp</h3>
            <h4><a href="${pageContext.request.contextPath}/rentalorder/select_page.jsp"><img src="../images/back1.gif" width="100" height="32" border="0">回首頁</a> </h4>
        </td>
    </tr>
</table>

<h3>新增追蹤品訂單 : </h3>

<!-- 錯誤表列 -->
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message.value}</li>
        </c:forEach>
    </ul>
</c:if>


<form METHOD="post" ACTION="${pageContext.request.contextPath}/rentalorder/RentalOrderController" >
    <ul>
        <li>
            <b>輸入租借品編號 : </b>
            <input type="text" name="rNo" value="" required>
        </li>
        <li>
            <b>輸入訂購人姓名 : </b>
            <input type="text" name="rByrName" value="" required>
        </li>
        <li>
            <b>輸入訂購人手機號碼 : </b>
            <input type="text" name="rByrPhone" value="" required>
        </li>
        <li>
            <b>輸入訂購人 Email : </b>
            <input type="text" name="rByrEmail" value="" required>
        </li>
        <li>
            <b>輸入收件人姓名 : </b>
            <input type="text" name="rRcvName" value="" required>
        </li>
        <li>
            <b>輸入收件人手機號碼 : </b>
            <input type="text" name="rRcvPhone" value="" required>
        </li>
        <li>
            <b>選擇取貨方式 : </b>
            <select size="1" name="rTakeMethod">
                <option value="0" selected>請選擇
                <option value="1">店取
                <option value="2">宅配到府
            </select>
        </li>
        <li>
            <b>輸入宅配地址 : </b>
            <input type="text" name="rAddr" value="" required>
        </li>
        <li>
            <b>選擇付款方式 : </b>
            <select size="1" name="rPayMethod">
                <option value="0" selected>請選擇
                <option value="1">綠界
                <option value="2">現場付款
            </select>
        </li>
        <li>
            <b>輸入訂單總金額(低配版) : </b>
            <input type="text" name="rAllPrice" value="" required>
        </li>
        <li>
            <b>輸入押金總金額(低配版) : </b>
            <input type="text" name="rAllDepPrice" value="" required>
        </li>
        <li>
            <b>輸入預計租借日期(低配版) : </b>
            <input type="datetime-local" name="rDate" value="" required>
        </li>
        <li>
            <b>輸入預計歸還日期(低配版) : </b>
            <input type="datetime-local" name="rBackDate" value="" required>
        </li>

    </ul>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="送出">
</form>

</body>
</html>
