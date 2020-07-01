package com.example.hotel.data.admin;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface AdminMapper {

    /**
     * 添加用户
     * @param user
     * @return
     */
    int addManager(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateManager(User user);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    int deleteManager(Integer userId);

    /**
     * 获得所有的用户
     * @return
     */
    List<User> getAllManagers();

}
