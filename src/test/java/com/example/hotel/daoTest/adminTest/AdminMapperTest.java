package com.example.hotel.daoTest.adminTest;

import com.example.hotel.data.admin.AdminMapper;
import com.example.hotel.po.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminMapperTest extends TestCase {

    @Autowired
    private AdminMapper adminMapper;


    @Test
    public void testAddManager() {
        User user =new User();
        user.setPassword("123456");
        user.setEmail("123@qq.com");
        int effectNum=adminMapper.addManager(user);
        assertEquals(1,effectNum);
    }

    @Test
    public void testUpdateManager() {
        User user=new User();
        user.setId(2);
        user.setPassword("123456");
        user.setEmail("1234@qq.com");
        int effectNum=adminMapper.updateManager(user);
        assertEquals(1,effectNum);
    }

    @Test
    public void testDeleteManager() {
        int effectNum=adminMapper.deleteManager(1);
        assertEquals(1,effectNum);
    }

    @Test
    public void testGetAllManagers() {
        List<User> users=adminMapper.getAllManagers();
        assertEquals(3,users.size());
    }


}