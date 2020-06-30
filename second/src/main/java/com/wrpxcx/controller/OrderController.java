package com.wrpxcx.controller;


import com.alibaba.fastjson.JSONObject;
import com.wrpxcx.POJO.Goods;
import com.wrpxcx.POJO.Order;
import com.wrpxcx.POJO.OrderDetail;
import com.wrpxcx.mapper.GoodsMapper;
import com.wrpxcx.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @RequestMapping("getOrderByOpenid")
    public void getOrderBySailOpenid(HttpServletResponse res, HttpServletRequest req) throws IOException {

        res.setContentType("application/json;charset=UTF-8");
        String openid = req.getParameter("openid");
        String operation = req.getParameter("operation");

        List<OrderDetail> orderDetails = null;
        if(operation.equals("1")){  //通过传来的openid  获取 我定的别人的订单,我是买家， openid为我的openid
            orderDetails = orderMapper.getOrderByBuyOpenid(openid, 1, 1);
        }
        else if(operation.equals("2")){ //获取 别人定的我的商品， 我是卖家， openid为
            orderDetails = orderMapper.getOrderBySailOpenid(openid, 1, 1);
        }
        else if(operation.equals("3")){  //通过传来的openid  获取 我买到的商品 ,我是买家， openid为我的openid
            orderDetails = orderMapper.getOrderByBuyOpenid(openid, 2, 3);
        }
        else if(operation.equals("4")){  //通过传来的openid  获取 我卖出的商品 ,我是卖家， openid为我的openid
            orderDetails = orderMapper.getOrderBySailOpenid(openid, 2, 3);
        }

        String result = JSONObject.toJSONString(orderDetails);

        PrintWriter out = res.getWriter();
        out.write(result);
        out.flush();
    }

    @RequestMapping("addOrder")
    public void addOrder(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json;charset=UTF-8");

        String buyOpenid = req.getParameter("openid");
        String sailOpenid = req.getParameter("sailOpenid");
        int goodsId = Integer.parseInt(req.getParameter("goodsId"));

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println("当前时间：" + sdf.format(d));
        String nowTime = sdf.format(d);
        Order order = new Order(buyOpenid, sailOpenid, goodsId, 1, nowTime);
        orderMapper.addOrder(order);

        Goods goods = goodsMapper.getGoodsByGoodsId(goodsId);

        goods.setGoodsStatus(2);
        goodsMapper.updateGoods(goods);

        PrintWriter out = res.getWriter();
        out.write("{\"status\": \"200\"}");
        out.flush();
    }

    @RequestMapping("/updateOrderStatus")
    public void updateOrderStatus(HttpServletResponse res, HttpServletRequest req) throws IOException {
        res.setContentType("application/json;charset=UTF-8");
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        int status = Integer.parseInt(req.getParameter("status"));

        orderMapper.updateOrderStatusByOrderId(orderId, status);

        PrintWriter out = res.getWriter();
        out.write("{\"status\": \"200\"}");
        out.flush();

    }

    @RequestMapping("cancelOrder")
    public void cancelOrder(HttpServletResponse res, HttpServletRequest req) throws IOException {
        res.setContentType("application/json;charset=UTF-8");

        String orderIdStr = req.getParameter("orderId");
        String openid = req.getParameter("openid");

        int orderId = Integer.parseInt(orderIdStr);
        Order order = orderMapper.getOrderByOrderId(orderId);
        if(order.getBuyOpenid().equals(openid)){
            orderMapper.deleteOrder(orderId);
            Goods goods = goodsMapper.getGoodsByGoodsId(order.getGoodsId());
            goods.setGoodsStatus(1);
            goodsMapper.updateGoods(goods);

            PrintWriter out = res.getWriter();
            out.write("{\"status\": \"200\"}");
            out.flush();
        }
    }
}
