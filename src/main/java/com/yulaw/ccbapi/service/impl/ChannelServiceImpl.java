package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.dao.ChannelAndVideoMapper;
import com.yulaw.ccbapi.model.dao.ChannelMapper;
import com.yulaw.ccbapi.model.dao.VideoMapper;
import com.yulaw.ccbapi.model.pojo.Channel;
import com.yulaw.ccbapi.model.pojo.ChannelAndVideo;
import com.yulaw.ccbapi.model.pojo.Video;
import com.yulaw.ccbapi.model.vo.ChannelVO;
import com.yulaw.ccbapi.service.ChannelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    VideoMapper videoMapper;

    @Override
    @Cacheable(value = "getChannelList")
    public List<ChannelVO> getChannelList() {
        List<Channel> channelAll = channelMapper.findChannelAll();
        List<ChannelVO> channelList = new ArrayList<>();
        for (Channel channel : channelAll) {
            ChannelVO channelVO = new ChannelVO();
            BeanUtils.copyProperties(channel,channelVO);
            channelList.add(channelVO);
        }
        return channelList;
    }

    @Override
    public ChannelVO getChannelById(Long id) {
        ChannelVO channelVO = new ChannelVO();
        Channel channel = channelMapper.selectByPrimaryKey(id);
        if(channel == null){
            throw new CcbException(CcbExceptionEnum.DATA_NOT_FOUND);
        }
        BeanUtils.copyProperties(channel,channelVO);
        ArrayList<Video> videos = new ArrayList<>();
        List<ChannelAndVideo> channelAndVideos = channelAndVideoMapper.selectByChannelId(channelVO.getId());
        for (ChannelAndVideo channelAndVideo : channelAndVideos) {
            Video video = videoMapper.selectByPrimaryKey(channelAndVideo.getVideoId());
            videos.add(video);
        }
        channelVO.setVideos(videos);
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
