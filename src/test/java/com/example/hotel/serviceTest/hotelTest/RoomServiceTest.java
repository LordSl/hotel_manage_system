package com.example.hotel.serviceTest.hotelTest;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.vo.RoomVO;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Test
    public void getOrderRoom() {
        RoomVO roomVO=roomService.getOrderRoom(2);
        assertEquals(199,roomVO.getPrice().intValue());
    }


}