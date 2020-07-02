package com.example.hotel.data.order;

import com.example.hotel.po.Appeal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AppealMapper {

    /**
     * 申诉订单
     * @param appeal
     */
    void appealOrder(Appeal appeal);

    /**
     * 根据订单id获取申诉
     * @param orderId
     * @return
     */
    Appeal getAppealById(int orderId);

    /**
     * 获取订单的申诉理由
     * @param orderId
     * @return
     */
    Appeal getAppealMessageById(int orderId);

    /**
     * 设置申诉的处理状态
     * @param orderId
     * @param status
     */
    void setAppealStatus(int orderId, String status);
}
