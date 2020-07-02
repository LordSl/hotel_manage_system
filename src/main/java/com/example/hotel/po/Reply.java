package com.example.hotel.po;

import java.time.LocalDateTime;

public class Reply {

    /**
     * 回复主键
     */
    private Integer id;

    /**
     * 回复针对的评论Id
     */
    private Integer commentId;

    /**
     * 回复者的id
     */
    private Integer fromId;

    /**
     * 被回复者的id
     */
    private Integer toId;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 发起回复的用户名
     */
    private String fromName;

    /**
     * 发起回复的用户头像url
     */
    private String fromAvatar;

    /**
     * 接收回复的用户名
     */
    private String toName;

    /**
     * 接受回复的用户头像
     */
    private String toAvatar;

    /**
     * 回复是否已读的状态
     */
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToAvatar() {
        return toAvatar;
    }

    public void setToAvatar(String toAvatar) {
        this.toAvatar = toAvatar;
    }

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 收到的点赞数
     */
    private Integer favor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
