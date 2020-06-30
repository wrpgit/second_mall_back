package com.wrpxcx.controller;

import com.alibaba.fastjson.JSONObject;
import com.wrpxcx.POJO.LastMessageDetail;
import com.wrpxcx.POJO.Message;
import com.wrpxcx.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    MessageMapper messageMapper;

    @RequestMapping("/getMessageList")
    public void getMessageList(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("application/json;charset=UTF-8");

        String openid = req.getParameter("openid");
        List<LastMessageDetail> lastMessageDetails =  messageMapper.getMessageListByOpenid(openid);
        String result = JSONObject.toJSONString(lastMessageDetails);

        PrintWriter out = res.getWriter();
        out.write(result);
        out.flush();

    }


    @RequestMapping("/getMessage")
    public void getMessage(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json;charset=UTF-8");

        String fromOpenid = req.getParameter("fromOpenid");
        String toOpenid = req.getParameter("toOpenid");

        List<Message> messages = messageMapper.getMessagesByOpenid(fromOpenid, toOpenid);
        String result = JSONObject.toJSONString(messages);

        PrintWriter out = res.getWriter();
        out.write(result);
        out.flush();

    }

    @RequestMapping("/zeroNotReadNum")
    public void zeroNotReadNum(HttpServletRequest req, HttpServletResponse res){

        String fromOpenid = req.getParameter("fromOpenid");
        String toOpenid = req.getParameter("toOpenid");

        messageMapper.zeroNotReadNum(fromOpenid, toOpenid);

    }
}
