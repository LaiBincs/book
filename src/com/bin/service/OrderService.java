package com.bin.service;

import com.bin.pojo.Cart;
import com.bin.pojo.Order;
import com.bin.pojo.OrderItem;
import com.bin.pojo.Status;

import java.util.List;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.service
 * @ClassName: OrderService
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 20:15
 */
public interface OrderService {
    /**
     * @param cart
     * @param userId
     * @ Description: 生成订单
     * @return: java.lang.String
     * @author: laibin
     * @date: 2021/10/17 20:16
     */
    public String createOrder(Cart cart, Integer userId);

    /**
     * @param
     * @ Description: 查询全部订单
     * @return: java.util.List<com.bin.pojo.Order>
     * @author: laibin
     * @date: 2021/10/17 20:17
     */
    public List<Order> showAllOrders();

    /**
     * @param orderId 订单号
     * @ Description: 管理员设置发货
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 20:18
     */
    public void sendOrder(String orderId);

    /**
     * @param orderId
     * @ Description: 查看订单详情
     * @return: java.util.List<com.bin.pojo.OrderItem>
     * @author: laibin
     * @date: 2021/10/17 20:24
     */
    public List<OrderItem> showOrderDetail(String orderId);

    /**
     * @param userId
     * @ Description:查看我的订单
     * @return: java.util.List<com.bin.pojo.Order>
     * @author: laibin
     * @date: 2021/10/17 21:31
     */
    public List<Order> showMyOrders(Integer userId);

    /**
     * @param orderId 用户 签收订单/确认收货
     * @ Description:
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 20:25
     */
    public void receiverOrder(String orderId);

}
