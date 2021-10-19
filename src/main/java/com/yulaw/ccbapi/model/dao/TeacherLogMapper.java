package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.TeacherLog;
import com.yulaw.ccbapi.model.pojo.VideoLog;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherLogMapper {
    int deleteByPrimaryKey(Integer id);

    TeacherLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherLog record);

    int updateByPrimaryKey(TeacherLog record);

    int insertSelective(TeacherLog record);
}