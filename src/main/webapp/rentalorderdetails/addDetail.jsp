<%--
  Created by IntelliJ IDEA.
  User: TMP-214
  Date: 2024/4/17
  Time: 下午 06:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>新增租借品訂單明細 - addDetail.jsp</title>

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
            <h3>新增租借品明細 - addDetail.jsp</h3>
            <h4><a href="${pageContext.request.contextPath}/rentalorderdetails/select_page.jsp"><img src="../images/back1.gif" width="100" height="32" border="0">回首頁</a> </h4>
        </td>
    </tr>
</table>

<h3>新增租借品訂單明細 : </h3>

<!-- 錯誤表列 -->
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
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
            <input type="text" name="rOrdNo" value="" required>
        </li>
        <li>
            <b>輸入租借品編號 : </b>
            <input type="text" name="rNo" value="" required>
        </li>
        <li>
            <b>輸入單價 : </b>
            <input type="text" name="rPrice" value="" required>
        </li>
        <li>
            <b>輸入押金 : </b>
            <input type="text" name="rDesPrice" value="" required>
        </li>
    </ul>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="送出">
</form>

</body>
</html>
