package com.example.hotel.daoTest.userTest;

import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.User;
import junit.framework.TestCase;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest extends TestCase {

    @Autowired
    private AccountMapper accountMapper;
    @Test
    public void testCreateNewAccount() {
        User user=new User();
        user.setEmail("21@qq.com");
        user.setPassword("123456");
        int effectNum=accountMapper.createNewAccount(user);
        assertEquals(1,effectNum);
    }
    @Test
    public void testGetAccountById() {
        User user=accountMapper.getAccountById(2);
        assertEquals(0,user.getIsWebVIP());
    }
}