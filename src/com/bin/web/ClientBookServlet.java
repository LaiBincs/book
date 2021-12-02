package com.bin.web;

import com.bin.pojo.Book;
import com.bin.pojo.Page;
import com.bin.service.BookService;
import com.bin.service.impl.BookServiceImpl;
import com.bin.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.web
 * @ClassName: ClientBookServlet
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/14 15:37
 */
public class ClientBookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2、调用BookService.page(pageNo,pageSize)；返回Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3、将page对象保存到request域中
        req.setAttribute("page", page);
        //4、请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);

    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        //2、调用BookService.page(pageNo,pageSize)；返回Page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        StringBuilder url = new StringBuilder("client/bookServlet?action=pageByPrice");
        if (req.getParameter("min") != null) {
            url.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null) {
            url.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(url.toString());
        //3、将page对象保存到request域中
        req.setAttribute("page", page);
        //4、请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);

    }
}
