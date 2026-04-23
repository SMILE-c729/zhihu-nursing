package com.zzyl.hospital.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.zzyl.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.hospital.mapper.AdmissionContractMapper;
import com.zzyl.hospital.domain.AdmissionContract;
import com.zzyl.hospital.service.IAdmissionContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 合同Service业务层处理
 *
 * @author alexis
 * @date 2026-02-01
 */
@Service
public class AdmissionContractServiceImpl extends ServiceImpl<AdmissionContractMapper, AdmissionContract> implements IAdmissionContractService {
    @Autowired
    private AdmissionContractMapper admissionContractMapper;

    /**
     * 查询合同
     *
     * @param id 合同主键
     * @return 合同
     */
    @Override
    public AdmissionContract selectAdmissionContractById(Long id) {
        return getById(id);
    }

    /**
     * 查询合同列表
     *
     * @param admissionContract 合同
     * @return 合同
     */
    @Override
    public List<AdmissionContract> selectAdmissionContractList(AdmissionContract admissionContract) {
        return admissionContractMapper.selectAdmissionContractList(admissionContract);
    }

    /**
     * 新增合同
     *
     * @param admissionContract 合同
     * @return 结果
     */
    @Override
    public int insertAdmissionContract(AdmissionContract admissionContract) {
        return save(admissionContract) ? 1 : 0;
    }

    /**
     * 修改合同
     *
     * @param admissionContract 合同
     * @return 结果
     */
    @Override
    public int updateAdmissionContract(AdmissionContract admissionContract) {
        return updateById(admissionContract) ? 1 : 0;
    }

    /**
     * 批量删除合同
     *
     * @param ids 需要删除的合同主键
     * @return 结果
     */
    @Override
    public int deleteAdmissionContractByIds(Long[] ids) {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除合同信息
     *
     * @param id 合同主键
     * @return 结果
     */
    @Override
    public int deleteAdmissionContractById(Long id) {
        return removeById(id) ? 1 : 0;
    }

    /**
     * 定时修改合同状态
     *
     */
    @Override
    public void updateAdmissionContractStatus() {
        // 合同为未生效状态时，修改状态为已生效，开始时间小于等于当前时间，结束时间大于当前时间
        lambdaUpdate()
                .ge(AdmissionContract::getEndDate, LocalDateTime.now())
                .le(AdmissionContract::getStartDate, LocalDateTime.now())
                .eq(AdmissionContract::getStatus, 0)
                .set(AdmissionContract::getStatus, 1)
                // 定时任务无登录用户，手工补齐审计字段（没有传实体，MyBatis‑Plus 的自动填充（MetaObjectHandler.updateFill）不会触发）
                .set(AdmissionContract::getUpdateTime, new Date())
                .set(AdmissionContract::getUpdateBy, "system")
                .update();
        //合同为已生效状态时，修改状态为已过期，结束时间小于当前时间
        lambdaUpdate()
                .lt(AdmissionContract::getEndDate, LocalDateTime.now())
                .eq(AdmissionContract::getStatus, 1)
                .set(AdmissionContract::getStatus, 2)
                // 定时任务无登录用户，手工补齐审计字段（没有传实体，MyBatis‑Plus 的自动填充（MetaObjectHandler.updateFill）不会触发）
                .set(AdmissionContract::getUpdateTime, new Date())
                .set(AdmissionContract::getUpdateBy, "system")
                .update();
    }
}
