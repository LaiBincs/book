package com.bin.web;

import com.bin.pojo.Book;
import com.bin.pojo.Page;
import com.bin.service.BookService;
import com.bin.service.impl.BookServiceImpl;
import com.bin.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.web
 * @ClassName: BookServlet
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/13 21:08
 */
public class BookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();


    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();

        //2.把全部图书保存到request域中
        req.setAttribute("books", books);

        //3.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //2.调用BookService.addBook()保存图书信息
        bookService.addBook(book);
        //3、跳到图书列表页面 使用重定向
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }


    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数并封装为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //2、调用BookService.updateBook(book)；修改图书
        bookService.updateBook(book);
        //3、重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2、调用book.Service.queryBookById(id) 得到修改的图书信息
        Book book = bookService.queryBookById(id);
        //3、把图书保存到request域中
        req.setAttribute("book", book);
        //4、请求转发到/pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService中的delete方法
        bookService.deleteBookById(id);
        //跳转回图书页面
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));


    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2、调用BookService.page(pageNo,pageSize)；返回Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3、将page对象保存到request域中
        req.setAttribute("page", page);
        //4、请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }

}
