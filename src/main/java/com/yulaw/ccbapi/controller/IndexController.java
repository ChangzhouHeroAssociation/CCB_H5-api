package com.yulaw.ccbapi.controller;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.common.BaseResponse;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.model.pojo.HomePage;
import com.yulaw.ccbapi.model.pojo.Video;
import com.yulaw.ccbapi.model.vo.*;
import com.yulaw.ccbapi.service.*;
import com.yulaw.ccbapi.util.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
    public BaseResponse getHomePage1(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false,defaultValue = "8") Integer pageSize){
        HomePage homePage = homePageService.getHomePage();
        PageInfo channelList = channelService.getChannelList(pageNum,pageSize);
        HomeAndChannelVO homeAndChannelVO = new HomeAndChannelVO();
        homeAndChannelVO.setHomePage(homePage);
        homeAndChannelVO.setChannelForHomeVOList(channelList);
        return ApiRestResponse.success(homeAndChannelVO);

    }

    @GetMapping("/homePagePart2")
    @ResponseBody
    public BaseResponse getHomePagePart2(@RequestParam(required = false, defaultValue = "1") Integer npageNum,
                                         @RequestParam(required = false, defaultValue = "4") Integer npageSize,
                                         @RequestParam(required = false, defaultValue = "1") Integer tpageNum,
                                         @RequestParam(required = false, defaultValue = "10") Integer tpageSize,
                                         @RequestParam(required = false, defaultValue = "1") Integer rpageNum,
                                         @RequestParam(required = false, defaultValue = "4") Integer rpageSize
                                         ){

        MiddleOfHomeVO middleOfHome = new MiddleOfHomeVO();
        middleOfHome.setNewVideoList(videoService.getNewVideoList(npageNum,npageSize));
        middleOfHome.setTeacherList(teacherService.getTeacherListForHome(tpageNum, tpageSize));
        middleOfHome.setRecommendVideoList(videoService.getRecommendVideoList(rpageNum, rpageSize,1));
        return ApiRestResponse.success(middleOfHome);

    }
    @GetMapping("/homePagePart3")
    @ResponseBody
    public BaseResponse getHomePagePart3(){

        List<BannerForHomeVO> bannerListForHome = bannerService.getBannerListForHome();
        return ApiRestResponse.success(bannerListForHome);

    }

    @GetMapping("/share")
    @ResponseBody
    public BaseResponse share(@RequestParam(required = false) String url){
        Map<String, String> token = SignUtil.getResult(url);
        return ApiRestResponse.success(token);
    }
}
