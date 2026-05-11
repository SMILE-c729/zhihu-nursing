package com.zzyl.hospital.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.zzyl.common.exception.base.BaseException;
import com.zzyl.common.utils.CodeGenerator;
import com.zzyl.hospital.domain.*;
import com.zzyl.hospital.dto.AdmissionApplyDto;
import com.zzyl.hospital.dto.AdmissionPatientDto;
import com.zzyl.hospital.mapper.*;
import com.zzyl.hospital.vo.AdmissionConfigVo;
import com.zzyl.hospital.vo.AdmissionDetailVo;
import com.zzyl.hospital.vo.AdmissionPatientVo;
import com.zzyl.hospital.vo.PatientFamilyVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.hospital.service.IAdmissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * 入院Service业务层处理
 *
 * @author alexis
 * @date 2026-02-01
 */
@Service
public class AdmissionServiceImpl extends ServiceImpl<AdmissionMapper, Admission> implements IAdmissionService {
    @Autowired
    private AdmissionMapper admissionMapper;
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private AdmissionContractMapper admissionContractMapper;
    @Autowired
    private AdmissionConfigMapper admissionConfigMapper;
    @Autowired
    private WardBedMapper wardBedMapper;

    /**
     * 查询入院
     *
     * @param id 入院主键
     * @return 入院
     */
    @Override
    public Admission selectAdmissionById(Long id) {
        return getById(id);
    }

    /**
     * 根据入院主键查询入院详情
     *
     * @param id 入院主键
     * @return 返回入院详情
     */
    @Override
    public AdmissionDetailVo selectDetailById(Long id) {
        Admission admission = admissionMapper.selectById(id);
        if (admission == null) {
            throw new BaseException("入院记录不存在");
        }

        AdmissionDetailVo detailVo = new AdmissionDetailVo();

        // 患者信息
        AdmissionPatientVo patientVo = new AdmissionPatientVo();
        Patient patient = patientMapper.selectById(admission.getPatientId());
        if (patient != null) {
            BeanUtils.copyProperties(patient, patientVo);
            patientVo.setAge(calculateAge(patient.getBirthday()));
        } else {
            patientVo.setId(admission.getPatientId());
            patientVo.setName(admission.getPatientName());
            patientVo.setIdCardNo(admission.getIdCardNo());
        }
        detailVo.setAdmissionPatientVo(patientVo);

        // 家属信息
        List<PatientFamilyVo> familyVoList = Collections.emptyList();
        if (StringUtils.isNotBlank(admission.getRemark())) {
            try {
                familyVoList = JSON.parseArray(admission.getRemark(), PatientFamilyVo.class);
            } catch (Exception ignore) {
                // 忽略异常，保障接口正常返回
            }
        }
        detailVo.setPatientFamilyVoList(familyVoList);

        // 入院配置信息
        AdmissionConfigVo admissionConfigVo = new AdmissionConfigVo();
        LambdaQueryWrapper<AdmissionConfig> configWrapper = new LambdaQueryWrapper<>();
        configWrapper.eq(AdmissionConfig::getAdmissionId, admission.getId())
                .orderByDesc(AdmissionConfig::getCreateTime)
                .last("limit 1");
        AdmissionConfig admissionConfig = admissionConfigMapper.selectOne(configWrapper);
        if (admissionConfig != null) {
            BeanUtils.copyProperties(admissionConfig, admissionConfigVo);
        }
        admissionConfigVo.setStartDate(admission.getStartDate());
        admissionConfigVo.setEndDate(admission.getEndDate());
        String wardBedNo = patient != null ? patient.getWardBedNo() : admission.getWardBedNo();
        admissionConfigVo.setWardBedNo(wardBedNo);
        detailVo.setAdmissionConfigVo(admissionConfigVo);

        //  合同信息
        LambdaQueryWrapper<AdmissionContract> admissionContractWrapper = new LambdaQueryWrapper<>();
        admissionContractWrapper.eq(AdmissionContract::getPatientId, admission.getPatientId())
                .orderByDesc(AdmissionContract::getCreateTime)
                .last("limit 1");
        AdmissionContract admissionContract = admissionContractMapper.selectOne(admissionContractWrapper);
        detailVo.setAdmissionContract(admissionContract);

        return detailVo;
    }

    /**
     * 查询入院列表
     *
     * @param admission 入院
     * @return 入院
     */
    @Override
    public List<Admission> selectAdmissionList(Admission admission) {
        return admissionMapper.selectAdmissionList(admission);
    }

    /**
     * 新增入院
     *
     * @param admission 入院
     * @return 结果
     */
    @Override
    public int insertAdmission(Admission admission) {
        return save(admission) ? 1 : 0;
    }

    /**
     * 修改入院
     *
     * @param admission 入院
     * @return 结果
     */
    @Override
    public int updateAdmission(Admission admission) {
        return updateById(admission) ? 1 : 0;
    }

