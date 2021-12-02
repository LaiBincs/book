package com.bin.test;

import com.bin.dao.impl.UserDaoImpl;
import com.bin.pojo.User;
import org.junit.Test;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.test
 * @ClassName: UserDaoTest
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/07 12:08
 */
public class UserDaoTest {
    UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("admin") == null) {
            System.out.println("用户名可用");
        } else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin", "admin") == null) {
            System.out.println("用户名或密码错误，登录失败");
        } else {
            System.out.println("查询成功");
            User user = userDao.queryUserByUsernameAndPassword("admin", "admin");
            System.out.println(user.getId());
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "peter", "123456", "peter@foxmail.com")));
    }
}
