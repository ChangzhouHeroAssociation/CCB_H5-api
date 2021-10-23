package com.yulaw.ccbapi.controller;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.common.BaseResponse;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.pojo.Banner;
import com.yulaw.ccbapi.model.vo.AdvertisementVO;
import com.yulaw.ccbapi.model.vo.BannerVO;
import com.yulaw.ccbapi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BannerController {

    @Autowired
    BannerService bannerService;



    @GetMapping("/banner/pageList")
    @ResponseBody
    public BaseResponse listBannerForAdmin(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(required = false, defaultValue = "3") Integer pageSize,
                                           @RequestParam(required = false, defaultValue = "weight") String orderBy){
        try {
            PageInfo pageInfo = bannerService.listForAdmin(pageNum , pageSize , orderBy);
            return ApiRestResponse.success(pageInfo);
        }catch (BadSqlGrammarException e){
            return ApiRestResponse.error(CcbExceptionEnum.REQUEST_PARAM_NOT_FOUND);
        }
    }

}
