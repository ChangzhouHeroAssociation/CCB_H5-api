package com.yulaw.ccbapi.service;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.model.vo.TeacherForHomeVO;
import com.yulaw.ccbapi.model.vo.TeacherVO;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface TeacherService {

    PageInfo getTeacherListForHome(Integer pageNum, Integer pageSize);

    TeacherVO getTeacherById(Long id,Integer distributionId);

    void addTeacherView(Integer distributionId,String teacherName);

}
