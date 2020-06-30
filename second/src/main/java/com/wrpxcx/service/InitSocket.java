package com.wrpxcx.service;

import javax.servlet.http.HttpServlet;

public class InitSocket extends HttpServlet {
    /**
     * 启动websocket 监听
     */
    public void init(){
        System.out.println("websocket启动成功");
        MyWebSocket myWebSocket = new MyWebSocket(7777);
        myWebSocket.start();
    }
}
