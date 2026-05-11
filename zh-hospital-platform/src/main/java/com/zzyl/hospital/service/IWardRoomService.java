package com.zzyl.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.hospital.domain.WardRoom;
import com.zzyl.hospital.vo.WardRoomVo;

import java.util.List;

/**
 * 房间Service接口
 *
 * @author ruoyi
 * @date 2024-04-26
 */
public interface IWardRoomService extends IService<WardRoom>
{
    /**
     * 查询房间
     *
     * @param id 房间主键
     * @return 房间
     */
    public WardRoom selectWardRoomById(Long id);

    /**
     * 查询房间列表
     *
     * @param wardRoom 房间
     * @return 房间集合
     */
    public List<WardRoom> selectWardRoomList(WardRoom wardRoom);

    /**
     * 新增房间
     *
     * @param wardRoom 房间
     * @return 结果
     */
    public int insertWardRoom(WardRoom wardRoom);

    /**
     * 修改房间
     *
     * @param wardRoom 房间
     * @return 结果
     */
    public int updateWardRoom(WardRoom wardRoom);

    /**
     * 批量删除房间
     *
     * @param ids 需要删除的房间主键集合
     * @return 结果
     */
    public int deleteWardRoomByIds(Long[] ids);

    /**
     * 根据楼层 id 获取房间视图对象列表
     * @param wardFloorId
     * @return
     */
    List<WardRoomVo> getWardRoomsByWardFloorId(Long wardFloorId);

    /**
     * 获取所有房间（责任患者）
     * @param wardFloorId
     * @return
     */
    List<WardRoomVo> getWardRoomsWithNurByWardFloorId(Long wardFloorId);

    /**
     * 根据楼层ID查询房间下的智能设备及数据
     *
     * @param wardFloorId 楼层ID
     * @return 房间列表
     */
    List<WardRoomVo> getWardRoomsWithMonitoringDeviceByWardFloorId(Long wardFloorId);

    WardRoomVo getWardRoomById(Long id);
}
