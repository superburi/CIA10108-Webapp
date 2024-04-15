<%--
  Created by IntelliJ IDEA.
  User: TMP-214
  Date: 2024/4/15
  Time: �W�� 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>�s�W���ɫ~�q�� - addOrder.jsp</title>

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
            <h3>�s�W���ɫ~�q�� - addOrder.jsp</h3>
            <h4><a href="${pageContext.request.contextPath}/rentalorder/select_page.jsp"><img src="../images/back1.gif" width="100" height="32" border="0">�^����</a> </h4>
        </td>
    </tr>
</table>

<h3>�s�W�l�ܫ~�q�� : </h3>

<!-- ���~��C -->
<c:if test="${not empty errorMsgs}">
    <font style="color:red">�Эץ��H�U���~:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message.value}</li>
        </c:forEach>
    </ul>
</c:if>


<form METHOD="post" ACTION="${pageContext.request.contextPath}/rentalorder/RentalOrderController" >
    <ul>
        <li>
            <b>��J���ɫ~�s�� : </b>
            <input type="text" name="rNo" value="" required>
        </li>
        <li>
            <b>��J�q�ʤH�m�W : </b>
            <input type="text" name="rByrName" value="" required>
        </li>
        <li>
            <b>��J�q�ʤH������X : </b>
            <input type="text" name="rByrPhone" value="" required>
        </li>
        <li>
            <b>��J�q�ʤH Email : </b>
            <input type="text" name="rByrEmail" value="" required>
        </li>
        <li>
            <b>��J����H�m�W : </b>
            <input type="text" name="rRcvName" value="" required>
        </li>
        <li>
            <b>��J����H������X : </b>
            <input type="text" name="rRcvPhone" value="" required>
        </li>
        <li>
            <b>��ܨ��f�覡 : </b>
            <select size="1" name="rTakeMethod">
                <option value="0" selected>�п��
                <option value="1">����
                <option value="2">�v�t�쩲
            </select>
        </li>
        <li>
            <b>��J�v�t�a�} : </b>
            <input type="text" name="rAddr" value="" required>
        </li>
        <li>
            <b>��ܥI�ڤ覡 : </b>
            <select size="1" name="rPayMethod">
                <option value="0" selected>�п��
                <option value="1">���
                <option value="2">�{���I��
            </select>
        </li>
        <li>
            <b>��J�q���`���B(�C�t��) : </b>
            <input type="text" name="rAllPrice" value="" required>
        </li>
        <li>
            <b>��J����`���B(�C�t��) : </b>
            <input type="text" name="rAllDepPrice" value="" required>
        </li>
        <li>
            <b>��J�w�p���ɤ��(�C�t��) : </b>
            <input type="datetime-local" name="rDate" value="" required>
        </li>
        <li>
            <b>��J�w�p�k�٤��(�C�t��) : </b>
            <input type="datetime-local" name="rBackDate" value="" required>
        </li>

    </ul>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="�e�X">
</form>

</body>
</html>
