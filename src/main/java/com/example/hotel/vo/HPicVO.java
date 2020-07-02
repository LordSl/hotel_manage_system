package com.example.hotel.vo;

public class HPicVO {
    /**
     * 酒店图片主键
     */
    private Integer id;

    /**
     * 酒店图片url
     */
    private String imgUrl;

    /**
     * 酒店id
     */
    private Integer hotelId;

    /**
     * 是否为用户上传
     */
    private boolean custom;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * 判断是否为
     */
    private Integer commentId;

    public boolean isCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
}
