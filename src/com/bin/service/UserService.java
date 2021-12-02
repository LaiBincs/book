package com.bin.service;

import com.bin.pojo.User;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.service.impl
 * @ClassName: UserService
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/07 15:35
 */
public interface UserService {
    /**
     * @param user
     * @ Description: 注册
     * @return: void
     * @author: laibin
     * @date: 2021/10/07 15:37
     */
    public void registUser(User user);

    /**
     * @param user
     * @ Description:登录
     * @return: void
     * @author: laibin
     * @date: 2021/10/07 15:37
     */
    public User login(User user);

    /**
     * @param username
     * @ Description: 检查用户名是否存在
     * @return: 如果已经存在则返回true，反之，则返回false；
     * @author: laibin
     * @date: 2021/10/07 15:37
     */
    public boolean existsUsername(String username);

}
