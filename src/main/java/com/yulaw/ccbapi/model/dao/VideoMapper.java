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

    List<Video> selectAllOrderByViews();

    Video selectNew();

    List<Video> selectHotByView();

    List<Video> selectByTitle(String title);

}