package com.yulaw.ccbapi.task;

import com.yulaw.ccbapi.service.IndexLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class IndexLogTask {

    @Autowired
    IndexLogService indexLogService;

    @Scheduled(cron = "0/10 * * * * ?")
    public void updateIndexLog(){
        indexLogService.task();
        System.out.println("更新首页统计！");
    }
}
