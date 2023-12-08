-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: movie
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `billId` int NOT NULL AUTO_INCREMENT,
  `custId` int NOT NULL,
  `payMethod` varchar(100) NOT NULL,
  `Allprice` int NOT NULL,
  `billWhen` date NOT NULL,
  `TicketId` int DEFAULT NULL,
  `SnOrderId` int DEFAULT NULL,
  `smcheck` int NOT NULL,
  PRIMARY KEY (`billId`),
  KEY `bill_FK_1` (`TicketId`),
  KEY `bill_FK` (`SnOrderId`),
  CONSTRAINT `bill_FK` FOREIGN KEY (`SnOrderId`) REFERENCES `snorder` (`SnOrderid`),
  CONSTRAINT `bill_FK_1` FOREIGN KEY (`TicketId`) REFERENCES `ticket` (`TicketId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookinfo`
--

DROP TABLE IF EXISTS `bookinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookinfo` (
  `bookId` int NOT NULL AUTO_INCREMENT,
  `RunningId` int NOT NULL,
  `bookwho` varchar(100) NOT NULL,
  `mvPrice` int NOT NULL,
  `seat` varchar(10) NOT NULL,
  PRIMARY KEY (`bookId`),
  KEY `bookinfo_FK` (`RunningId`),
  CONSTRAINT `bookinfo_FK` FOREIGN KEY (`RunningId`) REFERENCES `mvtime` (`RunningId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookinfo`
--

LOCK TABLES `bookinfo` WRITE;
/*!40000 ALTER TABLE `bookinfo` DISABLE KEYS */;
INSERT INTO `bookinfo` VALUES (1,1,'성인:1 청소년:2',28000,'1'),(2,1,'성인:1 청소년:2',28000,'4'),(3,1,'성인:1 청소년:2',28000,'5'),(4,1,'성인:1',10000,'13'),(5,2,'성인:1 청소년:1',19000,'20'),(6,2,'성인:1 청소년:1',19000,'21'),(7,10,'성인:1 청소년:1',19000,'21'),(8,10,'성인:1 청소년:1',19000,'22');
/*!40000 ALTER TABLE `bookinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `member` int NOT NULL AUTO_INCREMENT,
  `custid` varchar(100) NOT NULL,
  `custName` varchar(100) NOT NULL,
  `TicketId` int DEFAULT NULL,
  PRIMARY KEY (`member`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'kymin555','김영호',0),(2,'201945023','박무개',4),(3,'201945022','홍길동',4),(4,'Guest','Guest',0);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movieinfo`
--

DROP TABLE IF EXISTS `movieinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movieinfo` (
  `mvID` int NOT NULL AUTO_INCREMENT,
  `mvTitle` varchar(50) NOT NULL,
  `mvAge` varchar(10) NOT NULL,
  `mvType` varchar(30) NOT NULL,
  `mvWhen` date NOT NULL,
  PRIMARY KEY (`mvID`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movieinfo`
--

LOCK TABLES `movieinfo` WRITE;
/*!40000 ALTER TABLE `movieinfo` DISABLE KEYS */;
INSERT INTO `movieinfo` VALUES (23,'범죄 도시2','15','범죄','2022-05-18'),(49,'닥터스트레인지','15','액션','2022-04-05'),(50,'민스미트 작전','12','드라마','2022-05-11'),(51,'매스','12','드라마','2022-05-18'),(52,'뜨거운 피','15','범죄','2022-03-23'),(57,'니 부모 얼굴이 보고싶다','15','드라마','2022-04-27'),(62,'안녕하세요','12','드라마','2022-05-25'),(64,'서울괴담','15','공포','2022-04-27'),(86,'브로커','15','드라마','2022-06-07'),(90,'쥬라기 월드_도미니언','12','액션','2022-06-01');
/*!40000 ALTER TABLE `movieinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mvtime`
--

DROP TABLE IF EXISTS `mvtime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mvtime` (
  `RunningId` int NOT NULL AUTO_INCREMENT,
  `RDay` date NOT NULL,
  `RTime` time NOT NULL,
  `mvID` int NOT NULL,
  `MvWhere` int NOT NULL,
  PRIMARY KEY (`RunningId`),
  KEY `mvtime_FK` (`mvID`),
  CONSTRAINT `mvtime_FK` FOREIGN KEY (`mvID`) REFERENCES `movieinfo` (`mvID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mvtime`
--

LOCK TABLES `mvtime` WRITE;
/*!40000 ALTER TABLE `mvtime` DISABLE KEYS */;
INSERT INTO `mvtime` VALUES (1,'2022-06-01','09:00:00',23,1),(2,'2022-06-15','15:00:00',51,3),(3,'2022-06-01','15:00:00',23,3),(4,'2022-05-31','09:00:00',23,1),(6,'2022-06-04','21:00:00',23,3),(7,'2022-06-04','15:00:00',49,2),(8,'2022-06-04','21:00:00',49,2),(9,'2022-06-02','15:00:00',49,3),(10,'2022-06-05','09:00:00',64,1),(24,'2022-06-10','15:00:00',64,3),(25,'2022-06-05','09:00:00',64,3),(26,'2022-05-30','09:00:00',64,2),(29,'2022-06-01','15:00:00',64,3),(30,'2022-05-27','09:00:00',62,1),(31,'2022-05-27','12:00:00',62,1),(33,'2022-06-04','09:00:00',62,1),(37,'2022-05-27','12:00:00',64,3),(39,'2022-06-20','18:00:00',57,1),(42,'2022-06-15','12:00:00',50,1),(45,'2022-06-15','09:00:00',86,1);
/*!40000 ALTER TABLE `mvtime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `snack`
--

DROP TABLE IF EXISTS `snack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `snack` (
  `snID` int NOT NULL AUTO_INCREMENT,
  `snType` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `snTaste` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `snName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `snPrice` int NOT NULL,
  PRIMARY KEY (`snID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `snack`
--

LOCK TABLES `snack` WRITE;
/*!40000 ALTER TABLE `snack` DISABLE KEYS */;
INSERT INTO `snack` VALUES (10,'팝콘','일반','일반팝콘',10000),(11,'팝콘','카라멜','카라멜팝콘',12000),(12,'팝콘','초코','초코팝콘',13000),(13,'음료수','사이다','칠성사이다',3000),(14,'음료수','콜라','코카콜라',4000),(15,'음료수','오렌지','오렌지쥬스',3000),(16,'사이드','튀김','순살치킨',12000),(17,'사이드','튀김','모듬튀김',5000),(18,'사이드','일반','나쵸',5000),(32,'사이드','양파맛','양파링',1500),(38,'사이드','새우','새우깡',500);
/*!40000 ALTER TABLE `snack` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `snorder`
--

DROP TABLE IF EXISTS `snorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `snorder` (
  `SnOrderid` int NOT NULL AUTO_INCREMENT,
  `snName` varchar(30) NOT NULL,
  `snCount` int NOT NULL,
  `snPrice` int NOT NULL,
  `smCheck` int NOT NULL,
  PRIMARY KEY (`SnOrderid`)
) ENGINE=InnoDB AUTO_INCREMENT=5260343 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `snorder`
--

LOCK TABLES `snorder` WRITE;
/*!40000 ALTER TABLE `snorder` DISABLE KEYS */;
INSERT INTO `snorder` VALUES (5259866,'초코팝콘',2,26000,5251305),(5259867,'코카콜라',2,8000,5251305),(5259868,'코카콜라',2,8000,5253178),(5259869,'순살치킨',2,24000,5253178),(5259870,'일반팝콘',1,10000,5285222),(5259871,'양파링',1,11300,5285222),(5259872,'나쵸',1,5000,5285222),(5259873,'카라멜팝콘',5,60000,621550),(5259874,'오렌지쥬스',5,15000,621550),(5259875,'모듬튀김',5,40000,621550),(5259876,'카라멜팝콘',2,24000,636345),(5259877,'오렌지쥬스',1,3000,636345),(5259878,'일반팝콘',3,30000,635605),(5259879,'코카콜라',1,4000,635605),(5259880,'카라멜팝콘',4,48000,633966),(5259881,'오렌지쥬스',1,3000,633966),(5259882,'카라멜팝콘',1,12000,649984),(5259883,'카라멜팝콘',1,12000,645577),(5259884,'카라멜팝콘',1,12000,64344),(5259885,'일반팝콘',1,10000,642688),(5259886,'카라멜팝콘',1,12000,644916),(5259887,'일반팝콘',1,10000,646379),(5259888,'초코팝콘',1,13000,641816),(5259889,'카라멜팝콘',1,12000,643139),(5259890,'초코팝콘',1,13000,64122),(5259891,'카라멜팝콘',1,12000,642718),(5259892,'초코팝콘',1,13000,6479),(5259893,'일반팝콘',1,10000,646945),(5259894,'모듬튀김',1,8000,643152),(5259895,'카라멜팝콘',1,12000,64632),(5259896,'코카콜라',1,4000,648769),(5259897,'카라멜팝콘',1,12000,646736),(5259898,'카라멜팝콘',1,12000,646504),(5259899,'카라멜팝콘',1,12000,646831),(5259900,'초코팝콘',2,26000,64221),(5259901,'카라멜팝콘',1,12000,641771),(5259902,'초코팝콘',2,26000,642139),(5259903,'코카콜라',1,4000,642139),(5259904,'일반팝콘',4,40000,646725),(5259905,'카라멜팝콘',1,12000,644002),(5259906,'오렌지쥬스',1,3000,645244),(5259907,'초코팝콘',1,13000,645319),(5259908,'카라멜팝콘',1,12000,642327),(5259909,'카라멜팝콘',1,12000,644911),(5259910,'카라멜팝콘',1,12000,643003),(5259911,'일반팝콘',1,10000,6415),(5259912,'카라멜팝콘',1,12000,643310),(5259913,'일반팝콘',1,10000,686273),(5259914,'오렌지쥬스',4,12000,6996),(5259915,'카라멜팝콘',6,72000,692364),(5259916,'일반팝콘',69,690000,692364),(5259917,'나쵸',3,15000,692364),(5259918,'나쵸',5,25000,693530),(5259919,'나쵸',1,5000,697221),(5259920,'오렌지쥬스',1,3000,696919),(5259921,'나쵸',1,5000,693777),(5259922,'초코팝콘',1,13000,693377),(5259923,'초코팝콘',1,13000,691329),(5259924,'초코팝콘',1,13000,6113620),(5259925,'코카콜라',1,4000,6113620),(5259926,'나쵸',1,5000,6113620),(5259927,'코카콜라',6,24000,6114573),(5259928,'일반팝콘',10,100000,6117735),(5259929,'나쵸',1,5000,6116958),(5259930,'나쵸',2,10000,6116824),(5259931,'오렌지쥬스',1,3000,6115610),(5259932,'초코팝콘',4,52000,6113473),(5259933,'초코팝콘',1,13000,6118034),(5259934,'오렌지쥬스',3,9000,6118034),(5259935,'양파링',1,1600,61129),(5259936,'오렌지쥬스',1,3000,6116566),(5259937,'오렌지쥬스',1,3000,6118113),(5259938,'나쵸',1,5000,6112035),(5259939,'나쵸',1,5000,611912),(5259940,'모듬튀김',1,8000,6118397),(5259941,'일반팝콘',7,1000,6114370),(5259942,'오렌지쥬스',1,3000,6113832),(5259943,'나쵸',3,15000,6113832),(5259944,'오렌지쥬스',1,3000,6119467),(5259945,'나쵸',1,5000,6119467),(5259946,'모듬튀김',1,8000,6119467),(5259947,'오렌지쥬스',1,3000,6116858),(5259948,'나쵸',1,5000,6113467),(5259949,'코카콜라',1,4000,6113467),(5259950,'카라멜팝콘',1,12000,6113467),(5259951,'오렌지쥬스',1,3000,6119485),(5259952,'나쵸',1,5000,6119485),(5259953,'나쵸',7,35000,6117399),(5259954,'나쵸',1,5000,6112658),(5259955,'나쵸',1,5000,6119609),(5259956,'코카콜라',1,4000,6119609),(5259957,'코카콜라',1,4000,6118419),(5259958,'나쵸',1,5000,6118419),(5259959,'나쵸',1,5000,6115715),(5259960,'코카콜라',1,4000,6115715),(5259961,'코카콜라',1,4000,611896),(5259962,'나쵸',1,5000,611896),(5259963,'오렌지쥬스',1,3000,6114883),(5259964,'나쵸',1,5000,6114349),(5259965,'나쵸',1,5000,6111318),(5259966,'나쵸',1,5000,6119795),(5259967,'오렌지쥬스',1,3000,6117386),(5259968,'나쵸',1,5000,6111478),(5259969,'나쵸',1,5000,6118679),(5259970,'카라멜팝콘',1,12000,6116028),(5259971,'코카콜라',1,4000,6112299),(5259972,'일반팝콘',1,10000,6113273),(5259973,'나쵸',1,5000,6113213),(5259974,'나쵸',1,5000,6119907),(5259975,'나쵸',1,5000,6113322),(5259976,'오렌지쥬스',1,3000,6117907),(5259977,'나쵸',1,5000,6112645),(5259978,'나쵸',1,5000,6113816),(5259979,'나쵸',1,5000,6115097),(5259980,'나쵸',1,5000,6118543),(5259981,'오렌지쥬스',1,3000,6112112),(5259982,'나쵸',1,5000,6116261),(5259983,'나쵸',1,5000,6118468),(5259984,'오렌지쥬스',1,3000,6114786),(5259985,'나쵸',1,5000,6117268),(5259986,'양파링',1,1600,6118863),(5259987,'코카콜라',1,4000,6113549),(5259988,'모듬튀김',1,8000,6113549),(5259989,'나쵸',1,5000,6118140),(5259990,'오렌지쥬스',1,3000,6118140),(5259991,'카라멜팝콘',1,12000,6118140),(5259992,'코카콜라',1,4000,611198),(5259993,'나쵸',2,10000,611198),(5259994,'나쵸',1,5000,6117776),(5259995,'칠성사이다',1,3000,6117776),(5259996,'카라멜팝콘',1,12000,6117776),(5259997,'나쵸',1,5000,6119978),(5259998,'오렌지쥬스',1,3000,6119978),(5259999,'카라멜팝콘',1,12000,6119978),(5260000,'코카콜라',1,4000,6112646),(5260001,'카라멜팝콘',1,12000,6112646),(5260002,'나쵸',1,5000,6112646),(5260003,'초코팝콘',1,13000,6119627),(5260004,'나쵸',1,5000,6119627),(5260005,'양파링',1,1600,6119627),(5260006,'나쵸',1,5000,6112705),(5260007,'코카콜라',1,4000,6112705),(5260008,'모듬튀김',1,8000,6119775),(5260009,'초코팝콘',1,13000,6119775),(5260010,'모듬튀김',1,8000,6119448),(5260011,'오렌지쥬스',4,12000,6119448),(5260012,'오렌지쥬스',4,12000,6111949),(5260013,'나쵸',3,15000,6111949),(5260014,'일반팝콘',4,40000,6111949),(5260015,'오렌지쥬스',1,3000,6115797),(5260016,'나쵸',1,5000,6115797),(5260017,'초코팝콘',1,13000,6115797),(5260018,'오렌지쥬스',1,3000,6114431),(5260019,'나쵸',1,5000,6114431),(5260020,'코카콜라',1,4000,6114431),(5260021,'코카콜라',1,4000,6117531),(5260022,'나쵸',1,5000,6117531),(5260023,'오렌지쥬스',1,3000,6117531),(5260024,'코카콜라',1,4000,6111887),(5260025,'나쵸',1,5000,6111887),(5260026,'나쵸',2,10000,6111580),(5260027,'오렌지쥬스',1,3000,6111580),(5260028,'오렌지쥬스',1,3000,6113177),(5260029,'모듬튀김',1,8000,6113177),(5260030,'나쵸',1,5000,6111788),(5260031,'순살치킨',1,12000,6111788),(5260032,'코카콜라',1,4000,6114885),(5260033,'일반팝콘',1,10000,6114885),(5260034,'일반팝콘',1,10000,611357),(5260035,'카라멜팝콘',1,12000,611357),(5260036,'오렌지쥬스',1,3000,6114128),(5260037,'나쵸',1,5000,6114128),(5260038,'초코팝콘',1,13000,6114128),(5260039,'오렌지쥬스',1,3000,6113114),(5260040,'초코팝콘',4,52000,6113114),(5260041,'모듬튀김',2,16000,6113114),(5260042,'나쵸',1,5000,6113114),(5260043,'순살치킨',1,12000,6113114),(5260044,'칠성사이다',5,15000,611388),(5260045,'모듬튀김',5,40000,611388),(5260046,'나쵸',1,5000,6114055),(5260047,'코카콜라',1,4000,6114055),(5260048,'오렌지쥬스',1,3000,6113365),(5260049,'나쵸',1,5000,6116422),(5260050,'오렌지쥬스',1,3000,6116727),(5260051,'오렌지쥬스',1,3000,6117898),(5260052,'양파링',1,1600,6116788),(5260053,'오렌지쥬스',1,3000,6116788),(5260054,'나쵸',1,5000,6115070),(5260055,'오렌지쥬스',1,3000,6117546),(5260056,'나쵸',1,5000,6117546),(5260057,'나쵸',1,5000,6112553),(5260058,'오렌지쥬스',1,3000,6112553),(5260059,'나쵸',1,5000,6115647),(5260060,'오렌지쥬스',1,3000,6115647),(5260061,'나쵸',1,5000,611382),(5260062,'코카콜라',1,4000,6114485),(5260063,'순살치킨',1,12000,6114485),(5260064,'오렌지쥬스',1,3000,6118187),(5260065,'오렌지쥬스',1,3000,6112045),(5260066,'나쵸',1,5000,6115097),(5260067,'양파링',1,1600,6116901),(5260068,'오렌지쥬스',1,3000,6113485),(5260069,'나쵸',1,5000,6116213),(5260070,'오렌지쥬스',1,3000,6117889),(5260071,'나쵸',1,5000,6116669),(5260072,'오렌지쥬스',1,3000,6119541),(5260073,'순살치킨',1,12000,6119541),(5260074,'나쵸',1,5000,6114946),(5260075,'나쵸',1,5000,6119662),(5260076,'양파링',1,1600,6116213),(5260077,'나쵸',1,5000,6116213),(5260078,'오렌지쥬스',1,3000,611777),(5260079,'오렌지쥬스',1,3000,611548),(5260080,'나쵸',1,5000,611548),(5260081,'나쵸',1,5000,6113358),(5260082,'오렌지쥬스',1,3000,6113358),(5260083,'오렌지쥬스',1,3000,6116185),(5260084,'나쵸',1,5000,6116185),(5260085,'나쵸',1,5000,6111434),(5260086,'오렌지쥬스',1,3000,6111434),(5260087,'초코팝콘',1,13000,6113572),(5260088,'나쵸',1,5000,6113572),(5260089,'오렌지쥬스',1,3000,6113883),(5260090,'나쵸',1,5000,6113883),(5260091,'오렌지쥬스',1,3000,6115624),(5260092,'나쵸',1,5000,6115624),(5260093,'오렌지쥬스',1,3000,6119828),(5260094,'일반팝콘',1,10000,6119828),(5260095,'나쵸',1,5000,6116282),(5260096,'오렌지쥬스',1,3000,6116282),(5260097,'오렌지쥬스',1,3000,6116466),(5260098,'나쵸',1,5000,6116466),(5260099,'초코팝콘',1,13000,6113048),(5260100,'나쵸',1,5000,6113048),(5260101,'나쵸',1,5000,6118466),(5260102,'오렌지쥬스',1,3000,6112830),(5260103,'나쵸',1,5000,6112830),(5260104,'코카콜라',1,4000,6115037),(5260105,'양파링',1,1600,6115037),(5260106,'나쵸',1,5000,6119897),(5260107,'오렌지쥬스',1,3000,6119897),(5260108,'나쵸',1,5000,611618),(5260109,'오렌지쥬스',1,3000,611618),(5260110,'오렌지쥬스',1,3000,6116446),(5260111,'나쵸',1,5000,6116446),(5260112,'오렌지쥬스',1,3000,6116507),(5260113,'나쵸',1,5000,6116507),(5260114,'오렌지쥬스',1,3000,6115591),(5260115,'나쵸',1,5000,6115591),(5260116,'나쵸',1,5000,611256),(5260117,'오렌지쥬스',1,3000,611256),(5260118,'나쵸',1,5000,6114623),(5260119,'오렌지쥬스',1,3000,6114623),(5260120,'양파링',1,1600,6115045),(5260121,'오렌지쥬스',1,3000,6115045),(5260122,'나쵸',1,5000,6127773),(5260123,'칠성사이다',1,3000,6127773),(5260124,'초코팝콘',1,13000,6127773),(5260125,'순살치킨',2,24000,6127773),(5260126,'양파링',1,1600,6123820),(5260127,'코카콜라',1,4000,6123820),(5260128,'카라멜팝콘',1,12000,6123820),(5260129,'오렌지쥬스',1,3000,6121092),(5260130,'초코팝콘',1,13000,6121092),(5260131,'나쵸',1,5000,6121092),(5260132,'나쵸',6,30000,6127689),(5260133,'오렌지쥬스',1,3000,6127689),(5260134,'오렌지쥬스',1,3000,6123457),(5260135,'초코팝콘',6,78000,6123457),(5260136,'양파링',5,8000,6125322),(5260137,'오렌지쥬스',5,15000,6126531),(5260138,'양파링',6,9600,6126531),(5260139,'양파링',6,9600,6121719),(5260140,'오렌지쥬스',5,15000,6121719),(5260141,'초코팝콘',5,65000,6128899),(5260142,'나쵸',5,25000,6128899),(5260143,'오렌지쥬스',5,15000,6128899),(5260144,'오렌지쥬스',6,18000,6122886),(5260145,'양파링',5,8000,6122886),(5260146,'초코팝콘',6,78000,6122886),(5260147,'오렌지쥬스',7,21000,6127665),(5260148,'양파링',6,9600,6127665),(5260149,'초코팝콘',4,52000,6127665),(5260150,'오렌지쥬스',4,12000,6127565),(5260151,'양파링',4,6400,6127565),(5260152,'양파링',1,1600,6129084),(5260153,'양파링',5,8000,6128090),(5260154,'오렌지쥬스',5,15000,6128090),(5260155,'일반팝콘',4,40000,6122839),(5260156,'나쵸',2,10000,6122839),(5260157,'나쵸',4,20000,6126036),(5260158,'코카콜라',1,4000,6126036),(5260159,'나쵸',4,20000,6129140),(5260160,'코카콜라',1,4000,6129140),(5260161,'코카콜라',5,20000,6126450),(5260162,'나쵸',2,10000,6126450),(5260163,'코카콜라',5,20000,6126783),(5260164,'나쵸',6,30000,6126783),(5260165,'코카콜라',5,20000,6126180),(5260166,'일반팝콘',6,60000,6126180),(5260167,'코카콜라',5,20000,612428),(5260168,'일반팝콘',5,50000,612428),(5260169,'일반팝콘',4,40000,6124853),(5260170,'일반팝콘',4,40000,612700),(5260171,'코카콜라',1,4000,612700),(5260172,'칠성사이다',1,3000,612700),(5260173,'일반팝콘',4,40000,6129662),(5260174,'코카콜라',1,4000,6129662),(5260175,'칠성사이다',1,3000,6129662),(5260176,'일반팝콘',1,10000,6122960),(5260177,'코카콜라',1,4000,6122960),(5260178,'일반팝콘',4,40000,6123809),(5260179,'오렌지쥬스',1,3000,6123809),(5260180,'일반팝콘',1,10000,6122401),(5260181,'오렌지쥬스',1,3000,6122401),(5260182,'일반팝콘',4,40000,6121537),(5260183,'칠성사이다',1,3000,6121537),(5260184,'일반팝콘',2,20000,6124274),(5260185,'칠성사이다',1,3000,6124274),(5260186,'일반팝콘',4,40000,6122191),(5260187,'코카콜라',2,8000,6122191),(5260188,'일반팝콘',2,20000,6124509),(5260189,'오렌지쥬스',4,12000,6124509),(5260190,'칠성사이다',3,9000,6127454),(5260191,'초코팝콘',5,65000,6127454),(5260192,'초코팝콘',4,52000,6124670),(5260193,'나쵸',3,15000,6124670),(5260194,'카라멜팝콘',2,24000,6121640),(5260195,'나쵸',2,10000,6121640),(5260196,'일반팝콘',3,30000,6128687),(5260197,'오렌지쥬스',3,9000,6128687),(5260198,'모듬튀김',5,40000,6128687),(5260199,'순살치킨',4,48000,6129210),(5260200,'오렌지쥬스',3,9000,6129210),(5260201,'양파링',7,11200,6129210),(5260202,'초코팝콘',5,65000,6126188),(5260203,'코카콜라',4,16000,6126188),(5260204,'양파링',7,11200,6126188),(5260205,'칠성사이다',3,9000,612994),(5260206,'순살치킨',3,36000,612994),(5260207,'나쵸',1,5000,6127438),(5260208,'일반팝콘',1,10000,6126788),(5260209,'코카콜라',1,4000,6122429),(5260210,'초코팝콘',1,13000,6127081),(5260211,'나쵸',1,5000,6124533),(5260212,'초코팝콘',1,13000,6126278),(5260213,'초코팝콘',1,13000,6125087),(5260214,'나쵸',1,5000,6127918),(5260215,'일반팝콘',4,40000,6122686),(5260216,'나쵸',4,20000,6122686),(5260217,'오렌지쥬스',1,3000,6123099),(5260218,'나쵸',1,5000,6123099),(5260219,'코카콜라',5,20000,6128277),(5260220,'나쵸',4,20000,6128277),(5260221,'일반팝콘',4,40000,6128277),(5260222,'모듬튀김',5,40000,6125503),(5260223,'카라멜팝콘',5,60000,6125503),(5260224,'오렌지쥬스',4,12000,6125503),(5260225,'양파링',4,6400,6143373),(5260226,'초코팝콘',4,52000,6143373),(5260227,'나쵸',1,5000,6145204),(5260228,'오렌지쥬스',1,3000,6145204),(5260229,'나쵸',1,5000,6147005),(5260230,'코카콜라',1,4000,6144683),(5260231,'코카콜라',1,4000,6149679),(5260232,'오렌지쥬스',1,3000,6146266),(5260233,'나쵸',1,5000,6146266),(5260234,'코카콜라',1,4000,6144713),(5260235,'양파링',1,1600,6144713),(5260236,'오렌지쥬스',1,3000,6141116),(5260237,'나쵸',1,5000,6141116),(5260238,'오렌지쥬스',1,3000,6149587),(5260239,'코카콜라',1,4000,6149587),(5260240,'일반팝콘',1,10000,6149587),(5260241,'카라멜팝콘',1,12000,6142254),(5260242,'오렌지쥬스',1,3000,6142254),(5260243,'일반팝콘',1,10000,6148683),(5260244,'코카콜라',1,4000,6148683),(5260245,'나쵸',1,5000,6148536),(5260246,'칠성사이다',1,3000,6148536),(5260247,'카라멜팝콘',2,24000,6148536),(5260248,'오렌지쥬스',1,3000,6145755),(5260249,'코카콜라',1,4000,6149194),(5260250,'코카콜라',1,4000,6149293),(5260251,'오렌지쥬스',1,3000,6149439),(5260252,'오렌지쥬스',1,3000,6149304),(5260253,'나쵸',1,5000,614438),(5260254,'양파링',1,1600,614105),(5260255,'모듬튀김',1,8000,6145899),(5260256,'오렌지쥬스',1,3000,6141468),(5260257,'양파링',1,1600,6146711),(5260258,'오렌지쥬스',1,3000,6146711),(5260259,'양파링',1,1600,6143236),(5260260,'코카콜라',1,4000,6143236),(5260261,'코카콜라',1,4000,6146972),(5260262,'양파링',1,1600,6146972),(5260263,'카라멜팝콘',5,60000,6148349),(5260264,'순살치킨',3,36000,6148349),(5260265,'오렌지쥬스',3,9000,6148349),(5260266,'코카콜라',3,12000,6148349),(5260267,'나쵸',4,20000,6148349),(5260268,'오렌지쥬스',1,3000,614928),(5260269,'양파링',1,1600,614928),(5260270,'초코팝콘',1,13000,614928),(5260271,'양파링',1,1600,6154336),(5260272,'오렌지쥬스',1,3000,6154336),(5260273,'카라멜팝콘',1,12000,6154336),(5260274,'초코팝콘',1,13000,6155724),(5260275,'모듬튀김',5,40000,6155724),(5260276,'칠성사이다',4,12000,6155724),(5260277,'양파링',3,4800,61548),(5260278,'코카콜라',4,16000,61548),(5260279,'오렌지쥬스',4,12000,6156955),(5260280,'순살치킨',4,48000,6156955),(5260281,'모듬튀김',9,72000,6156955),(5260282,'카라멜팝콘',5,60000,6156955),(5260283,'오렌지쥬스',1,3000,6158333),(5260284,'순살치킨',1,12000,6158333),(5260285,'일반팝콘',3,30000,6158333),(5260286,'오렌지쥬스',1,3000,615823),(5260287,'오렌지쥬스',4,12000,6152318),(5260288,'일반팝콘',4,40000,6152318),(5260289,'나쵸',3,15000,6152318),(5260290,'카라멜팝콘',6,72000,6159757),(5260291,'일반팝콘',4,40000,6159757),(5260292,'칠성사이다',1,3000,615438),(5260293,'양파링',1,1600,6153175),(5260294,'나쵸',2,10000,615112),(5260295,'코카콜라',2,8000,6153277),(5260296,'코카콜라',1,4000,6151989),(5260297,'양파링',1,1500,6151696),(5260298,'오렌지쥬스',1,3000,6151696),(5260299,'양파링',1,1500,6155073),(5260300,'나쵸',1,5000,6157110),(5260301,'나쵸',1,5000,6157264),(5260302,'양파링',1,1500,6153626),(5260303,'양파링',1,1500,6153964),(5260304,'나쵸',1,5000,6154147),(5260305,'양파링',1,1500,6157125),(5260306,'나쵸',1,5000,6156205),(5260307,'오렌지쥬스',1,3000,6152177),(5260308,'카라멜팝콘',1,12000,6152177),(5260309,'오렌지쥬스',1,3000,6152018),(5260310,'오렌지쥬스',1,3000,6156645),(5260311,'새우깡',3,1500,6156645),(5260312,'일반팝콘',1,10000,6156645),(5260313,'새우깡',10,5000,6151104),(5260314,'오렌지쥬스',1,3000,6152566),(5260315,'새우깡',10,5000,6152566),(5260316,'새우깡',5,2500,6158934),(5260317,'오렌지쥬스',3,9000,6158934),(5260318,'양파링',4,6000,6158691),(5260319,'오렌지쥬스',1,3000,6155999),(5260320,'오렌지쥬스',1,3000,6154318),(5260321,'나쵸',1,5000,6155519),(5260322,'오렌지쥬스',1,3000,6155650),(5260323,'오렌지쥬스',3,9000,6157956),(5260324,'오렌지쥬스',1,3000,6155878),(5260325,'오렌지쥬스',1,3000,6159503),(5260326,'오렌지쥬스',1,3000,6157643),(5260327,'오렌지쥬스',1,3000,6158716),(5260328,'오렌지쥬스',1,3000,6156873),(5260329,'오렌지쥬스',1,3000,615145),(5260330,'오렌지쥬스',1,3000,6153641),(5260331,'오렌지쥬스',1,3000,6157852),(5260332,'오렌지쥬스',1,3000,6152253),(5260333,'오렌지쥬스',6,18000,6157689),(5260334,'일반팝콘',5,50000,6157689),(5260335,'양파링',6,9000,6157689),(5260336,'나쵸',5,25000,6157689),(5260337,'양파링',1,1500,6153310),(5260338,'오렌지쥬스',1,3000,6151177),(5260339,'오렌지쥬스',1,3000,6157024),(5260340,'나쵸',4,20000,615729),(5260341,'오렌지쥬스',1,3000,6158479),(5260342,'오렌지쥬스',1,3000,6155647);
/*!40000 ALTER TABLE `snorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `S_Index` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `ps` varchar(20) DEFAULT NULL,
  `sid` varchar(30) DEFAULT NULL,
  `spw` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`S_Index`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'김영호','점장','201945023','1234'),(2,'곽권빈','매니저','201945025','1234'),(3,'이웅회','매니저','201945022','1234'),(4,'간영훈','부점장','202145024','1234'),(5,'홍길동','사원','202245001','1234'),(29,'김영호','사원','201945023','1234'),(35,'곽권빈','매니저','201945025','1234'),(36,'이웅회','매니저','201945022','1234'),(37,'간영훈','부점장','202145024','1234');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `TicketId` int NOT NULL AUTO_INCREMENT,
  `RunningId` int NOT NULL,
  `MvWhen` date NOT NULL,
  `seat` varchar(10) NOT NULL,
  `mvPrice` int NOT NULL,
  `payMethod` varchar(100) NOT NULL,
  `member` int NOT NULL,
  `bookwho` varchar(100) NOT NULL,
  PRIMARY KEY (`TicketId`),
  KEY `ticket_FK_1` (`member`),
  KEY `ticket_FK_2` (`RunningId`),
  CONSTRAINT `ticket_FK_1` FOREIGN KEY (`member`) REFERENCES `customer` (`member`),
  CONSTRAINT `ticket_FK_2` FOREIGN KEY (`RunningId`) REFERENCES `mvtime` (`RunningId`)
) ENGINE=InnoDB AUTO_INCREMENT=111111112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (11111111,10,'2022-05-27','a1',19000,'카드',4,'성인:1 청소년:1'),(11111112,10,'2022-05-27','b3',19000,'카드',4,'성인:1 청소년:1'),(11111113,10,'2022-05-27','c5',19000,'카드',4,'성인:1 청소년:1'),(11111114,10,'2022-05-27','d2',19000,'카드',4,'성인:1 청소년:1'),(11111115,39,'2022-06-20','e3, e4',20000,'카드',4,'성인 2'),(11111116,39,'2022-06-20','d3, d4',20000,'카드',4,'성인 2');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'movie'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-15 23:31:25
