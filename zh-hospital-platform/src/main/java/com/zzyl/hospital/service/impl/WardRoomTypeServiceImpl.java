package com.zzyl.hospital.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzyl.common.exception.base.BaseException;
import com.zzyl.hospital.domain.WardRoomType;
import com.zzyl.hospital.mapper.WardRoomTypeMapper;
import com.zzyl.hospital.service.IWardRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 病房类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-26
 */
@Service
public class WardRoomTypeServiceImpl extends ServiceImpl<WardRoomTypeMapper, WardRoomType> implements IWardRoomTypeService
{
    @Autowired
    private WardRoomTypeMapper wardRoomTypeMapper;

    /**
     * 查询病房类型
     * 
     * @param id 病房类型主键
     * @return 病房类型
     */
    @Override
    public WardRoomType selectWardRoomTypeById(Long id)
    {
        return getById(id);
    }

    /**
     * 查询病房类型列表
     * 
     * @param wardRoomType 病房类型
     * @return 病房类型
     */
    @Override
    public List<WardRoomType> selectWardRoomTypeList(WardRoomType wardRoomType)
    {
        return wardRoomTypeMapper.selectWardRoomTypeList(wardRoomType);
    }

    /**
     * 新增病房类型
     * 
     * @param wardRoomType 病房类型
     * @return 结果
     */
    @Override
    public int insertWardRoomType(WardRoomType wardRoomType)
    {
        return save(wardRoomType) ? 1 : 0;
    }

    /**
     * 修改病房类型
     * 
     * @param wardRoomType 病房类型
     * @return 结果
     */
    @Override
    public int updateWardRoomType(WardRoomType wardRoomType)
    {
        return updateById(wardRoomType) ? 1 : 0;
    }

    /**
     * 批量删除病房类型
     * 
     * @param ids 需要删除的病房类型主键
     * @return 结果
     */
    @Override
    public int deleteWardRoomTypeByIds(Long[] ids)
    {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除病房类型信息
     * 
     * @param id 病房类型主键
     * @return 结果
     */
    @Override
    public int deleteWardRoomTypeById(Long id)
    {
        return removeById(id) ? 1 : 0;
    }

    /**
     * 按照状态查询房间类型
     * @param status
     * @return
     */
    @Override
    public List<WardRoomType> findWardRoomTypeListByStatus(Integer status) {

        if(ObjectUtil.isEmpty(status)){
            throw new BaseException("参数为空");
        }
        LambdaQueryWrapper<WardRoomType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WardRoomType::getStatus,status);
        return list(wrapper);
    }
}
