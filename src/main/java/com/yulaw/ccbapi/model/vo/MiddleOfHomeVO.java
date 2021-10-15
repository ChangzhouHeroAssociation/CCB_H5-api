package com.yulaw.ccbapi.model.vo;

import java.util.List;

public class MiddleOfHomeVO {

    private NewVideoVO newVideoVO;

    private List<TeacherForHomeVO> teacherForHomeVO;

    private List<HotVideoVO> hotVideoVO;

    public NewVideoVO getNewVideoVO() {
        return newVideoVO;
    }

    public void setNewVideoVO(NewVideoVO newVideoVO) {
        this.newVideoVO = newVideoVO;
    }

    public List<TeacherForHomeVO> getTeacherForHomeVO() {
        return teacherForHomeVO;
    }

    public void setTeacherForHomeVO(List<TeacherForHomeVO> teacherForHomeVO) {
        this.teacherForHomeVO = teacherForHomeVO;
    }

    public List<HotVideoVO> getHotVideoVO() {
        return hotVideoVO;
    }

    public void setHotVideoVO(List<HotVideoVO> hotVideoVO) {
        this.hotVideoVO = hotVideoVO;
    }
}
