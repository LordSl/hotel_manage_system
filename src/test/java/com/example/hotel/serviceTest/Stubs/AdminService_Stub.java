package com.example.hotel.serviceTest.Stubs;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.enums.UserType;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserInfoVO;
import com.example.hotel.vo.UserVO;

import java.util.LinkedList;
import java.util.List;

public class AdminService_Stub implements AdminService {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getIsWebVIP() {
        return isWebVIP;
    }

    public void setIsWebVIP(int isWebVIP) {
        this.isWebVIP = isWebVIP;
    }

    private Integer id;
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;
    private double credit;
    private UserType userType;
    private String avatarUrl;
    private int isWebVIP;


    @Override
    public ResponseVO addManager(UserForm userForm) {
        System.out.println("insert User");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO updateManager(UserInfoVO userInfoVO) {
        System.out.println("update User");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO deleteManager(Integer userId) {
        System.out.println("delete User");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO getAllManagers() {
        List<UserVO> list=new LinkedList<UserVO>();
        System.out.println("getAllManager User");
        return ResponseVO.buildSuccess(list);
    }

    @Override
    public ResponseVO uploadAD(String imgUrl) {
        System.out.println("uploadAD");
        return ResponseVO.buildSuccess(true);
    }
}
