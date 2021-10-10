package com.yulaw.ccbapi.service;

import com.yulaw.ccbapi.model.pojo.Banner;
import com.yulaw.ccbapi.model.vo.AdvertisementVO;
import com.yulaw.ccbapi.model.vo.BannerVO;

import java.util.List;

public interface BannerService {
    List<BannerVO> getBannerList();
}
