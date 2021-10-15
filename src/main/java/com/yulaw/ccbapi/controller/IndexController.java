package com.yulaw.ccbapi.controller;

import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.model.pojo.HomePage;
import com.yulaw.ccbapi.model.pojo.Video;
import com.yulaw.ccbapi.model.vo.*;
import com.yulaw.ccbapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    HomePageService homePageService;

    @Autowired
    VideoService videoService;

    @Autowired
    BannerService bannerService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    ChannelService channelService;

    @GetMapping("/homePagePart1")
    @ResponseBody
    public ApiRestResponse getHomePage1(){
        HomePage homePage = homePageService.getHomePage();
        List<ChannelForHomeVO> channelList = channelService.getChannelList();
        HomeAndChannelVO homeAndChannelVO = new HomeAndChannelVO();
        homeAndChannelVO.setHomePage(homePage);
        homeAndChannelVO.setChannelForHomeVOList(channelList);
        return ApiRestResponse.success(homeAndChannelVO);

    }

    @GetMapping("/homePagePart2")
    @ResponseBody
    public ApiRestResponse getHomePagePart2(){

        MiddleOfHomeVO middleOfHomeVO = new MiddleOfHomeVO();
        middleOfHomeVO.setNewVideoVO(videoService.getNew());
        middleOfHomeVO.setTeacherForHomeVO(teacherService.getTeacherListForHome());
        middleOfHomeVO.setHotVideoVO(videoService.getHotVideoVO());

        return ApiRestResponse.success(middleOfHomeVO);

    }
    @GetMapping("/homePagePart3")
    @ResponseBody
    public ApiRestResponse getHomePagePart3(){

        List<BannerForHomeVO> bannerListForHome = bannerService.getBannerListForHome();
        return ApiRestResponse.success(bannerListForHome);

    }
}
