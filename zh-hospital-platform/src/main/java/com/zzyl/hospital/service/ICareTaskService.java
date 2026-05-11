package com.zzyl.hospital.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.hospital.domain.CareTask;

/**
 * 照护任务Service接口
 */
public interface ICareTaskService extends IService<CareTask> {
    CareTask selectCareTaskById(Long id);

    List<CareTask> selectCareTaskList(CareTask careTask);

    int updateCareTask(CareTask careTask);

    int cancelCareTask(CareTask careTask);

    int executeCareTask(CareTask careTask);

    int updateCareTaskTime(CareTask careTask);
}
