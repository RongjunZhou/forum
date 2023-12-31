-- MySQL dump 10.13  Distrib 8.1.0, for macos13 (arm64)
--
-- Host: 127.0.0.1    Database: forum
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `content` varchar(1023) DEFAULT NULL,
  `like_count` int DEFAULT NULL,
  `father_id` int DEFAULT NULL,
  `time` datetime NOT NULL DEFAULT (now()),
  PRIMARY KEY (`id`),
  KEY `comment_father_id_index` (`father_id`),
  KEY `comment_post_id_father_id_index` (`post_id`,`father_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2085380100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (-1240600575,1,2,'你好',0,2085380098,'2023-11-02 19:34:07'),(1,1,1,'1',1,1,'2023-11-01 23:27:14'),(2,1,1,'1',10,1,'2023-11-01 23:27:40'),(2085380098,1,2,'你好',11,NULL,'2023-11-02 19:30:38'),(2085380099,1,2,'你好ya',0,2085380098,'2023-11-02 19:34:52');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `key` varchar(127) NOT NULL,
  `content` longblob NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `post`
--

SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `post` AS SELECT 
 1 AS `id`,
 1 AS `user_id`,
 1 AS `plate_id`,
 1 AS `title`,
 1 AS `content`,
 1 AS `resources`,
 1 AS `create_time`,
 1 AS `comment_count`,
 1 AS `update_time`,
 1 AS `view_count`,
 1 AS `like_count`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `post_info`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `plate_id` int NOT NULL,
  `title` varchar(31) NOT NULL,
  `content` text NOT NULL,
  `resources` json DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT (now()),
  PRIMARY KEY (`id`),
  KEY `post_info_plate_id_index` (`plate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_info`
--

LOCK TABLES `post_info` WRITE;
/*!40000 ALTER TABLE `post_info` DISABLE KEYS */;
INSERT INTO `post_info` VALUES (2,1,1,'1','1',NULL,'2023-11-02 19:22:34');
/*!40000 ALTER TABLE `post_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `praise`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `praise` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `post_id` int NOT NULL,
  `time` datetime NOT NULL DEFAULT (now()),
  PRIMARY KEY (`id`),
  KEY `like_user_id_post_id_time_index` (`user_id`,`post_id`,`time`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `praise`
--

LOCK TABLES `praise` WRITE;
/*!40000 ALTER TABLE `praise` DISABLE KEYS */;
/*!40000 ALTER TABLE `praise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(15) NOT NULL DEFAULT '未命名',
  `password` varchar(127) NOT NULL,
  `role` int DEFAULT NULL,
  `sex` tinyint NOT NULL DEFAULT '1',
  `avatar` varchar(127) NOT NULL DEFAULT 'https://i1.hdslb.com/bfs/face/4a66fc917f552b042441040be35670902a84376b.jpg@240w_240h_1c_1s_!web-avatar-nav.webp',
  `sign` varchar(63) DEFAULT NULL,
  `email` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_info_username_uindex` (`username`),
  UNIQUE KEY `user_info_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'123','MTIz',1,1,'https://i1.hdslb.com/bfs/face/4a66fc917f552b042441040be35670902a84376b.jpg@240w_240h_1c_1s_!web-avatar-nav.webp',NULL,NULL);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view_history`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `view_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `user_id` int NOT NULL,
  `time` datetime NOT NULL DEFAULT (now()),
  PRIMARY KEY (`id`),
  KEY `view_history_user_id_post_id_time_index` (`user_id`,`post_id`,`time`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view_history`
--

LOCK TABLES `view_history` WRITE;
/*!40000 ALTER TABLE `view_history` DISABLE KEYS */;
INSERT INTO `view_history` VALUES (1,2,1,'2023-11-02 19:33:29'),(2,2,1,'2023-11-02 19:34:11'),(3,2,1,'2023-11-02 19:34:55'),(4,2,1,'2023-11-02 19:47:30'),(5,2,1,'2023-11-02 19:47:42'),(6,2,1,'2023-11-02 19:48:00');
/*!40000 ALTER TABLE `view_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `post`
--

/*!50001 DROP VIEW IF EXISTS `post`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `post` AS select `post_info`.`id` AS `id`,`post_info`.`user_id` AS `user_id`,`post_info`.`plate_id` AS `plate_id`,`post_info`.`title` AS `title`,`post_info`.`content` AS `content`,`post_info`.`resources` AS `resources`,`post_info`.`create_time` AS `create_time`,(select count(0) from `comment` where (`comment`.`post_id` = `post_info`.`id`)) AS `comment_count`,max(`c`.`time`) AS `update_time`,(select count(0) from `view_history` where (`view_history`.`post_id` = `post_info`.`id`)) AS `view_count`,(select count(0) from `praise` where (`praise`.`post_id` = `post_info`.`id`)) AS `like_count` from (`post_info` left join `comment` `c` on((`c`.`post_id` = `post_info`.`id`))) group by `post_info`.`id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-08 13:13:32
