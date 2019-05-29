/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 25/02/2019 22:17:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for migrations
-- ----------------------------
DROP TABLE IF EXISTS `migrations`;
CREATE TABLE `migrations` (
  `id` int(11) NOT NULL,
  `migration` varchar(255) NOT NULL,
  `batch` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of migrations
-- ----------------------------
BEGIN;
INSERT INTO `migrations` VALUES (100, '2014_10_10_000000_create_roles_table', 1);
INSERT INTO `migrations` VALUES (101, '2014_10_12_000000_create_users_table', 1);
INSERT INTO `migrations` VALUES (102, '2014_10_12_100000_create_password_resets_table', 1);
INSERT INTO `migrations` VALUES (103, '2018_12_17_163212_create_products_table', 1);
COMMIT;

-- ----------------------------
-- Table structure for password_resets
-- ----------------------------
DROP TABLE IF EXISTS `password_resets`;
CREATE TABLE `password_resets` (
  `email` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  KEY `password_resets_email_index` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of products
-- ----------------------------
BEGIN;
INSERT INTO `products` VALUES ('e406c8d5-d18c-47a3-a78a-40aab7ff2d0a', 'cafe latte', 38000, 10, '2018-12-18 03:03:55', '2018-12-18 03:03:55');
INSERT INTO `products` VALUES ('f5d1b462-341b-426d-b5b7-6f761dfc71d3', 'cafe latte', 38000, 10, '2018-12-18 03:03:37', '2018-12-18 03:03:37');
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES ('50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'customer', '2018-12-18 02:40:03', '2018-12-18 02:40:03');
INSERT INTO `roles` VALUES ('62deede7-9b5d-4b39-a42e-2850717af3e5', 'admin', '2018-12-18 02:40:03', '2018-12-18 02:40:03');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
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
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `users_role_id_foreign` (`role_id`),
  CONSTRAINT `users_role_id_foreign` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('2b1de490-bd92-40b1-880f-1b0b3abf13f9', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'testt', 'test', '$2y$10$Lql5WF294i6h6DuzbruI7.QeTc/8iEqRFXQrPMlDgLJPSVnOHP7zW', 'as@gmail.com', '0812', 'male', NULL, '2018-12-20 00:24:18', '2018-12-20 00:24:18');
INSERT INTO `users` VALUES ('357036bf-60d3-423a-b0f4-018b47198b7a', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'test', 'test', '$2y$10$VYvuq/nbm/Zo2zlosB1EtuRXSrCZFRwRLFRTrkIUk7EjN2017XNLe', 'komala@gmail.com', '08121954', 'male', NULL, '2018-12-20 00:18:39', '2018-12-20 00:18:39');
INSERT INTO `users` VALUES ('49362587-76f4-4345-96cb-040c69fc954b', '62deede7-9b5d-4b39-a42e-2850717af3e5', 'valentinoekaputra', 'Valentino Ekaputra', '$2y$10$fPMg.Di0t4xlK.MRUCilU.RLEOLcww3zvbV9X3SrjbSjZRqSOhQme', NULL, NULL, NULL, NULL, '2018-12-18 02:40:03', '2018-12-18 02:40:03');
INSERT INTO `users` VALUES ('55143122-fee8-4a04-9f80-76283df4f816', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'komalasurya96', 'komala surya', '$2y$10$RJ.SaSwedl1HBCzUl2ILOezuBKaObyD7UvrPclNb8DoVFnGT9ftnC', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2018-12-18 02:48:11', '2018-12-18 02:48:11');
INSERT INTO `users` VALUES ('5bbe645e-db73-41d0-a3d5-d9411e566d65', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'komalasurya95', 'komala surya', '$2y$10$oozIyEiw.giFJFCnRGGlguiVuS4gPRNU/3IoQ9IFsfsgMkPEqQygy', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2018-12-18 02:46:18', '2018-12-18 02:46:18');
INSERT INTO `users` VALUES ('763a25fa-b2fd-4eb8-a6db-32039e6683e0', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'faf', 'fafa', '$2y$10$6JqZdkcjgL0mnisxY/evZuU5DovDTHRwAUB2nFNk2afiEyjDI0sJa', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2018-12-18 05:34:01', '2018-12-18 05:34:01');
INSERT INTO `users` VALUES ('9a32c604-35ff-4a95-8549-e7a27e386542', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'testing', 'testing', '$2y$10$v..J7EWfnDEXjmgRLYG98e4GFGtmTHszDwDTUAXi5lc83xHSauPrq', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2018-12-20 00:04:53', '2018-12-20 00:04:53');
INSERT INTO `users` VALUES ('cfb7ff2c-eda9-4a81-b0fb-f885c95af8ad', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'komalasurya967', 'komala surya', '$2y$10$rZ5Hc/zDmqc2gNUNltSPYenuSO90OVS2TFILw8a7UzX0cLSti5S3m', 'komala.surya.w@gmail.com', '081219545352', 'male', NULL, '2018-12-18 03:07:13', '2018-12-18 03:07:13');
INSERT INTO `users` VALUES ('e3e3c309-b4c2-49ea-8faf-84bee751542b', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'komalasurya', 'Komala Surya', '$2y$10$nVKXJKTS1Vq7tZWDobdC0OFHDjkiPophzQ.Dgegq.T3suy6jGRxbq', NULL, NULL, NULL, NULL, '2018-12-18 02:40:03', '2018-12-18 02:40:03');
INSERT INTO `users` VALUES ('f2974011-0351-4c38-9552-ef55a2430d8a', '50c3f3e1-8fcc-4bbd-acd3-f62040b4e8d4', 'fafa', 'fafa', '$2y$10$aKMlkEdsWwfJqtXfJ.FOw.L/QRUKCPUG81xZdHz1c5S4jqDDsuiL6', 'mfaizudd@gmail.com', '085726755758', 'male', NULL, '2018-12-18 05:38:06', '2018-12-18 05:38:06');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
