-- Flyway V3: 旧表名/列名 → 新表名/列名 条件重命名
-- 来源：sql/20260422_hospital_schema_refactor.sql 第 8-132 行
-- 说明：每条语句通过 information_schema 判断旧名存在且新名不存在才执行
--       新库场景下全部 no-op，已有旧库自动完成重命名

SET FOREIGN_KEY_CHECKS = 0;

-- ============================================================
-- 1. 旧业务表物理重命名
-- ============================================================

-- elder → patient
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'elder')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'patient'),
  'RENAME TABLE `elder` TO `patient`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- bed → ward_bed
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'bed')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'ward_bed'),
  'RENAME TABLE `bed` TO `ward_bed`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- room → ward_room
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'room')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'ward_room'),
  'RENAME TABLE `room` TO `ward_room`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- floor → ward_floor
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'floor')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'ward_floor'),
  'RENAME TABLE `floor` TO `ward_floor`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- room_type → ward_room_type
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'room_type')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'ward_room_type'),
  'RENAME TABLE `room_type` TO `ward_room_type`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- check_in → admission
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'check_in')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'admission'),
  'RENAME TABLE `check_in` TO `admission`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- check_in_config → admission_config
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'check_in_config')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'admission_config'),
  'RENAME TABLE `check_in_config` TO `admission_config`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- contract → admission_contract
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'contract')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'admission_contract'),
  'RENAME TABLE `contract` TO `admission_contract`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- admissionContract → admission_contract (兼容驼峰旧名)
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'admissionContract')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'admission_contract'),
  'RENAME TABLE `admissionContract` TO `admission_contract`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- health_assessment → patient_assessment
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'health_assessment')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'patient_assessment'),
  'RENAME TABLE `health_assessment` TO `patient_assessment`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- family_member → patient_contact
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'family_member')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'patient_contact'),
  'RENAME TABLE `family_member` TO `patient_contact`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- nursing_project → medical_order_item
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'nursing_project')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'medical_order_item'),
  'RENAME TABLE `nursing_project` TO `medical_order_item`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- nursing_plan → care_plan
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'nursing_plan')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'care_plan'),
  'RENAME TABLE `nursing_plan` TO `care_plan`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- nursing_level → care_level
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'nursing_level')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'care_level'),
  'RENAME TABLE `nursing_level` TO `care_level`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- nursing_project_plan → care_plan_order_item
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'nursing_project_plan')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'care_plan_order_item'),
  'RENAME TABLE `nursing_project_plan` TO `care_plan_order_item`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- nursing_task → care_task
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'nursing_task')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'care_task'),
  'RENAME TABLE `nursing_task` TO `care_task`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- device → monitoring_device
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'device')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'monitoring_device'),
  'RENAME TABLE `device` TO `monitoring_device`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- device_data → vital_sign_data
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'device_data')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'vital_sign_data'),
  'RENAME TABLE `device_data` TO `vital_sign_data`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- alert_rule → warning_rule
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'alert_rule')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'warning_rule'),
  'RENAME TABLE `alert_rule` TO `warning_rule`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- alert_data → warning_data
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'alert_data')
  AND NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = 'warning_data'),
  'RENAME TABLE `alert_data` TO `warning_data`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- ============================================================
-- 2. 关键字段物理重命名
-- ============================================================

