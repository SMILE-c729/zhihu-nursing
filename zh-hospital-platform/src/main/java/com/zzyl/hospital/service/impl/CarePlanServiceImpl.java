package com.zzyl.hospital.service.impl;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzyl.common.constant.CacheConstants;
import com.zzyl.common.core.redis.RedisCache;
import com.zzyl.common.utils.DateUtils;
import com.zzyl.common.utils.bean.BeanUtils;
import com.zzyl.hospital.domain.CarePlanOrderItem;
import com.zzyl.hospital.dto.CarePlanDto;
import com.zzyl.hospital.mapper.CarePlanOrderItemMapper;
import com.zzyl.hospital.vo.CarePlanVo;
import com.zzyl.hospital.vo.CarePlanOrderItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzyl.hospital.mapper.CarePlanMapper;
import com.zzyl.hospital.domain.CarePlan;
import com.zzyl.hospital.service.ICarePlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * 照护方案Service业务层处理
 * 
 * @author alexis
 * @date 2025-06-02
 */
@Service
public class CarePlanServiceImpl extends ServiceImpl<CarePlanMapper, CarePlan> implements ICarePlanService
{
    @Autowired
    private CarePlanMapper carePlanMapper;

    @Autowired
    private CarePlanOrderItemMapper carePlanOrderItemMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 照护方案缓存key
     */
    private static final String CARE_PLAN_CACHE_KEY = CacheConstants.CARE_PLAN_KEY + "all";

    /**
     * 查询照护方案
     * 
     * @param id 照护方案主键
     * @return 照护方案
     */
    @Override
    public CarePlanVo selectCarePlanById(Long id)
    {
        // 查询照护方案基本信息
        CarePlan carePlan = carePlanMapper.selectById(id);

        // 查询照护方案关联的医嘱项目集合
        List<CarePlanOrderItemVo> projectPlans = carePlanOrderItemMapper.selectByCarePlanId(id);

        // 将两部分信息合并到一个对象中返回
        CarePlanVo carePlanVo = new CarePlanVo();

        BeanUtils.copyProperties(carePlan, carePlanVo);
        carePlanVo.setProjectPlans(projectPlans);

        return carePlanVo;
    }

    /**
     * 查询照护方案列表
     * 
     * @param carePlan 照护方案
     * @return 照护方案
     */
    @Override
    public List<CarePlan> selectCarePlanList(CarePlan carePlan)
    {
        return carePlanMapper.selectCarePlanList(carePlan);
    }

    /**
     * 新增照护方案
     * 
     * @param dto 照护方案
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertCarePlan(CarePlanDto dto)
    {
        // 1.保存照护方案
        CarePlan carePlan = new CarePlan();
        BeanUtils.copyProperties(dto, carePlan);
        carePlan.setCreateTime(DateUtils.getNowDate());

        carePlanMapper.insert(carePlan);

        // 2.批量保存照护方案和医嘱项目的对应关系
        int count = carePlanOrderItemMapper.batchInsert(dto.getProjectPlans(), carePlan.getId());
            // 清除照护方案缓存
            clearCarePlanCache();

        return count == 0 ? 0 : 1;
    }

    /**
     * 修改照护方案
     * 
     * @param dto 照护方案
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCarePlan(CarePlanDto dto)
    {
        // 判断dto中的医嘱项目是否为空，如果不为空，先删除照护方案关联的所有医嘱项目，再重新批量保存最新的关联
        if (dto.getProjectPlans() != null && !dto.getProjectPlans().isEmpty()) {
            // 删除照护方案对应的医嘱项目列表
            carePlanOrderItemMapper.deleteByCarePlanId(dto.getId());

            // 批量保存照护方案关联的医嘱项目
            carePlanOrderItemMapper.batchInsert(dto.getProjectPlans(), dto.getId());
        }

        CarePlan carePlan = new CarePlan();
        BeanUtils.copyProperties(dto, carePlan);

        // 修改照护方案
        int result = carePlanMapper.updateById(carePlan);
        
        // 清除照护方案缓存
        clearCarePlanCache();
        
        return result;
    }

    /**
     * 批量删除照护方案
     * 
     * @param ids 需要删除的照护方案主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCarePlanByIds(Long[] ids)
    {
        // 先删除关联的医嘱项目
        for (Long id : ids) {
            carePlanOrderItemMapper.deleteByCarePlanId(id);
        }
        
        int result = removeByIds(Arrays.asList(ids)) ? 1 : 0;
        
        // 清除照护方案缓存
        clearCarePlanCache();
        
        return result;
    }

    /**
     * 删除照护方案信息
     * 
     * @param id 照护方案主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCarePlanById(Long id)
    {
        // 删除照护方案关联的医嘱项目
        carePlanOrderItemMapper.deleteByCarePlanId(id);
        // 删除照护方案
        int result = removeById(id) ? 1 : 0;
        
        // 清除照护方案缓存
        clearCarePlanCache();
        
        return result;
    }

    /**
     * 查询所有照护方案
     *
     * @return 照护方案列表
     */
    @Override
    public List<CarePlan> getAllCarePlans() {
        // 先从缓存中获取
        List<CarePlan> cachedPlans = redisCache.getCacheObject(CARE_PLAN_CACHE_KEY);
        if (cachedPlans != null && !cachedPlans.isEmpty()) {
            return cachedPlans;
        }
        
        // 缓存未命中，从数据库查询
        LambdaQueryWrapper<CarePlan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CarePlan::getStatus, 1);
        List<CarePlan> plans = list(queryWrapper);
        
        // 将结果存入缓存，设置30分钟过期时间
        redisCache.setCacheObject(CARE_PLAN_CACHE_KEY, plans);
        
        return plans;
    }
    
    /**
     * 清除照护方案缓存
     */
    private void clearCarePlanCache() {
        redisCache.deleteObject(CARE_PLAN_CACHE_KEY);
    }
}
