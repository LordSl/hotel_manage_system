package com.example.hotel.bl.Comment;

import com.example.hotel.po.Comment;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

public interface CommentService {

    /**
     * 传入一条新的评论
     * @param commentVO
     */
    ResponseVO insertComment(CommentVO commentVO);

    /**
     * 查询酒店所有的评论
     * @param hotelId
     * @return
     */
    List<CommentVO> retrieveCommentByHotelId(Integer hotelId);

    /**
     * 查询用户所有的评论
     * @param userId
     * @return
     */
    List<CommentVO> retrieveCommentByUserId(Integer userId);

    /**
     * 用户为该条评论点赞
     * @param id
     */
    ResponseVO updateCommentLikes(Integer id,Integer num);

    /**
     * 删除评论
     * @param id
     */
    ResponseVO deleteComment(Integer id);


}
