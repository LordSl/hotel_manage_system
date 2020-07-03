package com.example.hotel.blImpl.comment;

import com.example.hotel.bl.Comment.ReplyService;
import com.example.hotel.data.comment.ReplyMapper;
import com.example.hotel.po.Reply;
import com.example.hotel.vo.ReplyVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    ReplyMapper replyMapper;
    @Override
    public ResponseVO insertReply(ReplyVO replyVO) {
        Reply reply=new Reply();
        BeanUtils.copyProperties(replyVO,reply);

        try {
            int effectNum=replyMapper.insertReply(reply);
            if(effectNum>0){
                return ResponseVO.buildSuccess("回复成功");
            }else{
                return ResponseVO.buildFailure("回复失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("回复失败");
        }

    }

    @Override
    public List<ReplyVO> selectReplyByCommentId(Integer commentId) {
        List<Reply> replies=replyMapper.selectReplyByCommentId(commentId);
        List<ReplyVO> replyVOS=new LinkedList<>();
        for(Reply reply:replies){
            ReplyVO replyVO=new ReplyVO();
            BeanUtils.copyProperties(reply,replyVO);
            replyVOS.add(replyVO);
        }
        return replyVOS;
    }

    @Override
    public ResponseVO updateReplyLike(Integer id,Integer num) {
        try {
            replyMapper.updateReplyLikes(id,num);
            return ResponseVO.buildSuccess(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("点赞失败");
        }

    }

    @Override
    public List<ReplyVO> selectReplyByUserId(Integer userId) {
        List<Reply> replies=replyMapper.selectReplyByUserId(userId);
        List<ReplyVO> replyVOS=new LinkedList<>();
        for(Reply reply:replies){
            ReplyVO replyVO=new ReplyVO();
            BeanUtils.copyProperties(reply,replyVO);
            replyVOS.add(replyVO);
        }
        return replyVOS;
    }

    @Override
    public ResponseVO deleteReplyById(Integer id) {

        try {
            int effectNum=replyMapper.deleteReply(id);
            if(effectNum>0){
                return ResponseVO.buildSuccess("删除成功");
            }else{
                return ResponseVO.buildFailure("删除失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("删除回复失败");
        }
    }
}
