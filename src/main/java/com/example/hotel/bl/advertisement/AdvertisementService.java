package com.example.hotel.bl.advertisement;

import com.example.hotel.vo.AdvertisementVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

public interface AdvertisementService {

    /**
     * 获取所有广告
     * @return
     */
    List<AdvertisementVO> getAdvertisementList();

    /**
     * 删除酒店
     * @param id
     * @return
     */
    ResponseVO deleteAD(int id);

    /**
     * 增加广告
     * @param imgUrl
     * @return
     */
    ResponseVO addADImg(String imgUrl);

    /**
     * 绑定广告与酒店
     * @param adId
     * @param hotelId
     * @return
     */
    ResponseVO bindADHotel(int adId, int hotelId);

}
