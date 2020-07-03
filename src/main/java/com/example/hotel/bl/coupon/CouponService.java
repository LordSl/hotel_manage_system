package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;

import java.util.List;

/**
 * 优惠Service
 */
public interface CouponService {
    /**
     * 返回某一订单可用的优惠策略列表
     * @param orderVO
     * @return
     */
    List<Coupon> getMatchOrderCoupon(OrderVO orderVO);

    /**
     * 查看某个酒店提供的所有优惠策略（包括失效的）
     * @param hotelId
     * @return
     */
    List<Coupon> getHotelAllCoupon(Integer hotelId);

    /**
     * 添加酒店满减优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO);

    /**
     * 添加酒店多间特惠策略
     * @param couponVO
     * @return
     */
    CouponVO addHotelTargetRoomCoupon(HotelTargetRoomCouponVO couponVO);

    /**
     * 添加酒店限时特惠策略
     * @param couponVO
     * @return
     */
    CouponVO addTimeCoupon(TimeCouponVO couponVO);

    /**
     * 获得所有的网站优惠策略
     * @return
     */
    List<Coupon> getWebCoupons();

    /**
     * 删除评论
     * @param id
     * @return
     */
    int deleteCoupon(Integer id);

    List<CouponVO> getCouponForDisplay(int hotelId);
}
