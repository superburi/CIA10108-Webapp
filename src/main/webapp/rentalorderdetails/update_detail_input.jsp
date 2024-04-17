<%--
  Created by IntelliJ IDEA.
  User: TMP-214
  Date: 2024/4/17
  Time: 下午 06:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.howard.rentalorderdetails.vo.RentalOrderDetails" %>



<%
    RentalOrderDetails rentalOrderDetails = (RentalOrderDetails) request.getAttribute("detailVo");
    pageContext.setAttribute("rentalOrderDetails", rentalOrderDetails);
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
            <h3>租借品訂單明細資料修改 - update_detail_input.jsp</h3>
            <h4><a href="${pageContext.request.contextPath}/rentalorderdetails/select_page.jsp"><img src="../images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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


<form METHOD="post" ACTION="${pageContext.request.contextPath}/rentalorderdetails/RentalOrderDetailsController" >
    <ul>
        <li>
            <b>輸入租借品訂單編號 : </b>
            <input type="text" name="rOrdNo" value="${rentalOrderDetails.rOrdNo}" required>
        </li>
        <li>
            <b>輸入租借品編號 : </b>
            <input type="text" name="rNo" value="${rentalOrderDetails.rNo}" required>
        </li>
        <li>
            <b>輸入單價 : </b>
            <input type="text" name="rPrice" value="${rentalOrderDetails.rPrice}" required>
        </li>
        <li>
            <b>輸入押金 : </b>
            <input type="text" name="rDesPrice" value="${rentalOrderDetails.rDesPrice}" required>
        </li>
    </ul>
    <input type="hidden" name="action" value="update">
    <input type="submit" value="送出">
</form>

</body>
</html>
