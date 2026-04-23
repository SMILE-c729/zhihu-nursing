package com.zzyl.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyl.hospital.domain.WardFloor;
import com.zzyl.hospital.vo.WardStructureTreeVo;
import com.zzyl.hospital.vo.WardStructureVo;
import com.zzyl.hospital.vo.WardFloorVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 楼层Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-26
 */
@Mapper
public interface WardFloorMapper extends BaseMapper<WardFloor>
{
    /**
     * 查询楼层
     *
     * @param id 楼层主键
     * @return 楼层
     */
    public WardFloor selectWardFloorById(Long id);

    /**
     * 查询楼层列表
     *
     * @param wardFloor 楼层
     * @return 楼层集合
     */
    public List<WardFloor> selectWardFloorList(WardFloor wardFloor);

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
     * 删除楼层
     *
     * @param id 楼层主键
     * @return 结果
     */
    public int deleteWardFloorById(Long id);

    /**
     * 批量删除楼层
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWardFloorByIds(Long[] ids);

    /**
     * 查询所有楼层（责任患者）
     * @return 结果
     */
    List<WardFloor> selectAllByNur();

    /**
     * 查询所有有智能设备的楼层
     *
     * @return 楼层列表
     */
    List<WardFloorVo> selectAllByMonitoringDevice();

    /**
     * 根据病床状态查询获取所有楼层数据
     *
     * @param status wardBed status
     * @return rows for tree building
     */
    List<WardStructureTreeVo> selectWardRoomAndWardBedByWardBedStatus(Integer status);
}
