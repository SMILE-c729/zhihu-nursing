package com.zzyl.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzyl.common.utils.StringUtils;
import com.zzyl.hospital.domain.VitalSignData;
import com.zzyl.hospital.dto.MonitoringDeviceStatisticsQueryDto;
import com.zzyl.hospital.mapper.VitalSignDataMapper;
import com.zzyl.hospital.service.ICustomerUserDataService;
import com.zzyl.hospital.vo.MonitoringDeviceMetricsStatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户端用户健康数据服务实现
 */
@Service
public class CustomerUserDataServiceImpl implements ICustomerUserDataService {

    private static final DateTimeFormatter DAY_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter WEEK_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM.dd");
    private static final BigDecimal DAY_ZERO = BigDecimal.ZERO.setScale(1, RoundingMode.HALF_UP);
    private static final BigDecimal WEEK_ZERO = BigDecimal.ZERO.setScale(0, RoundingMode.HALF_UP);

    @Autowired
    private VitalSignDataMapper vitalSignDataMapper;

    /**
     * 按天统计查询指标数据，固定返回24个小时数据
     */
    @Override
    public List<MonitoringDeviceMetricsStatVo> queryVitalSignDataListByDay(MonitoringDeviceStatisticsQueryDto queryDto) {
        LocalDate queryDate = getQueryDate(queryDto);
        LocalDateTime startTime = queryDate.atStartOfDay();
        LocalDateTime endTime = queryDate.plusDays(1).atStartOfDay();

        List<VitalSignData> vitalSignDataList = listVitalSignData(queryDto, startTime, endTime);
        BigDecimal[] hourSumArr = new BigDecimal[24];
        int[] hourCountArr = new int[24];
        for (int i = 0; i < 24; i++) {
            hourSumArr[i] = BigDecimal.ZERO;
        }

        // 统计每个小时的均值，仅处理可转换为数值的数据
        for (VitalSignData vitalSignData : vitalSignDataList) {
            if (vitalSignData.getAlarmTime() == null) {
                continue;
            }
            BigDecimal dataValue = parseDataValue(vitalSignData.getDataValue());
            if (dataValue == null) {
                continue;
            }
            int hour = vitalSignData.getAlarmTime().getHour();
            hourSumArr[hour] = hourSumArr[hour].add(dataValue);
            hourCountArr[hour]++;
        }

        List<MonitoringDeviceMetricsStatVo> result = new ArrayList<>(24);
        for (int hour = 0; hour < 24; hour++) {
            BigDecimal avgValue = DAY_ZERO;
            if (hourCountArr[hour] > 0) {
                avgValue = hourSumArr[hour].divide(BigDecimal.valueOf(hourCountArr[hour]), 1, RoundingMode.HALF_UP);
            }
            String dateTime = LocalDateTime.of(queryDate, java.time.LocalTime.of(hour, 0)).format(DAY_TIME_FORMATTER);
            result.add(new MonitoringDeviceMetricsStatVo(dateTime, avgValue));
        }
        return result;
    }

    /**
     * 按周统计查询指标数据，固定返回近7天数据（含查询日期）
     */
    @Override
    public List<MonitoringDeviceMetricsStatVo> pageQueryWarningData(MonitoringDeviceStatisticsQueryDto queryDto) {
        LocalDate endDate = getQueryDate(queryDto);
        LocalDate startDate = endDate.minusDays(6);

        LocalDateTime startTime = startDate.atStartOfDay();
        LocalDateTime endTime = endDate.plusDays(1).atStartOfDay();

        List<VitalSignData> vitalSignDataList = listVitalSignData(queryDto, startTime, endTime);
        Map<LocalDate, BigDecimal> daySumMap = new HashMap<>();
        Map<LocalDate, Integer> dayCountMap = new HashMap<>();

        // 统计每天的均值，仅处理可转换为数值的数据
        for (VitalSignData vitalSignData : vitalSignDataList) {
            if (vitalSignData.getAlarmTime() == null) {
                continue;
            }
            BigDecimal dataValue = parseDataValue(vitalSignData.getDataValue());
            if (dataValue == null) {
                continue;
            }
            LocalDate currentDate = vitalSignData.getAlarmTime().toLocalDate();
            daySumMap.merge(currentDate, dataValue, BigDecimal::add);
            dayCountMap.merge(currentDate, 1, Integer::sum);
        }

        List<MonitoringDeviceMetricsStatVo> result = new ArrayList<>(7);
        for (LocalDate currentDate = startDate; !currentDate.isAfter(endDate); currentDate = currentDate.plusDays(1)) {
            BigDecimal avgValue = WEEK_ZERO;
            Integer count = dayCountMap.get(currentDate);
            if (count != null && count > 0) {
                avgValue = daySumMap.get(currentDate).divide(BigDecimal.valueOf(count), 0, RoundingMode.HALF_UP);
            }
            result.add(new MonitoringDeviceMetricsStatVo(currentDate.format(WEEK_TIME_FORMATTER), avgValue));
        }
        return result;
    }

    /**
     * 按查询条件获取设备数据
     */
    private List<VitalSignData> listVitalSignData(MonitoringDeviceStatisticsQueryDto queryDto, LocalDateTime startTime, LocalDateTime endTime) {
        LambdaQueryWrapper<VitalSignData> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(VitalSignData::getAlarmTime, startTime)
                .lt(VitalSignData::getAlarmTime, endTime)
                .orderByAsc(VitalSignData::getAlarmTime);
        if (queryDto != null) {
            queryWrapper.eq(StringUtils.isNotEmpty(queryDto.getIotId()), VitalSignData::getIotId, queryDto.getIotId());
            queryWrapper.eq(StringUtils.isNotEmpty(queryDto.getFunctionId()), VitalSignData::getFunctionId, queryDto.getFunctionId());
        }
        return vitalSignDataMapper.selectList(queryWrapper);
    }

    /**
     * 解析数值型数据，解析失败返回null
     */
    private BigDecimal parseDataValue(String dataValue) {
        if (StringUtils.isEmpty(dataValue)) {
            return null;
        }
        try {
            return new BigDecimal(dataValue.trim());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取查询日期，未传时默认当天
     */
    private LocalDate getQueryDate(MonitoringDeviceStatisticsQueryDto queryDto) {
        if (queryDto == null || queryDto.getDate() == null) {
            return LocalDate.now();
        }
        return queryDto.getDate();
    }
}
