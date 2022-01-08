package com.yulaw.ccbapi.controller;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.common.BaseResponse;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.pojo.Distribution;
import com.yulaw.ccbapi.model.vo.TinyVideoVO;
import com.yulaw.ccbapi.model.vo.VideoVO;
import com.yulaw.ccbapi.service.VideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class VideoController {

    @Autowired
    VideoService videoService;


    @GetMapping("/video/pageList")
    @ResponseBody
    public BaseResponse listVideoForAdmin(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                          @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                          @RequestParam(required = false, defaultValue = "create_time") String orderBy,
                                          @RequestParam(required = false) Long channelId,
                                          @RequestParam(required = false) Long categoryId,
                                          @RequestParam(required = false) String keywords,
                                          @RequestParam(required = false) Long teacherId){
        try{
            PageInfo pageInfo = videoService.getPageList(pageNum , pageSize , orderBy, channelId, categoryId, keywords, teacherId);
            return ApiRestResponse.success(pageInfo);
        }catch (Exception e){
            return ApiRestResponse.error(CcbExceptionEnum.REQUEST_PARAM_ERROR);
        }
    }
    @GetMapping("/video/recommend")
    @ResponseBody
    public BaseResponse getRecommendVideoList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                              @RequestParam(required = false, defaultValue = "4") Integer pageSize,
                                              @RequestParam(required = false) Integer isRecommend){
        PageInfo recommendVideoList = videoService.getRecommendVideoList(pageNum, pageSize, isRecommend);
        return ApiRestResponse.success(recommendVideoList);
    }

    @GetMapping("/video")
    @ResponseBody
    public BaseResponse getVideoById(@RequestParam Long id,HttpServletRequest request){
        String serverName = request.getServerName();
        Distribution distribution = videoService.getDistribution(serverName);

        VideoVO video = videoService.getVideoById(id);
        //播放，只要调取视频详情接口就可以计入一次播放
        videoService.addStarById(id,3,distribution.getId());
        return ApiRestResponse.success(video);

    }

    @PostMapping("/video/add")
    @ResponseBody
    public BaseResponse addStar(@RequestParam Long id, @RequestParam Integer type,HttpServletRequest request){
        String serverName = request.getServerName();
        Distribution distribution = videoService.getDistribution(serverName);

        videoService.addStarById(id, type, distribution.getId());
        return new BaseResponse(200,"SUCCESS");

    }

    @GetMapping("/video/next")
    @ResponseBody
    public BaseResponse nextVideo(@RequestParam Long id,HttpServletRequest request){

        VideoVO video = videoService.getNextVideoById(id);
        //播放，只要调取视频详情接口就可以计入一次播放

        String serverName = request.getServerName();
        Distribution distribution = videoService.getDistribution(serverName);

        videoService.addStarById(video.getId(),3,distribution.getId());
        return ApiRestResponse.success(video);
    }
    @GetMapping("/video/search")
    @ResponseBody
    public BaseResponse searchVideo(@RequestParam String title){
        List<TinyVideoVO> tinyVideoVOS = videoService.searchVideo(title);
        return ApiRestResponse.success(tinyVideoVOS);
    }


}
