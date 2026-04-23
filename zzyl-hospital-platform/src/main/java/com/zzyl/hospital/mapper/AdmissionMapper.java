package com.zzyl.hospital.mapper;

import java.util.List;
import com.zzyl.hospital.domain.Admission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 入院Mapper接口
 * 
 * @author alexis
 * @date 2026-02-01
 */
@Mapper
public interface AdmissionMapper extends BaseMapper<Admission>
{
    /**
     * 查询入院
     * 
     * @param id 入院主键
     * @return 入院
     */
    public Admission selectAdmissionById(Long id);

    /**
     * 查询入院列表
     * 
     * @param admission 入院
     * @return 入院集合
     */
    public List<Admission> selectAdmissionList(Admission admission);

    /**
     * 新增入院
     * 
     * @param admission 入院
     * @return 结果
     */
    public int insertAdmission(Admission admission);

    /**
     * 修改入院
     * 
     * @param admission 入院
     * @return 结果
     */
    public int updateAdmission(Admission admission);

    /**
     * 删除入院
     * 
     * @param id 入院主键
     * @return 结果
     */
    public int deleteAdmissionById(Long id);

    /**
     * 批量删除入院
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdmissionByIds(Long[] ids);
}
