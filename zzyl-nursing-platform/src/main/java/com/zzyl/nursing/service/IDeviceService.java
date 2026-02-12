package com.zzyl.nursing.service;

import java.util.List;

import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.nursing.domain.Device;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.nursing.dto.DeviceDto;
import com.zzyl.nursing.vo.DeviceDetailVo;
import com.zzyl.nursing.vo.ProductVo;

/**
 * 设备Service接口
 *
 * @author alexis
 * @date 2026-02-11
 */
public interface IDeviceService extends IService<Device> {
    /**
     * 查询设备列表
     *
     * @param device 设备
     * @return 设备集合
     */
    public List<Device> selectDeviceList(Device device);

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
     * @param deviceDto 设备信息
     */
    void registerDevice(DeviceDto deviceDto);

    /**
     * 查询设备详情
     *
     * @param iotId 设备iotId
     * @return 设备详情
     */
    DeviceDetailVo queryDeviceDetail(String iotId);

    /**
     * 查询设备上报数据
     *
     * @param iotId 设备iotId
     * @return 设备上报数据
     */
    AjaxResult queryServiceProperties(String iotId);
}
