package com.example.hotel.serviceTest.drivers;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.bl.advertisement.AdvertisementService;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserInfoVO;


public class advertisementService_Driver {
    public void drive(AdvertisementService advertisementService){
        if(advertisementService.getAdvertisementList().getSuccess()!=true){
            System.out.println("Error");
        }
        if(advertisementService.addADImg("add").getSuccess()!=true){
            System.out.println("Error");
        }
        if(advertisementService.bindADHotel(1,1).getSuccess()!=true){
            System.out.println("Error");
        }
        if(advertisementService.deleteAD(1).getSuccess()!=true){
            System.out.println("Error");
        }
    }
}
