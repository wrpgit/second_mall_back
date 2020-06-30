package com.wrpxcx.POJO;

public class Goods {
    private Integer goodsId;
    private String openid;
    private String goodsName;
    private Double price;
    private String description;
    private Integer schoolId;
    private Integer kindId;
    private String goodsImage;
    private String addTime;
    private int goodsStatus;

    public Goods(String openid, String goodsName, Double price, String description, Integer schoolId, Integer kindId, String goodsImage, String addTime, int goodsStatus) {

        //this.goodsId = goodsId;
        this.openid = openid;
        this.goodsName = goodsName;
        this.price = price;
        this.description = description;
        this.schoolId = schoolId;
        this.kindId = kindId;
        this.goodsImage = goodsImage;
        this.addTime = addTime;
        this.goodsStatus = goodsStatus;
    }
    public Goods(){};

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", openid='" + openid + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", schoolId=" + schoolId +
                ", kindId=" + kindId +
                ", goodsImage='" + goodsImage + '\'' +
                ", addTime='" + addTime + '\'' +
                ", goodsStatus=" + goodsStatus +
                '}';
    }
}
