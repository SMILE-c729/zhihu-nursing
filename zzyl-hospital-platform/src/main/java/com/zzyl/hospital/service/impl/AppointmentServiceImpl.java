package com.zzyl.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyl.common.exception.base.BaseException;
import com.zzyl.common.utils.StringUtils;
import com.zzyl.hospital.domain.Appointment;
import com.zzyl.hospital.dto.AppointmentCreateDto;
import com.zzyl.hospital.mapper.AppointmentMapper;
import com.zzyl.hospital.service.IAppointmentService;
import com.zzyl.hospital.vo.AppointmentTimeCountVo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 预约列表Service实现
 */
@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements IAppointmentService {

    /**
     * 每个时间段最多预约人数
     */
    private static final int MAX_COUNT_PER_TIME = 6;

    /**
     * 已取消状态
     */
    private static final int CANCELLED_STATUS = 2;

    /**
     * 已预约状态
     */
    private static final int RESERVED_STATUS = 0;
    /**
     * 已过期状态
     */
    private static final int OVERDUE_STATUS = 3;
    @Autowired
    private AppointmentMapper appointmentMapper;

    /**
     * 查询当前用户当天取消预约次数
     */
    @Override
    public Integer countCancelledAppointmentToday(Long userId) {
        String userIdStr = String.valueOf(userId);
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1);

        long count = lambdaQuery().eq(Appointment::getStatus, CANCELLED_STATUS).eq(Appointment::getUpdateBy, userIdStr).ge(Appointment::getUpdateTime, toDate(start)).lt(Appointment::getUpdateTime, toDate(end)).count();
        return (int) count;
    }

    /**
     * 查询当天每个时间段剩余预约次数
     */
    @Override
    public List<AppointmentTimeCountVo> countByTime() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1);

        QueryWrapper<Appointment> wrapper = new QueryWrapper<>();
        wrapper.select("`time` as time", "count(*) as usedCount").ge("`time`", start).lt("`time`", end).ne("status", CANCELLED_STATUS).groupBy("`time`").orderByAsc("`time`");

        List<Map<String, Object>> rows = appointmentMapper.selectMaps(wrapper);
        List<AppointmentTimeCountVo> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            AppointmentTimeCountVo vo = new AppointmentTimeCountVo();
            LocalDateTime time = toLocalDateTime(getMapValue(row, "time"));
            vo.setTime(time);
            Object usedCountObj = getMapValue(row, "usedCount");
            int usedCount = usedCountObj == null ? 0 : Integer.parseInt(usedCountObj.toString());
            vo.setCount(Math.max(0, MAX_COUNT_PER_TIME - usedCount));
            result.add(vo);
        }
        return result;
    }

    /**
     * 新增预约
     */
    @Override
    public void addAppointment(AppointmentCreateDto createDto, Long userId) {
        if (createDto == null || createDto.getTime() == null) {
            throw new BaseException("预约时间不能为空");
        }
        if (StringUtils.isBlank(createDto.getMobile())) {
            throw new BaseException("手机号不能为空");
        }
        if (StringUtils.isBlank(createDto.getName())) {
            throw new BaseException("姓名不能为空");
        }
        // 判断时间段是否已预约满
        long usedCount = lambdaQuery().eq(Appointment::getTime, createDto.getTime()).ne(Appointment::getStatus, CANCELLED_STATUS).count();
        if (usedCount >= MAX_COUNT_PER_TIME) {
            throw new BaseException("当前时间段预约已满");
        }
        // 创建预约记录
        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(createDto, appointment);
        appointment.setType(createDto.getType() == null ? 0 : createDto.getType());
        appointment.setStatus(RESERVED_STATUS);
        appointment.setCreateBy(String.valueOf(userId));
        appointment.setCreateTime(new Date());
        appointment.setVisitor(createDto.getVisitor());
        save(appointment);
    }

    /**
     * 分页查询当前用户预约记录
     */
    @Override
    public List<Appointment> pageMyAppointment(Appointment query, Long userId) {
        String userIdStr = String.valueOf(userId);
        LambdaQueryWrapper<Appointment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Appointment::getCreateBy, userIdStr).like(query != null && StringUtils.isNotBlank(query.getName()), Appointment::getName, query.getName()).eq(query != null && query.getStatus() != null, Appointment::getStatus, query.getStatus()).orderByDesc(Appointment::getCreateTime);
        return appointmentMapper.selectList(wrapper);
    }

    /**
     * 取消预约
     */
    @Override
    public void cancelAppointment(Long id, Long userId) {
        Appointment appointment = getById(id);
        if (appointment == null) {
            throw new BaseException("预约记录不存在");
        }

        String userIdStr = String.valueOf(userId);
        if (!Objects.equals(userIdStr, appointment.getCreateBy())) {
            throw new BaseException("无权限取消该预约");
        }
        if (Objects.equals(appointment.getStatus(), CANCELLED_STATUS)) {
            return;
        }

        appointment.setStatus(CANCELLED_STATUS);
        appointment.setUpdateBy(userIdStr);
        appointment.setUpdateTime(new Date());
        updateById(appointment);
    }

    /**
     * 定时任务
     * 预约自动到期取消预约
     */
    @Override
    public void cancelAppointmentByTime() {
        //判断当前时间是否已过预约时间以及状态是否为0
        LocalDateTime now = LocalDateTime.now();
        lambdaUpdate().eq(Appointment::getStatus, RESERVED_STATUS)
                .lt(Appointment::getTime, now)
                .set(Appointment::getStatus, OVERDUE_STATUS)
                .set(Appointment::getUpdateTime, now)
                .set(Appointment::getUpdateBy, 1L)
                .update();
    }

    /**
     * LocalDateTime转换为Date
     */
    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 通用时间对象转换为LocalDateTime
     */
    private LocalDateTime toLocalDateTime(Object timeObj) {
        if (timeObj == null) {
            return null;
        }
        if (timeObj instanceof LocalDateTime) {
            return (LocalDateTime) timeObj;
        }
        if (timeObj instanceof Timestamp) {
            return ((Timestamp) timeObj).toLocalDateTime();
        }
        if (timeObj instanceof Date) {
            return ((Date) timeObj).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        if (timeObj instanceof String) {
            try {
                return Timestamp.valueOf(((String) timeObj).replace("T", " ")).toLocalDateTime();
            } catch (Exception ignore) {
                return null;
            }
        }
        return null;
    }

    /**
     * 兼容不同驱动返回的列名大小写
     */
    private Object getMapValue(Map<String, Object> row, String key) {
        if (row.containsKey(key)) {
            return row.get(key);
        }
        for (Map.Entry<String, Object> entry : row.entrySet()) {
            if (key.equalsIgnoreCase(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
