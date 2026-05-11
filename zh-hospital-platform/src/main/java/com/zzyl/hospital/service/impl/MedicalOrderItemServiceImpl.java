package com.zzyl.hospital.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyl.common.constant.CacheConstants;
import com.zzyl.common.core.redis.RedisCache;
import com.zzyl.hospital.domain.MedicalOrderItem;
import com.zzyl.hospital.dto.QueryParm;
import com.zzyl.hospital.mapper.MedicalOrderItemMapper;
import com.zzyl.hospital.service.IMedicalOrderItemService;
import com.zzyl.hospital.vo.MedicalOrderItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 医嘱项目Service业务层处理
 *
 * @author alexis
 * @date 2025-06-02
 */
@Service
public class MedicalOrderItemServiceImpl extends ServiceImpl<MedicalOrderItemMapper, MedicalOrderItem> implements IMedicalOrderItemService {
    @Autowired
    private MedicalOrderItemMapper medicalOrderItemMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 医嘱项目缓存key
     */
    private static final String MEDICAL_ORDER_ITEM_CACHE_KEY = CacheConstants.MEDICAL_ORDER_ITEM_KEY + "all";

    /**
     * 查询医嘱项目
     *
     * @param id 医嘱项目主键
     * @return 医嘱项目
     */
    @Override
    public MedicalOrderItem selectMedicalOrderItemById(Long id) {
        return getById(id);
    }

    /**
     * 查询医嘱项目列表
     *
     * @param queryParm 查询参数
     * @return 医嘱项目
     */
    @Override
    public List<MedicalOrderItem> selectMedicalOrderItemList(QueryParm queryParm) {
        // 创建分页对象
        Page<MedicalOrderItem> page = new Page<>(queryParm.getPageNum(), queryParm.getPageSize());

        page = lambdaQuery().eq(queryParm.getStatus() != null, MedicalOrderItem::getStatus, queryParm.getStatus())
                .like(queryParm.getName() != null, MedicalOrderItem::getName, queryParm.getName())
                .orderByDesc(MedicalOrderItem::getCreateTime)
                .page(page);

        // 返回查询结果列表
        return page.getRecords();
    }

    /**
     * 新增医嘱项目
     *
     * @param medicalOrderItem 医嘱项目
     * @return 结果
     */
    @Override
    public int insertMedicalOrderItem(MedicalOrderItem medicalOrderItem) {
        int result = save(medicalOrderItem) ? 1 : 0;
        // 清除医嘱项目缓存
        clearMedicalOrderItemCache();
        return result;
    }

    /**
     * 修改医嘱项目
     *
     * @param medicalOrderItem 医嘱项目
     * @return 结果
     */
    @Override
    public int updateMedicalOrderItem(MedicalOrderItem medicalOrderItem) {
        int result = updateById(medicalOrderItem) ? 1 : 0;
        // 清除医嘱项目缓存
        clearMedicalOrderItemCache();
        return result;
    }

    /**
     * 批量删除医嘱项目
     *
     * @param ids 需要删除的医嘱项目主键
     * @return 结果
     */
    @Override
    public int deleteMedicalOrderItemByIds(Long[] ids) {
        int result = removeByIds(Arrays.asList(ids)) ? 1 : 0;
        // 清除医嘱项目缓存
        clearMedicalOrderItemCache();
        return result;
    }

    /**
     * 删除医嘱项目信息
     *
     * @param id 医嘱项目主键
     * @return 结果
     */
    @Override
    public int deleteMedicalOrderItemById(Long id) {
        int result = removeById(id) ? 1 : 0;
        // 清除医嘱项目缓存
        clearMedicalOrderItemCache();
        return result;
    }

    /**
     * 查询所有医嘱项目
     *
     * @return 医嘱项目列表
     */
    @Override
    public List<MedicalOrderItemVo> getAll() {
        // 先从缓存中获取
        List<MedicalOrderItemVo> cachedProjects = redisCache.getCacheObject(MEDICAL_ORDER_ITEM_CACHE_KEY);
        if (cachedProjects != null && !cachedProjects.isEmpty()) {
            return cachedProjects;
        }

        // 缓存未命中，从数据库查询
        List<MedicalOrderItemVo> projects = medicalOrderItemMapper.getAll();

        // 将结果存入缓存，设置30分钟过期时间
        redisCache.setCacheObject(MEDICAL_ORDER_ITEM_CACHE_KEY, projects, 30, TimeUnit.MINUTES);

        return projects;
    }

    /**
     * 清除医嘱项目缓存
     */
    private void clearMedicalOrderItemCache() {
        redisCache.deleteObject(MEDICAL_ORDER_ITEM_CACHE_KEY);
    }
}
