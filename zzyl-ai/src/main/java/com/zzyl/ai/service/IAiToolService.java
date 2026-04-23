package com.zzyl.ai.service;

import com.zzyl.ai.vo.AiBedAvailabilityVo;
import com.zzyl.ai.vo.AiCurrentPlansVo;

/**
 * AI 工具服务接口。
 *
 * <p>主要提供给 Dify 这类外部 Agent 作为 Tool 调用。</p>
 */
public interface IAiToolService
{
    /**
     * 校验工具接口访问 Token。
     *
     * @param authorizationHeader Authorization 请求头
     */
    void validateToolToken(String authorizationHeader);

    /**
     * 查询当前照护方案。
     *
     * <p>返回的是给 AI 使用的精简字段，而不是完整业务对象。</p>
     *
     * @return 当前照护方案摘要信息
     */
    AiCurrentPlansVo getCurrentPlans();

    /**
     * 查询当前剩余病床。
     *
     * @return 剩余病床信息
     */
    AiBedAvailabilityVo getRemainingBeds();
}
