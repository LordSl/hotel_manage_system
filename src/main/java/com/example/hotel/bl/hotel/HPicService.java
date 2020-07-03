package com.example.hotel.bl.hotel;

import com.example.hotel.vo.HPicVO;
import com.example.hotel.vo.ResponseVO;
import java.util.List;


/**
 * 酒店图册Service
 */
public interface HPicService {

    /**
     * 增加酒店图片
     * @param hPicVO
     * @return
     */
    ResponseVO addHPic(HPicVO hPicVO);

    /**
     * 删除酒店图片
     * @param id
     * @return
     */
    ResponseVO deleteHPic(Integer id);

    /**
     * 根据酒店id去获取酒店图片
     * @param hotelId
     * @return
     */
    List<HPicVO> getHotelPicById(Integer hotelId);
}
