package com.example.hotel.serviceTest.Stubs;

import com.example.hotel.bl.advertisement.AdvertisementService;
import com.example.hotel.vo.AdvertisementVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;

import java.util.LinkedList;
import java.util.List;

public class advertisement_Stub implements AdvertisementService {
    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getAdImgUrl() {
        return adImgUrl;
    }

    public void setAdImgUrl(String adImgUrl) {
        this.adImgUrl = adImgUrl;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public HotelVO getHotel() {
        return hotel;
    }

    public void setHotel(HotelVO hotel) {
        this.hotel = hotel;
    }

    int adId;
    String adImgUrl;
    int hotelId;
    HotelVO hotel;


    @Override
    public ResponseVO getAdvertisementList() {
        List<AdvertisementVO> advertisementVOS=new LinkedList<>();
        System.out.println("getAdvertisementList");
        return ResponseVO.buildSuccess(advertisementVOS);
    }

    @Override
    public ResponseVO deleteAD(int id) {
        System.out.println("deleteAd");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO addADImg(String imgUrl) {
        System.out.println("addADImg");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO bindADHotel(int adId, int hotelId) {
        System.out.println("bindADHotel");
        return ResponseVO.buildSuccess();
    }
}
