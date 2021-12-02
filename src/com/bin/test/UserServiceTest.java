package com.bin.test;

import com.bin.pojo.User;
import com.bin.service.UserService;
import com.bin.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.test
 * @ClassName: UserServiceTest
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/07 15:45
 */
public class UserServiceTest {
    private UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "tom", "1234", "tom@qq.com"));
    }

    @Test
    public void login() {
        User user = userService.login(new User(null, "admin", "admin", null));
        System.out.println(user.getId());

    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("peter"));
    }
}
