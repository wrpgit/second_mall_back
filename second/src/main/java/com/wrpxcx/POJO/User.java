package com.wrpxcx.POJO;

public class User {
    private String openid;
    private String userName;
    private String headAddress;
    private Integer userSchool;

    public User(String openid, String userName, String headAddress, Integer userSchool) {
        this.openid = openid;
        this.userName = userName;
        this.headAddress = headAddress;
        this.userSchool = userSchool;
    }

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

    public String getHeadAddress() {
        return headAddress;
    }

    public void setHeadAddress(String headAddress) {
        this.headAddress = headAddress;
    }

    public Integer getUserSchool() { return userSchool; }

    public void setUserSchool(Integer userSchool) {
        this.userSchool = userSchool;
    }

    @Override
    public String toString() {
        return "tb_user{" +
                "openid='" + openid + '\'' +
                ", userName='" + userName + '\'' +
                ", headAddress='" + headAddress + '\'' +
                ", userSchool=" + userSchool +
                '}';
    }
}
