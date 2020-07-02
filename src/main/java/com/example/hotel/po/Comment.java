package com.example.hotel.po;

import java.time.LocalDateTime;

public class Comment {

    /**
     * 评论Id
     */
    private Integer id;

    /**
     * 评论对应的酒店id
     */
    private Integer hotelId;

    /**
     * 发表评论的用户Id
     */
    private Integer userId;

    /**
     * 评论的时间
     */
    private LocalDateTime createTime;

    /**
     * 评论收到的点赞数
     */
    private Integer favor;

    /**
     * 参与评论的用户名
     */
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

    /**
     * 参与评论的用户头像
     */
    private String userAvatar;


    /**
     * 评论的内容
     */
    private String content;

    /**
     * 评论给酒店的星数
     */
    private Double score;

    public Double getScore(){
        return score;
    }

    public void setScore(Double score){
        this.score=score;
    }
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getFavor() {
        return favor;
    }

    public void setFavor(Integer favor) {
        this.favor = favor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
