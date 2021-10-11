package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.ChannelMapper;
import com.yulaw.ccbapi.model.dao.TeacherMapper;
import com.yulaw.ccbapi.model.pojo.Channel;
import com.yulaw.ccbapi.model.pojo.Teacher;
import com.yulaw.ccbapi.model.vo.ChannelVO;
import com.yulaw.ccbapi.model.vo.TeacherVO;
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

    @Override
    @Cacheable(value = "getTeacherList")
    public List<TeacherVO> getTeacherList() {
        List<Teacher> teacherAll = teacherMapper.findAll();
        List<TeacherVO> resultList = new ArrayList<>();
        for (Teacher teacher : teacherAll) {
            TeacherVO teacherVO = new TeacherVO();
            BeanUtils.copyProperties(teacher,teacherVO);
            resultList.add(teacherVO);
        }
        return resultList;
    }
}
