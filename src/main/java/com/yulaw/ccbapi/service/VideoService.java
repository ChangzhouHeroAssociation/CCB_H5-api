package com.yulaw.ccbapi.service;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.model.pojo.Video;
import com.yulaw.ccbapi.model.vo.HotVideoVO;
import com.yulaw.ccbapi.model.vo.NewVideoVO;
import com.yulaw.ccbapi.model.vo.TinyVideoVO;
import com.yulaw.ccbapi.model.vo.VideoVO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public interface VideoService {


    ArrayList<HotVideoVO> copyToHotVideo(List<Video> oldList);

    PageInfo getPageList(Integer pageNum, Integer pageSize, String orderBy,
                         Long channelId, Long categoryId, String keywords);

    PageInfo getNewVideoList(Integer pageNum, Integer pageSize);

    PageInfo getRecommendVideoList(Integer pageNum, Integer pageSize, Integer isRecommend);

    VideoVO getVideoById(Long id);

    void addStarById(Long id, Integer type);

    VideoVO getNextVideoById(Long id);

    List<TinyVideoVO> searchVideo(String title);
}
