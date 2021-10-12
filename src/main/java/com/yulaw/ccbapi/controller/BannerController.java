package com.yulaw.ccbapi.controller;

import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.model.pojo.Banner;
import com.yulaw.ccbapi.model.vo.AdvertisementVO;
import com.yulaw.ccbapi.model.vo.BannerVO;
import com.yulaw.ccbapi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BannerController {

    @Autowired
    BannerService bannerService;

    @GetMapping("/banner/list")
    @ResponseBody
    public ApiRestResponse getBannerList() throws CcbException {

        List<BannerVO> bannerList = bannerService.getBannerList();
        return ApiRestResponse.success(bannerList);
    }

}
