package com.example.hotel.serviceTest.vipHotelTest;

import com.example.hotel.bl.vipHotel.VipHotelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VipHotelServiceTest {

    @Autowired
    private VipHotelService vipHotelService;

    @Test
    public void isVIPCouple() {
        boolean effect=vipHotelService.isVIPCouple(1,1);
        assertEquals(false,effect);
    }
}