    /**
     * 批量删除入院
     *
     * @param ids 需要删除的入院主键
     * @return 结果
     */
    @Override
    public int deleteAdmissionByIds(Long[] ids) {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除入院信息
     *
     * @param id 入院主键
     * @return 结果
     */
    @Override
    public int deleteAdmissionById(Long id) {
        return removeById(id) ? 1 : 0;
    }

    /**
     * 申请入院
     *
     * @param admissionApplyDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyAdmission(AdmissionApplyDto admissionApplyDto) {
        // 判断患者是否已经入院
        // 通过身份证号查询患者
        LambdaQueryWrapper<Patient> patientQueryWrapper = new LambdaQueryWrapper<>();
        patientQueryWrapper.eq(Patient::getIdCardNo, admissionApplyDto.getAdmissionPatientDto().getIdCardNo());
        patientQueryWrapper.eq(Patient::getStatus, 1);
        Patient patient =  patientMapper.selectOne(patientQueryWrapper);
        if(ObjectUtils.isNotEmpty(patient)) {
            throw new BaseException("患者已入院");
        }

        // 更新病床的状态  已入院
        WardBed wardBed = wardBedMapper.selectById(admissionApplyDto.getAdmissionConfigDto().getWardBedId());
        wardBed.setWardBedStatus(1);
        wardBedMapper.updateById(wardBed);

        // 保存或更新患者数据
        patient = insertOrUpdate(wardBed, admissionApplyDto.getAdmissionPatientDto());

        // 生成合同编号
        String admissionContractNo = "HT" + CodeGenerator.generateContractNumber();

        // 新增签约办理
        insertAdmissionContract(admissionContractNo, patient, admissionApplyDto);

        // 新增入院信息
        Admission admission = insertAdmission(patient, admissionApplyDto);

        // 新增入院配置信息
        insertAdmissionConfig(admission.getId(), admissionApplyDto);
    }

    /**
     * 根据出生日期计算年龄
     *
     * @param birthday 出生日期，格式为yyyy-MM-dd
     * @return 年龄
     */
    private Integer calculateAge(String birthday) {
        if (StringUtils.isBlank(birthday)) {
            return null;
        }
        try {
            //1990-01-01 12:30:45" → "1990-01-01"
            String birthStr = birthday.length() > 10 ? birthday.substring(0, 10) : birthday;
            LocalDate birthDate = LocalDate.parse(birthStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return Period.between(birthDate, LocalDate.now()).getYears();
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 新增入院配置
     * @param admissionApplyDto
     */
    private void insertAdmissionConfig(Long admissionId, AdmissionApplyDto admissionApplyDto) {
        AdmissionConfig admissionConfig = new AdmissionConfig();
        BeanUtils.copyProperties(admissionApplyDto.getAdmissionConfigDto(), admissionConfig);
        admissionConfig.setAdmissionId(admissionId);
        admissionConfigMapper.insert(admissionConfig);
    }

    /**
     * 新增入院信息
     * @param patient
     * @param admissionApplyDto
     */
    private Admission insertAdmission(Patient patient, AdmissionApplyDto admissionApplyDto) {
        Admission admission = new Admission();
        admission.setPatientId(patient.getId());
        admission.setPatientName(patient.getName());
        admission.setIdCardNo(patient.getIdCardNo());
        admission.setCareLevelName(admissionApplyDto.getAdmissionConfigDto().getCareLevelName());
        admission.setStartDate(admissionApplyDto.getAdmissionConfigDto().getStartDate());
        admission.setEndDate(admissionApplyDto.getAdmissionConfigDto().getEndDate());
        admission.setWardBedNo(patient.getWardBedNo());
        admission.setRemark(JSON.toJSONString(admissionApplyDto.getPatientFamilyDtoList()));
        admission.setStatus(0);
        admissionMapper.insert(admission);
        return admission;
    }

    /**
     * 新增合同
     * @param admissionContractNo
     * @param patient
     * @param admissionApplyDto
     */
    private void insertAdmissionContract(String admissionContractNo, Patient patient, AdmissionApplyDto admissionApplyDto) {

        AdmissionContract admissionContract = new AdmissionContract();
        // 属性拷贝
        BeanUtils.copyProperties(admissionApplyDto.getAdmissionContractDto(), admissionContract);
        admissionContract.setAdmissionContractNo(admissionContractNo);
        admissionContract.setPatientId(patient.getId());
        admissionContract.setPatientName(patient.getName());
        // 状态、开始时间、结束时间
        // 签约时间小于等于当前时间，合同生效中
        LocalDateTime admissionStartTime = admissionApplyDto.getAdmissionConfigDto().getStartDate();
        LocalDateTime admissionEndTime = admissionApplyDto.getAdmissionConfigDto().getEndDate();
        Integer status = admissionStartTime.isAfter(LocalDateTime.now()) ? 0 : 1;
        admissionContract.setStatus(status);
        admissionContract.setStartDate(admissionStartTime);
        admissionContract.setEndDate(admissionEndTime);
        admissionContractMapper.insert(admissionContract);
    }

    /**
     * 新增或更新患者
     * @param wardBed
     * @param admissionPatientDto
     * @return
     */
    private Patient insertOrUpdate(WardBed wardBed, AdmissionPatientDto admissionPatientDto) {

        // 准备患者数据
        Patient patient = new Patient();
        // 属性拷贝
        BeanUtils.copyProperties(admissionPatientDto, patient);
        patient.setWardBedNo(wardBed.getWardBedNo());
        patient.setWardBedId(wardBed.getId());
        patient.setStatus(1);
        // 查询患者信息，（身份证号、状态不为1）
        LambdaQueryWrapper<Patient> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Patient::getIdCardNo, admissionPatientDto.getIdCardNo()).ne(Patient::getStatus, 1);
        Patient patientDb =  patientMapper.selectOne(lambdaQueryWrapper);
        if(ObjectUtils.isNotEmpty(patientDb)) {
            // 修改
            patientMapper.updateById(patient);
        }else {
            // 新增
            patientMapper.insert(patient);
        }
        return patient;
    }


}
