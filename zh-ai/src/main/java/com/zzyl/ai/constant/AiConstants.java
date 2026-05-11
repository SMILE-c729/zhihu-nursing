package com.zzyl.ai.constant;

/**
 * `zzyl-ai` 模块公共常量。
 */
public final class AiConstants
{
    private AiConstants()
    {
    }

    /**
     * Redis 中缓存 AI 会话映射时使用的前缀。
     */
    public static final String CONVERSATION_CACHE_PREFIX = "zzyl:ai:conversation:";

    /**
     * Redis 中缓存用户 AI 会话索引时使用的前缀。
     */
    public static final String USER_CONVERSATION_CACHE_PREFIX = "zzyl:ai:user:";

    /**
     * Bearer Token 的固定前缀。
     */
    public static final String AUTHORIZATION_PREFIX = "Bearer ";

    /**
     * 发送给 Dify 时，拼接用户标识使用的前缀。
     */
    public static final String DIFY_USER_PREFIX = "zzyl-user-";

    /**
     * 会话默认缓存时长，单位：分钟。
     */
    public static final Integer DEFAULT_SESSION_TTL_MINUTES = 1440;

    /**
     * Redis 中每个用户最多保留的临时会话数量。
     */
    public static final Integer MAX_HISTORY_SIZE = 30;

    /**
     * 每次请求拼接给模型的最近历史消息条数，20 条约等于最近 10 轮对话。
     */
    public static final Integer MAX_CONTEXT_MESSAGE_SIZE = 12;

    public static final Integer MAX_CONTEXT_MESSAGE_CHARS = 1200;

    public static final Integer MAX_CONTEXT_TOTAL_CHARS = 6000;

    public static final Integer MAX_SESSION_MESSAGE_SIZE = 80;

    public static final String ROLE_USER = "user";

    public static final String ROLE_ASSISTANT = "assistant";

    public static final String STATUS_NEW = "new";

    public static final String STATUS_RUNNING = "running";

    public static final String STATUS_COMPLETED = "completed";

    public static final String STATUS_STOPPED = "stopped";

    public static final String STATUS_ERROR = "error";
}
