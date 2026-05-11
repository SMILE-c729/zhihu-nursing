package com.zzyl.hospital.service;

import java.util.List;
import com.zzyl.hospital.domain.AdmissionContract;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 合同Service接口
 * 
 * @author alexis
 * @date 2026-02-01
 */
public interface IAdmissionContractService extends IService<AdmissionContract>
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
     * 批量删除合同
     * 
     * @param ids 需要删除的合同主键集合
     * @return 结果
     */
    public int deleteAdmissionContractByIds(Long[] ids);

    /**
     * 删除合同信息
     * 
     * @param id 合同主键
     * @return 结果
     */
    public int deleteAdmissionContractById(Long id);
    /*
     * 定时修改合同状态
     *
     * @param admissionContract 合同
     * @return 合同集合
     */
    public void updateAdmissionContractStatus();
}
