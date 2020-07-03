package com.example.hotel.po;

public class Hotel {

    /**
     * 酒店id
     */
    private Integer id;

    /**
     * 酒店名
     */
    private String hotelName;

    /**
     * 酒店地址
     */
    private String address;
    /**
     * 商圈
     */
    private String bizRegion;

    public Integer getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(Integer hotelStar) {
        this.hotelStar = hotelStar;
    }

    /**
     *酒店星级
     */
    private Integer hotelStar;

    /**
     * 酒店评分
     */
    private Double rate;

    /**
     * 酒店的简介
     */
    private String description;

    /**
     * 酒店的电话号码
     */
    private String phoneNum;
    /**
     * 酒店管理员id
     */
    private Integer managerId;

    /**
     * 酒店的图片Url
     */
    private String hotelImg;

    /**
     * 酒店所处的经度
     */
    private Double longitude;

    /**
     * 酒店所处的纬度
     */
    private Double latitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getHotelImg() {
        return hotelImg;
    }

    public void setHotelImg(String hotelImg) {
        this.hotelImg = hotelImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBizRegion() {
        return bizRegion;
    }

    public void setBizRegion(String bizRegion) {
        this.bizRegion = bizRegion;
    }


    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}

