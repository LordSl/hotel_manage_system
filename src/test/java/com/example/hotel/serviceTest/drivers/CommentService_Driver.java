package com.example.hotel.serviceTest.drivers;

import com.example.hotel.bl.Comment.CommentService;
import com.example.hotel.bl.advertisement.AdvertisementService;
import com.example.hotel.vo.CommentVO;

public class CommentService_Driver {
    public void drive(CommentService commentService){
        commentService.deleteComment(1);
        commentService.insertComment(new CommentVO());
        commentService.retrieveCommentByHotelId(1);
        commentService.retrieveCommentByUserId(1);
        commentService.updateCommentLikes(1,1);
        if(commentService.deleteComment(1).getSuccess()!=true){
            System.out.println("Error");
        }
        if(commentService.insertComment(new CommentVO()).getSuccess()!=true){
            System.out.println("Error");
        }
        if(commentService.updateCommentLikes(1,1).getSuccess()!=true){
            System.out.println("Error");
        }
    }
}
