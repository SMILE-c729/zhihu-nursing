package com.zzyl.hospital.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzyl.hospital.domain.CareTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * 照护任务Mapper接口
 */
@Mapper
public interface CareTaskMapper extends BaseMapper<CareTask> {
    List<CareTask> selectCareTaskList(CareTask careTask);
}
