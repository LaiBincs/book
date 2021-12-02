<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
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

<%--静态包含管理菜单--%>
<%@include file="/common/manager_menu.jsp" %>

<div id="main">
    <h1>欢迎管理员进入后台管理系统</h1>
</div>

<%--静态包含 页脚信息--%>
<%@include file="/common/footer.jsp" %>
</body>
</html>