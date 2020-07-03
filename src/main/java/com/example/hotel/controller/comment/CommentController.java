package com.example.hotel.controller.comment;


import com.example.hotel.bl.Comment.CommentService;
import com.example.hotel.bl.Comment.CommentStatusService;
import com.example.hotel.bl.Comment.ReplyService;
import com.example.hotel.po.Comment;
import com.example.hotel.po.CommentStatus;
import com.example.hotel.po.Reply;
import com.example.hotel.vo.CommentStatusVO;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.ReplyVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    ReplyService replyService;
    @Autowired
    CommentStatusService commentStatusService;
    @PostMapping("/addComment")
    public ResponseVO addComment(@RequestBody CommentVO commentVO){
        commentVO.setCreateTime(commentVO.getCreateTime().plusHours(8));
        return commentService.insertComment(commentVO);
    }

    @GetMapping("/deleteReply")
    public ResponseVO deleteReply(@RequestParam Integer id){
        return replyService.deleteReplyById(id);
    }

    @PostMapping("/addReply")
    public ResponseVO addReply(@RequestBody ReplyVO replyVO){
        replyVO.setCreateTime(replyVO.getCreateTime().plusHours(8));
        return replyService.insertReply(replyVO);
    }

    @GetMapping("/replyToUser")
    public ResponseVO retrieveReplyUser(@RequestParam Integer userId){
        return ResponseVO.buildSuccess(replyService.selectReplyByUserId(userId));
    }
    @GetMapping("/hotel/{id}")
    public ResponseVO retrieveHotelComment(@PathVariable Integer id){
        return ResponseVO.buildSuccess(commentService.retrieveCommentByHotelId(id));
    }

    @GetMapping("/reply/like")
    public ResponseVO increaseReplyLike(@RequestParam Integer id,@RequestParam Integer num){

        return replyService.updateReplyLike(id,num);
    }

    @GetMapping("/user/{id}")
    public ResponseVO retrieveUserComment(@PathVariable Integer id){
        return ResponseVO.buildSuccess(commentService.retrieveCommentByUserId(id));
    }

    @GetMapping("/like")
    public ResponseVO increaseCommentLike(@RequestParam Integer id,@RequestParam Integer num){
        return commentService.updateCommentLikes(id,num);
    }

    @GetMapping("/delete/{id}")
    public ResponseVO deleteComment(@PathVariable Integer id){
        return commentService.deleteComment(id);
    }

    @PostMapping("/addStatus")
    public ResponseVO addCommentStatus(@RequestBody CommentStatusVO commentStatusVO){
        return commentStatusService.insertCommentStatus(commentStatusVO);
    }

    @GetMapping("/deleteCommentStatus")
    public ResponseVO deleteCommentStatus(
            @RequestParam Integer userId,
            @RequestParam Integer commentId
    ){
        return commentStatusService.deleteCommentStatus(userId,commentId);
    }

    @GetMapping("/deleteReplyStatus")
    public ResponseVO deleteReplyStatus(
            @RequestParam Integer userId,
            @RequestParam Integer replyId
    ){
        return commentStatusService.deleteReplyStatus(userId,replyId);
    }

    @GetMapping("/getCommentStatus")
    public ResponseVO getCommentStatus(
    ){
        return commentStatusService.getCommentStatus();
    }
}
