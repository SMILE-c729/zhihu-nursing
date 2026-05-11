package com.zzyl.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyl.hospital.domain.WardRoom;
import com.zzyl.hospital.vo.WardRoomVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 房间Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-26
 */
@Mapper
public interface WardRoomMapper extends BaseMapper<WardRoom>
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
     * 删除房间
     *
     * @param id 房间主键
     * @return 结果
     */
    public int deleteWardRoomById(Long id);

    /**
     * 批量删除房间
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWardRoomByIds(Long[] ids);

    List<WardRoomVo> selectByWardFloorId(Long wardFloorId);
    /**
     * 查询楼层所有房间 List<WardRoomVo> selectByWardFloorIdWithNur(Long wardFloorId);
     *
     * @param wardFloorId 楼层主键
     * @return 房间集合
     */
    List<WardRoomVo> getWardRoomsWithMonitoringDeviceByWardFloorId(Long wardFloorId);

    List<WardRoomVo> selectByWardFloorIdWithNur(Long wardFloorId);

    WardRoomVo getWardRoomById(Long id);
}
