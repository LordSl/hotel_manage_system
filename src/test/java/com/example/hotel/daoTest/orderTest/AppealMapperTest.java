package com.example.hotel.daoTest.orderTest;

import com.example.hotel.data.order.AppealMapper;
import com.example.hotel.po.Appeal;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppealMapperTest extends TestCase {

    @Autowired
    private AppealMapper appealMapper;
    @Test
    public void testGetAppealById() {

        Appeal appeal=appealMapper.getAppealById(0);
        assertEquals(null,appeal);
    }
}