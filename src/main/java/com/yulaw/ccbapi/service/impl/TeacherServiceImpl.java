package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.*;
import com.yulaw.ccbapi.model.pojo.*;
import com.yulaw.ccbapi.model.vo.*;
import com.yulaw.ccbapi.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    VideoAndTeacherMapper videoAndTeacherMapper;

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    ChannelMapper channelMapper;

    @Autowired
    ChannelAndVideoMapper channelAndVideoMapper;



    @Override
    public List<TeacherForHomeVO> getTeacherListForHome(){
        List<Teacher> teachers = teacherMapper.selectForHome();
        ArrayList<TeacherForHomeVO> teacherForHomeVOs = new ArrayList<>();
        for (Teacher teacher : teachers) {
            TeacherForHomeVO teacherForHomeVO = new TeacherForHomeVO();
            BeanUtils.copyProperties(teacher,teacherForHomeVO);
            teacherForHomeVOs.add(teacherForHomeVO);
        }
        return teacherForHomeVOs;

    }

    @Override
    public TeacherVO getTeacherById(Long id) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        TeacherVO teacherVO = new TeacherVO();
        BeanUtils.copyProperties(teacher, teacherVO);
        ArrayList<HotVideoVO> hotVideoVOS = new ArrayList<>();
        List<VideoAndTeacher> videoAndTeachers = videoAndTeacherMapper.selectByTeacherId(teacherVO.getId());
        for (VideoAndTeacher videoAndTeacher : videoAndTeachers) {
            Video video = videoMapper.selectByPrimaryKey(videoAndTeacher.getVideoId());
            HotVideoVO hotVideoVO = new HotVideoVO();
            BeanUtils.copyProperties(video,hotVideoVO);
            hotVideoVO.setTeacher(teacherVO.getTeacherName());
            ChannelAndVideo channelAndVideo = channelAndVideoMapper.selectByVideoId(hotVideoVO.getId());
            Channel channel = channelMapper.selectByPrimaryKey(channelAndVideo.getChannelId());
            hotVideoVO.setChannelIcon(channel.getIcon());
            hotVideoVOS.add(hotVideoVO);
        }
        teacherVO.setHotVideoVOList(hotVideoVOS);
        return teacherVO;


    }
}
