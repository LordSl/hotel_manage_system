package com.example.hotel.daoTest.advertisementTest;

import com.example.hotel.data.advertisement.AdvertisementMapper;
import com.example.hotel.po.Advertisement;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvertisementMapperTest extends TestCase {

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Test
    public void testGetAdvertisementList() {
        List<Advertisement> advertisementList=advertisementMapper.getAdvertisementList();
        assertEquals(4,advertisementList.size());
    }
}