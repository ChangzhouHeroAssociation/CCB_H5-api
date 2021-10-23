package com.yulaw.ccbapi.controller;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.common.BaseResponse;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.model.pojo.Channel;
import com.yulaw.ccbapi.model.vo.ChannelForHomeVO;
import com.yulaw.ccbapi.model.vo.ChannelVO;
import com.yulaw.ccbapi.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @GetMapping("/channel/list")
    @ResponseBody
    public BaseResponse getChannelList(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                          @RequestParam(required = false,defaultValue = "8") Integer pageSize){
        PageInfo list = channelService.getChannelList(pageNum,pageSize);
        return ApiRestResponse.success(list);

    }

    @GetMapping("/channel")
    @ResponseBody
    public BaseResponse getChannelById(@RequestParam(required = false,defaultValue = "3") Long id,
                                       @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                       @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        ChannelVO channel = channelService.getChannelById(id,pageNum,pageSize);
        return ApiRestResponse.success(channel);

    }

}
