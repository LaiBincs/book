package com.bin.web;

import com.bin.pojo.User;
import com.bin.service.UserService;
import com.bin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.web
 * @ClassName: LoginServlet
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/07 17:19
 */
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        //2、处理登录业务
        User user = userService.login(new User(null, username, password, null));

        //3、根据2中返回值 检查是否登录成功;如果等于null则代表登录失败。
        if (user != null) {
            //成功 跳转到登录成功界面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);

        } else {
            req.setAttribute("username",username);
            req.setAttribute("msg","用户名 或密码错误");
            //失败 跳转到登录界面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }

    }
}
