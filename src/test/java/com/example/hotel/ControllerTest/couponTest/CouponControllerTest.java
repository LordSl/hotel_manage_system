package com.example.hotel.ControllerTest.couponTest;

import com.example.hotel.controller.coupon.CouponController;
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
public class CouponControllerTest {

    @Autowired
    private CouponController couponController;


    @Test
    public void getHotelAllCoupons() {
        ResponseVO responseVO =couponController.getHotelAllCoupons(2);
        assertEquals(1,((List<Coupon>)responseVO).size());

    }
}