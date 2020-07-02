package com.example.hotel.po;

import com.example.hotel.enums.RoomType;

public class HotelRoom {

    /**
     * 酒店房间主键id
     */
    private Integer id;

    /**
     * 房间的房型
     */
    private String roomType;

    /**
     * 房间所对应的酒店
     */
    private Integer hotelId;

    /**
     * 房间的价格
     */
    private double price;

    /**
     * 当前剩余可预定房间数
     */
    private int curNum;

    /**
     * 某类型房间总数
     */
    private int total;

    /**
     * 房间的图片url
     */
    private String roomImg;

    public String getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(String roomImg) {
        this.roomImg = roomImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCurNum() {
        return curNum;
    }

    public void setCurNum(int curNum) {
        this.curNum = curNum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
