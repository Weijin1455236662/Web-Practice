/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : test_db

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 12/11/2020 23:34:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for databasechangelog
-- ----------------------------
DROP TABLE IF EXISTS `databasechangelog`;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of databasechangelog
-- ----------------------------
BEGIN;
INSERT INTO `databasechangelog` VALUES ('2020-11-3', 'gly', 'db.changelog/changelog-1.0.xml', '2020-11-06 09:59:10', 1, 'EXECUTED', '8:dfdd42dbeda99b36c9b30399997e9b87', 'sql', '', NULL, '3.8.8', NULL, NULL, '4627946487');
INSERT INTO `databasechangelog` VALUES ('2020-11-8', 'gly', 'db.changelog/changelog-1.0.xml', '2020-11-09 16:29:23', 2, 'EXECUTED', '8:f77678d80bded3f106d4f5586adede78', 'sql', '', NULL, '3.8.8', NULL, NULL, '4910557801');
COMMIT;

-- ----------------------------
-- Table structure for databasechangeloglock
-- ----------------------------
DROP TABLE IF EXISTS `databasechangeloglock`;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of databasechangeloglock
-- ----------------------------
BEGIN;
INSERT INTO `databasechangeloglock` VALUES (1, b'0', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_craft
-- ----------------------------
DROP TABLE IF EXISTS `t_craft`;
CREATE TABLE `t_craft` (
  `craftid` int(11) NOT NULL AUTO_INCREMENT,
  `material_code` int(11) DEFAULT NULL,
  `human_num` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `human_res` varchar(200) DEFAULT NULL,
  `equipment_res` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`craftid`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_craft
-- ----------------------------
BEGIN;
INSERT INTO `t_craft` VALUES (34, 3211498, 8, 250, '29_31_32_1_16', '12');
INSERT INTO `t_craft` VALUES (35, 3059786, 10, 313, '16_31_29_8_14_20', '21_22_24_25_29');
INSERT INTO `t_craft` VALUES (36, 3209248, 5, 45, '1_2_32_4', '23_24_25_26_27_29');
INSERT INTO `t_craft` VALUES (37, 1302215, 2, 6250, '2_26', '36_31');
INSERT INTO `t_craft` VALUES (38, 3031238, 8, 196, '1_29_20_31_32_14_26', '6');
INSERT INTO `t_craft` VALUES (39, 3005837, 6, 238, '2_20_8_26', '10_24_25_26_27_29');
INSERT INTO `t_craft` VALUES (40, 3036466, 6, 178, '29_20_16_26_4', '7_29');
INSERT INTO `t_craft` VALUES (41, 3246515, 2, 200, '26_8_20_1', '24');
INSERT INTO `t_craft` VALUES (42, 3247967, 2, 200, '26_8_20_1', '21');
INSERT INTO `t_craft` VALUES (43, 3246418, 6, 178, '29_2_1_16_31_8_32_26', '7_29');
INSERT INTO `t_craft` VALUES (44, 3213124, 6, 178, '29_2_1_16_31_8_32_26', '7_29');
INSERT INTO `t_craft` VALUES (45, 3050015, 6, 178, '29_2_1_16_31_8_32_26', '7_29');
INSERT INTO `t_craft` VALUES (46, 3048823, 6, 178, '29_2_1_16_31_8_32_26', '7_29');
INSERT INTO `t_craft` VALUES (47, 3005840, 6, 178, '29_2_1_16_31_8_32_26', '7_29');
INSERT INTO `t_craft` VALUES (48, 3000607, 6, 178, '29_2_1_16_31_8_32_26', '7_29');
INSERT INTO `t_craft` VALUES (49, 1413036, 6, 178, '29_2_1_16_31_8_32_26', '7_29');
COMMIT;

-- ----------------------------
-- Table structure for t_equipment
-- ----------------------------
DROP TABLE IF EXISTS `t_equipment`;
CREATE TABLE `t_equipment` (
  `equipmentid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`equipmentid`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_equipment
-- ----------------------------
BEGIN;
INSERT INTO `t_equipment` VALUES (6, 'line06', 'line', 1);
INSERT INTO `t_equipment` VALUES (7, 'line07', 'line', 1);
INSERT INTO `t_equipment` VALUES (10, 'line11', 'line', 1);
INSERT INTO `t_equipment` VALUES (12, 'line14', 'line', 1);
INSERT INTO `t_equipment` VALUES (21, 'line33', 'line', 1);
INSERT INTO `t_equipment` VALUES (22, 'line34', 'line', 1);
INSERT INTO `t_equipment` VALUES (23, 'line35', 'line', 1);
INSERT INTO `t_equipment` VALUES (24, 'line36', 'line', 1);
INSERT INTO `t_equipment` VALUES (25, 'line37', 'line', 1);
INSERT INTO `t_equipment` VALUES (26, 'line38', 'line', 1);
INSERT INTO `t_equipment` VALUES (27, 'line39', 'line', 1);
INSERT INTO `t_equipment` VALUES (29, 'line41', 'line', 1);
INSERT INTO `t_equipment` VALUES (31, 'line43', 'line', 1);
INSERT INTO `t_equipment` VALUES (36, '自动包装机', '自动包装机', 2);
COMMIT;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `material_code` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `delivery_date` datetime DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
INSERT INTO `t_order` VALUES (1, 3246515, 770, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (2, 1413036, 446, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (3, 1413036, 300, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (4, 3246515, 840, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (5, 3247967, 100, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (6, 3247967, 200, '2018-11-14 00:00:00');
INSERT INTO `t_order` VALUES (7, 3246418, 320, '2018-11-14 00:00:00');
INSERT INTO `t_order` VALUES (8, 3048823, 600, '2018-11-14 00:00:00');
INSERT INTO `t_order` VALUES (9, 3213124, 100, '2018-11-14 00:00:00');
INSERT INTO `t_order` VALUES (10, 3213124, 150, '2018-11-15 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for t_team
-- ----------------------------
DROP TABLE IF EXISTS `t_team`;
CREATE TABLE `t_team` (
  `teamid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `begin_day` int(11) DEFAULT NULL,
  `end_day` int(11) DEFAULT NULL,
  `begin_time` int(11) DEFAULT NULL,
  `end_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`teamid`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_team
-- ----------------------------
BEGIN;
INSERT INTO `t_team` VALUES (1, '1组-彭晓慧', 5, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (2, '2组-丁雪梅', 3, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (4, '4组-赵东勤', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (8, '8组-张正萍', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (14, '14组-周清', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (16, '16组-朱绍美', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (20, '20组-王树梅', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (26, '27组-徐贤燕', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (29, '31组-史义玲', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (31, '36组-谢春霞', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (32, '39组-刘霞', 3, 1, 5, 7, 18);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
