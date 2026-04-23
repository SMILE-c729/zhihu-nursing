package com.zzyl.hospital.controller.member;

import com.zzyl.common.core.controller.BaseController;
import com.zzyl.common.core.domain.AjaxResult;
import com.zzyl.hospital.domain.WardRoomType;
import com.zzyl.hospital.service.IWardRoomTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member/wardRoomTypes")
@Api(tags = "小程序端-病房类型")
public class MemberWardRoomTypeController extends BaseController {

    @Autowired
    private IWardRoomTypeService wardRoomTypeService;

    /*
    * 查询房间类型列表
    * */
    @GetMapping
    @ApiOperation("查询房间类型列表")
    public AjaxResult findWardRoomTypeListByStatus(Integer status) {
        List<WardRoomType> wardRoomTypeVoList = wardRoomTypeService.findWardRoomTypeListByStatus(status);
        return success(wardRoomTypeVoList);
    }
}
