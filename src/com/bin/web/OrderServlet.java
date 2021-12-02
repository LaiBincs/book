package com.bin.web;

import com.bin.pojo.Cart;
import com.bin.pojo.Order;
import com.bin.pojo.OrderItem;
import com.bin.pojo.User;
import com.bin.service.OrderService;
import com.bin.service.impl.OrderServiceImpl;
import com.bin.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.web
 * @ClassName: OrderServlet
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 22:50
 */
public class OrderServlet extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();

    /**
     * @param req
     * @param resp
     * @ Description: 生成订单
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 22:52
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User) req.getSession().getAttribute("user");
        //验证是否登录
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = loginUser.getId();
        String orderId = null;
        orderId = orderService.createOrder(cart, userId);
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * @param req
     * @param resp
     * @ Description: 查看所有订单
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 22:52
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");
        //验证是否登录
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        List<Order> orderList = orderService.showAllOrders();
        req.setAttribute("orderList", orderList);
//        req.getSession().setAttribute("orderList", orderList);
//        resp.sendRedirect(req.getContextPath() + "/pages/manager/order_manager.jsp");
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    /**
     * @param req
     * @param resp
     * @ Description: 管理员设置发货
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 22:52
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");
        //验证是否登录
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        String orderId = req.getParameter("orderId");
        orderService.sendOrder(orderId);
        //重定向回原来所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * @param req
     * @param resp
     * @ Description: 查看订单详情
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 22:53
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");
        //验证是否登录
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        req.setAttribute("orderItems", orderItems);
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);
    }

    /**
     * @param req
     * @param resp
     * @ Description: 查看我的订单
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 22:53
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");
        //验证是否登录
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);
        List<Order> orderList = orderService.showMyOrders(userId);
        req.setAttribute("orderList", orderList);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    /**
     * @param req
     * @param resp
     * @ Description: 签收订单/确认收货
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 22:53
     */
    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");
        //验证是否登录
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        String orderId = req.getParameter("orderId");
        orderService.receiverOrder(orderId);
        //重定向回原来所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

}
