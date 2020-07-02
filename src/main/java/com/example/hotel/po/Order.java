package com.example.hotel.po;

import com.example.hotel.vo.RoomVO;

import java.util.Date;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */
public class Order {

    /**
     * 订单的主键id
     */
    private Integer id;

    /**
     * 下订单的用户id
     */
    private Integer userId;

    /**
     * 订单对应的酒店id
     */
    private Integer hotelId;

    /**
     * 订单对应的酒店名
     */
    private String hotelName;

    /**
     * 订单中的入住日期
     */
    private String checkInDate;

    /**
     * 订单中的离开日期
     */
    private String checkOutDate;

    /**
     * 订单对应的房间id
     */
    private Integer roomId ;

    /**
     * 所需的房间数
     */
    private Integer roomNum;

    /**
     * 入住的人数
     */
    private Integer peopleNum;

    /**
     * 是否有小孩
     */
    private boolean haveChild;

    /**
     * 订单创建的时间
     */
    private String createDate;

    /**
     * 订单的总价
     */
    private Double price;

    /**
     * 下订单的用户名
     */
    private String clientName;

    /**
     * 订单的用户电话号码
     */
    private String phoneNumber;

    /**
     * 订单的状态，分为已预订，已入住，已撤销，异常
     */
    private String orderState;

    /**
     * 订单的房型
     */
    private String roomType;


    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public boolean isHaveChild() {
        return haveChild;
    }

    public void setHaveChild(boolean haveChild) {
        this.haveChild = haveChild;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
