-- Flyway Repeatable: 菜单/字典/角色/种子数据同步
-- 说明：每次 Flyway 检测到文件内容变化（checksum 变化）时重新执行
--       所有 INSERT 均使用 ON DUPLICATE KEY UPDATE 保证幂等
--       确保医院主题菜单、字典数据始终处于最新期望状态

-- ============================================================
-- 1. 医院业务菜单（IDs 2000-2052）
-- ============================================================

-- 一级菜单
INSERT INTO `sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`icon`,`menu_type`,`visible`,`status`,`perms`,`is_cache`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2000,'医嘱照护',0,4,'nursing',NULL,'','example','M','0','0','','1','admin','2024-08-14 02:59:12','admin','2024-08-29 06:42:37','医嘱照护目录'),
(2019,'住院管理',0,3,'liveIn',NULL,'','date-range','M','0','0','','1','admin','2024-08-22 06:49:19','admin','2024-08-29 06:42:27',''),
(2022,'入出院管理',0,2,'enterQuit',NULL,'','component','M','0','0','','1','admin','2024-08-23 12:52:34','admin','2024-08-29 06:42:15',''),
(2036,'预约挂号',0,1,'appointment',NULL,'','button','M','0','0',NULL,'1','admin','2024-08-29 06:41:45','',NULL,''),
(2037,'生命监测',0,5,'intelligence',NULL,'','exit-fullscreen','M','0','0',NULL,'1','admin','2024-08-29 06:43:14','',NULL,'')
ON DUPLICATE KEY UPDATE `menu_name`=VALUES(`menu_name`),`path`=VALUES(`path`),`component`=VALUES(`component`),`icon`=VALUES(`icon`),`perms`=VALUES(`perms`),`remark`=VALUES(`remark`),`update_time`=VALUES(`update_time`),`update_by`=VALUES(`update_by`);

-- 医嘱照护子菜单
INSERT INTO `sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`icon`,`menu_type`,`visible`,`status`,`perms`,`is_cache`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2001,'医嘱项目',2000,1,'project','nursing/project/index','','color','C','0','0','hospital:project:list','1','admin','2024-08-14 03:00:15','admin','2025-01-01 14:21:47','医嘱项目菜单'),
(2007,'护理级别',2000,3,'level','nursing/level/index','','guide','C','0','0','hospital:level:list','1','admin','2024-08-14 08:29:05','admin','2025-01-01 14:22:41','护理级别菜单'),
(2013,'照护方案',2000,2,'plan','nursing/plan/index','','druid','C','0','0','hospital:plan:list','1','admin','2024-08-14 08:29:10','admin','2025-01-01 14:22:45','照护方案菜单'),
(2041,'责任患者',2000,4,'oldPeople','nursing/oldPeople/index','','peoples','C','0','0','','1','admin','2024-08-29 06:52:12','admin','2025-01-01 14:22:01',''),
(2043,'医嘱执行详情',2000,1,'arrangeDetails','nursing/arrange/details','','#','C','1','0','','1','admin','2024-08-29 06:53:37','admin','2024-11-03 14:24:57','')
ON DUPLICATE KEY UPDATE `menu_name`=VALUES(`menu_name`),`path`=VALUES(`path`),`component`=VALUES(`component`),`icon`=VALUES(`icon`),`perms`=VALUES(`perms`),`remark`=VALUES(`remark`),`update_time`=VALUES(`update_time`),`update_by`=VALUES(`update_by`);

