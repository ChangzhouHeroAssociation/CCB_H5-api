package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.ChannelLog;
import com.yulaw.ccbapi.model.pojo.TeacherLog;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelLogMapper {
    int deleteByPrimaryKey(Integer id);

    ChannelLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChannelLog record);

    int updateByPrimaryKey(ChannelLog record);

    int insertSelective(ChannelLog record);
}