<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%--静态包含 css、js、相对路径--%>
    <%@include file="/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }
    </style>
</head>
<body>
<%--静态包含 欢迎界面菜单--%>
<%@include file="/common/login_success_menu.jsp"%>

<div id="main">

    <h1>注册成功! <a href="index.jsp">转到主页</a></h1>

</div>
<%--静态包含 页脚信息--%>
<%@include file="/common/footer.jsp"%>
</body>
</html>