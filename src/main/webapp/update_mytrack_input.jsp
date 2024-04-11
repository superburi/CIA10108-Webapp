<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="RentalMyTrack.trackvo.Track" %>


<%
     Track trackVO = (Track) request.getAttribute("trackVO");
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
	            <h3>追蹤商品資料修改 - update_mytrack_input.jsp</h3>
	            <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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


	<form METHOD="post" ACTION="RentalMyTrack.TrackController" >
		<ul>
			<li>
				<b>輸入租借品編號 : </b>
	            <input type="text" name="rNo" value="${trackVO.rNo}" required>
			</li>
			<li>
				<b>輸入會員編號 : </b>
	            <input type="text" name="memNo" value="${trackVO.memNo}" required>
			</li>
			<li>
				<b>輸入期望租借日期 : </b>
	            <input type="date" name="expRentalDate" value="${trackVO.expRentalDate}">
			</li>
		</ul>
		<input type="hidden" name="action" value="update">
		<input type="submit" value="送出">
	</form>

</body>
</html>