-- 智护云医管：医院患者后台物理命名迁移
-- 适用：从旧养老库升级到医院患者管理主题。
-- 注意：本脚本尽量使用动态 SQL 做存在性判断，重复执行时会跳过已完成的重命名。

SET FOREIGN_KEY_CHECKS = 0;

-- 1. 旧业务表物理重命名
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'elder')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'patient'),
  'RENAME TABLE `elder` TO `patient`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'bed') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'ward_bed'), 'RENAME TABLE `bed` TO `ward_bed`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'room') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'ward_room'), 'RENAME TABLE `room` TO `ward_room`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'floor') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'ward_floor'), 'RENAME TABLE `floor` TO `ward_floor`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'room_type') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'ward_room_type'), 'RENAME TABLE `room_type` TO `ward_room_type`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'check_in') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'admission'), 'RENAME TABLE `check_in` TO `admission`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'check_in_config') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'admission_config'), 'RENAME TABLE `check_in_config` TO `admission_config`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'contract') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'admission_contract'), 'RENAME TABLE `contract` TO `admission_contract`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'admissionContract') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'admission_contract'), 'RENAME TABLE `admissionContract` TO `admission_contract`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'health_assessment') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'patient_assessment'), 'RENAME TABLE `health_assessment` TO `patient_assessment`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'family_member') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'patient_contact'), 'RENAME TABLE `family_member` TO `patient_contact`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'nursing_project') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'medical_order_item'), 'RENAME TABLE `nursing_project` TO `medical_order_item`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'nursing_plan') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'care_plan'), 'RENAME TABLE `nursing_plan` TO `care_plan`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'nursing_level') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'care_level'), 'RENAME TABLE `nursing_level` TO `care_level`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'nursing_project_plan') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'care_plan_order_item'), 'RENAME TABLE `nursing_project_plan` TO `care_plan_order_item`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'nursing_task') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'care_task'), 'RENAME TABLE `nursing_task` TO `care_task`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'device') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'monitoring_device'), 'RENAME TABLE `device` TO `monitoring_device`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'device_data') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'vital_sign_data'), 'RENAME TABLE `device_data` TO `vital_sign_data`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'alert_rule') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'warning_rule'), 'RENAME TABLE `alert_rule` TO `warning_rule`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'alert_data') AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'warning_data'), 'RENAME TABLE `alert_data` TO `warning_data`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- 2. 关键字段物理重命名
SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'patient' AND column_name = 'bed_number'), 'ALTER TABLE `patient` RENAME COLUMN `bed_number` TO `ward_bed_no`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'patient' AND column_name = 'bed_id'), 'ALTER TABLE `patient` RENAME COLUMN `bed_id` TO `ward_bed_id`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'ward_bed' AND column_name = 'bed_number'), 'ALTER TABLE `ward_bed` RENAME COLUMN `bed_number` TO `ward_bed_no`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'ward_bed' AND column_name = 'bed_status'), 'ALTER TABLE `ward_bed` RENAME COLUMN `bed_status` TO `ward_bed_status`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'ward_bed' AND column_name = 'room_id'), 'ALTER TABLE `ward_bed` RENAME COLUMN `room_id` TO `ward_room_id`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'ward_room' AND column_name = 'floor_id'), 'ALTER TABLE `ward_room` RENAME COLUMN `floor_id` TO `ward_floor_id`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission' AND column_name = 'elder_id'), 'ALTER TABLE `admission` RENAME COLUMN `elder_id` TO `patient_id`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission' AND column_name = 'elder_name'), 'ALTER TABLE `admission` RENAME COLUMN `elder_name` TO `patient_name`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission' AND column_name = 'nursing_level_name'), 'ALTER TABLE `admission` RENAME COLUMN `nursing_level_name` TO `care_level_name`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_config' AND column_name = 'nursing_level_id'), 'ALTER TABLE `admission_config` RENAME COLUMN `nursing_level_id` TO `care_level_id`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_config' AND column_name = 'nursing_fee'), 'ALTER TABLE `admission_config` RENAME COLUMN `nursing_fee` TO `care_fee`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_contract' AND column_name = 'elder_id'), 'ALTER TABLE `admission_contract` RENAME COLUMN `elder_id` TO `patient_id`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_contract' AND column_name = 'elder_name'), 'ALTER TABLE `admission_contract` RENAME COLUMN `elder_name` TO `patient_name`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_contract' AND column_name = 'contract_name'), 'ALTER TABLE `admission_contract` RENAME COLUMN `contract_name` TO `admission_contract_name`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_contract' AND column_name = 'contract_number'), 'ALTER TABLE `admission_contract` RENAME COLUMN `contract_number` TO `admission_contract_no`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_contract' AND column_name = 'admissionContract_name'), 'ALTER TABLE `admission_contract` RENAME COLUMN `admissionContract_name` TO `admission_contract_name`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_contract' AND column_name = 'admissionContract_number'), 'ALTER TABLE `admission_contract` RENAME COLUMN `admissionContract_number` TO `admission_contract_no`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'medical_order_item' AND column_name = 'nursing_requirement'), 'ALTER TABLE `medical_order_item` RENAME COLUMN `nursing_requirement` TO `order_requirement`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'care_level' AND column_name = 'plan_id'), 'ALTER TABLE `care_level` RENAME COLUMN `plan_id` TO `care_plan_id`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET @sql = (SELECT IF(EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'care_plan_order_item' AND column_name = 'project_id'), 'ALTER TABLE `care_plan_order_item` RENAME COLUMN `project_id` TO `medical_order_item_id`', 'SELECT 1'));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- 3. 补齐新增医院业务表
CREATE TABLE IF NOT EXISTS `appointment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '预约ID',
  `name` varchar(50) DEFAULT NULL COMMENT '预约人姓名',
  `mobile` varchar(30) DEFAULT NULL COMMENT '预约人手机号',
  `time` datetime DEFAULT NULL COMMENT '预约时间',
  `visitor` varchar(50) DEFAULT NULL COMMENT '就诊人姓名',
  `type` int DEFAULT '0' COMMENT '预约类型 0门诊 1复诊 2住院探视',
  `status` int DEFAULT '0' COMMENT '预约状态 0已预约 1已完成 2已取消 3已过期',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='预约挂号表';

CREATE TABLE IF NOT EXISTS `admission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '入院ID',
  `patient_name` varchar(50) DEFAULT NULL COMMENT '患者姓名',
  `patient_id` bigint DEFAULT NULL COMMENT '患者ID',
  `id_card_no` varchar(30) DEFAULT NULL COMMENT '身份证号',
  `start_date` datetime DEFAULT NULL COMMENT '入院开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '入院结束时间',
  `care_level_name` varchar(50) DEFAULT NULL COMMENT '护理级别名称',
  `ward_bed_no` varchar(30) DEFAULT NULL COMMENT '病床编号',
  `status` int DEFAULT '0' COMMENT '状态 0已入院 1已出院',
  `sort_order` int DEFAULT NULL COMMENT '排序编号',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入院办理表';

