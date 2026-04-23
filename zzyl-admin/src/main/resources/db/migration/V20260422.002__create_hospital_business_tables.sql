-- Flyway V2: 医院业务表（最终状态 DDL）
-- 来源：sql/zzyl-dev06-init.sql 权威 DDL
-- 说明：CREATE TABLE IF NOT EXISTS 安全跳过已存在表；INSERT 带 ON DUPLICATE KEY UPDATE 保证幂等

-- ============================================================
-- 1. 病房类型表
-- ============================================================
CREATE TABLE IF NOT EXISTS `ward_room_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '病房类型名称',
  `ward_bed_count` int NOT NULL DEFAULT '0' COMMENT '病床数量',
  `price` decimal(10,2) NOT NULL COMMENT '病床费用',
  `introduction` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '介绍',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '照片',
  `status` tinyint NOT NULL COMMENT '状态，0：禁用，1：启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='病房类型表';

INSERT INTO `ward_room_type` (`id`,`name`,`ward_bed_count`,`price`,`introduction`,`photo`,`status`,`create_time`,`update_time`,`create_by`,`update_by`,`remark`) VALUES
(1,'单人套房',0,4000.00,'宽敞舒适的套房，配备独立卫生间和基本生活设施，满足独自居住的需求，提供私密性和舒适度','https://yjy-slwl-oss.oss-cn-hangzhou.aliyuncs.com/e2f1031b-e23e-4379-95d4-ce8fe382f58f.png',1,'2023-09-26 15:57:50','2024-10-03 23:54:34',1671403256519078153,1,NULL),
(2,'双人套房',0,6000.00,'适合夫妻或朋友两人居住的套房，设有独立卫生间和基本生活设施，提供共享空间和私密性','https://yjy-slwl-oss.oss-cn-hangzhou.aliyuncs.com/ff84c185-2e28-431c-951d-d004cc2d5bdc.png',1,'2023-09-26 15:58:51','2023-09-26 15:58:51',1671403256519078153,NULL,NULL),
(3,'豪华单人间',0,3000.00,'豪华装修的单人房间，提供舒适的居住环境和高品质的服务，设计精美，配备独立卫生间和必需设施','https://yjy-slwl-oss.oss-cn-hangzhou.aliyuncs.com/d803832c-5b93-4cae-ba95-aeb52ab0c5e0.png',1,'2023-09-26 15:59:33','2023-09-26 15:59:33',1671403256519078153,NULL,NULL),
(4,'豪华双人间',0,4500.00,'精心装修的双人房间，提供舒适和豪华的居住环境，配备独立卫生间和高品质的家具','https://yjy-slwl-oss.oss-cn-hangzhou.aliyuncs.com/c3522da7-4c5c-48d2-94f9-9f0b95a048d2.png',1,'2023-09-26 16:00:03','2024-08-22 16:12:20',1671403256519078153,1,NULL),
(5,'普通单人间',0,2000.00,'简洁实用的单人房间，提供基本的居住设施和舒适度，适合独自居住的老年人，提供相对经济实惠的居住选择','https://yjy-slwl-oss.oss-cn-hangzhou.aliyuncs.com/1a330b1c-b0a1-463d-8d9a-221ef17c314f.png',1,'2023-09-26 16:00:27','2023-09-26 16:00:27',1671403256519078153,NULL,NULL),
(116,'标准双人间',10,1500.00,'适合好朋友一起居住','https://itheim.oss-cn-beijing.aliyuncs.com/91c4a814-efd5-4093-a5ac-963b41047019.png',1,'2024-09-12 22:52:36','2025-06-09 22:53:07',1,1,'')
ON DUPLICATE KEY UPDATE `name`=VALUES(`name`),`ward_bed_count`=VALUES(`ward_bed_count`),`price`=VALUES(`price`),`introduction`=VALUES(`introduction`),`photo`=VALUES(`photo`),`status`=VALUES(`status`),`update_time`=VALUES(`update_time`),`update_by`=VALUES(`update_by`);

-- ============================================================
-- 2. 楼层表
-- ============================================================
CREATE TABLE IF NOT EXISTS `ward_floor` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `code` bigint DEFAULT NULL COMMENT '编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=414 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='楼层表';

INSERT INTO `ward_floor` (`id`,`name`,`code`,`create_time`,`update_time`,`create_by`,`update_by`,`remark`) VALUES
(1,'1楼',11,'2023-09-26 16:10:27','2024-05-24 17:57:28',1671403256519078153,1,NULL),
(2,'2楼',2,'2023-09-26 17:37:20','2023-09-26 17:37:20',1671403256519078138,NULL,NULL),
(3,'3楼',3,'2023-09-26 17:37:26','2023-09-26 17:37:26',1671403256519078138,NULL,NULL),
(4,'4楼',4,'2023-09-26 17:37:32','2023-09-26 17:37:32',1671403256519078138,NULL,NULL),
(5,'5楼',5,'2023-09-26 17:37:38','2023-09-26 17:37:38',1671403256519078138,NULL,NULL),
(6,'6楼',6,'2023-09-26 17:37:42','2023-09-26 17:37:59',1671403256519078138,1671403256519078138,NULL),
(7,'7楼',7,'2023-09-26 17:37:47','2023-09-26 17:37:52',1671403256519078138,1671403256519078138,NULL),
(8,'8楼',8,'2023-09-26 17:38:09','2023-09-26 17:38:09',1671403256519078138,NULL,NULL),
(391,'9楼',8,'2023-12-18 14:53:50','2023-12-18 14:53:50',1671403256519078138,NULL,NULL),
(401,'10楼',9,'2023-12-26 19:29:54','2023-12-27 10:15:34',1671403256519078138,1671403256519078138,NULL),
(406,'12楼',1,'2024-05-27 14:07:32','2024-05-27 14:14:46',1,1,NULL)
ON DUPLICATE KEY UPDATE `name`=VALUES(`name`),`code`=VALUES(`code`),`update_time`=VALUES(`update_time`),`update_by`=VALUES(`update_by`);

-- ============================================================
-- 3. 房间表
-- ============================================================
CREATE TABLE IF NOT EXISTS `ward_room` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '房间编号',
  `sort` int DEFAULT NULL COMMENT '排序号',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '房间类型名称',
  `ward_floor_id` bigint DEFAULT NULL COMMENT '楼层id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='房间表';

INSERT INTO `ward_room` (`id`,`code`,`sort`,`type_name`,`ward_floor_id`,`create_time`,`update_time`,`is_deleted`,`create_by`,`update_by`,`remark`) VALUES
(1,'101',1,'豪华单人间',1,'2023-09-26 17:38:25','2023-12-23 16:19:55',0,1671403256519078138,1671403256519078138,NULL),
(2,'102',2,'普通双人间',1,'2023-09-26 17:38:32','2023-09-26 17:38:32',0,1671403256519078138,NULL,NULL),
(3,'103',3,'普通单人间',1,'2023-09-26 17:38:41','2023-09-26 17:38:41',0,1671403256519078138,NULL,NULL),
(4,'104',4,'豪华双人间',1,'2023-09-26 17:38:48','2023-09-26 17:38:48',0,1671403256519078138,NULL,NULL),
(5,'105',5,'豪华单人间',1,'2023-09-26 17:38:55','2023-09-26 17:38:55',0,1671403256519078138,NULL,NULL),
(6,'106',6,'双人套房',1,'2023-09-26 17:39:05','2023-09-26 17:39:05',0,1671403256519078138,NULL,NULL),
(7,'107',7,'单人套房',1,'2023-09-26 17:39:13','2023-09-26 17:39:13',0,1671403256519078138,NULL,NULL),
(8,'201',1,'特护房',2,'2023-09-26 17:42:02','2023-09-26 17:42:02',0,1671403256519078138,NULL,NULL),
(9,'202',2,'普通双人间',2,'2023-09-26 17:42:08','2023-09-26 17:42:08',0,1671403256519078138,NULL,NULL),
(10,'203',3,'普通单人间',2,'2023-09-26 17:42:15','2023-09-26 17:42:15',0,1671403256519078138,NULL,NULL),
(11,'204',4,'豪华双人间',2,'2023-09-26 17:42:22','2023-09-26 17:42:22',0,1671403256519078138,NULL,NULL),
(12,'205',5,'豪华单人间',2,'2023-09-26 17:42:30','2023-09-26 17:42:30',0,1671403256519078138,NULL,NULL),
(13,'206',6,'双人套房',2,'2023-09-26 17:42:41','2023-09-26 17:42:41',0,1671403256519078138,NULL,NULL),
(14,'207',7,'单人套房',2,'2023-09-26 17:42:48','2023-09-26 17:42:48',0,1671403256519078138,NULL,NULL),
(15,'301',1,'特护房',3,'2023-09-26 17:43:54','2023-09-26 17:43:54',0,1671403256519078138,NULL,NULL),
(16,'302',2,'普通双人间',3,'2023-09-26 17:44:01','2023-09-26 17:44:01',0,1671403256519078138,NULL,NULL),
(17,'303',3,'普通单人间',3,'2023-09-26 17:44:09','2023-09-26 17:44:09',0,1671403256519078138,NULL,NULL),
(18,'304',4,'豪华双人间',3,'2023-09-26 17:44:17','2023-09-26 17:44:17',0,1671403256519078138,NULL,NULL),
(19,'305',5,'豪华单人间',3,'2023-09-26 17:44:24','2023-09-26 17:44:24',0,1671403256519078138,NULL,NULL),
(20,'306',6,'双人套房',3,'2023-09-26 17:44:33','2023-09-26 17:44:33',0,1671403256519078138,NULL,NULL),
(21,'307',7,'单人套房',3,'2023-09-26 17:44:42','2023-09-26 17:44:42',0,1671403256519078138,NULL,NULL),
(22,'401',1,'特护房',4,'2023-09-26 18:51:10','2023-09-26 18:51:10',0,1671403256519078138,NULL,NULL),
(23,'402',2,'普通双人间',4,'2023-09-26 18:51:17','2023-09-26 18:51:17',0,1671403256519078138,NULL,NULL),
(24,'403',3,'普通单人间',4,'2023-09-26 18:51:23','2023-09-26 18:51:23',0,1671403256519078138,NULL,NULL),
(25,'404',4,'豪华双人间',4,'2023-09-26 18:51:32','2023-09-26 18:51:32',0,1671403256519078138,NULL,NULL),
(26,'405',5,'豪华单人间',4,'2023-09-26 18:51:42','2023-09-26 18:51:42',0,1671403256519078138,NULL,NULL),
(27,'406',6,'双人套房',4,'2023-09-26 18:51:54','2023-09-26 18:51:54',0,1671403256519078138,NULL,NULL),
(28,'407',7,'单人套房',4,'2023-09-26 18:52:03','2023-09-26 18:52:03',0,1671403256519078138,NULL,NULL),
(29,'501',1,'特护房',5,'2023-09-26 18:53:54','2023-09-26 18:53:54',0,1671403256519078138,NULL,NULL),
(31,'502',2,'普通双人间',5,'2023-09-26 18:54:05','2023-09-26 18:54:05',0,1671403256519078138,NULL,NULL),
(32,'503',3,'普通单人间',5,'2023-09-26 18:54:12','2023-09-26 18:54:12',0,1671403256519078138,NULL,NULL),
(33,'504',4,'豪华双人间',5,'2023-09-26 18:54:20','2023-09-26 18:54:20',0,1671403256519078138,NULL,NULL),
(34,'505',5,'豪华单人间',5,'2023-09-26 18:54:28','2023-09-26 18:54:28',0,1671403256519078138,NULL,NULL),
(35,'506',6,'双人套房',5,'2023-09-26 18:54:37','2023-09-26 18:54:37',0,1671403256519078138,NULL,NULL),
(36,'507',7,'单人套房',5,'2023-09-26 18:54:47','2023-09-26 18:54:47',0,1671403256519078138,NULL,NULL),
(37,'601',1,'特护房',6,'2023-09-26 18:57:14','2023-09-26 18:57:14',0,1671403256519078138,NULL,NULL),
(38,'602',2,'普通双人间',6,'2023-09-26 18:57:20','2023-09-26 18:57:20',0,1671403256519078138,NULL,NULL),
(39,'603',3,'普通单人间',6,'2023-09-26 18:57:28','2023-09-26 18:57:28',0,1671403256519078138,NULL,NULL),
(40,'604',4,'豪华双人间',6,'2023-09-26 18:57:36','2023-09-26 18:57:36',0,1671403256519078138,NULL,NULL),
(41,'605',5,'豪华单人间',6,'2023-09-26 19:01:36','2023-09-26 19:01:36',0,1671403256519078138,NULL,NULL),
(42,'606',6,'双人套房',6,'2023-09-26 19:01:45','2023-09-26 19:01:45',0,1671403256519078138,NULL,NULL),
(43,'607',7,'单人套房',6,'2023-09-26 19:01:54','2023-09-26 19:01:54',0,1671403256519078138,NULL,NULL),
(44,'701',1,'特护房',7,'2023-09-26 19:02:13','2023-09-26 19:02:13',0,1671403256519078138,NULL,NULL),
(45,'702',2,'普通双人间',7,'2023-09-26 19:02:20','2023-09-26 19:02:20',0,1671403256519078138,NULL,NULL),
(46,'703',3,'普通单人间',7,'2023-09-26 19:02:28','2023-09-26 19:02:28',0,1671403256519078138,NULL,NULL),
(47,'704',4,'豪华双人间',7,'2023-09-26 19:02:49','2023-09-26 19:02:49',0,1671403256519078138,NULL,NULL),
(48,'705',5,'豪华单人间',7,'2023-09-26 19:03:00','2023-09-26 19:03:00',0,1671403256519078138,NULL,NULL),
(49,'706',6,'双人套房',7,'2023-09-26 19:03:07','2023-09-26 19:03:07',0,1671403256519078138,NULL,NULL),
(50,'707',7,'单人套房',7,'2023-09-26 19:03:15','2023-09-26 19:03:15',0,1671403256519078138,NULL,NULL),
(51,'801',1,'特护房',8,'2023-09-26 19:03:49','2023-09-26 19:03:49',0,1671403256519078138,NULL,NULL),
(52,'802',2,'普通双人间',8,'2023-09-26 19:03:57','2023-09-26 19:03:57',0,1671403256519078138,NULL,NULL),
(53,'803',3,'普通单人间',8,'2023-09-26 19:04:04','2023-09-26 19:04:04',0,1671403256519078138,NULL,NULL),
(54,'804',4,'豪华双人间',8,'2023-09-26 19:04:13','2023-09-26 19:04:13',0,1671403256519078138,NULL,NULL),
(55,'805',5,'豪华单人间',8,'2023-09-26 19:04:45','2023-09-26 19:04:45',0,1671403256519078138,NULL,NULL),
(56,'806',6,'双人套房',8,'2023-09-26 19:04:52','2023-09-26 19:04:52',0,1671403256519078138,NULL,NULL),
(57,'807',7,'单人套房',8,'2023-09-26 19:05:00','2023-09-26 19:05:00',0,1671403256519078138,NULL,NULL),
(67,'108',8,'普通单人间',1,'2023-12-23 16:12:46','2023-12-23 17:08:47',0,1671403256519078138,1671403256519078138,NULL),
(81,'109',9,'单人套房',1,'2024-05-27 10:28:37',NULL,0,1,NULL,NULL),
(83,'1201',1,'双人套房',406,'2024-05-27 14:14:54',NULL,0,1,NULL,NULL),
(86,'102-2',1,'豪华单人间',406,'2024-08-22 18:49:21',NULL,0,1,NULL,NULL)
ON DUPLICATE KEY UPDATE `code`=VALUES(`code`),`sort`=VALUES(`sort`),`type_name`=VALUES(`type_name`),`ward_floor_id`=VALUES(`ward_floor_id`),`update_time`=VALUES(`update_time`),`is_deleted`=VALUES(`is_deleted`),`update_by`=VALUES(`update_by`);

-- ============================================================
-- 4. 病床表
-- ============================================================
CREATE TABLE IF NOT EXISTS `ward_bed` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '病床ID',
  `ward_bed_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '病床编号',
  `ward_bed_status` int DEFAULT NULL COMMENT '病床状态: 未入院0, 已入院1',
  `sort` int DEFAULT NULL COMMENT '病床号',
  `ward_room_id` bigint DEFAULT NULL COMMENT '房间ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ward_bed_no` (`ward_bed_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='病床表';

