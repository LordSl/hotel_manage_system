package com.example.hotel.controller.hotel;

import com.example.hotel.bl.hotel.HPicService;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.po.HPic;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HPicVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private HPicService hPicService;

    @PostMapping("/addHotel")
    public ResponseVO createHotel(@RequestBody HotelVO hotelVO) {
        return hotelService.addHotel(hotelVO);
    }

    @PostMapping("/updateHotel")
    public ResponseVO updateHotel(@RequestBody HotelVO hotelVO) throws ServiceException {

        hotelService.updateHotelInfo(hotelVO);
        System.out.println(hotelVO.getName()+" "+hotelVO.getId().toString());
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/all")
    public ResponseVO retrieveAllHotels(){
        return ResponseVO.buildSuccess(hotelService.retrieveHotels());
    }

    @PostMapping("/roomInfo")
    public ResponseVO addRoomInfo(@RequestBody RoomVO hotelRoom) {
        roomService.insertRoomInfo(hotelRoom);
        return ResponseVO.buildSuccess(true);
    }

    @PostMapping("/updateRoom")
    public ResponseVO updateRoomInfo(@RequestBody RoomVO hotelRoom) {
        roomService.updateRoom(hotelRoom);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/{roomId}/deleteRoom")
    public ResponseVO deleteRoom(@PathVariable Integer roomId) {
        roomService.deleteRoom(roomId);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/addRoomNum")
    public ResponseVO addRoomNum(@RequestParam Integer roomId,@RequestParam Integer num){
        roomService.addRoomNum(roomId,num);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/getCurRoom")
    public  ResponseVO getCurRoom(@RequestParam Integer roomId,@RequestParam String checkInDate,@RequestParam String checkOutDate){
        return ResponseVO.buildSuccess(roomService.getRoomNum(roomId,checkInDate,checkOutDate));
    }

    @GetMapping("/{hotelId}/detail")
    public ResponseVO retrieveHotelDetail(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(hotelService.retrieveHotelDetails(hotelId));
    }

    @GetMapping("/{HMId}/getHotelByHMId")
    public ResponseVO getHotelByHMId(@PathVariable Integer HMId) {
        return ResponseVO.buildSuccess(hotelService.getHotelByHMId(HMId));
    }

    @GetMapping("/getHotelListByName")
    public ResponseVO getHotelListByName(@RequestParam String hotelName){
        return ResponseVO.buildSuccess(hotelService.getHotelListByName(hotelName));
    }

    @GetMapping("/{hotelId}/allOrders")
    public ResponseVO retrieveHotelOrders(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(hotelService.getHotelOrders(hotelId));
    }
    @GetMapping("/{hotelId}/deleteHotel")
    public ResponseVO deleteHotel(@PathVariable Integer hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseVO.buildSuccess(true);
    }
    @GetMapping("/search")
    public ResponseVO searchHotel(@RequestParam String address,
                                          @RequestParam String bizRegion,
                                          @RequestParam String hotelStar,
                                          @RequestParam String checkInDate,
                                          @RequestParam String checkOutDate,
                                          @RequestParam Integer roomNum,
                                          @RequestParam String key,
                                          @RequestParam Boolean haveReserved,
                                          @RequestParam Integer userId
                                          ) {
        return ResponseVO.buildSuccess(hotelService.retrieveHotelByBizRegion(address,bizRegion,hotelStar,checkInDate,checkOutDate,roomNum,key,haveReserved,userId));
    }

    @GetMapping("/hotelImg")
    public ResponseVO updateHotelImg(@RequestParam String hotelImg,@RequestParam Integer id){
        return hotelService.updateHotelImg(id,hotelImg);
    }

    @GetMapping("/roomImg")
    public ResponseVO updateRoomImg(@RequestParam String roomImg,@RequestParam Integer id){
        return roomService.updateRoomImg(id,roomImg);
    }

    @PostMapping("/addHPic")
    public ResponseVO addHotelPic(@RequestBody HPicVO hPicVO){
        return hPicService.addHPic(hPicVO);
    }


    @GetMapping("/deleteHPic")
    public ResponseVO deleteHotelPic(@RequestParam Integer id){
        return hPicService.deleteHPic(id);
    }

}
