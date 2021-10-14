package com.yulaw.ccbapi.controller;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.vo.VideoVO;
import com.yulaw.ccbapi.service.VideoService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VideoController {

    @Autowired
    VideoService videoService;

    @GetMapping("/video/list")
    @ResponseBody
    public ApiRestResponse getVideoList() throws CcbException {
        List<VideoVO> resultList = videoService.getVideoList();
        return ApiRestResponse.success(resultList);

    }

    @PostMapping("/video/pageList")
    @ResponseBody
    public ApiRestResponse listVideoForAdmin(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                             @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                             @RequestParam(required = false, defaultValue = "views") String orderBy){
        try{
            PageInfo pageInfo = videoService.listForAdmin(pageNum , pageSize , orderBy);
            return ApiRestResponse.success(pageInfo);
        }catch (BadSqlGrammarException e){
            return ApiRestResponse.error(CcbExceptionEnum.REQUEST_PARAM_NOT_FOUND);
        }catch (MyBatisSystemException e){
            return ApiRestResponse.error(CcbExceptionEnum.RESULT_NOT_ONLY);
        }

    }
}
