package com.bin.pojo;

import java.math.BigDecimal;

/**
 * @ProjectName: 购物车商品项CartItem
 * @Package: com.bin.pojo
 * @ClassName: CartItem
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 11:08
 */
public class CartItem {
    private Integer id;//商品编号
    private String name;//商品名称
    private Integer count;//商品数量
    private BigDecimal price;//商品单价
    private BigDecimal totalPrice;//商品总价


    public CartItem() {
    }

    public CartItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice) {

        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
