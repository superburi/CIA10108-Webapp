<%--
  Created by IntelliJ IDEA.
  User: TMP-214
  Date: 2024/4/15
  Time: 下午 09:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.howard.rentalorder.vo.RentalOrderVo" %>


<%
    RentalOrderVo rentalOrderVo = (RentalOrderVo) request.getAttribute("rentalOrderVo");
    pageContext.setAttribute("rentalOrderVo", rentalOrderVo);
%>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <title>修改租借品 - update_notice_input.jsp</title>

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
<body bgcolor="white">

<table id="table-1">
    <tr>
        <td>
            <h3>租借訂單資料修改 - update_order_input.jsp</h3>
            <h4><a href="${pageContext.request.contextPath}/rentalorder/select_page.jsp"><img src="../images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
        </td>
    </tr>
</table>

<h3>資料修改:</h3>
<%--  錯誤表列  --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message.value}</li>
        </c:forEach>
    </ul>
</c:if>


<form METHOD="post" ACTION="${pageContext.request.contextPath}/rentalorder/RentalOrderController">
    <ul>
        <li>
            <b>租借品訂單編號 : </b>
            <input type="hidden" name="rOrdNo" value="${rentalOrderVo.rOrdNo}" >
        </li>
        <li>
            <b>會員編號 : </b>
            <input type="hidden" name="memNo" value="${rentalOrderVo.memNo}" >
        </li>
        <li>
            <b>輸入訂購人姓名 : </b>
            <input type="text" name="rByrName" value="${rentalOrderVo.rByrName}" required>
        </li>
        <li>
            <b>輸入訂購人手機號碼 : </b>
            <input type="text" name="rByrPhone" value="${rentalOrderVo.rByrPhone}" required>
        </li>
        <li>
            <b>輸入訂購人 Email : </b>
            <input type="text" name="rByrEmail" value="${rentalOrderVo.rByrEmail}" required>
        </li>
        <li>
            <b>輸入收件人姓名 : </b>
            <input type="text" name="rRcvName" value="${rentalOrderVo.rRcvName}" required>
        </li>
        <li>
            <b>輸入收件人手機號碼 : </b>
            <input type="text" name="rRcvPhone" value="${rentalOrderVo.rRcvPhone}" required>
        </li>
        <li>
            <b>取貨方式 : </b>
            <select size="1" name="rTakeMethod" typeof="hidden">
                <option value="0">請選擇
                <option value="1" ${rentalOrderVo.rTakeMethod == 1 ? "selected" : ""}>店取
                <option value="2" ${rentalOrderVo.rTakeMethod == 2 ? "selected" : ""}>宅配到府
            </select>
        </li>
        <li>
            <b>輸入宅配地址 : </b>
            <input type="${rentalOrderVo.rTakeMethod == 2 ? "text" : "hidden"}" name="rAddr" value="${rentalOrderVo.rAddr}" >
        </li>
        <li>
            <b>選擇付款方式 : </b>
            <select size="1" name="rPayMethod" typeof="hidden">
                <option value="0" selected>請選擇
                <option value="1" ${rentalOrderVo.rPayMethod == 1 ? "selected" : ""}>綠界
                <option value="2" ${rentalOrderVo.rPayMethod == 2 ? "selected" : ""}>現場付款
            </select>
        </li>
        <li>
            <b>輸入訂單總金額(低配版) : </b>
            <input type="text" name="rAllPrice" value="${rentalOrderVo.rAllPrice}" required>
        </li>
        <li>
            <b>輸入押金總金額(低配版) : </b>
            <input type="text" name="rAllDepPrice" value="${rentalOrderVo.rAllDepPrice}" required>
        </li>
        <li>
            <b>輸入預計租借日期(低配版) : </b>
            <input type="datetime-local" name="rDate" value="${rentalOrderVo.rDate}" required>
        </li>
        <li>
            <b>輸入預計歸還日期(低配版) : </b>
            <input type="datetime-local" name="rBackDate" value="${rentalOrderVo.rBackDate}" required>
        </li>
        <li>
            <b>輸入付款狀態 : </b>
            <select size="1" name="rPayStat" required>
                <option value="0" ${rentalOrderVo.rPayStat == 0 ? "selected" : ""}>未付款
                <option value="1" ${rentalOrderVo.rPayStat == 1 ? "selected" : ""}>已付款
            </select>
        </li>
        <li>
            <b>輸入訂單狀態 : </b>
            <select size="1" name="rOrdStat" required>
                <option value="0" ${rentalOrderVo.rOrdStat == 0 ? "selected" : ""}>訂單取消
                <option value="10" ${rentalOrderVo.rOrdStat == 10 ? "selected" : ""}>揀貨中
                <option value="20" ${rentalOrderVo.rOrdStat == 20 ? "selected" : ""}>配送中
                <option value="30" ${rentalOrderVo.rOrdStat == 30 ? "selected" : ""}>等待取貨
                <option value="40" ${rentalOrderVo.rOrdStat == 40 ? "selected" : ""}>訂單成立
                <option value="50" ${rentalOrderVo.rOrdStat == 50 ? "selected" : ""}>訂單完成
                <option value="81" ${rentalOrderVo.rOrdStat == 81 ? "selected" : ""}>申請換貨
                <option value="82" ${rentalOrderVo.rOrdStat == 82 ? "selected" : ""}>換貨中
            </select>
        </li>
        <li>
            <b>輸入歸還狀態 : </b>
            <select size="1" name="rtnStat" required>
                <option value="0" ${rentalOrderVo.rtnStat == 0 ? "selected" : ""}>未歸還
                <option value="1" ${rentalOrderVo.rtnStat == 1 ? "selected" : ""}>已歸還
            </select>
        </li>
        <li>
            <b>輸入歸還註記 : </b>
            <input type="text" name="rtnRemark" value="${rentalOrderVo.rtnRemark}" required>
        </li>
        <li>
            <b>輸入賠償金額 : </b>
            <input type="text" name="rtnCompensation" value="${rentalOrderVo.rtnCompensation}" required>
        </li>

    </ul>
    <input type="hidden" name="rOrdTime" value="${rentalOrderVo.rOrdTime}">
    <input type="hidden" name="rRealBackDate" value="${rentalOrderVo.rRealBackDate}">
    <input type="hidden" name="action" value="update">
    <input type="submit" value="送出">
</form>

</body>
</html>