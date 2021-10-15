package com.yulaw.ccbapi.model.vo;

import com.yulaw.ccbapi.model.pojo.Teacher;

import java.io.Serializable;

public class VideoVO implements Serializable {
    private Long id;

    private String videoTitle;

    private String description;

    private Integer views;

    private String url;

    private Integer enjoyCount;

    private Integer shareCount;

    private Long channelId;

    private TeacherForVideoVO teacher;

    private AdvertisementVO advertisement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle == null ? null : videoTitle.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getEnjoyCount() {
        return enjoyCount;
    }

    public void setEnjoyCount(Integer enjoyCount) {
        this.enjoyCount = enjoyCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public TeacherForVideoVO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherForVideoVO teacher) {
        this.teacher = teacher;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public AdvertisementVO getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(AdvertisementVO advertisement) {
        this.advertisement = advertisement;
    }
}
