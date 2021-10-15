package com.yulaw.ccbapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.model.dao.*;
import com.yulaw.ccbapi.model.pojo.*;
import com.yulaw.ccbapi.model.vo.*;
import com.yulaw.ccbapi.service.VideoService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    ChannelMapper channelMapper;

    @Autowired
    AdvertisementMapper advertisementMapper;

    @Autowired
    VideoAndTeacherMapper videoAndTeacherMapper;

    @Autowired
    ChannelAndVideoMapper channelAndVideoMapper;


    @Override
    @Cacheable(value = "getVideoList")
    public List<VideoVO> getVideoList() {
        List<Video> videoAll = videoMapper.findAll();
        List<VideoVO> resultList = new ArrayList<>();
        for (Video video : videoAll) {
            VideoVO videoVO = new VideoVO();
            BeanUtils.copyProperties(video,videoVO);
            resultList.add(videoVO);
        }
        return resultList;
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize, String orderBy){
        PageHelper.startPage(pageNum,pageSize,orderBy + " desc");
        List<Video> videoList = videoMapper.findAll();
        List<HotVideoVO> resultList = new ArrayList<>();
        for (Video video : videoList) {
            HotVideoVO hotVideoVO = new HotVideoVO();
            BeanUtils.copyProperties(video,hotVideoVO);
            try{
                VideoAndTeacher videoAndTeacher = videoAndTeacherMapper.selectByVideoId(hotVideoVO.getId());
                if(videoAndTeacher != null){
                    Teacher teacher = teacherMapper.selectByPrimaryKey(videoAndTeacher.getTeacherId());
                    hotVideoVO.setTeacher(teacher.getTeacherName());
                }
                ChannelAndVideo channelAndVideo = channelAndVideoMapper.selectByVideoId(hotVideoVO.getId());
                if(channelAndVideo != null){
                    Channel channel = channelMapper.selectByPrimaryKey(channelAndVideo.getChannelId());
                    hotVideoVO.setChannelIcon(channel.getIcon());
                }
                resultList.add(hotVideoVO);
            }catch (MyBatisSystemException e){
                throw e;
            }
        }
        PageInfo pageInfo = new PageInfo(resultList);
        return pageInfo;
    }

    @Override
    public NewVideoVO getNew(){
        Video video = videoMapper.selectNew();
        NewVideoVO newVideoVO = new NewVideoVO();
        BeanUtils.copyProperties(video,newVideoVO);
        return newVideoVO;
    }

    @Override
    public List<HotVideoVO> getHotVideoVO(){
        List<Video> videos = videoMapper.selectHotByView();
        ArrayList<HotVideoVO> hotVideoVOS = new ArrayList<>();
        for (Video video : videos) {
            HotVideoVO hotVideoVO = new HotVideoVO();
            BeanUtils.copyProperties(video,hotVideoVO);
            VideoAndTeacher videoAndTeacher = videoAndTeacherMapper.selectByVideoId(hotVideoVO.getId());
            Teacher teacher = teacherMapper.selectByPrimaryKey(videoAndTeacher.getTeacherId());
            hotVideoVO.setTeacher(teacher.getTeacherName());
            ChannelAndVideo channelAndVideo = channelAndVideoMapper.selectByVideoId(hotVideoVO.getId());
            Channel channel = channelMapper.selectByPrimaryKey(channelAndVideo.getChannelId());
            hotVideoVO.setChannelIcon(channel.getIcon());
            hotVideoVOS.add(hotVideoVO);

        }
        return hotVideoVOS;
    }

    @Override
    public VideoVO getVideoById(Long id) {
        Video video = videoMapper.selectByPrimaryKey(id);
        VideoVO videoVO = new VideoVO();
        BeanUtils.copyProperties(video, videoVO);
        VideoAndTeacher videoAndTeacher = videoAndTeacherMapper.selectByVideoId(videoVO.getId());
        Teacher teacher = teacherMapper.selectByPrimaryKey(videoAndTeacher.getTeacherId());
        TeacherForVideoVO teacherForVideoVO = new TeacherForVideoVO();
        BeanUtils.copyProperties(teacher,teacherForVideoVO);
        videoVO.setTeacher(teacherForVideoVO);
        videoVO.setChannelId(channelAndVideoMapper.selectByVideoId(videoVO.getId()).getChannelId());
        Advertisement advertisement = advertisementMapper.selectByChannelId(videoVO.getChannelId());
        if(advertisement != null){
            AdvertisementVO advertisementVO = new AdvertisementVO();
            BeanUtils.copyProperties(advertisement, advertisementVO);
            videoVO.setAdvertisement(advertisementVO);
        }
        return videoVO;
    }

}
