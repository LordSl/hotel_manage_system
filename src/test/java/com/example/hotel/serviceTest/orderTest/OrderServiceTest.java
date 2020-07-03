package com.example.hotel.serviceTest.orderTest;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void addOrder() {
        OrderVO orderVO=new OrderVO();
        orderVO.setRoomNum(1);
        orderVO.setCheckInDate("2020-07-15");
        orderVO.setCheckOutDate("2020-07-25");
        orderVO.setUserId(2);
        orderVO.setRoomId(2);
        ResponseVO responseVO=orderService.addOrder(orderVO);
        assertEquals(true,responseVO.getSuccess());
    }

    @Test
    public void deleteOrder() {
        ResponseVO responseVO=orderService.deleteOrder(14);
        assertEquals(true,responseVO.getSuccess());
    }
}