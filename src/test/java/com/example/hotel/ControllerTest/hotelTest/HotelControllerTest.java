package com.example.hotel.ControllerTest.hotelTest;

import com.example.hotel.controller.admin.AdminController;
import com.example.hotel.controller.hotel.HotelController;
import com.example.hotel.enums.UserType;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelControllerTest {
    @Autowired
    private HotelController hotelController;
    @Autowired
    private AdminController adminController;
    @Test
    public void createHotel() {
        HotelVO hotelVO=new HotelVO();
        hotelVO.setName("ht");
        ResponseVO responseVO=hotelController.createHotel(hotelVO);
        assertEquals(false,responseVO.getSuccess());
        UserForm userForm=new UserForm();
        userForm.setUserType(UserType.HotelManager);
        userForm.setEmail("321@qq.com");
        userForm.setPassword("22321");
        adminController.addManager(userForm);
        hotelVO.setManagerEmail("321@qq.com");
        responseVO=hotelController.createHotel(hotelVO);
        assertEquals(true,responseVO.getSuccess());
    }
}