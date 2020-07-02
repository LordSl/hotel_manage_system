package com.example.hotel.po;


import com.example.hotel.enums.UserType;

public class User {

    /**
     * 用户的主键
     */
    private Integer id;

    /**
     * 用户的email
     */
    private String email;

    /**
     * 用户的密码，用于登录
     */
    private String password;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户的电话号码
     */
    private String phoneNumber;

    /**
     * 用户的信用值
     */
    private double credit;

    /**
     * 用户的类型，分为客户、酒店管理员，网站管理员
     */
    private UserType userType;

    /**
     * 用户的头像url
     */
    private String avatarUrl;

    /**
     * 用户是否为网站vip，0表示不是， 1表示是
     */
    private int isWebVIP;

    public int getIsWebVIP(){return isWebVIP;}
    public void setIsWebVIP(int vip){isWebVIP = vip;}

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
