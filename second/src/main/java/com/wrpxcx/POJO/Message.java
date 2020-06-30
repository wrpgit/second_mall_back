package com.wrpxcx.POJO;

public class Message {

    private String fromOpenid;
    private String toOpenid;
    private String context;
    private String messageTime;
    private int status;

    public Message(String fromOpenid, String toOpenid, String context, String messageTime, int status) {
        this.fromOpenid = fromOpenid;
        this.toOpenid = toOpenid;
        this.context = context;
        this.messageTime = messageTime;
        this.status = status;
    }

    public Message(){};

    public String getFromOpenid() {
        return fromOpenid;
    }

    public void setFromOpenid(String fromOpenid) {
        this.fromOpenid = fromOpenid;
    }

    public String getToOpenid() {
        return toOpenid;
    }

    public void setToOpenid(String toOpenid) {
        this.toOpenid = toOpenid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTime() {
        return messageTime;
    }

    public void setTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "fromOpenid='" + fromOpenid + '\'' +
                ", toOpenid='" + toOpenid + '\'' +
                ", context='" + context + '\'' +
                ", messageTime='" + messageTime + '\'' +
                ", status=" + status +
                '}';
    }
}
