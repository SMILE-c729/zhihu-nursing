package com.zzyl.hospital.controller.member;

import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.R;
import com.zzyl.common.core.page.TableDataInfo;
import com.zzyl.hospital.domain.MedicalOrderItem;
import com.zzyl.hospital.dto.QueryParm;
import com.zzyl.hospital.service.IMedicalOrderItemService;
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
 * 小程序端医嘱项目接口
 */
@RestController
@RequestMapping("/member/orders/project")
@Api(tags = "小程序端-医嘱项目")
public class MemberMedicalOrderItemController extends BaseController {

    @Autowired
    private IMedicalOrderItemService medicalOrderItemService;

    /**
     * 分页查询医嘱项目列表
     */
    @GetMapping("/page")
    @ApiOperation("分页查询医嘱项目列表")
    public TableDataInfo<List<MedicalOrderItem>> page(@ApiParam("查询条件") QueryParm queryParm) {
        List<MedicalOrderItem> list = medicalOrderItemService.selectMedicalOrderItemList(queryParm);
        return getDataTable(list);
    }

    /**
     * 根据ID查询医嘱项目信息
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询医嘱项目信息")
    public R<MedicalOrderItem> detail(@PathVariable("id") @ApiParam("医嘱项目ID") Long id) {
        return R.ok(medicalOrderItemService.selectMedicalOrderItemById(id));
    }
}
