-- MySQL dump 10.13  Distrib 8.0.39, for Win64 (x86_64)
--
-- Host: 192.168.100.168    Database: zzyl
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ward_bed`
--
DROP DATABASE IF EXISTS `zzyl`;
CREATE DATABASE IF NOT EXISTS `zzyl`;
use `zzyl`;

DROP TABLE IF EXISTS `ward_bed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ward_bed` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '病床ID',
  `ward_bed_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '病床编号',
  `ward_bed_status` int DEFAULT NULL COMMENT '病床状态: 未入院0, 已入院1 ',
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward_bed`
--

LOCK TABLES `ward_bed` WRITE;
/*!40000 ALTER TABLE `ward_bed` DISABLE KEYS */;
INSERT INTO `ward_bed` VALUES (1,'101-1',1,1,1,'2023-09-26 17:39:53','2023-10-05 15:59:24',1671403256519078138,1671403256519078164,NULL),(2,'102-1',0,1,2,'2023-09-26 17:40:01','2023-10-05 16:00:05',1671403256519078138,1671403256519078164,NULL),(3,'102-2',0,2,2,'2023-09-26 17:40:09','2023-10-05 15:59:45',1671403256519078138,1671403256519078164,NULL),(4,'103-1',0,1,3,'2023-09-26 17:40:42','2023-10-05 16:00:21',1671403256519078138,1671403256519078164,NULL),(5,'104-1',1,1,4,'2023-09-26 17:40:49','2023-09-26 17:45:39',1671403256519078138,1671403256519078138,NULL),(6,'104-2',1,2,4,'2023-09-26 17:40:54','2023-10-20 23:22:12',1671403256519078138,1671403256519078164,NULL),(7,'105-1',0,1,5,'2023-09-26 17:41:09','2023-09-26 17:45:52',1671403256519078138,1671403256519078138,NULL),(8,'106-1',0,1,6,'2023-09-26 17:41:16','2023-09-26 17:45:58',1671403256519078138,1671403256519078138,NULL),(9,'106-2',0,2,6,'2023-09-26 17:41:24','2023-09-26 17:46:04',1671403256519078138,1671403256519078138,NULL),(10,'107-1',0,1,7,'2023-09-26 17:41:32','2023-12-21 09:37:49',1671403256519078138,1671403256519078138,NULL),(11,'201-1',0,1,8,'2023-09-26 17:44:53','2023-10-13 10:40:27',1671403256519078138,1671403256519078164,NULL),(12,'202-1',0,1,9,'2023-09-26 17:46:33','2023-09-26 17:46:33',1671403256519078138,NULL,NULL),(13,'202-2',0,2,9,'2023-09-26 17:46:47','2023-09-26 17:46:47',1671403256519078138,NULL,NULL),(14,'203-1',0,1,10,'2023-09-26 18:43:58','2023-09-26 18:43:58',1671403256519078138,NULL,NULL),(15,'204-1',0,1,11,'2023-09-26 18:44:03','2023-09-26 18:44:03',1671403256519078138,NULL,NULL),(16,'204-2',0,2,11,'2023-09-26 18:44:12','2023-09-26 18:44:12',1671403256519078138,NULL,NULL),(18,'205-1',0,1,12,'2023-09-26 18:44:23','2023-12-20 18:40:07',1671403256519078138,1671403256519078138,NULL),(19,'206-1',0,1,13,'2023-09-26 18:44:36','2023-12-20 21:43:10',1671403256519078138,1671403256519078138,NULL),(20,'206-2',0,2,13,'2023-09-26 18:44:42','2023-09-26 18:44:48',1671403256519078138,1671403256519078138,NULL),(21,'207-1',0,1,14,'2023-09-26 18:45:01','2023-09-26 18:45:01',1671403256519078138,NULL,NULL),(22,'301-2',0,1,15,'2023-09-26 18:45:26','2023-12-26 19:35:06',1671403256519078138,1671403256519078138,NULL),(23,'302-1',0,1,16,'2023-09-26 18:45:31','2023-09-26 18:45:31',1671403256519078138,NULL,NULL),(24,'302-3',0,2,16,'2023-09-26 18:45:39','2023-12-26 19:35:15',1671403256519078138,1671403256519078138,NULL),(25,'303-1',0,1,17,'2023-09-26 18:45:44','2023-09-26 18:45:44',1671403256519078138,NULL,NULL),(27,'304-1',0,1,18,'2023-09-26 18:45:55','2023-09-26 18:45:55',1671403256519078138,NULL,NULL),(28,'304-2',0,2,18,'2023-09-26 18:46:04','2023-09-26 18:46:04',1671403256519078138,NULL,NULL),(29,'305-1',0,1,19,'2023-09-26 18:46:11','2023-12-21 10:04:47',1671403256519078138,1671403256519078138,NULL),(30,'306-1',0,1,20,'2023-09-26 18:46:16','2023-09-26 18:46:16',1671403256519078138,NULL,NULL),(31,'306-2',0,2,20,'2023-09-26 18:46:22','2023-09-26 19:08:50',1671403256519078138,1671403256519078138,NULL),(32,'307-1',0,1,21,'2023-09-26 18:46:29','2023-09-26 18:46:29',1671403256519078138,NULL,NULL),(33,'401-1',0,1,22,'2023-09-26 18:52:14','2023-09-26 18:52:14',1671403256519078138,NULL,NULL),(34,'402-1',0,1,23,'2023-09-26 18:52:22','2023-09-26 18:52:22',1671403256519078138,NULL,NULL),(35,'402-2',0,2,23,'2023-09-26 18:52:35','2023-09-26 18:52:38',1671403256519078138,1671403256519078138,NULL),(36,'403-1',0,1,24,'2023-09-26 18:52:47','2023-09-26 18:52:47',1671403256519078138,NULL,NULL),(37,'404-1',0,1,25,'2023-09-26 18:52:54','2023-09-26 18:52:54',1671403256519078138,NULL,NULL),(38,'404-2',0,2,25,'2023-09-26 18:53:02','2023-09-26 18:53:10',1671403256519078138,1671403256519078138,NULL),(39,'405-1',0,1,26,'2023-09-26 18:53:18','2023-09-26 18:53:18',1671403256519078138,NULL,NULL),(40,'406-1',0,1,27,'2023-09-26 18:53:27','2023-09-26 18:53:27',1671403256519078138,NULL,NULL),(41,'406-2',0,2,27,'2023-09-26 18:53:36','2023-09-26 18:53:36',1671403256519078138,NULL,NULL),(42,'407-1',0,1,28,'2023-09-26 18:53:44','2023-09-26 18:53:44',1671403256519078138,NULL,NULL),(43,'501-1',0,1,29,'2023-09-26 18:55:47','2023-09-26 18:55:47',1671403256519078138,NULL,NULL),(44,'502-1',0,1,31,'2023-09-26 18:55:52','2023-09-26 18:55:52',1671403256519078138,NULL,NULL),(45,'502-2',0,2,31,'2023-09-26 18:56:02','2023-09-26 18:56:02',1671403256519078138,NULL,NULL),(46,'503-1',0,1,32,'2023-09-26 18:56:10','2023-09-26 18:56:10',1671403256519078138,NULL,NULL),(48,'504-1',0,1,33,'2023-09-26 18:56:26','2023-09-26 18:56:26',1671403256519078138,NULL,NULL),(49,'504-2',0,2,33,'2023-09-26 18:56:32','2023-09-26 18:56:32',1671403256519078138,NULL,NULL),(50,'505-1',0,1,34,'2023-09-26 18:56:37','2023-09-26 18:56:37',1671403256519078138,NULL,NULL),(52,'506-1',0,1,35,'2023-09-26 18:56:49','2023-09-26 18:56:49',1671403256519078138,NULL,NULL),(53,'506-2',0,2,35,'2023-09-26 18:56:54','2023-09-26 18:56:54',1671403256519078138,NULL,NULL),(54,'507-1',0,1,36,'2023-09-26 18:57:00','2023-09-26 18:57:00',1671403256519078138,NULL,NULL),(55,'601-1',0,1,37,'2023-09-26 19:05:11','2023-09-28 22:53:28',1671403256519078138,1671403256519078164,NULL),(56,'602-1',0,1,38,'2023-09-26 19:05:16','2023-09-26 19:05:16',1671403256519078138,NULL,NULL),(57,'602-2',0,2,38,'2023-09-26 19:05:24','2023-09-26 19:05:24',1671403256519078138,NULL,NULL),(58,'603-1',0,1,39,'2023-09-26 19:05:29','2023-09-26 19:05:29',1671403256519078138,NULL,NULL),(59,'604-1',0,1,40,'2023-09-26 19:05:33','2023-09-26 19:05:33',1671403256519078138,NULL,NULL),(60,'604-2',0,2,40,'2023-09-26 19:05:38','2023-09-26 19:05:38',1671403256519078138,NULL,NULL),(61,'605-1',0,1,41,'2023-09-26 19:05:43','2023-09-26 19:05:43',1671403256519078138,NULL,NULL),(62,'606-1',0,1,42,'2023-09-26 19:05:48','2023-09-26 19:05:48',1671403256519078138,NULL,NULL),(63,'606-2',0,2,42,'2023-09-26 19:05:54','2023-09-26 19:05:54',1671403256519078138,NULL,NULL),(64,'607-1',0,1,43,'2023-09-26 19:05:59','2023-09-26 19:05:59',1671403256519078138,NULL,NULL),(65,'701-1',0,1,44,'2023-09-26 19:06:10','2023-09-26 19:06:10',1671403256519078138,NULL,NULL),(66,'702-1',0,1,45,'2023-09-26 19:06:14','2023-09-26 19:06:26',1671403256519078138,1671403256519078138,NULL),(68,'702-2',0,2,45,'2023-09-26 19:06:35','2023-09-26 19:06:35',1671403256519078138,NULL,NULL),(69,'703-1',0,1,46,'2023-09-26 19:06:41','2023-09-26 19:06:41',1671403256519078138,NULL,NULL),(70,'704-1',0,1,47,'2023-09-26 19:06:46','2023-12-20 14:28:22',1671403256519078138,1671403256519078138,NULL),(71,'704-2',0,2,47,'2023-09-26 19:06:52','2023-09-26 19:06:57',1671403256519078138,1671403256519078138,NULL),(72,'705-1',0,1,48,'2023-09-26 19:07:04','2023-09-26 19:07:04',1671403256519078138,NULL,NULL),(73,'706-1',0,1,49,'2023-09-26 19:07:10','2023-09-26 19:07:10',1671403256519078138,NULL,NULL),(74,'706-2',0,2,49,'2023-09-26 19:07:14','2023-09-26 19:07:19',1671403256519078138,1671403256519078138,NULL),(75,'707-1',0,1,50,'2023-09-26 19:07:25','2023-09-26 19:07:25',1671403256519078138,NULL,NULL),(76,'801-1',0,1,51,'2023-09-26 19:07:41','2023-09-26 19:07:41',1671403256519078138,NULL,NULL),(77,'803-1',0,1,53,'2023-09-26 19:07:46','2023-09-26 19:07:46',1671403256519078138,NULL,NULL),(78,'805-1',0,1,55,'2023-09-26 19:07:51','2023-09-26 19:07:51',1671403256519078138,NULL,NULL),(79,'807-1',0,1,57,'2023-09-26 19:07:56','2023-09-26 19:07:56',1671403256519078138,1,NULL),(80,'802-1',0,1,52,'2023-09-26 19:08:04','2023-09-26 19:08:04',1671403256519078138,NULL,NULL),(81,'801-2',0,2,52,'2023-09-26 19:08:09','2023-09-26 19:08:09',1671403256519078138,NULL,NULL),(82,'804-1',0,1,54,'2023-09-26 19:08:15','2023-09-26 19:08:15',1671403256519078138,NULL,NULL),(83,'804-2',0,2,54,'2023-09-26 19:08:22','2023-09-26 19:08:22',1671403256519078138,NULL,NULL),(84,'806-1',0,1,56,'2023-09-26 19:08:28','2023-09-26 19:08:28',1671403256519078138,NULL,NULL),(85,'806-2',0,2,56,'2023-09-26 19:08:35','2023-09-26 19:08:35',1671403256519078138,1,NULL),(170,'101-2',1,2,1,'2023-12-21 11:45:09','2023-12-21 11:45:09',1671403256519078138,1,NULL),(171,'103-2',0,1,3,'2023-12-23 16:12:34','2023-12-23 16:22:03',1671403256519078138,1671403256519078138,NULL),(173,'108-1',0,1,67,'2023-12-23 17:59:23','2024-11-03 23:31:50',1671403256519078138,1,NULL),(177,'1011',0,1,74,'2023-12-26 19:32:07','2023-12-26 19:32:07',1671403256519078138,NULL,NULL),(178,'101',0,1,74,'2023-12-26 19:32:15','2023-12-26 19:32:15',1671403256519078138,NULL,NULL),(196,'109-01',0,1,81,'2024-07-31 11:29:18',NULL,1,NULL,NULL),(200,'1201-1',NULL,1,83,'2024-10-04 00:03:31',NULL,1,NULL,NULL),(201,'109-2',NULL,2,81,'2025-01-02 00:37:05',NULL,1,NULL,NULL),(202,'108-2',NULL,2,67,'2025-01-02 00:37:32',NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `ward_bed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (325,'13211223322','https://itheim.oss-cn-beijing.aliyuncs.com/1fb24c20-ec99-44b3-8691-766c5508b347.jpg','132123196712131234',1,1,'13211223322','2024-08-22','13211223322','https://itheim.oss-cn-beijing.aliyuncs.com/b79d8d1e-1015-4d04-83e9-a22d3cc47869.jpg','https://itheim.oss-cn-beijing.aliyuncs.com/07ee1b38-b611-4955-8339-fad02a6d7fc6.jpg','2024-08-27 16:43:20',NULL,1,NULL,NULL,'104-1',5),(326,'李天龙','https://itheim.oss-cn-beijing.aliyuncs.com/8aef5eb9-436f-4ece-b68f-34d957d6b050.png','132123196712131239',1,1,'13222334433','1967-12-13','北京市昌平区','https://itheim.oss-cn-beijing.aliyuncs.com/816d7d6c-d13e-4ec6-9879-68d4f8054935.jpg','https://itheim.oss-cn-beijing.aliyuncs.com/f96f4666-c58f-4f69-bf15-8a521788330f.jpg','2024-08-27 16:50:09',NULL,1,NULL,NULL,'104-2',6),(327,'老李','https://itheim.oss-cn-beijing.aliyuncs.com/1510df9e-fca8-4a35-9443-8e4bacad0e03.png','132123195612132345',1,1,'13212349900','1956-12-13','北京市昌平区','https://itheim.oss-cn-beijing.aliyuncs.com/c265f4da-2197-4cfa-91b8-f627a4875c3c.jpg','https://itheim.oss-cn-beijing.aliyuncs.com/08347a75-7e07-49ed-bfad-65a553517c52.jpg','2024-09-12 18:51:36',NULL,1,NULL,NULL,'101-2',170),(328,'老李头儿',NULL,'410725196904056698',1,1,'15100000001','2023-07-04','知道人色然',NULL,NULL,'2024-09-12 19:10:23',NULL,1,NULL,NULL,'101-1',1);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward_floor`
--

DROP TABLE IF EXISTS `ward_floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ward_floor` (
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward_floor`
--

