package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.VideoAndCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoAndCategoryMapper {

    int deleteByPrimaryKey(Integer id);

    VideoAndCategory selectByPrimaryKey(Integer id);

    List<VideoAndCategory> selectByCategoryId(Long categoryId);

    int updateByPrimaryKeySelective(VideoAndCategory record);

    int updateByPrimaryKey(VideoAndCategory record);
}