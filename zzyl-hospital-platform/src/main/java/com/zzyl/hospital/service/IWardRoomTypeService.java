package com.zzyl.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.hospital.domain.WardRoomType;

import java.util.List;

/**
 * 病房类型Service接口
 * 
 * @author ruoyi
 * @date 2024-04-26
 */
public interface IWardRoomTypeService extends IService<WardRoomType>
{
    /**
     * 查询病房类型
     * 
     * @param id 病房类型主键
     * @return 病房类型
     */
    public WardRoomType selectWardRoomTypeById(Long id);

    /**
     * 查询病房类型列表
     * 
     * @param wardRoomType 病房类型
     * @return 病房类型集合
     */
    public List<WardRoomType> selectWardRoomTypeList(WardRoomType wardRoomType);

    /**
     * 新增病房类型
     * 
     * @param wardRoomType 病房类型
     * @return 结果
     */
    public int insertWardRoomType(WardRoomType wardRoomType);

    /**
     * 修改病房类型
     * 
     * @param wardRoomType 病房类型
     * @return 结果
     */
    public int updateWardRoomType(WardRoomType wardRoomType);

    /**
     * 批量删除病房类型
     * 
     * @param ids 需要删除的病房类型主键集合
     * @return 结果
     */
    public int deleteWardRoomTypeByIds(Long[] ids);

    /**
     * 删除病房类型信息
     * 
     * @param id 病房类型主键
     * @return 结果
     */
    public int deleteWardRoomTypeById(Long id);

    /**
     * 按照状态查询房间类型
     * @param status
     * @return
     */
    List<WardRoomType> findWardRoomTypeListByStatus(Integer status);
}
