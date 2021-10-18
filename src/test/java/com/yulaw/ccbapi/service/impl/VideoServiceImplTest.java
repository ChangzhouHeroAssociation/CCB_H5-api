package com.yulaw.ccbapi.service.impl;

import com.yulaw.ccbapi.model.dao.VideoMapper;
import com.yulaw.ccbapi.model.pojo.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VideoServiceImplTest {

    @Autowired
    VideoMapper videoMapper;

    @Test
    public void getVideoListByTitle() {
        List<Video> tt = (ArrayList<Video>)videoMapper.selectByTitle("六");
        System.out.println(tt);

    }
}