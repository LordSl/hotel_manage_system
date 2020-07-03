package com.example.hotel.daoTest.commentTest;

import com.example.hotel.data.comment.CommentMapper;
import com.example.hotel.po.Comment;
import junit.framework.TestCase;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentMapperTest extends TestCase {

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void testInsertComment() {
        Comment comment=new Comment();
        comment.setContent("abc");
        int effectNum=commentMapper.insertComment(comment);
        assertEquals(1,effectNum);
    }

    @Test
    public void testDeleteComment() {
        int effectNum=commentMapper.deleteComment(7);
        assertEquals(1,effectNum);
    }
}