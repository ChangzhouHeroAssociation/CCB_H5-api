package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Answer;

public interface AnswerMapper {
    int deleteByPrimaryKey(Long id);

    Answer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
}