package com.zzyl.nursing.service;

import java.util.List;
import com.zzyl.nursing.domain.DeviceData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.nursing.vo.DevicePropertyReportVo;
import com.zzyl.nursing.vo.NotifyData;

/**
 * 设备数据Service接口
 * 
 * @author alexis
 * @date 2026-02-22
 */
public interface IDeviceDataService extends IService<DeviceData>
{

    /**
     * 批量插入设备数据
     *
     * @param notifyData 设备数据
     * @return 结果
     */
    void batchInsertDeviceData(NotifyData notifyData);
}
