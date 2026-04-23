package com.zzyl.hospital.mapper;

import java.util.List;
import com.zzyl.hospital.domain.PatientContact;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * memberMapper接口
 * 
 * @author alexis
 * @date 2026-02-08
 */
@Mapper
public interface PatientContactMapper extends BaseMapper<PatientContact>
{
    /**
     * 查询member
     * 
     * @param id member主键
     * @return member
     */
    public PatientContact selectPatientContactById(Long id);

    /**
     * 查询member列表
     * 
     * @param patientContact member
     * @return member集合
     */
    public List<PatientContact> selectPatientContactList(PatientContact patientContact);

    /**
     * 新增member
     * 
     * @param patientContact member
     * @return 结果
     */
    public int insertPatientContact(PatientContact patientContact);

    /**
     * 修改member
     * 
     * @param patientContact member
     * @return 结果
     */
    public int updatePatientContact(PatientContact patientContact);

    /**
     * 删除member
     * 
     * @param id member主键
     * @return 结果
     */
    public int deletePatientContactById(Long id);

    /**
     * 批量删除member
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePatientContactByIds(Long[] ids);
}
