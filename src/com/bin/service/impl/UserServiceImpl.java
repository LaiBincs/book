package com.bin.service.impl;

import com.bin.dao.impl.UserDao;
import com.bin.dao.impl.UserDaoImpl;
import com.bin.pojo.User;
import com.bin.service.UserService;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.service.impl
 * @ClassName: UserServiceImpl
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/07 15:38
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        if (userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword()) == null) {
            System.out.println("用户名或密码错误，登录失败");
            return null;
        } else {
            System.out.println("登录成功");
            return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        }

    }

    @Override
    public boolean existsUsername(String username) {
        User user = userDao.queryUserByUsername(username);
        //判断用户名是否已经存在，存在则返回true
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }
}
