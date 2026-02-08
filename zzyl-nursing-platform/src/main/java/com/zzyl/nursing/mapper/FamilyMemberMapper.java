package com.zzyl.nursing.mapper;

import java.util.List;
import com.zzyl.nursing.domain.FamilyMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * memberMapper接口
 * 
 * @author alexis
 * @date 2026-02-08
 */
@Mapper
public interface FamilyMemberMapper extends BaseMapper<FamilyMember>
{
    /**
     * 查询member
     * 
     * @param id member主键
     * @return member
     */
    public FamilyMember selectFamilyMemberById(Long id);

    /**
     * 查询member列表
     * 
     * @param familyMember member
     * @return member集合
     */
    public List<FamilyMember> selectFamilyMemberList(FamilyMember familyMember);

    /**
     * 新增member
     * 
     * @param familyMember member
     * @return 结果
     */
    public int insertFamilyMember(FamilyMember familyMember);

    /**
     * 修改member
     * 
     * @param familyMember member
     * @return 结果
     */
    public int updateFamilyMember(FamilyMember familyMember);

    /**
     * 删除member
     * 
     * @param id member主键
     * @return 结果
     */
    public int deleteFamilyMemberById(Long id);

    /**
     * 批量删除member
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFamilyMemberByIds(Long[] ids);
}
