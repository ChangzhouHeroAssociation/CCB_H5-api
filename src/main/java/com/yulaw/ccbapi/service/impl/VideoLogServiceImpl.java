package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.dao.VideoLogMapper;
import com.yulaw.ccbapi.model.dao.VideoMapper;
import com.yulaw.ccbapi.model.pojo.Video;
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
    VideoMapper videoMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Transactional
    public void task() {
        BoundHashOperations<String,String,Integer> hashView = redisTemplate.boundHashOps("video_view");
        BoundHashOperations<String,String,Integer> hashShare = redisTemplate.boundHashOps("video_share");

        Map<String,String> map = redisTemplate.opsForHash().entries("video_view");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            VideoLog videoLog = new VideoLog();
            String vidAndDistId = entry.getKey();
            String[] arr = vidAndDistId.split("-");
            videoLog.setCreateTime(new Date());
            videoLog.setViewCount(Integer.parseInt(entry.getValue()));
            videoLog.setDistributionId(Integer.parseInt(arr[1]));
            if(redisTemplate.opsForHash().hasKey("video_share",vidAndDistId)){
                videoLog.setShareCount(Integer.parseInt((String) redisTemplate.opsForHash().get("video_share",vidAndDistId)));
            }else {
                videoLog.setShareCount(0);
            }
            Video video = videoMapper.selectByPrimaryKey(Long.parseLong(arr[0]));
            if(video == null){
                redisTemplate.opsForHash().delete("video_view",vidAndDistId);
                throw new CcbException(CcbExceptionEnum.DATA_NOT_FOUND);
            }
            videoLog.setVideoName(video.getVideoTitle());
            videoLogMapper.insertSelective(videoLog);
        }
        redisTemplate.delete("video_view");
        redisTemplate.delete("video_share");
    }

    @Override
    @Transactional
    public void update() {

        Map<String,String> map = redisTemplate.opsForHash().entries("view_count");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            Video video = videoMapper.selectByPrimaryKey(Long.parseLong(entry.getKey()));
            if(video == null){
                redisTemplate.opsForHash().delete("view_count",entry.getKey());
                throw new CcbException(CcbExceptionEnum.DATA_NOT_FOUND);
            }
            video.setViews(video.getViews() + Integer.parseInt(entry.getValue()));
            if(redisTemplate.opsForHash().hasKey("share_count",video.getId().toString())){
                video.setShareCount(video.getShareCount() + Integer.parseInt((String) redisTemplate.opsForHash().get("share_count",video.getId().toString())));
            }
            if(redisTemplate.opsForHash().hasKey("enjoy_count",video.getId().toString())){
                video.setEnjoyCount(video.getEnjoyCount() + Integer.parseInt((String) redisTemplate.opsForHash().get("enjoy_count",video.getId().toString())));
            }
            videoMapper.updateByPrimaryKeySelective(video);
        }

        redisTemplate.delete("share_count");
        redisTemplate.delete("view_count");
        redisTemplate.delete("enjoy_count");
    }
}
