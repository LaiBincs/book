<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
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
            <td>单号</td>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${requestScope.orderList}" var="order">
            <tr>
                <td>${order.orderId}</td>
                <td>${order.createTime}</td>
                <td>${order.price}</td>
                <td>${order.statusValue.statusDesc}</td>
                <td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
                <c:if test="${order.statusValue.statusName=='DELIVERED'}">
                    <td>亲已收货成功</td>
                </c:if>
                <c:if test="${order.statusValue.statusName=='SHIPPED'}">
                    <td><a href="orderServlet?action=receiverOrder&orderId=${order.orderId}">确认收货</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>

<%--静态包含 页脚信息--%>
<%@include file="/common/footer.jsp" %>
</body>
</html>