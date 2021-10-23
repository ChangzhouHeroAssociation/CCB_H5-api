package com.yulaw.ccbapi.service;

import com.yulaw.ccbapi.model.pojo.Advertisement;
import com.yulaw.ccbapi.model.vo.AdvertisementVO;
import com.yulaw.ccbapi.model.vo.ChannelVO;

import java.util.List;

public interface AdvertisementService {

    Advertisement getAdvByChannelId(Long id);
}
