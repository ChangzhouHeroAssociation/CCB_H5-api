package com.yulaw.ccbapi.service.common;

import com.yulaw.ccbapi.model.dao.DistributionMapper;
import com.yulaw.ccbapi.model.pojo.Distribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class InitalizeResource implements CommandLineRunner {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    DistributionMapper distributionMapper;

    @Override
    public void run(String... args) throws Exception {

        for (Distribution distribution : distributionMapper.selectAll()) {
            redisTemplate.opsForHash().put("distribution", distribution.getUrl(), distribution.getId().toString());
        }
        //redisTemplate.expire("distribution",100, TimeUnit.SECONDS);
        System.out.println("distribution数据加载到redis内存成功");
    }
}
