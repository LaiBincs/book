package com.bin.test;

import com.bin.pojo.Book;
import com.bin.service.BookService;
import com.bin.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.test
 * @ClassName: BookServiceTest
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/13 11:24
 */
public class BookServiceTest {
    BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "哑舍II", "玄色", new BigDecimal(23.5), 20005500, 1500, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22, "哑舍III", "玄色", new BigDecimal(23.5), 20005500, 1500, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(15));
    }

    @Test
    public void queryBooks() {
        bookService.queryBooks().forEach(System.out::println);
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(0, 4, 10, 50));
    }
}