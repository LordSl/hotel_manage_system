package com.example.hotel.po;

public class CommentStatus {
    /**
     * 评论状态表主键
     */
    private Integer id;
    /**
     * 评论用户Id
     */
    private Integer userId;
    /**
     * 评论ID
     */
    private Integer commentId;

    /**
     * 对评回复的点赞状态，若为评论状态则replyId为-1，反之亦然
     */
    private Integer replyId;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 该条评论的状态，1代表喜欢，2代表不喜欢
     */
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

}
