package com.example.hotel.serviceTest.drivers;

import com.example.hotel.bl.Comment.CommentService;
import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.vo.*;

public class couponService_Driver {
    public void drive(CouponService couponService){
        couponService.deleteCoupon(1);
        couponService.getHotelAllCoupon(1);
        if(couponService.getHotelAllCoupon(1).size()!=0){
            System.out.println("Error");
        }
        couponService.addHotelTargetMoneyCoupon(new HotelTargetMoneyCouponVO());
        couponService.addHotelTargetRoomCoupon(new HotelTargetRoomCouponVO());
        couponService.addTimeCoupon(new TimeCouponVO());
        couponService.getCouponForDisplay(1);
        couponService.getMatchOrderCoupon(new OrderVO());
        if(couponService.deleteCoupon(1)!=1){
            System.out.println("Error");
        }
        if(couponService.getHotelAllCoupon(1).size()!=0){
            System.out.println("Error");
        }
        if(couponService.getCouponForDisplay(1).size()!=0){
            System.out.println("Error");
        }
        if(couponService.getMatchOrderCoupon(new OrderVO()).size()!=0){
            System.out.println("Error");
        }
    }
}
