package com.bin.dao;

import com.bin.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.dao.impl
 * @ClassName: BaseDao
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/07 11:32
 */
public abstract class BaseDao {
    //使用DbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * @param sql  sql语句
     * @param args 填充的占位符
     * @ Description:update() 方法用来执行：Insert\Update\Delete 语
     * @return: int 如果返回-1，则说明执行失败；如果成功执行，则返回影响的行数。
     * @author: laibin
     * @date: 2021/10/07 11:36
     */
    public int update(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args 填充的占位符
     * @ Description: 查询返回一个 javaBean 的 sql 语句
     * @return: T 返回的类型的泛型
     * @author: laibin
     * @date: 2021/10/07 11:42
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            T query = queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args 填充的占位符
     * @ Description: 查询返回多个 javaBean 的 sql 语句
     * @return: java.util.List<T>
     * @author: laibin
     * @date: 2021/10/07 12:01
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            List<T> list = queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @param sql  执行的sql语句
     * @param args 填充的占位符
     * @ Description:执行返回一行一列的 sql 语句
     * @return: java.lang.Object
     * @author: laibin
     * @date: 2021/10/07 12:04
     */
    public Object queryForSingleValue(String sql, Object... args) {
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            Object query = queryRunner.query(conn, sql, new ScalarHandler(), args);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
