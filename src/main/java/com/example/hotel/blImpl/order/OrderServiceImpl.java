package com.example.hotel.blImpl.order;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.data.order.AppealMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.Appeal;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final static String RESERVE_ERROR = "预订失败";
    private final static String ROOMNUM_LACK = "预订房间数量剩余不足";
    private final static String DELETE_ERROR = "撤销失败";
    private final static String RESERVE_ERROR_CREDIT = "由于您的信用值过低，您无法完成本次预定";
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    HotelService hotelService;
    @Autowired
    AccountService accountService;
    @Autowired
    OrderService orderService;
    @Autowired
    AppealMapper appealMapper;
    @Autowired
    RoomService roomService;
    @Override
    public ResponseVO addOrder(OrderVO orderVO) {
        int reserveRoomNum = orderVO.getRoomNum();
        int curNum = roomService.getRoomNum(orderVO.getRoomId(),orderVO.getCheckInDate(),orderVO.getCheckOutDate());
        if(reserveRoomNum>curNum){
            return ResponseVO.buildFailure(ROOMNUM_LACK);
        }
        try {
            UserVO user = accountService.getUserInfo(orderVO.getUserId());
            //判断信用值能不能预定，小于0就不能预定
            double credit = user.getCredit();
            if(credit<0) return ResponseVO.buildFailure(RESERVE_ERROR_CREDIT);
            //
//            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = new Date(System.currentTimeMillis());
//            String curdate = sf.format(date);
//            orderVO.setCreateDate(curdate);
            orderVO.setOrderState("已预订");
            orderVO.setClientName(user.getUserName());
            orderVO.setPhoneNumber(user.getPhoneNumber());
            Order order = new Order();
            BeanUtils.copyProperties(orderVO,order);
            orderMapper.addOrder(order);
//            hotelService.updateRoomInfo(orderVO.getHotelId(),orderVO.getRoomType(), curNum-orderVO.getRoomNum());
            //房间总数就不变了，具体剩余数量待后期实现
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(RESERVE_ERROR);
        }
       return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO changeAppealStatus(AppealVO appealVO) {
        appealMapper.setAppealStatus(appealVO.getOrderId(), appealVO.getAppealStatus());

        return ResponseVO.buildSuccess(true);
    }

    //    订单申诉
    @Override
    public ResponseVO appealOrder(AppealVO appealVO) {
        try {
            appealVO.setAppealStatus("申诉待审核");
            Appeal appeal = new Appeal();
            BeanUtils.copyProperties(appealVO,appeal);
            appealMapper.appealOrder(appeal);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(RESERVE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }
//    订单申诉

    @Override
    public List<OrderVO> getAllOrders() {
        //为每一个订单获取对应的appeal
        List<Order> orders =orderMapper.getAllOrders();
        List<OrderVO> allOrderGot =orders.stream().map(o->{
            OrderVO orderVO=new OrderVO();
            BeanUtils.copyProperties(o,orderVO);
            return orderVO;
        }).collect(Collectors.toList());
        for(int i = 0 ; i<allOrderGot.size(); i++){
            int orderId = allOrderGot.get(i).getId();
            Appeal appeal = appealMapper.getAppealById(orderId);
            if(appeal!=null){
                AppealVO appealVO=new AppealVO();
                BeanUtils.copyProperties(appeal,appealVO);
                allOrderGot.get(i).setAppeal(appealVO);
            }else{
                allOrderGot.get(i).setAppeal(null);
            }

            RoomVO room=roomService.getOrderRoom(allOrderGot.get(i).getRoomId());
            allOrderGot.get(i).setRoom(room);
        }
        return allOrderGot;
    }

    @Override
    public List<OrderVO> getUserOrders(int userid) {
        List<Order> orders = orderMapper.getUserOrders(userid);
        List<OrderVO> userOrdersGot =orders.stream().map(o->{
            OrderVO orderVO=new OrderVO();
            BeanUtils.copyProperties(o,orderVO);
            return orderVO;
        }).collect(Collectors.toList());
        //为每一个订单获取对应的申诉
        for(int i = 0; i< userOrdersGot.size();i++){
            int orderId = userOrdersGot.get(i).getId();
            Appeal appeal = appealMapper.getAppealById(orderId);
            if(appeal!=null){
                AppealVO appealVO=new AppealVO();
                BeanUtils.copyProperties(appeal,appealVO);
                userOrdersGot.get(i).setAppeal(appealVO);
            }else{
                userOrdersGot.get(i).setAppeal(null);
            }
            RoomVO room=roomService.getOrderRoom(userOrdersGot.get(i).getRoomId());
            userOrdersGot.get(i).setRoom(room);
        }
        return userOrdersGot;
    }

    @Override
    public ResponseVO annulOrder(int orderid) {
        //取消订单逻辑的具体实现（注意可能有和别的业务类之间的交互）
        Order order=orderMapper.getOrderById(orderid);
//        int curNum = hotelService.getRoomCurNum(order.getHotelId(),order.getRoomType());
        try {
            orderMapper.annulOrder(orderid);
//            hotelService.updateRoomInfo(order.getHotelId(),order.getRoomType(),order.getRoomNum()+curNum);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(DELETE_ERROR);
        }
        //扣除信用值
        //现在是只要撤销订单就扣除信用值
        int userId = order.getUserId();
        UserVO user = accountService.getUserInfo(userId);
        accountService.updateUserCredit(userId, user.getCredit() - 1.0 / 5 * order.getPrice());
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO deleteOrder(int orderid) {
        orderMapper.deleteOrder(orderid);
        orderMapper.deleteAppeal(orderid);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<OrderVO> getHotelOrders(Integer hotelId) {
        List<Order> orders =orderMapper.getOrderByHotelId(hotelId);
        List<OrderVO> allOrderGot =orders.stream().map(o->{
            OrderVO orderVO=new OrderVO();
            BeanUtils.copyProperties(o,orderVO);
            return orderVO;
        }).collect(Collectors.toList());
        for(int i = 0; i< allOrderGot.size();i++){
            int orderId = allOrderGot.get(i).getId();
            Appeal appeal = appealMapper.getAppealById(orderId);
            if(appeal!=null){
                AppealVO appealVO=new AppealVO();
                BeanUtils.copyProperties(appeal,appealVO);
                allOrderGot.get(i).setAppeal(appealVO);
            }else{
                allOrderGot.get(i).setAppeal(null);
            }
            RoomVO room=roomService.getOrderRoom(allOrderGot.get(i).getRoomId());
            allOrderGot.get(i).setRoom(room);
        }
        return allOrderGot;
    }


    @Override
    public ResponseVO getAppealMessage(int orderid) {
        String message = appealMapper.getAppealMessageById(orderid).getAppealMessage();
        return ResponseVO.buildSuccess(message);
    }

    @Override
    public ResponseVO checkInOrder(int orderid) {
        orderMapper.checkInOrder(orderid);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO autoUpdateOrderStatus() {
        //获取所有”已预定“的订单，如果到了入住日期，把订单状态改为异常
        //异常应该是指到了约定好的入住日期，还没有被酒店管理员登记为”已入住“
        List<Order>notFinished = orderMapper.getOrdersByState("已预订");
        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = format.format(currentDate);
        for(Order order : notFinished){
            String orderTime = order.getCheckInDate();
            if(currentTime.compareTo(orderTime) > 0){
                orderMapper.setAbnormal(order.getId());
            }
        }
        return ResponseVO.buildSuccess(true);
    }


    @Override
    public ResponseVO getStateOfUP(Integer userId, Integer hotelId) {
        List<Order> orders = orderMapper.getUserOrders(userId);
        if(orders.stream().anyMatch(order -> order.getHotelId().equals(hotelId)&&order.getOrderState().equals("已入住"))){
            return ResponseVO.buildSuccess("曾住过");
        }else if(orders.stream().anyMatch(order -> order.getHotelId().equals(hotelId)&&order.getOrderState().equals("已预订"))){
            return ResponseVO.buildSuccess("已预订");
        }else if(orders.stream().anyMatch(order -> order.getHotelId().equals(hotelId)&&order.getOrderState().equals("已撤销"))){
            return ResponseVO.buildSuccess("曾撤销订单");
        }
        return ResponseVO.buildSuccess("");
    }

    @Override
    public ResponseVO getPrevOrder(Integer userId, Integer hotelId) {
        List<Order> orders = orderMapper.getUserOrders(userId);
        List<Order> matchOrders=orders.stream().filter(order -> order.getHotelId().equals(hotelId)&&order.getOrderState().equals("已预订")).collect(Collectors.toList());
        if(matchOrders.size()==0){
            return ResponseVO.buildSuccess(null);
        }else {
            Collections.sort(matchOrders, new Comparator<Order>() {
                @Override
                public int compare(Order o1, Order o2) {
                    return o1.getCheckInDate().compareTo(o2.getCheckInDate());
                }
            });
            OrderVO prevOrder=new OrderVO();
            BeanUtils.copyProperties(matchOrders.get(0),prevOrder);
            RoomVO roomVO=roomService.getOrderRoom(prevOrder.getRoomId());
            prevOrder.setRoom(roomVO);
            return ResponseVO.buildSuccess(prevOrder);
        }
    }


}
