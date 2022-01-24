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
import com.yulaw.ccbapi.service.ChannelService;
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
    VideoMapper videoMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @Cacheable(value = "getChannelList")
    public PageInfo getChannelList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize, "weight desc");
        List<Channel> channelAll = channelMapper.selectAll();
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
    public ChannelVO getChannelById(Long id,Integer distributionId) {
        ChannelVO channelVO = new ChannelVO();
        Channel channel = channelMapper.selectByPrimaryKey(id);
        if(channel == null){
            throw new CcbException(CcbExceptionEnum.DATA_NOT_FOUND);
        }
        BeanUtils.copyProperties(channel,channelVO);

        return channelVO;
    }

    @Override
    public void addChannelView(Integer distributionId,String channelName) {
        // 将channel访问量记录到缓存
        //使用'-'符号拼接名称和distributionId作为key value为访问量
        String keyName = channelName + "-" + distributionId.toString();
        redisTemplate.opsForHash().increment("channel", keyName, 1);
    }

}
