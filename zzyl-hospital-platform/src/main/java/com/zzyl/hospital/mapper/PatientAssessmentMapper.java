package com.zzyl.hospital.mapper;

import java.util.List;
import com.zzyl.hospital.domain.PatientAssessment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 入院评估Mapper接口
 * 
 * @author alexis
 * @date 2026-02-06
 */
@Mapper
public interface PatientAssessmentMapper extends BaseMapper<PatientAssessment>
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
     * @param patientAssessment 入院评估
     * @return 结果
     */
    public int insertPatientAssessment(PatientAssessment patientAssessment);

    /**
     * 修改入院评估
     * 
     * @param patientAssessment 入院评估
     * @return 结果
     */
    public int updatePatientAssessment(PatientAssessment patientAssessment);

    /**
     * 删除入院评估
     * 
     * @param id 入院评估主键
     * @return 结果
     */
    public int deletePatientAssessmentById(Long id);

    /**
     * 批量删除入院评估
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePatientAssessmentByIds(Long[] ids);
}
