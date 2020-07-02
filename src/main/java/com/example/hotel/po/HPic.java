package com.example.hotel.po;

public class HPic {

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

    private Integer commentId;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public boolean isCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    /**
     * 是否为用户上传
     */
    private boolean  custom;

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
