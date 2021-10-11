package com.yulaw.ccbapi.controller;

import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.model.vo.QuestionVO;
import com.yulaw.ccbapi.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/questionList")
    @ResponseBody
    public ApiRestResponse getQuestionList() throws CcbException {
        List<QuestionVO> resultList = questionService.getQuestionList();
        return ApiRestResponse.success(resultList);

    }
}
