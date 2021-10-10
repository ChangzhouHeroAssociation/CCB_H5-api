package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.BannerMapper;
import com.yulaw.ccbapi.model.pojo.Banner;
import com.yulaw.ccbapi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService实现类
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    BannerMapper bannerMapper;

    @Override
    public Banner getBanner() {
        return bannerMapper.selectByPrimaryKey(24L);
    }
}
