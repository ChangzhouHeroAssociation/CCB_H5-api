package com.yulaw.ccbapi.model.vo;

import com.yulaw.ccbapi.model.pojo.HomePage;

import java.util.List;

public class HomeAndChannelVO {

    private HomePage homePage;

    private List<ChannelForHomeVO> channelForHomeVOList;

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public List<ChannelForHomeVO> getChannelForHomeVOList() {
        return channelForHomeVOList;
    }

    public void setChannelForHomeVOList(List<ChannelForHomeVO> channelForHomeVOList) {
        this.channelForHomeVOList = channelForHomeVOList;
    }
}
