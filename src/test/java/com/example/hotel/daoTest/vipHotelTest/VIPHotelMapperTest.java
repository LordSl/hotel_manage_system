package com.example.hotel.daoTest.vipHotelTest;

import com.example.hotel.data.vipHotel.VIPHotelMapper;
import com.example.hotel.po.VIPHotel;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VIPHotelMapperTest extends TestCase {

    @Autowired
    private VIPHotelMapper vipHotelMapper;
    @Test
    public void testRetriveByUidHid() {
        VIPHotel vipHotel=vipHotelMapper.retriveByUidHid(1,1);
        assertEquals(null,vipHotel);
    }
}