CREATE TABLE IF NOT EXISTS `admission_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '入院配置ID',
  `admission_id` bigint DEFAULT NULL COMMENT '入院ID',
  `care_level_id` bigint DEFAULT NULL COMMENT '护理级别ID',
  `care_level_name` varchar(50) DEFAULT NULL COMMENT '护理级别名称',
  `fee_start_date` datetime DEFAULT NULL COMMENT '费用开始时间',
  `fee_end_date` datetime DEFAULT NULL COMMENT '费用结束时间',
  `deposit` decimal(10,2) DEFAULT NULL COMMENT '押金',
  `care_fee` decimal(10,2) DEFAULT NULL COMMENT '照护费用',
  `ward_bed_fee` decimal(10,2) DEFAULT NULL COMMENT '病床费用',
  `insurance_payment` decimal(10,2) DEFAULT NULL COMMENT '医保支付',
  `government_subsidy` decimal(10,2) DEFAULT NULL COMMENT '政府补贴',
  `other_fees` decimal(10,2) DEFAULT NULL COMMENT '其他费用',
  `sort_order` int DEFAULT NULL COMMENT '排序编号',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入院费用配置表';

CREATE TABLE IF NOT EXISTS `admission_contract` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '合同ID',
  `patient_id` bigint DEFAULT NULL COMMENT '患者ID',
  `admission_contract_name` varchar(100) DEFAULT NULL COMMENT '合同名称',
  `admission_contract_no` varchar(80) DEFAULT NULL COMMENT '合同编号',
  `agreement_path` varchar(255) DEFAULT NULL COMMENT '协议地址',
  `third_party_phone` varchar(30) DEFAULT NULL COMMENT '联系人手机号',
  `third_party_name` varchar(50) DEFAULT NULL COMMENT '联系人姓名',
  `patient_name` varchar(50) DEFAULT NULL COMMENT '患者姓名',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `status` int DEFAULT '0' COMMENT '状态 0未生效 1已生效 2已过期 3已失效',
  `sign_date` datetime DEFAULT NULL COMMENT '签约日期',
  `termination_submitter` varchar(50) DEFAULT NULL COMMENT '解除提交人',
  `termination_date` datetime DEFAULT NULL COMMENT '解除日期',
  `termination_agreement_path` varchar(255) DEFAULT NULL COMMENT '解除协议地址',
  `sort_order` int DEFAULT NULL COMMENT '排序编号',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_admission_contract_no` (`admission_contract_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入院合同表';

CREATE TABLE IF NOT EXISTS `patient_assessment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评估ID',
  `patient_name` varchar(50) DEFAULT NULL COMMENT '患者姓名',
  `id_card` varchar(30) DEFAULT NULL COMMENT '身份证号',
  `birth_date` datetime DEFAULT NULL COMMENT '出生日期',
  `age` int DEFAULT NULL COMMENT '年龄',
  `gender` int DEFAULT NULL COMMENT '性别 0男 1女',
  `health_score` varchar(20) DEFAULT NULL COMMENT '健康评分',
  `risk_level` varchar(30) DEFAULT NULL COMMENT '风险等级',
  `suggestion_for_admission` int DEFAULT NULL COMMENT '是否建议入院',
  `care_level_name` varchar(50) DEFAULT NULL COMMENT '推荐护理级别',
  `admission_status` int DEFAULT '1' COMMENT '入院情况 0已入院 1未入院',
  `total_check_date` varchar(50) DEFAULT NULL COMMENT '体检日期',
  `physical_exam_institution` varchar(100) DEFAULT NULL COMMENT '体检机构',
  `physical_report_url` varchar(255) DEFAULT NULL COMMENT '体检报告URL',
  `assessment_time` datetime DEFAULT NULL COMMENT '评估时间',
  `report_summary` varchar(1000) DEFAULT NULL COMMENT '报告总结',
  `disease_risk` varchar(1000) DEFAULT NULL COMMENT '疾病风险',
  `abnormal_analysis` varchar(1000) DEFAULT NULL COMMENT '异常分析',
  `system_score` varchar(20) DEFAULT NULL COMMENT '健康系统分值',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入院评估表';

CREATE TABLE IF NOT EXISTS `patient_contact` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '患者联系人ID',
  `phone` varchar(30) DEFAULT NULL COMMENT '手机号',
  `name` varchar(50) DEFAULT NULL COMMENT '联系人姓名',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `open_id` varchar(100) DEFAULT NULL COMMENT '微信OpenID',
  `gender` int DEFAULT NULL COMMENT '性别 0男 1女',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_patient_contact_open_id` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='患者联系人表';

-- 4. 菜单、权限和基础数据切换到医院语义
UPDATE `sys_config` SET `config_value` = '智护云医管' WHERE `config_key` IN ('sys.index.title', 'sys.login.title');
UPDATE `sys_dept` SET `dept_name` = '智护云医管' WHERE `dept_id` = 100 OR `dept_name` IN ('智慧养老院', '中州养老');
UPDATE `sys_dept` SET `dept_name` = '院长办公室' WHERE `dept_name` IN ('高层办公室', '高层办公室 ');
UPDATE `sys_dept` SET `dept_name` = '患者服务部' WHERE `dept_name` IN ('市场部门', '市场部', '销售部');
UPDATE `sys_dept` SET `dept_name` = '设备运维科' WHERE `dept_name` IN ('运维部门', '运维部');
UPDATE `sys_role` SET `role_name` = '医护角色', `remark` = '医护角色' WHERE `role_key` = 'common' OR `role_name` = '普通角色';
UPDATE `sys_role` SET `role_name` = '责任护士' WHERE `role_key` = 'nursingUser' OR `role_name` = '护理员';
UPDATE `sys_role` SET `role_name` = '导诊专员' WHERE `role_key` = 'customerService' OR `role_name` = '客服专员';

UPDATE `sys_menu` SET `menu_name` = '预约挂号' WHERE `menu_id` = 2036;
UPDATE `sys_menu` SET `menu_name` = '预约列表', `perms` = 'hospital:appointment:list' WHERE `menu_id` = 2038;
UPDATE `sys_menu` SET `menu_name` = '入出院管理' WHERE `menu_id` = 2022;
UPDATE `sys_menu` SET `menu_name` = '入院办理', `perms` = 'hospital:admission:list' WHERE `menu_id` = 2023;
UPDATE `sys_menu` SET `menu_name` = '住院管理' WHERE `menu_id` = 2019;
UPDATE `sys_menu` SET `menu_name` = '病房类型' WHERE `menu_id` = 2020;
UPDATE `sys_menu` SET `menu_name` = '病床总览' WHERE `menu_id` = 2021;
UPDATE `sys_menu` SET `menu_name` = '医嘱照护' WHERE `menu_id` = 2000;
UPDATE `sys_menu` SET `menu_name` = '医嘱项目', `perms` = 'hospital:project:list' WHERE `menu_id` = 2001;
UPDATE `sys_menu` SET `menu_name` = '照护方案', `perms` = 'hospital:plan:list' WHERE `menu_id` = 2013;
UPDATE `sys_menu` SET `menu_name` = '护理级别', `perms` = 'hospital:level:list' WHERE `menu_id` = 2007;
UPDATE `sys_menu` SET `menu_name` = '责任患者' WHERE `menu_id` = 2041;
UPDATE `sys_menu` SET `menu_name` = '生命监测' WHERE `menu_id` = 2037;
UPDATE `sys_menu` SET `menu_name` = '预警规则' WHERE `menu_id` = 2047;
UPDATE `sys_menu` SET `menu_name` = '预警数据' WHERE `menu_id` = 2048;

DELETE `rm`
FROM `sys_role_menu` `rm`
JOIN `sys_menu` `m` ON `m`.`menu_id` = `rm`.`menu_id`
WHERE `m`.`menu_name` LIKE '%测试菜单%'
   OR `m`.`path` IN ('test', 'parent', 'child', 'test/parent', 'test/child')
   OR `m`.`component` LIKE 'test/%';

DELETE FROM `sys_menu`
WHERE `menu_name` LIKE '%测试菜单%'
   OR `path` IN ('test', 'parent', 'child', 'test/parent', 'test/child')
   OR `component` LIKE 'test/%';

UPDATE `sys_dict_type` SET `dict_name` = '医嘱项目状态' WHERE `dict_type` = 'medical_order_item_status';
UPDATE `sys_dict_type` SET `dict_name` = '照护方案状态' WHERE `dict_type` = 'care_plan_status';
UPDATE `sys_dict_type` SET `dict_name` = '护理级别状态' WHERE `dict_type` = 'care_level_status';
UPDATE `sys_dict_type` SET `dict_name` = '入院评估-入院状态' WHERE `dict_type` = 'admission_status';
UPDATE `sys_dict_data` SET `dict_label` = '已入院', `remark` = '已入院' WHERE `dict_type` = 'admission_status' AND `dict_value` = '0';
UPDATE `sys_dict_data` SET `dict_label` = '未入院', `remark` = '未入院' WHERE `dict_type` = 'admission_status' AND `dict_value` = '1';

-- 5. 医院主题示例数据
INSERT INTO `appointment` (`id`, `name`, `mobile`, `time`, `visitor`, `type`, `status`, `create_by`, `create_time`, `remark`)
VALUES
  (1001,'赵敏','13800010001','2026-04-22 09:30:00','赵敏',0,0,'1','2026-04-22 08:30:00','心内科初诊'),
  (1002,'王强','13800010002','2026-04-22 10:00:00','王强',1,0,'1','2026-04-22 08:35:00','糖尿病复诊')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`), `mobile` = VALUES(`mobile`), `time` = VALUES(`time`), `visitor` = VALUES(`visitor`), `type` = VALUES(`type`), `status` = VALUES(`status`), `remark` = VALUES(`remark`);

