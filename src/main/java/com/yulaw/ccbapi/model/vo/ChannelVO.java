package com.yulaw.ccbapi.model.vo;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.model.pojo.Video;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ChannelVO implements Serializable {
    private Long id;

    private String channelName;

    private String icon;

    private String content;

    private String image;

    private String video;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
