package com.example.hotel.data.user;

import com.example.hotel.po.CreditChange;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CreditChangeMapper {
    /**
     *  根据用户id 返回信用变更记录列表
     * @param userId 用户id
     * @return 信用变更记录列表
     */
    List<CreditChange> getCreditChangeByUserId(int userId);

    /**
     * 创建一个信用值变更记录
     * @param creditChange
     */
    void addCreditChangeRecord(CreditChange creditChange);
}
