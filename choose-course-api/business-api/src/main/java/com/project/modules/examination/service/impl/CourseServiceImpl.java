package com.project.modules.examination.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.common.service.impl.CrudServiceImpl;
import com.project.modules.examination.dao.CourseDao;
import com.project.modules.examination.dto.CourseDTO;
import com.project.modules.examination.entity.CourseEntity;
import com.project.modules.examination.service.CourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 *
 */
@Service
public class CourseServiceImpl extends CrudServiceImpl<CourseDao, CourseEntity, CourseDTO> implements CourseService {

    @Resource
    CourseDao courseDao;

    @Override
    public QueryWrapper<CourseEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CourseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public List<CourseDTO> getCourseByMajor(CourseEntity courseEntity) {
        return courseDao.getCourseByMajor(courseEntity);
    }
}