package com.wrpxcx.POJO;

public class LastMessage {

    private String fromOpenid;
    private String toOpenid;
    private String lastMessage;
    private String lastTime;
    private int notReadNum;

    public LastMessage(String fromOpenid, String toOpenid, String lastMessage, String lastTime, int notReadNum) {
        this.fromOpenid = fromOpenid;
        this.toOpenid = toOpenid;
        this.lastMessage = lastMessage;
        this.lastTime = lastTime;
        this.notReadNum = notReadNum;
    }

    public LastMessage(){}

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

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public int getNotReadNum() { return notReadNum; }

    public void setNotReadNum(int notReadNum) { this.notReadNum = notReadNum; }

    @Override
    public String toString() {
        return "LastMessage{" +
                "fromOpenid='" + fromOpenid + '\'' +
                ", toOpenid='" + toOpenid + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", notReadNum='" + notReadNum + '\'' +
                '}';
    }
}
