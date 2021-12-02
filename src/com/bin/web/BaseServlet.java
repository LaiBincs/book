package com.bin.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.web
 * @ClassName: BaseServlet
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/12 18:36
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决post请求中文乱码问题
        // 一定要在获取请求参数之前调用才有效
        req.setCharacterEncoding("UTF-8");
        //解决resp中文乱码
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        //使用反射进行优化
        try {
            //获取 action 业务鉴别字符串，获取相应的业务 方法反射对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //优化前
       /* if ("login".equals(action)) {
            login(req, resp);
        } else if ("regist".equals(action)) {
            regist(req, resp);
        }*/

    }
}
