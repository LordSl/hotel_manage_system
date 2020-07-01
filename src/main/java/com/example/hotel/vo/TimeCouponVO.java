package com.example.hotel.vo;

import java.time.LocalDateTime;

/**
 * 酒店限时优惠
 * 自行决定是打折还是直接减金额 如果选择打折就把targetMoney变为负数 否则就把discount变为负数
 */
public class TimeCouponVO extends CouponVO {


    private Integer hotelId;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }



    private Integer discountMoney;

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    private double discount;

    private Integer targetMoney;

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
}
