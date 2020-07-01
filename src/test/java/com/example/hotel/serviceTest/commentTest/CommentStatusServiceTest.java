package com.example.hotel.serviceTest.commentTest;

import com.example.hotel.bl.Comment.CommentStatusService;
import com.example.hotel.po.Comment;
import com.example.hotel.po.CommentStatus;
import com.example.hotel.vo.CommentStatusVO;
import com.example.hotel.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentStatusServiceTest {

    @Autowired
    private CommentStatusService commentStatusService;

    @Test
    public void insertCommentStatus() {
        CommentStatusVO commentStatusVO=new CommentStatusVO();
        commentStatusVO.setCommentId(1);
        commentStatusVO.setReplyId(-1);
        ResponseVO responseVO=commentStatusService.insertCommentStatus(commentStatusVO);
        assertEquals(true,responseVO.getSuccess());
    }

    @Test
    public void getCommentStatus() {
        ResponseVO responseVO=commentStatusService.getCommentStatus();
        assertEquals(1,((List<CommentStatusVO>)responseVO.getContent()).size());
    }
}