INSERT INTO `ward_bed` (`id`,`ward_bed_no`,`ward_bed_status`,`sort`,`ward_room_id`,`create_time`,`update_time`,`create_by`,`update_by`,`remark`) VALUES
(1,'101-1',1,1,1,'2023-09-26 17:39:53','2023-10-05 15:59:24',1671403256519078138,1671403256519078164,NULL),
(2,'102-1',0,1,2,'2023-09-26 17:40:01','2023-10-05 16:00:05',1671403256519078138,1671403256519078164,NULL),
(3,'102-2',0,2,2,'2023-09-26 17:40:09','2023-10-05 15:59:45',1671403256519078138,1671403256519078164,NULL),
(4,'103-1',0,1,3,'2023-09-26 17:40:42','2023-10-05 16:00:21',1671403256519078138,1671403256519078164,NULL),
(5,'104-1',1,1,4,'2023-09-26 17:40:49','2023-09-26 17:45:39',1671403256519078138,1671403256519078138,NULL),
(6,'104-2',1,2,4,'2023-09-26 17:40:54','2023-10-20 23:22:12',1671403256519078138,1671403256519078164,NULL),
(7,'105-1',0,1,5,'2023-09-26 17:41:09','2023-09-26 17:45:52',1671403256519078138,1671403256519078138,NULL),
(8,'106-1',0,1,6,'2023-09-26 17:41:16','2023-09-26 17:45:58',1671403256519078138,1671403256519078138,NULL),
(9,'106-2',0,2,6,'2023-09-26 17:41:24','2023-09-26 17:46:04',1671403256519078138,1671403256519078138,NULL),
(10,'107-1',0,1,7,'2023-09-26 17:41:32','2023-12-21 09:37:49',1671403256519078138,1671403256519078138,NULL),
(11,'201-1',0,1,8,'2023-09-26 17:44:53','2023-10-13 10:40:27',1671403256519078138,1671403256519078164,NULL),
(12,'202-1',0,1,9,'2023-09-26 17:46:33','2023-09-26 17:46:33',1671403256519078138,NULL,NULL),
(13,'202-2',0,2,9,'2023-09-26 17:46:47','2023-09-26 17:46:47',1671403256519078138,NULL,NULL),
(14,'203-1',0,1,10,'2023-09-26 18:43:58','2023-09-26 18:43:58',1671403256519078138,NULL,NULL),
(15,'204-1',0,1,11,'2023-09-26 18:44:03','2023-09-26 18:44:03',1671403256519078138,NULL,NULL),
(16,'204-2',0,2,11,'2023-09-26 18:44:12','2023-09-26 18:44:12',1671403256519078138,NULL,NULL),
(18,'205-1',0,1,12,'2023-09-26 18:44:23','2023-12-20 18:40:07',1671403256519078138,1671403256519078138,NULL),
(19,'206-1',0,1,13,'2023-09-26 18:44:36','2023-12-20 21:43:10',1671403256519078138,1671403256519078138,NULL),
(20,'206-2',0,2,13,'2023-09-26 18:44:42','2023-09-26 18:44:48',1671403256519078138,1671403256519078138,NULL),
(21,'207-1',0,1,14,'2023-09-26 18:45:01','2023-09-26 18:45:01',1671403256519078138,NULL,NULL),
(22,'301-2',0,1,15,'2023-09-26 18:45:26','2023-12-26 19:35:06',1671403256519078138,1671403256519078138,NULL),
(23,'302-1',0,1,16,'2023-09-26 18:45:31','2023-09-26 18:45:31',1671403256519078138,NULL,NULL),
(24,'302-3',0,2,16,'2023-09-26 18:45:39','2023-12-26 19:35:15',1671403256519078138,1671403256519078138,NULL),
(25,'303-1',0,1,17,'2023-09-26 18:45:44','2023-09-26 18:45:44',1671403256519078138,NULL,NULL),
(27,'304-1',0,1,18,'2023-09-26 18:45:55','2023-09-26 18:45:55',1671403256519078138,NULL,NULL),
(28,'304-2',0,2,18,'2023-09-26 18:46:04','2023-09-26 18:46:04',1671403256519078138,NULL,NULL),
(29,'305-1',0,1,19,'2023-09-26 18:46:11','2023-12-21 10:04:47',1671403256519078138,1671403256519078138,NULL),
(30,'306-1',0,1,20,'2023-09-26 18:46:16','2023-09-26 18:46:16',1671403256519078138,NULL,NULL),
(31,'306-2',0,2,20,'2023-09-26 18:46:22','2023-09-26 19:08:50',1671403256519078138,1671403256519078138,NULL),
(32,'307-1',0,1,21,'2023-09-26 18:46:29','2023-09-26 18:46:29',1671403256519078138,NULL,NULL),
(33,'401-1',0,1,22,'2023-09-26 18:52:14','2023-09-26 18:52:14',1671403256519078138,NULL,NULL),
(34,'402-1',0,1,23,'2023-09-26 18:52:22','2023-09-26 18:52:22',1671403256519078138,NULL,NULL),
(35,'402-2',0,2,23,'2023-09-26 18:52:35','2023-09-26 18:52:38',1671403256519078138,1671403256519078138,NULL),
(36,'403-1',0,1,24,'2023-09-26 18:52:47','2023-09-26 18:52:47',1671403256519078138,NULL,NULL),
(37,'404-1',0,1,25,'2023-09-26 18:52:54','2023-09-26 18:52:54',1671403256519078138,NULL,NULL),
(38,'404-2',0,2,25,'2023-09-26 18:53:02','2023-09-26 18:53:10',1671403256519078138,1671403256519078138,NULL),
(39,'405-1',0,1,26,'2023-09-26 18:53:18','2023-09-26 18:53:18',1671403256519078138,NULL,NULL),
(40,'406-1',0,1,27,'2023-09-26 18:53:27','2023-09-26 18:53:27',1671403256519078138,NULL,NULL),
(41,'406-2',0,2,27,'2023-09-26 18:53:36','2023-09-26 18:53:36',1671403256519078138,NULL,NULL),
(42,'407-1',0,1,28,'2023-09-26 18:53:44','2023-09-26 18:53:44',1671403256519078138,NULL,NULL),
(43,'501-1',0,1,29,'2023-09-26 18:55:47','2023-09-26 18:55:47',1671403256519078138,NULL,NULL),
(44,'502-1',0,1,31,'2023-09-26 18:55:52','2023-09-26 18:55:52',1671403256519078138,NULL,NULL),
(45,'502-2',0,2,31,'2023-09-26 18:56:02','2023-09-26 18:56:02',1671403256519078138,NULL,NULL),
(46,'503-1',0,1,32,'2023-09-26 18:56:10','2023-09-26 18:56:10',1671403256519078138,NULL,NULL),
(48,'504-1',0,1,33,'2023-09-26 18:56:26','2023-09-26 18:56:26',1671403256519078138,NULL,NULL),
(49,'504-2',0,2,33,'2023-09-26 18:56:32','2023-09-26 18:56:32',1671403256519078138,NULL,NULL),
(50,'505-1',0,1,34,'2023-09-26 18:56:37','2023-09-26 18:56:37',1671403256519078138,NULL,NULL),
(52,'506-1',0,1,35,'2023-09-26 18:56:49','2023-09-26 18:56:49',1671403256519078138,NULL,NULL),
(53,'506-2',0,2,35,'2023-09-26 18:56:54','2023-09-26 18:56:54',1671403256519078138,NULL,NULL),
(54,'507-1',0,1,36,'2023-09-26 18:57:00','2023-09-26 18:57:00',1671403256519078138,NULL,NULL),
(55,'601-1',0,1,37,'2023-09-26 19:05:11','2023-09-28 22:53:28',1671403256519078138,1671403256519078164,NULL),
(56,'602-1',0,1,38,'2023-09-26 19:05:16','2023-09-26 19:05:16',1671403256519078138,NULL,NULL),
(57,'602-2',0,2,38,'2023-09-26 19:05:24','2023-09-26 19:05:24',1671403256519078138,NULL,NULL),
(58,'603-1',0,1,39,'2023-09-26 19:05:29','2023-09-26 19:05:29',1671403256519078138,NULL,NULL),
(59,'604-1',0,1,40,'2023-09-26 19:05:33','2023-09-26 19:05:33',1671403256519078138,NULL,NULL),
(60,'604-2',0,2,40,'2023-09-26 19:05:38','2023-09-26 19:05:38',1671403256519078138,NULL,NULL),
(61,'605-1',0,1,41,'2023-09-26 19:05:43','2023-09-26 19:05:43',1671403256519078138,NULL,NULL),
(62,'606-1',0,1,42,'2023-09-26 19:05:48','2023-09-26 19:05:48',1671403256519078138,NULL,NULL),
(63,'606-2',0,2,42,'2023-09-26 19:05:54','2023-09-26 19:05:54',1671403256519078138,NULL,NULL),
(64,'607-1',0,1,43,'2023-09-26 19:05:59','2023-09-26 19:05:59',1671403256519078138,NULL,NULL),
(65,'701-1',0,1,44,'2023-09-26 19:06:10','2023-09-26 19:06:10',1671403256519078138,NULL,NULL),
(66,'702-1',0,1,45,'2023-09-26 19:06:14','2023-09-26 19:06:26',1671403256519078138,1671403256519078138,NULL),
(68,'702-2',0,2,45,'2023-09-26 19:06:35','2023-09-26 19:06:35',1671403256519078138,NULL,NULL),
(69,'703-1',0,1,46,'2023-09-26 19:06:41','2023-09-26 19:06:41',1671403256519078138,NULL,NULL),
(70,'704-1',0,1,47,'2023-09-26 19:06:46','2023-12-20 14:28:22',1671403256519078138,1671403256519078138,NULL),
(71,'704-2',0,2,47,'2023-09-26 19:06:52','2023-09-26 19:06:57',1671403256519078138,1671403256519078138,NULL),
(72,'705-1',0,1,48,'2023-09-26 19:07:04','2023-09-26 19:07:04',1671403256519078138,NULL,NULL),
(73,'706-1',0,1,49,'2023-09-26 19:07:10','2023-09-26 19:07:10',1671403256519078138,NULL,NULL),
(74,'706-2',0,2,49,'2023-09-26 19:07:14','2023-09-26 19:07:19',1671403256519078138,1671403256519078138,NULL),
(75,'707-1',0,1,50,'2023-09-26 19:07:25','2023-09-26 19:07:25',1671403256519078138,NULL,NULL),
(76,'801-1',0,1,51,'2023-09-26 19:07:41','2023-09-26 19:07:41',1671403256519078138,NULL,NULL),
(77,'803-1',0,1,53,'2023-09-26 19:07:46','2023-09-26 19:07:46',1671403256519078138,NULL,NULL),
(78,'805-1',0,1,55,'2023-09-26 19:07:51','2023-09-26 19:07:51',1671403256519078138,NULL,NULL),
(79,'807-1',0,1,57,'2023-09-26 19:07:56','2023-09-26 19:07:56',1671403256519078138,1,NULL),
(80,'802-1',0,1,52,'2023-09-26 19:08:04','2023-09-26 19:08:04',1671403256519078138,NULL,NULL),
(81,'801-2',0,2,52,'2023-09-26 19:08:09','2023-09-26 19:08:09',1671403256519078138,NULL,NULL),
(82,'804-1',0,1,54,'2023-09-26 19:08:15','2023-09-26 19:08:15',1671403256519078138,NULL,NULL),
(83,'804-2',0,2,54,'2023-09-26 19:08:22','2023-09-26 19:08:22',1671403256519078138,NULL,NULL),
(84,'806-1',0,1,56,'2023-09-26 19:08:28','2023-09-26 19:08:28',1671403256519078138,NULL,NULL),
(85,'806-2',0,2,56,'2023-09-26 19:08:35','2023-09-26 19:08:35',1671403256519078138,1,NULL),
(170,'101-2',1,2,1,'2023-12-21 11:45:09','2023-12-21 11:45:09',1671403256519078138,1,NULL),
(171,'103-2',0,1,3,'2023-12-23 16:12:34','2023-12-23 16:22:03',1671403256519078138,1671403256519078138,NULL),
(173,'108-1',0,1,67,'2023-12-23 17:59:23','2024-11-03 23:31:50',1671403256519078138,1,NULL),
(177,'1011',0,1,74,'2023-12-26 19:32:07','2023-12-26 19:32:07',1671403256519078138,NULL,NULL),
(178,'101',0,1,74,'2023-12-26 19:32:15','2023-12-26 19:32:15',1671403256519078138,NULL,NULL),
(196,'109-01',0,1,81,'2024-07-31 11:29:18',NULL,1,NULL,NULL),
(200,'1201-1',NULL,1,83,'2024-10-04 00:03:31',NULL,1,NULL,NULL),
(201,'109-2',NULL,2,81,'2025-01-02 00:37:05',NULL,1,NULL,NULL),
(202,'108-2',NULL,2,67,'2025-01-02 00:37:32',NULL,1,NULL,NULL)
ON DUPLICATE KEY UPDATE `ward_bed_no`=VALUES(`ward_bed_no`),`ward_bed_status`=VALUES(`ward_bed_status`),`sort`=VALUES(`sort`),`ward_room_id`=VALUES(`ward_room_id`),`update_time`=VALUES(`update_time`),`update_by`=VALUES(`update_by`);

