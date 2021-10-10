package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Channel;

public interface ChannelMapper {
    int deleteByPrimaryKey(Long id);

    Channel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
}