-- patient.bed_number → ward_bed_no
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'patient' AND column_name = 'bed_number'),
  'ALTER TABLE `patient` RENAME COLUMN `bed_number` TO `ward_bed_no`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- patient.bed_id → ward_bed_id
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'patient' AND column_name = 'bed_id'),
  'ALTER TABLE `patient` RENAME COLUMN `bed_id` TO `ward_bed_id`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- ward_bed.bed_number → ward_bed_no
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'ward_bed' AND column_name = 'bed_number'),
  'ALTER TABLE `ward_bed` RENAME COLUMN `bed_number` TO `ward_bed_no`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- ward_bed.bed_status → ward_bed_status
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'ward_bed' AND column_name = 'bed_status'),
  'ALTER TABLE `ward_bed` RENAME COLUMN `bed_status` TO `ward_bed_status`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- ward_bed.room_id → ward_room_id
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'ward_bed' AND column_name = 'room_id'),
  'ALTER TABLE `ward_bed` RENAME COLUMN `room_id` TO `ward_room_id`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- ward_room.floor_id → ward_floor_id
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'ward_room' AND column_name = 'floor_id'),
  'ALTER TABLE `ward_room` RENAME COLUMN `floor_id` TO `ward_floor_id`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- admission.elder_id → patient_id
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission' AND column_name = 'elder_id'),
  'ALTER TABLE `admission` RENAME COLUMN `elder_id` TO `patient_id`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- admission.elder_name → patient_name
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission' AND column_name = 'elder_name'),
  'ALTER TABLE `admission` RENAME COLUMN `elder_name` TO `patient_name`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- admission.nursing_level_name → care_level_name
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission' AND column_name = 'nursing_level_name'),
  'ALTER TABLE `admission` RENAME COLUMN `nursing_level_name` TO `care_level_name`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- admission_config.nursing_level_id → care_level_id
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_config' AND column_name = 'nursing_level_id'),
  'ALTER TABLE `admission_config` RENAME COLUMN `nursing_level_id` TO `care_level_id`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- admission_config.nursing_fee → care_fee
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_config' AND column_name = 'nursing_fee'),
  'ALTER TABLE `admission_config` RENAME COLUMN `nursing_fee` TO `care_fee`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- admission_contract.elder_id → patient_id
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_contract' AND column_name = 'elder_id'),
  'ALTER TABLE `admission_contract` RENAME COLUMN `elder_id` TO `patient_id`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- admission_contract.elder_name → patient_name
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_contract' AND column_name = 'elder_name'),
  'ALTER TABLE `admission_contract` RENAME COLUMN `elder_name` TO `patient_name`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- admission_contract.contract_name → admission_contract_name
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_contract' AND column_name = 'contract_name'),
  'ALTER TABLE `admission_contract` RENAME COLUMN `contract_name` TO `admission_contract_name`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- admission_contract.contract_number → admission_contract_no
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_contract' AND column_name = 'contract_number'),
  'ALTER TABLE `admission_contract` RENAME COLUMN `contract_number` TO `admission_contract_no`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- admission_contract.admissionContract_name → admission_contract_name (驼峰兼容)
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_contract' AND column_name = 'admissionContract_name'),
  'ALTER TABLE `admission_contract` RENAME COLUMN `admissionContract_name` TO `admission_contract_name`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- admission_contract.admissionContract_number → admission_contract_no (驼峰兼容)
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'admission_contract' AND column_name = 'admissionContract_number'),
  'ALTER TABLE `admission_contract` RENAME COLUMN `admissionContract_number` TO `admission_contract_no`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- medical_order_item.nursing_requirement → order_requirement
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'medical_order_item' AND column_name = 'nursing_requirement'),
  'ALTER TABLE `medical_order_item` RENAME COLUMN `nursing_requirement` TO `order_requirement`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- care_level.plan_id → care_plan_id
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'care_level' AND column_name = 'plan_id'),
  'ALTER TABLE `care_level` RENAME COLUMN `plan_id` TO `care_plan_id`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

-- care_plan_order_item.project_id → medical_order_item_id
SET @sql = (SELECT IF(
  EXISTS (SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = 'care_plan_order_item' AND column_name = 'project_id'),
  'ALTER TABLE `care_plan_order_item` RENAME COLUMN `project_id` TO `medical_order_item_id`',
  'SELECT 1'
));
PREPARE stmt FROM @sql; EXECUTE stmt; DEALLOCATE PREPARE stmt;

SET FOREIGN_KEY_CHECKS = 1;
