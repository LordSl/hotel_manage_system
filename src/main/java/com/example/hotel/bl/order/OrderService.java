package com.example.hotel.bl.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.AppealVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface OrderService {

    /**
     * 预订酒店
     * @param orderVO
     * @return
     */
    ResponseVO addOrder(OrderVO orderVO);

    /**
     * 获得所有订单信息
     * @return
     */
    List<OrderVO> getAllOrders();

    /**
     * 获得指定用户的所有订单信息
     * @param userid
     * @return
     */
    List<OrderVO> getUserOrders(int userid);

    /**
     * 撤销订单
     * @param orderid
     * @return
     */
    ResponseVO annulOrder(int orderid);

    /**
     * 申诉订单
     * @param appealVO
     * @return
     */
    ResponseVO appealOrder(AppealVO appealVO);

    /**
     * 查看酒店的所有订单
     * @param hotelId
     * @return
     */
    List<OrderVO> getHotelOrders(Integer hotelId);


    /**
     * 删除订单
     * @param orderid
     * @return
     */
    ResponseVO deleteOrder(int orderid);

    /**
     * 获得申诉信息
     * @param orderid
     * @return
     */
    ResponseVO getAppealMessage(int orderid);

    /**
     * 改变申诉状态
     * @param appealVO
     * @return
     */
    ResponseVO changeAppealStatus(AppealVO appealVO);

    /**
     * 改变订单状态为已入住
     * @param orderid
     * @return
     */
    ResponseVO checkInOrder(int orderid);

    /**
     * 更新订单状态
     * @return
     */
    ResponseVO autoUpdateOrderStatus();

    /**
     * 获得顾客是否住过该酒店的状态
     * @param userId
     * @param hotelId
     * @return
     */
    ResponseVO getStateOfUP(Integer userId, Integer hotelId);

    /**
     * 返回用户是否有订单待进入
     * @param userId
     * @param hotelId
     * @return
     */
    ResponseVO getPrevOrder(Integer userId,Integer hotelId);


}