-- ============================================================
-- 5. 患者表
-- ============================================================
CREATE TABLE IF NOT EXISTS `patient` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片',
  `id_card_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '身份证号',
  `sex` int DEFAULT NULL COMMENT '性别（0:女  1:男）',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态（0：禁用，1:启用  2:请假 3:出院中 4入院中 5已出院）',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `birthday` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '出生日期',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '家庭住址',
  `id_card_national_emblem_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '身份证国徽面',
  `id_card_portrait_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '身份证人像面',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `ward_bed_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '病床编号',
  `ward_bed_id` bigint DEFAULT NULL COMMENT '病床id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name_id_card_no` (`name`,`id_card_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='患者表';

INSERT INTO `patient` (`id`,`name`,`image`,`id_card_no`,`sex`,`status`,`phone`,`birthday`,`address`,`id_card_national_emblem_img`,`id_card_portrait_img`,`create_time`,`update_time`,`create_by`,`update_by`,`remark`,`ward_bed_no`,`ward_bed_id`) VALUES
(325,'13211223322','https://itheim.oss-cn-beijing.aliyuncs.com/1fb24c20-ec99-44b3-8691-766c5508b347.jpg','132123196712131234',1,1,'13211223322','2024-08-22','13211223322','https://itheim.oss-cn-beijing.aliyuncs.com/b79d8d1e-1015-4d04-83e9-a22d3cc47869.jpg','https://itheim.oss-cn-beijing.aliyuncs.com/07ee1b38-b611-4955-8339-fad02a6d7fc6.jpg','2024-08-27 16:43:20',NULL,1,NULL,NULL,'104-1',5),
(326,'李天龙','https://itheim.oss-cn-beijing.aliyuncs.com/8aef5eb9-436f-4ece-b68f-34d957d6b050.png','132123196712131239',1,1,'13222334433','1967-12-13','北京市昌平区','https://itheim.oss-cn-beijing.aliyuncs.com/816d7d6c-d13e-4ec6-9879-68d4f8054935.jpg','https://itheim.oss-cn-beijing.aliyuncs.com/f96f4666-c58f-4f69-bf15-8a521788330f.jpg','2024-08-27 16:50:09',NULL,1,NULL,NULL,'104-2',6),
(327,'老李','https://itheim.oss-cn-beijing.aliyuncs.com/1510df9e-fca8-4a35-9443-8e4bacad0e03.png','132123195612132345',1,1,'13212349900','1956-12-13','北京市昌平区','https://itheim.oss-cn-beijing.aliyuncs.com/c265f4da-2197-4cfa-91b8-f627a4875c3c.jpg','https://itheim.oss-cn-beijing.aliyuncs.com/08347a75-7e07-49ed-bfad-65a553517c52.jpg','2024-09-12 18:51:36',NULL,1,NULL,NULL,'101-2',170),
(328,'老李头儿',NULL,'410725196904056698',1,1,'15100000001','2023-07-04','知道人色然',NULL,NULL,'2024-09-12 19:10:23',NULL,1,NULL,NULL,'101-1',1)
ON DUPLICATE KEY UPDATE `name`=VALUES(`name`),`image`=VALUES(`image`),`status`=VALUES(`status`),`ward_bed_no`=VALUES(`ward_bed_no`),`ward_bed_id`=VALUES(`ward_bed_id`);

