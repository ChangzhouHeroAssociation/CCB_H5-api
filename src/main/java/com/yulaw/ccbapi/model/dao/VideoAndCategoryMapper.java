package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.VideoAndCategory;

public interface VideoAndCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    VideoAndCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VideoAndCategory record);

    int updateByPrimaryKey(VideoAndCategory record);
}