package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Banner;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerMapper {
    int deleteByPrimaryKey(Long id);

    Banner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);
}