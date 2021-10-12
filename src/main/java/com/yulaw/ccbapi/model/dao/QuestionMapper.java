package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Question;
import com.yulaw.ccbapi.model.pojo.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMapper {
    int deleteByPrimaryKey(Long id);

    Question selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> findAll();

    Question selectByChannelId(Long channelId);
}