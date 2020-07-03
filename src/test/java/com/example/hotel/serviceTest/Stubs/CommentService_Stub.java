package com.example.hotel.serviceTest.Stubs;

import com.example.hotel.bl.Comment.CommentService;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.ResponseVO;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CommentService_Stub implements CommentService {


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFavor() {
        return favor;
    }

    public void setFavor(Integer favor) {
        this.favor = favor;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    private Integer id;

    private Integer hotelId;

    private Integer userId;

    private String content;

    private Integer favor;

    private LocalDateTime createTime;
    @Override
    public ResponseVO insertComment(CommentVO commentVO) {
        System.out.println("insertComment");
        return ResponseVO.buildSuccess();
    }

    @Override
    public List<CommentVO> retrieveCommentByHotelId(Integer hotelId) {
        System.out.println("retrieveCommentByHotelId");
        return new LinkedList<>();
    }

    @Override
    public List<CommentVO> retrieveCommentByUserId(Integer userId) {
        System.out.println("retrieveCommentByHotelId");
        return new LinkedList<>();
    }

    @Override
    public ResponseVO updateCommentLikes(Integer id, Integer num) {
        System.out.println("updateCommentLikes");
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO deleteComment(Integer id) {
        System.out.println("deleteComment");
        return ResponseVO.buildSuccess();
    }
}
