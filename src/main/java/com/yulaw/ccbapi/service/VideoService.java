package com.yulaw.ccbapi.service;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.model.vo.VideoVO;

import java.util.List;

public interface VideoService {

    List<VideoVO> getVideoList();

    PageInfo listForAdmin(Integer pageNum, Integer pageSize, String orderBy);
}
