package com.project.modules.examination.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.common.service.impl.CrudServiceImpl;
import com.project.modules.examination.dao.UserCourseDao;
import com.project.modules.examination.dto.CourseDTO;
import com.project.modules.examination.dto.UserCourseDTO;
import com.project.modules.examination.entity.UserCourseEntity;
import com.project.modules.examination.service.UserCourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 */
@Service
public class UserCourseServiceImpl extends CrudServiceImpl<UserCourseDao, UserCourseEntity, UserCourseDTO> implements UserCourseService {

    @Autowired
    UserCourseDao userCourseDao;

    @Override
    public QueryWrapper<UserCourseEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String userName = (String)params.get("userName");

        QueryWrapper<UserCourseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(userName), "user_name", userName);

        return wrapper;
    }


    @Override
    public UserCourseDTO getStageByUser(UserCourseEntity userCourseEntity) {
        return userCourseDao.getStageByUser(userCourseEntity);
    }

    @Override
    public void deleteByUserName(String userName) {
        userCourseDao.deleteByUserName(userName);
    }

    @Override
    public List<CourseDTO> getAllMajorByType() {
        return userCourseDao.getAllMajorByType();
    }
}