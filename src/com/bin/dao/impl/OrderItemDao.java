package com.bin.dao.impl;

import com.bin.pojo.OrderItem;

import java.util.List;

/**
 * @ProjectName: OrderItemDao程序
 * @Package: com.bin.dao.impl
 * @ClassName: OrderItemDao
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 15:43
 */
public interface OrderItemDao {
    /**
     * @param
     * @ Description:保存订单项
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 15:45
     */
    public int saveOderItem(OrderItem orderItem);


    /**
     * @param orderId
     * @ Description:根据订单号查询订单明细
     * @return: com.bin.pojo.OrderItem
     * @author: laibin
     * @date: 2021/10/17 15:46
     */
    public List<OrderItem> queryOrderItemByOrderId(String orderId);
}
