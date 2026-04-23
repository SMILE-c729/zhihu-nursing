package com.zzyl.hospital.controller.member;

import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.core.domain.R;
import com.zzyl.common.utils.UserThreadLocal;
import com.zzyl.hospital.domain.Appointment;
import com.zzyl.hospital.dto.AppointmentCreateDto;
import com.zzyl.hospital.service.IAppointmentService;
import com.zzyl.hospital.vo.AppointmentTimeCountVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 预约列表Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/member/appointment")
@Api(tags = "小程序端-预约管理")
public class MemberAppointmentController extends BaseController {

    @Autowired
    private IAppointmentService appointmentService;

    /**
     * 查询当天取消预约数量
     */
    @GetMapping("/cancelled-count")
    @ApiOperation("查询取消预约数量")
    public R<Integer> getCancelledAppointmentCount() {
        Long userId = UserThreadLocal.getUserId();
        return R.ok(appointmentService.countCancelledAppointmentToday(userId));
    }

    /**
     * 查询当天每个时间段剩余预约次数
     */
    @GetMapping("/countByTime")
    @ApiOperation("查询当天每个时间段剩余预约次数")
    public R<List<AppointmentTimeCountVo>> countByTime() {
        return R.ok(appointmentService.countByTime());
    }

    /**
     * 新增预约
     */
    @PostMapping
    @ApiOperation("新增预约")
    public AjaxResult add(@RequestBody @ApiParam("新增预约参数") AppointmentCreateDto createDto) {
        Long userId = UserThreadLocal.getUserId();
        appointmentService.addAppointment(createDto, userId);
        return success();
    }

    /**
     * 分页查询当前用户预约记录
     */
    @GetMapping("/page")
    @ApiOperation("分页查询预约记录")
    public AjaxResult page(@ApiParam("查询条件") Appointment query) {
        Long userId = UserThreadLocal.getUserId();
        startPage();
        List<Appointment> list = appointmentService.pageMyAppointment(query, userId);
        return success(getDataTable(list));
    }

    /**
     * 取消预约
     */
    @PutMapping("/{id}/cancel")
    @ApiOperation("取消预约")
    public AjaxResult cancel(@PathVariable("id") @ApiParam("预约ID") Long id) {
        Long userId = UserThreadLocal.getUserId();
        appointmentService.cancelAppointment(id, userId);
        return success();
    }
}
