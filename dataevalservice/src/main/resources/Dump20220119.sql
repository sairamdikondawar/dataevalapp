CREATE DATABASE  IF NOT EXISTS `evaldb` /*!40100 DEFAULT CHARACTER SET utf32 */;
USE `evaldb`;
-- MySQL dump 10.13  Distrib 5.7.31, for macos10.14 (x86_64)
--
-- Host: localhost    Database: evaldb
-- ------------------------------------------------------
-- Server version	5.7.31

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
-- Table structure for table `FLOW_CONFIG`
--

DROP TABLE IF EXISTS `FLOW_CONFIG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FLOW_CONFIG` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FLOW_NAME` varchar(255) NOT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `FLOW_NAME_UNIQUE` (`FLOW_NAME`),
  UNIQUE KEY `UNIQUE_PER_ROLE` (`FLOW_NAME`,`ROLE_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FLOW_CONFIG`
--

LOCK TABLES `FLOW_CONFIG` WRITE;
/*!40000 ALTER TABLE `FLOW_CONFIG` DISABLE KEYS */;
INSERT INTO `FLOW_CONFIG` VALUES (1,'Test',NULL,NULL,1),(10,'Test13',NULL,NULL,2),(11,'Update Role1',NULL,NULL,2),(13,'Test12s',NULL,NULL,1),(14,'Test12s1',NULL,NULL,2),(15,'Test12snull',NULL,NULL,7),(16,'Test12s4',NULL,NULL,7),(17,'Test12s41',NULL,NULL,2),(18,'Test12s411',NULL,NULL,2),(20,'Test12s4111',NULL,NULL,2),(21,'Test12s4112',NULL,NULL,2),(23,'Test12s4113',NULL,NULL,2),(24,'Test12s4114',NULL,NULL,2),(26,'Test12s4115',NULL,NULL,2),(28,'Test12s4116',NULL,NULL,2),(30,'Test12s4117',NULL,NULL,2),(31,'Test12s4118',NULL,NULL,2),(32,'Test12s4119',NULL,NULL,2),(33,'Test12s4120',NULL,NULL,2),(34,'Test12s4121',NULL,NULL,2),(35,'Test12s4122',NULL,NULL,2),(36,'Test12s4123',NULL,NULL,2),(38,'Test12s4124',NULL,NULL,2),(39,'Test12s4125',NULL,NULL,2),(41,'Test12s4126',NULL,NULL,2),(42,'Test12s4127',NULL,NULL,2),(43,'Test12s4128',NULL,NULL,2),(44,'Test12s4129',NULL,NULL,2),(46,'Test12s4130',NULL,NULL,2),(48,'Test12s4131',NULL,NULL,2),(50,'Save Test',NULL,NULL,1),(51,'Test New',NULL,NULL,2),(52,'Save11',NULL,NULL,1),(53,'Save12',NULL,NULL,2);
/*!40000 ALTER TABLE `FLOW_CONFIG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FLOW_PAGES`
--

DROP TABLE IF EXISTS `FLOW_PAGES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FLOW_PAGES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PAGE_NAME` varchar(45) NOT NULL,
  `SEQUENCE` int(11) NOT NULL,
  `FLOW_ID` int(11) DEFAULT NULL,
  `LAYOUT_COLUMNS` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `FLOW_SEQUENCE` (`SEQUENCE`,`FLOW_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FLOW_PAGES`
--

LOCK TABLES `FLOW_PAGES` WRITE;
/*!40000 ALTER TABLE `FLOW_PAGES` DISABLE KEYS */;
INSERT INTO `FLOW_PAGES` VALUES (1,'FIRST',1,NULL,1),(2,'SECOND',2,NULL,1);
/*!40000 ALTER TABLE `FLOW_PAGES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PAGE_SECTIONS`
--

DROP TABLE IF EXISTS `PAGE_SECTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PAGE_SECTIONS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `STATUS` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAGE_SECTIONS`
--

LOCK TABLES `PAGE_SECTIONS` WRITE;
/*!40000 ALTER TABLE `PAGE_SECTIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PAGE_SECTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QUESTIONS`
--

DROP TABLE IF EXISTS `QUESTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QUESTIONS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUESTION_NAME` varchar(2000) NOT NULL,
  `QUESTION_TYPE` varchar(45) NOT NULL,
  `PAGE_ID` int(11) DEFAULT NULL,
  `FLOW_ID` int(11) DEFAULT NULL,
  `STATUS` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QUESTIONS`
--

LOCK TABLES `QUESTIONS` WRITE;
/*!40000 ALTER TABLE `QUESTIONS` DISABLE KEYS */;
INSERT INTO `QUESTIONS` VALUES (1,'Test1','INPUT_TEXT',NULL,NULL,'ACTIVE'),(3,'What is your name ?','INPUT_TEXT',NULL,NULL,'ACTIVE'),(4,'What is Date of birth ?-1','INPUT_TEXT',NULL,NULL,'ACTIVE'),(5,'Test Question ?','INPUT_TEXT',NULL,NULL,'ACTIVE'),(6,'Test111 ?','RADIO',NULL,NULL,'ACTIVE');
/*!40000 ALTER TABLE `QUESTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QUESTION_TYPES`
--

DROP TABLE IF EXISTS `QUESTION_TYPES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QUESTION_TYPES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUESTION_TYPE` varchar(45) NOT NULL,
  `Description` varchar(45) DEFAULT NULL,
  `STATUS` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QUESTION_TYPES`
--

LOCK TABLES `QUESTION_TYPES` WRITE;
/*!40000 ALTER TABLE `QUESTION_TYPES` DISABLE KEYS */;
INSERT INTO `QUESTION_TYPES` VALUES (1,'INPUT_TEXT','When User Wants to Enter any Input','ACT'),(2,'RADIO','RADIO Button','ACT');
/*!40000 ALTER TABLE `QUESTION_TYPES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLES`
--

DROP TABLE IF EXISTS `ROLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROLES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_DESC` varchar(45) DEFAULT NULL,
  `ROLE_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLES`
--

LOCK TABLES `ROLES` WRITE;
/*!40000 ALTER TABLE `ROLES` DISABLE KEYS */;
INSERT INTO `ROLES` VALUES (1,'ADMIN','ADMIN'),(2,'PATIENT','PATIENT'),(4,'NURSE','NURSE'),(7,'DOCTOR','DOCTOR');
/*!40000 ALTER TABLE `ROLES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `STATUS` varchar(45) NOT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `ROLE_KEY` FOREIGN KEY (`ID`) REFERENCES `ROLES` (`ID`) ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-19 13:17:42
