package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Answer;
import com.yulaw.ccbapi.model.pojo.VideoLog;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoLogMapper {
    int deleteByPrimaryKey(Integer id);

    VideoLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VideoLog record);

    int updateByPrimaryKey(VideoLog record);

    int insertSelective(VideoLog record);
}