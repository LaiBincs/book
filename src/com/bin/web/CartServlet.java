package com.bin.web;

import com.bin.pojo.Book;
import com.bin.pojo.Cart;
import com.bin.pojo.CartItem;
import com.bin.service.BookService;
import com.bin.service.impl.BookServiceImpl;
import com.bin.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @ProjectName: CartServlet程序
 * @Package: com.bin.web
 * @ClassName: CartServlet
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 11:44
 */
public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    /**
     * @param req
     * @param resp
     * @ Description:添加商品
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 11:47
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取商品id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用 bookService.queryBookById(id):Book 得到图书的
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为 CartItem 商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem()添加图书
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        System.out.println("请求头 Referer 的值：" + req.getHeader("Referer"));
        //最后一个添加商品的名称
        req.getSession().setAttribute("lastName", cartItem.getName());
        //重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));

    }

    /**
     * @param req
     * @param resp
     * @ Description: 通过ajax请求对数据的局部更新
     * @return: void
     * @author: laibin
     * @date: 2021/10/20 15:14
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取商品id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用 bookService.queryBookById(id):Book 得到图书的
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为 CartItem 商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem()添加图书
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        //将最后一次添加的商品名称存入session域中
        req.getSession().setAttribute("lastName", cartItem.getName());
        //将当前商品总数量和最后一个添加商品的名称放进map中
        HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("cartTotalCount", cart.getTotalCount());
        cartMap.put("cartLastName", cartItem.getName());
        //将map转换为JSON字符串
        Gson gson = new Gson();
        String cartMapJsonStr = gson.toJson(cartMap);
        //响应回传数据
        resp.getWriter().write(cartMapJsonStr);

    }

    /**
     * @param req
     * @param resp
     * @ Description: 删除商品
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 13:06
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session中的购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取需要删除的商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        if (cart != null) {
            cart.deleteItem(id);
            //重定向回原来商品所在的地址页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * @param req
     * @param resp
     * @ Description: 清空购物车
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 13:23
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session中的购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
            //重定向回原来商品所在的地址页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /// 获取请求的参数 商品编号 、商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            //修改商品数量
            cart.updateCount(id, count);
            //重定向回原来商品所在的地址页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
