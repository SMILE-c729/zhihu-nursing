-- 智护云医管：医院患者后台主题迁移
-- 说明：只调整展示文案、菜单、字典、示例组织角色和注释，不改接口路径、权限标识、表名或字段名。

UPDATE `sys_menu` SET `menu_name` = '医嘱照护', `remark` = '医嘱照护目录' WHERE `menu_id` = 2000;
UPDATE `sys_menu` SET `menu_name` = '医嘱项目', `remark` = '医嘱项目菜单' WHERE `menu_id` = 2001;
UPDATE `sys_menu` SET `menu_name` = REPLACE(`menu_name`, '护理项目', '医嘱项目') WHERE `parent_id` = 2001;
UPDATE `sys_menu` SET `menu_name` = '护理级别', `remark` = '护理级别菜单' WHERE `menu_id` = 2007;
UPDATE `sys_menu` SET `menu_name` = REPLACE(`menu_name`, '护理等级', '护理级别') WHERE `parent_id` = 2007;
UPDATE `sys_menu` SET `menu_name` = '照护方案', `remark` = '照护方案菜单' WHERE `menu_id` = 2013;
UPDATE `sys_menu` SET `menu_name` = REPLACE(`menu_name`, '护理计划', '照护方案') WHERE `parent_id` = 2013;
UPDATE `sys_menu` SET `menu_name` = '住院管理' WHERE `menu_id` = 2019;
UPDATE `sys_menu` SET `menu_name` = '病房类型' WHERE `menu_id` = 2020;
UPDATE `sys_menu` SET `menu_name` = '病床总览' WHERE `menu_id` = 2021;
UPDATE `sys_menu` SET `menu_name` = '智能病床' WHERE `menu_id` = 2052;
UPDATE `sys_menu` SET `menu_name` = '入出院管理' WHERE `menu_id` = 2022;
UPDATE `sys_menu` SET `menu_name` = '入院办理', `remark` = '入院菜单' WHERE `menu_id` = 2023;
UPDATE `sys_menu` SET `menu_name` = REPLACE(`menu_name`, '入住', '入院') WHERE `parent_id` = 2023;
UPDATE `sys_menu` SET `menu_name` = '入院详情' WHERE `menu_id` = 2035;
UPDATE `sys_menu` SET `menu_name` = '预约挂号' WHERE `menu_id` = 2036;
UPDATE `sys_menu` SET `menu_name` = '预约列表' WHERE `menu_id` = 2038;
UPDATE `sys_menu` SET `menu_name` = '入院评估' WHERE `menu_id` = 2039;
UPDATE `sys_menu` SET `menu_name` = '入院评估详情' WHERE `menu_id` = 2040;
UPDATE `sys_menu` SET `menu_name` = '责任患者' WHERE `menu_id` = 2041;
UPDATE `sys_menu` SET `menu_name` = '医嘱执行详情' WHERE `menu_id` = 2043;
UPDATE `sys_menu` SET `menu_name` = '生命监测' WHERE `menu_id` = 2037;
UPDATE `sys_menu` SET `menu_name` = '新增预警规则' WHERE `menu_id` = 2046;
UPDATE `sys_menu` SET `menu_name` = '预警规则' WHERE `menu_id` = 2047;
UPDATE `sys_menu` SET `menu_name` = '预警数据' WHERE `menu_id` = 2048;

DELETE `rm`
FROM `sys_role_menu` `rm`
JOIN `sys_menu` `m` ON `m`.`menu_id` = `rm`.`menu_id`
WHERE `m`.`menu_name` LIKE '%测试菜单%'
   OR `m`.`path` IN ('test', 'parent', 'child', 'test/parent', 'test/child')
   OR `m`.`component` IN ('test/parent', 'test/child')
   OR `m`.`component` LIKE 'test/%';

DELETE FROM `sys_menu`
WHERE `menu_name` LIKE '%测试菜单%'
   OR `path` IN ('test', 'parent', 'child', 'test/parent', 'test/child')
   OR `component` IN ('test/parent', 'test/child')
   OR `component` LIKE 'test/%';

UPDATE `sys_dict_type` SET `dict_name` = '医嘱项目状态' WHERE `dict_type` = 'medical_order_item_status';
UPDATE `sys_dict_type` SET `dict_name` = '照护方案状态' WHERE `dict_type` = 'care_plan_status';
UPDATE `sys_dict_type` SET `dict_name` = '护理级别状态' WHERE `dict_type` = 'care_level_status';
UPDATE `sys_dict_type` SET `dict_name` = '入院评估-入院状态' WHERE `dict_type` = 'admission_status';
UPDATE `sys_dict_data` SET `dict_label` = '已入院', `remark` = '已入院' WHERE `dict_type` = 'admission_status' AND `dict_value` = '0';
UPDATE `sys_dict_data` SET `dict_label` = '未入院', `remark` = '未入院' WHERE `dict_type` = 'admission_status' AND `dict_value` = '1';

