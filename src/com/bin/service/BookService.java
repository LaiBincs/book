package com.bin.service;

import com.bin.pojo.Book;
import com.bin.pojo.Page;

import java.util.List;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.service
 * @ClassName: BookService
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/13 11:20
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
