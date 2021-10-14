package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.ChannelAndVideo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelAndVideoMapper {
    int deleteByPrimaryKey(Integer id);

    ChannelAndVideo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChannelAndVideo record);

    int updateByPrimaryKey(ChannelAndVideo record);

    List<ChannelAndVideo> selectByChannelId(Long channelId);
}