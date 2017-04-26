/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-04-26 18:07:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `friends`
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `id` varchar(255) NOT NULL,
  `userid` varchar(255) NOT NULL,
  `friend` varchar(255) NOT NULL,
  `relation` varchar(255) NOT NULL,
  `imgage` varchar(255) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `tag` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES ('1', 'root1@qq.com', 'root@qq.com', '系统标记', 'img/icon.jpg', '备注', '大丰', '好友');
INSERT INTO `friends` VALUES ('2', 'root@qq.com', 'root1@qq.com', '系统标记', 'img/icon.jpg', '备注', '盐城', '好基友');
