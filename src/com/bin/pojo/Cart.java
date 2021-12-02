package com.bin.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ProjectName: 购物车对象Cart
 * @Package: com.bin.pojo
 * @ClassName: Cart
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 11:10
 */
public class Cart {
    //    private Integer totalCount;//总商品数量
    //    private BigDecimal totalPrice;//总商品金额
    private Map<Integer, CartItem> items = new LinkedHashMap<>();//购物车商品 key是商品编号，value是商品信息

    /**
     * @param cartItem
     * @ Description: 添加商品项
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 11:21
     */
    public void addItem(CartItem cartItem) {
        //先查看购物车中是否已经添加过此商品，如果已添加，则数量累加，总金额更新，如果没有添加过，直接放到 集合中即可
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            items.put(cartItem.getId(), cartItem);
        } else {
            //已经存在的情况下
            item.setCount(item.getCount() + 1);//商品数量累计
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额 multiply表示乘法
        }
    }

    /**
     * @param id
     * @ Description:删除商品项，根据商品编号
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 11:28
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * @param
     * @ Description:清空购物车
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 11:28
     */
    public void clear() {
        items.clear();
    }

    /**
     * @param id    商品编号
     * @param count 商品数量
     * @ Description: 修改商品数量
     * @return: void
     * @author: laibin
     * @date: 2021/10/17 11:29
     */
    public void updateCount(Integer id, Integer count) {
        //先查看是否有添加商品
        CartItem item = items.get(id);
        if (item != null) {
            //修改商品数量
            item.setCount(count);
            //修改商品总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));

        }
    }

    /**
     * @param
     * @ Description: 累计购物车中的商品总数量
     * @return: java.math.BigDecimal
     * @author: laibin
     * @date: 2021/10/17 11:38
     */
    public Integer getTotalCount() {
        Integer totalCount = 0;//总商品数量
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    /**
     * @param
     * @ Description: 累计购物车中的商品总金额
     * @return: java.math.BigDecimal
     * @author: laibin
     * @date: 2021/10/17 11:38
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);//总商品金额
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
