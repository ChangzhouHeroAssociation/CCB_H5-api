package com.yulaw.ccbapi.model.vo;

import com.yulaw.ccbapi.model.pojo.Video;

import java.util.List;

public class TeacherForHomeVO {

    private Long id;

    private String teacherName;

    private String teacherPhoto;

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
        this.teacherName = teacherName;
    }

    public String getTeacherPhoto() {
        return teacherPhoto;
    }

    public void setTeacherPhoto(String teacherPhoto) {
        this.teacherPhoto = teacherPhoto;
    }
}
