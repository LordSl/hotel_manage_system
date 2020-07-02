package com.example.hotel.vo;

/**
 * 酒店多间特惠： eg 大于2间减100
 */
public class HotelTargetRoomCouponVO extends CouponVO{

    private Integer hotelId;

    private Integer targetRoom;

    private Integer discountMoney;

    private double discount;

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Integer getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Integer targetMoney) {
        this.targetMoney = targetMoney;
    }

    private Integer targetMoney;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getTargetRoom() {
        return targetRoom;
    }

    public void setTargetRoom(Integer targetRoom) {
        this.targetRoom = targetRoom;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }
}
