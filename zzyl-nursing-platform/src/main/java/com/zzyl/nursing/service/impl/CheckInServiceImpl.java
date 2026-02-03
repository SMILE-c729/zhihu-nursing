package com.zzyl.nursing.service.impl;

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
import com.zzyl.nursing.domain.*;
import com.zzyl.nursing.dto.CheckInApplyDto;
import com.zzyl.nursing.dto.CheckInElderDto;
import com.zzyl.nursing.mapper.*;
import com.zzyl.nursing.vo.CheckInConfigVo;
import com.zzyl.nursing.vo.CheckInDetailVo;
import com.zzyl.nursing.vo.CheckInElderVo;
import com.zzyl.nursing.vo.ElderFamilyVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.nursing.service.ICheckInService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * 入住Service业务层处理
 *
 * @author alexis
 * @date 2026-02-01
 */
@Service
public class CheckInServiceImpl extends ServiceImpl<CheckInMapper, CheckIn> implements ICheckInService {
    @Autowired
    private CheckInMapper checkInMapper;
    @Autowired
    private ElderMapper elderMapper;
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private CheckInConfigMapper checkInConfigMapper;
    @Autowired
    private BedMapper bedMapper;

    /**
     * 查询入住
     *
     * @param id 入住主键
     * @return 入住
     */
    @Override
    public CheckIn selectCheckInById(Long id) {
        return getById(id);
    }

    /**
     * 根据入住主键查询入住详情
     *
     * @param id 入住主键
     * @return 返回入住详情
     */
    @Override
    public CheckInDetailVo selectDetailById(Long id) {
        CheckIn checkIn = checkInMapper.selectById(id);
        if (checkIn == null) {
            throw new BaseException("入住记录不存在");
        }

        CheckInDetailVo detailVo = new CheckInDetailVo();

        // 老人信息
        CheckInElderVo elderVo = new CheckInElderVo();
        Elder elder = elderMapper.selectById(checkIn.getElderId());
        if (elder != null) {
            BeanUtils.copyProperties(elder, elderVo);
            elderVo.setAge(calculateAge(elder.getBirthday()));
        } else {
            elderVo.setId(checkIn.getElderId());
            elderVo.setName(checkIn.getElderName());
            elderVo.setIdCardNo(checkIn.getIdCardNo());
        }
        detailVo.setCheckInElderVo(elderVo);

        // 家属信息
        List<ElderFamilyVo> familyVoList = Collections.emptyList();
        if (StringUtils.isNotBlank(checkIn.getRemark())) {
            try {
                familyVoList = JSON.parseArray(checkIn.getRemark(), ElderFamilyVo.class);
            } catch (Exception ignore) {
                // 忽略异常，保障接口正常返回
            }
        }
        detailVo.setElderFamilyVoList(familyVoList);

        // 入住配置信息
        CheckInConfigVo checkInConfigVo = new CheckInConfigVo();
        LambdaQueryWrapper<CheckInConfig> configWrapper = new LambdaQueryWrapper<>();
        configWrapper.eq(CheckInConfig::getCheckInId, checkIn.getId())
                .orderByDesc(CheckInConfig::getCreateTime)
                .last("limit 1");
        CheckInConfig checkInConfig = checkInConfigMapper.selectOne(configWrapper);
        if (checkInConfig != null) {
            BeanUtils.copyProperties(checkInConfig, checkInConfigVo);
        }
        checkInConfigVo.setStartDate(checkIn.getStartDate());
        checkInConfigVo.setEndDate(checkIn.getEndDate());
        String bedNumber = elder != null ? elder.getBedNumber() : checkIn.getBedNumber();
        checkInConfigVo.setBedNumber(bedNumber);
        detailVo.setCheckInConfigVo(checkInConfigVo);

        //  合同信息
        LambdaQueryWrapper<Contract> contractWrapper = new LambdaQueryWrapper<>();
        contractWrapper.eq(Contract::getElderId, checkIn.getElderId())
                .orderByDesc(Contract::getCreateTime)
                .last("limit 1");
        Contract contract = contractMapper.selectOne(contractWrapper);
        detailVo.setContract(contract);

        return detailVo;
    }

    /**
     * 查询入住列表
     *
     * @param checkIn 入住
     * @return 入住
     */
    @Override
    public List<CheckIn> selectCheckInList(CheckIn checkIn) {
        return checkInMapper.selectCheckInList(checkIn);
    }

    /**
     * 新增入住
     *
     * @param checkIn 入住
     * @return 结果
     */
    @Override
    public int insertCheckIn(CheckIn checkIn) {
        return save(checkIn) ? 1 : 0;
    }

    /**
     * 修改入住
     *
     * @param checkIn 入住
     * @return 结果
     */
    @Override
    public int updateCheckIn(CheckIn checkIn) {
        return updateById(checkIn) ? 1 : 0;
    }

