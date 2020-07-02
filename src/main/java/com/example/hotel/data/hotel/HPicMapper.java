package com.example.hotel.data.hotel;

import com.example.hotel.po.HPic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HPicMapper {

    /**
     * 上传新的酒店图片
     * @param Hpic
     * @return
     */
    int insertHPic(HPic Hpic);

    /**
     * 删除酒店图片
     * @param id
     * @return
     */
    int deleteHPic(@Param("id") int id);

    /**
     * 根据酒店id获取相应的官方图片
     * @param hotelId
     * @return
     */
    List<HPic> getHotelPicById(@Param("hotelId") Integer hotelId);

    /**
     * 根据commentId获取相应的图片
     * @param commentId
     * @return
     */
    List<HPic> getHotelPicByCommentId(@Param("commentId") Integer commentId);
}
