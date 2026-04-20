package com.zzyl.nursing.service;

import com.zzyl.nursing.dto.DeviceStatisticsQueryDto;
import com.zzyl.nursing.vo.DeviceMetricsStatVo;

import java.util.List;

/**
 * 客户端用户健康数据服务
 */
public interface ICustomerUserDataService {

    /**
     * 按天统计查询指标数据
     *
     * @param queryDto 查询参数
     * @return 24小时统计结果
     */
    List<DeviceMetricsStatVo> queryDeviceDataListByDay(DeviceStatisticsQueryDto queryDto);

    /**
     * 按周统计查询指标数据
     *
     * @param queryDto 查询参数
     * @return 7天统计结果
     */
    List<DeviceMetricsStatVo> pageQueryAlertData(DeviceStatisticsQueryDto queryDto);
}
