-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- ホスト: 127.0.0.1
-- 生成日時: 2023-12-07 02:41:49
-- サーバのバージョン： 10.4.28-MariaDB
-- PHP のバージョン: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- データベース: `healthcheck`
--

-- --------------------------------------------------------

--
-- テーブルの構造 `user`
--

CREATE TABLE `user` (
  `USER_ID` int(11) NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `is_deleted` tinyint(1) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- テーブルのデータのダンプ `user`
--

INSERT INTO `user` (`USER_ID`, `NAME`, `is_deleted`, `created_at`, `updated_at`) VALUES
(1, '山田　太郎', 0, '2023-12-06', '2023-12-06'),
(2, '田中　次郎', 1, '2023-12-06', NULL),
(3, '中島　三郎', 1, '2023-12-06', NULL);

-- --------------------------------------------------------

--
-- テーブルの構造 `user_info`
--

CREATE TABLE `user_info` (
  `u_id` int(11) NOT NULL,
  `height` double NOT NULL,
  `weight` double NOT NULL,
  `bmi` double NOT NULL,
  `targetBmi` double DEFAULT NULL,
  `targetWeight` double DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `is_deleted` tinyint(1) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- テーブルのデータのダンプ `user_info`
--

INSERT INTO `user_info` (`u_id`, `height`, `weight`, `bmi`, `targetBmi`, `targetWeight`, `user_id`, `is_deleted`, `created_at`, `updated_at`) VALUES
(1, 172, 65, 21.97, 20, -5.83, 1, 0, '2023-12-06', '2023-12-06'),
(2, 165.5, 56.7, 20.7, NULL, NULL, 2, 1, '2023-12-06', NULL),
(3, 185, 78.6, 22.96, NULL, NULL, 3, 1, '2023-12-06', NULL);

--
-- ダンプしたテーブルのインデックス
--

--
-- テーブルのインデックス `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`USER_ID`);

--
-- テーブルのインデックス `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`u_id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- ダンプしたテーブルの AUTO_INCREMENT
--

--
-- テーブルの AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `USER_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- テーブルの AUTO_INCREMENT `user_info`
--
ALTER TABLE `user_info`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
