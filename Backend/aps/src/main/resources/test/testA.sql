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

 Date: 12/11/2020 18:48:56
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_craft
-- ----------------------------
BEGIN;
INSERT INTO `t_craft` VALUES (1, 3211498, 8, 250, '27_11_24_1_23', '12');
INSERT INTO `t_craft` VALUES (10, 3005837, 6, 238, '32_2', '10_24_25_26_27_29');
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
INSERT INTO `t_equipment` VALUES (10, 'line11', 'line', 1);
INSERT INTO `t_equipment` VALUES (12, 'line14', 'line', 1);
INSERT INTO `t_equipment` VALUES (24, 'line36', 'line', 1);
INSERT INTO `t_equipment` VALUES (25, 'line37', 'line', 1);
INSERT INTO `t_equipment` VALUES (26, 'line38', 'line', 1);
INSERT INTO `t_equipment` VALUES (27, 'line39', 'line', 1);
INSERT INTO `t_equipment` VALUES (29, 'line41', 'line', 1);
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
INSERT INTO `t_order` VALUES (13, 3005837, 6400, '2018-11-15 00:00:00');
INSERT INTO `t_order` VALUES (17, 3211498, 4000, '2018-11-19 00:00:00');
INSERT INTO `t_order` VALUES (18, 3211498, 1900, '2018-11-20 00:00:00');
INSERT INTO `t_order` VALUES (19, 3211498, 2100, '2018-11-20 00:00:00');
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
INSERT INTO `t_team` VALUES (2, '2组-丁雪梅', 3, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (11, '11组-张娟', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (23, '23组-吴永凤', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (24, '24组-张娟1', 4, 1, 5, 7, 18);
INSERT INTO `t_team` VALUES (27, '28组-杜碧珍', 4, 1, 5, 19, 6);
INSERT INTO `t_team` VALUES (32, '39组-刘霞', 3, 1, 5, 7, 18);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
