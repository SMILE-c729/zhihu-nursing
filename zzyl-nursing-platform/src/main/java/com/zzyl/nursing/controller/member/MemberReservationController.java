package com.zzyl.nursing.controller.member;

import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.core.domain.R;
import com.zzyl.common.utils.UserThreadLocal;
import com.zzyl.nursing.domain.Reservation;
import com.zzyl.nursing.dto.ReservationCreateDto;
import com.zzyl.nursing.service.IReservationService;
import com.zzyl.nursing.vo.ReservationTimeCountVo;
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
 * 预约信息Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/member/reservation")
@Api(tags = "预约信息相关接口")
public class MemberReservationController extends BaseController {

    @Autowired
    private IReservationService reservationService;

    /**
     * 查询当天取消预约数量
     */
    @GetMapping("/cancelled-count")
    @ApiOperation("查询取消预约数量")
    public R<Integer> getCancelledReservationCount() {
        Long userId = UserThreadLocal.getUserId();
        return R.ok(reservationService.countCancelledReservationToday(userId));
    }

    /**
     * 查询当天每个时间段剩余预约次数
     */
    @GetMapping("/countByTime")
    @ApiOperation("查询当天每个时间段剩余预约次数")
    public R<List<ReservationTimeCountVo>> countByTime() {
        return R.ok(reservationService.countByTime());
    }

    /**
     * 新增预约
     */
    @PostMapping
    @ApiOperation("新增预约")
    public AjaxResult add(@RequestBody @ApiParam("新增预约参数") ReservationCreateDto createDto) {
        Long userId = UserThreadLocal.getUserId();
        reservationService.addReservation(createDto, userId);
        return success();
    }

    /**
     * 分页查询当前用户预约记录
     */
    @GetMapping("/page")
    @ApiOperation("分页查询预约记录")
    public AjaxResult page(@ApiParam("查询条件") Reservation query) {
        Long userId = UserThreadLocal.getUserId();
        startPage();
        List<Reservation> list = reservationService.pageMyReservation(query, userId);
        return success(getDataTable(list));
    }

    /**
     * 取消预约
     */
    @PutMapping("/{id}/cancel")
    @ApiOperation("取消预约")
    public AjaxResult cancel(@PathVariable("id") @ApiParam("预约ID") Long id) {
        Long userId = UserThreadLocal.getUserId();
        reservationService.cancelReservation(id, userId);
        return success();
    }
}
