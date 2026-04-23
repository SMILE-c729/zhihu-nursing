package com.zzyl.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyl.hospital.domain.WardBed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 病床Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-26
 */
@Mapper
public interface WardBedMapper extends BaseMapper<WardBed>
{
    /**
     * 查询病床
     * 
     * @param id 病床主键
     * @return 病床
     */
    public WardBed selectWardBedById(Long id);

    /**
     * 查询病床列表
     * 
     * @param wardBed 病床
     * @return 病床集合
     */
    public List<WardBed> selectWardBedList(WardBed wardBed);

    /**
     * 新增病床
     * 
     * @param wardBed 病床
     * @return 结果
     */
    public int insertWardBed(WardBed wardBed);

    /**
     * 修改病床
     * 
     * @param wardBed 病床
     * @return 结果
     */
    public int updateWardBed(WardBed wardBed);

    /**
     * 删除病床
     * 
     * @param id 病床主键
     * @return 结果
     */
    public int deleteWardBedById(Long id);

    /**
     * 批量删除病床
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWardBedByIds(Long[] ids);
}
