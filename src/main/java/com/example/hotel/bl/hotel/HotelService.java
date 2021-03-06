package com.example.hotel.bl.hotel;

import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import java.util.List;

/**
 * 酒店的Service层
 */
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
    ResponseVO updateHotelInfo(HotelVO hotelVO) throws ServiceException;

    /**
     * 删除酒店
     * @param hotelId
     */
    ResponseVO deleteHotel(Integer hotelId);

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
                                           Integer hotelStar,
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

    /**
     * 获取特定时期的房间数
     * @param hotelId
     * @param checkInDate
     * @param checkOutDate
     * @return
     */
    ResponseVO getHotelRoom(Integer hotelId,String checkInDate,String checkOutDate);
}
