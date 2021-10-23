package com.yulaw.ccbapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.dao.*;
import com.yulaw.ccbapi.model.pojo.*;
import com.yulaw.ccbapi.model.vo.*;
import com.yulaw.ccbapi.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    RedisTemplate redisTemplate;



    @Override
    @Cacheable(value = "getTeacherListForHome")
    public PageInfo getTeacherListForHome(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        ArrayList<TeacherForHomeVO> teacherForHomeVOs = new ArrayList<>();

        List<Teacher> teachers = teacherMapper.selectForHome();
        if(teachers == null){
            throw new CcbException(CcbExceptionEnum.NO_POINT_EXCEPTION);
        }
        for (Teacher teacher : teachers) {
            TeacherForHomeVO teacherForHomeVO = new TeacherForHomeVO();
            BeanUtils.copyProperties(teacher,teacherForHomeVO);
            teacherForHomeVOs.add(teacherForHomeVO);
        }

        return new PageInfo(teacherForHomeVOs);

    }

    @Override
    @Cacheable(value = "getTeacherById")
    public TeacherVO getTeacherById(Long id) {

        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        if(teacher == null){
            throw new CcbException(CcbExceptionEnum.DATA_NOT_FOUND);
        }
        TeacherVO teacherVO = new TeacherVO();
        BeanUtils.copyProperties(teacher, teacherVO);
        ArrayList<HotVideoVO> hotVideoList = new ArrayList<>();

        List<Video> videos = videoMapper.selectByTeacherId(teacherVO.getId());
        for (Video video : videos) {
            HotVideoVO hotVideoVO = new HotVideoVO();
            BeanUtils.copyProperties(video,hotVideoVO);
            hotVideoVO.setTeacher(teacherVO.getTeacherName());

            Channel channel = channelMapper.selectByPrimaryKey(video.getChannelId());
            if(channel == null){
                throw new CcbException(CcbExceptionEnum.DATA_NOT_FOUND);
            }
            hotVideoVO.setChannelIcon(channel.getIcon());

            hotVideoList.add(hotVideoVO);
        }
        teacherVO.setHotVideoVOList(hotVideoList);

        // 将teacher访问量记录到缓存
        BoundHashOperations<String,String,Integer> hashKey = redisTemplate.boundHashOps("teacher");

        if(hashKey.hasKey(teacherVO.getTeacherName())){
            //FIXME : 实现自增 BoundHashOperations.increament 报错
            Integer value2 = hashKey.get(teacherVO.getTeacherName());
            value2 = value2 + 1;
            hashKey.put(teacherVO.getTeacherName(), value2);
        }else {
            hashKey.put(teacherVO.getTeacherName(), 1);
        }


        return teacherVO;


    }
}
