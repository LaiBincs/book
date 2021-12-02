package com.bin.dao.impl;

import com.bin.dao.BaseDao;
import com.bin.pojo.Order;
import com.bin.pojo.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.dao.impl
 * @ClassName: OrderDaoImpl
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 15:50
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(order_id,create_time,price,`status`,user_id)values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select order_id orderId,create_time createTime,price,`status`,user_id userId from t_order order by createTime desc";
        return queryForList(Order.class, sql);
    }

    @Override
    public void changeOrderStatus(String orderId, Status status) {
        //String sql = "update t_book set name = ?,author = ?,price = ?,sales = ?,stock = ?,img_path = ? where id = ?";
        String sql = "update t_order set status = ? where order_id =?";
        update(sql, status.getStatusName(), orderId);
    }

    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        String sql = "select order_id orderId,create_time createTime,price,`status`,user_id userId from t_order where user_id = ? order by createTime desc";
        return queryForList(Order.class, sql, userId);
    }
}
