package com.example.hotel.blImpl.comment;

import com.example.hotel.bl.Comment.CommentStatusService;
import com.example.hotel.data.comment.CommentMapper;
import com.example.hotel.data.comment.StatusMapper;
import com.example.hotel.po.CommentStatus;
import com.example.hotel.vo.CommentStatusVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentStatusServiceImpl implements CommentStatusService {

    @Autowired
    StatusMapper statusMapper;
    @Override
    public ResponseVO insertCommentStatus(CommentStatusVO commentStatusVO) {
        CommentStatus commentStatus=new CommentStatus();
        BeanUtils.copyProperties(commentStatusVO,commentStatus);
        try{
            statusMapper.insertCommentStatus(commentStatus);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("插入评论状态失败");
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO deleteReplyStatus(Integer userId, Integer replyId) {
        try {
            statusMapper.deleteReplyStatus(userId,replyId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("删除回复状态失败");
        }
        return ResponseVO.buildSuccess(true);
    }


    @Override
    public ResponseVO deleteCommentStatus(Integer userId, Integer commentId) {
        try {
            statusMapper.deleteCommentStatus(userId,commentId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("删除评论状态失败");
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO getCommentStatus() {
        try {
            return ResponseVO.buildSuccess(statusMapper.getCommentStatus());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("获取广告失败");
        }


    }
}
