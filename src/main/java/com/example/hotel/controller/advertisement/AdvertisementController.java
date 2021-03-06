package com.example.hotel.controller.advertisement;

import com.example.hotel.bl.advertisement.AdvertisementService;
import com.example.hotel.vo.AdvertisementVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping("/getAdList")
    public ResponseVO getAdList() {
        return advertisementService.getAdvertisementList();
    }

    @GetMapping("/{id}/deleteAD")
    public ResponseVO deleteAD(@PathVariable int id){
        return advertisementService.deleteAD(id);
    }

    @GetMapping("/addADImg")
    public ResponseVO addADImg(@RequestParam String imgUrl){

        return advertisementService.addADImg(imgUrl);
    }

    @GetMapping("/bindADHotel")
    public ResponseVO bindADHotel(@RequestParam int adId, @RequestParam int hotelId){

        return advertisementService.bindADHotel(adId, hotelId);
    }
}
