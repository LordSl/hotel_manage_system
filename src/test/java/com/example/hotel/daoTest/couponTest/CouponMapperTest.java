package com.example.hotel.daoTest.couponTest;

import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponMapperTest extends TestCase {

    @Autowired
    private CouponMapper couponMapper;
    @Test
    public void testInsertCoupon() {
        Coupon coupon=new Coupon();
        coupon.setCouponName("abc");
        coupon.setCouponType(1);

        int effectNum=couponMapper.insertCoupon(coupon);
        assertEquals(1,effectNum);
    }

    @Test
    public void testDeleteCoupon() {
        int effectNum=couponMapper.deleteCoupon(2);
        assertEquals(1,effectNum);
    }
}