package com.example.hotel.daoTest.commentTest;

import com.example.hotel.data.comment.StatusMapper;
import com.example.hotel.po.CommentStatus;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatusMapperTest extends TestCase {

    @Autowired
    private StatusMapper statusMapper;

    @Test
    public void testInsertCommentStatus() {
        CommentStatus commentStatus=new CommentStatus();
        commentStatus.setUserId(1);
        commentStatus.setCommentId(1);
        int effectNum=statusMapper.insertCommentStatus(commentStatus);
        assertEquals(1,effectNum);
    }

    @Test
    public void testGetCommentStatus() {
        List<CommentStatus> status=statusMapper.getCommentStatus();
        assertEquals(1,status.size());
    }
}