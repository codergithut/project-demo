/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-04-27 11:17:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userid` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `group_id` varchar(255) DEFAULT NULL,
  `register` timestamp NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(23) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('root1@qq.com', null, 'root1', 'test', null, '上线', null, null);
INSERT INTO `users` VALUES ('root@qq.com', null, 'root', 'test', '2017-04-24 16:16:25', '上线', null, null);
INSERT INTO `users` VALUES ('周丽娟', null, 'root', null, '2017-04-25 16:16:37', null, null, null);
INSERT INTO `users` VALUES ('田剑', null, 'root', null, null, '上线', null, null);
INSERT INTO `users` VALUES ('陈龙', null, 'root', null, null, '上线', null, null);
