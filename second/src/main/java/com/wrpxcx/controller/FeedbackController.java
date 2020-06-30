package com.wrpxcx.controller;

import com.wrpxcx.POJO.FeedbackMessage;
import com.wrpxcx.POJO.User;
import com.wrpxcx.mapper.FeedbackMapper;
import com.wrpxcx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FeedbackController {

    @Autowired
    FeedbackMapper feedbackMapper;

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/feedback")
    public void feedback(HttpServletRequest req, HttpServletResponse res){
        res.setContentType("application/json;charset=UTF-8");

        String openid = req.getParameter("openid");
        String context = req.getParameter("context");

        User user = userMapper.getUserByOpenid(openid);
        String name = user.getUserName();
        FeedbackMessage feedbackMessage = new FeedbackMessage(openid, name, context);

        feedbackMapper.addFeedback(feedbackMessage);
    }
}
