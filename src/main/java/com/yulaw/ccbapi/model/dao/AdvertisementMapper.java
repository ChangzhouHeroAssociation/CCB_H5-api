package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Advertisement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementMapper {
    int deleteByPrimaryKey(Long id);

    Advertisement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);

    List<Advertisement> findAll();
}