package com.wrpxcx.POJO;

public class OrderDetail extends Order{

    //卖家详细信息
    private String sailUserName;   //卖家的名字
    private String sailHeadAddress;  //卖家的头像地址

    private String buyUserName;
    private String buyHeadAddress;  //卖家的头像地址

    //商品详细信息
    private String goodsName;
    private Double price;
    private String description;
    private int schoolId;
    private int kindId;
    private String goodsImage; //图片的地址
    private String addTime;


    public OrderDetail(String buyOpenid, String sailOpenid, int goodsId, int orderStatus, String orderTime, String sailUserName, String sailHeadAddress, String buyUserName, String buyHeadAddress, String goodsName, Double price, String description, int schoolId, int kindId, String goodsImage, String addTime) {
        super(buyOpenid, sailOpenid, goodsId, orderStatus, orderTime);
        this.sailUserName = sailUserName;
        this.sailHeadAddress = sailHeadAddress;
        this.buyUserName = buyUserName;
        this.buyHeadAddress = buyHeadAddress;
        this.goodsName = goodsName;
        this.price = price;
        this.description = description;
        this.schoolId = schoolId;
        this.kindId = kindId;
        this.goodsImage = goodsImage;
        this.addTime = addTime;
    }

    public OrderDetail(String sailUserName, String sailHeadAddress, String buyUserName, String buyHeadAddress, String goodsName, Double price, String description, int schoolId, int kindId, String goodsImage, String addTime) {
        this.sailUserName = sailUserName;
        this.sailHeadAddress = sailHeadAddress;
        this.buyUserName = buyUserName;
        this.buyHeadAddress = buyHeadAddress;
        this.goodsName = goodsName;
        this.price = price;
        this.description = description;
        this.schoolId = schoolId;
        this.kindId = kindId;
        this.goodsImage = goodsImage;
        this.addTime = addTime;
    }

    public OrderDetail(){}


    public String getSailUserName() {
        return sailUserName;
    }

    public void setSailUserName(String sailUserName) {
        this.sailUserName = sailUserName;
    }

    public String getSailHeadAddress() {
        return sailHeadAddress;
    }

    public void setSailHeadAddress(String sailHeadAddress) {
        this.sailHeadAddress = sailHeadAddress;
    }

    public String getBuyUserName() {
        return buyUserName;
    }

    public void setBuyUserName(String buyUserName) {
        this.buyUserName = buyUserName;
    }

    public String getBuyHeadAddress() {
        return buyHeadAddress;
    }

    public void setBuyHeadAddress(String buyHeadAddress) {
        this.buyHeadAddress = buyHeadAddress;
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

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getKindId() {
        return kindId;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                super.toString() +
                "sailUserName='" + sailUserName + '\'' +
                ", sailHeadAddress='" + sailHeadAddress + '\'' +
                ", buyUserName='" + buyUserName + '\'' +
                ", buyHeadAddress='" + buyHeadAddress + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", schoolId=" + schoolId +
                ", kindId=" + kindId +
                ", goodsImage='" + goodsImage + '\'' +
                ", addTime='" + addTime + '\'' +
                '}';
    }
}
