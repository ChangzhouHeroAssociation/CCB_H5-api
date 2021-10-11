package com.yulaw.ccbapi.controller;

import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.model.vo.TeacherVO;
import com.yulaw.ccbapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/teacherList")
    @ResponseBody
    public ApiRestResponse getTeacherList() throws CcbException {
        List<TeacherVO> resultList = teacherService.getTeacherList();
        return ApiRestResponse.success(resultList);

    }
}
