package com.yulaw.ccbapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.dao.*;
import com.yulaw.ccbapi.model.pojo.*;
import com.yulaw.ccbapi.model.vo.*;
import com.yulaw.ccbapi.service.AdvertisementService;
import com.yulaw.ccbapi.service.QuestionService;
import com.yulaw.ccbapi.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    ChannelMapper channelMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    DistributionMapper distributionMapper;

    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    QuestionService questionService;

    @Autowired
    RedisTemplate redisTemplate;


    /**
     * Video对象转HotVideoVO并填充属性值
     * @param oldList
     * @return
     */
    @Override
    public ArrayList<HotVideoVO> copyToHotVideo(List<Video> oldList){
        ArrayList<HotVideoVO> resultList = new ArrayList<>();
        for (Video video : oldList) {
            HotVideoVO hotVideoVO = new HotVideoVO();
            BeanUtils.copyProperties(video,hotVideoVO);


            //(视频讲师关联查询)
            List<Teacher> teachers = teacherMapper.selectByVideoId(hotVideoVO.getId());
            ArrayList<String> nameList = new ArrayList<>();
            for (Teacher teacher : teachers) {
                nameList.add(teacher.getTeacherName());
            }
            hotVideoVO.setTeacherNameList(nameList);


            //频道图标查询

            Channel channel = channelMapper.selectByPrimaryKey(video.getChannelId());
            if(channel != null){
                hotVideoVO.setChannelIcon(channel.getIcon());
                hotVideoVO.setChannnelName(channel.getChannelName());
            }
            resultList.add(hotVideoVO);
        }
        return resultList;
    }

    @Override
    @Cacheable(value = "getPageList")
    public PageInfo getPageList(Integer pageNum, Integer pageSize, String orderBy,
                                Long channelId, Long categoryId, String keywords, Long teacherId){

        ArrayList<HotVideoVO> resultList;
        List<Video> videoList;
        PageHelper.startPage(pageNum,pageSize,orderBy + " desc");
        if(categoryId == null && channelId == null && keywords == null && teacherId == null){
            videoList = videoMapper.findAll();
        }else if(teacherId != null){
            videoList = videoMapper.selectByTeacherId(teacherId);
        }else {
            videoList = videoMapper.selectByChannelIdCategoryIdAndName(channelId,categoryId,keywords);
        }
        if(videoList == null){
            throw new CcbException(CcbExceptionEnum.NO_POINT_EXCEPTION);
        }
        resultList = copyToHotVideo(videoList);
        return new PageInfo(resultList);
    }

    /**
     * 首页最新视频列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    @Cacheable(value = "getNewVideoList")
    public PageInfo getNewVideoList(Integer pageNum, Integer pageSize){
        return getPageList(pageNum,pageSize,"create_time",null,null,null,null);
    }

    /**
     * 首页推荐视频列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    @Cacheable(value = "getRecommendVideoList")
    public PageInfo getRecommendVideoList(Integer pageNum, Integer pageSize,Integer isRecommend) {
        ArrayList<HotVideoVO> recommendList;
        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        List<Video> videos = null;
        if (isRecommend == null){
            PageInfo resultList = getPageList(pageNum, pageSize, "create_time", null, null, null,null);
            return resultList;
        }
        if (isRecommend == 1) {
            videos = videoMapper.selectRecommend(1);
        }else if (isRecommend == 0){
            videos = videoMapper.selectRecommend(0);
        } else {
            throw new CcbException(CcbExceptionEnum.REQUEST_PARAM_ERROR);
        }
        if (videos == null) {
            throw new CcbException(CcbExceptionEnum.NO_POINT_EXCEPTION);
        }
        recommendList = copyToHotVideo(videos);
        return new PageInfo(recommendList);
    }

    @Override
    @Cacheable(value = "getVideoById")
    public VideoVO getVideoById(Long id) {


        Video video = videoMapper.selectByPrimaryKey(id);
        if(video == null){
            throw new CcbException(CcbExceptionEnum.DATA_NOT_FOUND);
        }

        VideoVO videoVO = new VideoVO();
        BeanUtils.copyProperties(video, videoVO);
        Channel channel = channelMapper.selectByPrimaryKey(videoVO.getChannelId());
        if(channel != null){
            videoVO.setChannelName(channel.getChannelName());
        }

        //添加讲师(关联查询）
        List<Teacher> teachers = teacherMapper.selectByVideoId(videoVO.getId());
        ArrayList<TeacherForVideoVO> teacherList = new ArrayList<>();
        for (Teacher teacher : teachers) {
            TeacherForVideoVO teacherForVideoVO = new TeacherForVideoVO();
            BeanUtils.copyProperties(teacher,teacherForVideoVO);
            teacherList.add(teacherForVideoVO);
        }
        videoVO.setTeacherList(teacherList);

        //添加广告
        Advertisement advertisement = advertisementService.getAdvByChannelId(videoVO.getChannelId());
        if(advertisement != null){
            AdvertisementVO advertisementVO = new AdvertisementVO();
            BeanUtils.copyProperties(advertisement, advertisementVO);
            videoVO.setAdvertisement(advertisementVO);
        }

        //添加问卷
        List<QuestionVO> questionVOS = questionService.getQuestionByChannelId(videoVO.getChannelId());
        if(questionVOS == null){
            throw new CcbException(CcbExceptionEnum.NO_POINT_EXCEPTION);
        }
        videoVO.setQuestionList(questionVOS);

        return videoVO;
    }

    /**
     * 视频日志统计
     * @param id
     * @param type
     */
    @Override
    public void addStarById(Long id, Integer type, Integer distributionId){
        if(type == 1){
            //增加一个点赞量到redis
            redisTemplate.opsForHash().increment("enjoy_count", id.toString(), 1);
        }
        if (type == 2){
            //增加一个分享量到redis
            redisTemplate.opsForHash().increment("share_count", id.toString(), 1);

            // 将video分享记录到缓存日志
            //使用'-'符号拼接videoid和distributionid作为key value为访问量
            String keyName = id + "-" + distributionId;
            redisTemplate.opsForHash().increment("video_share", keyName, 1);
        }
        if(type == 3){
            //增加一个播放量到redis
            redisTemplate.opsForHash().increment("view_count", id.toString(), 1);

            // 将video访问量记录到缓存日志
            //使用'-'符号拼接videoid和distributionid作为key value为访问量
            String keyName = id + "-" + distributionId;
            redisTemplate.opsForHash().increment("video_view", keyName, 1);
        }
    }

    @Override
    @Cacheable(value = "getNextVideoById")
    public VideoVO getNextVideoById(Long id) {
        Video video = videoMapper.selectByPrimaryKey(id);
        if(video == null){
            throw new CcbException(CcbExceptionEnum.DATA_NOT_FOUND);
        }
        List<Video> videos = videoMapper.selectByChannelId(video.getChannelId());
        Long resultId = null;
        int flag = 0;
        VideoVO videoVO;
        for (Video video1 : videos) {
            if(video1.getId().equals(id)){
                flag = 1;
                continue;
            }
            if(flag == 1){
                resultId = video1.getId();
                break;
            }
        }
        if(resultId == null){
            videoVO  = getVideoById(videos.get(0).getId());
        }else {
            videoVO = getVideoById(resultId);
        }
        return videoVO;
    }

    @Override
    @Cacheable(value = "searchVideoById")
    public List<TinyVideoVO> searchVideo(String title){
        List<Video> videos = videoMapper.selectByChannelIdCategoryIdAndName(null, null, title);
        ArrayList<TinyVideoVO> tinyVideoVOS = new ArrayList<>();
        for (Video video : videos) {
            TinyVideoVO tinyVideoVO = new TinyVideoVO();
            BeanUtils.copyProperties(video,tinyVideoVO);
            tinyVideoVOS.add(tinyVideoVO);
        }
        return tinyVideoVOS;
    }

    @Override
    public Distribution getDistribution(String url){
        Distribution distribution = null;
        distribution = distributionMapper.selectByUrl(url);
        if (distribution == null){
            throw new CcbException(CcbExceptionEnum.NO_POINT_EXCEPTION);
        }
        return distribution;
    }
}
