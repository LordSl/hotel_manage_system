package com.example.hotel.po;

public class VIPHotel {

    /**
     * 记录用户是否为酒店vip的表主键
     */
    private int vid;

    /**
     * 用户id
     */
    private int userId;

    /**
     * 酒店id
     */
    private int hotelId;

    public void setVid(int vid){this.vid =  vid;}
    public int getVid(){return vid;}
    public void setUserId(int id){this.userId = id;}
    public int getUserId(){return userId;}
    public void setHotelId(int id){this.hotelId = id;}
    public int getHotelId(){return hotelId;}
}
