-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 08, 2019 at 08:27 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shop`
--

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(11) NOT NULL,
  `migration` varchar(255) NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(100, '2014_10_10_000000_create_roles_table', 1),
(101, '2014_10_12_000000_create_users_table', 1),
(102, '2014_10_12_100000_create_password_resets_table', 1),
(103, '2018_12_17_163212_create_products_table', 1);

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `stock`, `created_at`, `updated_at`) VALUES
('e406c8d5-d18c-47a3-a78a-40aab7ff2d0a', 'cafe latte', 38000, 10, '2018-12-18 03:03:55', '2018-12-18 03:03:55'),
('f5d1b462-341b-426d-b5b7-6f761dfc71d3', 'cafe latte', 38000, 10, '2018-12-18 03:03:37', '2018-12-18 03:03:37');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`, `created_at`, `updated_at`) VALUES
('50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'customer', '2018-12-18 02:40:03', '2018-12-18 02:40:03'),
('62deede7-9b5d-4b39-a42e-2850717af3e5', 'admin', '2018-12-18 02:40:03', '2018-12-18 02:40:03');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` char(36) NOT NULL,
  `role_id` char(36) NOT NULL,
  `username` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `remember_token` varchar(100) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `role_id`, `username`, `name`, `password`, `email`, `phone`, `gender`, `remember_token`, `created_at`, `updated_at`) VALUES
('2b1de490-bd92-40b1-880f-1b0b3abf13f9', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'testt', 'test', '$2y$10$Lql5WF294i6h6DuzbruI7.QeTc/8iEqRFXQrPMlDgLJPSVnOHP7zW', 'as@gmail.com', '0812', 'male', NULL, '2018-12-20 00:24:18', '2018-12-20 00:24:18'),
('2f546690-1e18-4d85-ba39-edd501f2175f', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'komalasurya95', 'komala surya', '$2y$10$.G01LXRDHx.SOhxqeDmIzezBmsC8K1bFHrAfI2LIDt4Dd8VoEtGyW', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2019-04-06 09:53:42', '2019-04-06 09:53:42'),
('357036bf-60d3-423a-b0f4-018b47198b7a', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'test', 'test', '$2y$10$VYvuq/nbm/Zo2zlosB1EtuRXSrCZFRwRLFRTrkIUk7EjN2017XNLe', 'komala@gmail.com', '08121954', 'male', NULL, '2018-12-20 00:18:39', '2018-12-20 00:18:39'),
('49362587-76f4-4345-96cb-040c69fc954b', '62deede7-9b5d-4b39-a42e-2850717af3e5', 'valentinoekaputra', 'Valentino Ekaputra', '$2y$10$fPMg.Di0t4xlK.MRUCilU.RLEOLcww3zvbV9X3SrjbSjZRqSOhQme', NULL, NULL, NULL, NULL, '2018-12-18 02:40:03', '2018-12-18 02:40:03'),
('55143122-fee8-4a04-9f80-76283df4f816', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'komalasurya96', 'komala surya', '$2y$10$RJ.SaSwedl1HBCzUl2ILOezuBKaObyD7UvrPclNb8DoVFnGT9ftnC', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2018-12-18 02:48:11', '2018-12-18 02:48:11'),
('5bbe645e-db73-41d0-a3d5-d9411e566d65', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'komalasurya95', 'komala surya', '$2y$10$oozIyEiw.giFJFCnRGGlguiVuS4gPRNU/3IoQ9IFsfsgMkPEqQygy', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2018-12-18 02:46:18', '2018-12-18 02:46:18'),
('7292ba5a-c74b-434f-98b9-83f54f897758', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'komalasurya95', 'komala surya', '$2y$10$FhbXThggHvwCFOqbp1ssPOCUzaXzIQWjSDhvc278V8xsMASwoxnf.', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2019-04-06 09:53:51', '2019-04-06 09:53:51'),
('763a25fa-b2fd-4eb8-a6db-32039e6683e0', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'faf', 'fafa', '$2y$10$6JqZdkcjgL0mnisxY/evZuU5DovDTHRwAUB2nFNk2afiEyjDI0sJa', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2018-12-18 05:34:01', '2018-12-18 05:34:01'),
('9a32c604-35ff-4a95-8549-e7a27e386542', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'testing', 'testing', '$2y$10$v..J7EWfnDEXjmgRLYG98e4GFGtmTHszDwDTUAXi5lc83xHSauPrq', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2018-12-20 00:04:53', '2018-12-20 00:04:53'),
('a65feb90-aec4-42be-87a1-35609eaedde6', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'komalasurya95', 'komala surya', '$2y$10$.9Ji0xy6qIq9dNkgXkDINuqp3ONO9jtd1XPHGCdByESNK.OcKU2Jy', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2019-04-06 09:52:50', '2019-04-06 09:52:50'),
('b8097b8f-f73f-4aaf-add0-7e69939fc865', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'komalasurya95', 'komala surya', '$2y$10$Qnz6MRhWrQQ/QgxjYepU/Om2RgSqsjY4/TYLlAZcx6Dw/jO1s.HLW', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2019-04-06 09:52:49', '2019-04-06 09:52:49'),
('cfb7ff2c-eda9-4a81-b0fb-f885c95af8ad', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'komalasurya967', 'komala surya', '$2y$10$rZ5Hc/zDmqc2gNUNltSPYenuSO90OVS2TFILw8a7UzX0cLSti5S3m', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2018-12-18 03:07:13', '2018-12-18 03:07:13'),
('e3e3c309-b4c2-49ea-8faf-84bee751542b', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'komalasurya', 'Komala Surya', '$2y$10$nVKXJKTS1Vq7tZWDobdC0OFHDjkiPophzQ.Dgegq.T3suy6jGRxbq', NULL, NULL, NULL, NULL, '2018-12-18 02:40:03', '2018-12-18 02:40:03'),
('f2974011-0351-4c38-9552-ef55a2430d8a', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'fafa', 'fafa', '$2y$10$aKMlkEdsWwfJqtXfJ.FOw.L/QRUKCPUG81xZdHz1c5S4jqDDsuiL6', 'mfaizudd@gmail.com', '085726755758', 'male', NULL, '2018-12-18 05:38:06', '2018-12-18 05:38:06');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`) USING BTREE;

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `users_role_id_foreign` (`role_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_role_id_foreign` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
