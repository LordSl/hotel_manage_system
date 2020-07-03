package com.example.hotel.bl.vipHotel;

import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserVO;

import java.util.List;

/**
 * 酒店会员Service层
 */
public interface VipHotelService {

    /**
     * 注册酒店vip
     * @param userId
     * @param hotelId
     */
    ResponseVO registerAsHotelVIP(int userId, int hotelId);

    /**
     * 判断是否用户是否满足酒店vip优惠
     * @param userId
     * @param hotelId
     * @return
     */
    boolean isVIPCouple(int userId, int hotelId);

    /**
     * 获取用户所有的vip酒店
     * @param userId
     * @return
     */
    List<HotelVO> getHotelListOfCurrentVIP(int userId);

    /**
     * 获取酒店的所有vip
     * @param hotelId
     * @return
     */
    List<UserVO> getVipListOfCurrentHotel(int hotelId);

    /**
     * 删除酒店vip信息
     * @param userId
     * @param hotelId
     */
    ResponseVO deleteVIPHotel(int userId, int hotelId);
}
