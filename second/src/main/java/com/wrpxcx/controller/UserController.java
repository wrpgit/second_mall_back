package com.wrpxcx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.wrpxcx.POJO.User;
import com.wrpxcx.mapper.UserMapper;
import com.wrpxcx.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("getUserByOpenid")
    public void getUserByOpenid(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("application/json;charset=UTF-8");
        String openid = req.getParameter("openid");
        User user = userMapper.getUserByOpenid(openid);
        String result = JSONObject.toJSONString(user);

        PrintWriter out = res.getWriter();
        out.write(result);
        out.flush();

    }

    @RequestMapping(value = "/updateUserInfo")
    public void updateUserInfo(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
        res.setContentType("application/json;charset=UTF-8");

        //System.out.println(System.getProperty("file.encoding"));
        System.setProperty("file.encoding", "utf-8");
        //System.out.println(System.getProperty("file.encoding"));

        //String a = new String(new byte[]);
        //req.getServletContext().getRequestCharacterEncoding();
        String openid = req.getParameter("openid");
        String iv = req.getParameter("iv");
        String encryptedData = req.getParameter("encryptedData");
        String sessionKey = (String) req.getSession().getAttribute("sessionKey");

        if (iv == null || openid == null || encryptedData == null || sessionKey == null) {
            System.out.println("openid: " + openid);
            //System.out.println(iv);
            //System.out.println(encryptedData);
            //System.out.println(sessionKey);
            return;
        }

        String userInfo = WeChatUtil.decryptData(encryptedData, sessionKey, iv);

        JSONObject jsonObject = JSON.parseObject(userInfo);
        //System.out.println("转换成json后");
        //System.out.println(jsonObject);

        User user = userMapper.getUserByOpenid(openid);

        user.setUserName(jsonObject.getString("nickName"));
        user.setHeadAddress(jsonObject.getString("avatarUrl"));

        //更新用户信息
        userMapper.updateUser(user);

        PrintWriter out = null;
        try {
            out = res.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write("{\"status\": \"200\"}");
        out.flush();
    }

    @RequestMapping("updateUserSchool")
    public void updateUserSchool(HttpServletResponse res, HttpServletRequest req) throws IOException {

        res.setContentType("application/json;charset=UTF-8");
        String openid = req.getParameter("openid");
        String schoolIdStr = req.getParameter(("schoolIndex"));

        User user = userMapper.getUserByOpenid(openid);
        if (user == null)
            return;
        user.setUserSchool(Integer.parseInt(schoolIdStr));
        userMapper.updateUser(user);
        PrintWriter out = res.getWriter();
        out.write("{\"status\": \"200\"}");
        out.flush();

    }
}
