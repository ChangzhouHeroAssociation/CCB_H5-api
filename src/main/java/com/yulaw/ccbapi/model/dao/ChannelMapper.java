package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Channel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelMapper {
    int deleteByPrimaryKey(Long id);

    Channel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);

    List<Channel> findChannelAll();
}