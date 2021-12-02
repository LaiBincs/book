package com.bin.dao.impl;

import com.bin.dao.BaseDao;
import com.bin.pojo.OrderItem;

import java.util.List;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.dao.impl
 * @ClassName: OrderItemDaoImpl
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 16:06
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select id,name,count,price,total_price totalPrice ,order_id orderId from t_order_item where order_id = ?";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
