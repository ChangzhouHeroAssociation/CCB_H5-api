package com.yulaw.ccbapi.service;

import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.model.pojo.Video;
import com.yulaw.ccbapi.model.vo.HotVideoVO;
import com.yulaw.ccbapi.model.vo.NewVideoVO;
import com.yulaw.ccbapi.model.vo.VideoVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public interface VideoService {

    List<VideoVO> getVideoList();


    List<Video> getVideoListByChannelId(Long channelId);

    List<Video> getVideoListByCategoryId(Long categoryId);

    List<Video> getVideoListByTitle(String title);

    List<Video> getVideoListByTeacher(String name);

    ArrayList<HotVideoVO> copyToHotVideo(List<Video> oldList);

    PageInfo getPageList(Integer pageNum, Integer pageSize, String orderBy,
                         Long channelId, Long categoryId, String title, String teacherName);

    NewVideoVO getNew();

    List<HotVideoVO> getHotVideoVO();

    VideoVO getVideoById(Long id);


    void addStarById(Long id, Integer type);
}
