package com.zzyl.hospital.service;

import com.zzyl.hospital.dto.MonitoringDeviceStatisticsQueryDto;
import com.zzyl.hospital.vo.MonitoringDeviceMetricsStatVo;

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
    List<MonitoringDeviceMetricsStatVo> queryVitalSignDataListByDay(MonitoringDeviceStatisticsQueryDto queryDto);

    /**
     * 按周统计查询指标数据
     *
     * @param queryDto 查询参数
     * @return 7天统计结果
     */
    List<MonitoringDeviceMetricsStatVo> pageQueryWarningData(MonitoringDeviceStatisticsQueryDto queryDto);
}
