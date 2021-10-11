package com.yulaw.ccbapi.controller;

import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.model.vo.VideoVO;
import com.yulaw.ccbapi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VideoController {

    @Autowired
    VideoService videoService;

    @GetMapping("/videoList")
    @ResponseBody
    public ApiRestResponse getVideoList() throws CcbException {
        List<VideoVO> resultList = videoService.getVideoList();
        return ApiRestResponse.success(resultList);

    }
}
