CREATE DATABASE  IF NOT EXISTS `evaldb` /*!40100 DEFAULT CHARACTER SET utf32 */;
USE `evaldb`;
-- MySQL dump 10.13  Distrib 5.7.31, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: evaldb
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
  `STATUS` varchar(45) NOT NULL DEFAULT 'Inactive',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `FLOW_NAME_UNIQUE` (`FLOW_NAME`),
  UNIQUE KEY `UNIQUE_PER_ROLE` (`FLOW_NAME`,`ROLE_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FLOW_CONFIG`
--

LOCK TABLES `FLOW_CONFIG` WRITE;
/*!40000 ALTER TABLE `FLOW_CONFIG` DISABLE KEYS */;
INSERT INTO `FLOW_CONFIG` VALUES (54,'Nurse',NULL,NULL,3,'Inactive'),(55,'Patient',NULL,NULL,2,'Inactive'),(56,'Doctor',NULL,NULL,4,'Inactive'),(57,'Biller',NULL,NULL,5,'Inactive');
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
  `LAYOUT_COLUMNS` int(11) DEFAULT '1',
  `STATUS` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `FLOW_SEQUENCE` (`SEQUENCE`,`FLOW_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FLOW_PAGES`
--

LOCK TABLES `FLOW_PAGES` WRITE;
/*!40000 ALTER TABLE `FLOW_PAGES` DISABLE KEYS */;
INSERT INTO `FLOW_PAGES` VALUES (1,'First',1,NULL,1,'Active'),(2,'Second',2,NULL,1,'Active'),(4,'Step 3',1,NULL,NULL,'Active');
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
  `PAGE_ID` int(11) DEFAULT NULL,
  `LAYOUT` varchar(45) DEFAULT '1',
  `SEQUENCE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAGE_SECTIONS`
--

LOCK TABLES `PAGE_SECTIONS` WRITE;
/*!40000 ALTER TABLE `PAGE_SECTIONS` DISABLE KEYS */;
INSERT INTO `PAGE_SECTIONS` VALUES (1,'Alternate Contact','Test Secton1','Active',1,'2',5),(2,'Resource Assesment To Evaluate Self-Care','Resource Assesment To Evaluate Self-Care','Active',2,'2',2),(4,'Section 3',NULL,'Active',2,NULL,4),(5,'Section 3 ',NULL,'Active',4,NULL,1);
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
  `SECTION_ID` int(11) DEFAULT NULL,
  `REQUIRED` tinyint(4) NOT NULL DEFAULT '0',
  `FLOW_ID` int(11) DEFAULT NULL,
  `STATUS` varchar(45) NOT NULL,
  `SEQUENCE` int(11) DEFAULT '0',
  `USER_TYPES` varchar(500) NOT NULL DEFAULT 'PATIENT',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QUESTIONS`
--

LOCK TABLES `QUESTIONS` WRITE;
/*!40000 ALTER TABLE `QUESTIONS` DISABLE KEYS */;
INSERT INTO `QUESTIONS` VALUES (1,'My Care Giver Name :','Text',1,0,NULL,'Active',40,'PATIENT,NURSE'),(3,'My Care Giver Phone:','Text',1,0,NULL,'Active',2,'PATIENT,NURSE'),(4,'Emergency Care Contact Name:','Text',1,0,NULL,'Active',3,'PATIENT'),(5,'POF Finance Contact:','Text',1,0,NULL,'Inactive',4,'PATIENT'),(6,'POA Finance Contact Phone:','Text',1,0,NULL,'Active',5,'PATIENT'),(7,'POA Health Care Contact:','Text',4,0,NULL,'Active',6,'PATIENT'),(8,'POA Health Care Contact Phone','Text',1,0,NULL,'Inactive',7,'PATIENT'),(9,'I frequently forgot to take medcine:','Radio',2,0,NULL,'Active',8,'PATIENT'),(10,'I frequently miss my doctor appoinment:','Radio',2,0,NULL,'Active',9,'PATIENT'),(12,'I always choose to go to ER when I am sick:','Radio',2,0,NULL,'Active',10,'PATIENT'),(13,'Sometimes i go without food to eat at my home:','Radio',2,0,NULL,'Active',11,'PATIENT'),(14,'Sometimes my power or Utilities shoutoff:','Radio',2,0,NULL,'Active',12,'PATIENT'),(15,'Address/Contact given:','Text',2,0,NULL,'Active',13,'PATIENT'),(16,'Refferral Assistance/Education Done','Text',2,0,NULL,'Active',14,'DOCTOR');
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
INSERT INTO `QUESTION_TYPES` VALUES (1,'Text','When User Wants to Enter any Input','Active'),(2,'Radio','To Show Yes Or No','Active'),(3,'Date','To Show Date Component in UI','Inactive');
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
  `STATUS` varchar(45) NOT NULL DEFAULT 'Active',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLES`
--

LOCK TABLES `ROLES` WRITE;
/*!40000 ALTER TABLE `ROLES` DISABLE KEYS */;
INSERT INTO `ROLES` VALUES (1,'ADMIN','ADMIN','Inactive'),(2,'PATIENT','PATIENT','Active'),(3,'NURSE','NURSE','Active'),(4,'DOCTOR','DOCTOR','Active'),(5,'Biller','BILLER','Active');
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
  `PASSWORD` varchar(435) NOT NULL DEFAULT '$2a$10$Iem0Lf7huYWkYtbI8F/NiO1rpGQRZzDKgdBDsxzdK9TkAKiDNPgSm',
  `STATUS` varchar(45) NOT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `ROLE_KEY` FOREIGN KEY (`ID`) REFERENCES `ROLES` (`ID`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (1,'admin','$2a$10$tnHNl//LcKS4MEyECc0Tr.nVmBB7S.jKde7QnymLKXsCGhvbqy81.','Active',1),(2,'Nurse','$2a$10$tnHNl//LcKS4MEyECc0Tr.nVmBB7S.jKde7QnymLKXsCGhvbqy81.','Active',3),(3,'Doctor','$2a$10$Iem0Lf7huYWkYtbI8F/NiO1rpGQRZzDKgdBDsxzdK9TkAKiDNPgSm','Active',4),(4,'Biller','$2a$10$Iem0Lf7huYWkYtbI8F/NiO1rpGQRZzDKgdBDsxzdK9TkAKiDNPgSm','Active',5),(5,'patient','$2a$10$Iem0Lf7huYWkYtbI8F/NiO1rpGQRZzDKgdBDsxzdK9TkAKiDNPgSm','Active',2);
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_FORMS`
--

DROP TABLE IF EXISTS `USER_FORMS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_FORMS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `CREATION_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_FORMS`
--

LOCK TABLES `USER_FORMS` WRITE;
/*!40000 ALTER TABLE `USER_FORMS` DISABLE KEYS */;
INSERT INTO `USER_FORMS` VALUES (1,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(2,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(3,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(4,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(5,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(6,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(7,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(8,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(9,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(10,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(11,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(12,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(13,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(14,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(15,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(16,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(17,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(18,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(19,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(20,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(21,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(22,1,'2022-01-24 22:26:08','admin','2022-01-24 22:26:21','admin'),(23,1,NULL,'admin',NULL,'admin'),(24,1,NULL,'admin',NULL,'admin'),(25,1,NULL,'admin',NULL,'admin'),(26,1,NULL,'admin',NULL,'admin'),(27,1,'2022-01-25 00:00:00','admin','2022-01-25 00:00:00','admin'),(28,1,'2022-01-25 00:00:00','admin','2022-01-25 00:00:00','admin'),(29,1,'2022-01-25 00:00:00','admin','2022-01-25 00:00:00','admin'),(30,1,'2022-01-25 00:00:00','admin','2022-01-25 00:00:00','admin'),(31,1,'2022-01-25 00:00:00','admin','2022-01-25 00:00:00','admin'),(32,1,'2022-01-25 13:52:41','admin','2022-01-25 13:52:41','admin'),(33,1,'2022-01-25 13:58:04','admin','2022-01-25 13:58:04','admin'),(34,1,'2022-01-25 21:53:40','admin','2022-01-25 21:53:40','admin'),(35,1,'2022-01-26 20:35:49','admin','2022-01-26 20:35:49','admin'),(36,1,'2022-01-26 23:16:59','admin','2022-01-26 23:16:59','admin'),(37,1,'2022-01-27 15:08:55','admin','2022-01-27 15:08:55','admin'),(38,3,'2022-01-27 15:15:11','Doctor','2022-01-27 15:15:11','Doctor'),(39,5,'2022-01-27 16:59:03','patient','2022-01-27 16:59:03','patient');
/*!40000 ALTER TABLE `USER_FORMS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_PAGE`
--

DROP TABLE IF EXISTS `USER_PAGE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_PAGE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
  `SEQUENCE` int(11) DEFAULT NULL,
  `LAYOUT` int(11) DEFAULT NULL,
  `USER_FLOW_ID` int(11) NOT NULL,
  `ACTUAL_PAGE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_PAGE`
--

LOCK TABLES `USER_PAGE` WRITE;
/*!40000 ALTER TABLE `USER_PAGE` DISABLE KEYS */;
INSERT INTO `USER_PAGE` VALUES (1,'FIRST',1,1,10,1),(2,'FIRST',1,1,14,1),(3,'SECOND',2,1,14,2),(4,'FIRST',1,1,15,1),(5,'SECOND',2,1,15,2),(6,'FIRST',1,1,16,1),(7,'SECOND',2,1,16,2),(8,'FIRST',1,1,17,1),(9,'SECOND',2,1,17,2),(10,'FIRST',1,1,18,1),(11,'SECOND',2,1,18,2),(12,'FIRST',1,1,19,1),(13,'SECOND',2,1,19,2),(14,'FIRST',1,1,20,1),(15,'SECOND',2,1,20,2),(16,'Third',3,2,20,3),(17,'FIRST',1,1,21,1),(18,'SECOND',2,1,21,2),(19,'Third',3,2,21,3),(20,'FIRST',1,1,22,1),(21,'SECOND',2,1,22,2),(22,'Third',3,2,22,3),(23,'FIRST',1,1,23,1),(24,'SECOND',2,1,23,2),(25,'Third',3,2,23,3),(26,'FIRST',1,1,24,1),(27,'SECOND',2,1,24,2),(28,'Third',3,2,24,3),(29,'FIRST',1,1,25,1),(30,'SECOND',2,1,25,2),(31,'Third',3,2,25,3),(32,'FIRST',1,1,26,1),(33,'SECOND',2,1,26,2),(34,'Third',3,2,26,3),(35,'FIRST',1,1,27,1),(36,'SECOND',2,1,27,2),(37,'Third',3,2,27,3),(38,'FIRST',1,1,28,1),(39,'SECOND',2,1,28,2),(40,'Third',3,2,28,3),(41,'FIRST',1,1,29,1),(42,'SECOND',2,1,29,2),(43,'Third',3,2,29,3),(44,'FIRST',1,1,30,1),(45,'SECOND',2,1,30,2),(46,'Third',3,2,30,3),(47,'FIRST',1,1,31,1),(48,'SECOND',2,1,31,2),(49,'Third',3,2,31,3),(50,'FIRST',1,1,32,1),(51,'SECOND',2,1,32,2),(52,'Third',3,2,32,3),(53,'FIRST',1,1,33,1),(54,'SECOND',2,1,33,2),(55,'Third',3,2,33,3),(56,'FIRST',1,1,34,1),(57,'SECOND',2,1,34,2),(58,'First',1,1,36,1),(59,'Second',2,1,36,2),(60,'Step 3',1,NULL,36,4),(61,'Second',2,1,37,2),(62,'Second',2,1,38,2),(63,'First',1,1,39,1),(64,'Second',2,1,39,2),(65,'Step 3',1,NULL,39,4);
/*!40000 ALTER TABLE `USER_PAGE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_QUESTIONS`
--

DROP TABLE IF EXISTS `USER_QUESTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_QUESTIONS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `USER_SECTION_ID` int(11) NOT NULL,
  `ANSWER` varchar(255) DEFAULT NULL,
  `TYPE` varchar(45) DEFAULT NULL,
  `ACTUAL_QUESTION_ID` int(11) NOT NULL,
  `REQUIRED` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_QUESTIONS`
--

LOCK TABLES `USER_QUESTIONS` WRITE;
/*!40000 ALTER TABLE `USER_QUESTIONS` DISABLE KEYS */;
INSERT INTO `USER_QUESTIONS` VALUES (1,'What is your first Name ?',1,NULL,NULL,1,0),(2,'What is your first Name ?',2,NULL,NULL,1,0),(3,'What is your Last Name ?',2,NULL,NULL,3,0),(4,'What is Date of birth ?',3,NULL,NULL,4,0),(5,'Test Question ?',3,NULL,NULL,5,0),(6,'Test111 ?',2,NULL,NULL,6,0),(7,'Hello',2,NULL,NULL,7,0),(8,'Describe your self ?',3,NULL,NULL,8,0),(9,'Testing type ?',2,NULL,NULL,9,0),(10,'Tewst',2,NULL,NULL,10,0),(11,' ?Test Question111 ?',2,NULL,NULL,11,0),(12,'What is your first Name ?',4,NULL,NULL,1,0),(13,'What is your Last Name ?',4,NULL,NULL,3,0),(14,'What is Date of birth ?',5,NULL,NULL,4,0),(15,'Test Question ?',5,NULL,NULL,5,0),(16,'Test111 ?',4,NULL,NULL,6,0),(17,'Hello',4,NULL,NULL,7,0),(18,'Describe your self ?',5,NULL,NULL,8,0),(19,'Testing type ?',4,NULL,NULL,9,0),(20,'Tewst',4,NULL,NULL,10,0),(21,' ?Test Question111 ?',4,NULL,NULL,11,0),(22,'What is your first Name ?',6,'sai Ram',NULL,1,0),(23,'What is your Last Name ?',6,'Dikondawar',NULL,3,0),(24,'What is Date of birth ?',7,'1990',NULL,4,0),(25,'Test Question ?',7,'Question',NULL,5,0),(26,'Test111 ?',6,'Testing Answer',NULL,6,0),(27,'Hello',6,'Hello Answer',NULL,7,0),(28,'Describe your self ?',7,'Yes',NULL,8,0),(29,'Testing type ?',6,'Test Form1',NULL,9,0),(30,'Tewst',6,'',NULL,10,0),(31,' ?Test Question111 ?',6,'',NULL,11,0),(32,'What is your first Name ?',8,'fasdfasd',NULL,1,0),(33,'What is your Last Name ?',8,'fads',NULL,3,0),(34,'What is Date of birth ?',9,'ssss',NULL,4,0),(35,'Test Question ?',9,'ss',NULL,5,0),(36,'Test111 ?',8,'fasd',NULL,6,0),(37,'Hello',8,'fads',NULL,7,0),(38,'Describe your self ?',9,'sss',NULL,8,0),(39,'Testing type ?',8,'fads',NULL,9,0),(40,'Tewst',8,'fads',NULL,10,0),(41,' ?Test Question111 ?',8,'ssee',NULL,11,0),(42,'What is your first Name ?',10,'fadsfasd',NULL,1,0),(43,'What is your Last Name ?',10,'fasd',NULL,3,0),(44,'What is Date of birth ?',11,'',NULL,4,0),(45,'Test Question ?',11,'',NULL,5,0),(46,'Test111 ?',10,'fads',NULL,6,0),(47,'Hello',10,'fasd',NULL,7,0),(48,'Describe your self ?',11,'',NULL,8,0),(49,'Testing type ?',10,'afds',NULL,9,0),(50,'Tewst',10,'fads',NULL,10,0),(51,' ?Test Question111 ?',10,'afds',NULL,11,0),(52,'What is your first Name ?',12,'',NULL,1,0),(53,'What is your Last Name ?',12,'',NULL,3,0),(54,'What is Date of birth ?',13,'',NULL,4,0),(55,'Test Question ?',13,'',NULL,5,0),(56,'Test111 ?',12,'',NULL,6,0),(57,'Hello',12,'',NULL,7,0),(58,'Describe your self ?',13,'fadsfad',NULL,8,0),(59,'Testing type ?',12,'',NULL,9,0),(60,'Tewst',12,'',NULL,10,0),(61,' ?Test Question111 ?',12,'fasfdsa',NULL,11,0),(62,'What is your first Name ?',14,'',NULL,1,0),(63,'What is your Last Name ?',14,'',NULL,3,0),(64,'What is Date of birth ?',15,'',NULL,4,0),(65,'Test Question ?',15,'',NULL,5,0),(66,'Test111 ?',14,'',NULL,6,0),(67,'Hello',14,'',NULL,7,0),(68,'Describe your self ?',15,'',NULL,8,0),(69,'Testing type ?',14,'',NULL,9,0),(70,'Tewst',14,'',NULL,10,0),(71,' ?Test Question111 ?',16,'Section 3 Test',NULL,11,0),(72,'What is your first Name ?',17,'fadsfdasf',NULL,1,0),(73,'What is your Last Name ?',17,'',NULL,3,0),(74,'What is Date of birth ?',18,'',NULL,4,0),(75,'Test Question ?',18,'',NULL,5,0),(76,'Test111 ?',17,'',NULL,6,0),(77,'Hello',17,'',NULL,7,0),(78,'Describe your self ?',18,'',NULL,8,0),(79,'Testing type ?',17,'',NULL,9,0),(80,'Tewst',17,'',NULL,10,0),(81,' ?Test Question111 ?',19,'',NULL,11,0),(82,'What is your first Name ?',20,'',NULL,1,0),(83,'What is your Last Name ?',20,'',NULL,3,0),(84,'What is Date of birth ?',21,'',NULL,4,0),(85,'Test Question ?',21,'',NULL,5,0),(86,'Test111 ?',20,'',NULL,6,0),(87,'Hello',20,'',NULL,7,0),(88,'Describe your self ?',21,'',NULL,8,0),(89,'Testing type ?',20,'',NULL,9,0),(90,'Tewst',20,'',NULL,10,0),(91,' ?Test Question111 ?',22,'Test',NULL,11,0),(92,'What is your first Name ?',23,'SAi Ram',NULL,1,0),(93,'What is your Last Name ?',23,'Dikondawar',NULL,3,0),(94,'What is Date of birth ?',24,'1990',NULL,4,0),(95,'Test Question ?',24,'Question',NULL,5,0),(96,'Test111 ?',23,'Testing',NULL,6,0),(97,'Hello',23,'Hello',NULL,7,0),(98,'Describe your self ?',24,'Yes',NULL,8,0),(99,'Testing type ?',23,'Types',NULL,9,0),(100,'Tewst',23,'Teswws',NULL,10,0),(101,' ?Test Question111 ?',25,'Hee',NULL,11,0),(102,'What is your first Name ?',26,'Sai Ram',NULL,1,0),(103,'What is your Last Name ?',26,'Dikondawar',NULL,3,0),(104,'What is Date of birth ?',27,'fadsfads',NULL,4,0),(105,'Test Question ?',27,'fasdfasd',NULL,5,0),(106,'Test111 ?',26,'Test',NULL,6,0),(107,'Hello',26,'HHH',NULL,7,0),(108,'Describe your self ?',27,'fadsfasd',NULL,8,0),(109,'Testing type ?',28,'fasdfads',NULL,9,0),(110,'Tewst',26,'HHH',NULL,10,0),(111,' ?Test Question111 ?',28,'fsdfasdfad',NULL,11,0),(112,'What is your first Name ?',29,'Sai Ram',NULL,1,1),(113,'What is your Last Name ?',29,'',NULL,3,0),(114,'What is Date of birth ?',30,'',NULL,4,0),(115,'Test Question ?',30,'',NULL,5,0),(116,'Test111 ?',29,'',NULL,6,0),(117,'Hello',29,'',NULL,7,0),(118,'Describe your self ?',30,'',NULL,8,0),(119,'Testing type ?',31,'',NULL,9,0),(120,'Tewst',29,'',NULL,10,0),(121,' ?Test Question111 ?',31,'',NULL,11,0),(122,'What is your first Name ?',32,'Test',NULL,1,1),(123,'What is your Last Name ?',32,'',NULL,3,0),(124,'What is Date of birth ?',33,'',NULL,4,0),(125,'Test Question ?',33,'',NULL,5,0),(126,'Test111 ?',32,'',NULL,6,0),(127,'Hello',32,'',NULL,7,0),(128,'Describe your self ?',33,'',NULL,8,0),(129,'Testing type ?',34,'',NULL,9,0),(130,'Tewst',32,'',NULL,10,0),(131,' ?Test Question111 ?',34,'',NULL,11,0),(132,'What is your first Name ?',35,'TEst',NULL,1,1),(133,'What is your Last Name ?',35,'',NULL,3,0),(134,'What is Date of birth ?',36,'',NULL,4,0),(135,'Test Question ?',36,'',NULL,5,0),(136,'Test111 ?',35,'',NULL,6,0),(137,'Hello',35,'',NULL,7,0),(138,'Describe your self ?',36,'',NULL,8,0),(139,'Testing type ?',37,'',NULL,9,0),(140,'Tewst',35,'',NULL,10,0),(141,' ?Test Question111 ?',37,'',NULL,11,0),(142,'What is your first Name ?',38,'fdasfdsa',NULL,1,1),(143,'What is your Last Name ?',38,'',NULL,3,0),(144,'What is Date of birth ?',39,'',NULL,4,0),(145,'Test Question ?',39,'',NULL,5,0),(146,'Test111 ?',38,'Yes',NULL,6,1),(147,'Hello',38,'',NULL,7,0),(148,'Describe your self ?',39,'',NULL,8,0),(149,'Testing type ?',40,'',NULL,9,0),(150,'Tewst',38,'',NULL,10,0),(151,' ?Test Question111 ?',40,'',NULL,11,0),(152,'What is your first Name ?',41,'Hello','Text',1,1),(153,'What is your Last Name ?',41,'','Text',3,0),(154,'What is Date of birth ?',42,'','Text',4,0),(155,'Test Question ?',42,'','Text',5,0),(156,'Test111 ?',41,'No','Radio',6,1),(157,'Hello',41,'','Text',7,0),(158,'Describe your self ?',42,'','',8,0),(159,'Testing type ?',43,'','Text',9,0),(160,'Tewst',41,'','Text',10,0),(161,' ?Test Question111 ?',43,'','Text',11,0),(162,'What is your first Name ?',44,'fadsfda','Text',1,1),(163,'What is your Last Name ?',44,'','Text',3,0),(164,'What is Date of birth ?',45,'2022-01-25','Date',4,0),(165,'Test Question ?',45,'Test','Text',5,0),(166,'Test111 ?',44,'No','Radio',6,1),(167,'Hello',44,'','Text',7,0),(168,'Describe your self ?',45,'','Text',8,0),(169,'Testing type ?',46,'','Text',9,0),(170,'Tewst',44,'','Text',10,0),(171,' ?Test Question111 ?',46,'','Text',11,0),(172,'What is your first Name ?',47,'fasdfa','Text',1,1),(173,'What is your Last Name ?',47,'','Text',3,0),(174,'What is Date of birth ?',48,'2022-01-25','Date',4,0),(175,'Test Question ?',48,'','Text',5,0),(176,'Test111 ?',47,'No','Radio',6,1),(177,'Hello',47,'','Text',7,0),(178,'Describe your self ?',48,'','Text',8,0),(179,'Testing type ?',49,'','Text',9,0),(180,'Tewst',47,'','Text',10,0),(181,' ?Test Question111 ?',49,'','Text',11,0),(182,'What is your first Name ?',50,'Test','Text',1,1),(183,'What is your Last Name ?',50,'','Text',3,0),(184,'What is Date of birth ?',51,'2022-01-25','Date',4,1),(185,'Test Question ?',51,'','Text',5,0),(186,'Test111 ?',50,'No','Radio',6,1),(187,'Hello',50,'','Text',7,0),(188,'Describe your self ?',51,'','Text',8,0),(189,'Testing type ?',52,'','Text',9,0),(190,'Tewst',50,'','Text',10,0),(191,' ?Test Question111 ?',52,'','Text',11,0),(192,'What is your first Name ?',53,'Test','Text',1,1),(193,'What is your Last Name ?',53,'','Text',3,0),(194,'What is Date of birth ?',54,'2022-01-25','Date',4,1),(195,'Test Question ?',54,'','Text',5,0),(196,'Test111 ?',53,'No','Radio',6,1),(197,'Hello',53,'','Text',7,0),(198,'Describe your self ?',54,'','Text',8,0),(199,'Testing type ?',55,'','Text',9,0),(200,'Tewst',53,'','Text',10,0),(201,' ?Test Question111 ?',55,'','Text',11,0),(202,'My Care Giver Name:',56,'Sai Ram','Text',1,0),(203,'My Care Giver Phone:',56,'9849151198`','Text',3,0),(204,'Emergency Care Contact Name:',56,'Sai Ram','Text',4,0),(205,'POF Finance Contact:',56,'Akhilesh','Text',5,0),(206,'POA Finance Contact Phone:',56,'23456789','Text',6,0),(207,'POA Health Care Contact:',56,'Testing','Text',7,0),(208,'POA Health Care Contact Phone',56,'890000000','Text',8,0),(209,'I frequently forgot to take medcine:',57,'Yes','Radio',9,0),(210,'I frequently miss my doctor appoinment:',57,'Yes','Radio',10,0),(211,'I always choose to go to ER when I am sick:',57,'Yes','Radio',12,0),(212,'Sometimes i go without food to eat at my home:',57,'Yes','Radio',13,0),(213,'Sometimes my power or Utilities shoutoff:',57,'Yes','Radio',14,0),(214,'Address/Contact given:',57,'Green Villas','Text',15,0),(215,'Refferral Assistance/Education Done',57,'Refferal','Text',16,0),(216,'My Care Giver Name :',58,'Test 1','Text',1,0),(217,'My Care Giver Phone:',58,'Test 1','Text',3,0),(218,'Emergency Care Contact Name:',58,'Test 1','Text',4,0),(219,'POF Finance Contact:',58,'Test 1Test 1','Text',5,0),(220,'POA Finance Contact Phone:',58,'Test 1','Text',6,0),(221,'POA Health Care Contact:',58,'Test 1','Text',7,0),(222,'POA Health Care Contact Phone',58,'Test 1','Text',8,0),(223,'I frequently forgot to take medcine:',59,'Yes','Radio',9,0),(224,'I frequently miss my doctor appoinment:',59,'Yes','Radio',10,0),(225,'I always choose to go to ER when I am sick:',59,'Yes','Radio',12,0),(226,'Sometimes i go without food to eat at my home:',59,'Yes','Radio',13,0),(227,'Sometimes my power or Utilities shoutoff:',59,'Yes','Radio',14,0),(228,'Address/Contact given:',59,'Test','Text',15,0),(229,'Refferral Assistance/Education Done',59,'Test','Text',16,0),(230,'Test',59,'Test','Text',17,0),(231,'What is Your name ?',60,'Sai Ram','Text',18,0),(232,'Refferral Assistance/Education Done',61,'Testing Doctor Flow','Text',16,0),(233,'Test',61,'Testing Doctor Flow','Text',20,0),(234,'Refferral Assistance/Education Done',62,'Test','Text',16,0),(235,'Test',62,'Test','Text',20,0),(236,'My Care Giver Name :',63,'2022-01-27','Date',1,0),(237,'My Care Giver Phone:',63,'SAi Ram','Text',3,0),(238,'Emergency Care Contact Name:',63,'12222222','Text',4,0),(239,'POA Finance Contact Phone:',63,'Test','Text',6,0),(240,'POA Health Care Contact:',64,'Test','Text',7,0),(241,'I frequently forgot to take medcine:',65,'Yes','Radio',9,0),(242,'I frequently miss my doctor appoinment:',65,'Yes','Radio',10,0),(243,'I always choose to go to ER when I am sick:',65,'Yes','Radio',12,0),(244,'Sometimes i go without food to eat at my home:',65,'Yes','Radio',13,0),(245,'Sometimes my power or Utilities shoutoff:',65,'Yes','Radio',14,0),(246,'Address/Contact given:',65,'Test','Text',15,0),(247,'Test',65,'Test','Text',17,0),(248,'What is Your name ?',66,'Test','Text',18,0),(249,'Test',65,'Test2','Text',20,0);
/*!40000 ALTER TABLE `USER_QUESTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_SECTIONS`
--

DROP TABLE IF EXISTS `USER_SECTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_SECTIONS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
  `USER_PAGE_ID` int(11) NOT NULL,
  `SEQUENCE` int(11) DEFAULT NULL,
  `ACTUAL_SECTION_ID` int(11) NOT NULL,
  `LAYOUT` varchar(45) DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf32;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_SECTIONS`
--

LOCK TABLES `USER_SECTIONS` WRITE;
/*!40000 ALTER TABLE `USER_SECTIONS` DISABLE KEYS */;
INSERT INTO `USER_SECTIONS` VALUES (1,'Section1',1,NULL,1,'1'),(2,'Section1',2,NULL,1,'1'),(3,'Sect2on1',3,NULL,2,'1'),(4,'Section1',4,NULL,1,'1'),(5,'Sect2on1',5,NULL,2,'1'),(6,'Section1',6,NULL,1,'1'),(7,'Sect2on1',7,NULL,2,'1'),(8,'Section1',8,NULL,1,'1'),(9,'Sect2on1',9,NULL,2,'1'),(10,'Section1',10,NULL,1,'1'),(11,'Sect2on1',11,NULL,2,'1'),(12,'Section1',12,NULL,1,'1'),(13,'Sect2on1',13,NULL,2,'1'),(14,'Section1',14,NULL,1,'1'),(15,'Sect2on1',15,NULL,2,'1'),(16,'Section3',16,NULL,3,'2'),(17,'Section1',17,NULL,1,'1'),(18,'Sect2on1',18,NULL,2,'1'),(19,'Section3',19,NULL,3,'2'),(20,'Section1',20,NULL,1,'1'),(21,'Sect2on1',21,NULL,2,'1'),(22,'Section3',22,NULL,3,'2'),(23,'Section1',23,NULL,1,'1'),(24,'Sect2on1',24,NULL,2,'1'),(25,'Section3',25,NULL,3,'2'),(26,'Section1',26,NULL,1,'1'),(27,'Sect2on1',27,NULL,2,'1'),(28,'Section3',28,NULL,3,'2'),(29,'Section1',29,NULL,1,'1'),(30,'Sect2on1',30,NULL,2,'1'),(31,'Section3',31,NULL,3,'2'),(32,'Section1',32,1,1,'1'),(33,'Sect2on1',33,2,2,'1'),(34,'Section3',34,3,3,'2'),(35,'Section1',35,1,1,'1'),(36,'Sect2on1',36,2,2,'1'),(37,'Section3',37,3,3,'2'),(38,'Section1',38,1,1,'1'),(39,'Sect2on1',39,2,2,'1'),(40,'Section3',40,3,3,'2'),(41,'Section1',41,1,1,'1'),(42,'Sect2on1',42,2,2,'1'),(43,'Section3',43,3,3,'2'),(44,'Section1',44,1,1,'1'),(45,'Sect2on1',45,2,2,'1'),(46,'Section3',46,3,3,'2'),(47,'Section1',47,1,1,'1'),(48,'Sect2on1',48,2,2,'1'),(49,'Section3',49,3,3,'2'),(50,'Section1',50,1,1,'1'),(51,'Sect2on1',51,2,2,'1'),(52,'Section3',52,3,3,'2'),(53,'Section1',53,1,1,'1'),(54,'Sect2on1',54,2,2,'1'),(55,'Section3',55,3,3,'2'),(56,'Alternate Contact',56,1,1,'1'),(57,'Resource Assesment To Evaluate Self-Care',57,2,2,'1'),(58,'Alternate Contact',58,5,1,'1'),(59,'Resource Assesment To Evaluate Self-Care',59,2,2,'1'),(60,'Section 3 ',60,1,5,NULL),(61,'Resource Assesment To Evaluate Self-Care',61,2,2,'2'),(62,'Resource Assesment To Evaluate Self-Care',62,2,2,'2'),(63,'Alternate Contact',63,5,1,'2'),(64,'Section 3',64,4,4,NULL),(65,'Resource Assesment To Evaluate Self-Care',64,2,2,'2'),(66,'Section 3 ',65,1,5,NULL);
/*!40000 ALTER TABLE `USER_SECTIONS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-28 12:03:32
