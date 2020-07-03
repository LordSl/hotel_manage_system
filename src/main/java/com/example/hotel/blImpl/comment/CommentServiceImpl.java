package com.example.hotel.blImpl.comment;

import com.example.hotel.bl.Comment.CommentService;
import com.example.hotel.bl.Comment.ReplyService;
import com.example.hotel.data.comment.CommentMapper;
import com.example.hotel.data.hotel.HPicMapper;
import com.example.hotel.po.Comment;
import com.example.hotel.po.HPic;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.ReplyVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    HPicMapper hPicMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    ReplyService replyService;

    @Override
    public ResponseVO insertComment(CommentVO commentVO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentVO, comment);
        try {
            commentMapper.insertComment(comment);
            int commentId = comment.getId();
            if (commentVO.getPicList() != null) {
                for (String pic : commentVO.getPicList()) {
                    HPic hPic = new HPic();
                    hPic.setHotelId(comment.getHotelId());
                    hPic.setImgUrl(pic);
                    hPic.setCustom(true);
                    hPic.setCommentId(commentId);
                    hPicMapper.insertHPic(hPic);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("插入失败");
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<CommentVO> retrieveCommentByHotelId(Integer hotelId) {
        List<Comment> commentList = commentMapper.selectCommentsByHotelId(hotelId);
        List<CommentVO> commentVOS = new LinkedList<>();
        for (Comment comment : commentList) {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment, commentVO);
            List<HPic> picList = hPicMapper.getHotelPicByCommentId(comment.getId());
            commentVO.setPicList(picList.stream().map(p -> p.getImgUrl()).collect(Collectors.toList()));
            List<ReplyVO> replies = replyService.selectReplyByCommentId(comment.getId());
            commentVO.setReplyList(replies);
            commentVOS.add(commentVO);
        }
        return commentVOS;
    }

    @Override
    public List<CommentVO> retrieveCommentByUserId(Integer userId) {
        List<Comment> commentList = commentMapper.selectCommentsByUserId(userId);
        List<CommentVO> commentVOS = new LinkedList<>();
        for (Comment comment : commentList) {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment, commentVO);
            List<HPic> picList = hPicMapper.getHotelPicByCommentId(comment.getId());
            commentVO.setPicList(picList.stream().map(p -> p.getImgUrl()).collect(Collectors.toList()));
            List<ReplyVO> replies = replyService.selectReplyByCommentId(comment.getId());
            commentVO.setReplyList(replies);
            commentVOS.add(commentVO);
        }
        return commentVOS;
    }


    @Override
    public ResponseVO updateCommentLikes(Integer id, Integer num) {
        try {
            commentMapper.updateCommentLikes(id, num);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (num > 0) {
                return ResponseVO.buildFailure("点赞失败");
            } else {
                return ResponseVO.buildFailure("点赞失败");
            }

        }
        return ResponseVO.buildSuccess(true);

    }


    @Override
    public ResponseVO deleteComment(Integer id) {
        try {
            commentMapper.deleteComment(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("删除失败");
        }
        return ResponseVO.buildSuccess(true);
    }
}
