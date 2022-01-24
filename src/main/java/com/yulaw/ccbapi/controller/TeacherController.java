package com.yulaw.ccbapi.controller;

import com.yulaw.ccbapi.common.ApiRestResponse;
import com.yulaw.ccbapi.common.BaseResponse;
import com.yulaw.ccbapi.model.pojo.Distribution;
import com.yulaw.ccbapi.model.vo.TeacherVO;
import com.yulaw.ccbapi.service.TeacherService;
import com.yulaw.ccbapi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    VideoService videoService;

    @GetMapping("/teacher")
    @ResponseBody
    public BaseResponse getTeacherById(@RequestParam Long id, HttpServletRequest request){
        String serverName = request.getServerName();
        Distribution distribution = videoService.getDistribution(serverName);
        TeacherVO teacher = teacherService.getTeacherById(id,distribution.getId());
        return ApiRestResponse.success(teacher);
    }
}
