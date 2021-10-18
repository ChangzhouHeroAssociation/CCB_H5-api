package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Banner;
import com.yulaw.ccbapi.model.pojo.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    Banner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}