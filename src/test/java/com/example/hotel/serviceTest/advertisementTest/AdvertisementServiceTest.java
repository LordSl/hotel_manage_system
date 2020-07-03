package com.example.hotel.serviceTest.advertisementTest;

import com.example.hotel.bl.advertisement.AdvertisementService;
import com.example.hotel.po.Advertisement;
import com.example.hotel.vo.AdvertisementVO;
import com.example.hotel.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvertisementServiceTest {

    @Autowired
    private AdvertisementService advertisementService;

    @Test
    public void getAdvertisementList() {
        List<AdvertisementVO> advertisements=(List<AdvertisementVO>)advertisementService.getAdvertisementList().getContent();
        assertEquals(5,advertisements.size());
    }

    @Test
    public void addADImg() {
        ResponseVO effect=advertisementService.addADImg("abc.com");
        assertEquals(true,effect.getSuccess());
    }
}