package com.example.hotel.daoTest.hotelTest;

import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.po.Hotel;
import junit.framework.TestCase;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelMapperTest extends TestCase {

    @Autowired
    private HotelMapper hotelMapper;

    @Test
    public void testInsertHotel() {
        Hotel hotel=new Hotel();
        hotel.setHotelName("abc");
        int effectNum=hotelMapper.insertHotel(hotel);
        assertEquals(1,effectNum);
    }

    @Test
    public void testSelectAllHotel() {
        List<Hotel>  hotelList=hotelMapper.selectAllHotel();
        assertEquals(4,hotelList.size());
    }


}