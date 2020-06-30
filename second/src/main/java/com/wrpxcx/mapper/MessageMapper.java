package com.wrpxcx.mapper;

import com.wrpxcx.POJO.LastMessage;
import com.wrpxcx.POJO.LastMessageDetail;
import com.wrpxcx.POJO.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {

    //添加新消息
    int addMessage(Message message);

    //查询某两个人的消息
    List<Message> getMessagesByOpenid(@Param("fromOpenid") String fromOpenid,
                                            @Param("toOpenid") String toOpenid);


    //添加消息列表中的 信息， 如果两个人是第一次发消息，则调用这个
    int addLastMessage(LastMessage lastMessage);

    //每当人发消息时 ，都会更新列表
    int updateLastMessage(LastMessage lastMessage);

    //查询消息列表
    List<LastMessageDetail> getMessageListByOpenid(@Param("openid") String openid);

    //查询指定某个 最后一条通信
    LastMessageDetail getMessageListByFromAndTo(@Param("fromOpenid") String fromOpenid,
                                                @Param("toOpenid") String toOpenid);

    int zeroNotReadNum(@Param("fromOpenid") String fromOpenid,
                       @Param("toOpenid") String toOpenid);


}
