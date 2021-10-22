package com.yulaw.ccbapi.service;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.model.pojo.Banner;
import com.yulaw.ccbapi.model.vo.AdvertisementVO;
import com.yulaw.ccbapi.model.vo.BannerForHomeVO;
import com.yulaw.ccbapi.model.vo.BannerVO;

import java.util.List;

public interface BannerService {

    PageInfo listForAdmin(Integer pageNum, Integer pageSize, String orderBy);

    List<BannerForHomeVO> getBannerListForHome();
}
