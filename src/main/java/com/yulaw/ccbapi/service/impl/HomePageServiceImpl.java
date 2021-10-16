package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.HomePageMapper;
import com.yulaw.ccbapi.model.pojo.HomePage;
import com.yulaw.ccbapi.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

/**
 * UserService实现类
 */
@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    HomePageMapper homePageMapper;

    @Override
    @Cacheable(value = "getHomePage")
    public HomePage getHomePage() {
        return homePageMapper.selectOne();
    }
}
