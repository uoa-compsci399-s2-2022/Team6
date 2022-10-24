package com.project.modules.examination.controller;

import com.project.common.annotation.LogOperation;
import com.project.common.constant.Constant;
import com.project.common.exception.RenException;
import com.project.common.page.PageData;
import com.project.common.utils.ExcelUtils;
import com.project.common.utils.Result;
import com.project.common.utils.TemplateExcelUtils;
import com.project.common.validator.AssertUtils;
import com.project.common.validator.ValidatorUtils;
import com.project.common.validator.group.AddGroup;
import com.project.common.validator.group.DefaultGroup;
import com.project.common.validator.group.UpdateGroup;
import com.project.modules.examination.dto.CourseDTO;
import com.project.modules.examination.dto.UserCourseDTO;
import com.project.modules.examination.entity.UserCourseEntity;
import com.project.modules.examination.excel.UserCourseExcel;
import com.project.modules.examination.service.UserCourseService;
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
@RequestMapping("examination/usercourse")
@Api(tags="Student course selection form")
public class UserCourseController {
    @Autowired
    private UserCourseService userCourseService;

    @GetMapping("page")
    @ApiOperation("page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "Current page number, starting at 1", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "Number of records per page", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "Sort field", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = Constant.ORDER, value = "Sort mode, optional (asc, desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("examination:usercourse:page")
    public Result<PageData<UserCourseDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<UserCourseDTO> page = userCourseService.page(params);

        return new Result<PageData<UserCourseDTO>>().ok(page);
    }

    @GetMapping("exportUserCourse")
    @ApiOperation("export")
    @LogOperation("export")
//    @RequiresPermissions("examination:usercourse:exportUserCourse")
    public void exportUserCourse(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<UserCourseDTO> list = userCourseService.list(params);
        Map<String, Object> map = new HashMap<>();
        if(list.size()>0){
            for (int i = 0; i < list.get(0).getStage1().split(",").length; i++) {
                String[] split = list.get(0).getStage1().split(",");
                map.put("1-"+i,split[i]);
            }
            for (int i =0; i < list.get(0).getStage2().split(",").length; i++) {
                String[] split = list.get(0).getStage2().split(",");
                map.put("2-"+i,split[i]);
            }
            for (int i = 0; i < list.get(0).getStage3().split(",").length; i++) {
                String[] split = list.get(0).getStage3().split(",");
                map.put("3-"+i,split[i]);
            }
            for (int i = 0; i < list.get(0).getGe().split(",").length; i++) {
                String[] split = list.get(0).getGe().split(",");
                map.put("GE-"+i,split[i]);
            }
        }

        TemplateExcelUtils.exportMultipleSheet(map,response);

    }
    @PostMapping("getAllMajorByType")
    @ApiOperation("information")
    public Result<List<String>> getAllMajorByType(@RequestBody UserCourseEntity userCourseEntity ){

        List<CourseDTO> courseDTOList = userCourseService.getAllMajorByType();
        List<String> list = courseDTOList.stream().map(CourseDTO::getMajor)
                .collect(Collectors.toList());
        return new Result<List<String>>().ok(list);
    }

    @PostMapping("getStageByUser")
    @ApiOperation("information")
    public Result<UserCourseDTO> getStageByUser(@RequestBody UserCourseEntity userCourseEntity ){

        UserCourseDTO userCourseDTO = userCourseService.getStageByUser(userCourseEntity);

        return new Result<UserCourseDTO>().ok(userCourseDTO);
    }

    @PostMapping("insert")
    @ApiOperation("information")
    public Result<Boolean> insert(@RequestBody UserCourseEntity userCourseEntity){
        userCourseService.deleteByUserName(userCourseEntity.getUserName());
        boolean flag = userCourseService.insert(userCourseEntity);

        return new Result<Boolean>().ok(flag);
    }

    @GetMapping("{id}")
    @ApiOperation("information")
    @RequiresPermissions("examination:usercourse:info")
    public Result<UserCourseDTO> get(@PathVariable("id") Long id){
        UserCourseDTO data = userCourseService.get(id);

        return new Result<UserCourseDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("save")
    @LogOperation("save")
    @RequiresPermissions("examination:usercourse:save")
    public Result save(@RequestBody UserCourseDTO dto){
        //check data
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        userCourseService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("update")
    @LogOperation("update")
    @RequiresPermissions("examination:usercourse:update")
    public Result update(@RequestBody UserCourseDTO dto){
        //check data
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        userCourseService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("delete")
    @LogOperation("delete")
    @RequiresPermissions("examination:usercourse:delete")
    public Result delete(@RequestBody Long[] ids){
        //check data
        AssertUtils.isArrayEmpty(ids, "id");

        userCourseService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("export")
    @LogOperation("export")
    @RequiresPermissions("examination:usercourse:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<UserCourseDTO> list = userCourseService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, UserCourseExcel.class);
    }

}