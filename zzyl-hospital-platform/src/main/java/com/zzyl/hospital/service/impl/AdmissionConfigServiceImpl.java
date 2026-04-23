package com.zzyl.hospital.service.impl;

import java.util.Arrays;
import java.util.List;
import com.zzyl.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.hospital.mapper.AdmissionConfigMapper;
import com.zzyl.hospital.domain.AdmissionConfig;
import com.zzyl.hospital.service.IAdmissionConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 入院配置表Service业务层处理
 * 
 * @author alexis
 * @date 2026-02-01
 */
@Service
public class AdmissionConfigServiceImpl extends ServiceImpl<AdmissionConfigMapper, AdmissionConfig> implements IAdmissionConfigService
{
    @Autowired
    private AdmissionConfigMapper admissionConfigMapper;

    /**
     * 查询入院配置表
     * 
     * @param id 入院配置表主键
     * @return 入院配置表
     */
    @Override
    public AdmissionConfig selectAdmissionConfigById(Long id)
    {
        return getById(id);
    }

    /**
     * 查询入院配置表列表
     * 
     * @param admissionConfig 入院配置表
     * @return 入院配置表
     */
    @Override
    public List<AdmissionConfig> selectAdmissionConfigList(AdmissionConfig admissionConfig)
    {
        return admissionConfigMapper.selectAdmissionConfigList(admissionConfig);
    }

    /**
     * 新增入院配置表
     * 
     * @param admissionConfig 入院配置表
     * @return 结果
     */
    @Override
    public int insertAdmissionConfig(AdmissionConfig admissionConfig)
    {
        return save(admissionConfig) ? 1 : 0;
    }

    /**
     * 修改入院配置表
     * 
     * @param admissionConfig 入院配置表
     * @return 结果
     */
    @Override
    public int updateAdmissionConfig(AdmissionConfig admissionConfig)
    {
        return updateById(admissionConfig) ? 1 : 0;
    }

    /**
     * 批量删除入院配置表
     * 
     * @param ids 需要删除的入院配置表主键
     * @return 结果
     */
    @Override
    public int deleteAdmissionConfigByIds(Long[] ids)
    {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除入院配置表信息
     * 
     * @param id 入院配置表主键
     * @return 结果
     */
    @Override
    public int deleteAdmissionConfigById(Long id)
    {
        return removeById(id) ? 1 : 0;
    }
}
