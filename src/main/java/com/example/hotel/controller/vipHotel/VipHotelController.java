package com.example.hotel.controller.vipHotel;


import com.example.hotel.bl.vipHotel.VipHotelService;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/vipHotel")
public class VipHotelController {
    @Autowired
    VipHotelService vipHotelService;

    @GetMapping("/registerAsHotelVIP")
    ResponseVO registerAsHotelVIP(@RequestParam int userId,@RequestParam int hotelId){
        vipHotelService.registerAsHotelVIP(userId,hotelId);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/isVIPCouple")
    ResponseVO isVIPCouple(@RequestParam int userId, @RequestParam int hotelId){
        boolean result = vipHotelService.isVIPCouple(userId,hotelId);
        return ResponseVO.buildSuccess(result);
    }


    @GetMapping("/{userId}/getHotelListOfCurrentVIP")
    ResponseVO getHotelListOfCurrentVIP(@PathVariable int userId){
        return ResponseVO.buildSuccess(vipHotelService.getHotelListOfCurrentVIP(userId));
    }


    @GetMapping("/{hotelId}/getVipListOfCurrentHotel")
    ResponseVO getVipListOfCurrentHotel(@PathVariable int hotelId){
        return ResponseVO.buildSuccess(vipHotelService.getVipListOfCurrentHotel(hotelId));
    }

    @GetMapping("/deleteVIPHotel")
    ResponseVO deleteVIPHotel(@RequestParam int userId, @RequestParam int hotelId){
        vipHotelService.deleteVIPHotel(userId,hotelId);
        return ResponseVO.buildSuccess(true);
    }
}
