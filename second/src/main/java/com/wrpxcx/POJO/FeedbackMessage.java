package com.wrpxcx.POJO;

public class FeedbackMessage {
    private String openid;
    private String userName;
    private String context;

    public FeedbackMessage(String openid, String userName, String context) {
        this.openid = openid;
        this.userName = userName;
        this.context = context;
    }

    public FeedbackMessage(){};

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "FeedbackMessage{" +
                "openid='" + openid + '\'' +
                ", userName='" + userName + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}
