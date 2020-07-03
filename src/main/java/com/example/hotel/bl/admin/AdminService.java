package com.example.hotel.bl.admin;

import com.example.hotel.vo.*;
import org.springframework.stereotype.Service;

@Service
/**
 * 用户
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
    ResponseVO deleteManager(Integer userId);
    /**
     * 获得所有酒店管理人员信息
     * @return
     */
    ResponseVO getAllManagers();

    /**
     * 上传广告图片
     * @param imgUrl
     */
    ResponseVO uploadAD(String imgUrl);
}
