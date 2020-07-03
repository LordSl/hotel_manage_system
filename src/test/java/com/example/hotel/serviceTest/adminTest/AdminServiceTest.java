package com.example.hotel.serviceTest.adminTest;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.enums.UserType;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void addManager() {
        UserForm userForm=new UserForm();
        userForm.setEmail("12345@qq.com");
        userForm.setPassword("123456");
        userForm.setUserType(UserType.Client);
        ResponseVO effect=adminService.addManager(userForm);
        assertEquals(true,effect.getSuccess());
    }

    @Test
    public void getAllManagers() {
        List<UserVO> userVOList=(List<UserVO>)adminService.getAllManagers().getContent();
        assertEquals(5,userVOList.size());
    }


}