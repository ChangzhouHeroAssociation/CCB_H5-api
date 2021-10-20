package com.yulaw.ccbapi.task;


import com.yulaw.ccbapi.service.VideoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class VideoLogTask {

    @Autowired
    VideoLogService videoLogService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateVideoData(){
        videoLogService.task();
        System.out.println("更新视频统计！");
    }
}