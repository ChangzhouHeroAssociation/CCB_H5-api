package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Question;

public interface QuestionMapper {
    int deleteByPrimaryKey(Long id);

    Question selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);
}