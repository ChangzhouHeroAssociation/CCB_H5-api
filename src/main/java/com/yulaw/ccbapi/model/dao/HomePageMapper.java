package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.HomePage;
import org.springframework.stereotype.Repository;

@Repository
public interface HomePageMapper {
    int deleteByPrimaryKey(Integer id);

    HomePage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomePage record);

    int updateByPrimaryKey(HomePage record);
}