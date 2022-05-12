CREATE DATABASE  IF NOT EXISTS `finalexampttk` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `finalexampttk`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: finalexampttk
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'dominh','1234'),(2,'admin','admin');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `HouseNo` int NOT NULL,
  `Street` varchar(255) DEFAULT NULL,
  `District` varchar(255) DEFAULT NULL,
  `City` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,12,'Tây Mỗ','Nam Từ Liêm','Hà Nội'),(2,12,'Bưởi','Thanh Xuân','Hà Nội');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Bio` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Hoàng Văn Vân, Nguyễn Thị Chi, Lê Kim Dung, Phan Chí Nghĩa, Vũ Mai Trang, Lương Quỳnh Trang, Nguyễn Quốc Tuấn, David Kaye',NULL),(2,'Hoàng Văn Vân, Nguyễn Thị Chi, Lê Kim Dung, Phan Chí Nghĩa, Vũ Mai Trang, Lương Quỳnh Trang',NULL),(3,'Boris Pasternak',NULL),(4,'Agatha Christie',NULL),(5,'John L. Hennessy, David A. Patterson',NULL),(6,'William Stallings',NULL);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `ISBN` varchar(255) NOT NULL,
  `CategoryId` int NOT NULL,
  `PublisherId` int NOT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Summary` varchar(255) DEFAULT NULL,
  `PublicationDate` date DEFAULT NULL,
  `NumOfPages` int NOT NULL,
  `Language` varchar(255) DEFAULT NULL,
  `Dimensions` varchar(255) DEFAULT NULL,
  `Weight` float NOT NULL,
  `Edition` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `FKBook221707` (`PublisherId`),
  KEY `FKBook738172` (`CategoryId`),
  CONSTRAINT `FKBook221707` FOREIGN KEY (`PublisherId`) REFERENCES `publisher` (`Id`),
  CONSTRAINT `FKBook738172` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('978-0-099-54124-0',2,2,'Doctor Zhivago (Bác sĩ Zhivago)','Bác sĩ Zhivago - Bản dịch tiếng Anh của Richard Pevear và Larissa Volokhonsky','2011-01-01',514,'Tiếng Anh','13 x 19.5 cm',360,'1'),('978-0-12-811905-1',3,4,'Computer Architecture - A Quantitative Approach (6th ed.)','Computer Architecture - A Quantitative Approach - ấn bản lần thứ 6','2017-12-07',936,'Tiếng Anh','19.05 x 4.57 x 23.37 cm',1791,'6'),('978-0-13-467095-9',3,5,'Operating Systems: Internals and Design Principles (9th ed.)','Operating Systems: Internals And Design Principles (ấn bản lần thứ 9)','2017-03-13',800,'Tiếng Anh','18.54 x 3.56 x 23.75 cm',1120,'9'),('978-604-0-00835-0',1,1,'Tiếng Anh 7 - Sách Học Sinh - Tập 1','Sách giáo khoa Tiếng Anh 7 tập 1 cho học sinh','2015-11-09',71,'Tiếng Anh','19 x 26.5 cm',250,'1'),('978-604-0-25873-1',1,1,'Tiếng Anh 6 - Tập 1 - Sách Học Sinh (Global Success)','Sách giáo khoa Tiếng Anh 6 tập 1 cho học sinh (Global Success)','2021-05-07',71,'Tiếng Anh','20 x 28 cm',250,'1'),('978-604-1-07222-0',2,3,'Chuỗi án mạng A.B.C','Chuỗi án mạng A.B.C - Bản dịch tiếng Việt của Võ Thị Hương Lan','2015-04-15',298,'Tiếng Việt','13 x 20 cm',300,'1'),('978-604-1-07777-5',2,3,'Những chiếc đồng hồ kỳ lạ','Những chiếc đồng hồ kỳ lạ - Bản dịch tiếng Việt của Trần Hữu Nam','2015-12-10',367,'Tiếng Việt','13 x 20 cm',325,'2');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_author`
--

DROP TABLE IF EXISTS `book_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_author` (
  `BookISBN` varchar(255) NOT NULL,
  `AuthorId` int NOT NULL,
  PRIMARY KEY (`BookISBN`,`AuthorId`),
  KEY `FKBook_Autho438349` (`AuthorId`),
  CONSTRAINT `FKBook_Autho396816` FOREIGN KEY (`BookISBN`) REFERENCES `book` (`ISBN`),
  CONSTRAINT `FKBook_Autho438349` FOREIGN KEY (`AuthorId`) REFERENCES `author` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_author`
--

