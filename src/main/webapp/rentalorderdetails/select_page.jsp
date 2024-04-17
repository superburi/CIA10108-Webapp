<%--
  Created by IntelliJ IDEA.
  User: TMP-214
  Date: 2024/4/17
  Time: 下午 06:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>租借品訂單明細首頁</title>

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
    <tr><td><h3>RentalOrderDetails</h3><h4>( MVC )</h4></td></tr>
</table>

<h3>資料查詢:</h3>

<!-- 錯誤表列 -->
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message.value}</li>
        </c:forEach>
    </ul>
</c:if>

<ul>
    <li><a href='${pageContext.request.contextPath}/rentalorderdetails/listAllDetail.jsp'>查詢全部追蹤租借品</a><br></li>

    <jsp:useBean id="rentalOrderDetailsService" scope="page" class="com.howard.rentalorderdetails.service.RentalOrderDetailsService" />

    <li>
        <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/rentalorderdetails/RentalOrderDetailsController">
            <b>輸入租借品訂單編號 : </b>
            <input type="text" name="rOrdNo" value="" required>
            <br>
            <b>輸入租借品編號 : </b>
            <input type="text" name="rNo" value="" required>
            <br>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>

</ul>


<h3>租借品追蹤管理</h3>

<ul>
    <li><a href='addDetail.jsp'>新增追蹤租借品</a></li>
</ul>


</body>
</html>
