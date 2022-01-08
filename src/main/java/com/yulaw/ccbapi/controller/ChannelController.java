package com.yulaw.ccbapi.controller;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.common.BaseResponse;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.model.pojo.Channel;
import com.yulaw.ccbapi.model.pojo.Distribution;
import com.yulaw.ccbapi.model.vo.ChannelForHomeVO;
import com.yulaw.ccbapi.model.vo.ChannelVO;
import com.yulaw.ccbapi.service.ChannelService;
import com.yulaw.ccbapi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @Autowired
    VideoService videoService;

    @GetMapping("/channel")
    @ResponseBody
    public BaseResponse getChannelById(@RequestParam Long id, HttpServletRequest request){
        String serverName = request.getServerName();
        Distribution distribution = videoService.getDistribution(serverName);
        ChannelVO channel = channelService.getChannelById(id,distribution.getId());
        return ApiRestResponse.success(channel);
    }

    @GetMapping("/channel/pageList")
    @ResponseBody
    public BaseResponse getPageList(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                    @RequestParam(required = false,defaultValue = "6") Integer pageSize){
        PageInfo channelList = channelService.getChannelList(pageNum, pageSize);
        return ApiRestResponse.success(channelList);
    }
}
