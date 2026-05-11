package com.zzyl.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.hospital.domain.NursePatient;
import com.zzyl.hospital.dto.NursePatientDto;

import java.util.List;

/**
 * 责任护士患者关联Service接口
 *
 * @author ruoyi
 * @date 2024-05-28
 */
public interface INursePatientService extends IService<NursePatient>
{
    /**
     * 查询责任护士患者关联
     *
     * @param id 责任护士患者关联主键
     * @return 责任护士患者关联
     */
    public NursePatient selectNursePatientById(Long id);

    /**
     * 查询责任护士患者关联列表
     *
     * @param nursePatient 责任护士患者关联
     * @return 责任护士患者关联集合
     */
    public List<NursePatient> selectNursePatientList(NursePatient nursePatient);

    /**
     * 新增责任护士患者关联
     *
     * @param nursePatient 责任护士患者关联
     * @return 结果
     */
    public int insertNursePatient(NursePatient nursePatient);

    /**
     * 修改责任护士患者关联
     *
     * @param nursePatient 责任护士患者关联
     * @return 结果
     */
    public int updateNursePatient(NursePatient nursePatient);

    /**
     * 批量删除责任护士患者关联
     *
     * @param ids 需要删除的责任护士患者关联主键集合
     * @return 结果
     */
    public int deleteNursePatientByIds(Long[] ids);

    /**
     * 删除责任护士患者关联信息
     *
     * @param id 责任护士患者关联主键
     * @return 结果
     */
    public int deleteNursePatientById(Long id);

    Boolean setNursePatient(List<NursePatientDto> nursePatientDtos);

}
