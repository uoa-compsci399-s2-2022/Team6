package com.project.modules.examination.service;

import com.project.common.service.CrudService;
import com.project.modules.examination.dto.CourseDTO;
import com.project.modules.examination.dto.UserCourseDTO;
import com.project.modules.examination.entity.UserCourseEntity;

import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 */
public interface UserCourseService extends CrudService<UserCourseEntity, UserCourseDTO> {
    UserCourseDTO getStageByUser(UserCourseEntity userCourseEntity);

    void deleteByUserName(String userName);


    List<CourseDTO> getAllMajorByType();
}