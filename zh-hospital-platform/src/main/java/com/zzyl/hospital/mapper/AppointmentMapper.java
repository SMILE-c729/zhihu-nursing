package com.zzyl.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyl.hospital.domain.Appointment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预约列表Mapper
 */
@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {
}
