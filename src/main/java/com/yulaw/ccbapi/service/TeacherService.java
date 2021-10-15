package com.yulaw.ccbapi.service;

import com.yulaw.ccbapi.model.vo.TeacherForHomeVO;
import com.yulaw.ccbapi.model.vo.TeacherVO;

import java.util.List;

public interface TeacherService {

    List<TeacherForHomeVO> getTeacherListForHome();

    TeacherVO getTeacherById(Long id);
}
