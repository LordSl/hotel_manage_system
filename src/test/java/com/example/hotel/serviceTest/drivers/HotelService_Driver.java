package com.example.hotel.serviceTest.drivers;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.vo.*;

public class HotelService_Driver {

    public void drive(HotelService hotelService){
        hotelService.addHotel(new HotelVO());
        hotelService.retrieveHotels();
        hotelService.deleteHotel(1);
        hotelService.getHotelByHMId(1);
        hotelService.getHotelById(1);
        hotelService.getHotelListByName("as");
        hotelService.getHotelRoom(1,"20200302","20200504");
        if(hotelService.addHotel(new HotelVO()).getSuccess()!=true){
            System.out.println("Error");
        }
        if(hotelService.retrieveHotels().size()!=0){
            System.out.println("Error");
        }
        if(hotelService.deleteHotel(1).getSuccess()!=true){
            System.out.println("Error");
        }
        if(hotelService.getHotelListByName("as").size()!=0){
            System.out.println("Error");
        }
        if(hotelService.getHotelRoom(1,"20200302","20200504").getSuccess()!=true){
            System.out.println("Error");
        }
    }
}
