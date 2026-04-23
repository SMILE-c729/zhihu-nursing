# 智护云医管 API 接口文档

> 版本：v1.0 | 更新日期：2026-04-22 | 基于 Springfox Swagger 3 (OAS 3)

---

## 目录

- [一、系统模块](#一系统模块)
  - [1.1 登录认证](#11-登录认证)
  - [1.2 用户注册](#12-用户注册)
  - [1.3 验证码](#13-验证码)
  - [1.4 用户管理](#14-用户管理)
  - [1.5 角色管理](#15-角色管理)
  - [1.6 菜单管理](#16-菜单管理)
  - [1.7 部门管理](#17-部门管理)
  - [1.8 岗位管理](#18-岗位管理)
  - [1.9 字典类型](#19-字典类型)
  - [1.10 字典数据](#110-字典数据)
  - [1.11 参数配置](#111-参数配置)
  - [1.12 通知公告](#112-通知公告)
  - [1.13 个人信息](#113-个人信息)
  - [1.14 通用请求](#114-通用请求)
- [二、系统监控](#二系统监控)
  - [2.1 登录日志](#21-登录日志)
  - [2.2 操作日志](#22-操作日志)
  - [2.3 在线用户](#23-在线用户)
  - [2.4 服务监控](#24-服务监控)
  - [2.5 缓存监控](#25-缓存监控)
- [三、住院业务](#三住院业务)
  - [3.1 患者管理](#31-患者管理)
  - [3.2 预约挂号管理](#32-预约挂号管理)
  - [3.3 入院管理](#33-入院管理)
  - [3.4 入院合同管理](#34-入院合同管理)
  - [3.5 入院评估管理](#35-入院评估管理)
  - [3.6 护理级别管理](#36-护理级别管理)
  - [3.7 照护方案管理](#37-照护方案管理)
  - [3.8 医嘱项目管理](#38-医嘱项目管理)
  - [3.9 责任患者管理](#39-责任患者管理)
  - [3.10 病房类型管理](#310-病房类型管理)
  - [3.11 楼层管理](#311-楼层管理)
  - [3.12 房间管理](#312-房间管理)
  - [3.13 病床管理](#313-病床管理)
- [四、智能监测](#四智能监测)
  - [4.1 设备管理](#41-设备管理)
  - [4.2 生命体征数据管理](#42-生命体征数据管理)
  - [4.3 预警规则管理](#43-预警规则管理)
  - [4.4 预警数据管理](#44-预警数据管理)
- [五、小程序端](#五小程序端)
  - [5.1 患者联系人管理](#51-患者联系人管理)
  - [5.2 小程序端-预约管理](#52-小程序端-预约管理)
  - [5.3 小程序端-病房类型](#53-小程序端-病房类型)
  - [5.4 小程序端-医嘱项目](#54-小程序端-医嘱项目)
  - [5.5 客户端-健康数据](#55-客户端-健康数据)
- [六、AI 功能](#六ai-功能)
  - [6.1 AI 工具接口](#61-ai-工具接口)
  - [6.2 AI 机器人接口](#62-ai-机器人接口)

---

## 一、系统模块

### 1.1 登录认证

`@Api(tags = "登录认证")`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| POST | /login | 用户登录 | 无 |
| GET | /getInfo | 获取用户信息 | 无 |
| GET | /getRouters | 获取路由信息 | 无 |

### 1.2 用户注册

`@Api(tags = "用户注册")`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| POST | /register | 用户注册 | 无 |

### 1.3 验证码

`@Api(tags = "验证码")`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /captchaImage | 生成验证码 | 无 |

### 1.4 用户管理

`@Api(tags = "用户管理")` | 基础路径：`/system/user`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /system/user/list | 获取用户列表 | system:user:list |
| POST | /system/user/export | 导出用户数据 | system:user:export |
| POST | /system/user/importData | 导入用户数据 | system:user:import |
| POST | /system/user/importTemplate | 下载导入模板 | 无 |
| GET | /system/user/{userId} | 根据用户编号获取详细信息 | system:user:query |
| POST | /system/user | 新增用户 | system:user:add |
| PUT | /system/user | 修改用户 | system:user:edit |
| DELETE | /system/user/{userIds} | 删除用户 | system:user:remove |
| PUT | /system/user/resetPwd | 重置密码 | system:user:resetPwd |
| PUT | /system/user/changeStatus | 修改用户状态 | system:user:edit |
| GET | /system/user/authRole/{userId} | 根据用户编号获取授权角色 | system:user:query |
| PUT | /system/user/authRole | 用户授权角色 | system:user:edit |
| GET | /system/user/deptTree | 获取部门树列表 | system:user:list |
| GET | /system/user/listByDeptId | 根据部门编号查询员工列表 | 无 |

### 1.5 角色管理

`@Api(tags = "角色管理")` | 基础路径：`/system/role`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /system/role/list | 获取角色列表 | system:role:list |
| POST | /system/role/export | 导出角色数据 | system:role:export |
| GET | /system/role/{roleId} | 根据角色编号获取详细信息 | system:role:query |
| POST | /system/role | 新增角色 | system:role:add |
| PUT | /system/role | 修改角色 | system:role:edit |
| PUT | /system/role/dataScope | 修改数据权限 | system:role:edit |
| PUT | /system/role/changeStatus | 修改角色状态 | system:role:edit |
| DELETE | /system/role/{roleIds} | 删除角色 | system:role:remove |
| GET | /system/role/optionselect | 获取角色选择框列表 | system:role:query |
| GET | /system/role/authUser/allocatedList | 查询已分配用户角色列表 | system:role:list |
| GET | /system/role/authUser/unallocatedList | 查询未分配用户角色列表 | system:role:list |
| PUT | /system/role/authUser/cancel | 取消授权用户 | system:role:edit |
| PUT | /system/role/authUser/cancelAll | 批量取消授权用户 | system:role:edit |
| PUT | /system/role/authUser/selectAll | 批量选择用户授权 | system:role:edit |
| GET | /system/role/deptTree/{roleId} | 获取对应角色部门树列表 | system:role:query |

### 1.6 菜单管理

`@Api(tags = "菜单管理")` | 基础路径：`/system/menu`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /system/menu/list | 获取菜单列表 | system:menu:list |
| GET | /system/menu/{menuId} | 根据菜单编号获取详细信息 | system:menu:query |
| GET | /system/menu/treeselect | 获取菜单下拉树列表 | 无 |
| GET | /system/menu/roleMenuTreeselect/{roleId} | 加载对应角色菜单列表树 | 无 |
| POST | /system/menu | 新增菜单 | system:menu:add |
| PUT | /system/menu | 修改菜单 | system:menu:edit |
| DELETE | /system/menu/{menuId} | 删除菜单 | system:menu:remove |

### 1.7 部门管理

`@Api(tags = "部门管理")` | 基础路径：`/system/dept`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /system/dept/list | 获取部门列表 | system:dept:list |
| GET | /system/dept/list/exclude/{deptId} | 查询部门列表（排除节点） | system:dept:list |
| GET | /system/dept/{deptId} | 根据部门编号获取详细信息 | system:dept:query |
| POST | /system/dept | 新增部门 | system:dept:add |
| PUT | /system/dept | 修改部门 | system:dept:edit |
| DELETE | /system/dept/{deptId} | 删除部门 | system:dept:remove |

### 1.8 岗位管理

`@Api(tags = "岗位管理")` | 基础路径：`/system/post`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /system/post/list | 获取岗位列表 | system:post:list |
| POST | /system/post/export | 导出岗位数据 | system:post:export |
| GET | /system/post/{postId} | 根据岗位编号获取详细信息 | system:post:query |
| POST | /system/post | 新增岗位 | system:post:add |
| PUT | /system/post | 修改岗位 | system:post:edit |
| DELETE | /system/post/{postIds} | 删除岗位 | system:post:remove |
| GET | /system/post/optionselect | 获取岗位选择框列表 | 无 |

### 1.9 字典类型

`@Api(tags = "字典类型")` | 基础路径：`/system/dict/type`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /system/dict/type/list | 获取字典类型列表 | system:dict:list |
| POST | /system/dict/type/export | 导出字典类型 | system:dict:export |
| GET | /system/dict/type/{dictId} | 查询字典类型详细 | system:dict:query |
| POST | /system/dict/type | 新增字典类型 | system:dict:add |
| PUT | /system/dict/type | 修改字典类型 | system:dict:edit |
| DELETE | /system/dict/type/{dictIds} | 删除字典类型 | system:dict:remove |
| DELETE | /system/dict/type/refreshCache | 刷新字典缓存 | system:dict:remove |
| GET | /system/dict/type/optionselect | 获取字典选择框列表 | 无 |

### 1.10 字典数据

`@Api(tags = "字典数据")` | 基础路径：`/system/dict/data`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /system/dict/data/list | 获取字典数据列表 | system:dict:list |
| POST | /system/dict/data/export | 导出字典数据 | system:dict:export |
| GET | /system/dict/data/{dictCode} | 查询字典数据详细 | system:dict:query |
| GET | /system/dict/data/type/{dictType} | 根据字典类型查询字典数据信息 | 无 |
| POST | /system/dict/data | 新增字典数据 | system:dict:add |
| PUT | /system/dict/data | 修改字典数据 | system:dict:edit |
| DELETE | /system/dict/data/{dictCodes} | 删除字典数据 | system:dict:remove |

### 1.11 参数配置

`@Api(tags = "参数配置")` | 基础路径：`/system/config`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /system/config/list | 获取参数配置列表 | system:config:list |
| POST | /system/config/export | 导出参数配置 | system:config:export |
| GET | /system/config/{configId} | 根据参数编号获取详细信息 | system:config:query |
| GET | /system/config/configKey/{configKey} | 根据参数键名查询参数值 | 无 |
| POST | /system/config | 新增参数配置 | system:config:add |
| PUT | /system/config | 修改参数配置 | system:config:edit |
| DELETE | /system/config/{configIds} | 删除参数配置 | system:config:remove |
| DELETE | /system/config/refreshCache | 刷新参数缓存 | system:config:remove |

### 1.12 通知公告

`@Api(tags = "通知公告")` | 基础路径：`/system/notice`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /system/notice/list | 获取通知公告列表 | system:notice:list |
| GET | /system/notice/{noticeId} | 根据通知公告编号获取详细信息 | system:notice:query |
| POST | /system/notice | 新增通知公告 | system:notice:add |
| PUT | /system/notice | 修改通知公告 | system:notice:edit |
| DELETE | /system/notice/{noticeIds} | 删除通知公告 | system:notice:remove |

### 1.13 个人信息

`@Api(tags = "个人信息")` | 基础路径：`/system/user/profile`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /system/user/profile | 个人信息 | 无 |
| PUT | /system/user/profile | 修改用户信息 | 无 |
| PUT | /system/user/profile/updatePwd | 修改密码 | 无 |
| POST | /system/user/profile/avatar | 头像上传 | 无 |

### 1.14 通用请求

`@Api(tags = "通用请求")` | 基础路径：`/common`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /common/download | 通用下载请求 | 无 |
| POST | /common/upload | 通用上传请求（单个） | 无 |
| POST | /common/uploads | 通用上传请求（多个） | 无 |
| GET | /common/download/resource | 本地资源通用下载 | 无 |

---

## 二、系统监控

### 2.1 登录日志

`@Api(tags = "登录日志")` | 基础路径：`/monitor/logininfor`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /monitor/logininfor/list | 获取登录日志列表 | monitor:logininfor:list |
| POST | /monitor/logininfor/export | 导出登录日志 | monitor:logininfor:export |
| DELETE | /monitor/logininfor/{infoIds} | 删除登录日志 | monitor:logininfor:remove |
| DELETE | /monitor/logininfor/clean | 清空登录日志 | monitor:logininfor:remove |
| GET | /monitor/logininfor/unlock/{userName} | 账户解锁 | monitor:logininfor:unlock |

### 2.2 操作日志

`@Api(tags = "操作日志")` | 基础路径：`/monitor/operlog`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /monitor/operlog/list | 获取操作日志列表 | monitor:operlog:list |
| POST | /monitor/operlog/export | 导出操作日志 | monitor:operlog:export |
| DELETE | /monitor/operlog/{operIds} | 删除操作日志 | monitor:operlog:remove |
| DELETE | /monitor/operlog/clean | 清空操作日志 | monitor:operlog:remove |

### 2.3 在线用户

`@Api(tags = "在线用户")` | 基础路径：`/monitor/online`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /monitor/online/list | 获取在线用户列表 | monitor:online:list |
| DELETE | /monitor/online/{tokenId} | 强退用户 | monitor:online:forceLogout |

### 2.4 服务监控

`@Api(tags = "服务监控")` | 基础路径：`/monitor/server`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /monitor/server | 获取服务器信息 | monitor:server:list |

### 2.5 缓存监控

`@Api(tags = "缓存监控")` | 基础路径：`/monitor/cache`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /monitor/cache | 获取缓存信息 | monitor:cache:list |
| GET | /monitor/cache/getNames | 获取缓存名称列表 | monitor:cache:list |
| GET | /monitor/cache/getKeys/{cacheName} | 获取缓存键名列表 | monitor:cache:list |
| GET | /monitor/cache/getValue/{cacheName}/{cacheKey} | 获取缓存内容 | monitor:cache:list |
| DELETE | /monitor/cache/clearCacheName/{cacheName} | 清空缓存名称 | monitor:cache:list |
| DELETE | /monitor/cache/clearCacheKey/{cacheKey} | 清空缓存键名 | monitor:cache:list |
| DELETE | /monitor/cache/clearCacheAll | 清空全部缓存 | monitor:cache:list |

---

## 三、住院业务

### 3.1 患者管理

`@Api(tags = "患者管理")` | 基础路径：`/hospital/patient`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /hospital/patient/list | 查询患者列表 | hospital:patient:list |
| POST | /hospital/patient/export | 导出患者列表 | hospital:patient:export |
| GET | /hospital/patient/{id} | 获取患者详细信息 | hospital:patient:query |
| POST | /hospital/patient | 新增患者 | hospital:patient:add |
| PUT | /hospital/patient | 修改患者 | hospital:patient:edit |
| DELETE | /hospital/patient/{ids} | 删除患者 | hospital:patient:remove |

### 3.2 预约挂号管理

`@Api(tags = "预约挂号管理")` | 基础路径：`/hospital/appointment`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /hospital/appointment/list | 查询预约挂号列表 | hospital:appointment:list |
| POST | /hospital/appointment/export | 导出预约挂号列表 | hospital:appointment:export |
| GET | /hospital/appointment/{id} | 获取预约挂号详情 | hospital:appointment:query |
| POST | /hospital/appointment | 新增预约挂号 | hospital:appointment:add |
| PUT | /hospital/appointment | 修改预约挂号 | hospital:appointment:edit |
| DELETE | /hospital/appointment/{ids} | 删除预约挂号 | hospital:appointment:remove |

### 3.3 入院管理

`@Api(tags = "入院管理")` | 基础路径：`/hospital/admission`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /hospital/admission/list | 查询入院列表 | hospital:checkin:list |
| POST | /hospital/admission/export | 导出入院列表 | hospital:checkin:export |
| GET | /hospital/admission/{id} | 获取入院详细信息 | hospital:checkin:query |
| GET | /hospital/admission/detail/{id} | 查询入院详情 | 无 |
| POST | /hospital/admission | 新增入院 | hospital:checkin:add |
| POST | /hospital/admission/apply | 申请入院 | 无 |
| PUT | /hospital/admission | 修改入院 | hospital:checkin:edit |
| DELETE | /hospital/admission/{ids} | 删除入院 | hospital:checkin:remove |

### 3.4 入院合同管理

`@Api(tags = "入院合同管理")` | 基础路径：`/hospital/admissionContract`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /hospital/admissionContract/list | 查询合同列表 | hospital:admissionContract:list |
| POST | /hospital/admissionContract/export | 导出合同列表 | hospital:admissionContract:export |
| GET | /hospital/admissionContract/{id} | 获取合同详细信息 | hospital:admissionContract:query |
| POST | /hospital/admissionContract | 新增合同 | hospital:admissionContract:add |
| PUT | /hospital/admissionContract | 修改合同 | hospital:admissionContract:edit |
| DELETE | /hospital/admissionContract/{ids} | 删除合同 | hospital:admissionContract:remove |

### 3.5 入院评估管理

`@Api(tags = "入院评估管理")` | 基础路径：`/hospital/patientAssessment`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /hospital/patientAssessment/list | 查询入院评估列表 | hospital:patientAssessment:list |
| POST | /hospital/patientAssessment/export | 导出入院评估列表 | hospital:patientAssessment:export |
| GET | /hospital/patientAssessment/{id} | 获取入院评估详细信息 | hospital:patientAssessment:query |
| POST | /hospital/patientAssessment | 新增入院评估 | hospital:patientAssessment:add |
| PUT | /hospital/patientAssessment | 修改入院评估 | hospital:patientAssessment:edit |
| DELETE | /hospital/patientAssessment/{ids} | 删除入院评估 | hospital:patientAssessment:remove |
| POST | /hospital/patientAssessment/upload | 上传体检报告 | hospital:patientAssessment:upload |

### 3.6 护理级别管理

`@Api(tags = "护理级别管理")` | 基础路径：`/hospital/level`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /hospital/level/list | 查询护理级别列表 | hospital:level:list |
| POST | /hospital/level/export | 导出护理级别列表 | hospital:level:export |
| GET | /hospital/level/{id} | 获取护理级别详细信息 | hospital:level:query |
| POST | /hospital/level | 新增护理级别 | hospital:level:add |
| PUT | /hospital/level | 修改护理级别 | hospital:level:edit |
| DELETE | /hospital/level/{ids} | 删除护理级别 | hospital:level:remove |
| GET | /hospital/level/all | 查询所有护理级别信息 | 无 |

### 3.7 照护方案管理

`@Api(tags = "照护方案管理")` | 基础路径：`/hospital/plan`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /hospital/plan/list | 查询照护方案列表 | hospital:plan:list |
| POST | /hospital/plan/export | 导出照护方案列表 | hospital:plan:export |
| GET | /hospital/plan/{id} | 获取照护方案详细信息 | hospital:plan:query |
| POST | /hospital/plan | 新增照护方案 | hospital:plan:add |
| PUT | /hospital/plan | 修改照护方案 | hospital:plan:edit |
| DELETE | /hospital/plan/{id} | 删除照护方案 | hospital:plan:remove |
| GET | /hospital/plan/all | 获取所有照护方案 | 无 |

### 3.8 医嘱项目管理

`@Api(tags = "医嘱项目管理")` | 基础路径：`/hospital/project`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /hospital/project/list | 查询医嘱项目列表 | hospital:project:list |
| POST | /hospital/project/export | 导出医嘱项目列表 | hospital:project:export |
| GET | /hospital/project/{id} | 获取医嘱项目详细信息 | hospital:project:query |
| POST | /hospital/project | 新增医嘱项目 | hospital:project:add |
| PUT | /hospital/project | 修改医嘱项目 | hospital:project:edit |
| DELETE | /hospital/project/{ids} | 删除医嘱项目 | hospital:project:remove |
| GET | /hospital/project/all | 查询所有医嘱项目 | 无 |

### 3.9 责任患者管理

`@Api(tags = "责任患者管理")` | 基础路径：`/patient/hospitalPatient`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| POST | /patient/hospitalPatient/setNursing | 设置责任护士 | 无 |
| GET | /patient/hospitalPatient/list | 查询责任护士患者关联列表 | patient:patient:list |
| POST | /patient/hospitalPatient/export | 导出责任护士患者关联列表 | patient:patient:export |
| GET | /patient/hospitalPatient/{id} | 获取责任护士患者关联详细信息 | patient:patient:query |
| POST | /patient/hospitalPatient | 新增责任护士患者关联 | patient:patient:add |
| PUT | /patient/hospitalPatient | 修改责任护士患者关联 | patient:patient:edit |
| DELETE | /patient/hospitalPatient/{ids} | 删除责任护士患者关联 | patient:patient:remove |

### 3.10 病房类型管理

`@Api(tags = "病房类型管理")` | 基础路径：`/patient/wardRoomType`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /patient/wardRoomType/list | 查询病房类型列表 | 无 |
| GET | /patient/wardRoomType/listAll | 查询所有病房类型列表 | 无 |
| POST | /patient/wardRoomType/export | 导出病房类型列表 | 无 |
| GET | /patient/wardRoomType/{id} | 获取病房类型详细信息 | 无 |
| POST | /patient/wardRoomType | 新增病房类型 | 无 |
| PUT | /patient/wardRoomType | 修改病房类型 | 无 |
| DELETE | /patient/wardRoomType/{ids} | 删除病房类型 | 无 |

### 3.11 楼层管理

`@Api(tags = "楼层管理")` | 基础路径：`/patient/wardFloor`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /patient/wardFloor/list | 查询所有楼层列表 | patient:wardFloor:list |
| GET | /patient/wardFloor/{id} | 获取楼层详细信息 | patient:wardFloor:query |
| POST | /patient/wardFloor | 新增楼层 | patient:wardFloor:add |
| PUT | /patient/wardFloor | 修改楼层 | patient:wardFloor:edit |
| DELETE | /patient/wardFloor/{ids} | 删除楼层 | patient:wardFloor:remove |
| GET | /patient/wardFloor/getAllWardFloorsWithNur | 获取所有楼层（责任患者） | 无 |
| GET | /patient/wardFloor/getAllWardFloorsWithMonitoringDevice | 获取所有有智能设备的楼层 | 无 |
| GET | /patient/wardFloor/getWardRoomAndWardBedByWardBedStatus/{status} | 根据病床状态查询获取所有楼层数据 | 无 |

### 3.12 房间管理

`@Api(tags = "房间管理")` | 基础路径：`/patient/wardRoom`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /patient/wardRoom/list | 查询房间列表 | patient:wardRoom:list |
| GET | /patient/wardRoom/{id} | 获取房间详细信息 | patient:wardRoom:query |
| POST | /patient/wardRoom | 新增房间 | patient:wardRoom:add |
| PUT | /patient/wardRoom | 修改房间 | patient:wardRoom:edit |
| DELETE | /patient/wardRoom/{ids} | 删除房间 | patient:wardRoom:remove |
| GET | /patient/wardRoom/one/{id} | 按照房间id查询楼层、房间、价格 | 无 |
| GET | /patient/wardRoom/getWardRoomsWithNurByWardFloorId/{wardFloorId} | 获取所有房间（责任患者） | 无 |
| GET | /patient/wardRoom/getWardRoomsWithMonitoringDeviceByWardFloorId/{wardFloorId} | 根据楼层ID获取房间中的智能设备及数据 | 无 |
| GET | /patient/wardRoom/getWardRoomsByWardFloorId/{wardFloorId} | 获取所有房间（入院配置） | 无 |

### 3.13 病床管理

`@Api(tags = "病床管理")` | 基础路径：`/patient/wardBed`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /patient/wardBed/list | 查询病床列表 | patient:wardBed:list |
| GET | /patient/wardBed/{id} | 获取病床详细信息 | patient:wardBed:query |
| POST | /patient/wardBed | 新增病床 | patient:wardBed:add |
| PUT | /patient/wardBed | 修改病床 | patient:wardBed:edit |
| DELETE | /patient/wardBed/{ids} | 删除病床 | patient:wardBed:remove |

---

## 四、智能监测

### 4.1 设备管理

`@Api(tags = "设备管理")` | 基础路径：`/hospital/monitoringDevice`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /hospital/monitoringDevice/list | 查询设备列表 | hospital:monitoringDevice:list |
| POST | /hospital/monitoringDevice/syncProductList | 从物联网平台同步产品列表 | hospital:monitoringDevice:sync |
| GET | /hospital/monitoringDevice/allProduct | 查询所有产品列表 | hospital:monitoringDevice:list |
| POST | /hospital/monitoringDevice/register | 注册设备 | 无 |
| GET | /hospital/monitoringDevice/{iotId} | 查询设备详情 | hospital:monitoringDevice:query |
| GET | /hospital/monitoringDevice/queryServiceProperties/{iotId} | 查询设备上报数据 | 无 |
| PUT | /hospital/monitoringDevice | 修改设备 | hospital:monitoringDevice:edit |
| DELETE | /hospital/monitoringDevice/{iotId} | 删除设备 | hospital:monitoringDevice:remove |
| GET | /hospital/monitoringDevice/queryProduct/{productKey} | 查询产品详情 | hospital:monitoringDevice:query |

### 4.2 生命体征数据管理

`@Api(tags = "生命体征数据管理")` | 基础路径：`/hospital/data`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /hospital/data/list | 查询设备数据列表 | patient:data:list |

### 4.3 预警规则管理

`@Api(tags = "预警规则管理")` | 基础路径：`/hospital/warningRule`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /hospital/warningRule/list | 查询预警规则功能列表 | hospital:warningRule:list |
| POST | /hospital/warningRule/export | 导出预警规则功能列表 | hospital:warningRule:export |
| GET | /hospital/warningRule/{id} | 获取预警规则功能详细信息 | hospital:warningRule:query |
| POST | /hospital/warningRule | 新增预警规则功能 | hospital:warningRule:add |
| PUT | /hospital/warningRule | 修改预警规则功能 | hospital:warningRule:edit |
| DELETE | /hospital/warningRule/{ids} | 删除预警规则功能 | hospital:warningRule:remove |

### 4.4 预警数据管理

`@Api(tags = "预警数据管理")` | 基础路径：`/hospital/warningData`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /hospital/warningData/list | 查询预警数据列表 | hospital:data:list |
| POST | /hospital/warningData/export | 导出预警数据列表 | hospital:data:export |
| GET | /hospital/warningData/{id} | 获取预警数据详细信息 | hospital:data:query |
| POST | /hospital/warningData | 新增预警数据 | hospital:data:add |
| PUT | /hospital/warningData | 修改预警数据 | hospital:data:edit |
| DELETE | /hospital/warningData/{ids} | 删除预警数据 | hospital:data:remove |

---

## 五、小程序端

### 5.1 患者联系人管理

`@Api(tags = "患者联系人管理")` | 基础路径：`/member/user`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| POST | /member/user/login | 小程序登录 | 无 |
| GET | /member/user/queryServiceProperties/{iotId} | 查询健康数据 | 无 |

### 5.2 小程序端-预约管理

`@Api(tags = "小程序端-预约管理")` | 基础路径：`/member/appointment`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /member/appointment/cancelled-count | 查询取消预约数量 | 无 |
| GET | /member/appointment/countByTime | 查询当天每个时间段剩余预约次数 | 无 |
| POST | /member/appointment | 新增预约 | 无 |
| GET | /member/appointment/page | 分页查询预约记录 | 无 |
| PUT | /member/appointment/{id}/cancel | 取消预约 | 无 |

### 5.3 小程序端-病房类型

`@Api(tags = "小程序端-病房类型")` | 基础路径：`/member/wardRoomTypes`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /member/wardRoomTypes | 查询房间类型列表 | 无 |

### 5.4 小程序端-医嘱项目

`@Api(tags = "小程序端-医嘱项目")` | 基础路径：`/member/orders/project`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /member/orders/project/page | 分页查询医嘱项目列表 | 无 |
| GET | /member/orders/project/{id} | 根据ID查询医嘱项目信息 | 无 |

### 5.5 客户端-健康数据

`@Api(tags = "客户端-健康数据")` | 基础路径：`/customer/user`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /customer/user/queryVitalSignDataListByDay | 按天统计查询指标数据 | 无 |
| GET | /customer/user/pageQueryWarningData | 按周统计查询指标数据 | 无 |

---

## 六、AI 功能

### 6.1 AI 工具接口

`@Api(tags = "AI 工具接口")` | 基础路径：`/ai/tools`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| GET | /ai/tools/current-plans | 查询当前照护方案 | 无 |
| GET | /ai/tools/beds/remaining | 查询当前剩余病床 | 无 |

### 6.2 AI 机器人接口

`@Api(tags = "AI 机器人接口")` | 基础路径：`/ai`

| HTTP 方法 | 路径 | 说明 | 权限 |
|-----------|------|------|------|
| POST | /ai/conversation/create | 创建 AI 会话 | 无 |
| GET | /ai/conversation/list | 查询 AI 会话历史 | 无 |
| GET | /ai/conversation/{conversationId} | 查询 AI 会话详情 | 无 |
| POST | /ai/chat/send | 发送消息到 AI | 无 |
| POST | /ai/chat/stream | 流式发送消息到 AI | 无 |
| POST | /ai/chat/stop | 停止 AI 生成任务 | 无 |
| DELETE | /ai/conversation/{conversationId} | 删除 AI 会话 | 无 |

---

## 接口统计

| 模块 | Controller 数量 | 接口数量 |
|------|----------------|---------|
| 系统模块（zzyl-admin） | 19 | 109 |
| 住院业务（zzyl-hospital-platform） | 22 | ~120 |
| AI 功能（zzyl-ai） | 2 | 9 |
| **合计** | **43** | **~238** |
