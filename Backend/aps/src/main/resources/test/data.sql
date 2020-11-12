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

 Date: 12/11/2020 18:26:50
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_craft
-- ----------------------------
BEGIN;
INSERT INTO `t_craft` VALUES (18, 3211498, 8, 250, '27_11_24_1_23', '12');
INSERT INTO `t_craft` VALUES (19, 3059786, 10, 313, '32_5_13_24_4_16_31_29_8_14_20_28_6_7_2_19_23', '21_22_24_25_29');
INSERT INTO `t_craft` VALUES (20, 3209248, 5, 45, '25_26_15_33_5', '23_24_25_26_27_29');
INSERT INTO `t_craft` VALUES (21, 1302215, 2, 6250, '30_29_28_31', '36_31');
INSERT INTO `t_craft` VALUES (22, 3031238, 8, 196, '28_29_11_15_26_27_25_33_1_9_10_12_5', '6');
INSERT INTO `t_craft` VALUES (23, 3005837, 6, 238, '32_2', '10_24_25_26_27_29');
INSERT INTO `t_craft` VALUES (24, 3036466, 6, 178, '28_29_15_33_25_26_11_27_14', '7_29');
INSERT INTO `t_craft` VALUES (25, 3246515, 2, 200, '26', '24');
INSERT INTO `t_craft` VALUES (26, 3247967, 2, 200, '26', '21');
INSERT INTO `t_craft` VALUES (27, 3246418, 6, 178, '28_29_15_33_25_26_11_27_14', '7_29');
INSERT INTO `t_craft` VALUES (28, 3213124, 6, 178, '28_29_15_33_25_26_11_27_14', '7_29');
INSERT INTO `t_craft` VALUES (29, 3050015, 6, 178, '28_29_15_33_25_26_11_27_14', '7_29');
INSERT INTO `t_craft` VALUES (30, 3048823, 6, 178, '28_29_15_33_25_26_11_27_14', '7_29');
INSERT INTO `t_craft` VALUES (31, 3005840, 6, 178, '28_29_15_33_25_26_11_27_14', '7_29');
INSERT INTO `t_craft` VALUES (32, 3000607, 6, 178, '28_29_15_33_25_26_11_27_14', '7_29');
INSERT INTO `t_craft` VALUES (33, 1413036, 6, 178, '28_29_15_33_25_26_11_27_14', '7_29');
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
INSERT INTO `t_equipment` VALUES (1, 'line01', 'line', 1);
INSERT INTO `t_equipment` VALUES (2, 'line02', 'line', 1);
INSERT INTO `t_equipment` VALUES (3, 'line03', 'line', 1);
INSERT INTO `t_equipment` VALUES (4, 'line04', 'line', 1);
INSERT INTO `t_equipment` VALUES (5, 'line05', 'line', 1);
INSERT INTO `t_equipment` VALUES (6, 'line06', 'line', 1);
INSERT INTO `t_equipment` VALUES (7, 'line07', 'line', 1);
INSERT INTO `t_equipment` VALUES (8, 'line08', 'line', 1);
INSERT INTO `t_equipment` VALUES (9, 'line10', 'line', 1);
INSERT INTO `t_equipment` VALUES (10, 'line11', 'line', 1);
INSERT INTO `t_equipment` VALUES (11, 'line12', 'line', 1);
INSERT INTO `t_equipment` VALUES (12, 'line14', 'line', 1);
INSERT INTO `t_equipment` VALUES (13, 'line15', 'line', 1);
INSERT INTO `t_equipment` VALUES (14, 'line17', 'line', 1);
INSERT INTO `t_equipment` VALUES (15, 'line18', 'line', 1);
INSERT INTO `t_equipment` VALUES (16, 'line19', 'line', 1);
INSERT INTO `t_equipment` VALUES (17, 'line20', 'line', 1);
INSERT INTO `t_equipment` VALUES (18, 'line30', 'line', 1);
INSERT INTO `t_equipment` VALUES (19, 'line31', 'line', 1);
INSERT INTO `t_equipment` VALUES (20, 'line32', 'line', 1);
INSERT INTO `t_equipment` VALUES (21, 'line33', 'line', 1);
INSERT INTO `t_equipment` VALUES (22, 'line34', 'line', 1);
INSERT INTO `t_equipment` VALUES (23, 'line35', 'line', 1);
INSERT INTO `t_equipment` VALUES (24, 'line36', 'line', 1);
INSERT INTO `t_equipment` VALUES (25, 'line37', 'line', 1);
INSERT INTO `t_equipment` VALUES (26, 'line38', 'line', 1);
INSERT INTO `t_equipment` VALUES (27, 'line39', 'line', 1);
INSERT INTO `t_equipment` VALUES (28, 'line40', 'line', 1);
INSERT INTO `t_equipment` VALUES (29, 'line41', 'line', 1);
INSERT INTO `t_equipment` VALUES (30, 'line42', 'line', 1);
INSERT INTO `t_equipment` VALUES (31, 'line43', 'line', 1);
INSERT INTO `t_equipment` VALUES (32, '弹片机', '弹片机', 3);
INSERT INTO `t_equipment` VALUES (33, '电阻测试机', '电阻测试机', 2);
INSERT INTO `t_equipment` VALUES (34, '高电压测试机', '高电压测试机', 5);
INSERT INTO `t_equipment` VALUES (35, '移印机', '移印机', 1);
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
INSERT INTO `t_order` VALUES (1, 3246515, 7700, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (2, 1413036, 44600, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (3, 1413036, 30000, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (4, 3246515, 8400, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (5, 3247967, 100, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (6, 3247967, 200, '2018-11-14 00:00:00');
INSERT INTO `t_order` VALUES (7, 3246418, 3200, '2018-11-14 00:00:00');
INSERT INTO `t_order` VALUES (8, 3048823, 6000, '2018-11-14 00:00:00');
INSERT INTO `t_order` VALUES (9, 3213124, 100, '2018-11-14 00:00:00');
INSERT INTO `t_order` VALUES (10, 3213124, 1500, '2018-11-15 00:00:00');
INSERT INTO `t_order` VALUES (11, 3005840, 200, '2018-11-15 00:00:00');
INSERT INTO `t_order` VALUES (12, 3005840, 4800, '2018-11-15 00:00:00');
INSERT INTO `t_order` VALUES (13, 3005837, 6400, '2018-11-15 00:00:00');
INSERT INTO `t_order` VALUES (14, 3246418, 5500, '2018-11-15 00:00:00');
INSERT INTO `t_order` VALUES (15, 3048823, 5000, '2018-11-16 00:00:00');
INSERT INTO `t_order` VALUES (16, 3050015, 8100, '2018-11-19 00:00:00');
INSERT INTO `t_order` VALUES (17, 3211498, 4000, '2018-11-19 00:00:00');
INSERT INTO `t_order` VALUES (18, 3211498, 1900, '2018-11-20 00:00:00');
INSERT INTO `t_order` VALUES (19, 3211498, 2100, '2018-11-20 00:00:00');
INSERT INTO `t_order` VALUES (20, 3209248, 1620, '2018-11-21 00:00:00');
INSERT INTO `t_order` VALUES (21, 3000607, 3400, '2018-11-22 00:00:00');
INSERT INTO `t_order` VALUES (22, 3000607, 500, '2018-11-22 00:00:00');
INSERT INTO `t_order` VALUES (23, 3050015, 2700, '2018-11-23 00:00:00');
INSERT INTO `t_order` VALUES (24, 3059786, 11400, '2018-12-05 00:00:00');
INSERT INTO `t_order` VALUES (25, 3209248, 1980, '2018-12-16 00:00:00');
INSERT INTO `t_order` VALUES (26, 1302215, 21000, '2018-12-07 00:00:00');
INSERT INTO `t_order` VALUES (27, 3031238, 5000, '2018-12-18 00:00:00');
INSERT INTO `t_order` VALUES (28, 3036466, 3250, '2018-12-20 00:00:00');
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
INSERT INTO `t_team` VALUES (3, '3组-李翠', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (4, '4组-赵东勤', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (5, '5组-童小玲', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (6, '6组-李倩', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (7, '7组-黄太娣', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (8, '8组-张正萍', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (9, '9组-张现敏', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (10, '10组-陈云', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (11, '11组-张娟', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (12, '12组-姚先兰', 5, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (13, '13组-刘金燕', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (14, '14组-周清', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (15, '15组-李长娟', 5, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (16, '16组-朱绍美', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (17, '17组-吴桂凤', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (18, '18组-芮娜', 3, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (19, '19组-王秀', 3, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (20, '20组-王树梅', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (21, '21组-陈荷花', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (22, '22组-陈开霞', 6, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (23, '23组-吴永凤', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (24, '24组-张娟1', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (25, '26组-杨丽', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (26, '27组-徐贤燕', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (27, '28组-杜碧珍', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (28, '30组-陈梅', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (29, '31组-史义玲', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (30, '33组-许丽萍', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (31, '36组-谢春霞', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (32, '39组-刘霞', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (33, '40组-高海燕', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (34, 'UKK组-王带娣', 3, 1, 5, 7, 18);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
