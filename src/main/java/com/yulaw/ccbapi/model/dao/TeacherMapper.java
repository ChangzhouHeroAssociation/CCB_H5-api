package com.yulaw.ccbapi.model.dao;

import com.yulaw.ccbapi.model.pojo.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {

    Teacher selectByPrimaryKey(Long id);

    List<Teacher> findAll();

    List<Teacher> selectForHome();

    List<Teacher> selectByNameLike(String name);

    List<Teacher> selectByVideoId(Long id);
}