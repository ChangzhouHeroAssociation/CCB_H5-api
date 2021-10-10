package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Teacher;

public interface TeacherMapper {
    int deleteByPrimaryKey(Long id);

    Teacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
}