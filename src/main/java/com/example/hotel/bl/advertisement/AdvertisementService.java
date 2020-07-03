package com.example.hotel.bl.advertisement;
import com.example.hotel.vo.ResponseVO;

/**
 * 广告Service
 */
public interface AdvertisementService {

    /**
     * 获取所有广告
     * @return
     */
    ResponseVO getAdvertisementList();

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
