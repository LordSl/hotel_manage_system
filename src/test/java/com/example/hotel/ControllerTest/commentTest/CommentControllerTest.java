package com.example.hotel.ControllerTest.commentTest;

import com.example.hotel.controller.comment.CommentController;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.ResponseVO;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentControllerTest {

    @Autowired
    private CommentController commentController;

    @Test
    public void addComment() {
        CommentVO commentVO=new CommentVO();
        commentVO.setContent("abc");
        ResponseVO effect=commentController.addComment(commentVO);
        assertEquals(true,effect.getSuccess());
    }
}