package com.bin.test;

import com.bin.dao.impl.BookDao;
import com.bin.dao.impl.BookDaoImpl;
import com.bin.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.test
 * @ClassName: BookDaoTest
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/13 10:44
 */
public class BookDaoTest {
    BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "平方的世界", "路遥", new BigDecimal(79), 200000000, 15000, null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "平凡的世界", "路遥", new BigDecimal(79), 200000000, 15000, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(12));
    }

    @Test
    public void queryBooks() {
        bookDao.queryBooks().forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount(10, 50));
    }

    @Test
    public void queryForItems() {
        bookDao.queryForItems(0, 4, 10, 50).forEach(System.out::println);
    }
}