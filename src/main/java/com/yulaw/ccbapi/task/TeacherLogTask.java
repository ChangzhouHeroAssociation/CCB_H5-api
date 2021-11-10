package com.yulaw.ccbapi.task;

import com.yulaw.ccbapi.service.TeacherLogService;
import com.yulaw.ccbapi.service.VideoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TeacherLogTask {

    @Autowired
    TeacherLogService teacherLogService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void updateVideoData(){
        teacherLogService.task();
        System.out.println("更新讲师统计！");
    }
}
