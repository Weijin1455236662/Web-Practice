SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_team`;
CREATE TABLE `t_team` (
  `teamid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` text,
  `num` int(11) NOT NULL,
  `begin_day` int(11) NOT NULL,
  `end_day` int(11) NOT NULL,
  `begin_time` int(11) NOT NULL,
  `end_time` int(11) NOT NULL,
  PRIMARY KEY (`teamid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO t_team VALUES ('1', '小刚', '5', '1', '5', '9', '22');
INSERT INTO t_team VALUES ('2', '小红', '4', '1', '5', '7', '20');
INSERT INTO t_team VALUES ('3', '小明', '3', '1', '5', '19', '7');