-- 医嘱项目权限按钮
INSERT INTO `sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`icon`,`menu_type`,`visible`,`status`,`perms`,`is_cache`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2002,'医嘱项目查询',2001,1,'#','','','','F','0','0','hospital:project:query','0','admin','2024-08-14 03:00:15','',NULL,''),
(2003,'医嘱项目新增',2001,2,'#','','','','F','0','0','hospital:project:add','0','admin','2024-08-14 03:00:15','',NULL,''),
(2004,'医嘱项目修改',2001,3,'#','','','','F','0','0','hospital:project:edit','0','admin','2024-08-14 03:00:15','',NULL,''),
(2005,'医嘱项目删除',2001,4,'#','','','','F','0','0','hospital:project:remove','0','admin','2024-08-14 03:00:15','',NULL,''),
(2006,'医嘱项目导出',2001,5,'#','','','','F','0','0','hospital:project:export','0','admin','2024-08-14 03:00:15','',NULL,'')
ON DUPLICATE KEY UPDATE `menu_name`=VALUES(`menu_name`),`perms`=VALUES(`perms`);

-- 护理级别权限按钮
INSERT INTO `sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`icon`,`menu_type`,`visible`,`status`,`perms`,`is_cache`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2008,'护理级别查询',2007,1,'#','','','','F','0','0','hospital:level:query','0','admin','2024-08-14 08:29:05','admin','2024-10-03 10:47:20',''),
(2009,'护理级别新增',2007,2,'#','','','','F','0','0','hospital:level:add','0','admin','2024-08-14 08:29:05','admin','2024-10-03 10:47:27',''),
(2010,'护理级别修改',2007,3,'#','','','','F','0','0','hospital:level:edit','0','admin','2024-08-14 08:29:05','admin','2024-10-03 10:47:32',''),
(2011,'护理级别删除',2007,4,'#','','','','F','0','0','hospital:level:remove','0','admin','2024-08-14 08:29:05','admin','2024-10-03 10:47:37',''),
(2012,'护理级别导出',2007,5,'#','','','','F','0','0','hospital:level:export','0','admin','2024-08-14 08:29:05','admin','2024-10-03 10:47:42','')
ON DUPLICATE KEY UPDATE `menu_name`=VALUES(`menu_name`),`perms`=VALUES(`perms`);

-- 照护方案权限按钮
INSERT INTO `sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`icon`,`menu_type`,`visible`,`status`,`perms`,`is_cache`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2014,'照护方案查询',2013,1,'#','','','','F','0','0','hospital:plan:query','0','admin','2024-08-14 08:29:10','',NULL,''),
(2015,'照护方案新增',2013,2,'#','','','','F','0','0','hospital:plan:add','0','admin','2024-08-14 08:29:10','',NULL,''),
(2016,'照护方案修改',2013,3,'#','','','','F','0','0','hospital:plan:edit','0','admin','2024-08-14 08:29:10','',NULL,''),
(2017,'照护方案删除',2013,4,'#','','','','F','0','0','hospital:plan:remove','0','admin','2024-08-14 08:29:10','',NULL,''),
(2018,'照护方案导出',2013,5,'#','','','','F','0','0','hospital:plan:export','0','admin','2024-08-14 08:29:10','',NULL,'')
ON DUPLICATE KEY UPDATE `menu_name`=VALUES(`menu_name`),`perms`=VALUES(`perms`);

-- 入院办理权限按钮
INSERT INTO `sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`icon`,`menu_type`,`visible`,`status`,`perms`,`is_cache`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2024,'入院查询',2023,1,'#','','','','F','0','0','hospital:admission:query','0','admin','2024-08-23 13:13:48','',NULL,''),
(2025,'入院新增',2023,2,'#','','','','F','0','0','hospital:admission:add','0','admin','2024-08-23 13:13:48','',NULL,''),
(2026,'入院修改',2023,3,'#','','','','F','0','0','hospital:admission:edit','0','admin','2024-08-23 13:13:48','',NULL,''),
(2027,'入院删除',2023,4,'#','','','','F','0','0','hospital:admission:remove','0','admin','2024-08-23 13:13:48','',NULL,''),
(2028,'入院导出',2023,5,'#','','','','F','0','0','hospital:admission:export','0','admin','2024-08-23 13:13:48','',NULL,'')
ON DUPLICATE KEY UPDATE `menu_name`=VALUES(`menu_name`),`perms`=VALUES(`perms`);

-- 入出院管理子菜单
INSERT INTO `sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`icon`,`menu_type`,`visible`,`status`,`perms`,`is_cache`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2023,'入院办理',2022,2,'admission','nursing/checkIn/index','','edit','C','0','0','hospital:admission:list','0','admin','2024-08-23 13:13:48','admin','2024-11-07 03:10:32','入院菜单'),
(2035,'入院详情',2022,0,'admissionInfo','nursing/checkIn/details','','checkbox','C','1','0',NULL,'0','admin','2024-08-24 03:44:48','',NULL,''),
(2039,'入院评估',2022,1,'patientAssessment','nursing/healthAssessment/index','','eye','C','0','0',NULL,'0','admin','2024-08-29 06:48:51','',NULL,''),
(2040,'评估详情',2022,2,'healthDetails','nursing/healthAssessment/details','','#','C','1','0','','0','admin','2024-08-29 06:49:53','admin','2024-08-29 06:50:06','')
ON DUPLICATE KEY UPDATE `menu_name`=VALUES(`menu_name`),`path`=VALUES(`path`),`component`=VALUES(`component`),`icon`=VALUES(`icon`),`perms`=VALUES(`perms`),`remark`=VALUES(`remark`),`update_time`=VALUES(`update_time`),`update_by`=VALUES(`update_by`);

-- 住院管理子菜单
INSERT INTO `sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`icon`,`menu_type`,`visible`,`status`,`perms`,`is_cache`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2020,'病房类型',2019,0,'houseSet','nursing/roomType/index','','size','C','0','0',NULL,'0','admin','2024-08-22 06:50:50','',NULL,''),
(2021,'病床总览',2019,2,'ward_floor','nursing/floor/index','','tree-table','C','0','0',NULL,'0','admin','2024-08-22 08:15:05','',NULL,''),
(2052,'智能病床',2019,3,'smartWardBed','nursing/smartBed/index','','online','C','0','0',NULL,'0','admin','2024-11-15 02:14:10','',NULL,'')
ON DUPLICATE KEY UPDATE `menu_name`=VALUES(`menu_name`),`path`=VALUES(`path`),`component`=VALUES(`component`),`icon`=VALUES(`icon`),`perms`=VALUES(`perms`);

