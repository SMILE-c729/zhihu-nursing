package com.zzyl.hospital.mapper;

import java.util.List;
import com.zzyl.hospital.domain.AdmissionContract;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 合同Mapper接口
 * 
 * @author alexis
 * @date 2026-02-01
 */
@Mapper
public interface AdmissionContractMapper extends BaseMapper<AdmissionContract>
{
    /**
     * 查询合同
     * 
     * @param id 合同主键
     * @return 合同
     */
    public AdmissionContract selectAdmissionContractById(Long id);

    /**
     * 查询合同列表
     * 
     * @param admissionContract 合同
     * @return 合同集合
     */
    public List<AdmissionContract> selectAdmissionContractList(AdmissionContract admissionContract);

    /**
     * 新增合同
     * 
     * @param admissionContract 合同
     * @return 结果
     */
    public int insertAdmissionContract(AdmissionContract admissionContract);

    /**
     * 修改合同
     * 
     * @param admissionContract 合同
     * @return 结果
     */
    public int updateAdmissionContract(AdmissionContract admissionContract);

    /**
     * 删除合同
     * 
     * @param id 合同主键
     * @return 结果
     */
    public int deleteAdmissionContractById(Long id);

    /**
     * 批量删除合同
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdmissionContractByIds(Long[] ids);
}
