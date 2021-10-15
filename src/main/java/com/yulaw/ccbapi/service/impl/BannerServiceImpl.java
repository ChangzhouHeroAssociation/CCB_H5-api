package com.yulaw.ccbapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.model.dao.BannerMapper;
import com.yulaw.ccbapi.model.pojo.Advertisement;
import com.yulaw.ccbapi.model.pojo.Banner;
import com.yulaw.ccbapi.model.pojo.Video;
import com.yulaw.ccbapi.model.vo.AdvertisementVO;
import com.yulaw.ccbapi.model.vo.BannerForHomeVO;
import com.yulaw.ccbapi.model.vo.BannerVO;
import com.yulaw.ccbapi.model.vo.VideoVO;
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

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize, String orderBy) {
        PageHelper.startPage(pageNum,pageSize,orderBy + " desc");
        List<Banner> bannerList = bannerMapper.findAll();
        List<BannerVO> resultList = new ArrayList<>();
        for (Banner banner : bannerList) {
            BannerVO bannerVO = new BannerVO();
            BeanUtils.copyProperties(banner,bannerVO);
            resultList.add(bannerVO);
        }
        PageInfo pageInfo = new PageInfo(resultList);
        return pageInfo;
    }

    @Override
    public List<BannerForHomeVO> getBannerListForHome(){
        List<Banner> banners = bannerMapper.selectForHome();
        ArrayList<BannerForHomeVO> bannerForHomeVOS = new ArrayList<>();
        for (Banner banner : banners) {
            BannerForHomeVO bannerForHomeVO = new BannerForHomeVO();
            BeanUtils.copyProperties(banner,bannerForHomeVO);
            bannerForHomeVOS.add(bannerForHomeVO);

        }
        return  bannerForHomeVOS;

    }
}