-- 预约挂号子菜单
INSERT INTO `sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`icon`,`menu_type`,`visible`,`status`,`perms`,`is_cache`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2038,'预约列表',2036,1,'appointment','nursing/appointment/index','','dashboard','C','0','0','hospital:appointment:list','0','admin','2024-08-29 06:47:29','',NULL,'')
ON DUPLICATE KEY UPDATE `menu_name`=VALUES(`menu_name`),`path`=VALUES(`path`),`component`=VALUES(`component`),`icon`=VALUES(`icon`),`perms`=VALUES(`perms`);

-- 生命监测子菜单
INSERT INTO `sys_menu` (`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`query`,`icon`,`menu_type`,`visible`,`status`,`perms`,`is_cache`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2044,'设备管理',2037,0,'monitoring_device','nursing/device/index','','tool','C','0','0','','0','admin','2024-08-29 06:54:54','admin','2024-08-29 07:13:42',''),
(2045,'设备详情',2037,0,'details','nursing/device/details','','#','C','1','0',NULL,'0','admin','2024-08-29 06:55:25','',NULL,''),
(2046,'新增预警规则',2037,0,'ruleDetails','nursing/alertRule/details','','#','C','1','0',NULL,'0','admin','2024-08-29 06:56:01','',NULL,''),
(2047,'预警规则',2037,1,'warningRule','nursing/alertRule/index','','nested','C','0','0',NULL,'0','admin','2024-08-29 06:56:48','',NULL,''),
(2048,'预警数据',2037,2,'warningData','nursing/alertData/index','','skill','C','0','0',NULL,'0','admin','2024-08-29 06:57:25','',NULL,'')
ON DUPLICATE KEY UPDATE `menu_name`=VALUES(`menu_name`),`path`=VALUES(`path`),`component`=VALUES(`component`),`icon`=VALUES(`icon`),`perms`=VALUES(`perms`);

-- ============================================================
-- 2. 角色菜单关联（医院菜单授权给管理员角色）
-- ============================================================
-- 角色 2 = 管理员（管理员角色在 RuoYi 默认数据中为 role_id=1，但 dev06 中用的是 2）
-- 使用 INSERT IGNORE 确保不重复插入
INSERT IGNORE INTO `sys_role_menu` (`role_id`, `menu_id`)
SELECT r.role_id, m.menu_id
FROM `sys_role` r
CROSS JOIN `sys_menu` m
WHERE r.role_key = 'admin'
  AND m.menu_id BETWEEN 2000 AND 2052;

-- 医护角色（common）获得医院菜单权限
INSERT IGNORE INTO `sys_role_menu` (`role_id`, `menu_id`)
SELECT r.role_id, m.menu_id
FROM `sys_role` r
CROSS JOIN `sys_menu` m
WHERE r.role_key = 'common'
  AND m.menu_id BETWEEN 2000 AND 2052
  AND m.menu_type IN ('M', 'C');

-- 责任护士（nursingUser）获得医嘱照护相关权限
INSERT IGNORE INTO `sys_role_menu` (`role_id`, `menu_id`)
SELECT r.role_id, m.menu_id
FROM `sys_role` r
CROSS JOIN `sys_menu` m
WHERE r.role_key = 'nursingUser'
  AND m.menu_id IN (2000,2001,2002,2007,2008,2013,2014,2041,2043,2000,2019,2020,2021,2022,2023,2024,2035,2039,2040,2052);

