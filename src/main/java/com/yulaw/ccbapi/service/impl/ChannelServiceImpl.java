package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.dao.*;
import com.yulaw.ccbapi.model.pojo.*;
import com.yulaw.ccbapi.model.vo.ChannelForHomeVO;
import com.yulaw.ccbapi.model.vo.ChannelVO;
import com.yulaw.ccbapi.model.vo.HotVideoVO;
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
    VideoAndTeacherMapper videoAndTeacherMapper;

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    @Cacheable(value = "getChannelList")
    public List<ChannelForHomeVO> getChannelList() {
        List<Channel> channelAll = channelMapper.findChannelAll();
        List<ChannelForHomeVO> channelList = new ArrayList<>();
        for (Channel channel : channelAll) {
            ChannelForHomeVO channelForHomeVO = new ChannelForHomeVO();
            BeanUtils.copyProperties(channel,channelForHomeVO);
            channelList.add(channelForHomeVO);
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
        ArrayList<HotVideoVO> videos = new ArrayList<>();
        List<ChannelAndVideo> channelAndVideos = channelAndVideoMapper.selectByChannelId(channelVO.getId());
        for (ChannelAndVideo channelAndVideo : channelAndVideos) {
            Video video = videoMapper.selectByPrimaryKey(channelAndVideo.getVideoId());
            HotVideoVO hotVideoVO = new HotVideoVO();
            BeanUtils.copyProperties(video,hotVideoVO);
            hotVideoVO.setChannelIcon(channelVO.getIcon());
            VideoAndTeacher videoAndTeacher = videoAndTeacherMapper.selectByVideoId(hotVideoVO.getId());
            Teacher teacher = teacherMapper.selectByPrimaryKey(videoAndTeacher.getTeacherId());
            hotVideoVO.setTeacher(teacher.getTeacherName());
            videos.add(hotVideoVO);
        }
        channelVO.setHotVideoVOList(videos);
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
