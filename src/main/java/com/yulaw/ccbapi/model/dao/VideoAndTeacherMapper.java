package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.VideoAndTeacher;

public interface VideoAndTeacherMapper {
    int deleteByPrimaryKey(Long id);

    VideoAndTeacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VideoAndTeacher record);

    int updateByPrimaryKey(VideoAndTeacher record);
}