package com.yulaw.ccbapi.model.vo;

import java.io.Serializable;

public class TinyVideoVO implements Serializable {

    private Long id;

    private String videoTitle;

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
}
