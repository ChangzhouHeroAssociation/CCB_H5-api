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

import java.util.ArrayList;
import java.util.Date;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerMapper answerMapper;

    @Override
    public void add(AddAnswerReq[] addAnswerReq){
        ArrayList<Answer> answers = new ArrayList<>();
        for (AddAnswerReq answerReq : addAnswerReq) {
            Answer answer = new Answer();
            BeanUtils.copyProperties(answerReq,answer);
            answer.setCreateTime(new Date());
            answer.setUpdateTime(new Date());
            answers.add(answer);
        }
        try {
            int count = answerMapper.insertBatch(answers);
            if(count == 0){
                throw new CcbException(CcbExceptionEnum.ADD_QUESTION_FAILED);
            }else if(count < answers.size()){
                throw new CcbException(CcbExceptionEnum.INSERT_LOST);
            }
        }catch (Exception e){
            throw new CcbException(CcbExceptionEnum.ADD_QUESTION_FAILED);
        }
    }
}
