package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.TeacherLogMapper;
import com.yulaw.ccbapi.model.dao.VideoLogMapper;
import com.yulaw.ccbapi.model.pojo.TeacherLog;
import com.yulaw.ccbapi.model.pojo.VideoLog;
import com.yulaw.ccbapi.service.TeacherLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class TeacherLogServiceImpl implements TeacherLogService {

    @Autowired
    TeacherLogMapper teacherLogMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public void task() {
        BoundHashOperations<String,String,Integer> hashTeacher = redisTemplate.boundHashOps("teacher");
        Map<String,Integer> teacherMap = hashTeacher.entries();
        for (Map.Entry<String, Integer> entry : teacherMap.entrySet()) {
            TeacherLog teacherLog = new TeacherLog();
            teacherLog.setTeacherName(entry.getKey());
            teacherLog.setCount(entry.getValue());
            teacherLog.setCreateTime(new Date());
            teacherLogMapper.insertSelective(teacherLog);
        }
        redisTemplate.delete("teacher");
    }

}