-- ============================================================
-- 3. 医院主题字典数据
-- ============================================================

DELETE FROM `sys_dict_data`
WHERE `dict_type` IN (
  'medical_order_item_status',
  'care_plan_status',
  'care_level_status',
  'admission_status',
  'admission_type',
  'care_task_status',
  'appointment_status',
  'contract_status',
  'in_out_status'
);

-- 医嘱项目状态字典
INSERT INTO `sys_dict_type` (`dict_type`,`dict_name`,`status`,`create_by`,`create_time`,`remark`) VALUES
('medical_order_item_status','医嘱项目状态','0','admin',NOW(),'医嘱项目状态字典')
ON DUPLICATE KEY UPDATE `dict_name`=VALUES(`dict_name`);

INSERT INTO `sys_dict_data` (`dict_type`,`dict_label`,`dict_value`,`dict_sort`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`remark`) VALUES
('medical_order_item_status','禁用','0',1,'','danger','N','0','admin',NOW(),'禁用'),
('medical_order_item_status','启用','1',2,'','primary','Y','0','admin',NOW(),'启用')
ON DUPLICATE KEY UPDATE `dict_label`=VALUES(`dict_label`),`dict_sort`=VALUES(`dict_sort`);

-- 照护方案状态字典
INSERT INTO `sys_dict_type` (`dict_type`,`dict_name`,`status`,`create_by`,`create_time`,`remark`) VALUES
('care_plan_status','照护方案状态','0','admin',NOW(),'照护方案状态字典')
ON DUPLICATE KEY UPDATE `dict_name`=VALUES(`dict_name`);

INSERT INTO `sys_dict_data` (`dict_type`,`dict_label`,`dict_value`,`dict_sort`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`remark`) VALUES
('care_plan_status','禁用','0',1,'','danger','N','0','admin',NOW(),'禁用'),
('care_plan_status','启用','1',2,'','primary','Y','0','admin',NOW(),'启用')
ON DUPLICATE KEY UPDATE `dict_label`=VALUES(`dict_label`),`dict_sort`=VALUES(`dict_sort`);

-- 护理级别状态字典
INSERT INTO `sys_dict_type` (`dict_type`,`dict_name`,`status`,`create_by`,`create_time`,`remark`) VALUES
('care_level_status','护理级别状态','0','admin',NOW(),'护理级别状态字典')
ON DUPLICATE KEY UPDATE `dict_name`=VALUES(`dict_name`);

INSERT INTO `sys_dict_data` (`dict_type`,`dict_label`,`dict_value`,`dict_sort`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`remark`) VALUES
('care_level_status','禁用','0',1,'','danger','N','0','admin',NOW(),'禁用'),
('care_level_status','启用','1',2,'','primary','Y','0','admin',NOW(),'启用')
ON DUPLICATE KEY UPDATE `dict_label`=VALUES(`dict_label`),`dict_sort`=VALUES(`dict_sort`);

-- 入院评估-入院状态字典
INSERT INTO `sys_dict_type` (`dict_type`,`dict_name`,`status`,`create_by`,`create_time`,`remark`) VALUES
('admission_status','入院评估-入院状态','0','admin',NOW(),'入院状态字典')
ON DUPLICATE KEY UPDATE `dict_name`=VALUES(`dict_name`);

INSERT INTO `sys_dict_data` (`dict_type`,`dict_label`,`dict_value`,`dict_sort`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`remark`) VALUES
('admission_status','已入院','0',1,'','success','N','0','admin',NOW(),'已入院'),
('admission_status','未入院','1',2,'','info','Y','0','admin',NOW(),'未入院')
ON DUPLICATE KEY UPDATE `dict_label`=VALUES(`dict_label`),`remark`=VALUES(`remark`);

-- 入出院类型字典
INSERT INTO `sys_dict_type` (`dict_type`,`dict_name`,`status`,`create_by`,`create_time`,`remark`) VALUES
('admission_type','入出院类型','0','admin',NOW(),'入出院类型字典')
ON DUPLICATE KEY UPDATE `dict_name`=VALUES(`dict_name`);

INSERT INTO `sys_dict_data` (`dict_type`,`dict_label`,`dict_value`,`dict_sort`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`remark`) VALUES
('admission_type','门诊','0',1,'','primary','N','0','admin',NOW(),'门诊'),
('admission_type','住院','1',2,'','success','N','0','admin',NOW(),'住院'),
('admission_type','急诊','2',3,'','danger','N','0','admin',NOW(),'急诊')
ON DUPLICATE KEY UPDATE `dict_label`=VALUES(`dict_label`),`dict_sort`=VALUES(`dict_sort`);

