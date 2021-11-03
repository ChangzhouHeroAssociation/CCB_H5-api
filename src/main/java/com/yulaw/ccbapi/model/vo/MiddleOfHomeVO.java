package com.yulaw.ccbapi.model.vo;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class MiddleOfHomeVO {

    private PageInfo newVideoList;

    private PageInfo teacherList;

    private PageInfo RecommendVideoList;

    public PageInfo getNewVideoList() {
        return newVideoList;
    }

    public void setNewVideoList(PageInfo newVideoList) {
        this.newVideoList = newVideoList;
    }

    public PageInfo getRecommendVideoList() {
        return RecommendVideoList;
    }

    public void setRecommendVideoList(PageInfo recommendVideoList) {
        RecommendVideoList = recommendVideoList;
    }

    public PageInfo getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(PageInfo teacherList) {
        this.teacherList = teacherList;
    }
}


