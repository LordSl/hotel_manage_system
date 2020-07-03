package com.example.hotel.serviceTest.commentTest;

import com.example.hotel.bl.Comment.ReplyService;
import com.example.hotel.po.Reply;
import com.example.hotel.vo.ReplyVO;
import com.example.hotel.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Test
    public void insertReply() {
        ReplyVO replyVO=new ReplyVO();
        ResponseVO responseVO=replyService.insertReply(replyVO);
        assertEquals(true,responseVO.getSuccess());
    }

    @Test
    public void deleteReplyById() {
        ResponseVO responseVO=replyService.deleteReplyById(7);
        assertEquals(true,responseVO.getSuccess());
    }
}