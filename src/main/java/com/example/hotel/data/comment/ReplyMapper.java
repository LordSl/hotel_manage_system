package com.example.hotel.data.comment;

import com.example.hotel.po.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReplyMapper {

    /**
     * 插入一条新的回复
     * @param reply
     * @return
     */
    int insertReply(Reply reply);

    /**
     * 根据评论id获取所有的回复
     * @param commentId
     * @return
     */
    List<Reply> selectReplyByCommentId(@Param("commentId")Integer commentId);

    /**
     * 更新回复的点赞数
     * @param id
     * @param num
     * @return
     */
    int updateReplyLikes(@Param("id")Integer id,@Param("num")Integer num);

    /**
     * 根据用户id获得发给用户的回复
     * @param userId
     * @return
     */
    List<Reply> selectReplyByUserId(@Param("userId")Integer userId);

    /**
     * 根据回复id删除用户
     * @param id
     * @return
     */
    int deleteReply(Integer id);
}