-- ============================================================
-- 6. 责任护士患者关联表
-- ============================================================
CREATE TABLE IF NOT EXISTS `nurse_patient` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nurse_id` bigint DEFAULT NULL COMMENT '责任护士id',
  `patient_id` bigint DEFAULT NULL COMMENT '患者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nurse_id` (`nurse_id`,`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='责任护士患者关联表';

INSERT INTO `nurse_patient` (`id`,`nurse_id`,`patient_id`,`create_time`,`update_time`,`create_by`,`update_by`,`remark`) VALUES
(2,101,327,'2025-01-02 01:07:42',NULL,1,NULL,NULL),
(3,102,327,'2025-01-02 01:07:42',NULL,1,NULL,NULL),
(4,101,328,'2025-01-02 01:07:42',NULL,1,NULL,NULL),
(5,102,328,'2025-01-02 01:07:42',NULL,1,NULL,NULL),
(6,101,325,'2025-01-02 01:07:48',NULL,1,NULL,NULL),
(10,101,326,'2025-06-09 22:00:20',NULL,1,NULL,NULL)
ON DUPLICATE KEY UPDATE `nurse_id`=VALUES(`nurse_id`),`patient_id`=VALUES(`patient_id`);

-- ============================================================
-- 7. 照护方案表
-- ============================================================
CREATE TABLE IF NOT EXISTS `care_plan` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `sort_no` int DEFAULT NULL COMMENT '排序号',
  `plan_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态 0禁用 1启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `plan_name` (`plan_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='照护方案表';

INSERT INTO `care_plan` (`id`,`sort_no`,`plan_name`,`status`,`create_time`,`update_time`,`create_by`,`update_by`,`remark`) VALUES
(133,1,'术后监护照护方案',1,'2024-08-19 19:17:31',NULL,NULL,NULL,NULL),
(134,2,'慢病住院照护方案',1,'2024-08-19 19:36:10',NULL,NULL,NULL,NULL),
(135,3,'康复训练照护方案',1,'2024-08-29 16:56:39',NULL,NULL,NULL,NULL)
ON DUPLICATE KEY UPDATE `sort_no`=VALUES(`sort_no`),`plan_name`=VALUES(`plan_name`),`status`=VALUES(`status`);

-- ============================================================
-- 8. 护理级别表
-- ============================================================
CREATE TABLE IF NOT EXISTS `care_level` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '等级名称',
  `care_plan_id` int NOT NULL COMMENT '照护方案ID',
  `fee` decimal(10,2) NOT NULL COMMENT '照护费用',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态（0：禁用，1：启用）',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '等级说明',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='护理级别表';

INSERT INTO `care_level` (`id`,`name`,`care_plan_id`,`fee`,`status`,`description`,`create_time`,`create_by`,`update_by`,`remark`,`update_time`) VALUES
(76,'特级护理',133,3200.00,1,'适用于术后监护、生命体征异常风险较高患者','2024-08-14 16:33:16',NULL,1,NULL,'2026-04-22 10:00:00'),
(77,'一级护理',134,2200.00,1,'适用于病情稳定但需要密切照护的住院患者','2024-08-20 11:18:21',NULL,1,NULL,'2026-04-22 10:00:00'),
(78,'康复护理',135,1800.00,1,'适用于康复训练、慢病随访和出院前评估患者','2024-08-29 16:58:00',NULL,1,NULL,'2026-04-22 10:00:00')
ON DUPLICATE KEY UPDATE `name`=VALUES(`name`),`care_plan_id`=VALUES(`care_plan_id`),`fee`=VALUES(`fee`),`status`=VALUES(`status`),`description`=VALUES(`description`),`update_by`=VALUES(`update_by`);

-- ============================================================
-- 9. 医嘱项目表
-- ============================================================
CREATE TABLE IF NOT EXISTS `medical_order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `order_no` int DEFAULT NULL COMMENT '排序号',
  `unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '单位',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图片',
  `order_requirement` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '医嘱要求',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态（0：禁用，1：启用）',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '更新人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='医嘱项目表';

INSERT INTO `medical_order_item` (`id`,`name`,`order_no`,`unit`,`price`,`image`,`order_requirement`,`status`,`create_by`,`update_by`,`remark`,`create_time`,`update_time`) VALUES
(1,'生命体征监测',1,'次',12.00,'https://itheim.oss-cn-beijing.aliyuncs.com/b6631465-1684-41fe-8ccd-0b027cb91e90.png','按医嘱记录体温、脉搏、呼吸、血压',1,'1',NULL,NULL,'2024-08-29 16:51:50','2026-04-22 10:00:00'),
(2,'静脉输液巡视',2,'次',18.00,'https://itheim.oss-cn-beijing.aliyuncs.com/41fc58d3-0627-4fa9-8459-906599aa1efa.png','核对输液通路、滴速和患者反应',1,'1',NULL,NULL,'2024-08-29 16:52:27','2026-04-22 10:00:00'),
(3,'口服用药提醒',3,'次',8.00,'https://itheim.oss-cn-beijing.aliyuncs.com/e611fcc9-dc45-49ac-abeb-f2ea99c2cffc.png','按时提醒患者服药并登记执行结果',1,'1',NULL,NULL,'2024-08-29 16:52:52','2026-04-22 10:00:00'),
(4,'伤口换药协助',4,'次',45.00,'https://itheim.oss-cn-beijing.aliyuncs.com/d91ba642-88e5-4c3d-8e50-a681ae3300e5.png','准备换药物品并协助医生完成处理',1,'1',NULL,NULL,'2024-08-29 16:53:29','2026-04-22 10:00:00'),
(5,'血糖监测',5,'次',15.00,'https://itheim.oss-cn-beijing.aliyuncs.com/125df948-7646-4fce-b322-1db0a84856e7.png','餐前或医嘱时间测量血糖并记录',1,'1',NULL,NULL,'2024-08-29 16:53:51','2026-04-22 10:00:00'),
(6,'雾化吸入护理',6,'次',30.00,'https://itheim.oss-cn-beijing.aliyuncs.com/a38883fc-870b-40ff-a256-54ce2fc17af9.png','核对药液并观察吸入过程中的呼吸情况',1,'1',NULL,NULL,'2024-08-29 16:54:22','2026-04-22 10:00:00'),
(7,'康复训练指导',7,'次',60.00,'https://itheim.oss-cn-beijing.aliyuncs.com/95b0ad37-5d61-4ec2-a961-d6fb691a18f0.png','按照康复计划完成床旁或活动区训练',1,'1',NULL,NULL,'2024-08-29 16:54:45','2026-04-22 10:00:00'),
(8,'陪检转运协助',8,'次',35.00,'https://itheim.oss-cn-beijing.aliyuncs.com/8437eb2d-3ea5-4eee-9d78-017bc8b3a66e.png','协助患者前往检查科室并完成交接',1,'1',NULL,NULL,'2024-08-29 16:55:08','2026-04-22 10:00:00'),
(9,'心理疏导随访',9,'小时',80.00,'https://itheim.oss-cn-beijing.aliyuncs.com/dc004cc2-688c-4d22-8fbc-8e923219a2bd.png','关注焦虑、睡眠和治疗配合情况',1,'1',NULL,NULL,'2024-08-29 16:55:37','2026-04-22 10:00:00')
ON DUPLICATE KEY UPDATE `name`=VALUES(`name`),`order_no`=VALUES(`order_no`),`unit`=VALUES(`unit`),`price`=VALUES(`price`),`image`=VALUES(`image`),`order_requirement`=VALUES(`order_requirement`),`status`=VALUES(`status`),`update_time`=VALUES(`update_time`);

-- ============================================================
-- 10. 照护方案和医嘱项目关联表
-- ============================================================
CREATE TABLE IF NOT EXISTS `care_plan_order_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `care_plan_id` int NOT NULL COMMENT '计划id',
  `medical_order_item_id` int NOT NULL COMMENT '项目id',
  `execute_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '计划执行时间',
  `execute_cycle` int NOT NULL COMMENT '执行周期 0 天 1 周 2月',
  `execute_frequency` int NOT NULL COMMENT '执行频次',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1743 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='照护方案和医嘱项目关联表';

INSERT INTO `care_plan_order_item` (`id`,`care_plan_id`,`medical_order_item_id`,`execute_time`,`execute_cycle`,`execute_frequency`,`create_time`,`update_time`,`create_by`,`update_by`,`remark`) VALUES
(1736,133,1,'08:00:00',0,4,'2024-08-19 11:28:43',NULL,NULL,NULL,NULL),
(1737,133,4,'10:00:00',0,1,'2024-08-19 11:28:43',NULL,NULL,NULL,NULL),
(1739,134,3,'09:00:00',0,3,'2024-08-29 08:55:34',NULL,NULL,NULL,NULL),
(1740,134,5,'07:30:00',0,3,'2024-08-29 08:55:34',NULL,NULL,NULL,NULL),
(1741,135,7,'15:00:00',0,1,'2024-08-29 08:55:34',NULL,NULL,NULL,NULL),
(1742,135,8,'14:00:00',1,2,'2024-10-03 14:50:59',NULL,NULL,NULL,NULL)
ON DUPLICATE KEY UPDATE `care_plan_id`=VALUES(`care_plan_id`),`medical_order_item_id`=VALUES(`medical_order_item_id`),`execute_time`=VALUES(`execute_time`),`execute_cycle`=VALUES(`execute_cycle`),`execute_frequency`=VALUES(`execute_frequency`);

-- ============================================================
-- 11. 照护任务表
-- ============================================================
CREATE TABLE IF NOT EXISTS `care_task` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nurse_id` varchar(50) DEFAULT NULL COMMENT '责任护士id',
  `medical_order_item_id` int NOT NULL COMMENT '项目id',
  `medical_order_item_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '医嘱项目名称',
  `patient_id` bigint DEFAULT NULL COMMENT '患者ID',
  `patient_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '患者姓名',
  `ward_bed_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '病床编号',
  `estimated_server_time` datetime DEFAULT NULL COMMENT '预计服务时间',
  `real_server_time` datetime DEFAULT NULL COMMENT '实际服务时间',
  `mark` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '执行记录',
  `cancel_reason` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '取消原因',
  `status` int DEFAULT NULL COMMENT '状态  1待执行 2已执行 3已关闭',
  `task_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '执行图片',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='照护任务表';

-- care_task 表无初始数据

-- ============================================================
-- 12. 预约挂号表
-- ============================================================
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
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_appointment_time` (`time`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='预约挂号表';

INSERT INTO `appointment` (`id`,`name`,`mobile`,`time`,`visitor`,`type`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(1001,'赵敏','13800010001','2026-04-22 09:30:00','赵敏',0,0,'1','2026-04-22 08:30:00',NULL,NULL,'心内科初诊'),
(1002,'王强','13800010002','2026-04-22 10:00:00','王强',1,0,'1','2026-04-22 08:35:00',NULL,NULL,'糖尿病复诊'),
(1003,'李梅','13800010003','2026-04-22 14:30:00','李梅',2,1,'1','2026-04-22 08:40:00','1','2026-04-22 15:00:00','住院探视已完成')
ON DUPLICATE KEY UPDATE `name`=VALUES(`name`),`mobile`=VALUES(`mobile`),`time`=VALUES(`time`),`status`=VALUES(`status`),`update_by`=VALUES(`update_by`),`update_time`=VALUES(`update_time`);

-- ============================================================
-- 13. 入院办理表
-- ============================================================
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
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_admission_patient` (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入院办理表';

INSERT INTO `admission` (`id`,`patient_name`,`patient_id`,`id_card_no`,`start_date`,`end_date`,`care_level_name`,`ward_bed_no`,`status`,`sort_order`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2001,'赵敏',325,'132123196712131234','2026-04-20 09:00:00','2026-05-20 09:00:00','一级护理','104-1',0,1,'1','2026-04-20 09:00:00',NULL,NULL,'心内科住院观察'),
(2002,'王强',326,'132123196712131239','2026-04-21 10:00:00','2026-05-05 10:00:00','康复护理','104-2',0,2,'1','2026-04-21 10:00:00',NULL,NULL,'康复医学科住院')
ON DUPLICATE KEY UPDATE `patient_name`=VALUES(`patient_name`),`status`=VALUES(`status`),`update_by`=VALUES(`update_by`),`update_time`=VALUES(`update_time`);

-- ============================================================
-- 14. 入院费用配置表
-- ============================================================
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
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_admission_config_admission` (`admission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入院费用配置表';

INSERT INTO `admission_config` (`id`,`admission_id`,`care_level_id`,`care_level_name`,`fee_start_date`,`fee_end_date`,`deposit`,`care_fee`,`ward_bed_fee`,`insurance_payment`,`government_subsidy`,`other_fees`,`sort_order`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2101,2001,77,'一级护理','2026-04-20 09:00:00','2026-05-20 09:00:00',5000.00,2200.00,1800.00,1200.00,0.00,300.00,1,'1','2026-04-20 09:00:00',NULL,NULL,'月度住院费用配置'),
(2102,2002,78,'康复护理','2026-04-21 10:00:00','2026-05-05 10:00:00',3000.00,1800.00,1500.00,800.00,0.00,200.00,2,'1','2026-04-21 10:00:00',NULL,NULL,'康复住院费用配置')
ON DUPLICATE KEY UPDATE `care_level_name`=VALUES(`care_level_name`),`deposit`=VALUES(`deposit`),`care_fee`=VALUES(`care_fee`),`update_by`=VALUES(`update_by`),`update_time`=VALUES(`update_time`);

-- ============================================================
-- 15. 入院合同表
-- ============================================================
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
  UNIQUE KEY `uk_admission_contract_no` (`admission_contract_no`),
  KEY `idx_admission_contract_patient` (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2203 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入院合同表';

INSERT INTO `admission_contract` (`id`,`patient_id`,`admission_contract_name`,`admission_contract_no`,`agreement_path`,`third_party_phone`,`third_party_name`,`patient_name`,`start_date`,`end_date`,`status`,`sign_date`,`termination_submitter`,`termination_date`,`termination_agreement_path`,`sort_order`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2201,325,'赵敏入院照护协议','HT20260422001','/profile/contracts/HT20260422001.pdf','13900010001','赵华','赵敏','2026-04-20 09:00:00','2026-05-20 09:00:00',1,'2026-04-20 09:00:00',NULL,NULL,NULL,1,'1','2026-04-20 09:00:00',NULL,NULL,'心内科入院合同'),
(2202,326,'王强康复住院协议','HT20260422002','/profile/contracts/HT20260422002.pdf','13900010002','王丽','王强','2026-04-21 10:00:00','2026-05-05 10:00:00',1,'2026-04-21 10:00:00',NULL,NULL,NULL,2,'1','2026-04-21 10:00:00',NULL,NULL,'康复科入院合同')
ON DUPLICATE KEY UPDATE `admission_contract_name`=VALUES(`admission_contract_name`),`status`=VALUES(`status`),`update_by`=VALUES(`update_by`),`update_time`=VALUES(`update_time`);

-- ============================================================
-- 16. 入院评估表
-- ============================================================
CREATE TABLE IF NOT EXISTS `patient_assessment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评估ID',
  `patient_name` varchar(50) DEFAULT NULL COMMENT '患者姓名',
  `id_card` varchar(30) DEFAULT NULL COMMENT '身份证号',
  `birth_date` datetime DEFAULT NULL COMMENT '出生日期',
  `age` int DEFAULT NULL COMMENT '年龄',
  `gender` int DEFAULT NULL COMMENT '性别 0男 1女',
  `health_score` varchar(20) DEFAULT NULL COMMENT '健康评分',
  `risk_level` varchar(30) DEFAULT NULL COMMENT '风险等级',
  `suggestion_for_admission` int DEFAULT NULL COMMENT '是否建议入院 0建议 1不建议',
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
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_patient_assessment_name` (`patient_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2303 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='入院评估表';

INSERT INTO `patient_assessment` (`id`,`patient_name`,`id_card`,`birth_date`,`age`,`gender`,`health_score`,`risk_level`,`suggestion_for_admission`,`care_level_name`,`admission_status`,`total_check_date`,`physical_exam_institution`,`physical_report_url`,`assessment_time`,`report_summary`,`disease_risk`,`abnormal_analysis`,`system_score`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2301,'赵敏','132123196712131234','1967-12-13 00:00:00',58,1,'82','提示',0,'一级护理',0,'2026-04-18','智护云医管体检中心','/profile/reports/assessment-2301.pdf','2026-04-19 14:00:00','血压偏高，建议住院观察并完善心内科检查','高血压、冠心病风险需持续监测','收缩压偏高，睡眠质量一般','82','1','2026-04-19 14:00:00',NULL,NULL,'已办理入院'),
(2302,'王强','132123196712131239','1967-12-13 00:00:00',58,0,'76','风险',0,'康复护理',0,'2026-04-19','智护云医管体检中心','/profile/reports/assessment-2302.pdf','2026-04-20 15:00:00','血糖控制波动，建议康复期住院管理','糖尿病并发症风险需随访','空腹血糖偏高，运动耐受偏弱','76','1','2026-04-20 15:00:00',NULL,NULL,'已办理入院')
ON DUPLICATE KEY UPDATE `patient_name`=VALUES(`patient_name`),`admission_status`=VALUES(`admission_status`),`update_by`=VALUES(`update_by`),`update_time`=VALUES(`update_time`);

-- ============================================================
-- 17. 患者联系人表
-- ============================================================
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
) ENGINE=InnoDB AUTO_INCREMENT=2403 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='患者联系人表';

INSERT INTO `patient_contact` (`id`,`phone`,`name`,`avatar`,`open_id`,`gender`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) VALUES
(2401,'13900010001','赵华',NULL,'wx_patient_contact_2401',0,'1','2026-04-20 09:00:00',NULL,NULL,'赵敏家属'),
(2402,'13900010002','王丽',NULL,'wx_patient_contact_2402',1,'1','2026-04-21 10:00:00',NULL,NULL,'王强家属')
ON DUPLICATE KEY UPDATE `name`=VALUES(`name`),`phone`=VALUES(`phone`),`update_by`=VALUES(`update_by`),`update_time`=VALUES(`update_time`);
