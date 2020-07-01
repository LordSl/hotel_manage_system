package com.example.hotel.controller.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/hotelTargetMoney")
    public ResponseVO addHotelTargetMoneyCoupon(@RequestBody HotelTargetMoneyCouponVO hotelTargetMoneyCouponVO) {

        CouponVO couponVO = couponService.addHotelTargetMoneyCoupon(hotelTargetMoneyCouponVO);

        return ResponseVO.buildSuccess(couponVO);
    }

    @PostMapping("/hotelTargetRoom")
    public ResponseVO addHotelTargetRoomCoupon(@RequestBody HotelTargetRoomCouponVO hotelTargetRoomCouponVO) {

        CouponVO couponVO = couponService.addHotelTargetRoomCoupon(hotelTargetRoomCouponVO);

        return ResponseVO.buildSuccess(couponVO);
    }

    @PostMapping("/hotelTargetTime")
    public ResponseVO addHotelTimeCoupon(@RequestBody TimeCouponVO timeCouponVO) {

        CouponVO couponVO = couponService.addTimeCoupon(timeCouponVO);

        return ResponseVO.buildSuccess(couponVO);
    }



    @GetMapping("/hotelAllCoupons")
    public ResponseVO getHotelAllCoupons(@RequestParam Integer hotelId) {
        return ResponseVO.buildSuccess(couponService.getHotelAllCoupon(hotelId));
    }

    @GetMapping("/orderMatchCoupons")
    public ResponseVO getOrderMatchCoupons(@RequestParam Integer userId,
                                           @RequestParam Integer hotelId,
                                           @RequestParam Double orderPrice,
                                           @RequestParam Integer roomNum,
                                           @RequestParam String checkIn,
                                           @RequestParam String checkOut) {
        OrderVO requestOrderVO = new OrderVO();
        requestOrderVO.setUserId(userId);
        requestOrderVO.setHotelId(hotelId);
        requestOrderVO.setPrice(orderPrice);
        requestOrderVO.setRoomNum(roomNum);
        requestOrderVO.setCheckInDate(checkIn);
        requestOrderVO.setCheckOutDate(checkOut);
        return ResponseVO.buildSuccess(couponService.getMatchOrderCoupon(requestOrderVO));
    }



    @GetMapping("/webCoupon")
    public ResponseVO getWebCoupons(){
        return ResponseVO.buildSuccess(this.couponService.getWebCoupons());
    }

    @GetMapping("/delete")
    public ResponseVO deleteCoupon(@RequestParam Integer id){
        return ResponseVO.buildSuccess(this.couponService.deleteCoupon(id));
    }

    @GetMapping("/{hotelId}/getCouponForDisplay")
    public ResponseVO getCouponForDisplay(@PathVariable int hotelId){
        return ResponseVO.buildSuccess(couponService.getCouponForDisplay(hotelId));
    }
}
