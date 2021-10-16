package com.yulaw.ccbapi.service;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.model.pojo.Video;
import com.yulaw.ccbapi.model.vo.HotVideoVO;
import com.yulaw.ccbapi.model.vo.NewVideoVO;
import com.yulaw.ccbapi.model.vo.VideoVO;

import java.util.List;

public interface VideoService {

    List<VideoVO> getVideoList();

    PageInfo listForAdmin(Integer pageNum, Integer pageSize, String orderBy);

    NewVideoVO getNew();

    List<HotVideoVO> getHotVideoVO();

    VideoVO getVideoById(Long id);


    void addStarById(Long id, Integer type);
}
