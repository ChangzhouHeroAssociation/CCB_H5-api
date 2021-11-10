package com.yulaw.ccbapi.task;

import com.yulaw.ccbapi.service.ChannelLogService;
import com.yulaw.ccbapi.service.TeacherLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ChannelLogTask {

    @Autowired
    ChannelLogService channelLogService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void updateVideoData(){
        channelLogService.task();
        System.out.println("更新频道统计！");
    }
}
