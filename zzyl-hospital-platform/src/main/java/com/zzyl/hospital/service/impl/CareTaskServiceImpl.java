package com.zzyl.hospital.service.impl;

import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyl.hospital.domain.CareTask;
import com.zzyl.hospital.mapper.CareTaskMapper;
import com.zzyl.hospital.service.ICareTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 照护任务Service业务层处理
 */
@Service
public class CareTaskServiceImpl extends ServiceImpl<CareTaskMapper, CareTask> implements ICareTaskService {
    @Autowired
    private CareTaskMapper careTaskMapper;

    @Override
    public CareTask selectCareTaskById(Long id) {
        return getById(id);
    }

    @Override
    public List<CareTask> selectCareTaskList(CareTask careTask) {
        return careTaskMapper.selectCareTaskList(careTask);
    }

    @Override
    public int updateCareTask(CareTask careTask) {
        return updateById(careTask) ? 1 : 0;
    }

    @Override
    public int cancelCareTask(CareTask careTask) {
        careTask.setStatus(3);
        careTask.setUpdateTime(new Date());
        return updateById(careTask) ? 1 : 0;
    }

    @Override
    public int executeCareTask(CareTask careTask) {
        careTask.setStatus(2);
        if (careTask.getRealServerTime() == null) {
            careTask.setRealServerTime(new Date());
        }
        careTask.setUpdateTime(new Date());
        return updateById(careTask) ? 1 : 0;
    }

    @Override
    public int updateCareTaskTime(CareTask careTask) {
        careTask.setUpdateTime(new Date());
        return updateById(careTask) ? 1 : 0;
    }
}
