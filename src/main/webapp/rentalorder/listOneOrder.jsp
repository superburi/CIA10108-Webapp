<%--
  Created by IntelliJ IDEA.
  User: TMP-214
  Date: 2024/4/15
  Time: 上午 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.howard.rentalorder.vo.RentalOrderVo"%>

<%
    RentalOrderVo rentalOrderVo = (RentalOrderVo) request.getAttribute("rentalOrderVo");
    pageContext.setAttribute("rentalOrderVo", rentalOrderVo);
%>

<html>
<head>
    <title>租借品訂單資料 - listOneOrder.jsp</title>

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
            width: 600px;
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
<body bgcolor='white'>

<table id="table-1">
    <tr><td>
        <h3>租借品資料 - listOneOrder.jsp</h3>
        <h4><a href="${pageContext.request.contextPath}/rentalorder/select_page.jsp"><img src="../images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
    </td></tr>
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

    <tr>
        <td>${rentalOrderVo.rOrdNo}</td>
        <td>${rentalOrderVo.memNo}</td>
        <td>${rentalOrderVo.rByrName}</td>
        <td>${rentalOrderVo.rByrPhone}</td>
        <td>${rentalOrderVo.rByrEmail}</td>
        <td>${rentalOrderVo.rRcvName}</td>
        <td>${rentalOrderVo.rRcvPhone}</td>
        <td>${rentalOrderVo.rTakeMethod}</td>
        <td>${rentalOrderVo.rAddr}</td>
        <td>${rentalOrderVo.rPayMethod}</td>
        <td>${rentalOrderVo.rAllPrice}</td>
        <td>${rentalOrderVo.rAllDepPrice}</td>
        <td>${rentalOrderVo.rOrdTime}</td>
        <td>${rentalOrderVo.rDate}</td>
        <td>${rentalOrderVo.rBackDate}</td>
        <td>${rentalOrderVo.rRealBackDate}</td>
        <td>${rentalOrderVo.rPayStat}</td>
        <td>${rentalOrderVo.rOrdStat}</td>
        <td>${rentalOrderVo.rtnStat}</td>
        <td>${rentalOrderVo.rtnRemark}</td>
        <td>${rentalOrderVo.rtnCompensation}</td>
    </tr>
</table>

</body>
</html>
