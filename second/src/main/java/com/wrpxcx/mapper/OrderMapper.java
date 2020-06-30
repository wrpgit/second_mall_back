package com.wrpxcx.mapper;

import com.wrpxcx.POJO.Order;
import com.wrpxcx.POJO.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    //添加新订单
    int addOrder(Order order);

    //删除订单
    int deleteOrder(int orderId);

    //通过买家id查询订单   例： 查询某个买家的订单 buyOpenid, 0   查询我买到的物品就是  buyOpenid  1或2
    List<OrderDetail> getOrderByBuyOpenid(@Param("buyOpenid") String buyOpenid,
                                          @Param("orderStatus1") int orderStatus1,
                                          @Param("orderStatus2") int orderStatus2);

    //通过卖家id查询订单
    List<OrderDetail> getOrderBySailOpenid(@Param("sailOpenid") String sailOpenid,
                                           @Param("orderStatus1") int orderStatus,
                                           @Param("orderStatus2") int orderStatus2);

    //查询所有订单
    List<OrderDetail> getAllOrder();

    //查询某个订单
    OrderDetail getOrderByOrderId(int orderId);

    //更新订单的状态
    int updateOrderStatusByOrderId(@Param("orderId") int orderId,
                                   @Param("status") int status);

}
