package com.yulaw.ccbapi.model.vo;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class MiddleOfHomeVO {

    private NewVideoVO newVideo;

    private PageInfo teacherList;

    private List<HotVideoVO> hotVideo;

    public NewVideoVO getNewVideo() {
        return newVideo;
    }

    public void setNewVideo(NewVideoVO newVideo) {
        this.newVideo = newVideo;
    }

    public List<HotVideoVO> getHotVideo() {
        return hotVideo;
    }

    public void setHotVideo(List<HotVideoVO> hotVideo) {
        this.hotVideo = hotVideo;
    }

    public PageInfo getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(PageInfo teacherList) {
        this.teacherList = teacherList;
    }
}


