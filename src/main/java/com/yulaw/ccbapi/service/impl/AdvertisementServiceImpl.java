package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.AdvertisementMapper;
import com.yulaw.ccbapi.model.pojo.Advertisement;
import com.yulaw.ccbapi.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    AdvertisementMapper advertisementMapper;

    @Override
    @Cacheable(value = "getAdvByChannelId")
    public Advertisement getAdvByChannelId(Long id){
        return advertisementMapper.selectByChannelId(id);
    }
}
