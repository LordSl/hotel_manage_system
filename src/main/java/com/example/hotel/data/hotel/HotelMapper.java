package com.example.hotel.data.hotel;

import com.example.hotel.enums.BizRegion;
import com.example.hotel.po.Hotel;
import com.example.hotel.vo.HotelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HotelMapper {

    /**
     * 插入一条新的酒店
     * @param hotel
     * @return
     */
    int insertHotel(Hotel hotel);

    /**
     * 更新酒店信息
     * @param hotel
     * @return
     */
    int updateHotelInfo(Hotel hotel);

    /**
     * 获取所有的酒店
     * @return
     */
    List<Hotel> selectAllHotel();

    /**
     * 根据id获取酒店
     * @param id
     * @return
     */
    Hotel selectById(@Param("id") Integer id);

    /**
     * 根据酒店管理员id获取酒店
     * @param id
     * @return
     */
    Hotel selectByHMId(@Param("id") Integer id);//通过HotelManager唯一指定

    /**
     * 根据酒店id删除酒店
     * @param hotelId
     */
    void deleteHotel(Integer hotelId);

    /**
     * 更新酒店的封面图片
     * @param id
     * @param hotelImg
     * @return
     */
    int updateHotelImg(@Param("id") int id, @Param("hotelImg") String hotelImg);


}
