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
    public BaseResponse getTeacherById(@RequestParam Long id, HttpServletRequest request,
                                       @RequestParam(required = false,defaultValue = "1") Integer distributionId){
        //String serverName = request.getServerName();
        //String referName = request.getHeader("Origin");
        //System.out.println(referName);
        //Distribution distribution = videoService.getDistribution(serverName);
        TeacherVO teacher = teacherService.getTeacherById(id,distributionId);
        teacherService.addTeacherView(distributionId,teacher.getTeacherName());
        return ApiRestResponse.success(teacher);
    }
}
