package com.bin.service.impl;

import com.bin.dao.impl.*;
import com.bin.pojo.*;
import com.bin.service.BookService;
import com.bin.service.OrderService;
import com.bin.utils.JdbcUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.service.impl
 * @ClassName: OrderServiceImpl
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 20:26
 */
public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    /**
     * @param cart
     * @param userId
     * @ Description: 生成订单
     * @return: java.lang.String
     * @author: laibin
     * @date: 2021/10/17 20:16
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号唯一性：使用时间戳加用户id
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), Status.PROCESSING, userId);
        orderDao.saveOrder(order);
        //遍历购物车中每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            //获取购物车中每一个的商品项
            CartItem cartItem = entry.getValue();
            //转换为每一个订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            //保存每个订单项
            orderItemDao.saveOderItem(orderItem);
            //更新数据库的销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return orderId;
    }

    /**
     * @param
     * @ Description: 查询全部订单
     * @return: java.util.List<com.bin.pojo.Order>
     * @author: laibin
     * @date: 2021/10/17 20:17
     */
    @Override
    public List<Order> showAllOrders() {
        List<Order> orderList = orderDao.queryOrders();
        return orderList;
    }

    /**
     * @param orderId 订单号
     * @ Description: 管理员设置发货
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 20:18
     */
    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, Status.SHIPPED);
    }

    /**
     * @param orderId
     * @ Description: 查看订单详情
     * @return: java.util.List<com.bin.pojo.OrderItem>
     * @author: laibin
     * @date: 2021/10/17 20:24
     */
    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    /**
     * @param userId
     * @ Description:查看我的订单
     * @return: java.util.List<com.bin.pojo.Order>
     * @author: laibin
     * @date: 2021/10/17 21:31
     */
    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    /**
     * @param orderId 用户 签收订单/确认收货
     * @ Description:
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 20:25
     */
    @Override
    public void receiverOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, Status.DELIVERED);
    }
}
