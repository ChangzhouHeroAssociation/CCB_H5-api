package com.yulaw.ccbapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yulaw.ccbapi.exception.CcbException;
import com.yulaw.ccbapi.exception.CcbExceptionEnum;
import com.yulaw.ccbapi.model.dao.*;
import com.yulaw.ccbapi.model.pojo.*;
import com.yulaw.ccbapi.model.vo.*;
import com.yulaw.ccbapi.service.QuestionService;
import com.yulaw.ccbapi.service.VideoService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.namespace.QName;
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
    AdvertisementMapper advertisementMapper;

    @Autowired
    VideoAndTeacherMapper videoAndTeacherMapper;

    @Autowired
    ChannelAndVideoMapper channelAndVideoMapper;

    @Autowired
    VideoAndCategoryMapper videoAndCategoryMapper;

    @Autowired
    QuestionService questionService;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    @Cacheable(value = "getVideoList")
    public List<VideoVO> getVideoList() {
        List<Video> videoAll = videoMapper.findAll();
        List<VideoVO> resultList = new ArrayList<>();
        for (Video video : videoAll) {
            VideoVO videoVO = new VideoVO();
            BeanUtils.copyProperties(video,videoVO);
            resultList.add(videoVO);
        }
        return resultList;
    }

    /**
     * 按频道精确查询
     * @param channelId
     * @return
     */
    @Override
    @Cacheable("getVideoListByChannelId")
    public List<Video> getVideoListByChannelId(Long channelId){
        List<ChannelAndVideo> channelAndVideos = channelAndVideoMapper.selectByChannelId(channelId);
        ArrayList<Video> videoList = new ArrayList<>();
        for (ChannelAndVideo channelAndVideo : channelAndVideos) {
            Video video = videoMapper.selectByPrimaryKey(channelAndVideo.getVideoId());
            videoList.add(video);
        }
        return videoList;
    }

    /**
     * 按视频分组精确查询
     * @param categoryId
     * @return
     */
    @Override
    @Cacheable(value = "getVideoListByCategoryId")
    public List<Video> getVideoListByCategoryId(Long categoryId){
        List<VideoAndCategory> videoAndCategorys = videoAndCategoryMapper.selectByCategoryId(categoryId);
        ArrayList<Video> videoList = new ArrayList<>();
        for (VideoAndCategory videoAndCategory : videoAndCategorys) {
            Video video = videoMapper.selectByPrimaryKey(videoAndCategory.getVideoId());
            videoList.add(video);
        }
        return videoList;
    }

    /**
     * 按视频标题模糊查询
     * @param title
     * @return
     */
    @Override
    @Cacheable(value = "getVideoListByTitle")
    public List<Video> getVideoListByTitle(String title){
        List<Video> videoList = videoMapper.selectByTitle(title);
        return videoList;
    }

    /**
     * 按讲师姓名模糊查询
     * @param name
     * @return
     */
    @Override
    @Cacheable(value = "getVideoListByTeacher")
    public List<Video> getVideoListByTeacher(String name){
        List<Teacher> teachers = teacherMapper.selectByNameLike(name);
        List<Video> resultList = new ArrayList<>();
        for (Teacher teacher : teachers) {
            List<VideoAndTeacher> videoAndTeachers = videoAndTeacherMapper.selectByTeacherId(teacher.getId());
            for (VideoAndTeacher videoAndTeacher : videoAndTeachers) {
                resultList.add(videoMapper.selectByPrimaryKey(videoAndTeacher.getVideoId()));
            }
        }
        return resultList;
    }

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
            try{
                VideoAndTeacher videoAndTeacher = videoAndTeacherMapper.selectByVideoId(hotVideoVO.getId());
                if(videoAndTeacher != null){
                    Teacher teacher = teacherMapper.selectByPrimaryKey(videoAndTeacher.getTeacherId());
                    hotVideoVO.setTeacher(teacher.getTeacherName());
                }
                ChannelAndVideo channelAndVideo = channelAndVideoMapper.selectByVideoId(hotVideoVO.getId());
                if(channelAndVideo != null){
                    Channel channel = channelMapper.selectByPrimaryKey(channelAndVideo.getChannelId());
                    hotVideoVO.setChannelIcon(channel.getIcon());
                }
                resultList.add(hotVideoVO);
            }catch (MyBatisSystemException e){
                throw e;
            }
        }
        return resultList;
    }

    @Override
    @Cacheable(value = "getPageList")
    public PageInfo getPageList(Integer pageNum, Integer pageSize, String orderBy,
                                Long channelId, Long categoryId, String title, String teacherName){

        ArrayList<HotVideoVO> resultList = null;
        List<Video> videoList;
        if(channelId == 0L && categoryId == 0L && "".equals(title) && "".equals(teacherName)){
            PageHelper.startPage(pageNum,pageSize,orderBy + " desc");
            videoList = videoMapper.findAll();
        }else if(channelId != 0L && categoryId == 0L && "".equals(title) && "".equals(teacherName)){
            PageHelper.startPage(pageNum,pageSize);
            //FIXME : 缺少按新增时间排序
            videoList = getVideoListByChannelId(channelId);

        }else if(channelId == 0L && categoryId != 0L && "".equals(title) && "".equals(teacherName)){
            PageHelper.startPage(pageNum,pageSize);
            //FIXME : 缺少按新增时间排序
            videoList = getVideoListByCategoryId(categoryId);
        }else if(channelId == 0L && categoryId == 0L && !("".equals(title)) && "".equals(teacherName)){
            PageHelper.startPage(pageNum,pageSize,"create_time" + " desc");
            videoList = getVideoListByTitle(title);
        }else if(channelId == 0L && categoryId == 0L && "".equals(title)){
            PageHelper.startPage(pageNum,pageSize,"create_time" + " desc");
            videoList = getVideoListByTeacher(teacherName);
        }else {
            throw new CcbException(CcbExceptionEnum.REQUEST_PARAM_ERROR);
        }
        if(videoList != null){
            resultList = copyToHotVideo(videoList);
            return new PageInfo(resultList);
        }else {
            throw new CcbException(CcbExceptionEnum.DATA_NOT_FOUND);
        }
    }

    @Override
    @Cacheable(value = "getNew")
    public NewVideoVO getNew(){
        Video video = videoMapper.selectNew();
        NewVideoVO newVideoVO = new NewVideoVO();
        BeanUtils.copyProperties(video,newVideoVO);
        return newVideoVO;
    }

    @Override
    @Cacheable(value = "getHotVideoVO")
    public List<HotVideoVO> getHotVideoVO(){
        List<Video> videos = videoMapper.selectHotByView();
        ArrayList<HotVideoVO> hotVideoVOS = new ArrayList<>();
        for (Video video : videos) {
            HotVideoVO hotVideoVO = new HotVideoVO();
            BeanUtils.copyProperties(video,hotVideoVO);
            VideoAndTeacher videoAndTeacher = videoAndTeacherMapper.selectByVideoId(hotVideoVO.getId());
            Teacher teacher = teacherMapper.selectByPrimaryKey(videoAndTeacher.getTeacherId());
            hotVideoVO.setTeacher(teacher.getTeacherName());
            ChannelAndVideo channelAndVideo = channelAndVideoMapper.selectByVideoId(hotVideoVO.getId());
            Channel channel = channelMapper.selectByPrimaryKey(channelAndVideo.getChannelId());
            hotVideoVO.setChannelIcon(channel.getIcon());
            hotVideoVOS.add(hotVideoVO);

        }
        return hotVideoVOS;
    }

    @Override
    @Cacheable(value = "getVideoById")
    public VideoVO getVideoById(Long id) {
        //播放，只要调取视频详情接口就可以计入一次播放
        addStarById(id,2);


        Video video = videoMapper.selectByPrimaryKey(id);
        VideoVO videoVO = new VideoVO();
        BeanUtils.copyProperties(video, videoVO);

        VideoAndTeacher videoAndTeacher = videoAndTeacherMapper.selectByVideoId(videoVO.getId());
        Teacher teacher = teacherMapper.selectByPrimaryKey(videoAndTeacher.getTeacherId());
        TeacherForVideoVO teacherForVideoVO = new TeacherForVideoVO();
        BeanUtils.copyProperties(teacher,teacherForVideoVO);
        videoVO.setTeacher(teacherForVideoVO);

        videoVO.setChannelId(channelAndVideoMapper.selectByVideoId(videoVO.getId()).getChannelId());

        Advertisement advertisement = advertisementMapper.selectByChannelId(videoVO.getChannelId());
        if(advertisement != null){
            AdvertisementVO advertisementVO = new AdvertisementVO();
            BeanUtils.copyProperties(advertisement, advertisementVO);
            videoVO.setAdvertisement(advertisementVO);
        }
        videoVO.setQuestionList(questionService.selectByChannelId(videoVO.getChannelId()));

        // 将video访问量记录到缓存
        BoundHashOperations<String,String,Integer> hashKey = redisTemplate.boundHashOps("video_view");

        if(hashKey.hasKey(videoVO.getVideoTitle())){
            //FIXME : 实现自增 BoundHashOperations.increament 报错
            Integer value2 = hashKey.get(videoVO.getVideoTitle());
            value2 = value2 + 1;
            hashKey.put(videoVO.getVideoTitle(), value2);
        }else {
            hashKey.put(videoVO.getVideoTitle(), 1);
        }

        return videoVO;
    }

    @Override
    public void addStarById(Long id, Integer type){
        Video video = videoMapper.selectByPrimaryKey(id);
        if(type == 1){
            video.setEnjoyCount(video.getEnjoyCount() + 1);
        }
        if(type == 2){
            video.setViews(video.getViews() + 1);
        }
        if (type == 3){
            video.setShareCount(video.getShareCount() + 1);
            // 将video访问量记录到缓存
            BoundHashOperations<String,String,Integer> hashKey = redisTemplate.boundHashOps("video_share");

            if(hashKey.hasKey(video.getVideoTitle())){
                //FIXME : 实现自增 BoundHashOperations.increament 报错
                Integer value2 = hashKey.get(video.getVideoTitle());
                value2 = value2 + 1;
                hashKey.put(video.getVideoTitle(), value2);
            }else {
                hashKey.put(video.getVideoTitle(), 1);
            }
        }
        videoMapper.updateByPrimaryKeySelective(video);
    }

}
