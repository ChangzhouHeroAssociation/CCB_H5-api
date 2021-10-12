package com.yulaw.ccbapi.controller;

import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.request.AddAnswerReq;
import com.yulaw.ccbapi.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("/answer/submit")
    @ResponseBody
    public ApiRestResponse submitAnswer(@RequestBody AddAnswerReq addAnswerReq){
        if(addAnswerReq.getQuestionId() == null){
            return ApiRestResponse.error(CcbExceptionEnum.NEED_PARM);
        }
        answerService.add(addAnswerReq);
        return ApiRestResponse.success();
    }
}
