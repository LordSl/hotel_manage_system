package com.example.hotel.data.comment;

import com.example.hotel.po.CommentStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StatusMapper {

    /**
     * 插入一条新的记录点赞的状态
     * @param commentStatus
     * @return
     */
    int insertCommentStatus(CommentStatus commentStatus);

    /**
     * 删除一条记录用户对评论的点赞
     * @param userId
     * @param commentId
     * @return
     */
    int deleteCommentStatus(@Param("userId") Integer userId,@Param("commentId") Integer commentId);

    /**
     * 删除一条记录用户对回复的点赞
     * @param userId
     * @param replyId
     * @return
     */
    int deleteReplyStatus(@Param("userId") Integer userId,@Param("replyId")Integer replyId);

    /**
     * 获得所有的点赞状态
     * @return
     */
    List<CommentStatus> getCommentStatus();
}
