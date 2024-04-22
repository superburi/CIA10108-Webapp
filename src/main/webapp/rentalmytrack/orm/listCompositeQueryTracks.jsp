<%--
  Created by IntelliJ IDEA.
  User: TMP-214
  Date: 2024/4/22
  Time: 下午 08:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rentalmytrack/orm/css/main.css">
    <title>List Emps</title>
</head>
<body>
<h1>追蹤品列表</h1>
<br>
<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/rentalmytrack/orm/img/cat.png">
<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/rentalmytrack/orm/img/cat.png">
<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/rentalmytrack/orm/img/cat.png">
<table style="width:50%; text-align:center;">
    <tr>
        <th>租借品編號</th>
        <th>會員編號</th>
        <th>加入追蹤時間</th>
        <th>期望租借日期</th>
<%--        <th>薪資</th>--%>
<%--        <th>獎金</th>--%>
<%--        <th>所屬部門</th>--%>
    </tr>
    <c:forEach var="track" items="${trackList}">
        <tr>
            <td>${track.rental.rNo}</td>
            <td>${track.memberVO.memNo}</td>
            <td>${track.rTrackTime}</td>
            <td>${track.expRentalDate}</td>
<%--            <td>${emp.sal}</td>--%>
<%--            <td>${emp.comm}</td>--%>
<%--            <td>${emp.dept.dname}</td>--%>
        </tr>
    </c:forEach>
</table>
<br>
<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/rentalmytrack/orm/img/inversecat.png">
<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/rentalmytrack/orm/img/inversecat.png">
<img width="140px" height="100px" alt="要飛囉貓貓" src="${pageContext.request.contextPath}/rentalmytrack/orm/img/inversecat.png">
<br><br>

<a href="${pageContext.request.contextPath}/rentalmytrack/orm/index.jsp">回首頁</a>
</body>
</html>
