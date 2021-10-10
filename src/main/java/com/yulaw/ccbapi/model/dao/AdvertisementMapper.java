package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Advertisement;

public interface AdvertisementMapper {
    int deleteByPrimaryKey(Long id);

    Advertisement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);
}