package com.yulaw.ccbapi.controller;

import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.model.pojo.Banner;
import com.yulaw.ccbapi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BannerController {

    @Autowired
    BannerService bannerService;

    @GetMapping("/test")
    @ResponseBody
    public Banner showBanner(){
        return bannerService.getBanner();
    }

}
