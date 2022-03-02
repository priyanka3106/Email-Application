-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: emaildb
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `emails`
--

DROP TABLE IF EXISTS `emails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emails` (
  `toemail` varchar(50) DEFAULT NULL,
  `subject` varchar(100) NOT NULL,
  `contents` varchar(500) NOT NULL,
  `fromemail` varchar(50) DEFAULT NULL,
  `emaildate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emails`
--

LOCK TABLES `emails` WRITE;
/*!40000 ALTER TABLE `emails` DISABLE KEYS */;
INSERT INTO `emails` VALUES ('piyupatil766@gmail.com','Hello','This is my first email','omkarsakhare662@gmail.com','2022-02-23 02:16:51'),('omkarsakhare662@gmail.com','Hello','Cool','piyupatil766@gmail.com','2022-02-23 02:19:44');
/*!40000 ALTER TABLE `emails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inboxtable`
--

DROP TABLE IF EXISTS `inboxtable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inboxtable` (
  `receiver` varchar(50) NOT NULL,
  `subject` varchar(300) NOT NULL,
  `contents` varchar(1000) NOT NULL,
  `sender` varchar(50) NOT NULL,
  `emaildate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inboxtable`
--

LOCK TABLES `inboxtable` WRITE;
/*!40000 ALTER TABLE `inboxtable` DISABLE KEYS */;
INSERT INTO `inboxtable` VALUES ('omkarsakhare662@gmail.com','First Mail','This is my first mail......','piyupatil766@gmail.com','2022-02-23 03:30:23'),('piyupatil766@gmail.com','Second Mail','This is myy second mail....','omkarsakhare662@gmail.com','2022-02-23 03:32:17'),('omkarsakhare662@gmail.com','Hello','Hiiii','omkarsakhare662@gmail.com','2022-02-23 18:23:02'),('piyupatil766@gmail.com','Second Mail','Ok','omkarsakhare662@gmail.com','2022-02-23 18:24:44'),('piyupatil766@gmail.com','Hello','Please attend the drive','piyupatil766@gmail.com','2022-02-24 14:28:35'),('piyupatil766@gmail.com','Job','Thanks for the opportunity!!!','omkarsakhare662@gmail.com','2022-02-24 19:38:27'),('piyupatil766@gmail.com','First Mail','For your Kind attention- CGI Interview Invite\n\nJob ID: J0220-0060\nCategory: Engineering Services\nExperience Level: Entry/ Mid-level Professional\n\nApply here in prescribed format','omkarsakhare662@gmail.com','2022-02-24 19:40:54');
/*!40000 ALTER TABLE `inboxtable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `senttable`
--

DROP TABLE IF EXISTS `senttable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `senttable` (
  `sender` varchar(50) NOT NULL,
  `subject` varchar(300) NOT NULL,
  `contents` varchar(1000) NOT NULL,
  `receiver` varchar(50) NOT NULL,
  `emaildate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `senttable`
--

LOCK TABLES `senttable` WRITE;
/*!40000 ALTER TABLE `senttable` DISABLE KEYS */;
INSERT INTO `senttable` VALUES ('omkarsakhare662@gmail.com','Second Mail','This is myy second mail....','piyupatil766@gmail.com','2022-02-23 03:32:17'),('omkarsakhare662@gmail.com','Second Mail','Ok','piyupatil766@gmail.com','2022-02-23 18:24:44'),('piyupatil766@gmail.com','Hello','Please attend the drive','piyupatil766@gmail.com','2022-02-24 14:28:35'),('piyupatil766@gmail.com','First ','Welcomw','omkarsakhare662@gmail.com','2022-02-24 17:42:52'),('omkarsakhare662@gmail.com','Job','Thanks for the opportunity!!!','piyupatil766@gmail.com','2022-02-24 19:38:27'),('omkarsakhare662@gmail.com','First Mail','For your Kind attention- CGI Interview Invite\n\nJob ID: J0220-0060\nCategory: Engineering Services\nExperience Level: Entry/ Mid-level Professional\n\nApply here in prescribed format','piyupatil766@gmail.com','2022-02-24 19:40:54');
/*!40000 ALTER TABLE `senttable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlogin`
--

DROP TABLE IF EXISTS `userlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userlogin` (
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(10) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlogin`
--

LOCK TABLES `userlogin` WRITE;
/*!40000 ALTER TABLE `userlogin` DISABLE KEYS */;
INSERT INTO `userlogin` VALUES ('Omkar','Sakhare','omkarsakhare662@gmail.com','Omkar@3106'),('Piyu','Patil','piyupatil766@gmail.com','Piyu@3106');
/*!40000 ALTER TABLE `userlogin` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-02 14:33:08
