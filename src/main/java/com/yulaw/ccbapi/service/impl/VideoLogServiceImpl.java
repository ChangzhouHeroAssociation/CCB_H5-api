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
        BoundHashOperations<String,Long,Integer> hashView = redisTemplate.boundHashOps("video_view");
        BoundHashOperations<String,Long,Integer> hashShare = redisTemplate.boundHashOps("video_share");

        Map<Long,Integer> viewMap = hashView.entries();

        for (Map.Entry<Long, Integer> entry : viewMap.entrySet()) {
            VideoLog videoLog = new VideoLog();
            videoLog.setCreateTime(new Date());
            videoLog.setViewCount(entry.getValue());
            if(hashShare.hasKey(entry.getKey())){
                videoLog.setShareCount(hashShare.get(entry.getKey()));
            }else {
                videoLog.setShareCount(0);
            }
            Video video = videoMapper.selectByPrimaryKey(entry.getKey());
            if(video == null){
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
        BoundHashOperations<String,Long,Integer> share = redisTemplate.boundHashOps("share_count");
        BoundHashOperations<String,Long,Integer> view = redisTemplate.boundHashOps("view_count");
        BoundHashOperations<String,Long,Integer> enjoy = redisTemplate.boundHashOps("enjoy_count");

        Map<Long,Integer> viewMap = view.entries();

        for (Map.Entry<Long, Integer> entry : viewMap.entrySet()) {
            Video video = videoMapper.selectByPrimaryKey(entry.getKey());
            if(video == null){
                throw new CcbException(CcbExceptionEnum.DATA_NOT_FOUND);
            }
            video.setViews(video.getViews() + entry.getValue());
            if(share.hasKey(video.getId())){
                video.setShareCount(video.getShareCount() + share.get(video.getId()));
            }
            if(enjoy.hasKey(video.getId())){
                video.setEnjoyCount(video.getEnjoyCount() + enjoy.get(video.getId()));
            }
            videoMapper.updateByPrimaryKeySelective(video);
        }

        redisTemplate.delete("share_count");
        redisTemplate.delete("view_count");
        redisTemplate.delete("enjoy_count");
    }
}
