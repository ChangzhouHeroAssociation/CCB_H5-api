package com.yulaw.ccbapi.model.vo;

import java.io.Serializable;

public class BannerForHomeVO implements Serializable {
    private Long id;

    private String bannerName;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
