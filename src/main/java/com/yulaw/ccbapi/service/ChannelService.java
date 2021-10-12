package com.yulaw.ccbapi.service;

import com.yulaw.ccbapi.model.pojo.Channel;
import com.yulaw.ccbapi.model.vo.ChannelVO;

import java.util.ArrayList;
import java.util.List;

public interface ChannelService {

    List<ChannelVO> getChannelList();

    ChannelVO getChannelById(Long id);

    Channel getChannelByName(String name);
}
