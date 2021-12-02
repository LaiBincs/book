package com.bin.service.impl;

import com.bin.dao.impl.BookDao;
import com.bin.dao.impl.BookDaoImpl;
import com.bin.pojo.Book;
import com.bin.pojo.Page;
import com.bin.service.BookService;

import java.util.List;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.service.impl
 * @ClassName: BookServiceImpl
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/13 11:22
 */
public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page();
        page.setPageSize(pageSize);
        //总记录数
        int pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        //总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);

        //当前页数据
        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForItems(begin, pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page();
        page.setPageSize(pageSize);
        //总记录数
        int pageTotalCount = bookDao.queryForPageTotalCount(min, max);
        page.setPageTotalCount(pageTotalCount);

        //总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);

        //当前页数据
        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForItems(begin, pageSize, min, max);
        page.setItems(items);
        return page;
    }
}
