package com.example.hotel.data.vipHotel;

import com.example.hotel.po.Hotel;
import com.example.hotel.po.VIPHotel;
import com.example.hotel.vo.HotelVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VIPHotelMapper {

    /**
     * 注册酒店的vip
     * @param userId
     * @param hotelId
     */
    void registerAsHotelVIP(int userId, int hotelId);

    /**
     * 根据用户id和酒店id获取vip状态
     * @param userId
     * @param hotelId
     * @return
     */
    VIPHotel retriveByUidHid(int userId, int hotelId);

    /**
     * 获取用户所有vip状态
     * @param userId
     * @return
     */
    List<VIPHotel> retriveByUserId(int userId);

    /**
     * 获取酒店所有的vip
     * @param hotelId
     * @return
     */
    List<VIPHotel> retriveByHotelId(int hotelId);

    /**
     * 删除用户的vip状态
     * @param userId
     * @param hotelId
     */
    void deleteVIPHotel(int userId, int hotelId);
}
