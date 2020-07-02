package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class CouponServiceImpl implements CouponService {

    private final TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy;
    private final MultRoomStrategyImpl multRoomStrategy;
    private final TimeCouponStrategyImpl timeCouponStrategy;
    private final CouponMapper couponMapper;

    private static List<CouponMatchStrategy> strategyList = new ArrayList<>();



    @Autowired HotelVIPCouponStrategyImpl hotelVIPCouponStrategy;

    @Autowired
    public CouponServiceImpl(
                             TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy,
                             MultRoomStrategyImpl multRoomStrategy,
                             TimeCouponStrategyImpl timeCouponStrategy,
                             CouponMapper couponMapper) {
        this.multRoomStrategy = multRoomStrategy;
        this.couponMapper = couponMapper;
        this.targetMoneyCouponStrategy = targetMoneyCouponStrategy;
        this.timeCouponStrategy = timeCouponStrategy;
        strategyList.add(targetMoneyCouponStrategy);
        strategyList.add(timeCouponStrategy);
        strategyList.add(multRoomStrategy);
    }





    @Override
    public List<Coupon> getMatchOrderCoupon(OrderVO orderVO) {

        List<Coupon> hotelCoupons = getHotelAllCoupon(orderVO.getHotelId());
        List<Coupon> webCoupons= getWebCoupons();
        List<Coupon> availAbleCoupons = new ArrayList<>();
        hotelCoupons.addAll(webCoupons);
        //先获取到所有的coupon，下面进行筛选

        for (int i = 0; i < hotelCoupons.size(); i++) {
            //网站会员和酒店会员的判断写进了matchStrategy里面
            for (CouponMatchStrategy strategy : strategyList) {
                if (strategy.isMatch(orderVO, hotelCoupons.get(i))) {
                    availAbleCoupons.add(hotelCoupons.get(i));
                }
            }
        }


        return availAbleCoupons;
    }


    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        List<Coupon> hotelCoupons = couponMapper.selectByHotelId(hotelId);
        return hotelCoupons;
    }

    @Override
    public CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setTargetMoney((double)couponVO.getTargetMoney());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney((double)couponVO.getDiscountMoney());
        coupon.setStartTime(couponVO.getStartTime());
        coupon.setEndTime(couponVO.getEndTime());
        coupon.setStatus(1);
        coupon.setTargetRoom(-1.0D);
        coupon.setForHotelVip(couponVO.getForHotelVip());
        int result = this.couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addHotelTargetRoomCoupon(HotelTargetRoomCouponVO couponVO){
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setTargetMoney((double)couponVO.getTargetMoney());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney((double)couponVO.getDiscountMoney());
        coupon.setStartTime(couponVO.getStartTime());
        coupon.setEndTime(couponVO.getEndTime());
        coupon.setTargetRoom((double)couponVO.getTargetRoom());
        coupon.setStatus(1);
        coupon.setForHotelVip(couponVO.getForHotelVip());
        coupon.setDiscount(couponVO.getDiscount());
        try{
            int result = this.couponMapper.insertCoupon(coupon);
            couponVO.setId(result);
            return couponVO;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


    }

    @Override
    public CouponVO addTimeCoupon(TimeCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setTargetMoney((double)couponVO.getTargetMoney());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney((double)couponVO.getDiscountMoney());
        coupon.setStartTime(couponVO.getStartTime());
        coupon.setEndTime(couponVO.getEndTime());
        coupon.setForHotelVip(couponVO.getForHotelVip());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setStatus(1);
        int result = this.couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public List<Coupon> getWebCoupons() {
        List<Coupon> coupons=couponMapper.selectWebCoupons();
        return coupons;
    }

    @Override
    public int deleteCoupon(Integer id) {
        return couponMapper.deleteCoupon(id);
    }


    //获取用户酒店详情页面显示的Coupon
    @Override
    public List<CouponVO> getCouponForDisplay(int hotelId) {
        List<Coupon>couponsGot = couponMapper.selectByHotelId(hotelId);//酒店的
        couponsGot.addAll(couponMapper.selectByHotelId(0));//网站的优惠
        List<CouponVO>result = new ArrayList<>();
        for(Coupon coupon: couponsGot){
            //只需要描述和酒店id
            CouponVO couponVO = new CouponVO();
            couponVO.setDescription(coupon.getDescription());
            couponVO.setHotelId(coupon.getHotelId());
            couponVO.setName(coupon.getCouponName());
            couponVO.setId(coupon.getId());
            result.add(couponVO);
        }
        return result;
    }
}
