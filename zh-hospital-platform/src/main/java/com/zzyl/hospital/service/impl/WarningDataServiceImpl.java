package com.zzyl.hospital.service.impl;

import java.util.Arrays;
import java.util.List;
import com.zzyl.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.hospital.mapper.WarningDataMapper;
import com.zzyl.hospital.domain.WarningData;
import com.zzyl.hospital.service.IWarningDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 预警数据Service业务层处理
 * 
 * @author alexis
 * @date 2026-02-24
 */
@Service
public class WarningDataServiceImpl extends ServiceImpl<WarningDataMapper, WarningData> implements IWarningDataService
{
    @Autowired
    private WarningDataMapper warningDataMapper;

    /**
     * 查询预警数据
     * 
     * @param id 预警数据主键
     * @return 预警数据
     */
    @Override
    public WarningData selectWarningDataById(Long id)
    {
        return getById(id);
    }

    /**
     * 查询预警数据列表
     * 
     * @param warningData 预警数据
     * @return 预警数据
     */
    @Override
    public List<WarningData> selectWarningDataList(WarningData warningData)
    {
        return warningDataMapper.selectWarningDataList(warningData);
    }

    /**
     * 新增预警数据
     * 
     * @param warningData 预警数据
     * @return 结果
     */
    @Override
    public int insertWarningData(WarningData warningData)
    {
        return save(warningData) ? 1 : 0;
    }

    /**
     * 修改预警数据
     * 
     * @param warningData 预警数据
     * @return 结果
     */
    @Override
    public int updateWarningData(WarningData warningData)
    {
        return updateById(warningData) ? 1 : 0;
    }

    /**
     * 批量删除预警数据
     * 
     * @param ids 需要删除的预警数据主键
     * @return 结果
     */
    @Override
    public int deleteWarningDataByIds(Long[] ids)
    {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除预警数据信息
     * 
     * @param id 预警数据主键
     * @return 结果
     */
    @Override
    public int deleteWarningDataById(Long id)
    {
        return removeById(id) ? 1 : 0;
    }
}
