package com.example.hotel.data.coupon;

import com.example.hotel.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CouponMapper {

    /**
     * 插入一条新的优惠策略
     * @param coupon
     * @return
     */
    int insertCoupon(Coupon coupon);

    /**
     * 获得酒店所有的优惠策略
     * @param hotelId
     * @return
     */
    List<Coupon> selectByHotelId(@Param("hotelId") Integer hotelId);

    /**
     * 获得网站所有的优惠策略
     * @return
     */
    List<Coupon> selectWebCoupons();

    /**
     * 删除一套优惠策略
     * @param id
     * @return
     */
    int deleteCoupon(@Param("couponId") Integer id);
}
