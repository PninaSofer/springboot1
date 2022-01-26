--
-- Host: localhost    Database: ashCollege
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `ashCollege`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ashCollege` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ashCollege`;

--
-- Table structure for table `associations`
--

DROP TABLE IF EXISTS `associations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `associations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `associations`
--

LOCK TABLES `associations` WRITE;
/*!40000 ALTER TABLE `associations` DISABLE KEYS */;
INSERT INTO `associations` VALUES (1,'Teachers'),(2,'Drivers'),(3,'Developers'),(4,'Instructors'),(5,'Engineers');
/*!40000 ALTER TABLE `associations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `isGlobal` bit(1) DEFAULT NULL,
  `saleEnd` date DEFAULT NULL,
  `saleStart` date DEFAULT NULL,
  `storeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKodsf60e53x6kovrnh4yiv9s0u` (`storeId`)
) ENGINE=MyISAM AUTO_INCREMENT=351 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (301,'Smart boiler has 5% off',_binary '\0','2022-01-04','2022-01-20',25),(302,'Table lamp has 10 % off',_binary '\0','2022-02-26','2022-01-20',25),(303,'Berries has 4% off per killo',_binary '\0','2022-03-04','2022-01-20',24),(304,'Fruit mix has 4% off',_binary '\0','2022-04-26','2022-01-20',24),(305,'Five items get 10% off',_binary '\0','2022-01-20','2022-01-10',23),(306,'t SHirt',_binary '\0','2022-06-26','2022-01-20',23),(307,'2 Shirts for 3',_binary '\0','2022-07-04','2022-01-20',22),(308,'2 socks for 1',_binary '\0','2022-08-26','2022-01-20',22),(309,'Two tents for one',_binary '\0','2022-09-04','2022-01-25',21),(310,'Two warm coats for one',_binary '\0','2022-10-26','2022-01-20',21),(311,'T.V has 15% off',_binary '\0','2022-11-04','2022-01-20',25),(312,'Laptop has 10% off',_binary '\0','2022-12-26','2022-01-20',25),(313,'Ten Apples for five',_binary '\0','2022-01-04','2022-01-20',24),(314,'Five oranges for three',_binary '\0','2022-02-26','2022-01-20',24),(315,'Gloves',_binary '\0','2022-03-04','2022-01-20',23),(316,'paper clips',_binary '\0','2022-04-26','2022-01-20',23),(317,'two jeans for 1',_binary '\0','2022-05-04','2022-01-20',22),(318,'2 coats for 1',_binary '\0','2022-06-26','2022-01-20',22),(319,'Three Travel backpacks for one',_binary '\0','2022-07-04','2022-01-25',21),(320,'Two warm gloves for one',_binary '\0','2022-08-26','2022-01-20',21),(321,'Speakers has 13% off',_binary '\0','2022-09-04','2022-01-20',25),(322,'PC Screen 29\" has 5% off',_binary '\0','2022-10-26','2022-01-20',25),(323,'Killo bananas for half',_binary '\0','2022-11-04','2022-01-20',24),(324,'Mellon has 30% discount',_binary '\0','2022-12-26','2022-01-20',24),(325,'2 Cups for 1',_binary '\0','2022-01-04','2022-01-20',23),(326,'3 pictures for the proce of two',_binary '\0','2022-02-26','2022-01-20',23),(327,'two hats for 1',_binary '\0','2022-03-04','2022-01-20',22),(328,'4 green gloves for 2',_binary '\0','2022-04-26','2022-01-20',22),(329,'Two working shoes for one',_binary '\0','2022-05-04','2022-01-20',21),(330,'Two shoe brushes for one',_binary '\0','2022-06-26','2022-01-20',21),(331,'Tablet has 9% off',_binary '\0','2022-07-04','2022-01-20',25),(332,'Smart Lamp has 6% off',_binary '\0','2022-08-26','2022-01-20',25),(333,'Killo of Grapes has 25% off',_binary '','2022-01-25','2021-01-20',24),(334,'Seven Peaches for five',_binary '\0','2022-10-26','2022-01-20',24),(335,'2 books for 1',_binary '\0','2022-01-25','2021-05-20',23),(336,'5 pencils for 3',_binary '\0','2022-12-26','2022-01-20',23),(337,'Fleece coast has 3% off',_binary '','2023-01-04','2022-01-25',22),(338,'Two scarfs for one',_binary '\0','2022-02-26','2022-01-20',22),(339,'Hierk\'s shoes has 5% off',_binary '\0','2022-03-04','2022-01-20',21),(340,'Two Flannel roles for one',_binary '\0','2022-04-26','2022-01-20',21),(341,'Iphone 15 has 2% off',_binary '','2022-05-04','2021-01-20',25),(342,'Samsung 22s has 3% off',_binary '\0','2022-06-26','2022-01-20',25),(343,'Eight pears for six',_binary '\0','2022-07-04','2022-01-20',24),(344,'Two lemons for one',_binary '\0','2022-08-26','2022-01-20',24),(345,'Buy a house and get free pen',_binary '\0','2022-09-04','2022-01-20',23),(346,'Every 500 NIS gets 4% off',_binary '\0','2022-01-21','2021-01-20',23),(347,'Two skirts for one',_binary '\0','2022-11-04','2022-01-20',22),(348,'Three shoes for one',_binary '\0','2022-12-26','2022-01-20',22),(349,'Three lantern for two',_binary '\0','2023-01-04','2022-01-20',21),(350,'Five batteries for one',_binary '\0','2022-02-26','2022-01-20',21);
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storeAssociations`
--

DROP TABLE IF EXISTS `storeAssociations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `storeAssociations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `associationId` int(11) DEFAULT NULL,
  `storeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmttnggnd4h2qachou9h1xuldr` (`associationId`),
  KEY `FKolkeng7execmjlwhk98ajhm6c` (`storeId`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeAssociations`
--

LOCK TABLES `storeAssociations` WRITE;
/*!40000 ALTER TABLE `storeAssociations` DISABLE KEYS */;
INSERT INTO `storeAssociations` VALUES (31,1,25),(32,2,24),(33,3,23),(34,4,22),(35,5,21);
/*!40000 ALTER TABLE `storeAssociations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (25,'Electronics Store'),(24,'Fruits Store'),(23,'General  Store'),(22,'Fox Store'),(21,'Rikushet Store');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userAssociations`
--

DROP TABLE IF EXISTS `userAssociations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `userAssociations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `associationId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2hgvtcwci2bg37n1r0cjsy0kt` (`associationId`),
  KEY `FK3qgwm8rox6way971tmfjrt5wi` (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userAssociations`
--

LOCK TABLES `userAssociations` WRITE;
/*!40000 ALTER TABLE `userAssociations` DISABLE KEYS */;
INSERT INTO `userAssociations` VALUES (9,5,3),(11,1,3),(12,1,2);
/*!40000 ALTER TABLE `userAssociations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'1234','DE8A2505761AF377217CF8AB8501A86B','dcba'),(2,'1234','8E5141E23F62F125B756336FAEF4F3D1','qaws');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ashCollege'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-26 20:25:47
