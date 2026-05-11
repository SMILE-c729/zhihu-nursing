package com.zzyl.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyl.hospital.domain.WardRoomType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 病房类型Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-26
 */
@Mapper
public interface WardRoomTypeMapper extends BaseMapper<WardRoomType>
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
     * 删除病房类型
     * 
     * @param id 病房类型主键
     * @return 结果
     */
    public int deleteWardRoomTypeById(Long id);

    /**
     * 批量删除病房类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWardRoomTypeByIds(Long[] ids);
}
