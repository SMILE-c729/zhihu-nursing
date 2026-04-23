# ZZYL-AI 使用说明

## 1. 模块说明

`zzyl-ai` 是当前项目新增的 AI 对接模块，主要做两件事：

1. 对接 Dify 平台，提供会话创建、发送消息、删除会话能力。
2. 对外暴露两个给 Dify 调用的工具接口：
   - 查询当前护理计划
   - 查询当前床位剩余情况

当前代码已经按你项目现有风格整理为：

- `service`：接口定义
- `service.impl`：实现类
- `controller`：接口层
- `config`：配置类
- `vo/dto/domain`：请求、响应和缓存对象

## 2. 目录结构

关键代码位置：

- 会话接口：[AiConversationController.java](C:/zzly/zznursing/zzyl-ai/src/main/java/com/zzyl/ai/controller/AiConversationController.java)
- 工具接口：[AiToolController.java](C:/zzly/zznursing/zzyl-ai/src/main/java/com/zzyl/ai/controller/AiToolController.java)
- 会话服务接口：[IAiConversationService.java](C:/zzly/zznursing/zzyl-ai/src/main/java/com/zzyl/ai/service/IAiConversationService.java)
- 会话服务实现：[AiConversationServiceImpl.java](C:/zzly/zznursing/zzyl-ai/src/main/java/com/zzyl/ai/service/impl/AiConversationServiceImpl.java)
- 工具服务接口：[IAiToolService.java](C:/zzly/zznursing/zzyl-ai/src/main/java/com/zzyl/ai/service/IAiToolService.java)
- 工具服务实现：[AiToolServiceImpl.java](C:/zzly/zznursing/zzyl-ai/src/main/java/com/zzyl/ai/service/impl/AiToolServiceImpl.java)
- 配置类：[AiProperties.java](C:/zzly/zznursing/zzyl-ai/src/main/java/com/zzyl/ai/config/AiProperties.java)
- Dify 客户端配置：[DifyClientConfiguration.java](C:/zzly/zznursing/zzyl-ai/src/main/java/com/zzyl/ai/config/DifyClientConfiguration.java)

## 3. 配置说明

配置文件位置：

- [application.yml](C:/zzly/zznursing/zzyl-admin/src/main/resources/application.yml)

新增配置如下：

```yaml
zzyl:
  ai:
    dify:
      base-url: http://127.0.0.1/v1
      api-key: app-placeholder-api-key
      connect-timeout: 5000
      read-timeout: 60000
      write-timeout: 30000
    conversation:
      session-ttl-minutes: 1440
    tool:
      api-key: demo-dify-tool-key
```

你上线前只需要替换这三个核心值：

- `zzyl.ai.dify.base-url`
- `zzyl.ai.dify.api-key`
- `zzyl.ai.tool.api-key`

## 4. Dify 接入说明

当前使用的是官方 Java 客户端：

