package com.zzyl.hospital.service.impl;

import java.util.Arrays;
import java.util.List;

import com.zzyl.common.constant.CacheConstants;
import com.zzyl.common.utils.DateUtils;
import com.zzyl.hospital.vo.CareLevelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.zzyl.hospital.mapper.CareLevelMapper;
import com.zzyl.hospital.domain.CareLevel;
import com.zzyl.hospital.service.ICareLevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 护理级别Service业务层处理
 * 
 * @author alexis
 * @date 2025-06-02
 */
@Service
public class CareLevelServiceImpl extends ServiceImpl<CareLevelMapper, CareLevel> implements ICareLevelService
{
    @Autowired
    private CareLevelMapper careLevelMapper;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    /**
     * 查询护理级别
     * 
     * @param id 护理级别主键
     * @return 护理级别
     */
    @Override
    public CareLevel selectCareLevelById(Long id)
    {
        return getById(id);
    }

    /**
     * 查询护理级别列表
     * 
     * @param careLevel 护理级别
     * @return 护理级别
     */
    @Override
    public List<CareLevel> selectCareLevelList(CareLevel careLevel)
    {
        return careLevelMapper.selectCareLevelList(careLevel);
    }

    /**
     * 新增护理级别
     * 
     * @param careLevel 护理级别
     * @return 结果
     */
    @Override
    public int insertCareLevel(CareLevel careLevel)
    {
        boolean save = save(careLevel);
        deleteRedis();
        return save ? 1 : 0;
    }

    private void deleteRedis() {
        redisTemplate.delete(CacheConstants.ALL_CARE_LEVELS);
    }

    /**
     * 修改护理级别
     * 
     * @param careLevel 护理级别
     * @return 结果
     */
    @Override
    public int updateCareLevel(CareLevel careLevel)
    {
        boolean byId = updateById(careLevel);
        deleteRedis();
        return byId ? 1 : 0;
    }

    /**
     * 批量删除护理级别
     * 
     * @param ids 需要删除的护理级别主键
     * @return 结果
     */
    @Override
    public int deleteCareLevelByIds(Long[] ids)
    {
        boolean removeById = removeByIds(Arrays.asList(ids));
        deleteRedis();
        return removeById ? 1 : 0;
    }

    /**
     * 删除护理级别信息
     * 
     * @param id 护理级别主键
     * @return 结果
     */
    @Override
    public int deleteCareLevelById(Long id)
    {
        boolean byId = removeById(id);
        deleteRedis();
        return byId ? 1 : 0;
    }

    /**
     * 查询护理级别Vo列表
     *
     * @param careLevel 条件
     * @return 结果
     */
    @Override
    public List<CareLevelVo> selectCareLevelVoList(CareLevel careLevel) {
        return careLevelMapper.selectCareLevelVoList(careLevel);
    }
}
