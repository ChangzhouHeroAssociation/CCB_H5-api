package com.yulaw.ccbapi.service;

import com.yulaw.ccbapi.model.vo.AdvertisementVO;
import com.yulaw.ccbapi.model.vo.ChannelVO;

import java.util.List;

public interface AdvertisementService {

    List<AdvertisementVO> getAdvertisementList();


    AdvertisementVO selectByChannelId(Long id);
}
