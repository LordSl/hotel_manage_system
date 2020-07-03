package com.example.hotel.serviceTest.Stubs;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.vo.AppealVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.LinkedList;
import java.util.List;

public class OrderService_Stub  implements OrderService {

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

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private Integer id;
    private Integer userId;
    private Integer hotelId;
    private String hotelName;
    private String checkInDate;
    private String checkOutDate;
    private Integer roomId;
    private String roomType;
    private Integer roomNum;
    private Integer peopleNum;
    private boolean haveChild;
    private String createDate;
    private Double price;
    private String clientName;


    @Override
    public ResponseVO addOrder(OrderVO orderVO) {
        System.out.println("addOrder");
        return ResponseVO.buildSuccess();
    }

    @Override
    public List<OrderVO> getAllOrders() {
        System.out.println("getAllOrders");
        return new LinkedList<>();
    }

    @Override
    public List<OrderVO> getUserOrders(int userid) {
        System.out.println("getUserOrders");
        return new LinkedList<>();
    }

    @Override
    public ResponseVO annulOrder(int orderid) {
        System.out.println("annulOrder");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO appealOrder(AppealVO appealVO) {
        System.out.println("appealOrder");
        return ResponseVO.buildSuccess();
    }

    @Override
    public List<OrderVO> getHotelOrders(Integer hotelId) {
        System.out.println("getHotelOrders");
        return new LinkedList<>();
    }

    @Override
    public ResponseVO deleteOrder(int orderid) {
        System.out.println("deleteOrder");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO getAppealMessage(int orderid) {
        System.out.println("getAppealMessage");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO changeAppealStatus(AppealVO appealVO) {
        System.out.println("changeAppealStatus");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO checkInOrder(int orderid) {
        System.out.println("checkInOrder");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO autoUpdateOrderStatus() {
        System.out.println("autoUpdateOrderStatus");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO getStateOfUP(Integer userId, Integer hotelId) {
        System.out.println("getStateOfUP");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO getPrevOrder(Integer userId, Integer hotelId) {
        System.out.println("getPrevOrder");
        return ResponseVO.buildSuccess();
    }
}
