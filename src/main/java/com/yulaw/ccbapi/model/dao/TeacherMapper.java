package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    int deleteByPrimaryKey(Long id);

    Teacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher> findAll();
}