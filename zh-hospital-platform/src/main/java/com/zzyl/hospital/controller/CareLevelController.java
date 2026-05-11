package com.zzyl.hospital.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.function.ObjBoolConsumer;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzyl.common.constant.CacheConstants;
import com.zzyl.common.core.domain.R;
import com.zzyl.hospital.vo.CareLevelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zzyl.common.annotation.Log;
import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.common.enums.BusinessType;
import com.zzyl.hospital.domain.CareLevel;
import com.zzyl.hospital.service.ICareLevelService;
import com.zzyl.common.utils.poi.ExcelUtil;
import com.zzyl.common.core.page.TableDataInfo;

/**
 * 护理级别Controller
 *
 * @author alexis
 * @date 2025-06-02
 */
@Api(tags = "护理级别管理")
@RestController
@RequestMapping("/hospital/level")
public class CareLevelController extends BaseController {
    @Autowired
    private ICareLevelService careLevelService;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 查询护理级别列表
     */
    @ApiOperation("查询护理级别列表")
    @PreAuthorize("@ss.hasPermi('hospital:level:list')")
    @GetMapping("/list")
    public TableDataInfo<List<CareLevelVo>> list(@ApiParam("护理级别查询条件") CareLevel careLevel) {
        startPage();
        List<CareLevelVo> list = careLevelService.selectCareLevelVoList(careLevel);
        return getDataTable(list);
    }

    /**
     * 导出护理级别列表
     */
    @ApiOperation("导出护理级别列表")
    @PreAuthorize("@ss.hasPermi('hospital:level:export')")
    @Log(title = "护理级别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@ApiParam("导出的查询条件") HttpServletResponse response, CareLevel careLevel) {
        List<CareLevel> list = careLevelService.selectCareLevelList(careLevel);
        ExcelUtil<CareLevel> util = new ExcelUtil<CareLevel>(CareLevel.class);
        util.exportExcel(response, list, "护理级别数据");
    }

    /**
     * 获取护理级别详细信息
     */
    @ApiOperation("获取护理级别详细信息")
    @PreAuthorize("@ss.hasPermi('hospital:level:query')")
    @GetMapping(value = "/{id}")
    public R<CareLevel> getInfo(@PathVariable("id") @ApiParam("护理级别ID") Long id) {
        return R.ok(careLevelService.selectCareLevelById(id));
    }

    /**
     * 新增护理级别
     */
    @ApiOperation("新增护理级别")
    @PreAuthorize("@ss.hasPermi('hospital:level:add')")
    @Log(title = "护理级别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @ApiParam("新增的护理级别对象") CareLevel careLevel) {
        return toAjax(careLevelService.insertCareLevel(careLevel));
    }

    /**
     * 修改护理级别
     */
    @ApiOperation("修改护理级别")
    @PreAuthorize("@ss.hasPermi('hospital:level:edit')")
    @Log(title = "护理级别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @ApiParam("修改的护理级别对象") CareLevel careLevel) {
        return toAjax(careLevelService.updateCareLevel(careLevel));
    }

    /**
     * 删除护理级别
     */
    @ApiOperation("删除护理级别")
    @PreAuthorize("@ss.hasPermi('hospital:level:remove')")
    @Log(title = "护理级别", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable @ApiParam("要删除的护理级别ID") Long[] ids) {
        return toAjax(careLevelService.deleteCareLevelByIds(ids));
    }

    /*
     * 查询所有护理级别信息
     * */
    @ApiOperation("查询所有护理级别信息")
    @GetMapping("/all")
    public R<List<CareLevel>> getAllCareLevels() {
        //1.从缓存中获取所有护理级别信息
        List<CareLevel> list = (List<CareLevel>) redisTemplate.opsForValue().get(CacheConstants.ALL_CARE_LEVELS);
        //2.有数据返回
        if (ObjectUtil.isNotEmpty(list)) {
            return R.ok(list);
        }
        //3.无数据，查询数据库
        list = careLevelService.lambdaQuery().eq(CareLevel::getStatus, 1).list();
        //4.将查询结果存入缓存
        redisTemplate.opsForValue().set(CacheConstants.ALL_CARE_LEVELS, list);
        return R.ok(list);
    }
}
