package com.example.hotel.vo;

public class CreditChangeVO {
    /**
     *  信用值变更记录id
     */
    private int id;

    /**
     * 信用值变更记录所属用户的id
     */
    private int userId;

    /**
     * 信用值变更记录的时间
     */
    private String timeChanged;

    /**
     * 信用值变更记录的缘由
     */
    private String reason;

    /**
     * 信用值变更记录的大小
     */
    private double valueChanged;

    public void setId(int id){this.id = id;}

    public int getId(){return id;}

    public void setUserId(int id){userId = id;}

    public int getUserId(){return userId;}

    public void setTime(String time){this.timeChanged = time;}

    public String getTime(){return timeChanged;}

    public void setReason(String reason){this.reason = reason;}

    public String getReason(){return reason;}

    public void setValue(double value){this.valueChanged = value;}

    public double getValue(){return  valueChanged;}
}