UPDATE `care_plan` SET `plan_name` = '术后监护照护方案' WHERE `id` = 133;
UPDATE `care_plan` SET `plan_name` = '慢病住院照护方案' WHERE `id` = 134;
UPDATE `care_plan` SET `plan_name` = '康复训练照护方案' WHERE `id` = 135;
UPDATE `care_level` SET `name` = '特级护理', `fee` = 3200.00, `description` = '适用于术后监护、生命体征异常风险较高患者' WHERE `id` = 76;
UPDATE `care_level` SET `name` = '一级护理', `fee` = 2200.00, `description` = '适用于病情稳定但需要密切照护的住院患者' WHERE `id` = 77;
UPDATE `care_level` SET `name` = '康复护理', `fee` = 1800.00, `description` = '适用于康复训练、慢病随访和出院前评估患者' WHERE `id` = 78;
UPDATE `medical_order_item` SET `name` = '生命体征监测', `unit` = '次', `price` = 12.00, `order_requirement` = '按医嘱记录体温、脉搏、呼吸、血压' WHERE `id` = 1;
UPDATE `medical_order_item` SET `name` = '静脉输液巡视', `unit` = '次', `price` = 18.00, `order_requirement` = '核对输液通路、滴速和患者反应' WHERE `id` = 2;
UPDATE `medical_order_item` SET `name` = '口服用药提醒', `unit` = '次', `price` = 8.00, `order_requirement` = '按时提醒患者服药并登记执行结果' WHERE `id` = 3;
UPDATE `medical_order_item` SET `name` = '伤口换药协助', `unit` = '次', `price` = 45.00, `order_requirement` = '准备换药物品并协助医生完成处理' WHERE `id` = 4;
UPDATE `medical_order_item` SET `name` = '血糖监测', `unit` = '次', `price` = 15.00, `order_requirement` = '餐前或医嘱时间测量血糖并记录' WHERE `id` = 5;

SET FOREIGN_KEY_CHECKS = 1;
