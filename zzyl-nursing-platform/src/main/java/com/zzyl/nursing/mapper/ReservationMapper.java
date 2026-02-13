package com.zzyl.nursing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyl.nursing.domain.Reservation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预约信息Mapper
 */
@Mapper
public interface ReservationMapper extends BaseMapper<Reservation> {
}
