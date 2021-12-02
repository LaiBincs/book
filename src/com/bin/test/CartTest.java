package com.bin.test;

import com.bin.pojo.Cart;
import com.bin.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @ProjectName: JavaWeb
 * @Package: com.bin.test
 * @ClassName: CartTest
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 11:39
 */
public class CartTest {
    Cart cart = new Cart();

    @Test
    public void addItem() {
        cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(38.5), new BigDecimal(38.5)));
        cart.addItem(new CartItem(2, "计算机组成原理", 1, new BigDecimal(20), new BigDecimal(20)));
        cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(38.5), new BigDecimal(38.5)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(38.5), new BigDecimal(38.5)));
        cart.addItem(new CartItem(2, "计算机组成原理", 1, new BigDecimal(20), new BigDecimal(20)));
        cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(38.5), new BigDecimal(38.5)));

        cart.deleteItem(1);

        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(38.5), new BigDecimal(38.5)));
        cart.addItem(new CartItem(2, "计算机组成原理", 1, new BigDecimal(20), new BigDecimal(20)));
        cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(38.5), new BigDecimal(38.5)));

        cart.deleteItem(1);
        cart.clear();

        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(38.5), new BigDecimal(38.5)));
        cart.addItem(new CartItem(2, "计算机组成原理", 1, new BigDecimal(20), new BigDecimal(20)));
        cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(38.5), new BigDecimal(38.5)));

        cart.deleteItem(1);
        cart.clear();
        cart.addItem(new CartItem(1, "java入门到放弃", 1, new BigDecimal(38.5), new BigDecimal(38.5)));
        cart.updateCount(1, 10);

        System.out.println(cart);

    }
}