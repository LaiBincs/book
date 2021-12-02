package com.bin.dao.impl;

import com.bin.pojo.Book;

import java.util.List;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.dao
 * @ClassName: BookDao
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/13 9:52
 */
public interface BookDao {
    public int addBook(Book book);

    public int deleteBookById(int id);

    public int updateBook(Book book);

    public Book queryBookById(int id);

    public List<Book> queryBooks();


    public int queryForPageTotalCount();

    public int queryForPageTotalCount(int min, int max);

    public List<Book> queryForItems(int begin, int pageSize);

    List<Book> queryForItems(int begin, int pageSize, int min, int max);
}
