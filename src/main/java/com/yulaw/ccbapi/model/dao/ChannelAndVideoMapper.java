package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.ChannelAndVideo;

public interface ChannelAndVideoMapper {
    int deleteByPrimaryKey(Integer id);

    ChannelAndVideo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChannelAndVideo record);

    int updateByPrimaryKey(ChannelAndVideo record);
}