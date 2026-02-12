package com.zzyl.nursing.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.zzyl.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.nursing.mapper.ContractMapper;
import com.zzyl.nursing.domain.Contract;
import com.zzyl.nursing.service.IContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 合同Service业务层处理
 *
 * @author alexis
 * @date 2026-02-01
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements IContractService {
    @Autowired
    private ContractMapper contractMapper;

    /**
     * 查询合同
     *
     * @param id 合同主键
     * @return 合同
     */
    @Override
    public Contract selectContractById(Long id) {
        return getById(id);
    }

    /**
     * 查询合同列表
     *
     * @param contract 合同
     * @return 合同
     */
    @Override
    public List<Contract> selectContractList(Contract contract) {
        return contractMapper.selectContractList(contract);
    }

    /**
     * 新增合同
     *
     * @param contract 合同
     * @return 结果
     */
    @Override
    public int insertContract(Contract contract) {
        return save(contract) ? 1 : 0;
    }

    /**
     * 修改合同
     *
     * @param contract 合同
     * @return 结果
     */
    @Override
    public int updateContract(Contract contract) {
        return updateById(contract) ? 1 : 0;
    }

    /**
     * 批量删除合同
     *
     * @param ids 需要删除的合同主键
     * @return 结果
     */
    @Override
    public int deleteContractByIds(Long[] ids) {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除合同信息
     *
     * @param id 合同主键
     * @return 结果
     */
    @Override
    public int deleteContractById(Long id) {
        return removeById(id) ? 1 : 0;
    }

    /**
     * 定时修改合同状态
     *
     */
    @Override
    public void updateContractStatus() {
        // 合同为未生效状态时，修改状态为已生效，开始时间小于等于当前时间，结束时间大于当前时间
        lambdaUpdate()
                .ge(Contract::getEndDate, LocalDateTime.now())
                .le(Contract::getStartDate, LocalDateTime.now())
                .eq(Contract::getStatus, 0)
                .set(Contract::getStatus, 1)
                // 定时任务无登录用户，手工补齐审计字段（没有传实体，MyBatis‑Plus 的自动填充（MetaObjectHandler.updateFill）不会触发）
                .set(Contract::getUpdateTime, new Date())
                .set(Contract::getUpdateBy, "system")
                .update();
        //合同为已生效状态时，修改状态为已过期，结束时间小于当前时间
        lambdaUpdate()
                .lt(Contract::getEndDate, LocalDateTime.now())
                .eq(Contract::getStatus, 1)
                .set(Contract::getStatus, 2)
                // 定时任务无登录用户，手工补齐审计字段（没有传实体，MyBatis‑Plus 的自动填充（MetaObjectHandler.updateFill）不会触发）
                .set(Contract::getUpdateTime, new Date())
                .set(Contract::getUpdateBy, "system")
                .update();
    }
}
