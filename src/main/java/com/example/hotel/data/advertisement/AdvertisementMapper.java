package com.example.hotel.data.advertisement;

import com.example.hotel.po.Advertisement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdvertisementMapper {


    /**
     * 获得所有的广告
     * @return
     */
    List<Advertisement> getAdvertisementList();

    /**
     * 插入一条新的广告
     * @param imgUrl
     */
    void uploadAD(String imgUrl);

    /**
     * 根据id来删除广告
     * @param id
     */
    void deleteAD(int id);

    /**
     * 添加广告照片
     * @param imgUrl
     */
    void addADImg(String imgUrl);

    /**
     * 绑定广告与酒店
     * @param adId
     * @param hotelId
     */
    void bindADHotel(int adId, int hotelId);
}
