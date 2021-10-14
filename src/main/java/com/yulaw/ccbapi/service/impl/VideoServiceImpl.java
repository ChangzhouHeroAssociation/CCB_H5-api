package com.yulaw.ccbapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.model.dao.TeacherMapper;
import com.yulaw.ccbapi.model.dao.VideoAndTeacherMapper;
import com.yulaw.ccbapi.model.dao.VideoMapper;
import com.yulaw.ccbapi.model.pojo.Teacher;
import com.yulaw.ccbapi.model.pojo.Video;
import com.yulaw.ccbapi.model.pojo.VideoAndTeacher;
import com.yulaw.ccbapi.model.vo.TeacherVO;
import com.yulaw.ccbapi.model.vo.VideoVO;
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
    VideoAndTeacherMapper videoAndTeacherMapper;

    @Autowired
    TeacherMapper teacherMapper;

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
        List<VideoVO> resultList = new ArrayList<>();
        for (Video video : videoList) {
            VideoVO videoVO = new VideoVO();
            BeanUtils.copyProperties(video,videoVO);
            try{
                VideoAndTeacher videoAndTeacher = videoAndTeacherMapper.selectByVideoId(videoVO.getId());
                if(videoAndTeacher != null){
                    Teacher teacher = teacherMapper.selectByPrimaryKey(videoAndTeacher.getTeacherId());
                    videoVO.setTeacher(teacher);
                }
                resultList.add(videoVO);
            }catch (MyBatisSystemException e){
                throw e;
            }
        }
        PageInfo pageInfo = new PageInfo(resultList);
        return pageInfo;
    }

}
