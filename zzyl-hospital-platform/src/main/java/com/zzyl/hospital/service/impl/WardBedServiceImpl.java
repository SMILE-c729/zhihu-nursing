package com.zzyl.hospital.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyl.hospital.domain.WardBed;
import com.zzyl.hospital.mapper.WardBedMapper;
import com.zzyl.hospital.service.IWardBedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 病床Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-26
 */
@Service
public class WardBedServiceImpl extends ServiceImpl<WardBedMapper, WardBed> implements IWardBedService
{
    @Autowired
    private WardBedMapper wardBedMapper;

    /**
     * 查询病床
     * 
     * @param id 病床主键
     * @return 病床
     */
    @Override
    public WardBed selectWardBedById(Long id)
    {
        return getById(id);
    }

    /**
     * 查询病床列表
     * 
     * @param wardBed 病床
     * @return 病床
     */
    @Override
    public List<WardBed> selectWardBedList(WardBed wardBed)
    {
        return wardBedMapper.selectWardBedList(wardBed);
    }

    /**
     * 新增病床
     * 
     * @param wardBed 病床
     * @return 结果
     */
    @Override
    public int insertWardBed(WardBed wardBed)
    {
        return save(wardBed) ? 1 : 0;
    }

    /**
     * 修改病床
     * 
     * @param wardBed 病床
     * @return 结果
     */
    @Override
    public int updateWardBed(WardBed wardBed)
    {
        return updateById(wardBed) ? 1 : 0;
    }

    /**
     * 批量删除病床
     * 
     * @param ids 需要删除的病床主键
     * @return 结果
     */
    @Override
    public int deleteWardBedByIds(Long[] ids)
    {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除病床信息
     * 
     * @param id 病床主键
     * @return 结果
     */
    @Override
    public int deleteWardBedById(Long id)
    {
        return removeById(id) ? 1 : 0;
    }
}
