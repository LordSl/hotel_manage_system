package com.example.hotel.po;

public class Appeal {
    /**
     * 参与申诉的订单id
     */
    private int orderId;

    /**
     * 申诉的信息
     */
    private String appealMessage;

    /**
     * 当前申诉的状态，分为申诉成功和待处理两种状态
     */
    private String appealStatus;

    public int getOrderId(){return orderId;}
    public  void setOrderId(int id){orderId = id;}
    public void setAppealMessage(String message){appealMessage = message;}
    public void setAppealStatus(String s){appealStatus = s;}
    public  String getAppealMessage(){return  appealMessage;}
    public String getAppealStatus(){return appealStatus;}
}
