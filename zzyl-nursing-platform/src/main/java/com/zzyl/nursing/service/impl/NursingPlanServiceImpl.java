package com.zzyl.nursing.service.impl;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzyl.common.constant.CacheConstants;
import com.zzyl.common.core.redis.RedisCache;
import com.zzyl.common.utils.DateUtils;
import com.zzyl.common.utils.bean.BeanUtils;
import com.zzyl.nursing.domain.NursingProjectPlan;
import com.zzyl.nursing.dto.NursingPlanDto;
import com.zzyl.nursing.mapper.NursingProjectPlanMapper;
import com.zzyl.nursing.vo.NursingPlanVo;
import com.zzyl.nursing.vo.NursingProjectPlanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.nursing.mapper.NursingPlanMapper;
import com.zzyl.nursing.domain.NursingPlan;
import com.zzyl.nursing.service.INursingPlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * 护理计划Service业务层处理
 * 
 * @author alexis
 * @date 2025-06-02
 */
@Service
public class NursingPlanServiceImpl extends ServiceImpl<NursingPlanMapper, NursingPlan> implements INursingPlanService
{
    @Autowired
    private NursingPlanMapper nursingPlanMapper;

    @Autowired
    private NursingProjectPlanMapper nursingProjectPlanMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 护理计划缓存key
     */
    private static final String NURSING_PLAN_CACHE_KEY = CacheConstants.NURSING_PLAN_KEY + "all";

    /**
     * 查询护理计划
     * 
     * @param id 护理计划主键
     * @return 护理计划
     */
    @Override
    public NursingPlanVo selectNursingPlanById(Long id)
    {
        // 查询护理计划基本信息
        NursingPlan nursingPlan = nursingPlanMapper.selectById(id);

        // 查询护理计划关联的护理项目集合
        List<NursingProjectPlanVo> projectPlans = nursingProjectPlanMapper.selectByNursingPlanId(id);

        // 将两部分信息合并到一个对象中返回
        NursingPlanVo nursingPlanVo = new NursingPlanVo();

        BeanUtils.copyProperties(nursingPlan, nursingPlanVo);
        nursingPlanVo.setProjectPlans(projectPlans);

        return nursingPlanVo;
    }

    /**
     * 查询护理计划列表
     * 
     * @param nursingPlan 护理计划
     * @return 护理计划
     */
    @Override
    public List<NursingPlan> selectNursingPlanList(NursingPlan nursingPlan)
    {
        return nursingPlanMapper.selectNursingPlanList(nursingPlan);
    }

    /**
     * 新增护理计划
     * 
     * @param dto 护理计划
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertNursingPlan(NursingPlanDto dto)
    {
        // 1.保存护理计划
        NursingPlan nursingPlan = new NursingPlan();
        BeanUtils.copyProperties(dto, nursingPlan);
        nursingPlan.setCreateTime(DateUtils.getNowDate());

        nursingPlanMapper.insert(nursingPlan);

        // 2.批量保存护理计划和护理项目的对应关系
        int count = nursingProjectPlanMapper.batchInsert(dto.getProjectPlans(), nursingPlan.getId());
            // 清除护理计划缓存
            clearNursingPlanCache();

        return count == 0 ? 0 : 1;
    }

    /**
     * 修改护理计划
     * 
     * @param dto 护理计划
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateNursingPlan(NursingPlanDto dto)
    {
        // 判断dto中的护理项目是否为空，如果不为空，先删除护理计划关联的所有护理项目，再重新批量保存最新的关联
        if (dto.getProjectPlans() != null && !dto.getProjectPlans().isEmpty()) {
            // 删除护理计划对应的护理项目列表
            nursingProjectPlanMapper.deleteByNursingPlanId(dto.getId());

            // 批量保存护理计划关联的护理项目
            nursingProjectPlanMapper.batchInsert(dto.getProjectPlans(), dto.getId());
        }

        NursingPlan nursingPlan = new NursingPlan();
        BeanUtils.copyProperties(dto, nursingPlan);

        // 修改护理计划
        int result = nursingPlanMapper.updateById(nursingPlan);
        
        // 清除护理计划缓存
        clearNursingPlanCache();
        
        return result;
    }

    /**
     * 批量删除护理计划
     * 
     * @param ids 需要删除的护理计划主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteNursingPlanByIds(Long[] ids)
    {
        // 先删除关联的护理项目
        for (Long id : ids) {
            nursingProjectPlanMapper.deleteByNursingPlanId(id);
        }
        
        int result = removeByIds(Arrays.asList(ids)) ? 1 : 0;
        
        // 清除护理计划缓存
        clearNursingPlanCache();
        
        return result;
    }

    /**
     * 删除护理计划信息
     * 
     * @param id 护理计划主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteNursingPlanById(Long id)
    {
        // 删除护理计划关联的护理项目
        nursingProjectPlanMapper.deleteByNursingPlanId(id);
        // 删除护理计划
        int result = removeById(id) ? 1 : 0;
        
        // 清除护理计划缓存
        clearNursingPlanCache();
        
        return result;
    }

    /**
     * 查询所有护理计划
     *
     * @return 护理计划列表
     */
    @Override
    public List<NursingPlan> getAllNursingPlans() {
        // 先从缓存中获取
        List<NursingPlan> cachedPlans = redisCache.getCacheObject(NURSING_PLAN_CACHE_KEY);
        if (cachedPlans != null && !cachedPlans.isEmpty()) {
            return cachedPlans;
        }
        
        // 缓存未命中，从数据库查询
        LambdaQueryWrapper<NursingPlan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(NursingPlan::getStatus, 1);
        List<NursingPlan> plans = list(queryWrapper);
        
        // 将结果存入缓存，设置30分钟过期时间
        redisCache.setCacheObject(NURSING_PLAN_CACHE_KEY, plans);
        
        return plans;
    }
    
    /**
     * 清除护理计划缓存
     */
    private void clearNursingPlanCache() {
        redisCache.deleteObject(NURSING_PLAN_CACHE_KEY);
    }
}
