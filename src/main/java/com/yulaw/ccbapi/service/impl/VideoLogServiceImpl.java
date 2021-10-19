package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.VideoLogMapper;
import com.yulaw.ccbapi.model.pojo.VideoLog;
import com.yulaw.ccbapi.service.VideoLogService;
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
public class VideoLogServiceImpl implements VideoLogService {

    @Autowired
    VideoLogMapper videoLogMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public void task() {
        BoundHashOperations<String,String,Integer> hashView = redisTemplate.boundHashOps("video_view");
        BoundHashOperations<String,String,Integer> hashShare = redisTemplate.boundHashOps("video_share");
        Map<String,Integer> viewMap = hashView.entries();
        Map<String,Integer> shareMap = hashShare.entries();
        ArrayList<VideoLog> videoLogs = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : viewMap.entrySet()) {
            VideoLog videoLog = new VideoLog();
            videoLog.setCreateTime(new Date());
            videoLog.setVideoName(entry.getKey());
            videoLog.setViewCount(entry.getValue());
            videoLog.setShareCount(0);
            videoLogs.add(videoLog);
        }
        for (Map.Entry<String, Integer> entry : shareMap.entrySet()) {
            for (VideoLog videoLog : videoLogs) {
                if(entry.getKey().equals(videoLog.getVideoName())){
                    videoLog.setShareCount(entry.getValue());
                }
            }
        }

        for (VideoLog videoLog : videoLogs) {
            videoLogMapper.insertSelective(videoLog);

        }
        redisTemplate.delete("video_view");
        redisTemplate.delete("video_share");
    }
}
