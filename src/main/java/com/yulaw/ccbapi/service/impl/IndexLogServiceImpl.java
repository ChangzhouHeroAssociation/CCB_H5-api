package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.IndexLogMapper;
import com.yulaw.ccbapi.model.pojo.IndexLog;
import com.yulaw.ccbapi.service.IndexLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class IndexLogServiceImpl implements IndexLogService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    IndexLogMapper indexLogMapper;

    @Override
    public void addIndexViewCount(){
        redisTemplate.opsForValue().increment("indexLog");
    }

    @Override
    @Transactional
    public void task() {
        if(redisTemplate.hasKey("indexLog")){
            IndexLog indexLog = new IndexLog();
            indexLog.setCreateTime(new Date());
            String count = (String)redisTemplate.opsForValue().get("indexLog");
            indexLog.setViewCount(Integer.parseInt(count));
            indexLogMapper.insertSelective(indexLog);
            redisTemplate.delete("indexLog");
        }
    }
}
