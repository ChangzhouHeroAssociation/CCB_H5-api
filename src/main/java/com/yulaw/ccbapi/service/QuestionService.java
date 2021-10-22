package com.yulaw.ccbapi.service;

import com.yulaw.ccbapi.model.pojo.Question;
import com.yulaw.ccbapi.model.vo.QuestionVO;


import java.util.List;

public interface QuestionService {

    List<QuestionVO> selectByChannelId(Long id);
}
