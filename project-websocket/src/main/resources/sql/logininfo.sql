/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-04-26 18:08:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `logininfo`
-- ----------------------------
DROP TABLE IF EXISTS `logininfo`;
CREATE TABLE `logininfo` (
  `userid` varchar(255) NOT NULL,
  `loginway` varchar(255) NOT NULL,
  `ip` varchar(255) NOT NULL,
  `signintime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `signuptime` datetime DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logininfo
-- ----------------------------
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:39:50', null, '75fd4d92b98c24a4bf29dc0cd901263e', '002ba057-2a32-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 17:44:55', null, '1dd0dec081c0ae85098f922ed376ea35', '01cfb655-2a65-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '127.0.0.1', '2017-04-26 16:47:43', null, '8a0b29b81d5758b65b30c7070f0d67fd', '031270de-2a5d-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '127.0.0.1', '2017-04-26 15:57:47', null, '7542d4eed4e678341c1697aaac65109f', '08f5b01b-2a56-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '127.0.0.1', '2017-04-26 15:57:53', null, 'e201342c7f5a2b0d458e4d2a8984eabf', '0c8ae123-2a56-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'pc', '127.0.0.1', '2017-04-03 10:24:58', '2017-04-04 10:25:03', '22', '1');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '127.0.0.1', '2017-04-26 16:48:06', null, '8a79c2925c7a927db8470cdbc522d260', '10684e00-2a5d-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:04:42', null, 'd6fae117bb39d8b771dd7f9c4ad640b3', '183ad8fe-2a2d-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '127.0.0.1', '2017-04-26 15:51:11', null, '0232a599b1bfbef7162776b9299caf53', '1d26f988-2a55-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:40:54', null, '34229b495ad8c214f3a1cf8772bfa5c8', '265c72f0-2a32-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '127.0.0.1', '2017-04-26 15:51:35', null, '2e6f01ff7551b91f7e1f300e31fa83a2', '2b89e860-2a55-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:26:48', null, 'e26d5911713285bbfe959d1869fb286d', '2e95f1ef-2a30-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root1@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 17:46:10', null, '7bfb05feb15b6ef9b21b636f437f1f03', '2ebe2cc7-2a65-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:41:38', null, '492405f250c8bec6596cf331bf0c67f6', '40e39c2b-2a32-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 13:36:13', null, 'b2991c70e513ea15475993e4d3c5c581', '42502f0d-2a42-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:20:13', null, 'adb5937a368d64e077749f4558a53047', '430c4f55-2a2f-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '127.0.0.1', '2017-04-26 15:59:34', null, 'b44b151d4a5effaec672f46cd6e1b1a9', '48d084f8-2a56-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:56:12', null, '60eed1dcbb3e812d5b9821ef58ddc47c', '49e52a5e-2a34-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '127.0.0.1', '2017-04-26 15:59:37', null, '013d68b85d19ea61430adde6ba4e703c', '4abf3143-2a56-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '127.0.0.1', '2017-04-26 15:52:33', null, 'e0a63b78000ffb65a88caab6a4e4fd87', '4e12158b-2a55-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:13:24', null, 'a87dbc662bfc3b2e67e5df449fd47fb7', '4ee3a894-2a2e-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 12:53:39', null, 'c831aa5dfdec0c0cd0ee0b592f0acdf4', '50869fe2-2a3c-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 13:36:39', null, 'b7ccc9af34a2929e06720da6b2da4c72', '51db17e6-2a42-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '127.0.0.1', '2017-04-26 11:21:08', null, 'be39901ce46fee3feca14b4b6b788058', '63cfb281-2a2f-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '127.0.0.1', '2017-04-26 16:00:22', null, '145c207ef871b17a394ff1a37bb72093', '65a14d17-2a56-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '127.0.0.1', '2017-04-26 15:53:19', null, '89192a0074c3f5d86184c0e9146d21c4', '694d929f-2a55-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root1@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 12:54:29', null, '2032b9d3548770e070c812eafcb0ad1d', '6ddcf730-2a3c-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '127.0.0.1', '2017-04-26 16:00:41', null, '57c28c80a12b48d12ab166db3c43a867', '71160945-2a56-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '127.0.0.1', '2017-04-26 16:07:52', null, '21c4f741d1d3b63d84a103e7a0e9857c', '71ae6edb-2a57-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:07:30', null, '846913903a077988befe92878a84dcf1', '7c40a18a-2a2d-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:21:52', null, '022a1a467b08cfbe1f47fa3e373f40b8', '7dd4ea0e-2a2f-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:36:11', null, 'fa9a1860f4402b5bc7a8fcfdbe6ffcca', '7dffdebc-2a31-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:14:43', null, '34b6a84cf64ccd4da9c52f7fbe574111', '7e698d86-2a2e-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:07:35', null, '8734eb8f6c1fb3104d43a8aad9683bce', '7edf094a-2a2d-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '127.0.0.1', '2017-04-26 17:12:45', null, 'efb8c919026d7c0ed588d3cb1514db3c', '8223bc7a-2a60-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '127.0.0.1', '2017-04-26 16:08:28', null, 'f8f0a337f7c5bf7f6fd0ab53df40dc03', '86ff9ff5-2a57-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '127.0.0.1', '2017-04-26 16:08:35', null, '8c051f2cbe66f4bf7ff1678c43effec5', '8b35f32a-2a57-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '127.0.0.1', '2017-04-26 16:08:39', null, 'c30c8f8c12e625d63589e04403cc3102', '8de4b88a-2a57-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:15:16', null, '5564f79b466100ea9056195657291d71', '92290bff-2a2e-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '127.0.0.1', '2017-04-26 17:27:37', null, '89c94968124356029e2dcc140d48497a', '9617bda2-2a62-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:51:22', null, 'ede1a25393d32d91ad41e365d19238c4', '9cca22cb-2a33-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:08:39', null, '5564f79b466100ea9056195657291d71', 'a57611b1-2a2d-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root1@qq.com', 'PC', '127.0.0.1', '2017-04-26 12:56:02', null, '8ed998bc12bc1490f2873996b6ca08d9', 'a5c215df-2a3c-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '127.0.0.1', '2017-04-26 17:13:45', null, '633619e2b0fd96f3d29ca791c1826880', 'a5faff63-2a60-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:15:52', null, '912660a5c7d9b23507aab943de3b0a0c', 'a748dc40-2a2e-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '127.0.0.1', '2017-04-26 17:56:34', null, '83e17731f34efdc4e65945e161640191', 'a852dc88-2a66-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:08:53', null, '1376a72b107ae4edfbed4be4e5ddf640', 'ad6595a0-2a2d-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '127.0.0.1', '2017-04-26 12:56:16', null, '420264b279c3efd0a9d7882ba121a787', 'adf956b1-2a3c-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '127.0.0.1', '2017-04-26 17:14:04', null, '6789f9b41b298a0e4ef8b49a9bc34038', 'b1895cc5-2a60-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:09:17', null, '972a46efd3c5e90d63feb45bdca79cce', 'bbdfb1c7-2a2d-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '127.0.0.1', '2017-04-26 15:55:55', null, '8d8e364a0e170b4e4e3dbe9b4a10d631', 'c6716201-2a55-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 12:57:01', null, '218ea6dbc28dd621ec7960364801bb83', 'c8ba8fcb-2a3c-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '127.0.0.1', '2017-04-26 11:38:25', null, 'ab949960b98b3eb240522e4c332ba150', 'cd84b154-2a31-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:09:49', null, 'e889d5dfc8d0c927896da8d17c5eed44', 'ced658eb-2a2d-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root1@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 12:57:14', null, '36587606e9e385a0f7059d9b556d9c74', 'd061f0bc-2a3c-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 17:43:38', null, '0a4245e5829fb8561dc028242ec535bd', 'd2522e4f-2a64-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '127.0.0.1', '2017-04-26 15:56:38', null, '8a9ff156302d096eed60d9ddc21e35cd', 'e05c166d-2a55-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '127.0.0.1', '2017-04-26 15:56:48', null, 'b4b02f2225219f1f1a1d2105b92d5c33', 'e62e1287-2a55-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '127.0.0.1', '2017-04-26 16:46:57', null, 'f83f3419f409235d32b5126509b28cde', 'e7a04962-2a5c-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:03:26', null, '86060052bb2ffa09f41dbf666b1ef974', 'eb73e45d-2a2c-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('陈龙', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 17:44:28', null, 'ede1a25393d32d91ad41e365d19238c4', 'f07b440d-2a64-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('田剑', 'PC', '127.0.0.1', '2017-04-26 17:16:12', null, '01798e68cca5947ae0acaf2c9a24dd6b', 'fd44d159-2a60-11e7-8a12-68f728ccfde6');
INSERT INTO `logininfo` VALUES ('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 11:18:17', null, '6f3bb137bd72aea56f5a26d19e70d249', 'fd8642f5-2a2e-11e7-8a12-68f728ccfde6');
