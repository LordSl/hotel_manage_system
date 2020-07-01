package com.example.hotel.vo;


public class CouponVO {
    private Integer id;
    private String description;
    private Integer status;
    private Integer hotelId;
    private String name;
    private Integer type;
    /**
     * 是否针对酒店vip，0表示不针对酒店vip， 1表示针对酒店vip
     */
    private int forHotelVip;  // 0表示不针对酒店vip， 1表示针对酒店vip
    private String startTime;
    private String endTime;
    public int getForHotelVip(){return forHotelVip;}

    public void setForHotelVip(int s){forHotelVip = s;}

    public void setHotelId(int id){this.hotelId = id;}

    public Integer getHotelId(){return hotelId;}

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
