package com.zzyl.hospital.service;

import java.util.List;
import com.zzyl.hospital.domain.WarningData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 预警数据Service接口
 * 
 * @author alexis
 * @date 2026-02-24
 */
public interface IWarningDataService extends IService<WarningData>
{
    /**
     * 查询预警数据
     * 
     * @param id 预警数据主键
     * @return 预警数据
     */
    public WarningData selectWarningDataById(Long id);

    /**
     * 查询预警数据列表
     * 
     * @param warningData 预警数据
     * @return 预警数据集合
     */
    public List<WarningData> selectWarningDataList(WarningData warningData);

    /**
     * 新增预警数据
     * 
     * @param warningData 预警数据
     * @return 结果
     */
    public int insertWarningData(WarningData warningData);

    /**
     * 修改预警数据
     * 
     * @param warningData 预警数据
     * @return 结果
     */
    public int updateWarningData(WarningData warningData);

    /**
     * 批量删除预警数据
     * 
     * @param ids 需要删除的预警数据主键集合
     * @return 结果
     */
    public int deleteWarningDataByIds(Long[] ids);

    /**
     * 删除预警数据信息
     * 
     * @param id 预警数据主键
     * @return 结果
     */
    public int deleteWarningDataById(Long id);
}
