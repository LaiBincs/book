package com.bin.dao.impl;

import com.bin.pojo.User;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.dao
 * @ClassName: UserDao
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/07 12:14
 */
public interface UserDao {
    /**
     * @param username 用户名
     * @ Description: 根据用户名查询用户信息
     * @return: 如果返回 null,说明没有这个用户。反之亦然
     * @author: laibin
     * @date: 2021/10/07 12:15
     */
    public User queryUserByUsername(String username);


    /**
     * @ Description: 据 用户名和密码查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return: 如果返回 null,说明用户名或密码错误,反之亦然
     * @author: laibin
     * @date: 2021/10/07 12:21
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * @ Description: 保存用户信息
     * @param user
     * @return: 返回-1 表示操作失败，其他是 sql 语句影响的行数
     * @author: laibin
     * @date: 2021/10/07 12:22
     */
    public int saveUser(User user);
}
