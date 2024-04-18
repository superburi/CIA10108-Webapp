<%--
  Created by IntelliJ IDEA.
  User: TMP-214
  Date: 2024/4/15
  Time: 上午 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>租借品訂單首頁</title>

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
    <tr><td><h3>RentalOrder</h3><h4>( MVC )</h4></td></tr>
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
    <li><a href='${pageContext.request.contextPath}/rentalorder/listAllOrder.jsp'>查詢全部租借品訂單</a><br></li>

    <jsp:useBean id="rentalOrderService" scope="page" class="com.howard.rentalorder.service.RentalOrderService" />

    <li>
        <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/rentalorder/RentalOrderController">
            <b>用租借品訂單編號查訂單 : </b>
            <input type="text" name="rOrdNo" value="" required>
            <br>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>
    <li>
        <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/rentalorder/RentalOrderController">
            <b>用訂購人姓名查訂單 : </b>
            <input type="text" name="rByrName" value="" required>
            <br>
            <input type="hidden" name="action" value="getAll_For_Display_ByName">
            <input type="submit" value="送出">
        </FORM>
    </li>
    <li>
        <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/rentalorder/RentalOrderController">
            <b>用會員編號查訂單 : </b>
            <input type="text" name="memNo" value="" required>
            <br>
            <input type="hidden" name="action" value="getAll_For_Display_ByMemNo">
            <input type="submit" value="送出">
        </FORM>
    </li>

</ul>


<h3>租借品訂單管理</h3>

<ul>
    <li><a href='addOrder.jsp'>新增租借品訂單</a></li>
</ul>

<ul>
    <li><a href='deleteOrder.jsp'>刪除租借品訂單</a></li>
</ul>



</body>
</html>
