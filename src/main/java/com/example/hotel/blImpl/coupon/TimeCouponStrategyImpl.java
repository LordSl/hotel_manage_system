package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.User;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeCouponStrategyImpl implements CouponMatchStrategy {


    /**
     * 判断某个订单是否满足某种限时优惠策略
     * @param orderVO
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        if (coupon.getCouponType() == 4 && orderVO.getCreateDate().compareTo(coupon.getStartTime()) >= 0 && orderVO.getCreateDate().compareTo(coupon.getEndTime()) <= 0) {
            //符和限时优惠的条件了
            //已经符和满减条件，下面判断是否符和会员
            //网站优惠
            if (coupon.getHotelId() == 0) {
                if (coupon.getForHotelVip() == 0) {
                    //不是针对网站会员
                    return true;
                } else {
                    if (isWebVip(orderVO.getUserId())) return true;
                    return false;
                }
            }
            //酒店会员
            if (coupon.getForHotelVip() == 1) {
                if (hotelVIPCouponStrategy.isMatch(orderVO, coupon)) {
                    //如果是酒店会员
                    return true;
                }
                return false;
            }
            //不针对会员
            return true;
        }
        return false;
    }


    @Autowired
    HotelVIPCouponStrategyImpl hotelVIPCouponStrategy;
    @Autowired
    AccountMapper accountMapper;

    Boolean isWebVip(int id) {
        User user = accountMapper.getAccountById(id);
        if (user.getIsWebVIP() == 0) return false;
        return true;
    }
}
