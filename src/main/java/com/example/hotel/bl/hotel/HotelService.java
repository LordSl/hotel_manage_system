package com.example.hotel.bl.hotel;

import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

public interface HotelService {

    /**
     * 添加酒店
     * @param hotelVO
     * @throws
     */
    ResponseVO addHotel(HotelVO hotelVO);

    /**
     * 更新酒店信息
     * @param hotelVO
     * @throws ServiceException
     */
    void updateHotelInfo(HotelVO hotelVO) throws ServiceException;

    /**
     * 预订酒店修改剩余客房信息
     * @param hotelId
     * @param roomType
     * @param rooms
     */
    void updateRoomInfo(Integer hotelId, String roomType,Integer rooms);

    /**
     * 删除酒店
     * @param hotelId
     */
    void deleteHotel(Integer hotelId);

    /**
     * 根据hotel名字获得所有hotel
     * @param hotelName
     * @return
     */
    List<HotelVO> getHotelListByName(String hotelName);

    /**
     * 列表获取酒店信息
     * @return
     */
    List<HotelVO> retrieveHotels();

    /**
     * 列表获取特定商区下的酒店
     * @param bizRegion
     * @return
     */
    List<HotelVO> retrieveHotelByBizRegion(String address,
                                           String bizRegion,
                                           String hotelStar,
                                           String checkInDate,
                                           String checkOutDate,
                                           Integer roomNum,
                                           String key,
                                           Boolean haveReserved,
                                           Integer userId

                                           );
    /**
     * 获取某家酒店详细信息
     * @param hotelId
     * @return
     */
    HotelVO retrieveHotelDetails(Integer hotelId);

    /**
     * 根据酒店管理员获取酒店
     * @param HMId
     * @return
     */
    HotelVO getHotelByHMId(Integer HMId);

    /**
     * 查看酒店的所有订单
     * @param hotelId
     * @return
     */
    List<OrderVO> getHotelOrders(Integer hotelId);

    /**
     * 更新酒店图片
     * @param id
     * @param hotelImg
     * @return
     */
    ResponseVO updateHotelImg(int id, String hotelImg);

    /**
     * 根据酒店id获取酒店
     * @param id
     * @return
     */
    HotelVO getHotelById(Integer id);
}
