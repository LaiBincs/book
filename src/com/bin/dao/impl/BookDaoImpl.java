package com.bin.dao.impl;

import com.bin.dao.BaseDao;
import com.bin.pojo.Book;

import java.util.List;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.dao.impl
 * @ClassName: BookDaoImpl
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/13 10:24
 */
public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?) ";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(int id) {
        String sql = "delete from t_book where id = ? ";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name = ?,author = ?,price = ?,sales = ?,stock = ?,img_path = ? where id = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        //img_path imgPath 为其取别名是为了 后面使用WebUtils时注入信息时调用其数据的set方法
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book where id = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book ";
        return queryForList(Book.class, sql);
    }

    @Override
    public int queryForPageTotalCount() {
        String sql = "select Count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public int queryForPageTotalCount(int min, int max) {
        String sql = "select Count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql, min, max);
        return count.intValue();
    }


    @Override
    public List<Book> queryForItems(int begin, int pageSize) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book  limit ?,?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public List<Book> queryForItems(int begin, int pageSize, int min, int max) {
        String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book  where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }
}
