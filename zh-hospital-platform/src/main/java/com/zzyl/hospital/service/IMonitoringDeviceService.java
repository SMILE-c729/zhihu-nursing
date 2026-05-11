package com.zzyl.hospital.service;

import java.util.List;

import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.hospital.domain.MonitoringDevice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.hospital.dto.MonitoringDeviceDto;
import com.zzyl.hospital.vo.MonitoringDeviceDetailVo;
import com.zzyl.hospital.vo.ProductVo;

/**
 * 设备Service接口
 *
 * @author alexis
 * @date 2026-02-11
 */
public interface IMonitoringDeviceService extends IService<MonitoringDevice> {
    /**
     * 查询设备列表
     *
     * @param monitoringDevice 设备
     * @return 设备集合
     */
    public List<MonitoringDevice> selectMonitoringDeviceList(MonitoringDevice monitoringDevice);

    /**
     * 从物联网平台同步产品列表
     */
    void syncProductList();

    /**
     * 查询所有产品列表
     */
    List<ProductVo> allProduct();

    /**
     * 注册设备
     *
     * @param monitoringDeviceDto 设备信息
     */
    void registerMonitoringDevice(MonitoringDeviceDto monitoringDeviceDto);

    /**
     * 查询设备详情
     *
     * @param iotId 设备iotId
     * @return 设备详情
     */
    MonitoringDeviceDetailVo queryMonitoringDeviceDetail(String iotId);

    /**
     * 查询设备上报数据
     *
     * @param iotId 设备iotId
     * @return 设备上报数据
     */
    AjaxResult queryServiceProperties(String iotId);

    /**
     * 修改设备
     *
     * @param monitoringDeviceDto 设备信息
     * @return 结果
     */
    int updateMonitoringDevice(MonitoringDeviceDto monitoringDeviceDto);

    /**
     * 删除设备
     *
     * @param iotId 设备iotId
     * @return 结果
     */
    int deleteMonitoringDeviceByIotId(String iotId);

    /**
     * 查询产品详情
     *
     * @param productKey 产品id
     * @return 产品服务能力详情
     */
    AjaxResult queryProduct(String productKey);
}
