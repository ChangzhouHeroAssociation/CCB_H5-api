package com.yulaw.ccbapi.model.vo;

import com.yulaw.ccbapi.model.pojo.Teacher;

import java.io.Serializable;
import java.util.List;

public class VideoVO implements Serializable {
    private Long id;

    private String videoTitle;

    private String description;

    private Integer views;

    private String url;

    private String textPage;

    private Integer enjoyCount;

    private Integer shareCount;

    private Long channelId;

    private String channelName;

    private List<TeacherForVideoVO> teacherList;

    private AdvertisementVO advertisement;

    private List<QuestionVO> questionList;



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

    public List<TeacherForVideoVO> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherForVideoVO> teacherList) {
        this.teacherList = teacherList;
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

    public List<QuestionVO> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionVO> questionList) {
        this.questionList = questionList;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getTextPage() {
        return textPage;
    }

    public void setTextPage(String textPage) {
        this.textPage = textPage;
    }
}
