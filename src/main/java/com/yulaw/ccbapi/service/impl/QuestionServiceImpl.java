package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.dao.ChannelMapper;
import com.yulaw.ccbapi.model.dao.QuestionMapper;
import com.yulaw.ccbapi.model.pojo.Channel;
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
    public List<QuestionVO> selectByChannelId(Long id){
        List<Question> questionList = questionMapper.selectByChannelId(id);
        ArrayList<QuestionVO> questionVOS = new ArrayList<>();
        for (Question question : questionList) {
            QuestionVO questionVO = new QuestionVO();
            BeanUtils.copyProperties(question, questionVO);
            switch (questionVO.getCategory()){
                case 1:
                case 2:
                    //1单选,2多选
                    String options = question.getOption();
                    String[] split = options.split("&");
                    questionVO.setOptionList(split);
                    break;
                case 3:
                    //判断
                    String[] list = new String[]{"是","否"};
                    questionVO.setOptionList(list);
                    break;
                case 4:
                    //填空
                    questionVO.setOptionList(new String[]{"请输入内容"});
                    break;
                default:
                    throw new CcbException(CcbExceptionEnum.QUESTION_TYPE_ERROR);
            }
            questionVOS.add(questionVO);

        }
        return  questionVOS;
    }
}
