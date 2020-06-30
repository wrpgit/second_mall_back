package com.wrpxcx.POJO;

public class Order {
    private int orderId;
    private String buyOpenid;
    private String sailOpenid;
    private int goodsId;
    private int orderStatus;
    private String orderTime;

    public Order(String buyOpenid, String sailOpenid, int goodsId, int orderStatus, String orderTime) {
        this.buyOpenid = buyOpenid;
        this.sailOpenid = sailOpenid;
        this.goodsId = goodsId;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
    }

    public Order(){}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getBuyOpenid() {
        return buyOpenid;
    }

    public void setBuyOpenid(String buyOpenid) {
        this.buyOpenid = buyOpenid;
    }

    public String getSailOpenid() {
        return sailOpenid;
    }

    public void setSailOpenid(String sailOpenid) {
        this.sailOpenid = sailOpenid;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", buyOpenid='" + buyOpenid + '\'' +
                ", sailOpenid='" + sailOpenid + '\'' +
                ", goodsId=" + goodsId +
                ", orderStatus=" + orderStatus +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }
}
