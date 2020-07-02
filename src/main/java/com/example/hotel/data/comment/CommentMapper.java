package com.example.hotel.data.comment;

import com.example.hotel.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    /**
     * 插入一条新的评论
     * @param comment
     * @return
     */
    int insertComment(Comment comment);

    /**
     * 根据酒店id获取酒店所有的评论
     * @param hotelId
     * @return
     */
    List<Comment> selectCommentsByHotelId(@Param("hotelId") Integer hotelId);

    /**
     * 更具用户id获取用户所有的评论
     * @param userId
     * @return
     */
    List<Comment> selectCommentsByUserId(@Param("userId") Integer userId);

    /**
     * 更新评论的点赞数
     * @param id
     * @param num
     * @return
     */
    int updateCommentLikes(@Param("id")Integer id,@Param("num")Integer num);


    /**
     * 根据id来删除评论
     * @param id
     * @return
     */
    int deleteComment(@Param("id")Integer id);
}
