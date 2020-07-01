package com.example.hotel.bl.admin;

import com.example.hotel.po.User;
import com.example.hotel.vo.*;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface AdminService {

    /**
     * 添加酒店管理人员账号
     * @param userForm
     * @return
     */
    ResponseVO addManager(UserForm userForm);

    /**
     * 更新用户信息
     * @param userInfoVO
     * @return
     */
    ResponseVO updateManager(UserInfoVO userInfoVO);

    /**
     * 删除用户
     * @param userId
     */
    void deleteManager(Integer userId);
    /**
     * 获得所有酒店管理人员信息
     * @return
     */
    List<UserVO> getAllManagers();

    /**
     * 上传广告图片
     * @param imgUrl
     */
    void uploadAD(String imgUrl);
}
