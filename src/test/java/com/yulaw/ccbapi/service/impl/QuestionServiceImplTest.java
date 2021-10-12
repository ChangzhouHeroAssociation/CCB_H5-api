package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.ChannelMapper;
import com.yulaw.ccbapi.model.dao.QuestionMapper;
import com.yulaw.ccbapi.model.pojo.Channel;
import com.yulaw.ccbapi.model.pojo.Question;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class QuestionServiceImplTest {

    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    ChannelMapper channelMapper;

    @Test
    public void selectByChannelName() {
        Channel channel = channelMapper.selectByName("频道3");
        Question question = questionMapper.selectByChannelId(channel.getId());
        System.out.println(question.getTitle());
    }
}