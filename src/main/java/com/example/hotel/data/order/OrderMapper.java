package com.example.hotel.data.order;

import com.example.hotel.po.Appeal;
import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface OrderMapper {


    /**
     * 增加订单
     * @param order
     * @return
     */
    int addOrder(Order order);


    /**
     * 获取所有的订单
     * @return
     */
    List<Order> getAllOrders();

    /**
     * 获取用户所有的订单
     * @param userid
     * @return
     */
    List<Order> getUserOrders(@Param("userid") int userid);

    /**
     * 取消订单
     * @param orderid
     * @return
     */
    int annulOrder(@Param("orderid") int orderid);

    /**
     * 根据订单id获取订单
     * @param orderid
     * @return
     */
    Order getOrderById(@Param("orderid") int orderid);

    /**
     * 删除订单
     * @param orderid
     * @return
     */
    int deleteOrder(int orderid);

    /**
     * 删除申诉
     * @param orderid
     */
    void deleteAppeal(int orderid);


    /**
     * 改变订单的点入状态
     * @param orderid
     */
    void checkInOrder(int orderid);

    /**
     * 设置订单的异常状态
     * @param id
     */
    void setAbnormal(Integer id);

    /**
     * 根据状态来获取订单
     * @param state
     * @return
     */
    List<Order> getOrdersByState(String state);

    /**
     * 根据酒店id获取订单
     * @param hotelId
     * @return
     */
    List<Order> getOrderByHotelId(Integer hotelId);
}
