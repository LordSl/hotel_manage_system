package com.example.hotel.serviceTest.couponTest;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponServiceTest {
    @Autowired
    private CouponService couponService;

    @Test
    public void getHotelAllCoupon() {
        List<Coupon> coupons=couponService.getHotelAllCoupon(2);
        assertEquals(1,coupons.size());
    }

    @Test
    public void deleteCoupon() {
        int effectNum=couponService.deleteCoupon(2);
        assertEquals(1,effectNum);
    }
}