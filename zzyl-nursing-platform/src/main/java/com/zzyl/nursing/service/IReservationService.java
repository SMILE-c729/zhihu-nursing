package com.zzyl.nursing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.nursing.domain.Reservation;
import com.zzyl.nursing.dto.ReservationCreateDto;
import com.zzyl.nursing.vo.ReservationTimeCountVo;
import java.util.List;

/**
 * 预约信息Service接口
 */
public interface IReservationService extends IService<Reservation> {

    /**
     * 查询当前用户当天取消预约次数
     *
     * @param userId 当前用户ID
     * @return 取消次数
     */
    Integer countCancelledReservationToday(Long userId);

    /**
     * 查询当天每个时间段剩余预约次数
     *
     * @return 时间段剩余预约次数列表
     */
    List<ReservationTimeCountVo> countByTime();

    /**
     * 新增预约
     *
     * @param createDto 新增预约参数
     * @param userId 当前用户ID
     */
    void addReservation(ReservationCreateDto createDto, Long userId);

    /**
     * 分页查询当前用户预约记录
     *
     * @param query 查询条件
     * @param userId 当前用户ID
     * @return 预约记录列表
     */
    List<Reservation> pageMyReservation(Reservation query, Long userId);

    /**
     * 取消预约
     *
     * @param id 预约ID
     * @param userId 当前用户ID
     */
    void cancelReservation(Long id, Long userId);

    /**
     * 定时任务
     * 预约自动到期取消预约
     */
    void cancelReservationByTime();

}
