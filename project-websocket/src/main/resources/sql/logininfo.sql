-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 2017-05-01 11:33:02
-- 服务器版本： 5.7.15-log
-- PHP Version: 5.6.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chat`
--

-- --------------------------------------------------------

--
-- 表的结构 `logininfo`
--

CREATE TABLE `logininfo` (
  `userid` varchar(255) NOT NULL,
  `loginway` varchar(255) NOT NULL,
  `ip` varchar(255) NOT NULL,
  `signintime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `signuptime` datetime DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `logininfo`
--

INSERT INTO `logininfo` (`userid`, `loginway`, `ip`, `signintime`, `signuptime`, `token`, `id`) VALUES
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:39:50', NULL, '75fd4d92b98c24a4bf29dc0cd901263e', '002ba057-2a32-11e7-8a12-68f728ccfde6'),
('陈龙', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 09:44:55', NULL, '1dd0dec081c0ae85098f922ed376ea35', '01cfb655-2a65-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '127.0.0.1', '2017-04-26 08:47:43', NULL, '8a0b29b81d5758b65b30c7070f0d67fd', '031270de-2a5d-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-29 13:16:19', NULL, '91c7e3ed0f6b96906d08bba300819318', '08186371-2cde-11e7-aaf3-80fa5b0324f9'),
('陈龙', 'PC', '127.0.0.1', '2017-04-26 07:57:47', NULL, '7542d4eed4e678341c1697aaac65109f', '08f5b01b-2a56-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '127.0.0.1', '2017-04-26 07:57:53', NULL, 'e201342c7f5a2b0d458e4d2a8984eabf', '0c8ae123-2a56-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'pc', '127.0.0.1', '2017-04-03 02:24:58', '2017-04-04 10:25:03', '22', '1'),
('陈龙', 'PC', '127.0.0.1', '2017-04-26 08:48:06', NULL, '8a79c2925c7a927db8470cdbc522d260', '10684e00-2a5d-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '0:0:0:0:0:0:0:1', '2017-04-29 14:06:48', NULL, '8905c396ff36465b42e6904a6127ea3c', '15c436f3-2ce5-11e7-aaf3-80fa5b0324f9'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:04:42', NULL, 'd6fae117bb39d8b771dd7f9c4ad640b3', '183ad8fe-2a2d-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '127.0.0.1', '2017-04-26 07:51:11', NULL, '0232a599b1bfbef7162776b9299caf53', '1d26f988-2a55-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '0:0:0:0:0:0:0:1', '2017-04-29 14:07:03', NULL, '99de70813393a15391d144957cb561a4', '1e82923c-2ce5-11e7-aaf3-80fa5b0324f9'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:40:54', NULL, '34229b495ad8c214f3a1cf8772bfa5c8', '265c72f0-2a32-11e7-8a12-68f728ccfde6'),
('陈龙', 'PC', '127.0.0.1', '2017-04-26 07:51:35', NULL, '2e6f01ff7551b91f7e1f300e31fa83a2', '2b89e860-2a55-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:26:48', NULL, 'e26d5911713285bbfe959d1869fb286d', '2e95f1ef-2a30-11e7-8a12-68f728ccfde6'),
('root1@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 09:46:10', NULL, '7bfb05feb15b6ef9b21b636f437f1f03', '2ebe2cc7-2a65-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '0:0:0:0:0:0:0:1', '2017-04-29 14:07:39', NULL, '1636874858db7858c3c6bc9454fb03dd', '33d3faa8-2ce5-11e7-aaf3-80fa5b0324f9'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:41:38', NULL, '492405f250c8bec6596cf331bf0c67f6', '40e39c2b-2a32-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 05:36:13', NULL, 'b2991c70e513ea15475993e4d3c5c581', '42502f0d-2a42-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:20:13', NULL, 'adb5937a368d64e077749f4558a53047', '430c4f55-2a2f-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '127.0.0.1', '2017-04-26 07:59:34', NULL, 'b44b151d4a5effaec672f46cd6e1b1a9', '48d084f8-2a56-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:56:12', NULL, '60eed1dcbb3e812d5b9821ef58ddc47c', '49e52a5e-2a34-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '0:0:0:0:0:0:0:1', '2017-04-29 13:25:20', NULL, 'f0f459f45f4262deea466c3490f5b150', '4a8fc2ba-2cdf-11e7-aaf3-80fa5b0324f9'),
('陈龙', 'PC', '127.0.0.1', '2017-04-26 07:59:37', NULL, '013d68b85d19ea61430adde6ba4e703c', '4abf3143-2a56-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '127.0.0.1', '2017-04-26 07:52:33', NULL, 'e0a63b78000ffb65a88caab6a4e4fd87', '4e12158b-2a55-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:13:24', NULL, 'a87dbc662bfc3b2e67e5df449fd47fb7', '4ee3a894-2a2e-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 04:53:39', NULL, 'c831aa5dfdec0c0cd0ee0b592f0acdf4', '50869fe2-2a3c-11e7-8a12-68f728ccfde6'),
('陈龙', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 05:36:39', NULL, 'b7ccc9af34a2929e06720da6b2da4c72', '51db17e6-2a42-11e7-8a12-68f728ccfde6'),
('tianjian', 'PC', '0:0:0:0:0:0:0:1', '2017-04-30 03:51:44', NULL, '14adcb5ad458e539d7a4884f644d2ded', '6037b0b4-2d58-11e7-aaf3-80fa5b0324f9'),
('root1@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-29 08:46:51', NULL, '647c31fcdc8a7d7025d89d3e77e24dd3', '638b0405-2cb8-11e7-aaf3-80fa5b0324f9'),
('root@qq.com', 'PC', '127.0.0.1', '2017-04-26 03:21:08', NULL, 'be39901ce46fee3feca14b4b6b788058', '63cfb281-2a2f-11e7-8a12-68f728ccfde6'),
('陈龙', 'PC', '127.0.0.1', '2017-04-26 08:00:22', NULL, '145c207ef871b17a394ff1a37bb72093', '65a14d17-2a56-11e7-8a12-68f728ccfde6'),
('clong', 'PC', '0:0:0:0:0:0:0:1', '2017-04-30 03:51:56', NULL, '48b0e6095ebea649ee10df2a8ceb3dff', '6778beef-2d58-11e7-aaf3-80fa5b0324f9'),
('陈龙', 'PC', '127.0.0.1', '2017-04-26 07:53:19', NULL, '89192a0074c3f5d86184c0e9146d21c4', '694d929f-2a55-11e7-8a12-68f728ccfde6'),
('陈龙', 'PC', '0:0:0:0:0:0:0:1', '2017-04-30 03:44:50', NULL, 'faaf569c3550b0598d9c9b5a8fc6fc27', '6958abdd-2d57-11e7-aaf3-80fa5b0324f9'),
('root1@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 04:54:29', NULL, '2032b9d3548770e070c812eafcb0ad1d', '6ddcf730-2a3c-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '127.0.0.1', '2017-04-26 08:00:41', NULL, '57c28c80a12b48d12ab166db3c43a867', '71160945-2a56-11e7-8a12-68f728ccfde6'),
('陈龙', 'PC', '127.0.0.1', '2017-04-26 08:07:52', NULL, '21c4f741d1d3b63d84a103e7a0e9857c', '71ae6edb-2a57-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:07:30', NULL, '846913903a077988befe92878a84dcf1', '7c40a18a-2a2d-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:21:52', NULL, '022a1a467b08cfbe1f47fa3e373f40b8', '7dd4ea0e-2a2f-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:36:11', NULL, 'fa9a1860f4402b5bc7a8fcfdbe6ffcca', '7dffdebc-2a31-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:14:43', NULL, '34b6a84cf64ccd4da9c52f7fbe574111', '7e698d86-2a2e-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:07:35', NULL, '8734eb8f6c1fb3104d43a8aad9683bce', '7edf094a-2a2d-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '127.0.0.1', '2017-04-26 09:12:45', NULL, 'efb8c919026d7c0ed588d3cb1514db3c', '8223bc7a-2a60-11e7-8a12-68f728ccfde6'),
('陈龙', 'PC', '127.0.0.1', '2017-04-26 08:08:28', NULL, 'f8f0a337f7c5bf7f6fd0ab53df40dc03', '86ff9ff5-2a57-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '127.0.0.1', '2017-04-26 08:08:35', NULL, '8c051f2cbe66f4bf7ff1678c43effec5', '8b35f32a-2a57-11e7-8a12-68f728ccfde6'),
('root1', 'PC', '0:0:0:0:0:0:0:1', '2017-05-01 03:37:08', NULL, 'cc389e37b25011058bd0c67f95130884', '8c09fe38-2e1f-11e7-aaf3-80fa5b0324f9'),
('陈龙', 'PC', '127.0.0.1', '2017-04-26 08:08:39', NULL, 'c30c8f8c12e625d63589e04403cc3102', '8de4b88a-2a57-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '0:0:0:0:0:0:0:1', '2017-04-29 13:55:56', NULL, '103df61698a253772b7e94da1da41ddc', '91207ced-2ce3-11e7-aaf3-80fa5b0324f9'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:15:16', NULL, '5564f79b466100ea9056195657291d71', '92290bff-2a2e-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '127.0.0.1', '2017-04-26 09:27:37', NULL, '89c94968124356029e2dcc140d48497a', '9617bda2-2a62-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:51:22', NULL, 'ede1a25393d32d91ad41e365d19238c4', '9cca22cb-2a33-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:08:39', NULL, '5564f79b466100ea9056195657291d71', 'a57611b1-2a2d-11e7-8a12-68f728ccfde6'),
('root1@qq.com', 'PC', '127.0.0.1', '2017-04-26 04:56:02', NULL, '8ed998bc12bc1490f2873996b6ca08d9', 'a5c215df-2a3c-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '127.0.0.1', '2017-04-26 09:13:45', NULL, '633619e2b0fd96f3d29ca791c1826880', 'a5faff63-2a60-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:15:52', NULL, '912660a5c7d9b23507aab943de3b0a0c', 'a748dc40-2a2e-11e7-8a12-68f728ccfde6'),
('陈龙', 'PC', '127.0.0.1', '2017-04-26 09:56:34', NULL, '83e17731f34efdc4e65945e161640191', 'a852dc88-2a66-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:08:53', NULL, '1376a72b107ae4edfbed4be4e5ddf640', 'ad6595a0-2a2d-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '127.0.0.1', '2017-04-26 04:56:16', NULL, '420264b279c3efd0a9d7882ba121a787', 'adf956b1-2a3c-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '127.0.0.1', '2017-04-26 09:14:04', NULL, '6789f9b41b298a0e4ef8b49a9bc34038', 'b1895cc5-2a60-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '0:0:0:0:0:0:0:1', '2017-04-29 13:42:35', NULL, 'c2a076051017300789c031db9853e77f', 'b3ae98b2-2ce1-11e7-aaf3-80fa5b0324f9'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:09:17', NULL, '972a46efd3c5e90d63feb45bdca79cce', 'bbdfb1c7-2a2d-11e7-8a12-68f728ccfde6'),
('陈龙', 'PC', '127.0.0.1', '2017-04-26 07:55:55', NULL, '8d8e364a0e170b4e4e3dbe9b4a10d631', 'c6716201-2a55-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 04:57:01', NULL, '218ea6dbc28dd621ec7960364801bb83', 'c8ba8fcb-2a3c-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '127.0.0.1', '2017-04-26 03:38:25', NULL, 'ab949960b98b3eb240522e4c332ba150', 'cd84b154-2a31-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:09:49', NULL, 'e889d5dfc8d0c927896da8d17c5eed44', 'ced658eb-2a2d-11e7-8a12-68f728ccfde6'),
('root1@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 04:57:14', NULL, '36587606e9e385a0f7059d9b556d9c74', 'd061f0bc-2a3c-11e7-8a12-68f728ccfde6'),
('陈龙', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 09:43:38', NULL, '0a4245e5829fb8561dc028242ec535bd', 'd2522e4f-2a64-11e7-8a12-68f728ccfde6'),
('陈龙', 'PC', '0:0:0:0:0:0:0:1', '2017-04-29 13:22:05', NULL, 'fa95e233703d030d7a063f1c60b5d7ce', 'd62d215a-2cde-11e7-aaf3-80fa5b0324f9'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-29 13:15:07', NULL, 'f5df8c3759bf0eedc0cbc44577a1d28f', 'dd16afdf-2cdd-11e7-aaf3-80fa5b0324f9'),
('陈龙', 'PC', '127.0.0.1', '2017-04-26 07:56:38', NULL, '8a9ff156302d096eed60d9ddc21e35cd', 'e05c166d-2a55-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '127.0.0.1', '2017-04-26 07:56:48', NULL, 'b4b02f2225219f1f1a1d2105b92d5c33', 'e62e1287-2a55-11e7-8a12-68f728ccfde6'),
('陈龙', 'PC', '127.0.0.1', '2017-04-26 08:46:57', NULL, 'f83f3419f409235d32b5126509b28cde', 'e7a04962-2a5c-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:03:26', NULL, '86060052bb2ffa09f41dbf666b1ef974', 'eb73e45d-2a2c-11e7-8a12-68f728ccfde6'),
('陈龙', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 09:44:28', NULL, 'ede1a25393d32d91ad41e365d19238c4', 'f07b440d-2a64-11e7-8a12-68f728ccfde6'),
('田剑', 'PC', '0:0:0:0:0:0:0:1', '2017-04-29 13:23:05', NULL, '51280ab5d158a40459f1bb9643e7cd70', 'f9eff93b-2cde-11e7-aaf3-80fa5b0324f9'),
('田剑', 'PC', '127.0.0.1', '2017-04-26 09:16:12', NULL, '01798e68cca5947ae0acaf2c9a24dd6b', 'fd44d159-2a60-11e7-8a12-68f728ccfde6'),
('root@qq.com', 'PC', '0:0:0:0:0:0:0:1', '2017-04-26 03:18:17', NULL, '6f3bb137bd72aea56f5a26d19e70d249', 'fd8642f5-2a2e-11e7-8a12-68f728ccfde6');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `logininfo`
--
ALTER TABLE `logininfo`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
