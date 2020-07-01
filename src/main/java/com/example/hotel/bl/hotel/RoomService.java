package com.example.hotel.bl.hotel;

import com.example.hotel.po.HotelRoom;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.RoomVO;

import java.util.List;

public interface RoomService {

    /**
     * 获取某个酒店的全部房间信息
     * @param hotelId
     * @return
     */
    List<RoomVO> retrieveHotelRoomInfo(Integer hotelId);

    /**
     * 添加酒店客房信息
     * @param hotelRoom
     */
    void insertRoomInfo(RoomVO hotelRoom);

    /**
     * 预订酒店后更新客房房间数量
     * @param hotelId
     * @param roomType
     * @param rooms
     */
    void updateRoomInfo(Integer hotelId, String roomType, Integer rooms);

    /**
     * 更新酒店房间
     * @param hotelRoom
     */
    void updateRoom(RoomVO hotelRoom);

    /**
     * 删除房间
     * @param roomId
     */
    void deleteRoom(Integer roomId);
    /**
     * 根据酒店和roomId获得当前房间的个数
     * @param roomId
     * @param checkInDate
     * @param checkOutDate
     * @return
     */
    Integer getRoomNum(Integer roomId,String checkInDate,String checkOutDate);

    /**
     * 增加酒店房间
     * @param roomId
     * @param num
     * @return
     */
    Integer addRoomNum(Integer roomId, Integer num);

    /**
     * 更新房间图片
     * @param id
     * @param roomImg
     * @return
     */
    ResponseVO updateRoomImg (int id, String roomImg);

    /**
     * 根据房间id获得房间信息
     * @param id
     * @return
     */
    RoomVO getOrderRoom(Integer id);

}
