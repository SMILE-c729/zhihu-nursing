package com.zzyl.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.hospital.domain.WardBed;

import java.util.List;

/**
 * 病床Service接口
 * 
 * @author ruoyi
 * @date 2024-04-26
 */
public interface IWardBedService extends IService<WardBed>
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
     * 批量删除病床
     * 
     * @param ids 需要删除的病床主键集合
     * @return 结果
     */
    public int deleteWardBedByIds(Long[] ids);

    /**
     * 删除病床信息
     * 
     * @param id 病床主键
     * @return 结果
     */
    public int deleteWardBedById(Long id);
}
