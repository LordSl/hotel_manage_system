
-- MySQL dump 10.13  Distrib 5.7.19, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: Hotel
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `Coupon`
--

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `Coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `hotelId` int(11) DEFAULT '-1',
  `couponType` int(11) NOT NULL,
  `couponName` varchar(255) NOT NULL,
  `target_money` int(11) DEFAULT NULL,
  `target_room` int(11) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `discount_money` int(11) DEFAULT NULL,
  `forHotelVip` int(2) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Coupon`
--
BEGIN;
/*!40000 ALTER TABLE `Coupon` DISABLE KEYS */;
INSERT INTO `Coupon` VALUES (2,'满500-100优惠',2,3,'满减优惠券',500,0,0,1,NULL,NULL,100,0);
/*!40000 ALTER TABLE `Coupon` ENABLE KEYS */;
COMMIT;

--
-- Table structure for table `Hotel`
--

DROP TABLE IF EXISTS `Hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hotelName` varchar(255) NOT NULL,
  `hotelDescription` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `bizRegion` varchar(255) DEFAULT NULL,
  `hotelStar` varchar(255) DEFAULT NULL,
  `phoneNum` varchar(11) DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL,
  `hotelImg` varchar(255) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hotel`
--

BEGIN;
/*!40000 ALTER TABLE `Hotel` DISABLE KEYS */;
INSERT INTO `Hotel` VALUES (1,'汉庭酒店','花园连体别墅酒店理念设计的豪华酒店','南京市鼓楼区珠江路28号','XiDan','Five',1829373819,4.8,NULL,'https://dimg11.c-ctrip.com/images/200a0v000000jzsw3EDFD_R_130_130.jpg',116.397428,39.90923);
INSERT INTO `Hotel` VALUES (2,'儒家酒店','儒家酒店邀您共享古城南京的怀旧时刻','南京市鼓楼区珠江路68号','XiDan','Four',1829373819,4.8,3,'https://dimg11.c-ctrip.com/images/20030h00000095o40DFDA_R_130_130.jpg',116.397428,38.90923);
INSERT INTO `Hotel` VALUES (3,'桂圆酒店','新装修！欢迎入住','南京市栖霞区珠江路268号','XiDan','Three',1829553719,4.8,NULL,'https://dimg11.c-ctrip.com/images/20020r000000h88sjDEF9_R_130_130.jpg',116.397428,37.90923);
INSERT INTO `Hotel` VALUES (4,'丽呈睿轩酒店','Welcome to SanYa!','三亚市万达广场含南路263号','万达广场','Five',1829373819,4.9,NULL,'https://tse2-mm.cn.bing.net/th/id/OIP.djuTY-7i4CjU4vxXuklaJwHaGs?pid=Api&rs=1',109.500000,18.250000);
INSERT INTO `Hotel` VALUES (5,'绿城度假酒店','夜入绿城，日出长城','北京市长城路99号','长城','Five',1829373819,4.9,NULL,'https://tse2-mm.cn.bing.net/th/id/OIP.0VMO62WWFun61xE-XXOIAgHaLH?pid=Api&rs=1',109.500000,18.250000);
/*!40000 ALTER TABLE `Hotel` ENABLE KEYS */;
COMMIT;

--
-- Table structure for table `OrderList`
--

DROP TABLE IF EXISTS `OrderList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderList` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `hotelId` int(11) DEFAULT NULL,
  `hotelName` varchar(255) DEFAULT NULL,
  `checkInDate` varchar(255) DEFAULT NULL,
  `checkOutDate` varchar(255) DEFAULT NULL,
  `roomId` int(11) DEFAULT NULL,
  `roomType` varchar(255) DEFAULT NULL,
  `roomNum` int(255) DEFAULT NULL,
  `peopleNum` int(255) DEFAULT NULL,
  `haveChild` tinytext,
  `createDate` varchar(255) DEFAULT NULL,
  `price` decimal(65,0) DEFAULT NULL,
  `clientName` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `orderState` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderList`
