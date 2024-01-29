-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: book-manager
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `book`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `publish` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `category` int DEFAULT NULL,
  `num` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `book_category` (`category`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'巨人的陨落','肯.福莱特','江苏凤凰文艺出版社',2,0),(2,'三体','刘慈欣','南京大学出版社',1,67),(3,'复活','列夫.托尔斯泰','上海译文出版社',1,19),(6,'平凡的世界','路遥','上海文艺出版社',1,88),(15,'白鹿原','陈忠实','南京出版社',1,36),(16,'计算机网络','谢希仁','电子工业出版社',1,49),(17,'霍乱时期的爱情','加西亚·马尔克斯','译林出版社',1,39),(18,'天才在左疯子在右','高铭','北京联合出版公司',1,40),(19,'废都','贾平凹','商务印书馆',1,29),(20,'jQuery','Ryan','中国电力出版社',2,78),(21,'python数据爬虫','张博文','清华大学出版社',2,52),(22,'入门python可视化','variation','电子大学出版社',2,61),(71,'Springboot从入门到实践','筱威','北京大学出版社',2,78),(72,'背影','朱自清','新华书店',3,23),(73,'背影','朱自清','新华书店',3,20),(77,'背影2','朱自清','新华书店',3,2),(79,'背影1111','朱自清121212','新华书店',3,2),(80,'背影1111','朱自清121212','新华书店',3,2),(81,'背影1111','朱自清121212','新华书店',3,2),(82,'背影1111','朱自清121212','新华书店',3,2),(83,'背影1111','朱自清121212','新华书店',3,2);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `borrowTime` datetime NOT NULL,
  `backTime` datetime NOT NULL DEFAULT '1900-01-01 00:00:00',
  `endTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='借书记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES (9,5,1,'2020-08-04 10:00:00','1900-01-01 00:00:00','2020-09-05 00:00:00'),(28,5,19,'2020-08-01 12:00:11','2020-08-10 00:00:00','2020-09-01 00:00:00'),(31,2,20,'2020-08-02 08:30:00','2024-01-20 19:03:22','2020-09-02 00:00:00'),(55,1,21,'2020-08-19 09:54:09','2020-08-01 00:00:00','2020-09-01 00:00:00'),(57,1,17,'2020-08-19 10:00:12','2020-08-01 00:00:00','2020-09-01 00:00:00'),(61,1,16,'2020-08-21 23:12:11','2020-08-01 00:00:00','2020-09-01 00:00:00'),(62,3,72,'2024-01-20 22:41:19','1900-01-01 00:00:00','2024-02-20 22:41:19'),(63,3,72,'2024-01-20 22:42:26','1900-01-01 00:00:00','2024-02-20 22:42:26'),(64,3,2,'2024-01-20 22:42:44','2024-01-20 22:49:03','2024-02-20 22:42:44'),(65,3,2,'2024-01-20 22:43:32','2024-01-23 16:10:31','2024-02-20 22:43:32'),(66,4,3,'2024-01-22 21:11:43','2024-01-23 16:11:27','2024-02-22 21:11:43');
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'小说'),(2,'专业书籍'),(3,'诗歌2');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `page` varchar(100) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `status` int NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (2,3,'hi','2','fdgdsfsdfsfds','321443241',1,'2022-02-01 12:00:00'),(3,3,'uewiyuy','dddddd','mkdljsfjas','23793',0,'2024-01-20 23:06:29'),(4,3,'1212','12121','额212121','123456554',0,'2024-01-20 23:08:54'),(5,3,'32323','3232','3232','3232',0,'2024-01-20 23:10:04'),(6,3,'23','232','23','123456554',0,'2024-01-22 21:07:12'),(7,4,'反馈','1','啦啦啦不满意！','3232',0,'2024-01-22 21:12:33');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(2000) NOT NULL,
  `time` datetime NOT NULL,
  `user` int NOT NULL,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'新店开业大酬宾','2024-01-19 00:00:00',1,'公告1'),(2,'测试内容','2024-01-20 04:54:11',1,'测试标题'),(3,'测试内容','2024-01-20 16:54:55',1,'测试标题');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `admin` int NOT NULL,
  `availableNum` int NOT NULL,
  `phone` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','96e79218965eb72c92a549dd5a330112',1,10,'123'),(2,'admin1','96e79218965eb72c92a549dd5a330112',1,10,'123'),(3,'user1','96e79218965eb72c92a549dd5a330112',0,4,''),(4,'user2','c4ca4238a0b923820dcc509a6f75849b',0,10,'123456'),(5,'admin4','96e79218965eb72c92a549dd5a330112',0,10,''),(23,'hello','96e79218965eb72c92a549dd5a330112',0,9,'123456554'),(24,'hello1','6512bd43d9caa6e02c990b0a82652dca',0,10,'123456554');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'book-manager'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-23 17:55:19
