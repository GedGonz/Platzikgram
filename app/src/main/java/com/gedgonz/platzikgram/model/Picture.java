package com.gedgonz.platzikgram.model;

public class Picture {

    private String picture;
    private String userName;
    private String time;
    private String like_numer;

    public Picture(String picture, String userName, String time, String like_numer) {
        this.picture = picture;
        this.userName = userName;
        this.time = time;
        this.like_numer = like_numer;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLike_numer() {
        return like_numer;
    }

    public void setLike_numer(String like_numer) {
        this.like_numer = like_numer;
    }
}
