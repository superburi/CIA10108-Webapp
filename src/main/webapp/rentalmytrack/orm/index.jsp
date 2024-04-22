<%--
  Created by IntelliJ IDEA.
  User: TMP-214
  Date: 2024/4/22
  Time: 下午 08:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/rentalmytrack/orm/css/main.css">
    <title>Hibernate Demo</title>
</head>
<body>
<h1>這是一位後端人員作的網頁 QQ</h1>
<h2>員工系統</h2>
<a href="${pageContext.request.contextPath}/rentalmytrack/track.do?action=getAll">查詢所有追蹤租借品</a>
<br><br>
<h3><b>複合查詢 (使用 HQL Query)：</b></h3>
<form action="${pageContext.request.contextPath}/rentalmytrack/track.do" method="post">
    <p><label>租借品編號：</label></p>
    <input type="text" name="rNo"><br>
    <p><label>會員編號：</label></p>
    <input type="text" name="memNo"><br>
<%--    <select name="memNo">--%>
<%--        <option value="">選取職位</option>--%>
<%--        <option value="PRESIDENT">PRESIDENT</option>--%>
<%--        <option value="MANAGER">MANAGER</option>--%>
<%--        <option value="SALESMAN">SALESMAN</option>--%>
<%--        <option value="CLERK">CLERK</option>--%>
<%--        <option value="ANALYST">ANALYST</option>--%>
<%--    </select>--%>
    <p><label>加入追蹤時間</label></p>
    <input type="date" name="rTrackTime"><br>
<%--    ～ <input type="date" name="endhiredate"><br>--%>
    <p><label>期望租借時間</label></p>
    <input type="date" name="expRentalDate"><br>
<%--    ～ <input type="text" name="endsal"><br>--%>
    <p><input type="submit" value="送出"></p>
    <input type="hidden" name="action" value="compositeQuery">
</form>
</body>
</html>
