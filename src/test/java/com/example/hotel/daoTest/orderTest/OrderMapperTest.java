package com.example.hotel.daoTest.orderTest;

import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTest extends TestCase {

    @Autowired private OrderMapper orderMapper;

    @Test
    public void testAddOrder() {
        Order order=new Order();
        int effectNum=orderMapper.addOrder(order);
        assertEquals(1,effectNum);
    }

    @Test
    public void testGetUserOrders() {
        List<Order> orderList=orderMapper.getUserOrders(1);
        assertEquals(0,orderList.size());
    }
}