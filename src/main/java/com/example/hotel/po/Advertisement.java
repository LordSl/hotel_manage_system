package com.example.hotel.po;


public class Advertisement {

    /**
     * 数据库中广告的主键
     */
    int adId;

    /**
     * 广告的url
     */
    String adImgUrl;

    /**
     * 与广告绑定的酒店id
     */
    int hotelId;


    public int getHotelId(){return hotelId;}
    public void setHotelId(int id){hotelId = id;}
    public int getAdId(){return adId;}
    public String getAdImgUrl(){return adImgUrl;}
    public void setAdId(int id){adId = id;}
    public void setAdImgUrl(String url){adImgUrl = url;}
}
