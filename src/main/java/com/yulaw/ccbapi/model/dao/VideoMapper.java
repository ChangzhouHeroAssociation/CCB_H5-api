package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Teacher;
import com.yulaw.ccbapi.model.pojo.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoMapper {
    int deleteByPrimaryKey(Long id);

    Video selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    List<Video> findAll();

    Video selectNew();

    Video selectByChannelId(Long id);

    List<Video> selectHotByView();

    List<Video> selectByTitle(String title);

    List<Video> selectByTeacher(String keywords);

    List<Video> selectByTeacherId(Long id);

    List<Video> selectByChannelIdCategoryIdAndName(Long channelId, Long categoryId,String keywords);

}