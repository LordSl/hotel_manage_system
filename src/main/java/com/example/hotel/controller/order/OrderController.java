package com.example.hotel.controller.order;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.vo.AppealVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */


@RestController()
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseVO reserveHotel(@RequestBody OrderVO orderVO){

        return orderService.addOrder(orderVO);
    }

    @PostMapping("/changeAppealStatus")
    public ResponseVO changeAppealStatus(@RequestBody AppealVO appealVO){
        return orderService.changeAppealStatus(appealVO);
    }



    @PostMapping("/cancelAppeal")
    public ResponseVO orderAppeal(@RequestBody AppealVO appealVO){
        return orderService.appealOrder(appealVO);
    }

    @GetMapping("/getAllOrders")
    public ResponseVO retrieveAllOrders(){
        return ResponseVO.buildSuccess(orderService.getAllOrders());
    }

    @GetMapping("/{userid}/getUserOrders")
    public  ResponseVO retrieveUserOrders(@PathVariable int userid){
        return ResponseVO.buildSuccess(orderService.getUserOrders(userid));
    }

    @GetMapping("/{orderid}/annulOrder")
    public ResponseVO annulOrder(@PathVariable int orderid){
        return orderService.annulOrder(orderid);
    }


    @GetMapping("/{hotelId}/allOrders")
    public ResponseVO retrieveHotelOrders(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(orderService.getHotelOrders(hotelId));
    }


    @GetMapping("/{orderid}/deleteOrder")
    public ResponseVO deleteOrder(@PathVariable int orderid){return orderService.deleteOrder(orderid);}

    @GetMapping("/{orderId}/getAppealMessage")
    public ResponseVO getAppealMessage(@PathVariable int orderId){return orderService.getAppealMessage(orderId);}

    @GetMapping("/{orderid}/checkedIn")
    public ResponseVO orderCheckedIn(@PathVariable int orderid){return orderService.checkInOrder(orderid);}

    @GetMapping("/autoUpdateOrderStatus")
    public ResponseVO autoUpdateOrderStatus(){
        return orderService.autoUpdateOrderStatus();
    }

    @GetMapping("/getStateOfUP")
    public ResponseVO getStateOfUP(@RequestParam Integer userId,@RequestParam Integer hotelId){
        return orderService.getStateOfUP(userId,hotelId);
    }

    @GetMapping("/getPrevOrder")
    public ResponseVO getPrevOrder(@RequestParam Integer userId,@RequestParam Integer hotelId){
        return orderService.getPrevOrder(userId,hotelId);
    }
    @GetMapping("/{hotelId}/getOrderListByHotelId")
    public ResponseVO getOrderListByHotelId(@PathVariable int hotelId){
        return ResponseVO.buildSuccess(orderService.getHotelOrders(hotelId));
    }
}
