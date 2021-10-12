package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.dao.ChannelMapper;
import com.yulaw.ccbapi.model.pojo.Channel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ChannelServiceImplTest {

    @Autowired
    ChannelMapper channelMapper;

    @Test
    public void getChannelByName() {
        //Channel channel = channelMapper.selectByPrimaryKey(3L);
        Channel channel = channelMapper.selectByName("频道4");
        if(channel == null){
            throw new CcbException(CcbExceptionEnum.DATA_NOT_FOUND);
        }
        System.out.println(channel.getContent());
    }
}