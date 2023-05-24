package com.Game.ccmusa.android;

public class YandNModel {
    // string course_name for storing course_name
    // and imgid for storing image id.
    private String course_name;
    private int imgid;
    private int lock;
    private int errid;

    public YandNModel(String course_name, int imgid, int lock, int errid) {
        this.course_name = course_name;
        this.imgid = imgid;
        this.lock = lock;
        this.errid = errid;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getImgid() {
        return imgid;
    }
    public int getLock() {
        return lock;
    }
    public int getErrid() {
        return errid;
    }
    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
    public void setLock(int lock) {
        this.lock = lock;
    }
}
