<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LAI
  Date: 2021/10/18
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%--静态包含 css、js、相对路径--%>
    <%@include file="/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<%--静态包含 欢迎界面菜单--%>
<%@include file="/common/login_success_menu.jsp" %>

<div id="main">

    <table>
        <tr>
            <td>名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>总价</td>
            <td>订单编号</td>
        </tr>
        <c:forEach items="${requestScope.orderItems}" var="items">
            <tr>
                <td>${items.name}</td>
                <td>${items.count}</td>
                <td>${items.price}</td>
                <td>${items.totalPrice}</td>
                <td>${items.orderId}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<%--静态包含 页脚信息--%>
<%@include file="/common/footer.jsp" %>
</body>
</html>