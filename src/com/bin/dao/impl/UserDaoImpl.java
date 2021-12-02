package com.bin.dao.impl;

import com.bin.dao.BaseDao;
import com.bin.pojo.User;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.dao.impl
 * @ClassName: UserDaoImpl
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/07 12:16
 */
public class UserDaoImpl extends BaseDao implements UserDao {


    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id,username,password,email from t_user where username = ?";
        return queryForOne(User.class, sql, username);
    }


    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {

        String sql = "select id,username,password,email from t_user where username = ? and password =?";
        return queryForOne(User.class, sql, username, password);


    }


    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email)values(?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
