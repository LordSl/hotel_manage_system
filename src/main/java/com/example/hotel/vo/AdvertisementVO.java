package com.example.hotel.vo;
public class AdvertisementVO {


    int adId;
    String adImgUrl;
    int hotelId;

    /**
     * 广告vo所持有的hotel对象
     */
    HotelVO hotel;

    public HotelVO getHotel(){return hotel;}
    public void setHotel(HotelVO hotel){this.hotel = hotel;}
    public int getHotelId(){return hotelId;}
    public void setHotelId(int id){hotelId = id;}
    public int getAdId(){return adId;}
    public String getAdImgUrl(){return adImgUrl;}
    public void setAdId(int id){adId = id;}
    public void setAdImgUrl(String url){adImgUrl = url;}

}
