package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.BannerMapper;
import com.yulaw.ccbapi.model.pojo.Advertisement;
import com.yulaw.ccbapi.model.pojo.Banner;
import com.yulaw.ccbapi.model.vo.AdvertisementVO;
import com.yulaw.ccbapi.model.vo.BannerVO;
import com.yulaw.ccbapi.service.BannerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserService实现类
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    BannerMapper bannerMapper;

    @Override
    @Cacheable(value = "getBannerList")
    public List<BannerVO> getBannerList() {
        List<Banner> bannerList = bannerMapper.findAll();
        List<BannerVO> resultList = new ArrayList<>();
        for (Banner banner : bannerList) {
            BannerVO bannerVO = new BannerVO();
            BeanUtils.copyProperties(banner,bannerVO);
            resultList.add(bannerVO);
        }
        return resultList;
    }
}
