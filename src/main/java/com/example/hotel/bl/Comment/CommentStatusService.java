package com.example.hotel.bl.Comment;


import com.example.hotel.vo.CommentStatusVO;
import com.example.hotel.vo.ResponseVO;

/**
 * 点赞状态的Service层
 */
public interface CommentStatusService {

    /**
     * 插入一条新的点赞状态
     * @param commentStatusVO
     * @return
     */
    ResponseVO insertCommentStatus(CommentStatusVO commentStatusVO);

    /**
     * 删除回复点赞状态
     * @param userId
     * @param replyId
     * @return
     */
    ResponseVO deleteReplyStatus(Integer userId,Integer replyId);

    /**
     * 删除评论点赞状态
     * @param userId
     * @param commentId
     * @return
     */
    ResponseVO deleteCommentStatus(Integer userId,Integer commentId);

    /**
     * 获取所有点赞状态
     * @return
     */
    ResponseVO getCommentStatus();
}
