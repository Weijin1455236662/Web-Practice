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

 Date: 05/01/2021 20:38:09
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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_craft
-- ----------------------------
BEGIN;
<<<<<<< Updated upstream
INSERT INTO `t_craft` VALUES (67, 3246418, 40, '84', '76', 4, 40, '79_80_83_81', '74_73', 40, '78');
INSERT INTO `t_craft` VALUES (68, 3213124, 40, '84', '76', 4, 40, '80_82_81_83', '73_75', 40, '78');
INSERT INTO `t_craft` VALUES (69, 3050015, 40, '84', '76', 4, 40, '81_82_79_80_83', '74', 40, '78');
INSERT INTO `t_craft` VALUES (70, 3048823, 40, '84', '76', 4, 40, '83_79_80_82_81', '73_75', 40, '78');
INSERT INTO `t_craft` VALUES (71, 3005840, 40, '84', '76', 4, 40, '79_81_82_83', '75', 40, '78');
INSERT INTO `t_craft` VALUES (72, 3031238, 40, '84', '76', 4, 40, '80_79_82_81_83', '74_73', 40, '78');
=======
INSERT INTO `t_craft` VALUES (50, 3211498, -1, -1, -1, 8, 100, '45_66_68_62_67', '48', 800, '70');
INSERT INTO `t_craft` VALUES (51, 3059786, -1, -1, -1, 10, 163, '76_49_57_68_65_60_66_48_52_58_64_72_50_51_46_63_67', '57_58_60_61_65', -1, -1);
INSERT INTO `t_craft` VALUES (52, 3209248, -1, -1, -1, 5, 45, '46_53_62_65_49', '59_60_61_62_63_65', -1, -1);
INSERT INTO `t_craft` VALUES (53, 1302215, -1, -1, -1, 2, 1400, '74_48_51_49', '72_67', -1, -1);
INSERT INTO `t_craft` VALUES (54, 3031238, 96, '57', '68', 4, 96, '51_60_67_62_70_45_46_61_66_53_54_56_49', '42', 96, '70');
INSERT INTO `t_craft` VALUES (55, 3005837, -1, -1, -1, 6, 108, '76_48_60_46', '46_60_61_62_63_65', 500, '69');
INSERT INTO `t_craft` VALUES (56, 3036466, 98, '57', '68', 4, 98, '51_60_62_61_74_53_67_45_64', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (57, 3246515, -1, -1, -1, 2, 200, '54', '60', 200, '70');
INSERT INTO `t_craft` VALUES (58, 3247967, -1, -1, -1, 2, 200, '54', '57', 200, '70');
INSERT INTO `t_craft` VALUES (59, 3246418, 98, '57', '68', 4, 98, '51_52_62_61_74_54_76_47_64', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (60, 3213124, 98, '57', '68', 4, 98, '63_52_65_61_74_56_76_47_64', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (61, 3050015, 98, '75', '68', 4, 98, '63_52_65_77_53_56_76_47_50', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (62, 3031238, 98, '75', '68', 4, 98, '72_73_59_77_69_70_55_71_50', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (63, 3048823, 98, '75', '68', 4, 98, '63_73_65_77_69_56_55_47_50', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (64, 3005840, 98, '75', '68', 4, 98, '72_73_59_77_69_70_55_71_58', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (65, 3000607, 98, '75', '68', 4, 98, '72_73_59_77_69_70_55_71_58', '43_65', 98, '70');
INSERT INTO `t_craft` VALUES (66, 1413036, 98, '75', '68', 4, 98, '72_73_59_77_69_70_55_71_58', '43_65', 98, '70');
>>>>>>> Stashed changes
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
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_equipment
-- ----------------------------
BEGIN;
INSERT INTO `t_equipment` VALUES (73, 'line01', 'line', 1);
INSERT INTO `t_equipment` VALUES (74, 'line02', 'line', 1);
INSERT INTO `t_equipment` VALUES (75, 'line03', 'line', 1);
INSERT INTO `t_equipment` VALUES (76, '弹片机', '弹片机', 3);
INSERT INTO `t_equipment` VALUES (77, '电阻测试机', '电阻测试机', 2);
INSERT INTO `t_equipment` VALUES (78, '高电压测试机', '高电压测试机', 5);
INSERT INTO `t_equipment` VALUES (79, '移印机', '移印机', 1);
INSERT INTO `t_equipment` VALUES (80, '自动包装机', '自动包装机', 2);
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
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
INSERT INTO `t_order` VALUES (79, 3246418, 100, '2018-11-14 00:00:00', 0);
INSERT INTO `t_order` VALUES (80, 3246418, 100, '2018-11-15 00:00:00', 0);
INSERT INTO `t_order` VALUES (81, 3213124, 100, '2018-11-14 00:00:00', 0);
INSERT INTO `t_order` VALUES (82, 3213124, 100, '2018-11-15 00:00:00', 0);
INSERT INTO `t_order` VALUES (83, 3050015, 100, '2018-11-15 00:00:00', 0);
INSERT INTO `t_order` VALUES (84, 3050015, 100, '2018-11-10 00:00:00', 0);
INSERT INTO `t_order` VALUES (85, 3048823, 100, '2018-11-14 00:00:00', 0);
INSERT INTO `t_order` VALUES (86, 3048823, 100, '2018-11-16 00:00:00', 0);
INSERT INTO `t_order` VALUES (87, 3031238, 100, '2018-11-09 00:00:00', 0);
INSERT INTO `t_order` VALUES (88, 3005840, 100, '2018-11-15 00:00:00', 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_team
-- ----------------------------
BEGIN;
<<<<<<< Updated upstream
INSERT INTO `t_team` VALUES (79, '1组-彭晓慧', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (80, '2组-丁雪梅', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (81, '3组-李翠', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (82, '4组-赵东勤', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (83, '5组-童小玲', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (84, '6组-李倩', 4, 1, 5, 7, 18);
=======
INSERT INTO `t_team` VALUES (45, '1组-彭晓慧', 5, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (46, '2组-丁雪梅', 3, 1, 5, 25200, 64800;
INSERT INTO `t_team` VALUES (47, '3组-李翠', 4, 1, 5, 25200, 64800;
INSERT INTO `t_team` VALUES (48, '4组-赵东勤', 3, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (49, '5组-童小玲', 5, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (50, '6组-李倩', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (51, '7组-黄太娣', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (52, '8组-张正萍', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (53, '9组-张现敏', 5, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (54, '10组-陈云', 5, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (55, '11组-张娟', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (56, '12组-姚先兰', 5, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (57, '13组-刘金燕', 3, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (58, '14组-周清', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (59, '15组-李长娟', 5, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (60, '16组-朱绍美', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (61, '17组-吴桂凤', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (62, '18组-芮娜', 3, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (63, '19组-王秀', 3, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (64, '20组-王树梅', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (65, '21组-陈荷花', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (66, '22组-陈开霞', 6, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (67, '23组-吴永凤', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (68, '24组-张娟1', 3, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (69, '26组-杨丽', 5, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (70, '27组-徐贤燕', 5, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (71, '28组-杜碧珍', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (72, '30组-陈梅', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (73, '31组-史义玲', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (74, '33组-许丽萍', 5, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (75, '36组-谢春霞', 4, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (76, '39组-刘霞', 3, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (77, '40组-高海燕', 5, 1, 5, 25200, 64800);
INSERT INTO `t_team` VALUES (78, 'UKK组-王带娣', 3, 1, 5, 25200, 64800);
>>>>>>> Stashed changes
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
