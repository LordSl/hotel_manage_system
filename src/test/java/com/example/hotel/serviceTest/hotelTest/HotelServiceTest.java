package com.example.hotel.serviceTest.hotelTest;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.Hotel;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelServiceTest {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private AdminService adminService;

    @Test
    public void addHotel() {
        HotelVO hotelVO=new HotelVO();
        hotelVO.setName("ht");
        ResponseVO responseVO=hotelService.addHotel(hotelVO);
        assertEquals(false,responseVO.getSuccess());
        UserForm userForm=new UserForm();
        userForm.setUserType(UserType.HotelManager);
        userForm.setEmail("321@qq.com");
        userForm.setPassword("22321");
        adminService.addManager(userForm);
        hotelVO.setManagerEmail("321@qq.com");
        responseVO=hotelService.addHotel(hotelVO);
        assertEquals(true,responseVO.getSuccess());
    }

}