LOCK TABLES `book_author` WRITE;
/*!40000 ALTER TABLE `book_author` DISABLE KEYS */;
INSERT INTO `book_author` VALUES ('978-604-0-00835-0',1),('978-604-0-25873-1',2),('978-0-099-54124-0',3),('978-604-1-07222-0',4),('978-604-1-07777-5',4),('978-0-12-811905-1',5),('978-0-13-467095-9',6);
/*!40000 ALTER TABLE `book_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `PaymentId` int NOT NULL,
  `TotalAmount` int NOT NULL,
  `TotalPrice` float NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKCart453015` (`PaymentId`),
  CONSTRAINT `FKCart453015` FOREIGN KEY (`PaymentId`) REFERENCES `payment` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cash`
--

DROP TABLE IF EXISTS `cash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cash` (
  `CashTendered` float NOT NULL,
  `PaymentId` int NOT NULL,
  PRIMARY KEY (`PaymentId`),
  CONSTRAINT `FKCash452996` FOREIGN KEY (`PaymentId`) REFERENCES `payment` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cash`
--

LOCK TABLES `cash` WRITE;
/*!40000 ALTER TABLE `cash` DISABLE KEYS */;
/*!40000 ALTER TABLE `cash` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Giáo dục'),(2,'Văn học'),(3,'Khoa học - Công nghệ');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit`
--

DROP TABLE IF EXISTS `credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credit` (
  `CardId` varchar(255) DEFAULT NULL,
  `AccountId` varchar(255) DEFAULT NULL,
  `Bank` varchar(255) DEFAULT NULL,
  `Type` varchar(255) DEFAULT NULL,
  `ExpDate` date DEFAULT NULL,
  `PaymentId` int NOT NULL,
  PRIMARY KEY (`PaymentId`),
  CONSTRAINT `FKCredit999017` FOREIGN KEY (`PaymentId`) REFERENCES `payment` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit`
--

LOCK TABLES `credit` WRITE;
/*!40000 ALTER TABLE `credit` DISABLE KEYS */;
/*!40000 ALTER TABLE `credit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `AccountId` int NOT NULL,
  `FullNameId` int NOT NULL,
  `AddressId` int NOT NULL,
  `Tel` varchar(255) DEFAULT NULL,
  `DoB` date DEFAULT NULL,
  `Sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKCustomer95429` (`AccountId`),
  KEY `FKCustomer394362` (`FullNameId`),
  KEY `FKCustomer673480` (`AddressId`),
  CONSTRAINT `FKCustomer394362` FOREIGN KEY (`FullNameId`) REFERENCES `fullname` (`Id`),
  CONSTRAINT `FKCustomer673480` FOREIGN KEY (`AddressId`) REFERENCES `address` (`Id`),
  CONSTRAINT `FKCustomer95429` FOREIGN KEY (`AccountId`) REFERENCES `account` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,1,1,1,'0982347993','2021-12-26','Nam');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `AccountId` int NOT NULL,
  `FullNameId` int NOT NULL,
  `AddressId` int NOT NULL,
  `Tel` varchar(255) DEFAULT NULL,
  `DoB` date DEFAULT NULL,
  `Sex` varchar(255) DEFAULT NULL,
  `Role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKEmployee390432` (`AccountId`),
  KEY `FKEmployee99359` (`FullNameId`),
  KEY `FKEmployee621522` (`AddressId`),
  CONSTRAINT `FKEmployee390432` FOREIGN KEY (`AccountId`) REFERENCES `account` (`Id`),
  CONSTRAINT `FKEmployee621522` FOREIGN KEY (`AddressId`) REFERENCES `address` (`Id`),
  CONSTRAINT `FKEmployee99359` FOREIGN KEY (`FullNameId`) REFERENCES `fullname` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,2,2,2,'092321312','2021-11-30','Nam','admin');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fullname`
--

DROP TABLE IF EXISTS `fullname`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fullname` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(255) DEFAULT NULL,
  `MidName` varchar(255) DEFAULT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fullname`
--

LOCK TABLES `fullname` WRITE;
/*!40000 ALTER TABLE `fullname` DISABLE KEYS */;
INSERT INTO `fullname` VALUES (1,'Minh','Đỗ','Trần'),(2,'Admin','Admin','Admin');
/*!40000 ALTER TABLE `fullname` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `ItemBookBarcode` varchar(255) NOT NULL,
  `Src` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKImage914881` (`ItemBookBarcode`),
  CONSTRAINT `FKImage914881` FOREIGN KEY (`ItemBookBarcode`) REFERENCES `itembook` (`Barcode`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'1','img/TiengAnh7_SHS_1.jpg'),(2,'2','img/TiengAnh6_SHS_1.jpg'),(3,'3','img/DoctorZhivago.jpg'),(4,'4','img/ABCMurders.jpg'),(5,'5','img/StrangeClocks.jpg'),(6,'6','img/ComputerArchitecture_Quantitative_6.jpg'),(7,'7','img/OS_Internals_9.jpg');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itembook`
--

DROP TABLE IF EXISTS `itembook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itembook` (
  `Barcode` varchar(255) NOT NULL,
  `BookISBN` varchar(255) NOT NULL,
  `Price` float NOT NULL,
  `Discount` float NOT NULL,
  `PromoText` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Barcode`),
  KEY `FKItemBook68250` (`BookISBN`),
  CONSTRAINT `FKItemBook68250` FOREIGN KEY (`BookISBN`) REFERENCES `book` (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itembook`
--

LOCK TABLES `itembook` WRITE;
/*!40000 ALTER TABLE `itembook` DISABLE KEYS */;
INSERT INTO `itembook` VALUES ('1','978-604-0-00835-0',40000,0,NULL,'Sách tiếng Anh 7 tập 1 cho học sinh'),('2','978-604-0-25873-1',48000,0,NULL,'Sách tiếng Anh 6 (Global Success) tập 1 cho học sinh'),('3','978-0-099-54124-0',232000,0,NULL,'Bác sĩ Zhivago (Vintage UK) - Bản dịch của Richard Pevear và Larissa Volokhonsky'),('4','978-604-1-07222-0',95000,0,NULL,'Chuỗi án mạng A.B.C - Bản dịch của Võ Thị Hương Lan'),('5','978-604-1-07777-5',107000,0,NULL,'Những chiếc đồng hồ kỳ lạ - Bản dịch của Trần Hữu Kham'),('6','978-0-12-811905-1',1769000,0,NULL,'Computer Architecture - A Quantitative Approach (ấn bản lần thứ 6)'),('7','978-0-13-467095-9',4096000,0,NULL,'Operating Systems: Internals and Design Principles (ấn bản lần thứ 9)');
/*!40000 ALTER TABLE `itembook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `CustomerId` int NOT NULL,
  `CartId` int NOT NULL,
  `ShipmentId` int NOT NULL,
  `PaymentId` int NOT NULL,
  `TotalPrice` float NOT NULL,
  `Tax` float NOT NULL,
  `Status` varchar(255) DEFAULT NULL,
  `OrderDate` date DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKOrder556775` (`CustomerId`),
  KEY `FKOrder795661` (`CartId`),
  KEY `FKOrder751860` (`ShipmentId`),
  KEY `FKOrder92127` (`PaymentId`),
  CONSTRAINT `FKOrder556775` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`Id`),
  CONSTRAINT `FKOrder751860` FOREIGN KEY (`ShipmentId`) REFERENCES `shipment` (`Id`),
  CONSTRAINT `FKOrder795661` FOREIGN KEY (`CartId`) REFERENCES `cart` (`Id`),
  CONSTRAINT `FKOrder92127` FOREIGN KEY (`PaymentId`) REFERENCES `payment` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'NXB Giáo dục Việt Nam','81 Trần Hưng Đạo, Hoàn Kiếm, Hà Nội'),(2,'Vintage UK Random House','20 Vauxhall Bridge Road, London, SW1V 2SA, UK'),(3,'NXB Trẻ','Phòng 602, 209 P. Giảng Võ, Chợ Dừa, Đống Đa, Hà Nội'),(4,'Morgan Kaufmann (Elsevier)','50 Hampshire Street, 5th Floor, Cambridge, MA 02139, United States'),(5,'Pearson','330 Hudson Street, New York NY 10013');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selecteditems`
--

DROP TABLE IF EXISTS `selecteditems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `selecteditems` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Quantity` int NOT NULL,
  `ItemBookBarcode` varchar(255) NOT NULL,
  `CartId` int NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `SelectedItems` (`ItemBookBarcode`),
  KEY `SelectedItems2` (`CartId`),
  CONSTRAINT `SelectedItems` FOREIGN KEY (`ItemBookBarcode`) REFERENCES `itembook` (`Barcode`),
  CONSTRAINT `SelectedItems2` FOREIGN KEY (`CartId`) REFERENCES `cart` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selecteditems`
--

LOCK TABLES `selecteditems` WRITE;
/*!40000 ALTER TABLE `selecteditems` DISABLE KEYS */;
/*!40000 ALTER TABLE `selecteditems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `PaymentId` int NOT NULL,
  `Method` varchar(255) DEFAULT NULL,
  `Cost` float NOT NULL,
  `ShippingAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKShipment231267` (`PaymentId`),
  CONSTRAINT `FKShipment231267` FOREIGN KEY (`PaymentId`) REFERENCES `payment` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Content` varchar(255) DEFAULT NULL,
  `CommentDate` date DEFAULT NULL,
  `CustomerId` int NOT NULL,
  `ItemBookBarcode` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKReviewCus` (`CustomerId`),
  KEY `FKReviewItem` (`ItemBookBarcode`),
  CONSTRAINT `FKReviewCus` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`Id`),
  CONSTRAINT `FKReviewItem` FOREIGN KEY (`ItemBookBarcode`) REFERENCES `itembook` (`Barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dump completed on 2022-01-01  1:25:34
