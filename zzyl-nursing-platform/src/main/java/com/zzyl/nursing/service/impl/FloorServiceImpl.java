package com.zzyl.nursing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyl.nursing.domain.Floor;
import com.zzyl.nursing.mapper.FloorMapper;
import com.zzyl.nursing.service.IFloorService;
import com.zzyl.nursing.vo.FloorRoomBedTreeVo;
import com.zzyl.nursing.vo.FloorRoomBedVo;
import com.zzyl.nursing.vo.FloorVo;
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
public class FloorServiceImpl extends ServiceImpl<FloorMapper, Floor> implements IFloorService
{
    @Autowired
    private FloorMapper floorMapper;

    /**
     * 查询楼层
     *
     * @param id 楼层主键
     * @return 楼层
     */
    @Override
    public Floor selectFloorById(Long id)
    {
        return getById(id);
    }

    /**
     * 新增楼层
     *
     * @param floor 楼层
     * @return 结果
     */
    @Override
    public int insertFloor(Floor floor)
    {
        return save(floor) ? 1 : 0;
    }

    /**
     * 修改楼层
     *
     * @param floor 楼层
     * @return 结果
     */
    @Override
    public int updateFloor(Floor floor)
    {
        return updateById(floor) ? 1 : 0;
    }

    /**
     * 批量删除楼层
     *
     * @param ids 需要删除的楼层主键
     * @return 结果
     */
    @Override
    public int deleteFloorByIds(Long[] ids)
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
    public int deleteFloorById(Long id)
    {
        return removeById(id) ? 1 : 0;
    }

    /**
     * 查询所有楼层（负责老人）
     */
    @Override
    public List<Floor> selectAllByNur() {
        return floorMapper.selectAllByNur();
    }

    /**
     * 查询所有有智能设备的楼层
     */
    @Override
    public List<FloorVo> selectAllByDevice() {
        return floorMapper.selectAllByDevice();
    }

    /**
     * 根据床位状态查询楼层树
     *
     * @param status bed status
     * @return tree nodes
     */
    @Override
    public List<FloorRoomBedTreeVo> getRoomAndBedByBedStatus(Integer status) {
       /* //1.1. 调用 mapper 查询数据,
        List<FloorRoomBedVo> rows = floorMapper.selectRoomAndBedByBedStatus(status);
        //1.2. 判断数据是否存在,
        if (rows == null || rows.isEmpty()) {
            //1.3. 如果不存在, 返回空集合,
            return Collections.emptyList();
        }
        //2.key = roomId，value = 房间树节点
        Map<Long, FloorRoomBedTreeVo> floorNodes = new LinkedHashMap<>();
        //3.key = floorId，value = 楼层树节点
        Map<Long, Map<Long, FloorRoomBedTreeVo>> roomNodes = new LinkedHashMap<>();

        for (FloorRoomBedVo row : rows) {
            // 5. 跳过不完整行
            if (row.getFloorId() == null || row.getRoomId() == null || row.getBedId() == null) {
                continue;
            }

            FloorRoomBedTreeVo floorNode = floorNodes.computeIfAbsent(row.getFloorId(), id -> {
                FloorRoomBedTreeVo node = new FloorRoomBedTreeVo(String.valueOf(id), row.getFloorName(), new ArrayList<>());
                return node;
            });

            Map<Long, FloorRoomBedTreeVo> floorRoomMap = roomNodes.computeIfAbsent(row.getFloorId(), id -> new LinkedHashMap<>());
            FloorRoomBedTreeVo roomNode = floorRoomMap.computeIfAbsent(row.getRoomId(), id -> {
                FloorRoomBedTreeVo node = new FloorRoomBedTreeVo(String.valueOf(id), row.getRoomCode(), new ArrayList<>());
                floorNode.getChildren().add(node);
                return node;
            });

            roomNode.getChildren().add(new FloorRoomBedTreeVo(String.valueOf(row.getBedId()), row.getBedNumber(), null));
        }

        return new ArrayList<>(floorNodes.values());*/
        return floorMapper.selectRoomAndBedByBedStatus(status);
    }

}