UPDATE `sys_dept` SET `dept_name` = '智护云医管' WHERE `dept_id` = 100 OR `dept_name` IN ('智慧养老院', '中州养老');
UPDATE `sys_dept` SET `dept_name` = '医务管理办公室' WHERE `dept_name` = '高层办公室';
UPDATE `sys_dept` SET `dept_name` = '患者服务部' WHERE `dept_name` IN ('市场部门', '市场部', '销售部');
UPDATE `sys_dept` SET `dept_name` = '财务科' WHERE `dept_name` IN ('财务部门', '财务部');
UPDATE `sys_dept` SET `dept_name` = '设备运维科' WHERE `dept_name` = '运维部门';
UPDATE `sys_dept` SET `dept_name` = '院务部' WHERE `dept_name` = '行政部';
UPDATE `sys_dept` SET `dept_name` = '后勤保障部' WHERE `dept_name` = '后勤部';

UPDATE `sys_role` SET `role_name` = '医护角色', `remark` = '医护角色' WHERE `role_key` = 'common' OR `role_name` = '普通角色';
UPDATE `sys_role` SET `role_name` = '责任护士' WHERE `role_key` = 'nursingUser' OR `role_name` = '护理员';
UPDATE `sys_role` SET `role_name` = '设备维修员' WHERE `role_key` = 'maintainer' OR `role_name` = '维修工';
UPDATE `sys_role` SET `role_name` = '导诊专员' WHERE `role_key` = 'customerService' OR `role_name` = '客服专员';

ALTER TABLE `ward_bed` COMMENT = '病床表';
ALTER TABLE `patient` COMMENT = '患者表';
ALTER TABLE `nurse_patient` COMMENT = '责任护士患者关联表';
ALTER TABLE `care_level` COMMENT = '护理级别表';
ALTER TABLE `care_plan` COMMENT = '照护方案表';
ALTER TABLE `medical_order_item` COMMENT = '医嘱项目表';
ALTER TABLE `care_plan_order_item` COMMENT = '照护方案和医嘱项目关联表';
ALTER TABLE `care_task` COMMENT = '照护任务表';
ALTER TABLE `warning_data` COMMENT = '预警数据';

ALTER TABLE `ward_bed` MODIFY COLUMN `id` bigint NOT NULL AUTO_INCREMENT COMMENT '病床ID';
ALTER TABLE `ward_bed` MODIFY COLUMN `ward_bed_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '病床编号';
ALTER TABLE `ward_bed` MODIFY COLUMN `ward_bed_status` int DEFAULT NULL COMMENT '病床状态: 未入院0, 已入院1';
ALTER TABLE `ward_bed` MODIFY COLUMN `sort` int DEFAULT NULL COMMENT '病床号';
ALTER TABLE `patient` MODIFY COLUMN `status` int NOT NULL DEFAULT '1' COMMENT '状态（0：禁用，1:启用  2:请假 3:出院中 4入院中 5已出院）';
ALTER TABLE `patient` MODIFY COLUMN `ward_bed_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '病床编号';
ALTER TABLE `patient` MODIFY COLUMN `ward_bed_id` bigint DEFAULT NULL COMMENT '病床id';
ALTER TABLE `nurse_patient` MODIFY COLUMN `nurse_id` bigint DEFAULT NULL COMMENT '责任护士id';
ALTER TABLE `nurse_patient` MODIFY COLUMN `patient_id` bigint DEFAULT NULL COMMENT '患者ID';
ALTER TABLE `care_level` MODIFY COLUMN `care_plan_id` int NOT NULL COMMENT '照护方案ID';
ALTER TABLE `care_level` MODIFY COLUMN `fee` decimal(10,2) NOT NULL COMMENT '照护费用';
ALTER TABLE `medical_order_item` MODIFY COLUMN `order_requirement` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '医嘱要求';
ALTER TABLE `care_task` MODIFY COLUMN `nurse_id` varchar(50) DEFAULT NULL COMMENT '责任护士id';
ALTER TABLE `care_task` MODIFY COLUMN `medical_order_item_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '医嘱项目名称';
ALTER TABLE `care_task` MODIFY COLUMN `patient_id` bigint DEFAULT NULL COMMENT '患者ID';
ALTER TABLE `care_task` MODIFY COLUMN `patient_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '患者姓名';
ALTER TABLE `care_task` MODIFY COLUMN `ward_bed_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '病床编号';
ALTER TABLE `warning_rule` MODIFY COLUMN `warning_data_type` int DEFAULT NULL COMMENT '预警数据类型，0：患者异常数据，1：设备异常数据';
ALTER TABLE `warning_rule` MODIFY COLUMN `warning_effective_period` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '预警生效时段';
ALTER TABLE `warning_rule` MODIFY COLUMN `warning_silent_period` int DEFAULT NULL COMMENT '预警沉默周期';
ALTER TABLE `warning_data` MODIFY COLUMN `warning_rule_id` bigint NOT NULL COMMENT '预警规则id';
ALTER TABLE `warning_data` MODIFY COLUMN `warning_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '预警原因，格式：功能名称+运算符+阈值+持续周期+聚合周期';
ALTER TABLE `warning_data` MODIFY COLUMN `type` int NOT NULL COMMENT '预警数据类型，0：患者异常数据，1：设备异常数据';
