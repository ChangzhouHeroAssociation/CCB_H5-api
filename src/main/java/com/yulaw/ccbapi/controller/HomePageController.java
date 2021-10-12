package com.yulaw.ccbapi.controller;

import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.model.pojo.HomePage;
import com.yulaw.ccbapi.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomePageController {

    @Autowired
    HomePageService homePageService;
    
    @GetMapping("/homePage")
    @ResponseBody
    public ApiRestResponse getHomePage() throws CcbException {
        HomePage homePage = homePageService.getHomePage();
        return ApiRestResponse.success(homePage);

    }
}
