package com.yulaw.ccbapi.model.vo;

import com.yulaw.ccbapi.model.pojo.Teacher;

import java.io.Serializable;
import java.util.List;

public class HotVideoVO implements Serializable {
    private Long id;

    private String videoTitle;

    private String description;

    private Integer views;

    private String url;

    private List<String> teacherNameList;

    private String channelIcon;

    private String channnelName;

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
        this.videoTitle = videoTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        this.url = url;
    }

    public List<String> getTeacherNameList() {
        return teacherNameList;
    }

    public void setTeacherNameList(List<String> teacherNameList) {
        this.teacherNameList = teacherNameList;
    }

    public String getChannelIcon() {
        return channelIcon;
    }

    public void setChannelIcon(String channelIcon) {
        this.channelIcon = channelIcon;
    }

    public String getChannnelName() {
        return channnelName;
    }

    public void setChannnelName(String channnelName) {
        this.channnelName = channnelName;
    }
}
