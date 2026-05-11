package com.zzyl.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.common.core.page.TableDataInfo;
import com.zzyl.hospital.domain.VitalSignData;
import com.zzyl.hospital.dto.VitalSignDataPageReqDto;
import com.zzyl.hospital.vo.NotifyData;

/**
 * 设备数据Service接口
 * 
 * @author alexis
 * @date 2026-02-22
 */
public interface IVitalSignDataService extends IService<VitalSignData>
{

    /**
     * 批量插入设备数据
     *
     * @param notifyData 设备数据
     * @return 结果
     */
    void batchInsertVitalSignData(NotifyData notifyData);

    /**
     * 查询设备数据列表
     */
    TableDataInfo selectVitalSignDataList(VitalSignDataPageReqDto vitalSignDataPageReqDto);
}
