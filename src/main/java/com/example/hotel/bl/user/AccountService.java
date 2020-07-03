package com.example.hotel.bl.user;

import com.example.hotel.po.CreditChange;
import com.example.hotel.po.User;
import com.example.hotel.vo.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author huwen
 * @date 2019/3/23
 */
public interface AccountService {

    /**
     * 注册账号
     *
     * @return
     */
    ResponseVO registerAccount(UserVO userVO);

    /**
     * 用户登录，登录成功会将用户信息保存再session中
     *
     * @return
     */
    UserVO login(UserForm userForm);

    /**
     * 获取用户个人信息
     * @param id
     * @return
     */
    UserVO getUserInfo(int id);

    /**
     * 更新用户个人信息
     * @param id
     * @param password
     * @param username
     * @param phonenumber
     * @return
     */
    ResponseVO updateUserInfo(int id, String password,String username,String phonenumber);

    /**
     * 信用充值
     * @param id
     * @param amount
     * @return
     */
    ResponseVO creditRecharge(int id, Double amount);

    /**
     * 更新用户信用
     * @param id
     * @param credit
     * @return
     */
    ResponseVO updateUserCredit(int id,double credit);

    /**
     * 更新用户的头像
     * @param id
     * @param userImg
     * @return
     */
    ResponseVO updateUserAvatarUrl(int id,String userImg);


    /**
     * 设置用户为网站vip
     * @param userId
     * @param isWebVIP
     * @return
     */
    ResponseVO setUserWebVIP(int userId, int isWebVIP);

    /**
     * 获取网站所有vip
     * @return
     */
    ResponseVO getWebVIP();


    /**
     * 获取用户信用变更记录
     * @return
     */
    ResponseVO getCreditChangeListByUserId(int userId);

    /**
     * 增加信用记录
     * @param creditChangeVO
     * @return
     */
    ResponseVO addCreditChangeRecord(CreditChangeVO creditChangeVO);
}
