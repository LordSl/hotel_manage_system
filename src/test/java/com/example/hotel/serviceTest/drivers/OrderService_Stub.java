package com.example.hotel.serviceTest.drivers;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import org.mockito.internal.matchers.Or;

public class OrderService_Stub {

    public void drive(OrderService orderService){
        orderService.getAllOrders();
        orderService.addOrder(new OrderVO());
        orderService.deleteOrder(1);
        orderService.getHotelOrders(1);
        orderService.annulOrder(1);
        orderService.checkInOrder(1);
        orderService.getPrevOrder(1,1);
        orderService.autoUpdateOrderStatus();
        if(orderService.getAllOrders().size()!=0){
            System.out.println("Error");
        }
        if(orderService.addOrder(new OrderVO()).getSuccess()!=true){
            System.out.println("Error");
        }
        if(orderService.deleteOrder(1).getSuccess()!=true){
            System.out.println("Error");
        }
        if(orderService.getHotelOrders(1).size()!=0){
            System.out.println("Error");
        }
        if(orderService.autoUpdateOrderStatus().getSuccess()!=true){
            System.out.println("Error");
        }
    }
}