-- 照护任务状态字典
INSERT INTO `sys_dict_type` (`dict_type`,`dict_name`,`status`,`create_by`,`create_time`,`remark`) VALUES
('care_task_status','照护任务状态','0','admin',NOW(),'照护任务状态字典')
ON DUPLICATE KEY UPDATE `dict_name`=VALUES(`dict_name`);

INSERT INTO `sys_dict_data` (`dict_type`,`dict_label`,`dict_value`,`dict_sort`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`remark`) VALUES
('care_task_status','待执行','1',1,'','warning','Y','0','admin',NOW(),'待执行'),
('care_task_status','已执行','2',2,'','success','N','0','admin',NOW(),'已执行'),
('care_task_status','已关闭','3',3,'','info','N','0','admin',NOW(),'已关闭')
ON DUPLICATE KEY UPDATE `dict_label`=VALUES(`dict_label`),`dict_sort`=VALUES(`dict_sort`);

-- 预约状态字典
INSERT INTO `sys_dict_type` (`dict_type`,`dict_name`,`status`,`create_by`,`create_time`,`remark`) VALUES
('appointment_status','预约状态','0','admin',NOW(),'预约状态字典')
ON DUPLICATE KEY UPDATE `dict_name`=VALUES(`dict_name`);

INSERT INTO `sys_dict_data` (`dict_type`,`dict_label`,`dict_value`,`dict_sort`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`remark`) VALUES
('appointment_status','已预约','0',1,'','primary','Y','0','admin',NOW(),'已预约'),
('appointment_status','已完成','1',2,'','success','N','0','admin',NOW(),'已完成'),
('appointment_status','已取消','2',3,'','danger','N','0','admin',NOW(),'已取消'),
('appointment_status','已过期','3',4,'','info','N','0','admin',NOW(),'已过期')
ON DUPLICATE KEY UPDATE `dict_label`=VALUES(`dict_label`),`dict_sort`=VALUES(`dict_sort`);

-- 合同状态字典
INSERT INTO `sys_dict_type` (`dict_type`,`dict_name`,`status`,`create_by`,`create_time`,`remark`) VALUES
('contract_status','合同状态','0','admin',NOW(),'合同状态字典')
ON DUPLICATE KEY UPDATE `dict_name`=VALUES(`dict_name`);

INSERT INTO `sys_dict_data` (`dict_type`,`dict_label`,`dict_value`,`dict_sort`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`remark`) VALUES
('contract_status','未生效','0',1,'','info','Y','0','admin',NOW(),'未生效'),
('contract_status','已生效','1',2,'','success','N','0','admin',NOW(),'已生效'),
('contract_status','已过期','2',3,'','warning','N','0','admin',NOW(),'已过期'),
('contract_status','已失效','3',4,'','danger','N','0','admin',NOW(),'已失效')
ON DUPLICATE KEY UPDATE `dict_label`=VALUES(`dict_label`),`dict_sort`=VALUES(`dict_sort`);

-- 入院出院状态字典
INSERT INTO `sys_dict_type` (`dict_type`,`dict_name`,`status`,`create_by`,`create_time`,`remark`) VALUES
('in_out_status','入院出院状态','0','admin',NOW(),'入院出院状态字典')
ON DUPLICATE KEY UPDATE `dict_name`=VALUES(`dict_name`);

INSERT INTO `sys_dict_data` (`dict_type`,`dict_label`,`dict_value`,`dict_sort`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`remark`) VALUES
('in_out_status','已入院','0',1,'','success','Y','0','admin',NOW(),'已入院'),
('in_out_status','已出院','1',2,'','info','N','0','admin',NOW(),'已出院')
ON DUPLICATE KEY UPDATE `dict_label`=VALUES(`dict_label`),`dict_sort`=VALUES(`dict_sort`);

-- ============================================================
-- 4. 系统配置同步
-- ============================================================
DELETE FROM `sys_config`
WHERE `config_key` IN ('sys.index.title', 'sys.login.title');

INSERT INTO `sys_config` (`config_name`,`config_key`,`config_value`,`config_type`,`create_by`,`create_time`,`remark`) VALUES
('系统标题','sys.index.title','智护云医管','Y','admin',NOW(),'系统标题'),
('用户登录标题','sys.login.title','智护云医管','Y','admin',NOW(),'用户登录标题')
ON DUPLICATE KEY UPDATE `config_value`=VALUES(`config_value`);
