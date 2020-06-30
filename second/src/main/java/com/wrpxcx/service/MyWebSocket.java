package com.wrpxcx.service;

import com.alibaba.fastjson.JSONObject;
import com.wrpxcx.POJO.LastMessage;
import com.wrpxcx.POJO.Message;
import com.wrpxcx.mapper.GoodsMapper;
import com.wrpxcx.mapper.MessageMapper;
import com.wrpxcx.mapper.UserMapper;
import com.wrpxcx.util.WsPool;
import org.apache.ibatis.session.SqlSession;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class MyWebSocket extends WebSocketServer {

    //@Autowired
    //MessageMapper messageMapper;

    public MyWebSocket() throws UnknownHostException {
        super();
    }

    public MyWebSocket(int port)  {
        super(new InetSocketAddress(port));
    }

    public MyWebSocket(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // ws连接的时候触发的代码，onOpen中我们不做任何操作
        String url = handshake.getResourceDescriptor();
        int position = url.indexOf("openid=");
        String openid = url.substring(position + 7);
        System.out.println(openid);
        userJoin(conn, openid);

        System.out.println(openid + "加入用户池");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        //断开连接时候触发代码
        userLeave(conn);
        System.out.println(reason);
    }

    @Override
    public void onMessage(WebSocket conn, String data) {
        //有用户发送消息
        @SuppressWarnings("unchecked")
        Map<String, String> obj =  (Map<String,String>) JSONObject.parse(data);
        System.out.println(data);
        String fromOpenid = obj.get("fromOpenid");
        String toOpenid = obj.get("toOpenid");
        String word = obj.get("message");

        insertMessage(fromOpenid, toOpenid, word);  //将消息插入到数据库中

        WebSocket receivePeople = WsPool.getWsByUser(toOpenid);

        //WsPool.sendMessageToAll(data);
        if(receivePeople != null) { //该用户在线， 将消息发给他

            WsPool.sendMessageToUser(receivePeople, data);
            //WsPool.sendMessageToAll(data);
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        //错误时候触发的代码
        System.out.println("on error");
        ex.printStackTrace();
    }

    /**
     * 去除掉失效的websocket链接
     */
    private void userLeave(WebSocket conn){
        WsPool.removeUser(conn);
    }
    /**
     * 将websocket加入用户池
     * @param conn
     * @param userName
     */
    private void userJoin(WebSocket conn,String userName){
        WsPool.addUser(userName, conn);
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub

    }

    private void insertMessage(String fromOpenid, String toOpenid, String word) {

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println("当前时间：" + sdf.format(d));
        String nowTime = sdf.format(d);

        Message message = new Message(fromOpenid, toOpenid, word, nowTime, 0);
        System.out.println(message);

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MessageMapper messageMapper = (MessageMapper) context.getBean("messageMapper");

        if(messageMapper == null){
            System.out.println("messageMapper为空");
        }

        else {
            messageMapper.addMessage(message);
            LastMessage lastMessage = messageMapper.getMessageListByFromAndTo(fromOpenid, toOpenid);

            if (lastMessage == null) {

                LastMessage newLastMessage = new LastMessage(fromOpenid, toOpenid, word, nowTime, 1);
                messageMapper.addLastMessage(newLastMessage);
                System.out.println("插入数据");
            } else {

                int notRead = lastMessage.getNotReadNum();
                LastMessage newLastMessage = new LastMessage(fromOpenid, toOpenid, word, nowTime, notRead);
                messageMapper.updateLastMessage(newLastMessage);
                System.out.println("更新数据");
            }
        }

    }

}