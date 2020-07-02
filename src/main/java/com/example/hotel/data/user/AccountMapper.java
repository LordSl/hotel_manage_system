package com.example.hotel.data.user;

import com.example.hotel.po.CreditChange;
import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface AccountMapper {

    /**
     * 创建一个新的账号
     *
     * @return
     */
     int createNewAccount(User user);

    /**
     * 根据用户名查找账号
     * @param email
     * @return
     */
     User getAccountByName(@Param("email") String email);

    /**
     * 根据id来获取用户信息
     * @param id
     * @return
     */
     User getAccountById(@Param("id") int id);

    /**
     * 更新用户头像
     * @param id
     * @param avatarUrl
     * @return
     */
     int updateUserImg(@Param("id") int id, @Param("avatarUrl") String avatarUrl);

    /**
     * 更新用户信息
     * @param id
     * @param password
     * @param username
     * @param phonenumber
     * @return
     */
    int updateAccount(@Param("id") int id, @Param("password") String password,@Param("userName") String username, @Param("phoneNumber") String phonenumber);
    /**
     * 信用充值
     * @param id
     * @param amount
     * @return
     */
     int creditRecharge(@Param("id") int id,@Param("amount") double amount);

    /**
     * 更新信用
     * @param id
     * @param credit 新的信用
     * @return
     */
    void updateAccountCredit(@Param("id") int id, @Param("credit")double credit);

    /**
     * 设置用户的
     * @param userId
     * @param isWebVIP
     */
    void setUserWebVIP(int userId, int isWebVIP);

    /**
     * 获取所有的网站vip
     * @return
     */
    List<User> getWebVIP();


}
