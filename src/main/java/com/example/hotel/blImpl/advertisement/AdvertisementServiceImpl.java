package com.example.hotel.blImpl.advertisement;


import com.example.hotel.bl.advertisement.AdvertisementService;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.data.advertisement.AdvertisementMapper;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.po.Advertisement;
import com.example.hotel.vo.AdvertisementVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private AdvertisementMapper advertisementMapper;
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private HotelService hotelService;


    @Override
    public ResponseVO getAdvertisementList() {
        try {
            List<AdvertisementVO> results = new ArrayList<>();
            List<Advertisement> got = advertisementMapper.getAdvertisementList();
            for (int i = 0; i < got.size(); i++) {
                AdvertisementVO advertisementVO = new AdvertisementVO();
                advertisementVO.setAdId(got.get(i).getAdId());
                advertisementVO.setHotelId(got.get(i).getHotelId());
                advertisementVO.setAdImgUrl(got.get(i).getAdImgUrl());
                HotelVO hotelVO = hotelService.getHotelById(advertisementVO.getHotelId());
                advertisementVO.setHotel(hotelVO);
                results.add(advertisementVO);
            }
            return ResponseVO.buildSuccess(results);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("获取广告失败");
        }

    }

    @Override
    public ResponseVO deleteAD(int id) {
        List<AdvertisementVO> got = (List<AdvertisementVO>) advertisementService.getAdvertisementList().getContent();
        if (got.size() == 1) {
            return ResponseVO.buildFailure("至少有一张广告");
        }
        try {
            advertisementMapper.deleteAD(id);
            return ResponseVO.buildSuccess(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("获取广告失败");
        }
    }

    @Override
    public ResponseVO addADImg(String imgUrl) {
        try {
            advertisementMapper.addADImg(imgUrl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("上传图片失败");
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO bindADHotel(int adId, int hotelId) {
        try {
            advertisementMapper.bindADHotel(adId, hotelId);
            return ResponseVO.buildSuccess(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("绑定酒店失败");
        }
    }
}
