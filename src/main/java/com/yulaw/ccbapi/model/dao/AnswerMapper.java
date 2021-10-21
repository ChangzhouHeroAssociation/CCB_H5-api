package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Answer;
import com.yulaw.ccbapi.model.request.AddAnswerReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerMapper {
    int deleteByPrimaryKey(Long id);

    Answer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);

    int insertSelective(Answer record);

    int insertBatch(@Param("list") List<Answer> list);
}