    /**
     * 批量删除入住
     *
     * @param ids 需要删除的入住主键
     * @return 结果
     */
    @Override
    public int deleteCheckInByIds(Long[] ids) {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除入住信息
     *
     * @param id 入住主键
     * @return 结果
     */
    @Override
    public int deleteCheckInById(Long id) {
        return removeById(id) ? 1 : 0;
    }

    /**
     * 申请入住
     *
     * @param checkInApplyDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyCheckIn(CheckInApplyDto checkInApplyDto) {
        // 判断老人是否已经入住
        // 通过身份证号查询老人
        LambdaQueryWrapper<Elder> elderQueryWrapper = new LambdaQueryWrapper<>();
        elderQueryWrapper.eq(Elder::getIdCardNo, checkInApplyDto.getCheckInElderDto().getIdCardNo());
        elderQueryWrapper.eq(Elder::getStatus, 1);
        Elder elder =  elderMapper.selectOne(elderQueryWrapper);
        if(ObjectUtils.isNotEmpty(elder)) {
            throw new BaseException("老人已入住");
        }

        // 更新床位的状态  已入住
        Bed bed = bedMapper.selectById(checkInApplyDto.getCheckInConfigDto().getBedId());
        bed.setBedStatus(1);
        bedMapper.updateById(bed);

        // 保存或更新老人数据
        elder = insertOrUpdate(bed, checkInApplyDto.getCheckInElderDto());

        // 生成合同编号
        String contractNo = "HT" + CodeGenerator.generateContractNumber();

        // 新增签约办理
        insertContract(contractNo, elder, checkInApplyDto);

        // 新增入住信息
        CheckIn checkIn = insertCheckIn(elder, checkInApplyDto);

        // 新增入住配置信息
        insertCheckInConfig(checkIn.getId(), checkInApplyDto);
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
     * 新增入住配置
     * @param checkInApplyDto
     */
    private void insertCheckInConfig(Long checkInId, CheckInApplyDto checkInApplyDto) {
        CheckInConfig checkInConfig = new CheckInConfig();
        BeanUtils.copyProperties(checkInApplyDto.getCheckInConfigDto(), checkInConfig);
        checkInConfig.setCheckInId(checkInId);
        checkInConfigMapper.insert(checkInConfig);
    }

    /**
     * 新增入住信息
     * @param elder
     * @param checkInApplyDto
     */
    private CheckIn insertCheckIn(Elder elder, CheckInApplyDto checkInApplyDto) {
        CheckIn checkIn = new CheckIn();
        checkIn.setElderId(elder.getId());
        checkIn.setElderName(elder.getName());
        checkIn.setIdCardNo(elder.getIdCardNo());
        checkIn.setNursingLevelName(checkInApplyDto.getCheckInConfigDto().getNursingLevelName());
        checkIn.setStartDate(checkInApplyDto.getCheckInConfigDto().getStartDate());
        checkIn.setEndDate(checkInApplyDto.getCheckInConfigDto().getEndDate());
        checkIn.setBedNumber(elder.getBedNumber());
        checkIn.setRemark(JSON.toJSONString(checkInApplyDto.getElderFamilyDtoList()));
        checkIn.setStatus(0);
        checkInMapper.insert(checkIn);
        return checkIn;
    }

    /**
     * 新增合同
     * @param contractNo
     * @param elder
     * @param checkInApplyDto
     */
    private void insertContract(String contractNo, Elder elder, CheckInApplyDto checkInApplyDto) {

        Contract contract = new Contract();
        // 属性拷贝
        BeanUtils.copyProperties(checkInApplyDto.getCheckInContractDto(), contract);
        contract.setContractNumber(contractNo);
        contract.setElderId(elder.getId());
        contract.setElderName(elder.getName());
        // 状态、开始时间、结束时间
        // 签约时间小于等于当前时间，合同生效中
        LocalDateTime checkInStartTime = checkInApplyDto.getCheckInConfigDto().getStartDate();
        LocalDateTime checkInEndTime = checkInApplyDto.getCheckInConfigDto().getEndDate();
        Integer status = checkInStartTime.isAfter(LocalDateTime.now()) ? 0 : 1;
        contract.setStatus(status);
        contract.setStartDate(checkInStartTime);
        contract.setEndDate(checkInEndTime);
        contractMapper.insert(contract);
    }

    /**
     * 新增或更新老人
     * @param bed
     * @param checkInElderDto
     * @return
     */
    private Elder insertOrUpdate(Bed bed, CheckInElderDto checkInElderDto) {

        // 准备老人数据
        Elder elder = new Elder();
        // 属性拷贝
        BeanUtils.copyProperties(checkInElderDto, elder);
        elder.setBedNumber(bed.getBedNumber());
        elder.setBedId(bed.getId());
        elder.setStatus(1);
        // 查询老人信息，（身份证号、状态不为1）
        LambdaQueryWrapper<Elder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Elder::getIdCardNo, checkInElderDto.getIdCardNo()).ne(Elder::getStatus, 1);
        Elder elderDb =  elderMapper.selectOne(lambdaQueryWrapper);
        if(ObjectUtils.isNotEmpty(elderDb)) {
            // 修改
            elderMapper.updateById(elder);
        }else {
            // 新增
            elderMapper.insert(elder);
        }
        return elder;
    }


}
