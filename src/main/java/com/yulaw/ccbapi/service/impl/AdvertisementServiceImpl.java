package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.AdvertisementMapper;
import com.yulaw.ccbapi.model.pojo.Advertisement;
import com.yulaw.ccbapi.model.vo.AdvertisementVO;
import com.yulaw.ccbapi.service.AdvertisementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    AdvertisementMapper advertisementMapper;

    @Override
    @Cacheable(value = "getAdvertisementList")
    public List<AdvertisementVO> getAdvertisementList() {
        List<Advertisement> advertisementList = advertisementMapper.findAll();
        List<AdvertisementVO> advList = new ArrayList<>();
        for (Advertisement advertisement : advertisementList) {
            AdvertisementVO advertisementVO = new AdvertisementVO();
            BeanUtils.copyProperties(advertisement,advertisementVO);
            advList.add(advertisementVO);
        }
        return advList;
    }

}
