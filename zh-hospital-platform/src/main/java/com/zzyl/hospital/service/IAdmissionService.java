package com.zzyl.hospital.service;

import com.zzyl.hospital.domain.Admission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzyl.hospital.dto.AdmissionApplyDto;
import com.zzyl.hospital.vo.AdmissionDetailVo;
import java.util.List;

/**
 * 入院Service接口
 * 
 * @author alexis
 * @date 2026-02-01
 */
public interface IAdmissionService extends IService<Admission>
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
     * 批量删除入院
     * 
     * @param ids 需要删除的入院主键集合
     * @return 结果
     */
    public int deleteAdmissionByIds(Long[] ids);

    /**
     * 删除入院信息
     * 
     * @param id 入院主键
     * @return 结果
     */
    public int deleteAdmissionById(Long id);

    /**
     * 申请入院
     *
     * @param applyDto request payload
     * @return result
     */
    void applyAdmission(AdmissionApplyDto applyDto);

    /**
     * 查询入院详情
     *
     * @param id 入院ID
     * @return 详情数据
     */
    AdmissionDetailVo selectDetailById(Long id);
}
