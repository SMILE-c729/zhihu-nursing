package com.zzyl.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.hospital.domain.Appointment;
import com.zzyl.hospital.dto.AppointmentCreateDto;
import com.zzyl.hospital.vo.AppointmentTimeCountVo;
import java.util.List;

/**
 * 预约列表Service接口
 */
public interface IAppointmentService extends IService<Appointment> {

    /**
     * 查询当前用户当天取消预约次数
     *
     * @param userId 当前用户ID
     * @return 取消次数
     */
    Integer countCancelledAppointmentToday(Long userId);

    /**
     * 查询当天每个时间段剩余预约次数
     *
     * @return 时间段剩余预约次数列表
     */
    List<AppointmentTimeCountVo> countByTime();

    /**
     * 新增预约
     *
     * @param createDto 新增预约参数
     * @param userId 当前用户ID
     */
    void addAppointment(AppointmentCreateDto createDto, Long userId);

    /**
     * 分页查询当前用户预约记录
     *
     * @param query 查询条件
     * @param userId 当前用户ID
     * @return 预约记录列表
     */
    List<Appointment> pageMyAppointment(Appointment query, Long userId);

    /**
     * 取消预约
     *
     * @param id 预约ID
     * @param userId 当前用户ID
     */
    void cancelAppointment(Long id, Long userId);

    /**
     * 定时任务
     * 预约自动到期取消预约
     */
    void cancelAppointmentByTime();

}
