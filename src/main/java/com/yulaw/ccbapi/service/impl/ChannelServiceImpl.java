package com.yulaw.ccbapi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.dao.*;
import com.yulaw.ccbapi.model.pojo.*;
import com.yulaw.ccbapi.model.vo.ChannelForHomeVO;
import com.yulaw.ccbapi.model.vo.ChannelVO;
import com.yulaw.ccbapi.model.vo.HotVideoVO;
import com.yulaw.ccbapi.service.ChannelService;
import com.yulaw.ccbapi.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelMapper channelMapper;

    @Autowired
    ChannelAndVideoMapper channelAndVideoMapper;

    @Autowired
    VideoAndTeacherMapper videoAndTeacherMapper;

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    VideoService videoService;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Cacheable(value = "getChannelList")
    public PageInfo getChannelList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize, "create_time desc");
        List<Channel> channelAll = channelMapper.findChannelAll();
        if(channelAll == null){
            throw new CcbException(CcbExceptionEnum.NO_POINT_EXCEPTION);
        }
        List<ChannelForHomeVO> channelList = new ArrayList<>();
        for (Channel channel : channelAll) {
            ChannelForHomeVO channelForHomeVO = new ChannelForHomeVO();
            BeanUtils.copyProperties(channel,channelForHomeVO);
            channelList.add(channelForHomeVO);
        }
        return new PageInfo(channelList);
    }

    @Override
    @Cacheable(value = "getChannelById")
    public ChannelVO getChannelById(Long id,Integer pageNum, Integer pageSize) {
        ChannelVO channelVO = new ChannelVO();
        Channel channel = channelMapper.selectByPrimaryKey(id);
        if(channel == null){
            throw new CcbException(CcbExceptionEnum.DATA_NOT_FOUND);
        }
        BeanUtils.copyProperties(channel,channelVO);
        PageInfo videoList = videoService.getPageList(pageNum, pageSize, "views", id, 0L, "");

        channelVO.setVideoList(videoList);

        // 将channel访问量记录到缓存
        BoundHashOperations<String,String,Integer> hashKey = redisTemplate.boundHashOps("channel");

        if(hashKey.hasKey(channelVO.getChannelName())){
            //FIXME : 实现自增 BoundHashOperations.increament 报错
            Integer value2 = hashKey.get(channelVO.getChannelName());
            value2 = value2 + 1;
            hashKey.put(channelVO.getChannelName(), value2);
        }else {
            hashKey.put(channelVO.getChannelName(), 1);
        }
        return channelVO;
    }

    @Override
    public Channel getChannelByName(String name) {
        Channel channel = channelMapper.selectByName(name);
        if(channel == null){
            throw new CcbException(CcbExceptionEnum.DATA_NOT_FOUND);
        }
        return channel;
    }
}
