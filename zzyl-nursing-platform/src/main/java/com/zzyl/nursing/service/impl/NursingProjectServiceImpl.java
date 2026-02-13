package com.zzyl.nursing.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyl.common.constant.CacheConstants;
import com.zzyl.common.core.redis.RedisCache;
import com.zzyl.nursing.domain.NursingProject;
import com.zzyl.nursing.dto.QueryParm;
import com.zzyl.nursing.mapper.NursingProjectMapper;
import com.zzyl.nursing.service.INursingProjectService;
import com.zzyl.nursing.vo.NursingProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 护理项目Service业务层处理
 *
 * @author alexis
 * @date 2025-06-02
 */
@Service
public class NursingProjectServiceImpl extends ServiceImpl<NursingProjectMapper, NursingProject> implements INursingProjectService {
    @Autowired
    private NursingProjectMapper nursingProjectMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 护理项目缓存key
     */
    private static final String NURSING_PROJECT_CACHE_KEY = CacheConstants.NURSING_PROJECT_KEY + "all";

    /**
     * 查询护理项目
     *
     * @param id 护理项目主键
     * @return 护理项目
     */
    @Override
    public NursingProject selectNursingProjectById(Long id) {
        return getById(id);
    }

    /**
     * 查询护理项目列表
     *
     * @param queryParm 查询参数
     * @return 护理项目
     */
    @Override
    public List<NursingProject> selectNursingProjectList(QueryParm queryParm) {
        // 创建分页对象
        Page<NursingProject> page = new Page<>(queryParm.getPageNum(), queryParm.getPageSize());

        page = lambdaQuery().eq(queryParm.getStatus() != null, NursingProject::getStatus, queryParm.getStatus())
                .like(queryParm.getName() != null, NursingProject::getName, queryParm.getName())
                .orderByDesc(NursingProject::getCreateTime)
                .page(page);

        // 返回查询结果列表
        return page.getRecords();
    }

    /**
     * 新增护理项目
     *
     * @param nursingProject 护理项目
     * @return 结果
     */
    @Override
    public int insertNursingProject(NursingProject nursingProject) {
        int result = save(nursingProject) ? 1 : 0;
        // 清除护理项目缓存
        clearNursingProjectCache();
        return result;
    }

    /**
     * 修改护理项目
     *
     * @param nursingProject 护理项目
     * @return 结果
     */
    @Override
    public int updateNursingProject(NursingProject nursingProject) {
        int result = updateById(nursingProject) ? 1 : 0;
        // 清除护理项目缓存
        clearNursingProjectCache();
        return result;
    }

    /**
     * 批量删除护理项目
     *
     * @param ids 需要删除的护理项目主键
     * @return 结果
     */
    @Override
    public int deleteNursingProjectByIds(Long[] ids) {
        int result = removeByIds(Arrays.asList(ids)) ? 1 : 0;
        // 清除护理项目缓存
        clearNursingProjectCache();
        return result;
    }

    /**
     * 删除护理项目信息
     *
     * @param id 护理项目主键
     * @return 结果
     */
    @Override
    public int deleteNursingProjectById(Long id) {
        int result = removeById(id) ? 1 : 0;
        // 清除护理项目缓存
        clearNursingProjectCache();
        return result;
    }

    /**
     * 查询所有护理项目
     *
     * @return 护理项目列表
     */
    @Override
    public List<NursingProjectVo> getAll() {
        // 先从缓存中获取
        List<NursingProjectVo> cachedProjects = redisCache.getCacheObject(NURSING_PROJECT_CACHE_KEY);
        if (cachedProjects != null && !cachedProjects.isEmpty()) {
            return cachedProjects;
        }

        // 缓存未命中，从数据库查询
        List<NursingProjectVo> projects = nursingProjectMapper.getAll();

        // 将结果存入缓存，设置30分钟过期时间
        redisCache.setCacheObject(NURSING_PROJECT_CACHE_KEY, projects, 30, TimeUnit.MINUTES);

        return projects;
    }

    /**
     * 清除护理项目缓存
     */
    private void clearNursingProjectCache() {
        redisCache.deleteObject(NURSING_PROJECT_CACHE_KEY);
    }
}
