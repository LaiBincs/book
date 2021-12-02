<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%--静态包含 css、js、相对路径--%>
    <%@include file="/common/head.jsp" %>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
    <script type="text/javascript">
        //页面加载完之后
        $(function () {

            //为用户名输入框绑定一个失去焦点事件
            $("#username").blur(function () {
                //获取注册时的用户名
                var username = $("#username").val();
                $.getJSON("http://localhost:8080/book/userServlet", "action=ajaxExistsUsername&username=" + username, function (data) {
                    // alert(data.existsUsername);
                    if (data.existsUsername) {
                        $("span.errorMsg").text("输入的用户名已存在！");
                    } else {
                        $("span.errorMsg").text("输入的用户名可用！");

                    }


                });
            });

            //获取验证码绑定单击事件
            $("#code_img").click(function () {
                // 在事件响应的 function 函数中有一个 this 对象。这个 this 对象，是当前正在响应事件的 dom 对象
                // src 属性表示验证码 img 标签的 图片路径。它可读，可写
                // alert(this.src);
                this.src = ("${requestScope.scheme}kaptcha.jpg?d=" + new Date());
            });


            //为注册按钮绑定单击事件
            $("#sub_btn").click(function () {
                //1、验证用户名
                //获取用户名
                var usernameText = $("#username").val();
                //编写正则表达式
                var usernamepatt = /^\w{5,12}$/;
                //判断
                if (!usernamepatt.test(usernameText)) {
                    $("span.errorMsg").text("输入的用户名不合法");
                    return false;
                }
                $("span.errorMsg").text("");

                //2、验证密码
                //获取密码
                var passwordText = $("#password").val();
                //编写正则表达式
                var passwordpatt = /^\w{5,12}$/;
                //判断
                if (!passwordpatt.test(passwordText)) {
                    $("span.errorMsg").text("输入的密码不合法");
                    return false;
                }
                $("span.errorMsg").text("");

                var repwdText = $("#repwd").val();
                if (passwordText != repwdText) {
                    $("span.errorMsg").text("两次输入的密码不一致");
                    return false;
                }
                $("span.errorMsg").text("");

                //3.验证电子邮件
                //获取电子邮件
                var emailText = $("#email").val();
                //编写正则表达式
                var emaildpatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //判断
                if (!emaildpatt.test(emailText)) {
                    $("span.errorMsg").text("输入的电子邮件不合法");
                    return false;
                }
                $("span.errorMsg").text("");

                //4、验证码
                //本次只看是否有输入值
                //获取
                var codeText = $("#code").val();
                var codeText = codeText.trim().trim();
                //判断
                if (codeText == null || codeText == "") {
                    $("span.errorMsg").text("请输入验证码");
                    return false;
                }
                $("span.errorMsg").text("");
            });
        });
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
                        <%--<%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%>--%>
                        ${empty requestScope.msg ? "" : requestScope.msg }
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username"
                        <%--value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"--%>
                               value="${requestScope.username}"
                        />
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email"
                        <%--value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"--%>
                               value="${requestScope.email}"
                        />
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 100px;" name="code" id="code"/>
                        <img alt="" src="kaptcha.jpg"
                             style="float: right; margin-right: 70px;width: 100px;height: 40px;" id="code_img">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--静态包含 页脚信息--%>
<%@include file="/common/footer.jsp" %>
</body>
</html>