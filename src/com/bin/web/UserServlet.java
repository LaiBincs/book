package com.bin.web;

import com.bin.pojo.User;
import com.bin.service.UserService;
import com.bin.service.impl.UserServiceImpl;
import com.bin.utils.CookieUtils;
import com.bin.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.web
 * @ClassName: UserServlet
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/12 18:13
 */
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        String username = req.getParameter("username");

        //2、处理登录业务
        //把所有请求的参数都注入到 user 对象中
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        User loginUser = userService.login(user);

        //3、根据2中返回值 检查是否登录成功;如果等于null则代表登录失败。
        if (loginUser != null) {
            //保存登录用户信息到Session域中
            HttpSession session = req.getSession();
            session.setAttribute("user", loginUser);
            System.out.println(loginUser.getId());
            System.out.println(loginUser.getUsername());
            //成功 跳转到登录成功界面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);


        } else {
            //将信息保存到request静态域中
            req.setAttribute("username", username);
            req.setAttribute("msg", "用户名或密码错误");
            // 失败 跳转到登录界面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }

    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、销毁Session中的用户登录信息(或者销毁Session)
        Cookie cookie = CookieUtils.findCookie("JSESSIONID", req.getCookies());
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        //2、重定向回首页
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取ajax请求过来的参数
        String username = req.getParameter("username");
        //2、调用userServic.existsUsername方法查询数据库中是否存在数据
        boolean existsUsername = userService.existsUsername(username);
        //3、将结果存入map中，
        HashMap<String, Object> map = new HashMap<>();
        map.put("existsUsername", existsUsername);
        //4、将map转换为Json
        Gson gson = new Gson();
        String mapJson = gson.toJson(map);
        resp.getWriter().write(mapJson);
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //1、获取请求参数
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //2、检查验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)) {
            //正确
            //检查用户名是否可用
            if (userService.existsUsername(username)) {
                //将信息保存到request静态域中
                req.setAttribute("msg", "用户名[" + username + "]已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                //不可用 跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //把所有请求的参数都注入到 user 对象中
                User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
                //可用 调用Service保存到数据库
                userService.registUser(user);
                //跳转到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            //错误  跳回注册页面
            //将信息保存到request静态域中
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }


}
