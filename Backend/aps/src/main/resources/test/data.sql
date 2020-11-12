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

 Date: 12/11/2020 17:08:01
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
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_craft
-- ----------------------------
BEGIN;
INSERT INTO `t_craft` VALUES (89, 3211498, 8, 250, '129_113_126_103_125', '48');
INSERT INTO `t_craft` VALUES (90, 3059786, 10, 313, '134_107_115_126_106_118_133_131_110_116_122_130_108_109_104_121_125', '57_58_60_61_65');
INSERT INTO `t_craft` VALUES (91, 3209248, 5, 45, '127_128_117_135_107', '59_60_61_62_63_65');
INSERT INTO `t_craft` VALUES (92, 1302215, 2, 6250, '132_131_130_133', '72_67');
INSERT INTO `t_craft` VALUES (93, 3031238, 8, 196, '130_131_113_117_128_129_127_135_103_111_112_114_107', '42');
INSERT INTO `t_craft` VALUES (94, 3005837, 6, 238, '134_104', '46_60_61_62_63_65');
INSERT INTO `t_craft` VALUES (95, 3036466, 6, 178, '130_131_117_135_127_128_113_129_116', '43_65');
INSERT INTO `t_craft` VALUES (96, 3246515, 2, 200, '128', '60');
INSERT INTO `t_craft` VALUES (97, 3246418, 6, 178, '130_131_117_135_127_128_113_129_116', '43_65');
INSERT INTO `t_craft` VALUES (98, 3213124, 6, 178, '130_131_117_135_127_128_113_129_116', '43_65');
INSERT INTO `t_craft` VALUES (99, 3050015, 6, 178, '130_131_117_135_127_128_113_129_116', '43_65');
INSERT INTO `t_craft` VALUES (100, 3048823, 6, 178, '130_131_117_135_127_128_113_129_116', '43_65');
INSERT INTO `t_craft` VALUES (101, 3031238, 6, 178, '130_131_117_135_127_128_113_129_116', '43_65');
INSERT INTO `t_craft` VALUES (102, 3005840, 6, 178, '130_131_117_135_127_128_113_129_116', '43_65');
INSERT INTO `t_craft` VALUES (103, 3000607, 6, 178, '130_131_117_135_127_128_113_129_116', '43_65');
INSERT INTO `t_craft` VALUES (104, 1413036, 6, 178, '130_131_117_135_127_128_113_129_116', '43_65');
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
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
INSERT INTO `t_order` VALUES (29, 3246515, 7700, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (30, 1413036, 44600, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (31, 1413036, 30000, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (32, 3246515, 8400, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (33, 3247967, 100, '2018-11-09 00:00:00');
INSERT INTO `t_order` VALUES (34, 3247967, 200, '2018-11-14 00:00:00');
INSERT INTO `t_order` VALUES (35, 3246418, 3200, '2018-11-14 00:00:00');
INSERT INTO `t_order` VALUES (36, 3048823, 6000, '2018-11-14 00:00:00');
INSERT INTO `t_order` VALUES (37, 3213124, 100, '2018-11-14 00:00:00');
INSERT INTO `t_order` VALUES (38, 3213124, 1500, '2018-11-15 00:00:00');
INSERT INTO `t_order` VALUES (39, 3005840, 200, '2018-11-15 00:00:00');
INSERT INTO `t_order` VALUES (40, 3005840, 4800, '2018-11-15 00:00:00');
INSERT INTO `t_order` VALUES (41, 3005837, 6400, '2018-11-15 00:00:00');
INSERT INTO `t_order` VALUES (42, 3246418, 5500, '2018-11-15 00:00:00');
INSERT INTO `t_order` VALUES (43, 3048823, 5000, '2018-11-16 00:00:00');
INSERT INTO `t_order` VALUES (44, 3050015, 8100, '2018-11-19 00:00:00');
INSERT INTO `t_order` VALUES (45, 3211498, 4000, '2018-11-19 00:00:00');
INSERT INTO `t_order` VALUES (46, 3211498, 1900, '2018-11-20 00:00:00');
INSERT INTO `t_order` VALUES (47, 3211498, 2100, '2018-11-20 00:00:00');
INSERT INTO `t_order` VALUES (48, 3209248, 1620, '2018-11-21 00:00:00');
INSERT INTO `t_order` VALUES (49, 3000607, 3400, '2018-11-22 00:00:00');
INSERT INTO `t_order` VALUES (50, 3000607, 500, '2018-11-22 00:00:00');
INSERT INTO `t_order` VALUES (51, 3050015, 2700, '2018-11-23 00:00:00');
INSERT INTO `t_order` VALUES (52, 3059786, 11400, '2018-12-05 00:00:00');
INSERT INTO `t_order` VALUES (53, 3209248, 1980, '2018-12-16 00:00:00');
INSERT INTO `t_order` VALUES (54, 1302215, 21000, '2018-12-07 00:00:00');
INSERT INTO `t_order` VALUES (55, 3031238, 5000, '2018-12-18 00:00:00');
INSERT INTO `t_order` VALUES (56, 3036466, 3250, '2018-12-20 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_team
-- ----------------------------
BEGIN;
INSERT INTO `t_team` VALUES (103, '1组-彭晓慧', 5, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (104, '2组-丁雪梅', 3, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (105, '3组-李翠', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (106, '4组-赵东勤', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (107, '5组-童小玲', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (108, '6组-李倩', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (109, '7组-黄太娣', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (110, '8组-张正萍', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (111, '9组-张现敏', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (112, '10组-陈云', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (113, '11组-张娟', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (114, '12组-姚先兰', 5, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (115, '13组-刘金燕', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (116, '14组-周清', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (117, '15组-李长娟', 5, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (118, '16组-朱绍美', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (119, '17组-吴桂凤', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (120, '18组-芮娜', 3, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (121, '19组-王秀', 3, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (122, '20组-王树梅', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (123, '21组-陈荷花', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (124, '22组-陈开霞', 6, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (125, '23组-吴永凤', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (126, '24组-张娟1', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (127, '26组-杨丽', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (128, '27组-徐贤燕', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (129, '28组-杜碧珍', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (130, '30组-陈梅', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (131, '31组-史义玲', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (132, '33组-许丽萍', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (133, '36组-谢春霞', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (134, '39组-刘霞', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (135, '40组-高海燕', 5, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (136, 'UKK组-王带娣', 3, 1, 5, 7, 18);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
