package com.zzyl.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.hospital.domain.WardFloor;
import com.zzyl.hospital.vo.WardStructureTreeVo;
import com.zzyl.hospital.vo.WardFloorVo;

import java.util.List;

/**
 * 楼层Service接口
 *
 * @author ruoyi
 * @date 2024-04-26
 */
public interface IWardFloorService extends IService<WardFloor>
{
    /**
     * 查询楼层
     *
     * @param id 楼层主键
     * @return 楼层
     */
    public WardFloor selectWardFloorById(Long id);

    /**
     * 新增楼层
     *
     * @param wardFloor 楼层
     * @return 结果
     */
    public int insertWardFloor(WardFloor wardFloor);

    /**
     * 修改楼层
     *
     * @param wardFloor 楼层
     * @return 结果
     */
    public int updateWardFloor(WardFloor wardFloor);

    /**
     * 批量删除楼层
     *
     * @param ids 需要删除的楼层主键集合
     * @return 结果
     */
    public int deleteWardFloorByIds(Long[] ids);

    /**
     * 删除楼层信息
     *
     * @param id 楼层主键
     * @return 结果
     */
    public int deleteWardFloorById(Long id);

    /**
     * 查询所有楼层（责任患者）
     * @return
     */
    List<WardFloor> selectAllByNur();

    /**
     * 查询所有有智能设备的楼层
     *
     * @return 楼层列表
     */
    List<WardFloorVo> selectAllByMonitoringDevice();

    /**
     * Get wardFloor/wardRoom/wardBed tree by wardBed status.
     *
     * @param status wardBed status
     * @return tree nodes
     */
    List<WardStructureTreeVo> getWardRoomAndWardBedByWardBedStatus(Integer status);
}
