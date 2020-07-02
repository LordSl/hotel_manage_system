package com.example.hotel.vo;

public class AppealVO {

    private String appealMessage;
    private String appealStatus;
    private int orderId;

    public String getAppealMessage(){return appealMessage;}
    public String getAppealStatus(){return appealStatus;}
    public int getOrderId(){return orderId;}
    public void setAppealMessage(String appeal){appealMessage = appeal;}
    public void setAppealStatus(String status1){this.appealStatus = status1;}
    public void setOrderId(int orderId){this.orderId = orderId;}
}