- [dify-java-client](https://github.com/imfangs/dify-java-client)

代码里已经接入以下能力：

- `DifyClientFactory.createChatClient(...)`
- `sendChatMessage(...)`
- `deleteConversation(...)`

你只需要把自己的 Dify `base-url` 和 `api-key` 替换掉，就可以连到你在 Dify 创建的 Agent。

## 5. 接口清单

### 5.1 前端聊天接口

这 3 个接口走你现有登录态，继续用项目本身的 `Authorization` 登录 Token。

#### 1）创建会话

- 路径：`POST /ai/conversation/create`
- 说明：创建本地会话 ID，后续聊天都用这个 ID

返回示例：

```json
{
  "code": 200,
  "msg": "创建会话成功",
  "data": {
    "id": "0c6dbe2b1d7b4d2f9f03a65d0d2d61af"
  }
}
```

#### 2）发送消息

- 路径：`POST /ai/chat/send`
- 请求体：

```json
{
  "conversationId": "0c6dbe2b1d7b4d2f9f03a65d0d2d61af",
  "content": "帮我总结一下今天的护理工作"
}
```

返回示例：

```json
{
  "code": 200,
  "msg": "这是 Dify 返回的回复内容",
  "data": {
    "conversationId": "0c6dbe2b1d7b4d2f9f03a65d0d2d61af",
    "reply": "这是 Dify 返回的回复内容",
    "messageId": "xxx",
    "difyConversationId": "yyy"
  }
}
```

#### 3）删除会话

- 路径：`DELETE /ai/conversation/{conversationId}`
- 说明：删除本地会话，同时如果已经关联 Dify 会话，也会一并删除

---

### 5.2 给 Dify 用的工具接口

这 2 个接口是给 Dify Tool 调用的，**不需要登录用户信息**，只需要带工具 Token。

统一认证方式：

```http
Authorization: Bearer demo-dify-tool-key
```

生产环境请替换成你自己的：

```yaml
zzyl.ai.tool.api-key: 你自己的工具Token
```

## 6. 护理计划工具接口

### 6.1 接口信息

- 路径：`GET /ai/tools/current-plans`
- 认证：只要 `Authorization` 头
- 请求参数：无
- 请求体：无

如果你是通过网关或前端代理访问，外部地址可能是：

- `/prod-api/ai/tools/current-plans`

如果你是服务直连，使用代码中的真实地址：

- `/ai/tools/current-plans`

### 6.2 curl 调用示例

```bash
curl --location --request GET 'http://127.0.0.1:8080/ai/tools/current-plans' \
--header 'Authorization: Bearer demo-dify-tool-key'
```

### 6.3 返回字段说明

这个接口已经按你的要求做了裁剪，**只返回关键字段**：

- `totalPlanCount`：当前启用中的护理计划总数
- `plans`：护理计划列表
- `plans[].planId`：护理计划 ID
- `plans[].planName`：护理计划名称
- `plans[].projects`：该计划下的护理项目列表
- `plans[].projects[].projectId`：护理项目 ID
- `plans[].projects[].projectName`：护理项目名称
- `plans[].projects[].executeTime`：执行时间
- `plans[].projects[].executeCycleLabel`：执行周期中文说明
- `plans[].projects[].executeFrequency`：执行频次

### 6.4 返回示例

```json
{
  "code": 200,
  "msg": "查询护理计划成功",
  "data": {
    "totalPlanCount": 2,
    "plans": [
      {
        "planId": 1,
        "planName": "失能老人护理计划",
        "projects": [
          {
            "projectId": 11,
            "projectName": "血压监测",
            "executeTime": "08:00",
            "executeCycleLabel": "天",
            "executeFrequency": 2
          },
          {
            "projectId": 12,
            "projectName": "翻身护理",
            "executeTime": "10:00",
            "executeCycleLabel": "天",
            "executeFrequency": 3
          }
        ]
      }
    ]
  }
}
```

## 7. 剩余床位工具接口

### 7.1 接口信息

- 路径：`GET /ai/tools/beds/remaining`
- 认证：只要 `Authorization` 头
- 请求参数：无
- 请求体：无

### 7.2 curl 调用示例

```bash
curl --location --request GET 'http://127.0.0.1:8080/ai/tools/beds/remaining' \
--header 'Authorization: Bearer demo-dify-tool-key'
```

### 7.3 返回字段说明

- `summary.availableBedCount`：总剩余床位数
- `summary.availableRoomCount`：有空床的房间数
- `summary.availableFloorCount`：有空床的楼层数
- `floors`：按楼层拆分的数据

## 8. 在 Dify 中如何配置工具

如果你在 Dify 里通过 HTTP / API Tool 调当前系统接口，可以这样配：

### 8.1 查询护理计划

- Method：`GET`
- URL：`http://你的服务地址/ai/tools/current-plans`
- Header：

```json
{
  "Authorization": "Bearer demo-dify-tool-key"
}
```

- Query：无
- Body：无

### 8.2 查询剩余床位

- Method：`GET`
- URL：`http://你的服务地址/ai/tools/beds/remaining`
- Header：

```json
{
  "Authorization": "Bearer demo-dify-tool-key"
}
```

- Query：无
- Body：无

## 9. 你后续需要做的事

你现在只需要做下面几步：

1. 把 `application.yml` 里的 Dify 配置换成你自己的。
2. 把 `zzyl.ai.tool.api-key` 换成你要给 Dify 使用的工具 Token。
3. 在 Dify 里配置工具调用地址和 `Authorization: Bearer xxx` 请求头。
4. 先本地测试 `/ai/tools/current-plans` 和 `/ai/tools/beds/remaining` 是否能正常返回。

## 10. 当前实现说明

当前护理计划接口已经满足你这次提的要求：

- 只需要 Token
- 不需要额外参数
- 只返回关键字段
- 代码已改成接口 + Impl 结构
- 关键类已补中文注释
