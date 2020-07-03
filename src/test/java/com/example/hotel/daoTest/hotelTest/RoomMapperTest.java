package com.example.hotel.daoTest.hotelTest;

import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomMapperTest extends TestCase {

    @Autowired
    private RoomMapper roomMapper;

    @Test
    public void testInsertRoom() {
        HotelRoom room=new HotelRoom();
        int effectNum=roomMapper.insertRoom(room);
        assertEquals(1,effectNum);
    }


}