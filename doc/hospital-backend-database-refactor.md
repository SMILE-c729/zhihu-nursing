# 智护云医管后端与数据库物理改造说明

## 改造目标

本次改造将原“中州养老”业务后端进一步物理迁移为医院患者后台管理系统主题，系统名统一为“智护云医管”。和前一轮低风险前端改文案不同，本轮已经同步调整后端包名、类名、接口路径、Mapper、数据库表名、字段名、初始化数据和迁移脚本，使代码与数据库语义都贴合“医院、患者、病床、入出院、医嘱照护、生命监测”。

## 后端结构变化

- 业务包从 `com.zzyl.nursing` 迁移为 `com.zzyl.hospital`。
- Mapper XML 从 `mapper/nursing` 迁移为 `mapper/hospital`。
- Maven 业务模块 artifact 改为 `zzyl-hospital-platform`，项目目录仍保留 `zzyl-nursing-platform`，避免大范围目录引用震荡。
- 原预约挂号缺失的后台控制器已补齐：`AppointmentController`，接口前缀为 `/hospital/appointment`。
- AI 病床余量返回字段同步医院语义：`bedId/bedNumber` 改为 `wardBedId/wardBedNo`。

## 主要命名映射

| 原命名 | 新命名 |
| --- | --- |
| `Elder` / `elder` | `Patient` / `patient` |
| `Bed` / `bed` | `WardBed` / `ward_bed` |
| `Room` / `room` | `WardRoom` / `ward_room` |
| `Floor` / `floor` | `WardFloor` / `ward_floor` |
| `RoomType` / `room_type` | `WardRoomType` / `ward_room_type` |
| `CheckIn` / `check_in` | `Admission` / `admission` |
| `CheckInConfig` / `check_in_config` | `AdmissionConfig` / `admission_config` |
| `Contract` / `contract` | `AdmissionContract` / `admission_contract` |
| `HealthAssessment` / `health_assessment` | `PatientAssessment` / `patient_assessment` |
| `FamilyMember` / `family_member` | `PatientContact` / `patient_contact` |
| `NursingProject` / `nursing_project` | `MedicalOrderItem` / `medical_order_item` |
| `NursingPlan` / `nursing_plan` | `CarePlan` / `care_plan` |
| `NursingLevel` / `nursing_level` | `CareLevel` / `care_level` |
| `NursingProjectPlan` / `nursing_project_plan` | `CarePlanOrderItem` / `care_plan_order_item` |
| `Device` / `device` | `MonitoringDevice` / `monitoring_device` |
| `DeviceData` / `device_data` | `VitalSignData` / `vital_sign_data` |
| `AlertRule` / `alert_rule` | `WarningRule` / `warning_rule` |
| `AlertData` / `alert_data` | `WarningData` / `warning_data` |

## 关键字段变化

- `elder_id`、`elder_name` 改为 `patient_id`、`patient_name`。
- `nursing_level_id`、`nursing_level_name` 改为 `care_level_id`、`care_level_name`。
- `nursing_fee` 改为 `care_fee`。
- `nursing_requirement` 改为 `order_requirement`。
- `bed_number`、`bed_status`、`bed_id` 改为 `ward_bed_no`、`ward_bed_status`、`ward_bed_id`。
- `room_id`、`floor_id` 改为 `ward_room_id`、`ward_floor_id`。
- `device_name`、`device_description` 改为 `monitoring_device_name`、`monitoring_device_description`。
- `alert_*` 相关字段改为 `warning_*`。

## 接口路径变化

- 医嘱照护：`/hospital/project`、`/hospital/plan`、`/hospital/level`。
- 入出院与患者：`/hospital/admission`、`/hospital/admissionContract`、`/hospital/patient`、`/hospital/patientAssessment`。
- 预约挂号：`/hospital/appointment`。
- 病区病床：`/patient/wardFloor`、`/patient/wardRoom`、`/patient/wardBed`、`/patient/wardRoomType`。
- 生命监测：`/hospital/monitoringDevice`、`/hospital/data`、`/hospital/warningRule`、`/hospital/warningData`。

## 数据库脚本

- `sql/zzyl-dev06-init.sql`
  - 初始化表结构已使用医院物理表名和字段名。
  - 菜单、字典、部门、角色、业务示例数据已改为医院主题。
  - 医嘱项目、照护方案、护理级别、预约挂号、入院、合同、患者评估、患者联系人均补充了医院场景示例数据。
  - 历史登录日志和操作日志插入已移除，避免新库导入旧主题操作痕迹。
- `sql/20260422_hospital_schema_refactor.sql`
  - 用于从旧库迁移到新物理命名。
  - 包含表重命名、字段重命名、医院业务表补齐、菜单/字典/部门/角色文案迁移、测试菜单删除和主题示例数据更新。
  - 脚本中出现旧表名/旧字段名仅用于识别旧库来源并执行迁移。

## 前端联动

- `src/api/nursing/*` 中请求地址已切换到新的 `/hospital/*` 和 `/patient/*` 后端接口。
- 业务页面字段已同步为 `patientId`、`patientName`、`wardBedNo`、`careLevelName`、`careFee`、`orderRequirement`、`monitoringDeviceName`、`warningData` 等医院语义。
- 测试菜单页面 `src/views/test` 已删除。
- 修复 dashboard 与智能病床页面的旧图标引用，生产构建不再出现资源路径未解析警告。

## 验证结果

- 后端构建：`mvn -pl zzyl-admin -am -DskipTests package` 通过。
- 前端构建：`npm run build:prod` 通过。
- 旧主题扫描：核心后端源码、Mapper、前端 API、初始化 SQL 和前端展示未再检出旧业务包名、旧后端路径、旧核心表名，以及“养老/老人/退住/测试菜单”等旧主题展示词。
- 数据库迁移脚本已生成但未在本地 MySQL 实例实际导入执行；导入现有库前建议先备份数据库，再执行 `sql/20260422_hospital_schema_refactor.sql`。

## 注意事项

- `zzyl-nursing-platform` 作为磁盘目录名暂未改动，避免 Maven 多模块路径和历史工程配置出现额外波动；对外 artifact 和业务包名已经改为医院主题。
- 角色键 `nursingUser` 如在已有账号授权中存在，可后续单独做权限键迁移；本轮已保证角色展示名为医院语义。
- 若线上已有数据，需要先在测试库执行迁移脚本并校验菜单、权限和核心业务列表，再切换正式库。
