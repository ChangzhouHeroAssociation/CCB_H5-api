package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Video;

public interface VideoMapper {
    int deleteByPrimaryKey(Long id);

    Video selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);
}