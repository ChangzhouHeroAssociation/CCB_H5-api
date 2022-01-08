package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.IndexLog;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexLogMapper {
    int deleteByPrimaryKey(Integer id);

    IndexLog selectByPrimaryKey(Integer id);

    int insertSelective(IndexLog record);
}
