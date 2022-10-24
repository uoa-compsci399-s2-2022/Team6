package com.project.modules.examination.controller;

import com.project.common.annotation.LogOperation;
import com.project.common.constant.Constant;
import com.project.common.page.PageData;
import com.project.common.utils.ExcelUtils;
import com.project.common.utils.Result;
import com.project.common.validator.AssertUtils;
import com.project.common.validator.ValidatorUtils;
import com.project.common.validator.group.AddGroup;
import com.project.common.validator.group.DefaultGroup;
import com.project.common.validator.group.UpdateGroup;
import com.project.modules.examination.dto.CourseDTO;
import com.project.modules.examination.dto.UserCourseDTO;
import com.project.modules.examination.entity.CourseEntity;
import com.project.modules.examination.entity.UserCourseEntity;
import com.project.modules.examination.excel.CourseExcel;
import com.project.modules.examination.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 
 *
 *
 */
@RestController
@RequestMapping("examination/course")
@Api(tags="")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("getCourseByMajor")
    @ApiOperation("getCourse")
    public Result<Map<String, Object>> getCourseByMajor(@RequestBody CourseEntity courseEntity){

        List<CourseDTO> userCourseDTOList = courseService.getCourseByMajor(courseEntity);

        List<String> list = userCourseDTOList.stream().map(CourseDTO::getCourse)
                .collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("majorRemark",userCourseDTOList.get(0).getMajorRemark());
        map.put("list",list);
        return new Result<Map<String, Object>>().ok(map);
    }

    @PostMapping("getCourseRemarkByMajor")
    @ApiOperation("getCourse Remark")
    public Result<String> getCourseRemarkByMajor(@RequestBody CourseEntity courseEntity ){

        List<CourseDTO> userCourseDTOList = courseService.getCourseByMajor(courseEntity);

        return new Result<String>().ok(userCourseDTOList.get(0).getRemark());
    }

    @GetMapping("page")
    @ApiOperation("page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "Current page number, starting at 1", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "Number of records per page", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "Sort field", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "Sort mode, optional (asc, desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("examination:course:page")
    public Result<PageData<CourseDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<CourseDTO> page = courseService.page(params);

        return new Result<PageData<CourseDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("information")
    @RequiresPermissions("examination:course:info")
    public Result<CourseDTO> get(@PathVariable("id") Long id){
        CourseDTO data = courseService.get(id);

        return new Result<CourseDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("save")
    @LogOperation("save")
    @RequiresPermissions("examination:course:save")
    public Result save(@RequestBody CourseDTO dto){
        //check data
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        courseService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("update")
    @LogOperation("update")
    @RequiresPermissions("examination:course:update")
    public Result update(@RequestBody CourseDTO dto){
        //check data
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        courseService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("delete")
    @LogOperation("delete")
    @RequiresPermissions("examination:course:delete")
    public Result delete(@RequestBody Long[] ids){
        //check data
        AssertUtils.isArrayEmpty(ids, "id");

        courseService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("export")
    @LogOperation("export")
    @RequiresPermissions("examination:course:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CourseDTO> list = courseService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, CourseExcel.class);
    }

}