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

 Date: 04/01/2021 21:43:21
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
INSERT INTO `databasechangelog` VALUES ('2020-11-13', 'wj', 'db.changelog/changelog-1.0.xml', '2020-11-13 13:06:24', 3, 'EXECUTED', '8:45102b3e499d5c0e7680d7bad847ade4', 'sql', '', NULL, '3.8.8', NULL, NULL, '5243983947');
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
  `punch_capacity` int(11) DEFAULT NULL,
  `punch_human_res` varchar(30) DEFAULT NULL,
  `punch_equipment_res` varchar(30) DEFAULT NULL,
  `human_num` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `human_res` varchar(200) DEFAULT NULL,
  `equipment_res` varchar(30) DEFAULT NULL,
  `test_capacity` int(11) DEFAULT NULL,
  `test_equipment_res` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`craftid`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_craft
-- ----------------------------
BEGIN;
INSERT INTO `t_craft` VALUES (50, 3211498, NULL, NULL, NULL, 8, 100, '45_66_68_62_67', '48', 800, '70');
INSERT INTO `t_craft` VALUES (51, 3059786, NULL, NULL, NULL, 10, 163, '76_49_57_68_65_60_66_48_52_58_64_72_50_51_46_63_67', '57_58_60_61_65', NULL, NULL);
INSERT INTO `t_craft` VALUES (52, 3209248, NULL, NULL, NULL, 5, 45, '46_53_62_65_49', '59_60_61_62_63_65', NULL, NULL);
INSERT INTO `t_craft` VALUES (53, 1302215, NULL, NULL, NULL, 2, 1400, '74_48_51_49', '72_67', NULL, NULL);
INSERT INTO `t_craft` VALUES (54, 3031238, 96, '57', '68', 4, 96, '51_60_67_62_70_45_46_61_66_53_54_56_49', '42', 96, '70');
INSERT INTO `t_craft` VALUES (55, 3005837, NULL, NULL, NULL, 6, 108, '76_48_60_46', '46_60_61_62_63_65', 500, '69');
INSERT INTO `t_craft` VALUES (56, 3036466, 98, '57', '68', 4, 98, '51_60_62_61_74_53_67_45_64', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (57, 3246515, NULL, NULL, NULL, 2, 200, '54', '60', 200, '70');
INSERT INTO `t_craft` VALUES (58, 3247967, NULL, NULL, NULL, 2, 200, '54', '57', 200, '70');
INSERT INTO `t_craft` VALUES (59, 3246418, 98, '57', '68', 4, 98, '51_52_62_61_74_54_76_47_64', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (60, 3213124, 98, '57', '68', 4, 98, '63_52_65_61_74_56_76_47_64', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (61, 3050015, 98, '75', '68', 4, 98, '63_52_65_77_53_56_76_47_50', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (62, 3031238, 98, '75', '68', 4, 98, '72_73_59_77_69_70_55_71_50', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (63, 3048823, 98, '75', '68', 4, 98, '63_73_65_77_69_56_55_47_50', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (64, 3005840, 98, '75', '68', 4, 98, '72_73_59_77_69_70_55_71_58', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (65, 3000607, 98, '75', '68', 4, 98, '72_73_59_77_69_70_55_71_58', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (66, 1413036, 98, '75', '68', 4, 98, '72_73_59_77_69_70_55_71_58', '43_65', 98, '70');
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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_equipment
-- ----------------------------
BEGIN;
INSERT INTO `t_equipment` VALUES (37, 'line01', 'line', 1);
INSERT INTO `t_equipment` VALUES (38, 'line02', 'line', 1);
INSERT INTO `t_equipment` VALUES (39, 'line03', 'line', 1);
INSERT INTO `t_equipment` VALUES (40, 'line04', 'line', 1);
INSERT INTO `t_equipment` VALUES (41, 'line05', 'line', 1);
INSERT INTO `t_equipment` VALUES (42, 'line06', 'line', 1);
INSERT INTO `t_equipment` VALUES (43, 'line07', 'line', 1);
INSERT INTO `t_equipment` VALUES (44, 'line08', 'line', 1);
INSERT INTO `t_equipment` VALUES (45, 'line10', 'line', 1);
INSERT INTO `t_equipment` VALUES (46, 'line11', 'line', 1);
INSERT INTO `t_equipment` VALUES (47, 'line12', 'line', 1);
INSERT INTO `t_equipment` VALUES (48, 'line14', 'line', 1);
INSERT INTO `t_equipment` VALUES (49, 'line15', 'line', 1);
INSERT INTO `t_equipment` VALUES (50, 'line17', 'line', 1);
INSERT INTO `t_equipment` VALUES (51, 'line18', 'line', 1);
INSERT INTO `t_equipment` VALUES (52, 'line19', 'line', 1);
INSERT INTO `t_equipment` VALUES (53, 'line20', 'line', 1);
INSERT INTO `t_equipment` VALUES (54, 'line30', 'line', 1);
INSERT INTO `t_equipment` VALUES (55, 'line31', 'line', 1);
INSERT INTO `t_equipment` VALUES (56, 'line32', 'line', 1);
INSERT INTO `t_equipment` VALUES (57, 'line33', 'line', 1);
INSERT INTO `t_equipment` VALUES (58, 'line34', 'line', 1);
INSERT INTO `t_equipment` VALUES (59, 'line35', 'line', 1);
INSERT INTO `t_equipment` VALUES (60, 'line36', 'line', 1);
INSERT INTO `t_equipment` VALUES (61, 'line37', 'line', 1);
INSERT INTO `t_equipment` VALUES (62, 'line38', 'line', 1);
INSERT INTO `t_equipment` VALUES (63, 'line39', 'line', 1);
INSERT INTO `t_equipment` VALUES (64, 'line40', 'line', 1);
INSERT INTO `t_equipment` VALUES (65, 'line41', 'line', 1);
INSERT INTO `t_equipment` VALUES (66, 'line42', 'line', 1);
INSERT INTO `t_equipment` VALUES (67, 'line43', 'line', 1);
INSERT INTO `t_equipment` VALUES (68, '弹片机', '弹片机', 3);
INSERT INTO `t_equipment` VALUES (69, '电阻测试机', '电阻测试机', 2);
INSERT INTO `t_equipment` VALUES (70, '高电压测试机', '高电压测试机', 5);
INSERT INTO `t_equipment` VALUES (71, '移印机', '移印机', 1);
INSERT INTO `t_equipment` VALUES (72, '自动包装机', '自动包装机', 2);
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
  `urgent` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
INSERT INTO `t_order` VALUES (29, 3247967, 100, '2018-11-09 00:00:00', 0);
INSERT INTO `t_order` VALUES (30, 3247967, 200, '2018-11-14 00:00:00', 0);
INSERT INTO `t_order` VALUES (31, 3246515, 400, '2018-11-09 00:00:00', 0);
INSERT INTO `t_order` VALUES (32, 3246515, 400, '2018-11-09 00:00:00', 0);
INSERT INTO `t_order` VALUES (33, 3246418, 200, '2018-11-14 00:00:00', 0);
INSERT INTO `t_order` VALUES (34, 3246418, 200, '2018-11-15 00:00:00', 0);
INSERT INTO `t_order` VALUES (35, 3213124, 100, '2018-11-14 00:00:00', 0);
INSERT INTO `t_order` VALUES (36, 3213124, 200, '2018-11-15 00:00:00', 0);
INSERT INTO `t_order` VALUES (37, 3211498, 200, '2018-11-16 00:00:00', 0);
INSERT INTO `t_order` VALUES (38, 3211498, 200, '2018-11-16 00:00:00', 0);
INSERT INTO `t_order` VALUES (39, 3211498, 200, '2018-11-15 00:00:00', 0);
INSERT INTO `t_order` VALUES (40, 3209248, 100, '2018-11-14 00:00:00', 0);
INSERT INTO `t_order` VALUES (41, 3209248, 100, '2018-11-16 00:00:00', 0);
INSERT INTO `t_order` VALUES (42, 3059786, 330, '2018-11-15 00:00:00', 0);
INSERT INTO `t_order` VALUES (43, 3050015, 200, '2018-11-15 00:00:00', 0);
INSERT INTO `t_order` VALUES (44, 3050015, 200, '2018-11-10 00:00:00', 0);
INSERT INTO `t_order` VALUES (45, 3048823, 200, '2018-11-14 00:00:00', 0);
INSERT INTO `t_order` VALUES (46, 3048823, 200, '2018-11-16 00:00:00', 0);
INSERT INTO `t_order` VALUES (47, 3036466, 210, '2018-11-12 00:00:00', 0);
INSERT INTO `t_order` VALUES (48, 3031238, 190, '2018-11-09 00:00:00', 0);
INSERT INTO `t_order` VALUES (49, 3005840, 200, '2018-11-15 00:00:00', 0);
INSERT INTO `t_order` VALUES (50, 3005840, 200, '2018-11-15 00:00:00', 0);
INSERT INTO `t_order` VALUES (51, 3005837, 230, '2018-11-15 00:00:00', 0);
INSERT INTO `t_order` VALUES (52, 3000607, 200, '2018-11-12 00:00:00', 0);
INSERT INTO `t_order` VALUES (53, 3000607, 210, '2018-11-13 00:00:00', 0);
INSERT INTO `t_order` VALUES (54, 1413036, 200, '2018-11-09 00:00:00', 0);
INSERT INTO `t_order` VALUES (55, 1413036, 200, '2018-11-09 00:00:00', 0);
INSERT INTO `t_order` VALUES (56, 1302215, 2800, '2018-11-14 00:00:00', 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_team
-- ----------------------------
BEGIN;
INSERT INTO `t_team` VALUES (45, '1组-彭晓慧', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (46, '2组-丁雪梅', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (47, '3组-李翠', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (48, '4组-赵东勤', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (49, '5组-童小玲', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (50, '6组-李倩', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (51, '7组-黄太娣', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (52, '8组-张正萍', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (53, '9组-张现敏', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (54, '10组-陈云', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (55, '11组-张娟', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (56, '12组-姚先兰', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (57, '13组-刘金燕', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (58, '14组-周清', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (59, '15组-李长娟', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (60, '16组-朱绍美', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (61, '17组-吴桂凤', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (62, '18组-芮娜', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (63, '19组-王秀', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (64, '20组-王树梅', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (65, '21组-陈荷花', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (66, '22组-陈开霞', 6, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (67, '23组-吴永凤', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (68, '24组-张娟1', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (69, '26组-杨丽', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (70, '27组-徐贤燕', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (71, '28组-杜碧珍', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (72, '30组-陈梅', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (73, '31组-史义玲', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (74, '33组-许丽萍', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (75, '36组-谢春霞', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (76, '39组-刘霞', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (77, '40组-高海燕', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (78, 'UKK组-王带娣', 3, 1, 5, 7, 18);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
