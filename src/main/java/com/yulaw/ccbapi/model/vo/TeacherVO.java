package com.yulaw.ccbapi.model.vo;

import com.yulaw.ccbapi.model.pojo.Video;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TeacherVO implements Serializable {
    private Long id;

    private String teacherName;

    private String job;

    private String description;

    private String video;

    private String cover;

    private String teacherPhoto;

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getTeacherPhoto() {
        return teacherPhoto;
    }

    public void setTeacherPhoto(String teacherPhoto) {
        this.teacherPhoto = teacherPhoto == null ? null : teacherPhoto.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
