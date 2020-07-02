package com.example.hotel.serviceTest.commentTest;

import com.example.hotel.bl.Comment.CommentService;
import com.example.hotel.po.Comment;
import com.example.hotel.po.CommentStatus;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void insertComment() {
        CommentVO commentVO=new CommentVO();
        commentVO.setContent("abc");
        ResponseVO effect=commentService.insertComment(commentVO);
        assertEquals(true,effect.getSuccess());
    }

    @Test
    public void deleteComment() {
        ResponseVO responseVO=commentService.deleteComment(7);
        assertEquals(true,responseVO.getSuccess());
    }
}