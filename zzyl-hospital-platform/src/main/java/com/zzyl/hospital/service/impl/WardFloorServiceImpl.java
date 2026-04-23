package com.zzyl.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyl.hospital.domain.WardFloor;
import com.zzyl.hospital.mapper.WardFloorMapper;
import com.zzyl.hospital.service.IWardFloorService;
import com.zzyl.hospital.vo.WardStructureTreeVo;
import com.zzyl.hospital.vo.WardStructureVo;
import com.zzyl.hospital.vo.WardFloorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 楼层Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-26
 */
@Service
public class WardFloorServiceImpl extends ServiceImpl<WardFloorMapper, WardFloor> implements IWardFloorService
{
    @Autowired
    private WardFloorMapper wardFloorMapper;

    /**
     * 查询楼层
     *
     * @param id 楼层主键
     * @return 楼层
     */
    @Override
    public WardFloor selectWardFloorById(Long id)
    {
        return getById(id);
    }

    /**
     * 新增楼层
     *
     * @param wardFloor 楼层
     * @return 结果
     */
    @Override
    public int insertWardFloor(WardFloor wardFloor)
    {
        return save(wardFloor) ? 1 : 0;
    }

    /**
     * 修改楼层
     *
     * @param wardFloor 楼层
     * @return 结果
     */
    @Override
    public int updateWardFloor(WardFloor wardFloor)
    {
        return updateById(wardFloor) ? 1 : 0;
    }

    /**
     * 批量删除楼层
     *
     * @param ids 需要删除的楼层主键
     * @return 结果
     */
    @Override
    public int deleteWardFloorByIds(Long[] ids)
    {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除楼层信息
     *
     * @param id 楼层主键
     * @return 结果
     */
    @Override
    public int deleteWardFloorById(Long id)
    {
        return removeById(id) ? 1 : 0;
    }

    /**
     * 查询所有楼层（责任患者）
     */
    @Override
    public List<WardFloor> selectAllByNur() {
        return wardFloorMapper.selectAllByNur();
    }

    /**
     * 查询所有有智能设备的楼层
     */
    @Override
    public List<WardFloorVo> selectAllByMonitoringDevice() {
        return wardFloorMapper.selectAllByMonitoringDevice();
    }

    /**
     * 根据病床状态查询楼层树
     *
     * @param status wardBed status
     * @return tree nodes
     */
    @Override
    public List<WardStructureTreeVo> getWardRoomAndWardBedByWardBedStatus(Integer status) {
       /* //1.1. 调用 mapper 查询数据,
        List<WardStructureVo> rows = wardFloorMapper.selectWardRoomAndWardBedByWardBedStatus(status);
        //1.2. 判断数据是否存在,
        if (rows == null || rows.isEmpty()) {
            //1.3. 如果不存在, 返回空集合,
            return Collections.emptyList();
        }
        //2.key = wardRoomId，value = 房间树节点
        Map<Long, WardStructureTreeVo> wardFloorNodes = new LinkedHashMap<>();
        //3.key = wardFloorId，value = 楼层树节点
        Map<Long, Map<Long, WardStructureTreeVo>> wardRoomNodes = new LinkedHashMap<>();

        for (WardStructureVo row : rows) {
            // 5. 跳过不完整行
            if (row.getWardFloorId() == null || row.getWardRoomId() == null || row.getWardBedId() == null) {
                continue;
            }

            WardStructureTreeVo wardFloorNode = wardFloorNodes.computeIfAbsent(row.getWardFloorId(), id -> {
                WardStructureTreeVo node = new WardStructureTreeVo(String.valueOf(id), row.getWardFloorName(), new ArrayList<>());
                return node;
            });

            Map<Long, WardStructureTreeVo> wardFloorWardRoomMap = wardRoomNodes.computeIfAbsent(row.getWardFloorId(), id -> new LinkedHashMap<>());
            WardStructureTreeVo wardRoomNode = wardFloorWardRoomMap.computeIfAbsent(row.getWardRoomId(), id -> {
                WardStructureTreeVo node = new WardStructureTreeVo(String.valueOf(id), row.getWardRoomCode(), new ArrayList<>());
                wardFloorNode.getChildren().add(node);
                return node;
            });

            wardRoomNode.getChildren().add(new WardStructureTreeVo(String.valueOf(row.getWardBedId()), row.getWardBedNo(), null));
        }

        return new ArrayList<>(wardFloorNodes.values());*/
        return wardFloorMapper.selectWardRoomAndWardBedByWardBedStatus(status);
    }

}
