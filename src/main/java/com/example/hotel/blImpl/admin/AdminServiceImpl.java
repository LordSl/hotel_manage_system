package com.example.hotel.blImpl.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.data.admin.AdminMapper;
import com.example.hotel.data.advertisement.AdvertisementMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class AdminServiceImpl implements AdminService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AdvertisementMapper advertisementMapper;
    @Override
    public ResponseVO addManager(UserForm userForm) {
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setUserType(userForm.getUserType());
        try {
            adminMapper.addManager(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }


    public ResponseVO updateManager(UserInfoVO userInfoVO) {
        User user = new User();
        user.setId(userInfoVO.getId());
        user.setCredit(userInfoVO.getCredit());
        user.setPhoneNumber(userInfoVO.getPhoneNumber());
        user.setUserName(userInfoVO.getUserName());
        user.setPassword(userInfoVO.getPassword());
        try {
            adminMapper.updateManager(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("更新失败");
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public void deleteManager(Integer userId){
        adminMapper.deleteManager(userId);
    }

    @Override
    public List<UserVO> getAllManagers() {
        List<User> users=adminMapper.getAllManagers();

        return users.stream().map(u->{
            UserVO userVO=new UserVO();
            BeanUtils.copyProperties(u,userVO);
            return userVO;
        }).collect(Collectors.toList());
    }

    @Override
    public void uploadAD(String imgUrl) {
        advertisementMapper.uploadAD(imgUrl);
    }
}
