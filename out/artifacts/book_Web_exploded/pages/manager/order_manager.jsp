<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%--静态包含 css、js、相对路径--%>
    <%@include file="/common/head.jsp" %>
</head>
<body>
<%--静态包含管理菜单--%>
<%@include file="/common/manager_menu.jsp" %>

<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>详情</td>
            <td>发货</td>
        </tr>
        <c:forEach items="${requestScope.orderList}" var="order">
            <tr>
                <td>${order.createTime}</td>
                <td>${order.price}</td>
                <td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
                <c:if test="${order.statusValue.statusName=='PROCESSING'}">
                    <td><a href="orderServlet?action=sendOrder&orderId=${order.orderId}">点击发货</a></td>
                </c:if>
                <c:if test="${order.statusValue.statusName=='SHIPPED'}">
                    <td>已发货</td>
                </c:if>
                <c:if test="${order.statusValue.statusName=='DELIVERED'}">
                    <td>已签收</td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
<%--静态包含 页脚信息--%>
<%@include file="/common/footer.jsp" %>
</body>
</html>