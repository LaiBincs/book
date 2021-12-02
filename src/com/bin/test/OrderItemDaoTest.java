package com.bin.test;

import com.bin.dao.impl.OrderItemDao;
import com.bin.dao.impl.OrderItemDaoImpl;
import com.bin.pojo.Cart;
import com.bin.pojo.CartItem;
import com.bin.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.test
 * @ClassName: OrderItemDaoTest
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 16:11
 */
public class OrderItemDaoTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();


    @Test
    public void saveOderItem() {
        orderItemDao.saveOderItem(new OrderItem(null, "java入门到精通", 1, new BigDecimal(20), new BigDecimal(20), "1234"));
        orderItemDao.saveOderItem(new OrderItem(null, "phP入门到精通", 1, new BigDecimal(15), new BigDecimal(15), "1234"));
        OrderItem orderItem = new OrderItem(null, "web入门", 2, new BigDecimal(20), new BigDecimal(20).multiply(new BigDecimal(2)), "1234");
        orderItemDao.saveOderItem(orderItem);
    }

    @Test
    public void queryOrderItemByOrderId() {
        List<OrderItem> orderItems = orderItemDao.queryOrderItemByOrderId("123");
        orderItems.forEach(System.out::println);
    }
}