LOCK TABLES `ward_floor` WRITE;
/*!40000 ALTER TABLE `ward_floor` DISABLE KEYS */;
INSERT INTO `ward_floor` VALUES (1,'1楼',11,'2023-09-26 16:10:27','2024-05-24 17:57:28',1671403256519078153,1,NULL),(2,'2楼',2,'2023-09-26 17:37:20','2023-09-26 17:37:20',1671403256519078138,NULL,NULL),(3,'3楼',3,'2023-09-26 17:37:26','2023-09-26 17:37:26',1671403256519078138,NULL,NULL),(4,'4楼',4,'2023-09-26 17:37:32','2023-09-26 17:37:32',1671403256519078138,NULL,NULL),(5,'5楼',5,'2023-09-26 17:37:38','2023-09-26 17:37:38',1671403256519078138,NULL,NULL),(6,'6楼',6,'2023-09-26 17:37:42','2023-09-26 17:37:59',1671403256519078138,1671403256519078138,NULL),(7,'7楼',7,'2023-09-26 17:37:47','2023-09-26 17:37:52',1671403256519078138,1671403256519078138,NULL),(8,'8楼',8,'2023-09-26 17:38:09','2023-09-26 17:38:09',1671403256519078138,NULL,NULL),(391,'9楼',8,'2023-12-18 14:53:50','2023-12-18 14:53:50',1671403256519078138,NULL,NULL),(401,'10楼',9,'2023-12-26 19:29:54','2023-12-27 10:15:34',1671403256519078138,1671403256519078138,NULL),(406,'12楼',1,'2024-05-27 14:07:32','2024-05-27 14:14:46',1,1,NULL);
/*!40000 ALTER TABLE `ward_floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_table`
--

DROP TABLE IF EXISTS `gen_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table` (
  `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `tpl_web_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '前端模板类型（element-ui模版 element-plus模版）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='代码生成业务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table`
--

LOCK TABLES `gen_table` WRITE;
/*!40000 ALTER TABLE `gen_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `gen_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_table_column`
--

DROP TABLE IF EXISTS `gen_table_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table_column` (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=262 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='代码生成业务表字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table_column`
--

LOCK TABLES `gen_table_column` WRITE;
/*!40000 ALTER TABLE `gen_table_column` DISABLE KEYS */;
/*!40000 ALTER TABLE `gen_table_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `general_log`
--

DROP TABLE IF EXISTS `general_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `general_log` (
  `event_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `user_host` mediumtext NOT NULL,
  `thread_id` bigint unsigned NOT NULL,
  `server_id` int unsigned NOT NULL,
  `command_type` varchar(64) NOT NULL,
  `argument` mediumblob NOT NULL
) ENGINE=CSV DEFAULT CHARSET=utf8mb3 COMMENT='General log';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `general_log`
--

LOCK TABLES `general_log` WRITE;
/*!40000 ALTER TABLE `general_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `general_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nurse_patient`
--

DROP TABLE IF EXISTS `nurse_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nurse_patient` (
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nurse_patient`
--

LOCK TABLES `nurse_patient` WRITE;
/*!40000 ALTER TABLE `nurse_patient` DISABLE KEYS */;
INSERT INTO `nurse_patient` VALUES (2,101,327,'2025-01-02 01:07:42',NULL,1,NULL,NULL),(3,102,327,'2025-01-02 01:07:42',NULL,1,NULL,NULL),(4,101,328,'2025-01-02 01:07:42',NULL,1,NULL,NULL),(5,102,328,'2025-01-02 01:07:42',NULL,1,NULL,NULL),(6,101,325,'2025-01-02 01:07:48',NULL,1,NULL,NULL),(10,101,326,'2025-06-09 22:00:20',NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `nurse_patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `care_level`
--

DROP TABLE IF EXISTS `care_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `care_level` (
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `care_level`
--

LOCK TABLES `care_level` WRITE;
/*!40000 ALTER TABLE `care_level` DISABLE KEYS */;
INSERT INTO care_level VALUES (76,'特级护理',133,3200.00,1,'适用于术后监护、生命体征异常风险较高患者','2024-08-14 16:33:16',NULL,1,NULL,'2026-04-22 10:00:00'),(77,'一级护理',134,2200.00,1,'适用于病情稳定但需要密切照护的住院患者','2024-08-20 11:18:21',NULL,1,NULL,'2026-04-22 10:00:00'),(78,'康复护理',135,1800.00,1,'适用于康复训练、慢病随访和出院前评估患者','2024-08-29 16:58:00',NULL,1,NULL,'2026-04-22 10:00:00');
/*!40000 ALTER TABLE `care_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `care_plan`
--

DROP TABLE IF EXISTS `care_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `care_plan` (
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `care_plan`
--

LOCK TABLES `care_plan` WRITE;
/*!40000 ALTER TABLE `care_plan` DISABLE KEYS */;
INSERT INTO care_plan VALUES (133,1,'术后监护照护方案',1,'2024-08-19 19:17:31',NULL,NULL,NULL,NULL),(134,2,'慢病住院照护方案',1,'2024-08-19 19:36:10',NULL,NULL,NULL,NULL),(135,3,'康复训练照护方案',1,'2024-08-29 16:56:39',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `care_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_order_item`
--

DROP TABLE IF EXISTS `medical_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_order_item` (
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_order_item`
--

LOCK TABLES `medical_order_item` WRITE;
/*!40000 ALTER TABLE `medical_order_item` DISABLE KEYS */;
INSERT INTO medical_order_item VALUES (1,'生命体征监测',1,'次',12.00,'https://itheim.oss-cn-beijing.aliyuncs.com/b6631465-1684-41fe-8ccd-0b027cb91e90.png','按医嘱记录体温、脉搏、呼吸、血压',1,'1',NULL,NULL,'2024-08-29 16:51:50','2026-04-22 10:00:00'),(2,'静脉输液巡视',2,'次',18.00,'https://itheim.oss-cn-beijing.aliyuncs.com/41fc58d3-0627-4fa9-8459-906599aa1efa.png','核对输液通路、滴速和患者反应',1,'1',NULL,NULL,'2024-08-29 16:52:27','2026-04-22 10:00:00'),(3,'口服用药提醒',3,'次',8.00,'https://itheim.oss-cn-beijing.aliyuncs.com/e611fcc9-dc45-49ac-abeb-f2ea99c2cffc.png','按时提醒患者服药并登记执行结果',1,'1',NULL,NULL,'2024-08-29 16:52:52','2026-04-22 10:00:00'),(4,'伤口换药协助',4,'次',45.00,'https://itheim.oss-cn-beijing.aliyuncs.com/d91ba642-88e5-4c3d-8e50-a681ae3300e5.png','准备换药物品并协助医生完成处理',1,'1',NULL,NULL,'2024-08-29 16:53:29','2026-04-22 10:00:00'),(5,'血糖监测',5,'次',15.00,'https://itheim.oss-cn-beijing.aliyuncs.com/125df948-7646-4fce-b322-1db0a84856e7.png','餐前或医嘱时间测量血糖并记录',1,'1',NULL,NULL,'2024-08-29 16:53:51','2026-04-22 10:00:00'),(6,'雾化吸入护理',6,'次',30.00,'https://itheim.oss-cn-beijing.aliyuncs.com/a38883fc-870b-40ff-a256-54ce2fc17af9.png','核对药液并观察吸入过程中的呼吸情况',1,'1',NULL,NULL,'2024-08-29 16:54:22','2026-04-22 10:00:00'),(7,'康复训练指导',7,'次',60.00,'https://itheim.oss-cn-beijing.aliyuncs.com/95b0ad37-5d61-4ec2-a961-d6fb691a18f0.png','按照康复计划完成床旁或活动区训练',1,'1',NULL,NULL,'2024-08-29 16:54:45','2026-04-22 10:00:00'),(8,'陪检转运协助',8,'次',35.00,'https://itheim.oss-cn-beijing.aliyuncs.com/8437eb2d-3ea5-4eee-9d78-017bc8b3a66e.png','协助患者前往检查科室并完成交接',1,'1',NULL,NULL,'2024-08-29 16:55:08','2026-04-22 10:00:00'),(9,'心理疏导随访',9,'小时',80.00,'https://itheim.oss-cn-beijing.aliyuncs.com/dc004cc2-688c-4d22-8fbc-8e923219a2bd.png','关注焦虑、睡眠和治疗配合情况',1,'1',NULL,NULL,'2024-08-29 16:55:37','2026-04-22 10:00:00');
/*!40000 ALTER TABLE `medical_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `care_plan_order_item`
--

DROP TABLE IF EXISTS `care_plan_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `care_plan_order_item` (
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
) ENGINE=InnoDB AUTO_INCREMENT=1743 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='照护方案和项目关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `care_plan_order_item`
--

LOCK TABLES `care_plan_order_item` WRITE;
/*!40000 ALTER TABLE `care_plan_order_item` DISABLE KEYS */;
INSERT INTO care_plan_order_item VALUES (1736,133,1,'08:00:00',0,4,'2024-08-19 11:28:43',NULL,NULL,NULL,NULL),(1737,133,4,'10:00:00',0,1,'2024-08-19 11:28:43',NULL,NULL,NULL,NULL),(1739,134,3,'09:00:00',0,3,'2024-08-29 08:55:34',NULL,NULL,NULL,NULL),(1740,134,5,'07:30:00',0,3,'2024-08-29 08:55:34',NULL,NULL,NULL,NULL),(1741,135,7,'15:00:00',0,1,'2024-08-29 08:55:34',NULL,NULL,NULL,NULL),(1742,135,8,'14:00:00',1,2,'2024-10-03 14:50:59',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `care_plan_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `care_task`
--

DROP TABLE IF EXISTS `care_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `care_task` (
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
  `status` int DEFAULT NULL COMMENT '状态  1待执行 2已执行 3已关闭 ',
  `task_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '执行图片',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` bigint DEFAULT NULL COMMENT '创建人id',
  `update_by` bigint DEFAULT NULL COMMENT '更新人id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='照护任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `care_task`
--

LOCK TABLES `care_task` WRITE;
/*!40000 ALTER TABLE `care_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `care_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward_room`
--

DROP TABLE IF EXISTS `ward_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ward_room` (
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward_room`
--

LOCK TABLES `ward_room` WRITE;
/*!40000 ALTER TABLE `ward_room` DISABLE KEYS */;
INSERT INTO `ward_room` VALUES (1,'101',1,'豪华单人间',1,'2023-09-26 17:38:25','2023-12-23 16:19:55',0,1671403256519078138,1671403256519078138,NULL),(2,'102',2,'普通双人间',1,'2023-09-26 17:38:32','2023-09-26 17:38:32',0,1671403256519078138,NULL,NULL),(3,'103',3,'普通单人间',1,'2023-09-26 17:38:41','2023-09-26 17:38:41',0,1671403256519078138,NULL,NULL),(4,'104',4,'豪华双人间',1,'2023-09-26 17:38:48','2023-09-26 17:38:48',0,1671403256519078138,NULL,NULL),(5,'105',5,'豪华单人间',1,'2023-09-26 17:38:55','2023-09-26 17:38:55',0,1671403256519078138,NULL,NULL),(6,'106',6,'双人套房',1,'2023-09-26 17:39:05','2023-09-26 17:39:05',0,1671403256519078138,NULL,NULL),(7,'107',7,'单人套房',1,'2023-09-26 17:39:13','2023-09-26 17:39:13',0,1671403256519078138,NULL,NULL),(8,'201',1,'特护房',2,'2023-09-26 17:42:02','2023-09-26 17:42:02',0,1671403256519078138,NULL,NULL),(9,'202',2,'普通双人间',2,'2023-09-26 17:42:08','2023-09-26 17:42:08',0,1671403256519078138,NULL,NULL),(10,'203',3,'普通单人间',2,'2023-09-26 17:42:15','2023-09-26 17:42:15',0,1671403256519078138,NULL,NULL),(11,'204',4,'豪华双人间',2,'2023-09-26 17:42:22','2023-09-26 17:42:22',0,1671403256519078138,NULL,NULL),(12,'205',5,'豪华单人间',2,'2023-09-26 17:42:30','2023-09-26 17:42:30',0,1671403256519078138,NULL,NULL),(13,'206',6,'双人套房',2,'2023-09-26 17:42:41','2023-09-26 17:42:41',0,1671403256519078138,NULL,NULL),(14,'207',7,'单人套房',2,'2023-09-26 17:42:48','2023-09-26 17:42:48',0,1671403256519078138,NULL,NULL),(15,'301',1,'特护房',3,'2023-09-26 17:43:54','2023-09-26 17:43:54',0,1671403256519078138,NULL,NULL),(16,'302',2,'普通双人间',3,'2023-09-26 17:44:01','2023-09-26 17:44:01',0,1671403256519078138,NULL,NULL),(17,'303',3,'普通单人间',3,'2023-09-26 17:44:09','2023-09-26 17:44:09',0,1671403256519078138,NULL,NULL),(18,'304',4,'豪华双人间',3,'2023-09-26 17:44:17','2023-09-26 17:44:17',0,1671403256519078138,NULL,NULL),(19,'305',5,'豪华单人间',3,'2023-09-26 17:44:24','2023-09-26 17:44:24',0,1671403256519078138,NULL,NULL),(20,'306',6,'双人套房',3,'2023-09-26 17:44:33','2023-09-26 17:44:33',0,1671403256519078138,NULL,NULL),(21,'307',7,'单人套房',3,'2023-09-26 17:44:42','2023-09-26 17:44:42',0,1671403256519078138,NULL,NULL),(22,'401',1,'特护房',4,'2023-09-26 18:51:10','2023-09-26 18:51:10',0,1671403256519078138,NULL,NULL),(23,'402',2,'普通双人间',4,'2023-09-26 18:51:17','2023-09-26 18:51:17',0,1671403256519078138,NULL,NULL),(24,'403',3,'普通单人间',4,'2023-09-26 18:51:23','2023-09-26 18:51:23',0,1671403256519078138,NULL,NULL),(25,'404',4,'豪华双人间',4,'2023-09-26 18:51:32','2023-09-26 18:51:32',0,1671403256519078138,NULL,NULL),(26,'405',5,'豪华单人间',4,'2023-09-26 18:51:42','2023-09-26 18:51:42',0,1671403256519078138,NULL,NULL),(27,'406',6,'双人套房',4,'2023-09-26 18:51:54','2023-09-26 18:51:54',0,1671403256519078138,NULL,NULL),(28,'407',7,'单人套房',4,'2023-09-26 18:52:03','2023-09-26 18:52:03',0,1671403256519078138,NULL,NULL),(29,'501',1,'特护房',5,'2023-09-26 18:53:54','2023-09-26 18:53:54',0,1671403256519078138,NULL,NULL),(31,'502',2,'普通双人间',5,'2023-09-26 18:54:05','2023-09-26 18:54:05',0,1671403256519078138,NULL,NULL),(32,'503',3,'普通单人间',5,'2023-09-26 18:54:12','2023-09-26 18:54:12',0,1671403256519078138,NULL,NULL),(33,'504',4,'豪华双人间',5,'2023-09-26 18:54:20','2023-09-26 18:54:20',0,1671403256519078138,NULL,NULL),(34,'505',5,'豪华单人间',5,'2023-09-26 18:54:28','2023-09-26 18:54:28',0,1671403256519078138,NULL,NULL),(35,'506',6,'双人套房',5,'2023-09-26 18:54:37','2023-09-26 18:54:37',0,1671403256519078138,NULL,NULL),(36,'507',7,'单人套房',5,'2023-09-26 18:54:47','2023-09-26 18:54:47',0,1671403256519078138,NULL,NULL),(37,'601',1,'特护房',6,'2023-09-26 18:57:14','2023-09-26 18:57:14',0,1671403256519078138,NULL,NULL),(38,'602',2,'普通双人间',6,'2023-09-26 18:57:20','2023-09-26 18:57:20',0,1671403256519078138,NULL,NULL),(39,'603',3,'普通单人间',6,'2023-09-26 18:57:28','2023-09-26 18:57:28',0,1671403256519078138,NULL,NULL),(40,'604',4,'豪华双人间',6,'2023-09-26 18:57:36','2023-09-26 18:57:36',0,1671403256519078138,NULL,NULL),(41,'605',5,'豪华单人间',6,'2023-09-26 19:01:36','2023-09-26 19:01:36',0,1671403256519078138,NULL,NULL),(42,'606',6,'双人套房',6,'2023-09-26 19:01:45','2023-09-26 19:01:45',0,1671403256519078138,NULL,NULL),(43,'607',7,'单人套房',6,'2023-09-26 19:01:54','2023-09-26 19:01:54',0,1671403256519078138,NULL,NULL),(44,'701',1,'特护房',7,'2023-09-26 19:02:13','2023-09-26 19:02:13',0,1671403256519078138,NULL,NULL),(45,'702',2,'普通双人间',7,'2023-09-26 19:02:20','2023-09-26 19:02:20',0,1671403256519078138,NULL,NULL),(46,'703',3,'普通单人间',7,'2023-09-26 19:02:28','2023-09-26 19:02:28',0,1671403256519078138,NULL,NULL),(47,'704',4,'豪华双人间',7,'2023-09-26 19:02:49','2023-09-26 19:02:49',0,1671403256519078138,NULL,NULL),(48,'705',5,'豪华单人间',7,'2023-09-26 19:03:00','2023-09-26 19:03:00',0,1671403256519078138,NULL,NULL),(49,'706',6,'双人套房',7,'2023-09-26 19:03:07','2023-09-26 19:03:07',0,1671403256519078138,NULL,NULL),(50,'707',7,'单人套房',7,'2023-09-26 19:03:15','2023-09-26 19:03:15',0,1671403256519078138,NULL,NULL),(51,'801',1,'特护房',8,'2023-09-26 19:03:49','2023-09-26 19:03:49',0,1671403256519078138,NULL,NULL),(52,'802',2,'普通双人间',8,'2023-09-26 19:03:57','2023-09-26 19:03:57',0,1671403256519078138,NULL,NULL),(53,'803',3,'普通单人间',8,'2023-09-26 19:04:04','2023-09-26 19:04:04',0,1671403256519078138,NULL,NULL),(54,'804',4,'豪华双人间',8,'2023-09-26 19:04:13','2023-09-26 19:04:13',0,1671403256519078138,NULL,NULL),(55,'805',5,'豪华单人间',8,'2023-09-26 19:04:45','2023-09-26 19:04:45',0,1671403256519078138,NULL,NULL),(56,'806',6,'双人套房',8,'2023-09-26 19:04:52','2023-09-26 19:04:52',0,1671403256519078138,NULL,NULL),(57,'807',7,'单人套房',8,'2023-09-26 19:05:00','2023-09-26 19:05:00',0,1671403256519078138,NULL,NULL),(67,'108',8,'普通单人间',1,'2023-12-23 16:12:46','2023-12-23 17:08:47',0,1671403256519078138,1671403256519078138,NULL),(81,'109',9,'单人套房',1,'2024-05-27 10:28:37',NULL,0,1,NULL,NULL),(83,'1201',1,'双人套房',406,'2024-05-27 14:14:54',NULL,0,1,NULL,NULL),(86,'102-2',1,'豪华单人间',406,'2024-08-22 18:49:21',NULL,0,1,NULL,NULL);
/*!40000 ALTER TABLE `ward_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward_room_type`
--

DROP TABLE IF EXISTS `ward_room_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ward_room_type` (
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward_room_type`
--

LOCK TABLES `ward_room_type` WRITE;
/*!40000 ALTER TABLE `ward_room_type` DISABLE KEYS */;
INSERT INTO `ward_room_type` VALUES (1,'单人套房',0,4000.00,'宽敞舒适的套房，配备独立卫生间和基本生活设施，满足独自居住的需求，提供私密性和舒适度','https://yjy-slwl-oss.oss-cn-hangzhou.aliyuncs.com/e2f1031b-e23e-4379-95d4-ce8fe382f58f.png',1,'2023-09-26 15:57:50','2024-10-03 23:54:34',1671403256519078153,1,NULL),(2,'双人套房',0,6000.00,'适合夫妻或朋友两人居住的套房，设有独立卫生间和基本生活设施，提供共享空间和私密性','https://yjy-slwl-oss.oss-cn-hangzhou.aliyuncs.com/ff84c185-2e28-431c-951d-d004cc2d5bdc.png',1,'2023-09-26 15:58:51','2023-09-26 15:58:51',1671403256519078153,NULL,NULL),(3,'豪华单人间',0,3000.00,'豪华装修的单人房间，提供舒适的居住环境和高品质的服务，设计精美，配备独立卫生间和必需设施','https://yjy-slwl-oss.oss-cn-hangzhou.aliyuncs.com/d803832c-5b93-4cae-ba95-aeb52ab0c5e0.png',1,'2023-09-26 15:59:33','2023-09-26 15:59:33',1671403256519078153,NULL,NULL),(4,'豪华双人间',0,4500.00,'精心装修的双人房间，提供舒适和豪华的居住环境，配备独立卫生间和高品质的家具','https://yjy-slwl-oss.oss-cn-hangzhou.aliyuncs.com/c3522da7-4c5c-48d2-94f9-9f0b95a048d2.png',1,'2023-09-26 16:00:03','2024-08-22 16:12:20',1671403256519078153,1,NULL),(5,'普通单人间',0,2000.00,'简洁实用的单人房间，提供基本的居住设施和舒适度，适合独自居住的老年人，提供相对经济实惠的居住选择','https://yjy-slwl-oss.oss-cn-hangzhou.aliyuncs.com/1a330b1c-b0a1-463d-8d9a-221ef17c314f.png',1,'2023-09-26 16:00:27','2023-09-26 16:00:27',1671403256519078153,NULL,NULL),(116,'标准双人间',10,1500.00,'适合好朋友一起居住','https://itheim.oss-cn-beijing.aliyuncs.com/91c4a814-efd5-4093-a5ac-963b41047019.png',1,'2024-09-12 22:52:36','2025-06-09 22:53:07',1,1,'');
/*!40000 ALTER TABLE `ward_room_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slow_log`
--

DROP TABLE IF EXISTS `slow_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slow_log` (
  `start_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `user_host` mediumtext NOT NULL,
  `query_time` time(6) NOT NULL,
  `lock_time` time(6) NOT NULL,
  `rows_sent` int NOT NULL,
  `rows_examined` int NOT NULL,
  `db` varchar(512) NOT NULL,
  `last_insert_id` int NOT NULL,
  `insert_id` int NOT NULL,
  `server_id` int unsigned NOT NULL,
  `sql_text` mediumblob NOT NULL,
  `thread_id` bigint unsigned NOT NULL
) ENGINE=CSV DEFAULT CHARSET=utf8mb3 COMMENT='Slow log';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slow_log`
--

LOCK TABLES `slow_log` WRITE;
/*!40000 ALTER TABLE `slow_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `slow_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_config` (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` VALUES (1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2024-08-14 02:48:24','',NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2024-08-14 02:48:24','',NULL,'初始化密码 123456'),(3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2024-08-14 02:48:24','',NULL,'深色主题theme-dark，浅色主题theme-light'),(4,'账号自助-验证码开关','sys.account.captchaEnabled','true','Y','admin','2024-08-14 02:48:24','',NULL,'是否开启验证码功能（true开启，false关闭）'),(5,'账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y','admin','2024-08-14 02:48:24','',NULL,'是否开启注册用户功能（true开启，false关闭）'),(6,'用户登录-黑名单列表','sys.login.blackIPList','','Y','admin','2024-08-14 02:48:24','',NULL,'设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (100,0,'0','智护云医管',0,'若依','15888888888','ry@qq.com','0','0','admin','2024-08-14 02:48:23','admin','2024-10-18 10:08:30'),(101,100,'0,100','医务管理办公室',1,'若依','15888888888','ry@qq.com','0','0','admin','2024-08-14 02:48:23','admin','2024-10-18 10:08:40'),(102,100,'0,100','财务科',2,'','','','0','0','admin','2024-08-14 02:48:23','admin','2024-10-18 10:11:02'),(103,101,'0,100,101','院长办公室',1,'若依','15888888888','ry@qq.com','0','0','admin','2024-08-14 02:48:23','admin','2024-10-18 10:08:55'),(104,101,'0,100,101','患者服务部',2,'若依','15888888888','ry@qq.com','0','2','admin','2024-08-14 02:48:23','',NULL),(105,101,'0,100,101','测试部门',3,'若依','15888888888','ry@qq.com','0','2','admin','2024-08-14 02:48:23','',NULL),(106,101,'0,100,101','财务科',4,'若依','15888888888','ry@qq.com','0','2','admin','2024-08-14 02:48:23','',NULL),(107,101,'0,100,101','设备运维科',5,'若依','15888888888','ry@qq.com','0','2','admin','2024-08-14 02:48:23','',NULL),(108,102,'0,100,102','患者服务部',1,'若依','15888888888','ry@qq.com','0','2','admin','2024-08-14 02:48:23','',NULL),(109,102,'0,100,102','财务科',2,'若依','15888888888','ry@qq.com','0','2','admin','2024-08-14 02:48:23','',NULL),(200,100,'0,100','院务部',3,NULL,NULL,NULL,'0','0','admin','2024-10-18 10:10:44','',NULL),(201,100,'0,100','护理部',4,NULL,NULL,NULL,'0','0','admin','2024-10-18 10:10:55','',NULL),(202,100,'0,100','后勤保障部',5,NULL,NULL,NULL,'0','0','admin','2024-10-18 10:11:15','',NULL),(203,100,'0,100','患者服务部',6,NULL,NULL,NULL,'0','0','admin','2024-10-18 10:11:24','',NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_data`
--

DROP TABLE IF EXISTS `sys_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_data`
--

LOCK TABLES `sys_dict_data` WRITE;
/*!40000 ALTER TABLE `sys_dict_data` DISABLE KEYS */;
INSERT INTO `sys_dict_data` VALUES (1,1,'男','0','sys_user_sex','','','Y','0','admin','2024-08-14 02:48:24','',NULL,'性别男'),(2,2,'女','1','sys_user_sex','','','N','0','admin','2024-08-14 02:48:24','',NULL,'性别女'),(3,3,'未知','2','sys_user_sex','','','N','0','admin','2024-08-14 02:48:24','',NULL,'性别未知'),(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2024-08-14 02:48:24','',NULL,'显示菜单'),(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2024-08-14 02:48:24','',NULL,'隐藏菜单'),(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2024-08-14 02:48:24','',NULL,'正常状态'),(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2024-08-14 02:48:24','',NULL,'停用状态'),(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2024-08-14 02:48:24','',NULL,'正常状态'),(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2024-08-14 02:48:24','',NULL,'停用状态'),(10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2024-08-14 02:48:24','',NULL,'默认分组'),(11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2024-08-14 02:48:24','',NULL,'系统分组'),(12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2024-08-14 02:48:24','',NULL,'系统默认是'),(13,2,'否','N','sys_yes_no','','danger','N','0','admin','2024-08-14 02:48:24','',NULL,'系统默认否'),(14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2024-08-14 02:48:24','',NULL,'通知'),(15,2,'公告','2','sys_notice_type','','success','N','0','admin','2024-08-14 02:48:24','',NULL,'公告'),(16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2024-08-14 02:48:24','',NULL,'正常状态'),(17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2024-08-14 02:48:24','',NULL,'关闭状态'),(18,99,'其他','0','sys_oper_type','','info','N','0','admin','2024-08-14 02:48:24','',NULL,'其他操作'),(19,1,'新增','1','sys_oper_type','','info','N','0','admin','2024-08-14 02:48:24','',NULL,'新增操作'),(20,2,'修改','2','sys_oper_type','','info','N','0','admin','2024-08-14 02:48:24','',NULL,'修改操作'),(21,3,'删除','3','sys_oper_type','','danger','N','0','admin','2024-08-14 02:48:24','',NULL,'删除操作'),(22,4,'授权','4','sys_oper_type','','primary','N','0','admin','2024-08-14 02:48:24','',NULL,'授权操作'),(23,5,'导出','5','sys_oper_type','','warning','N','0','admin','2024-08-14 02:48:24','',NULL,'导出操作'),(24,6,'导入','6','sys_oper_type','','warning','N','0','admin','2024-08-14 02:48:24','',NULL,'导入操作'),(25,7,'强退','7','sys_oper_type','','danger','N','0','admin','2024-08-14 02:48:24','',NULL,'强退操作'),(26,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2024-08-14 02:48:24','',NULL,'生成操作'),(27,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2024-08-14 02:48:24','',NULL,'清空操作'),(28,1,'成功','0','sys_common_status','','primary','N','0','admin','2024-08-14 02:48:24','',NULL,'正常状态'),(29,2,'失败','1','sys_common_status','','danger','N','0','admin','2024-08-14 02:48:24','',NULL,'停用状态'),(100,0,'启用','1','medical_order_item_status',NULL,'default','N','0','admin','2024-08-18 02:41:15','',NULL,NULL),(101,0,'禁用','0','medical_order_item_status',NULL,'default','N','0','admin','2024-08-18 02:41:30','',NULL,NULL),(103,0,'启用','1','care_plan_status',NULL,'default','N','0','admin','2024-08-19 11:00:04','',NULL,NULL),(104,0,'禁用','2','care_plan_status',NULL,'default','N','0','admin','2024-08-19 11:00:10','',NULL,NULL),(105,0,'启用','1','care_level_status',NULL,'default','N','0','admin','2024-08-20 03:04:14','',NULL,NULL),(106,0,'禁用','0','care_level_status',NULL,'default','N','0','admin','2024-08-20 03:04:21','',NULL,NULL),(107,0,'已入院','0','admission_status',NULL,'default','N','0','admin','2024-08-31 03:20:26','',NULL,NULL),(108,0,'未入院','1','admission_status',NULL,'default','N','0','admin','2024-08-31 03:20:35','',NULL,NULL);
/*!40000 ALTER TABLE `sys_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE KEY `dict_type` (`dict_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
INSERT INTO `sys_dict_type` VALUES (1,'用户性别','sys_user_sex','0','admin','2024-08-14 02:48:24','',NULL,'用户性别列表'),(2,'菜单状态','sys_show_hide','0','admin','2024-08-14 02:48:24','',NULL,'菜单状态列表'),(3,'系统开关','sys_normal_disable','0','admin','2024-08-14 02:48:24','',NULL,'系统开关列表'),(4,'任务状态','sys_job_status','0','admin','2024-08-14 02:48:24','',NULL,'任务状态列表'),(5,'任务分组','sys_job_group','0','admin','2024-08-14 02:48:24','',NULL,'任务分组列表'),(6,'系统是否','sys_yes_no','0','admin','2024-08-14 02:48:24','',NULL,'系统是否列表'),(7,'通知类型','sys_notice_type','0','admin','2024-08-14 02:48:24','',NULL,'通知类型列表'),(8,'通知状态','sys_notice_status','0','admin','2024-08-14 02:48:24','',NULL,'通知状态列表'),(9,'操作类型','sys_oper_type','0','admin','2024-08-14 02:48:24','',NULL,'操作类型列表'),(10,'系统状态','sys_common_status','0','admin','2024-08-14 02:48:24','',NULL,'登录状态列表'),(100,'医嘱项目状态','medical_order_item_status','0','admin','2024-08-18 02:40:48','',NULL,NULL),(101,'照护方案状态','care_plan_status','0','admin','2024-08-19 10:59:40','',NULL,NULL),(102,'护理级别状态','care_level_status','0','admin','2024-08-20 03:04:03','',NULL,NULL),(103,'入院评估-入院状态','admission_status','0','admin','2024-08-31 03:19:57','',NULL,NULL);
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job`
--

DROP TABLE IF EXISTS `sys_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job` (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`,`job_name`,`job_group`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='定时任务调度表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job`
--

LOCK TABLES `sys_job` WRITE;
/*!40000 ALTER TABLE `sys_job` DISABLE KEYS */;
INSERT INTO `sys_job` VALUES (1,'系统默认（无参）','DEFAULT','ryTask.ryNoParams','0/10 * * * * ?','3','1','1','admin','2024-08-14 02:48:24','',NULL,''),(2,'系统默认（有参）','DEFAULT','ryTask.ryParams(\'ry\')','0/15 * * * * ?','3','1','1','admin','2024-08-14 02:48:24','',NULL,''),(3,'系统默认（多参）','DEFAULT','ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)','0/20 * * * * ?','3','1','1','admin','2024-08-14 02:48:24','',NULL,'');
/*!40000 ALTER TABLE `sys_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_job_log`
--

DROP TABLE IF EXISTS `sys_job_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_job_log` (
  `job_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='定时任务调度日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_job_log`
--

LOCK TABLES `sys_job_log` WRITE;
/*!40000 ALTER TABLE `sys_job_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_job_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_logininfor`
--

DROP TABLE IF EXISTS `sys_logininfor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_logininfor` (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE,
  KEY `idx_sys_logininfor_s` (`status`) USING BTREE,
  KEY `idx_sys_logininfor_lt` (`login_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='系统访问记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_logininfor`
--

LOCK TABLES `sys_logininfor` WRITE;
/*!40000 ALTER TABLE `sys_logininfor` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_logininfor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由参数',
  `route_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '路由名称',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2053 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'系统管理',0,6,'system',NULL,'','',1,0,'M','0','0','','system','admin','2024-08-14 02:48:23','admin','2024-08-29 06:40:54','系统管理目录'),(2,'系统监控',0,7,'monitor',NULL,'','',1,0,'M','0','0','','monitor','admin','2024-08-14 02:48:23','admin','2024-08-29 06:41:07','系统监控目录'),(3,'系统工具',0,8,'tool',NULL,'','',1,0,'M','0','0','','tool','admin','2024-08-14 02:48:23','admin','2024-08-29 06:41:14','系统工具目录'),(4,'若依官网',0,4,'http://ruoyi.vip',NULL,'','',0,0,'M','1','0','','guide','admin','2024-08-14 02:48:23','admin','2024-08-20 01:57:10','若依官网地址'),(100,'用户管理',1,1,'user','system/user/index','','',1,0,'C','0','0','system:user:list','user','admin','2024-08-14 02:48:23','',NULL,'用户管理菜单'),(101,'角色管理',1,2,'role','system/role/index','','',1,0,'C','0','0','system:role:list','peoples','admin','2024-08-14 02:48:23','',NULL,'角色管理菜单'),(102,'菜单管理',1,3,'menu','system/menu/index','','',1,0,'C','0','0','system:menu:list','tree-table','admin','2024-08-14 02:48:23','',NULL,'菜单管理菜单'),(103,'部门管理',1,4,'dept','system/dept/index','','',1,0,'C','0','0','system:dept:list','tree','admin','2024-08-14 02:48:23','',NULL,'部门管理菜单'),(104,'岗位管理',1,5,'post','system/post/index','','',1,0,'C','0','0','system:post:list','post','admin','2024-08-14 02:48:23','',NULL,'岗位管理菜单'),(105,'字典管理',1,6,'dict','system/dict/index','','',1,0,'C','0','0','system:dict:list','dict','admin','2024-08-14 02:48:23','',NULL,'字典管理菜单'),(106,'参数设置',1,7,'config','system/config/index','','',1,0,'C','0','0','system:config:list','edit','admin','2024-08-14 02:48:23','',NULL,'参数设置菜单'),(107,'通知公告',1,8,'notice','system/notice/index','','',1,0,'C','0','0','system:notice:list','message','admin','2024-08-14 02:48:23','',NULL,'通知公告菜单'),(108,'日志管理',1,9,'log','','','',1,0,'M','0','0','','log','admin','2024-08-14 02:48:23','',NULL,'日志管理菜单'),(109,'在线用户',2,1,'online','monitor/online/index','','',1,0,'C','0','0','monitor:online:list','online','admin','2024-08-14 02:48:23','',NULL,'在线用户菜单'),(110,'定时任务',2,2,'job','monitor/job/index','','',1,0,'C','0','0','monitor:job:list','job','admin','2024-08-14 02:48:23','',NULL,'定时任务菜单'),(111,'数据监控',2,3,'druid','monitor/druid/index','','',1,0,'C','0','0','monitor:druid:list','druid','admin','2024-08-14 02:48:23','',NULL,'数据监控菜单'),(112,'服务监控',2,4,'server','monitor/server/index','','',1,0,'C','0','0','monitor:server:list','server','admin','2024-08-14 02:48:23','',NULL,'服务监控菜单'),(113,'缓存监控',2,5,'cache','monitor/cache/index','','',1,0,'C','0','0','monitor:cache:list','redis','admin','2024-08-14 02:48:23','',NULL,'缓存监控菜单'),(114,'缓存列表',2,6,'cacheList','monitor/cache/list','','',1,0,'C','0','0','monitor:cache:list','redis-list','admin','2024-08-14 02:48:23','',NULL,'缓存列表菜单'),(115,'表单构建',3,1,'build','tool/build/index','','',1,0,'C','0','0','tool:build:list','build','admin','2024-08-14 02:48:23','',NULL,'表单构建菜单'),(116,'代码生成',3,2,'gen','tool/gen/index','','',1,0,'C','0','0','tool:gen:list','code','admin','2024-08-14 02:48:23','',NULL,'代码生成菜单'),(117,'系统接口',3,3,'swagger','tool/swagger/index','','',1,0,'C','0','0','tool:swagger:list','swagger','admin','2024-08-14 02:48:23','',NULL,'系统接口菜单'),(500,'操作日志',108,1,'operlog','monitor/operlog/index','','',1,0,'C','0','0','monitor:operlog:list','form','admin','2024-08-14 02:48:23','',NULL,'操作日志菜单'),(501,'登录日志',108,2,'logininfor','monitor/logininfor/index','','',1,0,'C','0','0','monitor:logininfor:list','logininfor','admin','2024-08-14 02:48:23','',NULL,'登录日志菜单'),(1000,'用户查询',100,1,'','','','',1,0,'F','0','0','system:user:query','#','admin','2024-08-14 02:48:23','',NULL,''),(1001,'用户新增',100,2,'','','','',1,0,'F','0','0','system:user:add','#','admin','2024-08-14 02:48:23','',NULL,''),(1002,'用户修改',100,3,'','','','',1,0,'F','0','0','system:user:edit','#','admin','2024-08-14 02:48:23','',NULL,''),(1003,'用户删除',100,4,'','','','',1,0,'F','0','0','system:user:remove','#','admin','2024-08-14 02:48:23','',NULL,''),(1004,'用户导出',100,5,'','','','',1,0,'F','0','0','system:user:export','#','admin','2024-08-14 02:48:23','',NULL,''),(1005,'用户导入',100,6,'','','','',1,0,'F','0','0','system:user:import','#','admin','2024-08-14 02:48:23','',NULL,''),(1006,'重置密码',100,7,'','','','',1,0,'F','0','0','system:user:resetPwd','#','admin','2024-08-14 02:48:23','',NULL,''),(1007,'角色查询',101,1,'','','','',1,0,'F','0','0','system:role:query','#','admin','2024-08-14 02:48:23','',NULL,''),(1008,'角色新增',101,2,'','','','',1,0,'F','0','0','system:role:add','#','admin','2024-08-14 02:48:23','',NULL,''),(1009,'角色修改',101,3,'','','','',1,0,'F','0','0','system:role:edit','#','admin','2024-08-14 02:48:23','',NULL,''),(1010,'角色删除',101,4,'','','','',1,0,'F','0','0','system:role:remove','#','admin','2024-08-14 02:48:23','',NULL,''),(1011,'角色导出',101,5,'','','','',1,0,'F','0','0','system:role:export','#','admin','2024-08-14 02:48:23','',NULL,''),(1012,'菜单查询',102,1,'','','','',1,0,'F','0','0','system:menu:query','#','admin','2024-08-14 02:48:23','',NULL,''),(1013,'菜单新增',102,2,'','','','',1,0,'F','0','0','system:menu:add','#','admin','2024-08-14 02:48:23','',NULL,''),(1014,'菜单修改',102,3,'','','','',1,0,'F','0','0','system:menu:edit','#','admin','2024-08-14 02:48:23','',NULL,''),(1015,'菜单删除',102,4,'','','','',1,0,'F','0','0','system:menu:remove','#','admin','2024-08-14 02:48:23','',NULL,''),(1016,'部门查询',103,1,'','','','',1,0,'F','0','0','system:dept:query','#','admin','2024-08-14 02:48:23','',NULL,''),(1017,'部门新增',103,2,'','','','',1,0,'F','0','0','system:dept:add','#','admin','2024-08-14 02:48:23','',NULL,''),(1018,'部门修改',103,3,'','','','',1,0,'F','0','0','system:dept:edit','#','admin','2024-08-14 02:48:23','',NULL,''),(1019,'部门删除',103,4,'','','','',1,0,'F','0','0','system:dept:remove','#','admin','2024-08-14 02:48:23','',NULL,''),(1020,'岗位查询',104,1,'','','','',1,0,'F','0','0','system:post:query','#','admin','2024-08-14 02:48:23','',NULL,''),(1021,'岗位新增',104,2,'','','','',1,0,'F','0','0','system:post:add','#','admin','2024-08-14 02:48:23','',NULL,''),(1022,'岗位修改',104,3,'','','','',1,0,'F','0','0','system:post:edit','#','admin','2024-08-14 02:48:23','',NULL,''),(1023,'岗位删除',104,4,'','','','',1,0,'F','0','0','system:post:remove','#','admin','2024-08-14 02:48:23','',NULL,''),(1024,'岗位导出',104,5,'','','','',1,0,'F','0','0','system:post:export','#','admin','2024-08-14 02:48:23','',NULL,''),(1025,'字典查询',105,1,'#','','','',1,0,'F','0','0','system:dict:query','#','admin','2024-08-14 02:48:23','',NULL,''),(1026,'字典新增',105,2,'#','','','',1,0,'F','0','0','system:dict:add','#','admin','2024-08-14 02:48:23','',NULL,''),(1027,'字典修改',105,3,'#','','','',1,0,'F','0','0','system:dict:edit','#','admin','2024-08-14 02:48:23','',NULL,''),(1028,'字典删除',105,4,'#','','','',1,0,'F','0','0','system:dict:remove','#','admin','2024-08-14 02:48:23','',NULL,''),(1029,'字典导出',105,5,'#','','','',1,0,'F','0','0','system:dict:export','#','admin','2024-08-14 02:48:23','',NULL,''),(1030,'参数查询',106,1,'#','','','',1,0,'F','0','0','system:config:query','#','admin','2024-08-14 02:48:23','',NULL,''),(1031,'参数新增',106,2,'#','','','',1,0,'F','0','0','system:config:add','#','admin','2024-08-14 02:48:23','',NULL,''),(1032,'参数修改',106,3,'#','','','',1,0,'F','0','0','system:config:edit','#','admin','2024-08-14 02:48:23','',NULL,''),(1033,'参数删除',106,4,'#','','','',1,0,'F','0','0','system:config:remove','#','admin','2024-08-14 02:48:23','',NULL,''),(1034,'参数导出',106,5,'#','','','',1,0,'F','0','0','system:config:export','#','admin','2024-08-14 02:48:23','',NULL,''),(1035,'公告查询',107,1,'#','','','',1,0,'F','0','0','system:notice:query','#','admin','2024-08-14 02:48:23','',NULL,''),(1036,'公告新增',107,2,'#','','','',1,0,'F','0','0','system:notice:add','#','admin','2024-08-14 02:48:23','',NULL,''),(1037,'公告修改',107,3,'#','','','',1,0,'F','0','0','system:notice:edit','#','admin','2024-08-14 02:48:23','',NULL,''),(1038,'公告删除',107,4,'#','','','',1,0,'F','0','0','system:notice:remove','#','admin','2024-08-14 02:48:23','',NULL,''),(1039,'操作查询',500,1,'#','','','',1,0,'F','0','0','monitor:operlog:query','#','admin','2024-08-14 02:48:23','',NULL,''),(1040,'操作删除',500,2,'#','','','',1,0,'F','0','0','monitor:operlog:remove','#','admin','2024-08-14 02:48:23','',NULL,''),(1041,'日志导出',500,3,'#','','','',1,0,'F','0','0','monitor:operlog:export','#','admin','2024-08-14 02:48:23','',NULL,''),(1042,'登录查询',501,1,'#','','','',1,0,'F','0','0','monitor:logininfor:query','#','admin','2024-08-14 02:48:23','',NULL,''),(1043,'登录删除',501,2,'#','','','',1,0,'F','0','0','monitor:logininfor:remove','#','admin','2024-08-14 02:48:23','',NULL,''),(1044,'日志导出',501,3,'#','','','',1,0,'F','0','0','monitor:logininfor:export','#','admin','2024-08-14 02:48:23','',NULL,''),(1045,'账户解锁',501,4,'#','','','',1,0,'F','0','0','monitor:logininfor:unlock','#','admin','2024-08-14 02:48:23','',NULL,''),(1046,'在线查询',109,1,'#','','','',1,0,'F','0','0','monitor:online:query','#','admin','2024-08-14 02:48:23','',NULL,''),(1047,'批量强退',109,2,'#','','','',1,0,'F','0','0','monitor:online:batchLogout','#','admin','2024-08-14 02:48:23','',NULL,''),(1048,'单条强退',109,3,'#','','','',1,0,'F','0','0','monitor:online:forceLogout','#','admin','2024-08-14 02:48:23','',NULL,''),(1049,'任务查询',110,1,'#','','','',1,0,'F','0','0','monitor:job:query','#','admin','2024-08-14 02:48:23','',NULL,''),(1050,'任务新增',110,2,'#','','','',1,0,'F','0','0','monitor:job:add','#','admin','2024-08-14 02:48:23','',NULL,''),(1051,'任务修改',110,3,'#','','','',1,0,'F','0','0','monitor:job:edit','#','admin','2024-08-14 02:48:23','',NULL,''),(1052,'任务删除',110,4,'#','','','',1,0,'F','0','0','monitor:job:remove','#','admin','2024-08-14 02:48:23','',NULL,''),(1053,'状态修改',110,5,'#','','','',1,0,'F','0','0','monitor:job:changeStatus','#','admin','2024-08-14 02:48:23','',NULL,''),(1054,'任务导出',110,6,'#','','','',1,0,'F','0','0','monitor:job:export','#','admin','2024-08-14 02:48:23','',NULL,''),(1055,'生成查询',116,1,'#','','','',1,0,'F','0','0','tool:gen:query','#','admin','2024-08-14 02:48:23','',NULL,''),(1056,'生成修改',116,2,'#','','','',1,0,'F','0','0','tool:gen:edit','#','admin','2024-08-14 02:48:23','',NULL,''),(1057,'生成删除',116,3,'#','','','',1,0,'F','0','0','tool:gen:remove','#','admin','2024-08-14 02:48:23','',NULL,''),(1058,'导入代码',116,4,'#','','','',1,0,'F','0','0','tool:gen:import','#','admin','2024-08-14 02:48:23','',NULL,''),(1059,'预览代码',116,5,'#','','','',1,0,'F','0','0','tool:gen:preview','#','admin','2024-08-14 02:48:23','',NULL,''),(1060,'生成代码',116,6,'#','','','',1,0,'F','0','0','tool:gen:code','#','admin','2024-08-14 02:48:23','',NULL,''),(2000,'医嘱照护',0,4,'nursing',NULL,NULL,'',1,0,'M','0','0','','example','admin','2024-08-14 02:59:12','admin','2024-08-29 06:42:37',''),(2001,'医嘱项目',2000,1,'project','nursing/project/index',NULL,'',1,0,'C','0','0','hospital:project:list','color','admin','2024-08-14 03:00:15','admin','2025-01-01 14:21:47','医嘱项目菜单'),(2002,'医嘱项目查询',2001,1,'#','',NULL,'',1,0,'F','0','0','hospital:project:query','#','admin','2024-08-14 03:00:15','',NULL,''),(2003,'医嘱项目新增',2001,2,'#','',NULL,'',1,0,'F','0','0','hospital:project:add','#','admin','2024-08-14 03:00:15','',NULL,''),(2004,'医嘱项目修改',2001,3,'#','',NULL,'',1,0,'F','0','0','hospital:project:edit','#','admin','2024-08-14 03:00:15','',NULL,''),(2005,'医嘱项目删除',2001,4,'#','',NULL,'',1,0,'F','0','0','hospital:project:remove','#','admin','2024-08-14 03:00:15','',NULL,''),(2006,'医嘱项目导出',2001,5,'#','',NULL,'',1,0,'F','0','0','hospital:project:export','#','admin','2024-08-14 03:00:15','',NULL,''),(2007,'护理级别',2000,3,'level','nursing/level/index',NULL,'',1,0,'C','0','0','hospital:level:list','guide','admin','2024-08-14 08:29:05','admin','2025-01-01 14:22:41','护理级别菜单'),(2008,'护理级别查询',2007,1,'#','',NULL,'',1,0,'F','0','0','hospital:level:query','#','admin','2024-08-14 08:29:05','admin','2024-10-03 10:47:20',''),(2009,'护理级别新增',2007,2,'#','',NULL,'',1,0,'F','0','0','hospital:level:add','#','admin','2024-08-14 08:29:05','admin','2024-10-03 10:47:27',''),(2010,'护理级别修改',2007,3,'#','',NULL,'',1,0,'F','0','0','hospital:level:edit','#','admin','2024-08-14 08:29:05','admin','2024-10-03 10:47:32',''),(2011,'护理级别删除',2007,4,'#','',NULL,'',1,0,'F','0','0','hospital:level:remove','#','admin','2024-08-14 08:29:05','admin','2024-10-03 10:47:37',''),(2012,'护理级别导出',2007,5,'#','',NULL,'',1,0,'F','0','0','hospital:level:export','#','admin','2024-08-14 08:29:05','admin','2024-10-03 10:47:42',''),(2013,'照护方案',2000,2,'plan','nursing/plan/index',NULL,'',1,0,'C','0','0','hospital:plan:list','druid','admin','2024-08-14 08:29:10','admin','2025-01-01 14:22:45','照护方案菜单'),(2014,'照护方案查询',2013,1,'#','',NULL,'',1,0,'F','0','0','hospital:plan:query','#','admin','2024-08-14 08:29:10','',NULL,''),(2015,'照护方案新增',2013,2,'#','',NULL,'',1,0,'F','0','0','hospital:plan:add','#','admin','2024-08-14 08:29:10','',NULL,''),(2016,'照护方案修改',2013,3,'#','',NULL,'',1,0,'F','0','0','hospital:plan:edit','#','admin','2024-08-14 08:29:10','',NULL,''),(2017,'照护方案删除',2013,4,'#','',NULL,'',1,0,'F','0','0','hospital:plan:remove','#','admin','2024-08-14 08:29:10','',NULL,''),(2018,'照护方案导出',2013,5,'#','',NULL,'',1,0,'F','0','0','hospital:plan:export','#','admin','2024-08-14 08:29:10','',NULL,''),(2019,'住院管理',0,3,'liveIn',NULL,NULL,'',1,0,'M','0','0','','date-range','admin','2024-08-22 06:49:19','admin','2024-08-29 06:42:27',''),(2020,'病房类型',2019,0,'houseSet','nursing/wardRoomType/index',NULL,'',1,0,'C','0','0',NULL,'size','admin','2024-08-22 06:50:50','',NULL,''),(2021,'病床总览',2019,2,'ward_floor','nursing/ward_floor/index',NULL,'',1,0,'C','0','0',NULL,'tree-table','admin','2024-08-22 08:15:05','',NULL,''),(2022,'入出院管理',0,2,'enterQuit',NULL,NULL,'',1,0,'M','0','0','','component','admin','2024-08-23 12:52:34','admin','2024-08-29 06:42:15',''),(2023,'入院办理',2022,2,'admission','nursing/admission/index',NULL,'',1,0,'C','0','0','hospital:admission:list','edit','admin','2024-08-23 13:13:48','admin','2024-11-07 03:10:32','入院菜单'),(2024,'入院查询',2023,1,'#','',NULL,'',1,0,'F','0','0','hospital:admission:query','#','admin','2024-08-23 13:13:48','',NULL,''),(2025,'入院新增',2023,2,'#','',NULL,'',1,0,'F','0','0','hospital:admission:add','#','admin','2024-08-23 13:13:48','',NULL,''),(2026,'入院修改',2023,3,'#','',NULL,'',1,0,'F','0','0','hospital:admission:edit','#','admin','2024-08-23 13:13:48','',NULL,''),(2027,'入院删除',2023,4,'#','',NULL,'',1,0,'F','0','0','hospital:admission:remove','#','admin','2024-08-23 13:13:48','',NULL,''),(2028,'入院导出',2023,5,'#','',NULL,'',1,0,'F','0','0','hospital:admission:export','#','admin','2024-08-23 13:13:48','',NULL,''),(2035,'入院详情',2022,0,'admissionInfo','nursing/admission/details',NULL,'',1,0,'C','1','0',NULL,'checkbox','admin','2024-08-24 03:44:48','',NULL,''),(2036,'预约挂号',0,1,'appointment',NULL,NULL,'',1,0,'M','0','0',NULL,'button','admin','2024-08-29 06:41:45','',NULL,''),(2037,'生命监测',0,5,'intelligence',NULL,NULL,'',1,0,'M','0','0',NULL,'exit-fullscreen','admin','2024-08-29 06:43:14','',NULL,''),(2038,'预约列表',2036,1,'appointment','nursing/appointment/index',NULL,'',1,0,'C','0','0',NULL,'dashboard','admin','2024-08-29 06:47:29','',NULL,''),(2039,'入院评估',2022,1,'patientAssessment','nursing/patientAssessment/index',NULL,'',1,0,'C','0','0',NULL,'eye','admin','2024-08-29 06:48:51','',NULL,''),(2040,'评估详情',2022,2,'healthDetails','nursing/patientAssessment/details',NULL,'',1,0,'C','1','0','','#','admin','2024-08-29 06:49:53','admin','2024-08-29 06:50:06',''),(2041,'责任患者',2000,4,'oldPeople','nursing/oldPeople/index',NULL,'',1,0,'C','0','0','','peoples','admin','2024-08-29 06:52:12','admin','2025-01-01 14:22:01',''),(2043,'医嘱执行详情',2000,1,'arrangeDetails','nursing/arrange/details',NULL,'',1,0,'C','1','0','','#','admin','2024-08-29 06:53:37','admin','2024-11-03 14:24:57',''),(2044,'设备管理',2037,0,'monitoring_device','nursing/monitoring_device/index',NULL,'',1,0,'C','0','0','','tool','admin','2024-08-29 06:54:54','admin','2024-08-29 07:13:42',''),(2045,'设备详情',2037,0,'details','nursing/monitoring_device/details',NULL,'',1,0,'C','1','0',NULL,'#','admin','2024-08-29 06:55:25','',NULL,''),(2046,'新增预警规则',2037,0,'ruleDetails','nursing/warningRule/details',NULL,'',1,0,'C','1','0',NULL,'#','admin','2024-08-29 06:56:01','',NULL,''),(2047,'预警规则',2037,1,'warningRule','nursing/warningRule/index',NULL,'',1,0,'C','0','0',NULL,'nested','admin','2024-08-29 06:56:48','',NULL,''),(2048,'预警数据',2037,2,'warningData','nursing/warningData/index',NULL,'',1,0,'C','0','0',NULL,'skill','admin','2024-08-29 06:57:25','',NULL,''),(2052,'智能病床',2019,3,'smartWardBed','nursing/smartWardBed/index',NULL,'',1,0,'C','0','0',NULL,'online','admin','2024-11-15 02:14:10','',NULL,'');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_notice`
--

DROP TABLE IF EXISTS `sys_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='通知公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_notice`
--

LOCK TABLES `sys_notice` WRITE;
/*!40000 ALTER TABLE `sys_notice` DISABLE KEYS */;
INSERT INTO `sys_notice` VALUES (1,'温馨提醒：2018-07-01 若依新版本发布啦','2',_binary '新版本内容','0','admin','2024-08-14 02:48:24','',NULL,'管理员'),(2,'维护通知：2018-07-01 若依系统凌晨维护','1',_binary '维护内容','0','admin','2024-08-14 02:48:24','',NULL,'管理员');
/*!40000 ALTER TABLE `sys_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oper_log`
--

DROP TABLE IF EXISTS `sys_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_oper_log` (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '模块标题',
  `business_type` int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求方式',
  `operator_type` int DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '返回参数',
  `status` int DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint DEFAULT '0' COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`) USING BTREE,
  KEY `idx_sys_oper_log_bt` (`business_type`) USING BTREE,
  KEY `idx_sys_oper_log_s` (`status`) USING BTREE,
  KEY `idx_sys_oper_log_ot` (`oper_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=423 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oper_log`
--

LOCK TABLES `sys_oper_log` WRITE;
/*!40000 ALTER TABLE `sys_oper_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_oper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_post`
--

DROP TABLE IF EXISTS `sys_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_post` (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='岗位信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_post`
--

LOCK TABLES `sys_post` WRITE;
/*!40000 ALTER TABLE `sys_post` DISABLE KEYS */;
INSERT INTO `sys_post` VALUES (1,'ceo','董事长',1,'0','admin','2024-08-14 02:48:23','',NULL,''),(2,'se','项目经理',2,'0','admin','2024-08-14 02:48:23','',NULL,''),(3,'hr','人力资源',3,'0','admin','2024-08-14 02:48:23','',NULL,''),(4,'user','医护人员',4,'0','admin','2024-08-14 02:48:23','',NULL,'');
/*!40000 ALTER TABLE `sys_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','admin',1,'1',1,1,'0','0','admin','2024-08-14 02:48:23','',NULL,'超级管理员'),(2,'医护角色','common',2,'2',1,1,'0','0','admin','2024-08-14 02:48:23','admin','2025-06-09 13:33:26','医护角色'),(100,'责任护士','nursingUser',3,'1',1,1,'0','0','admin','2024-09-24 17:25:26','admin','2025-06-09 13:33:33',NULL),(101,'设备维修员','maintainer',4,'1',1,1,'0','0','admin','2024-09-24 17:26:31','admin','2025-06-09 13:33:47',NULL),(102,'导诊专员','customerService',5,'1',1,1,'0','0','admin','2024-09-24 17:27:42','admin','2025-06-09 13:33:59',NULL),(103,'院长','SuperAdministrator',6,'1',1,1,'0','0','admin','2024-09-24 17:30:54','admin','2025-06-09 13:34:05',NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_dept` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色和部门关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_dept`
--

LOCK TABLES `sys_role_dept` WRITE;
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
INSERT INTO `sys_role_dept` VALUES (2,100),(2,101),(2,105);
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色和菜单关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (2,1),(2,2),(2,3),(2,100),(2,101),(2,102),(2,103),(2,104),(2,105),(2,106),(2,107),(2,108),(2,109),(2,110),(2,111),(2,112),(2,113),(2,114),(2,115),(2,116),(2,117),(2,500),(2,501),(2,1000),(2,1001),(2,1002),(2,1003),(2,1004),(2,1005),(2,1006),(2,1007),(2,1008),(2,1009),(2,1010),(2,1011),(2,1012),(2,1013),(2,1014),(2,1015),(2,1016),(2,1017),(2,1018),(2,1019),(2,1020),(2,1021),(2,1022),(2,1023),(2,1024),(2,1025),(2,1026),(2,1027),(2,1028),(2,1029),(2,1030),(2,1031),(2,1032),(2,1033),(2,1034),(2,1035),(2,1036),(2,1037),(2,1038),(2,1039),(2,1040),(2,1041),(2,1042),(2,1043),(2,1044),(2,1045),(2,1046),(2,1047),(2,1048),(2,1049),(2,1050),(2,1051),(2,1052),(2,1053),(2,1054),(2,1055),(2,1056),(2,1057),(2,1058),(2,1059),(2,1060),(2,2000),(2,2001),(2,2002),(2,2003),(2,2004),(2,2005),(2,2006),(2,2007),(2,2008),(2,2009),(2,2010),(2,2011),(2,2012),(2,2013),(2,2014),(2,2015),(2,2016),(2,2017),(2,2018),(2,2019),(2,2020),(2,2021),(2,2022),(2,2023),(2,2024),(2,2025),(2,2026),(2,2027),(2,2028),(2,2035),(2,2036),(2,2037),(2,2038),(2,2039),(2,2040),(2,2041),(2,2043),(2,2044),(2,2045),(2,2046),(2,2047),(2,2048),(100,1),(100,2),(100,3),(100,100),(100,101),(100,102),(100,103),(100,104),(100,105),(100,106),(100,107),(100,108),(100,109),(100,110),(100,111),(100,112),(100,113),(100,114),(100,115),(100,116),(100,117),(100,500),(100,501),(100,1000),(100,1001),(100,1002),(100,1003),(100,1004),(100,1005),(100,1006),(100,1007),(100,1008),(100,1009),(100,1010),(100,1011),(100,1012),(100,1013),(100,1014),(100,1015),(100,1016),(100,1017),(100,1018),(100,1019),(100,1020),(100,1021),(100,1022),(100,1023),(100,1024),(100,1025),(100,1026),(100,1027),(100,1028),(100,1029),(100,1030),(100,1031),(100,1032),(100,1033),(100,1034),(100,1035),(100,1036),(100,1037),(100,1038),(100,1039),(100,1040),(100,1041),(100,1042),(100,1043),(100,1044),(100,1045),(100,1046),(100,1047),(100,1048),(100,1049),(100,1050),(100,1051),(100,1052),(100,1053),(100,1054),(100,1055),(100,1056),(100,1057),(100,1058),(100,1059),(100,1060),(100,2000),(100,2001),(100,2002),(100,2003),(100,2004),(100,2005),(100,2006),(100,2007),(100,2008),(100,2009),(100,2010),(100,2011),(100,2012),(100,2013),(100,2014),(100,2015),(100,2016),(100,2017),(100,2018),(100,2019),(100,2020),(100,2021),(100,2022),(100,2023),(100,2024),(100,2025),(100,2026),(100,2027),(100,2028),(100,2035),(100,2036),(100,2037),(100,2038),(100,2039),(100,2040),(100,2041),(100,2043),(100,2044),(100,2045),(100,2046),(100,2047),(100,2048),(101,1),(101,2),(101,3),(101,100),(101,101),(101,102),(101,103),(101,104),(101,105),(101,106),(101,107),(101,108),(101,109),(101,110),(101,111),(101,112),(101,113),(101,114),(101,115),(101,116),(101,117),(101,500),(101,501),(101,1000),(101,1001),(101,1002),(101,1003),(101,1004),(101,1005),(101,1006),(101,1007),(101,1008),(101,1009),(101,1010),(101,1011),(101,1012),(101,1013),(101,1014),(101,1015),(101,1016),(101,1017),(101,1018),(101,1019),(101,1020),(101,1021),(101,1022),(101,1023),(101,1024),(101,1025),(101,1026),(101,1027),(101,1028),(101,1029),(101,1030),(101,1031),(101,1032),(101,1033),(101,1034),(101,1035),(101,1036),(101,1037),(101,1038),(101,1039),(101,1040),(101,1041),(101,1042),(101,1043),(101,1044),(101,1045),(101,1046),(101,1047),(101,1048),(101,1049),(101,1050),(101,1051),(101,1052),(101,1053),(101,1054),(101,1055),(101,1056),(101,1057),(101,1058),(101,1059),(101,1060),(101,2000),(101,2001),(101,2002),(101,2003),(101,2004),(101,2005),(101,2006),(101,2007),(101,2008),(101,2009),(101,2010),(101,2011),(101,2012),(101,2013),(101,2014),(101,2015),(101,2016),(101,2017),(101,2018),(101,2019),(101,2020),(101,2021),(101,2022),(101,2023),(101,2024),(101,2025),(101,2026),(101,2027),(101,2028),(101,2035),(101,2036),(101,2037),(101,2038),(101,2039),(101,2040),(101,2041),(101,2043),(101,2044),(101,2045),(101,2046),(101,2047),(101,2048),(102,1),(102,2),(102,3),(102,100),(102,101),(102,102),(102,103),(102,104),(102,105),(102,106),(102,107),(102,108),(102,109),(102,110),(102,111),(102,112),(102,113),(102,114),(102,115),(102,116),(102,117),(102,500),(102,501),(102,1000),(102,1001),(102,1002),(102,1003),(102,1004),(102,1005),(102,1006),(102,1007),(102,1008),(102,1009),(102,1010),(102,1011),(102,1012),(102,1013),(102,1014),(102,1015),(102,1016),(102,1017),(102,1018),(102,1019),(102,1020),(102,1021),(102,1022),(102,1023),(102,1024),(102,1025),(102,1026),(102,1027),(102,1028),(102,1029),(102,1030),(102,1031),(102,1032),(102,1033),(102,1034),(102,1035),(102,1036),(102,1037),(102,1038),(102,1039),(102,1040),(102,1041),(102,1042),(102,1043),(102,1044),(102,1045),(102,1046),(102,1047),(102,1048),(102,1049),(102,1050),(102,1051),(102,1052),(102,1053),(102,1054),(102,1055),(102,1056),(102,1057),(102,1058),(102,1059),(102,1060),(102,2000),(102,2001),(102,2002),(102,2003),(102,2004),(102,2005),(102,2006),(102,2007),(102,2008),(102,2009),(102,2010),(102,2011),(102,2012),(102,2013),(102,2014),(102,2015),(102,2016),(102,2017),(102,2018),(102,2019),(102,2020),(102,2021),(102,2022),(102,2023),(102,2024),(102,2025),(102,2026),(102,2027),(102,2028),(102,2035),(102,2036),(102,2037),(102,2038),(102,2039),(102,2040),(102,2041),(102,2043),(102,2044),(102,2045),(102,2046),(102,2047),(102,2048),(103,1),(103,2),(103,3),(103,100),(103,101),(103,102),(103,103),(103,104),(103,105),(103,106),(103,107),(103,108),(103,109),(103,110),(103,111),(103,112),(103,113),(103,114),(103,115),(103,116),(103,117),(103,500),(103,501),(103,1000),(103,1001),(103,1002),(103,1003),(103,1004),(103,1005),(103,1006),(103,1007),(103,1008),(103,1009),(103,1010),(103,1011),(103,1012),(103,1013),(103,1014),(103,1015),(103,1016),(103,1017),(103,1018),(103,1019),(103,1020),(103,1021),(103,1022),(103,1023),(103,1024),(103,1025),(103,1026),(103,1027),(103,1028),(103,1029),(103,1030),(103,1031),(103,1032),(103,1033),(103,1034),(103,1035),(103,1036),(103,1037),(103,1038),(103,1039),(103,1040),(103,1041),(103,1042),(103,1043),(103,1044),(103,1045),(103,1046),(103,1047),(103,1048),(103,1049),(103,1050),(103,1051),(103,1052),(103,1053),(103,1054),(103,1055),(103,1056),(103,1057),(103,1058),(103,1059),(103,1060),(103,2000),(103,2001),(103,2002),(103,2003),(103,2004),(103,2005),(103,2006),(103,2007),(103,2008),(103,2009),(103,2010),(103,2011),(103,2012),(103,2013),(103,2014),(103,2015),(103,2016),(103,2017),(103,2018),(103,2019),(103,2020),(103,2021),(103,2022),(103,2023),(103,2024),(103,2025),(103,2026),(103,2027),(103,2028),(103,2035),(103,2036),(103,2037),(103,2038),(103,2039),(103,2040),(103,2041),(103,2043),(103,2044),(103,2045),(103,2046),(103,2047),(103,2048);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,103,'admin','若依','00','ry@163.com','15888888888','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2025-06-09 21:01:33','admin','2024-08-14 02:48:23','','2025-06-09 13:01:32','管理员'),(2,103,'ry','若依','00','ry@qq.com','15666666666','1','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2024-08-14 02:48:23','admin','2024-08-14 02:48:23','admin','2024-10-18 10:10:07','测试员'),(100,103,'guotianle','guotianle','00','','18888888888','0','','$2a$10$GhMQGKcwBXW43oekDfuSeuddpnBOXqUqRtLdRvGctnFZU4qRnjluK','0','2','127.0.0.1','2024-10-13 10:11:11','admin','2024-10-13 02:10:30','','2024-10-13 02:11:10',NULL),(101,201,'xiaoqing','小青','00','','','1','','$2a$10$Ggf5yLwf.HKHZdmu36ujNuWuBOMb8UhaHKWP.Q1h39AQCM.4Rp.Li','0','0','127.0.0.1','2024-10-18 18:31:55','admin','2024-10-18 10:12:45','admin','2024-11-03 16:11:29',NULL),(102,201,'xiaobai','小白','00','','','1','','$2a$10$gTuSQuL2gCF7DRAog5CQi.f7X7aO4SE18V2EtDmKvUp0YNZKNw9Ou','0','0','',NULL,'admin','2024-10-18 10:13:12','admin','2024-11-03 16:11:36',NULL),(103,202,'laozhang','老张','00','','','0','','$2a$10$vhgwrN3aGZB9q/lVhmfvDOQmkkB9Lo9B2MQjN2vytASPqmdVlCrlO','0','0','',NULL,'admin','2024-10-18 10:13:41','admin','2024-11-03 16:11:42',NULL),(104,103,'laowang','老王','00','','','0','','$2a$10$78oaYzQz3g8JDZg.g6x31eZZvQj7AzViwUJP47s/zazNEGmGe7tbC','0','0','',NULL,'admin','2024-10-18 10:13:59','admin','2024-11-03 16:11:47',NULL),(105,201,'xuxian','许仙','00','','','1','','$2a$10$8tu/vev69rM8rxAECsO8Qe5WQlFgyRSMEHmlY0jcOLWemHj/1rIvq','0','0','',NULL,'admin','2024-10-19 01:27:15','admin','2024-11-03 16:11:57',NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_post`
--

DROP TABLE IF EXISTS `sys_user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_post` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户与岗位关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_post`
--

LOCK TABLES `sys_user_post` WRITE;
/*!40000 ALTER TABLE `sys_user_post` DISABLE KEYS */;
INSERT INTO `sys_user_post` VALUES (1,1),(2,2),(101,4),(102,4),(103,4),(104,1),(105,4);
/*!40000 ALTER TABLE `sys_user_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户和角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1),(2,2),(101,100),(102,100),(103,101),(104,103),(105,100);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Hospital patient workflow tables
--

DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
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

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1001,'赵敏','13800010001','2026-04-22 09:30:00','赵敏',0,0,'1','2026-04-22 08:30:00',NULL,NULL,'心内科初诊'),(1002,'王强','13800010002','2026-04-22 10:00:00','王强',1,0,'1','2026-04-22 08:35:00',NULL,NULL,'糖尿病复诊'),(1003,'李梅','13800010003','2026-04-22 14:30:00','李梅',2,1,'1','2026-04-22 08:40:00','1','2026-04-22 15:00:00','住院探视已完成');
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `admission`;
CREATE TABLE `admission` (
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

LOCK TABLES `admission` WRITE;
/*!40000 ALTER TABLE `admission` DISABLE KEYS */;
INSERT INTO `admission` VALUES (2001,'赵敏',325,'132123196712131234','2026-04-20 09:00:00','2026-05-20 09:00:00','一级护理','104-1',0,1,'1','2026-04-20 09:00:00',NULL,NULL,'心内科住院观察'),(2002,'王强',326,'132123196712131239','2026-04-21 10:00:00','2026-05-05 10:00:00','康复护理','104-2',0,2,'1','2026-04-21 10:00:00',NULL,NULL,'康复医学科住院');
/*!40000 ALTER TABLE `admission` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `admission_config`;
CREATE TABLE `admission_config` (
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

LOCK TABLES `admission_config` WRITE;
/*!40000 ALTER TABLE `admission_config` DISABLE KEYS */;
INSERT INTO `admission_config` VALUES (2101,2001,77,'一级护理','2026-04-20 09:00:00','2026-05-20 09:00:00',5000.00,2200.00,1800.00,1200.00,0.00,300.00,1,'1','2026-04-20 09:00:00',NULL,NULL,'月度住院费用配置'),(2102,2002,78,'康复护理','2026-04-21 10:00:00','2026-05-05 10:00:00',3000.00,1800.00,1500.00,800.00,0.00,200.00,2,'1','2026-04-21 10:00:00',NULL,NULL,'康复住院费用配置');
/*!40000 ALTER TABLE `admission_config` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `admission_contract`;
CREATE TABLE `admission_contract` (
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

LOCK TABLES `admission_contract` WRITE;
/*!40000 ALTER TABLE `admission_contract` DISABLE KEYS */;
INSERT INTO `admission_contract` VALUES (2201,325,'赵敏入院照护协议','HT20260422001','/profile/contracts/HT20260422001.pdf','13900010001','赵华','赵敏','2026-04-20 09:00:00','2026-05-20 09:00:00',1,'2026-04-20 09:00:00',NULL,NULL,NULL,1,'1','2026-04-20 09:00:00',NULL,NULL,'心内科入院合同'),(2202,326,'王强康复住院协议','HT20260422002','/profile/contracts/HT20260422002.pdf','13900010002','王丽','王强','2026-04-21 10:00:00','2026-05-05 10:00:00',1,'2026-04-21 10:00:00',NULL,NULL,NULL,2,'1','2026-04-21 10:00:00',NULL,NULL,'康复科入院合同');
/*!40000 ALTER TABLE `admission_contract` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `patient_assessment`;
CREATE TABLE `patient_assessment` (
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

LOCK TABLES `patient_assessment` WRITE;
/*!40000 ALTER TABLE `patient_assessment` DISABLE KEYS */;
INSERT INTO `patient_assessment` VALUES (2301,'赵敏','132123196712131234','1967-12-13 00:00:00',58,1,'82','提示',0,'一级护理',0,'2026-04-18','智护云医管体检中心','/profile/reports/assessment-2301.pdf','2026-04-19 14:00:00','血压偏高，建议住院观察并完善心内科检查','高血压、冠心病风险需持续监测','收缩压偏高，睡眠质量一般','82','1','2026-04-19 14:00:00',NULL,NULL,'已办理入院'),(2302,'王强','132123196712131239','1967-12-13 00:00:00',58,0,'76','风险',0,'康复护理',0,'2026-04-19','智护云医管体检中心','/profile/reports/assessment-2302.pdf','2026-04-20 15:00:00','血糖控制波动，建议康复期住院管理','糖尿病并发症风险需随访','空腹血糖偏高，运动耐受偏弱','76','1','2026-04-20 15:00:00',NULL,NULL,'已办理入院');
/*!40000 ALTER TABLE `patient_assessment` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `patient_contact`;
CREATE TABLE `patient_contact` (
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

LOCK TABLES `patient_contact` WRITE;
/*!40000 ALTER TABLE `patient_contact` DISABLE KEYS */;
INSERT INTO `patient_contact` VALUES (2401,'13900010001','赵华',NULL,'wx_patient_contact_2401',0,'1','2026-04-20 09:00:00',NULL,NULL,'赵敏家属'),(2402,'13900010002','王丽',NULL,'wx_patient_contact_2402',1,'1','2026-04-21 10:00:00',NULL,NULL,'王强家属');
/*!40000 ALTER TABLE `patient_contact` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-09 22:55:58
