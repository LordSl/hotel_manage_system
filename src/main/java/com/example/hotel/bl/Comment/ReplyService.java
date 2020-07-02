package com.example.hotel.bl.Comment;

import com.example.hotel.po.Reply;
import com.example.hotel.vo.ReplyVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

public interface ReplyService {

    /**
     * 插入一条新的评论
     * @param replyVO
     */
    ResponseVO insertReply(ReplyVO replyVO);

    /**
     * 根据评论id获取所有的子评论
     * @param commentId
     * @return
     */
    List<ReplyVO> selectReplyByCommentId(Integer commentId);

    /**
     * 增加点赞数
     * @param id
     */
    void updateReplyLike(Integer id,Integer num);

    /**
     * 返回评论@的消息
     * @param userId
     * @return
     */
    List<ReplyVO> selectReplyByUserId(Integer userId);


    /**
     * 根据id删除回复
     * @param id
     * @return
     */
    ResponseVO deleteReplyById(Integer id);
}
