-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 2017-05-01 11:32:53
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
-- 表的结构 `friends`
--

CREATE TABLE `friends` (
  `id` varchar(255) NOT NULL,
  `userid` varchar(255) NOT NULL,
  `friend` varchar(255) NOT NULL,
  `friendname` varchar(100) NOT NULL COMMENT '//好友名字',
  `relation` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `tag` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `friends`
--

INSERT INTO `friends` (`id`, `userid`, `friend`, `friendname`, `relation`, `image`, `remark`, `address`, `tag`) VALUES
('00ce49fa-2e3d-11e7-aaf3-80fa5b0324f9', 'clong', 'tianjian', '田剑', '朋友', 'user3.jpg', '默认好友', '大丰', '测试'),
('00db9e82-2e3d-11e7-aaf3-80fa5b0324f9', 'tianjian', 'clong', '陈龙', '朋友', 'user4.jpg', '默认好友', '大丰', '测试');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
