package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Distribution;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributionMapper {

    Distribution selectByUrl(String url);

    List<Distribution> selectAll();

}
