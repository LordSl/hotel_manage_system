package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;

import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.OrderVO;

public interface CouponMatchStrategy {

    /**
     * 判断订单是否满足优惠策略
     * @param orderVO
     * @param coupon
     * @return
     */
    boolean isMatch(OrderVO orderVO, Coupon coupon);

}