--

/*!40000 ALTER TABLE `OrderList` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderList` ENABLE KEYS */;

-- Table structure for table `Appeal`
DROP TABLE IF EXISTS `Appeal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Appeal` (
                             `orderId` int(11) NOT NULL,
                             `appealMessage` varchar(255) DEFAULT NULL,
                             `appealStatus` varchar(32)DEFAULT  NULL,
                             PRIMARY KEY (`orderId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `Appeal`
--
/*!40000 ALTER TABLE `Appeal` DISABLE KEYS */;
/*!40000 ALTER TABLE `Appeal` ENABLE KEYS */;


-- Table structure for table `VIPHotel`
DROP TABLE IF EXISTS `VIPHotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VIPHotel` (
                          `vid` int(11) NOT NULL AUTO_INCREMENT,
                          `userId` int(11) DEFAULT NULL,
                          `hotelId` int(11) DEFAULT  NULL,
                          PRIMARY KEY (`vid`)
) ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `HotelVIP`
--
/*!40000 ALTER TABLE `VIPHotel` DISABLE KEYS */;
/*!40000 ALTER TABLE `VIPHotel` ENABLE KEYS */;


-- Table structure for table `HPic`
DROP TABLE IF EXISTS `HPic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HPic` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `imgUrl` varchar(255) DEFAULT NULL,
                            `hotelId` int(11) DEFAULT  NULL,
                            `custom` boolean DEFAULT NULL,
                            `commentId` int(11) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `HotelVIP`
--
/*!40000 ALTER TABLE `HPic` DISABLE KEYS */;
/*!40000 ALTER TABLE `HPic` ENABLE KEYS */;
BEGIN;
/*!40000 ALTER TABLE `HPic` DISABLE KEYS */;
INSERT INTO `HPic` VALUES (1,'https://m.tuniucdn.com/fb2/t1/G5/M00/78/5F/Cii-s114o-SIR8olAAKZEEcauY8AAaVLQGoxooAApko499_w950_h600_c1_t0.jpg',1,0,0);
INSERT INTO `HPic` VALUES (2,'https://m.tuniucdn.com/fb3/s1/2n9c/MXX7MVgAcCGnvgnbESNzXhPtKUj_w950_h600_c1_t0.jpg',1,0,0);
INSERT INTO `HPic` VALUES (4,'https://m.tuniucdn.com/fb3/s1/2n9c/WacX19xfya3JzQ2CbZGTf2aK63c_w950_h600_c1_t0.jpg',1,0,0);
INSERT INTO `HPic` VALUES (5,'https://tse2-mm.cn.bing.net/th/id/OIP.R27K-lRE_KO-0El_KxhXkwHaFU?w=282&h=202&c=7&o=5&dpr=1.25&pid=1.7',1,0,0);
INSERT INTO `HPic` VALUES (6,'https://tse2-mm.cn.bing.net/th/id/OIP.Ssqnx18WJTu2dsqAtVDn4QHaFj?w=270&h=202&c=7&o=5&dpr=1.25&pid=1.7',1,0,0);
INSERT INTO `HPic` VALUES (7,'https://tse1-mm.cn.bing.net/th/id/OIP.L0YCpBPE51xsJbX-oyd89wHaEw?pid=Api&rs=1',1,0,0);
INSERT INTO `HPic` VALUES (8,'https://m.tuniucdn.com/fb3/s1/2n9c/2EUGN2MMSGRFXJnhmuSqVeBRuLMK_w950_h600_c1_t0.jpg',1,0,0);
/*!40000 ALTER TABLE `HPic` ENABLE KEYS */;
COMMIT;



-- Table structure for table `CreditChange`
DROP TABLE IF EXISTS `CreditChange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CreditChange` (
                                 `id` int(11) NOT NULL AUTO_INCREMENT,
                                 `userId` int(11) DEFAULT NULL,
                                 `timeChanged` varchar(50) DEFAULT NULL,
                                 `reason` varchar(50) DEFAULT NULL,
                                 `valueChanged` double(255,0) DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `Appeal`
--
/*!40000 ALTER TABLE `CreditChange` DISABLE KEYS */;
/*!40000 ALTER TABLE `CreditChange` ENABLE KEYS */;





-- Table structure for table `Advertisement`
DROP TABLE IF EXISTS `Advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Advertisement` (
                           `adId` int(11) NOT NULL AUTO_INCREMENT,
                          `adImgUrl` varchar(255) DEFAULT NULL,
                           `hotelId` int(11) DEFAULT -1,
                          PRIMARY KEY (`adId`)
) ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `Appeal`
--
/*!40000 ALTER TABLE `Advertisement` DISABLE KEYS */;
/*!40000 ALTER TABLE `Advertisement` ENABLE KEYS */;

BEGIN;
/*!40000 ALTER TABLE `Advertisement` DISABLE KEYS */;
INSERT INTO `Advertisement` VALUES (1,'https://m2.tuniucdn.com/fb2/t1/G6/M00/9E/D7/Cii-TF6XtL6IbQQgAAeWa8je0mwAAMVnAOVvy4AB5aD80.jpeg',-1);
INSERT INTO `Advertisement` VALUES (2,'https://dimg04.c-ctrip.com/images/zg0n1g000001hi84zB7D6.jpg',-1);
INSERT INTO `Advertisement` VALUES (3,'https://pic5.40017.cn/i/ori/Od19HjlH5S.jpg',-1);
INSERT INTO `Advertisement` VALUES (4,'http://static.bestwehotel.com/opt/static/chameleon/image/20200401/b122f772-70db-412b-abf0-56156c6ffcee.jpg',-1);
/*!40000 ALTER TABLE `Advertisement` ENABLE KEYS */;
COMMIT;


--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `curNum` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `roomType` varchar(50) DEFAULT NULL,
  `roomImg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

BEGIN;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` VALUES (2,199,20,20,1,'大床房','https://s.tuniu.net/qn/image/f2/ba125369df19a8698935cb06ded9cfe9/6038cc87-7984-4999-abfb-b6250fd6b122.jpeg?imageView2/1/w/450/h/300'),(3,299,30,30,1,'双床房','https://s.tuniu.net/qn/image/f2/ba125369df19a8698935cb06ded9cfe9/280f57b6-6f25-4866-8aeb-c3aa2283b523.jpeg?imageView2/1/w/450/h/300'),(4,399,10,10,1,'家庭房','https://s.tuniu.net/qn/image/f2/ba125369df19a8698935cb06ded9cfe9/f70abda4-c1d7-43f9-9cae-213dd455724a.jpeg?imageView2/1/w/450/h/300');
INSERT INTO `Room` VALUES (5,169,20,20,2,'大床房','https://m.tuniucdn.com/fb2/t1/G6/M00/68/30/Cii-TF3iquiIanOHAAIj45slzakAAHuFAH-F38AAiP7525_w640_h480_c1_t0.jpg'),(6,299,30,30,2,'双床房','https://m.tuniucdn.com/fb3/s1/2n9c/2tciRQjV7Bgtj3s27HHCXr53aZ8n_w950_h600_c1_t0.jpg'),(7,399,10,10,2,'家庭房','https://m.tuniucdn.com/fb3/s1/2n9c/3Eo8dZmKnjBC2WN1H8aJekBFND3c_w950_h600_c1_t0.jpg');
INSERT INTO `Room` VALUES (8,137,20,20,3,'大床房','https://m.tuniucdn.com/fb3/s1/2n9c/25RUnjqa9DXCaTDsxmFpSbe574Mg_w950_h600_c1_t0.jpg');
INSERT INTO `Room` VALUES (11,164,20,20,4,'大床房','https://m.tuniucdn.com/fb3/s1/2n9c/3Cz5B37TTCRbA1faZMRTopUsn39A_w950_h600_c1_t0.jpg');
INSERT INTO `Room` VALUES (14,175,20,20,5,'大床房','https://m.tuniucdn.com/fb3/s1/2n9c/47DaEckjSDhkXETk6RDisVqjsqR6_w950_h600_c1_t0.jpg'),(15,299,30,30,5,'双床房','https://m.tuniucdn.com/fb3/s1/2n9c/3VtAW7RfsR2cnWLUxJWJUvKEry5q_w950_h600_c1_t0.jpg');
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
COMMIT;

--
-- Table structure for table `Comment`
--
DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comment` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `user_id` int(11) DEFAULT NULL,
                        `userName` varchar(255) DEFAULT NULL,
                        `userAvatar` varchar(255) DEFAULT NULL,
                        `hotel_id` int(11) DEFAULT NULL,
                        `content` varchar(255) DEFAULT NULL,
                        `favor` int(11) DEFAULT NULL,
                        `create_time` DATETIME DEFAULT NULL,
                        `dislike` int(11) DEFAULT NULL,
                        `score` double DEFAULT NULL,
                        `imgUrl`varchar(255) DEFAULT  Null,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table 'Comment`
--
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;


--
-- Table structure for table `Comment`
--
DROP TABLE IF EXISTS `Reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reply` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `comment_id` int(11) DEFAULT NULL,
                           `from_id` int(11) DEFAULT NULL,
                           `fromName` varchar(255) DEFAULT NULL,
                           `fromAvatar` varchar(255) DEFAULT NULL,
                           `content` varchar(255) DEFAULT NULL,
                           `favor` int(11) DEFAULT NULL,
                           `create_time` DATETIME DEFAULT NULL,
                           `to_id` int(11) DEFAULT NULL,
                           `toName` varchar(255) DEFAULT NULL,
                           `toAvatar` varchar(255) DEFAULT NULL,
                           `state` int(11) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reply`
--
/*!40000 ALTER TABLE `Reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reply` ENABLE KEYS */;

--
-- Table structure for table `Status`
--
DROP TABLE IF EXISTS `Status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Status` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `reply_id` int(11) DEFAULT NULL,
                           `user_id` int(11) DEFAULT NULL,
                           `comment_id` int(11) DEFAULT NULL,
                           `state` int(11) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Status`
--
/*!40000 ALTER TABLE `Status` DISABLE KEYS */;
/*!40000 ALTER TABLE `Status` ENABLE KEYS */;
--
-- Table structure for table `User`
--

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL UNIQUE ,
  `password` varchar(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `credit` double(255,0) DEFAULT NULL,
  `usertype` varchar(255) DEFAULT NULL,
  `avatarurl` varchar(255) DEFAULT NULL,
  `isWebVIP` int(2) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

BEGIN;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'C1@qq.com','123456','客户一号',NULL,100,'Client','https://avatars0.githubusercontent.com/Marchlhw',0);
INSERT INTO `User` VALUES (2,'C2@qq.com','123456','客户二号',NULL,100,'Client','https://tse2-mm.cn.bing.net/th/id/OIP.mnD-3m6HyLjvzRvICjgsiwAAAA?w=179&h=180&c=7&o=5&dpr=1.25&pid=1.7',0);
INSERT INTO `User` VALUES (3,'HM1@qq.com','123456','酒管一号',NULL,100,'HotelManager','https://tse4-mm.cn.bing.net/th/id/OIP.AeM4lYkWtJ59GNeD6Wf6RQAAAA?pid=Api&rs=1',0);
INSERT INTO `User` VALUES (4,'M1@qq.com','123456','网管一号',NULL,100,'Manager','https://img-blog.csdnimg.cn/20200630042151711.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ0NzYxNTA3,size_16,color_FFFFFF,t_70',0);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
COMMIT;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-12 10:08:42
SET FOREIGN_KEY_CHECKS = 1;