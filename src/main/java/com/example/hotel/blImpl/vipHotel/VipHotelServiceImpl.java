package com.example.hotel.blImpl.vipHotel;

import com.example.hotel.bl.vipHotel.VipHotelService;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.data.vipHotel.VIPHotelMapper;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.po.VIPHotel;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class VipHotelServiceImpl implements VipHotelService {
    @Autowired
    VIPHotelMapper vipHotelMapper;
    @Autowired
    HotelMapper hotelMapper;
    @Autowired
    AccountMapper accountMapper;


    @Override
    public ResponseVO registerAsHotelVIP(int userId, int hotelId) {
        try {
            vipHotelMapper.registerAsHotelVIP(userId, hotelId);
            return ResponseVO.buildSuccess(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("注册酒店会员失败");
        }

    }

    @Override
    public boolean isVIPCouple(int userId, int hotelId) {
        if(vipHotelMapper.retriveByUidHid(userId, hotelId) == null)return false;
        return true;
    }

    @Override
    public List<HotelVO> getHotelListOfCurrentVIP(int userId) {
        List<VIPHotel>vipHotels = vipHotelMapper.retriveByUserId(userId);
        List<Hotel>matchedHotels = new ArrayList<>();
        for(VIPHotel vipHotel: vipHotels){
            matchedHotels.add(hotelMapper.selectById(vipHotel.getHotelId()));
        }
        List<HotelVO>got = new ArrayList<>();
        for(Hotel hotel : matchedHotels){
            HotelVO hotelVO = new HotelVO();
            hotelVO.setName(hotel.getHotelName());
            hotelVO.setAddress(hotel.getAddress());
            hotelVO.setBizRegion(hotel.getBizRegion());
            hotelVO.setHotelStar(hotel.getHotelStar());
            hotelVO.setId(hotel.getId());
            hotelVO.setHotelImg(hotel.getHotelImg());
            hotelVO.setDescription(hotel.getDescription());
            hotelVO.setRate(hotel.getRate());
            got.add(hotelVO);
        }

        return got;
    }


    @Override
    public List<UserVO> getVipListOfCurrentHotel(int hotelId) {
        List<VIPHotel> vipHotels = vipHotelMapper.retriveByHotelId(hotelId);
        List<User> users = new ArrayList<>();
        for(VIPHotel vipHotel: vipHotels){
            users.add(accountMapper.getAccountById(vipHotel.getUserId()));
        }
        List<UserVO> outcome = new ArrayList<>();
        for(User user:users){
            UserVO userVO = new UserVO();
            userVO.setAvatarUrl(user.getAvatarUrl());
            userVO.setCredit(user.getCredit());
            userVO.setEmail(user.getEmail());
            userVO.setId(user.getId());
            userVO.setUserName(user.getUserName());
            userVO.setPhoneNumber(user.getPhoneNumber());
            outcome.add(userVO);
        }
        return outcome;
    }

    @Override
    public ResponseVO deleteVIPHotel(int userId, int hotelId) {
        try {
            vipHotelMapper.deleteVIPHotel(userId,hotelId);
            return ResponseVO.buildSuccess(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("删除酒店会员失败");
        }

    }
}
