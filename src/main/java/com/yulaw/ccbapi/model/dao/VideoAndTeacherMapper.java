package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.VideoAndTeacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoAndTeacherMapper {
    int deleteByPrimaryKey(Long id);

    VideoAndTeacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VideoAndTeacher record);

    int updateByPrimaryKey(VideoAndTeacher record);

    VideoAndTeacher selectByVideoId(Long videoId);

    List<VideoAndTeacher> selectByTeacherId(Long teacherId);
}