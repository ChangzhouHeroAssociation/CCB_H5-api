package com.yulaw.ccbapi.model.pojo;

import java.util.Date;

public class Video {
    private Long id;

    private String videoTitle;

    private String description;

    private Integer views;

    private String url;

    private Byte status;

    private Integer enjoyCount;

    private Integer shareCount;

    private Date createTime;

    private Date updateTime;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", videoTitle='" + videoTitle + '\'' +
                ", description='" + description + '\'' +
                ", views=" + views +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", enjoyCount=" + enjoyCount +
                ", shareCount=" + shareCount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}