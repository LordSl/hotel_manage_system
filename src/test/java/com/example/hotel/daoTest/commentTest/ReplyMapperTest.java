package com.example.hotel.daoTest.commentTest;

import com.example.hotel.data.comment.ReplyMapper;
import com.example.hotel.po.Reply;
import junit.framework.TestCase;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReplyMapperTest extends TestCase {

    @Autowired
    private ReplyMapper replyMapper;

    @Test
    public void testInsertReply() {
        Reply reply=new Reply();
        reply.setContent("abc");
        int effectNum=replyMapper.insertReply(reply);
        assertEquals(1,effectNum);
    }

    @Test
    public void testDeleteReply() {
        int effectNum=replyMapper.deleteReply(7);
        assertEquals(1,effectNum);
    }
}