package com.zzyl.hospital.service.impl;

import java.util.Arrays;
import java.util.List;
import com.zzyl.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.hospital.mapper.PatientMapper;
import com.zzyl.hospital.domain.Patient;
import com.zzyl.hospital.service.IPatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 患者Service业务层处理
 * 
 * @author alexis
 * @date 2026-02-01
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements IPatientService
{
    @Autowired
    private PatientMapper patientMapper;

    /**
     * 查询患者
     * 
     * @param id 患者主键
     * @return 患者
     */
    @Override
    public Patient selectPatientById(Long id)
    {
        return getById(id);
    }

    /**
     * 查询患者列表
     * 
     * @param patient 患者
     * @return 患者
     */
    @Override
    public List<Patient> selectPatientList(Patient patient)
    {
        return patientMapper.selectPatientList(patient);
    }

    /**
     * 新增患者
     * 
     * @param patient 患者
     * @return 结果
     */
    @Override
    public int insertPatient(Patient patient)
    {
        return save(patient) ? 1 : 0;
    }

    /**
     * 修改患者
     * 
     * @param patient 患者
     * @return 结果
     */
    @Override
    public int updatePatient(Patient patient)
    {
        return updateById(patient) ? 1 : 0;
    }

    /**
     * 批量删除患者
     * 
     * @param ids 需要删除的患者主键
     * @return 结果
     */
    @Override
    public int deletePatientByIds(Long[] ids)
    {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除患者信息
     * 
     * @param id 患者主键
     * @return 结果
     */
    @Override
    public int deletePatientById(Long id)
    {
        return removeById(id) ? 1 : 0;
    }
}
