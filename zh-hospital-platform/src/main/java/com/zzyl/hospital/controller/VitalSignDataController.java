package com.zzyl.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zzyl.common.core.domain.R;
import com.zzyl.hospital.dto.VitalSignDataPageReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.hospital.domain.VitalSignData;
import com.zzyl.hospital.service.IVitalSignDataService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;

/**
 * 设备数据Controller
 * 
 * @author alexis
 * @date 2026-02-22
 */
@Api(tags = "生命体征数据管理")
@RestController
@RequestMapping("/hospital/data")
public class VitalSignDataController extends BaseController
{
    @Autowired
    private IVitalSignDataService vitalSignDataService;
    /**
     * 查询设备数据列表
     */
    @PreAuthorize("@ss.hasPermi('patient:data:list')")
    @GetMapping("/list")
    @ApiOperation("查询设备数据列表")
    public TableDataInfo list(VitalSignDataPageReqDto vitalSignDataPageReqDto)
    {
        return vitalSignDataService.selectVitalSignDataList(vitalSignDataPageReqDto);
    }
}
