package com.zzyl.ai.controller;

import com.zzyl.ai.service.IAiToolService;
import com.zzyl.ai.vo.AiBedAvailabilityVo;
import com.zzyl.ai.vo.AiCurrentPlansVo;
import com.zzyl.common.annotation.Anonymous;
import com.zzyl.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AI 工具接口。
 *
 * <p>这一组接口供 Dify 等外部 Agent 调用。</p>
 * <p>调用方只需要在 `Authorization` 请求头中携带工具 Token 即可。</p>
 */
@Api(tags = "AI 工具接口")
@RestController
@RequestMapping("/ai/tools")
@RequiredArgsConstructor
public class AiToolController
{
    /**
     * AI 工具服务。
     */
    private final IAiToolService aiToolService;

    /**
     * 查询当前照护方案。
     *
     * <p>这里只返回给 AI 使用的关键字段，避免返回过大的业务对象。</p>
     *
     * @param
     * @return 当前照护方案
     */
    @ApiOperation("查询当前照护方案")
    @GetMapping("/current-plans")
    public AjaxResult getCurrentPlans()
    {
        AiCurrentPlansVo data = aiToolService.getCurrentPlans();
        return AjaxResult.success("查询照护方案成功", data);
    }

    /**
     * 查询当前剩余病床。
     *
     * @return 剩余病床信息
     */
    @ApiOperation("查询当前剩余病床")
    @GetMapping("/beds/remaining")
    public AjaxResult getRemainingBeds()
    {
        //aiToolService.validateToolToken(authorization);
        AiBedAvailabilityVo data = aiToolService.getRemainingBeds();
        return AjaxResult.success("查询剩余病床成功", data);
    }
}
