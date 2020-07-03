package com.example.hotel.ControllerTest.UserTest;

import com.example.hotel.controller.user.AccountController;
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
public class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @Test
    public void login() {
        UserForm userForm=new UserForm();
        userForm.setEmail("C1@qq.com");
        userForm.setPassword("123456");
        ResponseVO responseVO =accountController.login(userForm);
        assertEquals("C1@qq.com",((UserVO)responseVO.getContent()).getEmail());
    }
}