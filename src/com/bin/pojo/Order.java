package com.bin.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ProjectName: Order订单
 * @Package: com.bin.pojo
 * @ClassName: Order
 * @Author: laibin
 * @Description:
 * @Date: 2021/10/17 15:12
 */
public class Order {
    private String orderId;//订单号
    private Date createTime;//下单时间
    private BigDecimal price;//金额
    private String status;
    private Status statusValue = Status.PROCESSING;//状态
    private Integer userId;//用户编号

    public Order() {
    }

    public Order(String orderId, Date createTime, BigDecimal price, Status statusValue, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.statusValue = statusValue;
        this.userId = userId;
        this.status = statusValue.getStatusName();
    }

    public String getOrderId() {

        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Status getStatusValue() {
        return statusValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        setStatusValue(status);
    }

    public void setStatusValue(String status) {
        this.statusValue = Status.valueOf(status);
//        setStatus(this.statusValue.getStatusName());
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override

    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", statusValue=" + statusValue +
                ", userId=" + userId +
                '}';
    }
}
