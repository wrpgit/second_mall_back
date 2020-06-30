package com.wrpxcx.mapper;

import com.wrpxcx.POJO.FeedbackMessage;

import java.util.List;

public interface FeedbackMapper {

    //添加新反馈信息
    int addFeedback(FeedbackMessage feedbackMessage);

    //查询所有信息
    List<FeedbackMapper> getAllFeedback();
}
