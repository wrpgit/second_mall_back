package com.wrpxcx.POJO;

public class LastMessageDetail extends LastMessage{

    private String fromName;
    private String fromHeadAddress;

    private String toName;
    private String toHeadAddress;

    public LastMessageDetail(String fromOpenid, String toOpenid, String lastMessage, String lastTime, int notReadNum, String fromName, String fromHeadAddress, String toName, String toHeadAddress) {

        super(fromOpenid, toOpenid, lastMessage, lastTime, notReadNum);
        this.fromName = fromName;
        this.fromHeadAddress = fromHeadAddress;
        this.toName = toName;
        this.toHeadAddress = toHeadAddress;
    }

    public LastMessageDetail(String fromName, String fromHeadAddress, String toName, String toHeadAddress) {
        this.fromName = fromName;
        this.fromHeadAddress = fromHeadAddress;
        this.toName = toName;
        this.toHeadAddress = toHeadAddress;
    }

    public LastMessageDetail(){}

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromHeadAddress() {
        return fromHeadAddress;
    }

    public void setFromHeadAddress(String fromHeadAddress) {
        this.fromHeadAddress = fromHeadAddress;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToHeadAddress() {
        return toHeadAddress;
    }

    public void setToHeadAddress(String toHeadAddress) {
        this.toHeadAddress = toHeadAddress;
    }

    @Override
    public String toString() {
        return "LastMessageDetail{" +
                "fromName='" + fromName + '\'' +
                ", fromHeadAddress='" + fromHeadAddress + '\'' +
                ", toName='" + toName + '\'' +
                ", toHeadAddress='" + toHeadAddress + '\'' +
                '}';
    }
}
