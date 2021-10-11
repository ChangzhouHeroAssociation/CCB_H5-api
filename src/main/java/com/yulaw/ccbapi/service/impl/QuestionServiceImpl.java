package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.QuestionMapper;
import com.yulaw.ccbapi.model.pojo.Question;
import com.yulaw.ccbapi.model.vo.QuestionVO;
import com.yulaw.ccbapi.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    @Cacheable(value = "getQuestionList")
    public List<QuestionVO> getQuestionList() {
        List<Question> questionAll = questionMapper.findAll();
        List<QuestionVO> resultList = new ArrayList<>();
        for (Question question : questionAll) {
            QuestionVO questionVO = new QuestionVO();
            BeanUtils.copyProperties(question,questionVO);
            resultList.add(questionVO);
        }
        return resultList;
    }
}
