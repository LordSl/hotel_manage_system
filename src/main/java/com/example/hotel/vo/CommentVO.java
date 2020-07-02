package com.example.hotel.vo;

import java.time.LocalDateTime;
import java.util.List;

public class CommentVO {

    private Integer id;

    private Integer hotelId;

    private Integer userId;

    private String content;

    private Integer favor;

    private LocalDateTime createTime;

    /**
     * 评论所持有的所有pic的Url
     */
    private List<String> picList;

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }

    private Double score;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    private String userAvatar;

    public Double getScore(){
        return score;
    }

    public void setScore(Double score){
        this.score=score;
    }
    public List<ReplyVO> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyVO> replyList) {
        this.replyList = replyList;
    }

    /**
     * 评论所持有的回复列表
     */
    List<ReplyVO> replyList;

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
}
