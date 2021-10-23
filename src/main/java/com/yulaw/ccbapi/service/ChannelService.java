package com.yulaw.ccbapi.service;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.model.pojo.Channel;
import com.yulaw.ccbapi.model.vo.ChannelForHomeVO;
import com.yulaw.ccbapi.model.vo.ChannelVO;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.List;

public interface ChannelService {

    PageInfo getChannelList(Integer pageNum, Integer pageSize);

    ChannelVO getChannelById(Long id, Integer pageNum, Integer pageSize);

}
