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
  CONSTRAINT `choose_course.c_id` FOREIGN KEY (`c_id`) REFERENCES `courses` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `choose_course.s_id` FOREIGN KEY (`s_id`) REFERENCES `users` (`uid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choose_course`
--

LOCK TABLES `choose_course` WRITE;
/*!40000 ALTER TABLE `choose_course` DISABLE KEYS */;
INSERT INTO `choose_course` (`c_id`, `s_id`)
VALUES (1, 10), (2, 10), (2, 11), (3, 10), (1, 11), (4, 10), (4, 11), (1, 8), (5, 11);
/*!40000 ALTER TABLE `choose_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_pages`
--

DROP TABLE IF EXISTS `course_pages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_pages` (
  `id`      int(10) NOT NULL AUTO_INCREMENT,
  `c_id`    int(20) NOT NULL,
  `number`  text NOT NULL,
  `title`   text NOT NULL,
  `link`    text,
  `content` text,
  PRIMARY KEY (`id`),
  KEY `course_pages.c_id` (`c_id`),
  CONSTRAINT `course_pages.c_id` FOREIGN KEY (`c_id`) REFERENCES `courses` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_pages`
--

LOCK TABLES `course_pages` WRITE;
/*!40000 ALTER TABLE `course_pages` DISABLE KEYS */;
INSERT INTO `course_pages` (`id`, `c_id`, `number`, `title`, `link`, `content`)
VALUES (1, 1, '1', 'PERSONA', '', 'PERSONA 是 ATLUS 旗下的一系列RPG游戏'), (2, 1, '1.1', 'PERSONA 初版的故事', '', '因为过于古老，找不到视频'),
  (3, 1, '2', 'PERSONA2 罪 与 罚', '', ''),
  (4, 1, '2.1', '罪 ', 'https://www.bilibili.com/video/av19608596 https://www.bilibili.com/video/av19699730 https://www.bilibili.com/video/av20055891', 'PSP 重制版的游戏体验'),
  (5, 1, '2.2', '罚', 'https://www.bilibili.com/video/av248737', '罚 TVCM'), (6, 1, '2.3', '其他', NULL, NULL),
  (7, 1, '2.3.1', '广播剧', 'https://www.bilibili.com/video/av5334082', '官方公认 OOC'),
  (8, 1, '2.3.2', 'OP 欣赏', 'https://www.bilibili.com/video/av10886694', 'OP'),
  (9, 2, '1', 'Introduction, ISA Design', 'http://inst.eecs.berkeley.edu/~cs152/sp09/lectures/L01-Intro.ppt', 'Introduction, ISA Design'),
  (10, 2, '1.1 ', 'Introduction to Simics simulator', 'http://inst.eecs.berkeley.edu/~cs152/sp09/lectures/S01-Simics.ppt https://www.youtube.com/watch?v=zrnrj74ZV4w', 'Introduction to Simics simulator'),
  (11, 3, '1', 'Deemo', NULL, 'DEEMO 是一款音乐游戏'),
  (12, 3, '1.1', '3M 音乐', 'https://www.youtube.com/watch?v=h3WSp7mPvWY', 'Marigold、Myosotis、Magnolia');
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
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` (`id`, `title`, `t_uid`, `img`, `content`) VALUES
  (1, 'PERSONA', 9, 'http://i.imgur.com/NC08W3F.jpg',
   '女神异闻录系列=ペルソナ系列=PERSONA系列，是由ATLUS发售的RPG系列。正式作品标题为“PERSONA”，“女神异闻录”其实是初代的前缀（表示作为同社另一RPG系列《真·女神转生》的衍生作品之意），后来国人大都用其当作系列名称，但需要注意“女神异闻录”实际上并非正式名称。'),
  (2, 'Computer Architecture and Engineering', 9,
   'https://people.eecs.berkeley.edu/~krste/projects/riscv/RISCV-logo128.png',
   'Welcome to the Spring 2009 CS152 web page. The course is intended to provide a foundation for students interested in performance programming, compilers, and operating systems, as well as computer architecture and engineering. Our goal is for you to better understand how software interacts with hardware, and to understand how trends in technology, applications, and economics drive continuing changes in the field. The course will cover the different forms of parallelism found in applications (instruction-level, data-level, thread-level, gate-level) and how these can be exploited with various architectural features. We will also explore techniques that take advantage of the predictability in real applications to accelerate execution or simplify hardware.\n\nWe made substantial changes to the CS152 course curriculum last Spring. Instead of a large FPGA-based design project, students will complete a series of lab assignments using a variety of computer architectures. Dropping the FPGA design project in CS152 allows us to remove the CS150 prerequisite and leaves more time to study more advanced topics in computer architecture, and in particular, the move to multicore parallel processing. Many of the topics we will cover were previously only offered in the CS252 graduate course.'),
  (3, 'Deemo', 9,
   'https://is3-ssl.mzstatic.com/image/thumb/Purple128/v4/09/22/f0/0922f0cc-c064-18c5-8e71-fd876de5e176/mzl.ghfrmnmc.png/643x0w.jpg',
   'Rayark 良心音乐手游'), (4, 'Cytus', 9,
                      'https://is1-ssl.mzstatic.com/image/thumb/Purple71/v4/3b/81/5d/3b815de0-9ba6-ec4b-69b6-ab01b697c798/mzl.huuftyat.png/643x0w.jpg',
                      'Rayark 良心音乐手游'),
  (5, 'PERSONA3 DANCING MOONNIGHT', 10, 'http://www.taghobby.com/wp-content/uploads/2017/12/ogp-4.jpg', '一起跳舞吧');
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
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homework`
--

LOCK TABLES `homework` WRITE;
/*!40000 ALTER TABLE `homework` DISABLE KEYS */;
INSERT INTO `homework` (`id`, `c_id`, `beg_time`, `end_time`, `title`, `content`)
VALUES (1, 1, '2018-08-01', '2018-08-09', '女神异闻录3', '主人公在剧场版、舞台剧版、漫画中有着不同的名字。请问分别是什么？'),
  (2, 2, '2018-08-02', '2018-08-10', 'Microprogramming Bus-Based Architecture',
   'In this problem, we explore microprogramming by writing microcode for the bus-based implementation of the MIPS machine described in Handout #1 (Bus-Based MIPS<br>Implementation), which we have included at the end of this quiz for your reference. '),
  (3, 2, '2018-08-03', '2018-08-12', 'Mem-ALU Pipeline',
   'In this problem, we consider further modifications to the fully bypassed 5-stage MIPS<br>processor pipeline presented in Lecture 3 and Problem Set 1. We will re-order the stages<br>so the Execute (ALU) stage comes after the Memory stage. After this change we will<br>only support register indirect addressing. This change will let us use the contents of<br>memory as one of the operands for arithmetic operations. ');
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
  CONSTRAINT `homework_submit.hw_id` FOREIGN KEY (`hw_id`) REFERENCES `homework` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `homework_submit.s_id` FOREIGN KEY (`s_id`) REFERENCES `users` (`uid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homework_submit`
--

LOCK TABLES `homework_submit` WRITE;
/*!40000 ALTER TABLE `homework_submit` DISABLE KEYS */;
INSERT INTO `homework_submit` (`hw_id`, `s_id`, `content`, `score`)
VALUES (1, 10, '结城理\n汐见朔也\n有理凑', 10), (1, 11, '结城理、汐见朔也、有理凑', NULL);
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
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

LOCK TABLES `resources` WRITE;
/*!40000 ALTER TABLE `resources` DISABLE KEYS */;
INSERT INTO `resources` (`c_id`, `number`, `content`) VALUES (1, '1',
                                                              'http://m10.music.126.net/20180803030852/672b0bc1ad34fc82134d7657fd60ab32/ymusic/726c/c78d/9068/ae6980949564f94ecd4f5c0ad9fb8b88.mp3'),
  (1, '1.1',
   'http://m10.music.126.net/20180803030937/51abfd87c505880e352d97ce8d344242/ymusic/60f5/d0b0/4022/a1436770ea52615ff96197b3d10a67e1.mp3'),
  (2, '1', 'http://inst.eecs.berkeley.edu/~cs152/sp09/handouts/quiz1.pdf'),
  (3, '1', 'http://www.mediafire.com/file/83pwidnq47t13ec/V.K%E5%85%8B+-+Evolution+Era+%28Arr.+Salamanz%29.pdf');
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
  CONSTRAINT `statistics.c_id` FOREIGN KEY (`c_id`) REFERENCES `courses` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `statistics.cp_id` FOREIGN KEY (`cp_id`) REFERENCES `course_pages` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `statistics.s_id` FOREIGN KEY (`s_id`) REFERENCES `users` (`uid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistics`
--

LOCK TABLES `statistics` WRITE;
/*!40000 ALTER TABLE `statistics` DISABLE KEYS */;
INSERT INTO `statistics` (`s_id`, `c_id`, `cp_id`)
VALUES (10, 1, 2), (10, 1, 2), (10, 1, 1), (10, 1, 3), (10, 1, 4), (10, 1, 5), (10, 1, 6), (10, 1, 7), (10, 1, 8),
  (10, 2, 9), (10, 2, 10), (11, 1, 2), (11, 1, 6), (11, 1, 5), (8, 1, 6), (8, 1, 2), (8, 1, 4), (8, 1, 5);
/*!40000 ALTER TABLE `statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `talk`
--

DROP TABLE IF EXISTS `talk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `talk` (
  `id`       int(10) NOT NULL AUTO_INCREMENT,
  `username` text    NOT NULL,
  `content`  text,
  `time`     date    NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talk`
--

LOCK TABLES `talk` WRITE;
/*!40000 ALTER TABLE `talk` DISABLE KEYS */;
INSERT INTO `talk` (`id`, `username`, `content`, `time`)
VALUES (1, 'veronica', '沙发沙发', '2018-08-01'), (2, 'yukari', '诶诶诶', '2018-08-01'), (3, 'yuuki', '有点……像贴吧', '2018-08-01');
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
  `password` text NOT NULL,
  PRIMARY KEY (`uid`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`uid`, `username`, `nickname`, `email`, `password`)
VALUES (8, 'root', 'Administrator', 'veronicadavichi@outlook.com', '84ffffa2fbf0036ceaf6a96769767104'),
  (9, 'veronica', '哈姆子', 'veronicadavichi@outlook.com', 'a244d8de71780aee92f07f93054af3fd'),
  (10, 'yuuki', '結城　理', 'veronicadavichi@outlook.com', '97d46eb07b1cf1d37a3db84c40190cd1'),
  (11, 'yukari', '由佳里', 'veronicadavichi@outlook.com', 'ffddaaa8ba616c5e7db334280bcd7ecc');
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

-- Dump completed on 2018-08-03  4:05:42
