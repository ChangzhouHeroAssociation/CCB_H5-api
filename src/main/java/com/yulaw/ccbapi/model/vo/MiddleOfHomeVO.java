package com.yulaw.ccbapi.model.vo;

import java.util.List;

public class MiddleOfHomeVO {

    private NewVideoVO newVideo;

    private List<TeacherForHomeVO> teacherForHome;

    private List<HotVideoVO> hotVideo;

    public NewVideoVO getNewVideo() {
        return newVideo;
    }

    public void setNewVideo(NewVideoVO newVideo) {
        this.newVideo = newVideo;
    }

    public List<TeacherForHomeVO> getTeacherForHome() {
        return teacherForHome;
    }

    public void setTeacherForHome(List<TeacherForHomeVO> teacherForHome) {
        this.teacherForHome = teacherForHome;
    }

    public List<HotVideoVO> getHotVideo() {
        return hotVideo;
    }

    public void setHotVideo(List<HotVideoVO> hotVideo) {
        this.hotVideo = hotVideo;
    }
}
