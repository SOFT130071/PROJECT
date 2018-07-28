-- MySQL dump 10.13  Distrib 5.7.12, for osx10.11 (x86_64)
--
-- Host: 127.0.0.1    Database: mooc
-- ------------------------------------------------------
-- Server version	5.7.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `choose_course`
--

DROP TABLE IF EXISTS `choose_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `choose_course` (
  `c_id` int(20) NOT NULL,
  `s_id` int(10) NOT NULL,
  KEY `choose_course.c_id` (`c_id`),
  KEY `choose_course.s_id` (`s_id`),
  CONSTRAINT `choose_course.c_id` FOREIGN KEY (`c_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `choose_course.s_id` FOREIGN KEY (`s_id`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choose_course`
--

LOCK TABLES `choose_course` WRITE;
/*!40000 ALTER TABLE `choose_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `choose_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_pages`
--

DROP TABLE IF EXISTS `course_pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_pages` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `c_id` int(20) NOT NULL,
  `number` text NOT NULL,
  `title` text NOT NULL,
  `link` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_pages.c_id` (`c_id`),
  CONSTRAINT `course_pages.c_id` FOREIGN KEY (`c_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_pages`
--

LOCK TABLES `course_pages` WRITE;
/*!40000 ALTER TABLE `course_pages` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_pages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` text,
  `t_uid` int(10) NOT NULL,
  `img` text NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `courses.t_uid` (`t_uid`),
  CONSTRAINT `courses.t_uid` FOREIGN KEY (`t_uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homework`
--

DROP TABLE IF EXISTS `homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `homework` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `c_id` int(20) NOT NULL,
  `beg_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `title` text NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `homework.c_id` (`c_id`),
  CONSTRAINT `homework.c_id` FOREIGN KEY (`c_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homework`
--

LOCK TABLES `homework` WRITE;
/*!40000 ALTER TABLE `homework` DISABLE KEYS */;
/*!40000 ALTER TABLE `homework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homework_submit`
--

DROP TABLE IF EXISTS `homework_submit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `homework_submit` (
  `hw_id` int(10) NOT NULL,
  `s_id` int(10) NOT NULL,
  `content` text,
  `score` int(5) DEFAULT NULL,
  KEY `homework_submit.hw_id` (`hw_id`),
  KEY `homework_submit.s_id` (`s_id`),
  CONSTRAINT `homework_submit.hw_id` FOREIGN KEY (`hw_id`) REFERENCES `homework` (`id`),
  CONSTRAINT `homework_submit.s_id` FOREIGN KEY (`s_id`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homework_submit`
--

LOCK TABLES `homework_submit` WRITE;
/*!40000 ALTER TABLE `homework_submit` DISABLE KEYS */;
/*!40000 ALTER TABLE `homework_submit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resources` (
  `c_id` int(20) NOT NULL,
  `number` text NOT NULL,
  `content` text NOT NULL,
  KEY `resources.c_id` (`c_id`),
  CONSTRAINT `resources.c_id` FOREIGN KEY (`c_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

LOCK TABLES `resources` WRITE;
/*!40000 ALTER TABLE `resources` DISABLE KEYS */;
/*!40000 ALTER TABLE `resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistics`
--

DROP TABLE IF EXISTS `statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statistics` (
  `s_id` int(10) NOT NULL,
  `c_id` int(20) NOT NULL,
  `cp_id` int(10) NOT NULL,
  KEY `statistics.s_id` (`s_id`),
  KEY `statistics.c_id` (`c_id`),
  KEY `statistics.cp_id` (`cp_id`),
  CONSTRAINT `statistics.c_id` FOREIGN KEY (`c_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `statistics.cp_id` FOREIGN KEY (`cp_id`) REFERENCES `course_pages` (`id`),
  CONSTRAINT `statistics.s_id` FOREIGN KEY (`s_id`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistics`
--

LOCK TABLES `statistics` WRITE;
/*!40000 ALTER TABLE `statistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `talk`
--

DROP TABLE IF EXISTS `talk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `talk` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `content` text,
  `time` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `talk.uid` (`uid`),
  CONSTRAINT `talk.uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talk`
--

LOCK TABLES `talk` WRITE;
/*!40000 ALTER TABLE `talk` DISABLE KEYS */;
/*!40000 ALTER TABLE `talk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` text NOT NULL,
  `nickname` text NOT NULL,
  `email` text,
  `logged` enum('0','1') NOT NULL DEFAULT '0',
  `password` text NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`uid`, `username`, `nickname`, `email`, `logged`, `password`) VALUES (1,'admin','管理员大佬',NULL,'0','password');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-25 14:36:18
