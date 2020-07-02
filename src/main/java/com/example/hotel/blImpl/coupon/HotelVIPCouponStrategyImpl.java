package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.data.vipHotel.VIPHotelMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.VIPHotel;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelVIPCouponStrategyImpl implements CouponMatchStrategy {
    @Autowired
    private VIPHotelMapper vipHotelMapper;
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        VIPHotel vipHotel = vipHotelMapper.retriveByUidHid(orderVO.getUserId(),orderVO.getHotelId());
        if(vipHotel == null)return false;
        return true;
    }
}
