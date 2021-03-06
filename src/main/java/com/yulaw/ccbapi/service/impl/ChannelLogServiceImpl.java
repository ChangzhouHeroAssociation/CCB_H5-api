package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.ChannelLogMapper;
import com.yulaw.ccbapi.model.dao.TeacherLogMapper;
import com.yulaw.ccbapi.model.pojo.ChannelLog;
import com.yulaw.ccbapi.model.pojo.TeacherLog;
import com.yulaw.ccbapi.service.ChannelLogService;
import com.yulaw.ccbapi.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class ChannelLogServiceImpl implements ChannelLogService {

    @Autowired
    ChannelLogMapper channelLogMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public void task() {
        /*BoundHashOperations<String,String,Integer> hashChannel = redisTemplate.boundHashOps("channel");
        Map<String,Integer> channelMap = hashChannel.entries();*/
        Map<String,String> map = redisTemplate.opsForHash().entries("channel");
        for (Map.Entry<String,String> entry : map.entrySet()) {
            ChannelLog channelLog = new ChannelLog();
            String nameAndDistId = entry.getKey();
            String[] arr = nameAndDistId.split("-");
            channelLog.setChannelName(arr[0]);
            channelLog.setDistributionId(Integer.parseInt(arr[1]));
            channelLog.setCount(Integer.parseInt(entry.getValue()));
            channelLog.setCreateTime(new Date());
            channelLogMapper.insertSelective(channelLog);
        }
        redisTemplate.delete("channel");
    }
}
