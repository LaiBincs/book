package com.bin.test;

import com.bin.pojo.Cart;
import com.bin.pojo.CartItem;
import com.bin.pojo.Order;
import com.bin.pojo.OrderItem;
import com.bin.service.OrderService;
import com.bin.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.test
 * @ClassName: OrderServiceTest
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 21:24
 */
public class OrderServiceTest {
    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(38.5), new BigDecimal(38.5)));
        cart.addItem(new CartItem(2, "计算机组成原理", 1, new BigDecimal(20), new BigDecimal(20)));
        cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(38.5), new BigDecimal(38.5)));
        System.out.println("订单号是:" + orderService.createOrder(cart, 1));
    }

    @Test
    public void showAllOrders() {
        List<Order> orderList = orderService.showAllOrders();
        orderList.forEach(System.out::println);
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("16344772424881");
    }

    @Test
    public void showOrderDetail() {
        List<OrderItem> orderItems = orderService.showOrderDetail("16344772424881");
        orderItems.forEach(System.out::println);
    }

    @Test
    public void showMyOrders() {
        List<Order> orderList = orderService.showMyOrders(1);
        orderList.forEach(System.out::println);
    }


    @Test
    public void receiverOrder() {
        orderService.receiverOrder("16344772424881");
        List<Order> orderList = orderService.showAllOrders();
        orderList.forEach(System.out::println);
    }
}