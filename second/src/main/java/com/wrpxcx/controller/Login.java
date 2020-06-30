package com.wrpxcx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wrpxcx.POJO.GoodsKind;
import com.wrpxcx.POJO.SchoolList;
import com.wrpxcx.POJO.User;
import com.wrpxcx.mapper.GoodsMapper;
import com.wrpxcx.mapper.SchoolListMapper;
import com.wrpxcx.mapper.UserMapper;
import com.wrpxcx.service.MyWebSocket;
import com.wrpxcx.util.code2openidAndSessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;


@Controller
public class Login {

    @Autowired
    UserMapper userMapper;

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    SchoolListMapper schoolListMapper;
    @Resource
    code2openidAndSessionKey getOpenidAndSessionKey;

    @RequestMapping("/login")
    public void login(HttpServletRequest req, HttpServletResponse res){

        //设置响应的编码
        res.setContentType("application/json;charset=UTF-8");
        String code = req.getParameter("code");

        //System.out.println("code: " + code);
        //GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code

        //code2openidAndSessionKey t = new code2openidAndSessionKey();
        String jsonTemp = getOpenidAndSessionKey.getOpenid(code, "wx5a852245cfc73a90","a5e5ff7cab423beea0bfc4fa83296bf3");


        JSONObject jsonObject = JSONObject.parseObject(jsonTemp);
        //System.out.println(jsonTemp);
        //System.out.println(jsonObject.toString());
        String openid = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("sessionKey", sessionKey);

        User user = userMapper.getUserByOpenid(openid);
        if(user == null){
            //添加新用户
            User newUser = new User(openid, null, null, 0);
            userMapper.addUser(newUser);
        }

        String sessionId = httpSession.getId();
        JSONObject openidJson = new JSONObject();
        openidJson.put("openid", openid);
        openidJson.put("sessionId", sessionId);
        openidJson.put("schoolIndex", user.getUserSchool());

        try (PrintWriter out = res.getWriter()) {
            out.write(openidJson.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/getNavAndSchool")
    public void getNav(HttpServletResponse res, HttpServletRequest req){
        //设置响应的编码
        res.setContentType("application/json;charset=UTF-8");

        List<GoodsKind> nav = goodsMapper.getNav();
        List<SchoolList> schoolLists = schoolListMapper.getSchoolList();


        JSONObject t = new JSONObject();
        t.put("nav", nav);
        t.put("school", schoolLists);
        String tStr = JSONObject.toJSONString(t);

        //System.out.println(tStr);

        try (PrintWriter out = res.getWriter()) {
            out.write(tStr);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
