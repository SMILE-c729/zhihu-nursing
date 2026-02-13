package com.zzyl.nursing.controller.member;

import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.R;
import com.zzyl.common.core.page.TableDataInfo;
import com.zzyl.nursing.domain.NursingProject;
import com.zzyl.nursing.dto.QueryParm;
import com.zzyl.nursing.service.INursingProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小程序端护理项目接口
 */
@RestController
@RequestMapping("/member/orders/project")
@Api(tags = "小程序端-护理项目")
public class MemberNursingProjectController extends BaseController {

    @Autowired
    private INursingProjectService nursingProjectService;

    /**
     * 分页查询护理项目列表
     */
    @GetMapping("/page")
    @ApiOperation("分页查询护理项目列表")
    public TableDataInfo<List<NursingProject>> page(@ApiParam("查询条件") QueryParm queryParm) {
        List<NursingProject> list = nursingProjectService.selectNursingProjectList(queryParm);
        return getDataTable(list);
    }

    /**
     * 根据ID查询护理项目信息
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询护理项目信息")
    public R<NursingProject> detail(@PathVariable("id") @ApiParam("护理项目ID") Long id) {
        return R.ok(nursingProjectService.selectNursingProjectById(id));
    }
}
