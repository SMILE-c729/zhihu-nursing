package com.zzyl.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyl.hospital.domain.NursePatient;
import com.zzyl.hospital.dto.NursePatientDto;
import com.zzyl.hospital.mapper.NursePatientMapper;
import com.zzyl.hospital.service.INursePatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 责任护士患者关联Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-28
 */
@Service
public class NursePatientServiceImpl extends ServiceImpl<NursePatientMapper, NursePatient> implements INursePatientService {

    @Autowired
    private NursePatientMapper nursePatientMapper;

    @Override
    public Boolean setNursePatient(List<NursePatientDto> nursePatientDtos) {


//        List<Long> patientIds = nursePatientDtos.stream().map(NursePatientDto::getPatientId).collect(Collectors.toList());
        //重新添加
        List<NursePatient> list = new ArrayList<>();
        List<Long> patientIds = new ArrayList<>();
        nursePatientDtos.forEach(nursePatientDto -> {
            Long patientId = nursePatientDto.getPatientId();
            patientIds.add(patientId);
            nursePatientDto.getNurseIds().forEach(nurseId -> {
                NursePatient nursePatient = new NursePatient();
                nursePatient.setNurseId(nurseId);
                nursePatient.setPatientId(patientId);
                list.add(nursePatient);
            });
        });

        //删除所有的对应的关系
        LambdaQueryWrapper<NursePatient> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(NursePatient::getPatientId, patientIds);
        nursePatientMapper.delete(wrapper);
        //批量新增
        return saveBatch(list);
    }

    /**
     * 查询责任护士患者关联
     *
     * @param id 责任护士患者关联主键
     * @return 责任护士患者关联
     */
    @Override
    public NursePatient selectNursePatientById(Long id) {
        return getById(id);
    }

    /**
     * 查询责任护士患者关联列表
     *
     * @param nursePatient 责任护士患者关联
     * @return 责任护士患者关联
     */
    @Override
    public List<NursePatient> selectNursePatientList(NursePatient nursePatient) {
        return nursePatientMapper.selectNursePatientList(nursePatient);
    }

    /**
     * 新增责任护士患者关联
     *
     * @param nursePatient 责任护士患者关联
     * @return 结果
     */
    @Override
    public int insertNursePatient(NursePatient nursePatient) {
        return save(nursePatient) ? 1 : 0;
    }

    /**
     * 修改责任护士患者关联
     *
     * @param nursePatient 责任护士患者关联
     * @return 结果
     */
    @Override
    public int updateNursePatient(NursePatient nursePatient) {
        return updateById(nursePatient) ? 1 : 0;
    }

    /**
     * 批量删除责任护士患者关联
     *
     * @param ids 需要删除的责任护士患者关联主键
     * @return 结果
     */
    @Override
    public int deleteNursePatientByIds(Long[] ids) {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除责任护士患者关联信息
     *
     * @param id 责任护士患者关联主键
     * @return 结果
     */
    @Override
    public int deleteNursePatientById(Long id) {
        return removeById(id) ? 1 : 0;
    }
}
