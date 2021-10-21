package com.yulaw.ccbapi.model.vo;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.model.pojo.HomePage;

import java.util.List;

public class HomeAndChannelVO {

    private HomePage homePage;

    private PageInfo channelForHomeVOList;

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public PageInfo getChannelForHomeVOList() {
        return channelForHomeVOList;
    }

    public void setChannelForHomeVOList(PageInfo channelForHomeVOList) {
        this.channelForHomeVOList = channelForHomeVOList;
    }
}
