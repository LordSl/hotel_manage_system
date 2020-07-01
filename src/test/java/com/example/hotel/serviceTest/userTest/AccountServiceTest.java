package com.example.hotel.serviceTest.userTest;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void login() {
        UserForm userForm=new UserForm();
        userForm.setEmail("C1@qq.com");
        userForm.setPassword("123456");
        UserVO userVO=accountService.login(userForm);
        assertEquals("C1@qq.com",userVO.getEmail());
    }

    @Test
    public void getUserInfo() {
        UserVO userVO=accountService.getUserInfo(2);
        assertEquals("C2@qq.com",userVO.getEmail());
    }
}