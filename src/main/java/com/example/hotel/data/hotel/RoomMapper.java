package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.vo.RoomVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoomMapper {

    /**
     * 更新房间数
     * @param hotelId
     * @param roomType
     * @param curNum
     * @return
     */
    int updateRoomInfo(@Param("hotelId") Integer hotelId,@Param("roomType") String roomType,@Param("curNum") Integer curNum);

    /**
     * 插入新的房间
     * @param hotelRoom
     * @return
     */
    int insertRoom(HotelRoom hotelRoom);

    /**
     * 更新房间
     * @param hotelRoom
     * @return
     */
    int updateRoom(@Param("hotelRoom") HotelRoom hotelRoom);

    /**
     * 删除房间
     * @param roomId
     */
    void deleteRoom(Integer roomId);

    /**
     * 根据酒店id获取所有房间
     * @param hotelId
     * @return
     */
    List<HotelRoom> selectRoomsByHotelId(@Param("hotelId") Integer hotelId);

    /**
     * 根据房间id获取房间
     * @param id
     * @return
     */
    HotelRoom selectRoomByRoomId(@Param("id")Integer id);

    /**
     * 获得房间当前数量
     * @param roomId
     * @return
     */
    int getRoomCurNum(@Param("roomId") Integer roomId);

    /**
     * 添加房间数
     * @param roomId
     * @param num
     * @return
     */
    int addRoomNum(@Param("roomId")Integer roomId,@Param("num")Integer num);

    /**
     * 上传房间图片
     * @param id
     * @param roomImg
     * @return
     */
    int updateRoomImg(@Param("id") int id, @Param("roomImg") String roomImg);
}
