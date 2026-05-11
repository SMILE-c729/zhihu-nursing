package com.zzyl.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.core.domain.R;
import com.zzyl.common.core.page.TableDataInfo;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.common.utils.StringUtils;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.hospital.domain.Appointment;
import com.zzyl.hospital.service.IAppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 预约挂号管理
 */
@Api(tags = "预约挂号管理")
@RestController
@RequestMapping("/hospital/appointment")
public class AppointmentController extends BaseController {

    @Autowired
    private IAppointmentService appointmentService;

    @ApiOperation("查询预约挂号列表")
    @PreAuthorize("@ss.hasPermi('hospital:appointment:list')")
    @GetMapping("/list")
    public TableDataInfo<List<Appointment>> list(@ApiParam("预约查询条件") Appointment appointment) {
        startPage();
        LambdaQueryWrapper<Appointment> wrapper = buildQueryWrapper(appointment);
        wrapper.orderByDesc(Appointment::getTime);
        return getDataTable(appointmentService.list(wrapper));
    }

    @ApiOperation("导出预约挂号列表")
    @PreAuthorize("@ss.hasPermi('hospital:appointment:export')")
    @Log(title = "预约挂号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Appointment appointment) {
        List<Appointment> list = appointmentService.list(buildQueryWrapper(appointment));
        ExcelUtil<Appointment> util = new ExcelUtil<>(Appointment.class);
        util.exportExcel(response, list, "预约挂号数据");
    }

    @ApiOperation("获取预约挂号详情")
    @PreAuthorize("@ss.hasPermi('hospital:appointment:query')")
    @GetMapping(value = "/{id}")
    public R<Appointment> getInfo(@PathVariable("id") Long id) {
        return R.ok(appointmentService.getById(id));
    }

    @ApiOperation("新增预约挂号")
    @PreAuthorize("@ss.hasPermi('hospital:appointment:add')")
    @Log(title = "预约挂号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Appointment appointment) {
        return toAjax(appointmentService.save(appointment));
    }

    @ApiOperation("修改预约挂号")
    @PreAuthorize("@ss.hasPermi('hospital:appointment:edit')")
    @Log(title = "预约挂号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Appointment appointment) {
        return toAjax(appointmentService.updateById(appointment));
    }

    @ApiOperation("删除预约挂号")
    @PreAuthorize("@ss.hasPermi('hospital:appointment:remove')")
    @Log(title = "预约挂号", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(appointmentService.removeByIds(Arrays.asList(ids)));
    }

    private LambdaQueryWrapper<Appointment> buildQueryWrapper(Appointment appointment) {
        LambdaQueryWrapper<Appointment> wrapper = new LambdaQueryWrapper<>();
        if (appointment == null) {
            return wrapper;
        }
        wrapper.like(StringUtils.isNotBlank(appointment.getName()), Appointment::getName, appointment.getName())
                .like(StringUtils.isNotBlank(appointment.getMobile()), Appointment::getMobile, appointment.getMobile())
                .like(StringUtils.isNotBlank(appointment.getVisitor()), Appointment::getVisitor, appointment.getVisitor())
                .eq(appointment.getType() != null, Appointment::getType, appointment.getType())
                .eq(appointment.getStatus() != null, Appointment::getStatus, appointment.getStatus());
        return wrapper;
    }
}
