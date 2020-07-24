package com.example.hotel.serviceTest.Stubs;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;

import java.util.LinkedList;
import java.util.List;

public class CouponService_Stub implements CouponService {
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

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
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

    public int getForHotelVip() {
        return forHotelVip;
    }

    public void setForHotelVip(int forHotelVip) {
        this.forHotelVip = forHotelVip;
    }

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

    @Override
    public List<Coupon> getMatchOrderCoupon(OrderVO orderVO) {
        System.out.println("getMatchOrderCoupon");
        return new LinkedList<>();
    }

    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        System.out.println("getHotelAllCoupon");
        return new LinkedList<>();
    }

    @Override
    public CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO) {
        System.out.println("addHotelTargetMoneyCoupon");
        return couponVO;
    }

    @Override
    public CouponVO addHotelTargetRoomCoupon(HotelTargetRoomCouponVO couponVO) {
        System.out.println("addHotelTargetRoomCoupon");
        return couponVO;
    }

    @Override
    public CouponVO addTimeCoupon(TimeCouponVO couponVO) {
        System.out.println("addTimeCoupon");
        return couponVO;
    }

    @Override
    public List<Coupon> getWebCoupons() {
        System.out.println("getHotelAllCoupon");
        return new LinkedList<>();
    }

    @Override
    public int deleteCoupon(Integer id) {
        System.out.println("deleteCoupon");
        return 1;
    }

    @Override
    public List<CouponVO> getCouponForDisplay(int hotelId) {
        System.out.println("getHotelAllCoupon");
        return new LinkedList<>();
    }
}
