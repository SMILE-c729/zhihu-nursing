package com.zzyl.hospital.mapper;

import java.util.List;
import com.zzyl.hospital.domain.AdmissionConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 入院配置表Mapper接口
 * 
 * @author alexis
 * @date 2026-02-01
 */
@Mapper
public interface AdmissionConfigMapper extends BaseMapper<AdmissionConfig>
{
    /**
     * 查询入院配置表
     * 
     * @param id 入院配置表主键
     * @return 入院配置表
     */
    public AdmissionConfig selectAdmissionConfigById(Long id);

    /**
     * 查询入院配置表列表
     * 
     * @param admissionConfig 入院配置表
     * @return 入院配置表集合
     */
    public List<AdmissionConfig> selectAdmissionConfigList(AdmissionConfig admissionConfig);

    /**
     * 新增入院配置表
     * 
     * @param admissionConfig 入院配置表
     * @return 结果
     */
    public int insertAdmissionConfig(AdmissionConfig admissionConfig);

    /**
     * 修改入院配置表
     * 
     * @param admissionConfig 入院配置表
     * @return 结果
     */
    public int updateAdmissionConfig(AdmissionConfig admissionConfig);

    /**
     * 删除入院配置表
     * 
     * @param id 入院配置表主键
     * @return 结果
     */
    public int deleteAdmissionConfigById(Long id);

    /**
     * 批量删除入院配置表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdmissionConfigByIds(Long[] ids);
}
