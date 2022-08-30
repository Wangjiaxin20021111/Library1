-- MySQL dump 10.13  Distrib 5.7.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: library
-- ------------------------------------------------------
-- Server version	5.7.38-log

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `ISBN` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `prize` float DEFAULT NULL,
  `writer` varchar(20) DEFAULT NULL,
  `bookState` int(11) DEFAULT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`bookId`, `ISBN`, `name`, `count`, `prize`, `writer`, `bookState`) VALUES (1,'Minhua','Dictionray',2782,30,'XiaoMing',1),(2,'cscv','vsvsdvs',33,120,'dwdw',NULL);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commonstaff`
--

DROP TABLE IF EXISTS `commonstaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commonstaff` (
  `commonStaffId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`commonStaffId`),
  KEY `commonstaff_user__fk` (`userId`),
  CONSTRAINT `commonstaff_user__fk` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commonstaff`
--

LOCK TABLES `commonstaff` WRITE;
/*!40000 ALTER TABLE `commonstaff` DISABLE KEYS */;
INSERT INTO `commonstaff` (`commonStaffId`, `name`, `sex`, `telephone`, `userId`, `state`) VALUES (1,'Jack',1,'18929947109',2,0);
/*!40000 ALTER TABLE `commonstaff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curator`
--

DROP TABLE IF EXISTS `curator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curator` (
  `curatorId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`curatorId`),
  KEY `curator_user__fk` (`userId`),
  CONSTRAINT `curator_user__fk` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curator`
--

LOCK TABLES `curator` WRITE;
/*!40000 ALTER TABLE `curator` DISABLE KEYS */;
INSERT INTO `curator` (`curatorId`, `name`, `sex`, `phone`, `email`, `userId`) VALUES (1,'Jack',1,'123344455554','2504309099@qq.com',2);
/*!40000 ALTER TABLE `curator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identity`
--

DROP TABLE IF EXISTS `identity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identity` (
  `identityNum` int(11) NOT NULL AUTO_INCREMENT,
  `identityName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`identityNum`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identity`
--

LOCK TABLES `identity` WRITE;
/*!40000 ALTER TABLE `identity` DISABLE KEYS */;
INSERT INTO `identity` (`identityNum`, `identityName`) VALUES (1,'CommonStaff'),(2,'Curator');
/*!40000 ALTER TABLE `identity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `identity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `User_userName_uindex` (`userName`),
  KEY `user_identity__fk` (`identity`),
  CONSTRAINT `user_identity__fk` FOREIGN KEY (`identity`) REFERENCES `identity` (`identityNum`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `userName`, `password`, `identity`) VALUES (1,'JIM','20021111aA#',1),(2,'JACK','0607101321a',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-25 11:04:31
