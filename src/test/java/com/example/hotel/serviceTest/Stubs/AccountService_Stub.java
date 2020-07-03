package com.example.hotel.serviceTest.Stubs;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.enums.UserType;
import com.example.hotel.vo.CreditChangeVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;

public class AccountService_Stub implements AccountService {

    private Integer id;
    private String email;
    private String password;
    private String userName;
    private String phoneNumber;

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

    private double credit;
    private UserType userType;
    private String avatarUrl;
    private int isWebVIP;
    @Override
    public ResponseVO registerAccount(UserVO userVO) {
        System.out.println("registerAccount");
        return ResponseVO.buildSuccess();
    }

    @Override
    public UserVO login(UserForm userForm) {
        System.out.println("login");
        return new UserVO();
    }

    @Override
    public UserVO getUserInfo(int id) {
        System.out.println("getUserInfo");
        return new UserVO();
    }

    @Override
    public ResponseVO updateUserInfo(int id, String password, String username, String phonenumber) {
        System.out.println("updateUserInfo");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO creditRecharge(int id, Double amount) {
        System.out.println("creditRecharge");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO updateUserCredit(int id, double credit) {
        System.out.println("updateUserCredit");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO updateUserAvatarUrl(int id, String userImg) {
        System.out.println("updateUserAvatarUrl");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO setUserWebVIP(int userId, int isWebVIP) {
        System.out.println("setUserWebVIP");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO getWebVIP() {
        System.out.println("getWebVIP");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO getCreditChangeListByUserId(int userId) {
        System.out.println("getCreditChangeListByUserId");
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO addCreditChangeRecord(CreditChangeVO creditChangeVO) {
        System.out.println("addCreditChangeRecord");
        return ResponseVO.buildSuccess();
    }
}
