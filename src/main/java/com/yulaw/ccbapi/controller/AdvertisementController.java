package com.yulaw.ccbapi.controller;

import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.model.vo.AdvertisementVO;
import com.yulaw.ccbapi.model.vo.ChannelVO;
import com.yulaw.ccbapi.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdvertisementController {

    @Autowired
    AdvertisementService advertisementService;

    @GetMapping("/advertisementList")
    @ResponseBody
    public ApiRestResponse getAdvertisementList() throws CcbException {
        List<AdvertisementVO> advList = advertisementService.getAdvertisementList();
        return ApiRestResponse.success(advList);

    }
}
