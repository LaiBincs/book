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
 * @ClassName: RegistServlet
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/07 16:22
 */
public class RegistServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //2、检查验证码是否正确
        if ("abcde".equalsIgnoreCase(code)) {
            //正确
            //检查用户名是否可用
            if (userService.existsUsername(username)) {
                System.out.println("用户名[" + username + "]已存在！");
                req.setAttribute("msg", "用户名[" + username + "]已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                //不可用 跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //可用 调用Service保存到数据库
                userService.registUser(new User(null, username, password, email));
                //跳转到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            //错误  跳回注册页面
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }


    }
}
