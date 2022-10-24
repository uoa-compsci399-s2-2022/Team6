package com.project.modules.examination.service;

import com.project.common.service.CrudService;
import com.project.modules.examination.dto.CourseDTO;
import com.project.modules.examination.entity.CourseEntity;

import java.util.List;

/**
 * 
 *
 *
 */
public interface CourseService extends CrudService<CourseEntity, CourseDTO> {
    List<CourseDTO> getCourseByMajor(CourseEntity courseEntity);
}