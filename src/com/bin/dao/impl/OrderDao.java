package com.bin.dao.impl;

import com.bin.pojo.Order;
import com.bin.pojo.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.dao.impl
 * @ClassName: OrderDao
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 15:37
 */
public interface OrderDao {
    /**
     * @param order
     * @ Description:保存订单
     * @return: java.lang.String
     * @author: laibin
     * @date: 2021/10/17 15:42
     */
    public int saveOrder(Order order);

    /**
     * @param
     * @ Description:查询全部订单
     * @return: java.util.ArrayList<com.bin.pojo.Order>
     * @author: laibin
     * @date: 2021/10/17 15:42
     */
    public List<Order> queryOrders();


    /**
     * @param orderId
     * @param status
     * @ Description: 修改订单状态
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 15:42
     */
    public void changeOrderStatus(String orderId, Status status);

    /**
     * @param userId
     * @ Description:根据用户编号查询订单信息
     * @return: com.bin.pojo.Order
     * @author: laibin
     * @date: 2021/10/17 15:42
     */
    public List<Order> queryOrderByUserId(Integer userId);
}
