package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.dao.BannerMapper;
import com.yulaw.ccbapi.model.pojo.Banner;
import com.yulaw.ccbapi.model.vo.BannerForHomeVO;
import com.yulaw.ccbapi.service.BannerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * BannerService实现类
 * 前两个函数测试用的 业务中没用到
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    BannerMapper bannerMapper;

    @Override
    @Cacheable(value = "getBannerListForHome")
    public List<BannerForHomeVO> getBannerListForHome(){

        ArrayList<BannerForHomeVO> bannerForHomeVOS = new ArrayList<>();

        List<Banner> banners = bannerMapper.selectForHome();
        if(banners == null){
            throw new CcbException(CcbExceptionEnum.NO_POINT_EXCEPTION);
        }
        for (Banner banner : banners) {
            BannerForHomeVO bannerForHomeVO = new BannerForHomeVO();
            BeanUtils.copyProperties(banner,bannerForHomeVO);
            bannerForHomeVOS.add(bannerForHomeVO);
        }
        return  bannerForHomeVOS;
    }
}
