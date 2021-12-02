package com.bin.test;

import com.bin.dao.impl.OrderDao;
import com.bin.dao.impl.OrderDaoImpl;
import com.bin.pojo.Cart;
import com.bin.pojo.CartItem;
import com.bin.pojo.Order;
import com.bin.pojo.Status;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.test
 * @ClassName: OrderDaoTest
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 16:11
 */
public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        Order order = new Order("1234", new Date(), new BigDecimal(100), Status.PROCESSING, 3);
        orderDao.saveOrder(order);
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();
       /* for (Order i : orders) {
            System.out.println(i.getStatus());
            i.setStatus(null, Status.DELIVERED);
            System.out.println(i.getStatus().getStatusDesc());
        }*/

        orders.forEach(System.out::println);
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("16344777347031", Status.SHIPPED);
        List<Order> orders = orderDao.queryOrders();
        orders.forEach(System.out::println);
    }

    @Test
    public void queryOrderByUserId() {
        List<Order> orders = orderDao.queryOrderByUserId(1);
        orders.forEach(System.out::println);

    }
}

