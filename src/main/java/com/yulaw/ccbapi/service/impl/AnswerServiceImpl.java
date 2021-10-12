package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.dao.AnswerMapper;
import com.yulaw.ccbapi.model.pojo.Answer;
import com.yulaw.ccbapi.model.request.AddAnswerReq;
import com.yulaw.ccbapi.service.AnswerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerMapper answerMapper;

    @Override
    public void add(AddAnswerReq addAnswerReq){
        Answer answer = new Answer();
        BeanUtils.copyProperties(addAnswerReq,answer);
        answer.setCreateTime(new Date());
        answer.setUpdateTime(new Date());
        int count = answerMapper.insertSelective(answer);
        if(count == 0){
            throw new CcbException(CcbExceptionEnum.ADD_QUESTION_FAILED);
        }
    }
}
