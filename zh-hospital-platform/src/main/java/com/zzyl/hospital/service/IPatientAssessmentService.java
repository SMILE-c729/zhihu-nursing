package com.zzyl.hospital.service;

import java.util.List;
import com.zzyl.hospital.domain.PatientAssessment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 入院评估Service接口
 * 
 * @author alexis
 * @date 2026-02-06
 */
public interface IPatientAssessmentService extends IService<PatientAssessment>
{
    /**
     * 查询入院评估
     * 
     * @param id 入院评估主键
     * @return 入院评估
     */
    public PatientAssessment selectPatientAssessmentById(Long id);

    /**
     * 查询入院评估列表
     * 
     * @param patientAssessment 入院评估
     * @return 入院评估集合
     */
    public List<PatientAssessment> selectPatientAssessmentList(PatientAssessment patientAssessment);

    /**
     * 新增入院评估
     * 
     * @param patientAssessment 入院评估对象
     * @return 结果
     */
    public Long insertPatientAssessment(PatientAssessment patientAssessment);

    /**
     * 修改入院评估
     * 
     * @param patientAssessment 入院评估
     * @return 结果
     */
    public int updatePatientAssessment(PatientAssessment patientAssessment);

    /**
     * 批量删除入院评估
     * 
     * @param ids 需要删除的入院评估主键集合
     * @return 结果
     */
    public int deletePatientAssessmentByIds(Long[] ids);

    /**
     * 删除入院评估信息
     * 
     * @param id 入院评估主键
     * @return 结果
     */
    public int deletePatientAssessmentById(Long id);
}
