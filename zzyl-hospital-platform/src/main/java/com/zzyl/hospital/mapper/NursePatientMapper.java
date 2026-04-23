package com.zzyl.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyl.common.core.domain.entity.SysUser;
import com.zzyl.hospital.domain.NursePatient;
import com.zzyl.hospital.vo.MedicalOrderNameVo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * 责任护士患者关联Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-28
 */
@Mapper
public interface NursePatientMapper extends BaseMapper<NursePatient>
{

    @Select("select tu.user_id as userId, tu.nick_name from nurse_patient ne, sys_user tu where ne.patient_id = #{patientId} and tu.user_id = ne.nurse_id and tu.status = 0")
    List<SysUser> selectUserByPatientId(Long patientId);

    @Select("")
    List<MedicalOrderNameVo> selectNickNameByPatientId(@Param("set") Set<Long> patientIds);

    /**
     * 查询责任护士患者关联
     *
     * @param id 责任护士患者关联主键
     * @return 责任护士患者关联
     */
    @Select("")
    public NursePatient selectNursePatientById(Long id);

    /**
     * 查询责任护士患者关联列表
     *
     * @param nursePatient 责任护士患者关联
     * @return 责任护士患者关联集合
     */
    @Select("")
    public List<NursePatient> selectNursePatientList(NursePatient nursePatient);

    /**
     * 新增责任护士患者关联
     *
     * @param nursePatient 责任护士患者关联
     * @return 结果
     */
    @Insert("")
    public int insertNursePatient(NursePatient nursePatient);

    /**
     * 修改责任护士患者关联
     *
     * @param nursePatient 责任护士患者关联
     * @return 结果
     */
    @Update("")
    public int updateNursePatient(NursePatient nursePatient);

    /**
     * 删除责任护士患者关联
     *
     * @param id 责任护士患者关联主键
     * @return 结果
     */
    @Delete("")
    public int deleteNursePatientById(Long id);

    /**
     * 批量删除责任护士患者关联
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    @Delete("")
    public int deleteNursePatientByIds(Long[] ids);
}
