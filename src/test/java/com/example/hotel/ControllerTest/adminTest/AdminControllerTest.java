package com.example.hotel.ControllerTest.adminTest;

import com.example.hotel.controller.admin.AdminController;
import com.example.hotel.enums.UserType;
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
public class AdminControllerTest {
    @Autowired
    private AdminController adminController;

    @Test
    public void addManager() {
        UserForm userForm=new UserForm();
        userForm.setEmail("12345@qq.com");
        userForm.setPassword("123456");
        userForm.setUserType(UserType.Client);
        ResponseVO effect=adminController.addManager(userForm);
        assertEquals(true,effect.getSuccess());
    }
}