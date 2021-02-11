
-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: baechoo
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like` (
                        `like_id` int NOT NULL AUTO_INCREMENT,
                        `user_id` int NOT NULL,
                        `product_id` int NOT NULL,
                        PRIMARY KEY (`like_id`,`user_id`,`product_id`),
                        UNIQUE KEY `like_id_UNIQUE` (`like_id`),
                        KEY `user_id_idx` (`user_id`),
                        KEY `like_fk2_idx` (`product_id`),
                        CONSTRAINT `like_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
                        CONSTRAINT `like_fk2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--

LOCK TABLES `like` WRITE;
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
/*!40000 ALTER TABLE `like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
                         `order_id` int NOT NULL AUTO_INCREMENT,
                         `user_id` int NOT NULL,
                         `product_id` int NOT NULL,
                         PRIMARY KEY (`order_id`,`user_id`,`product_id`),
                         KEY `order_fk1_idx` (`user_id`),
                         KEY `order_fk2_idx` (`product_id`),
                         CONSTRAINT `order_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
                         CONSTRAINT `order_fk2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
                           `product_id` int NOT NULL AUTO_INCREMENT,
                           `user_id` int NOT NULL,
                           `name` varchar(100) NOT NULL,
                           `price` int NOT NULL DEFAULT '0',
                           `description` varchar(400) DEFAULT NULL,
                           `picture_url` varchar(45) NOT NULL,
                           `upload_date` timestamp NOT NULL,
                           `update_date` timestamp NOT NULL,
                           `views` int NOT NULL DEFAULT '0',
                           `on_sale` tinyint(1) NOT NULL,
                           `like_count` int DEFAULT NULL,
                           PRIMARY KEY (`product_id`,`user_id`),
                           UNIQUE KEY `product_id_UNIQUE` (`product_id`),
                           KEY `user_id_idx` (`user_id`),
                           CONSTRAINT `product_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `role_id` int NOT NULL,
                        `name` varchar(45) NOT NULL,
                        PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `user_id` int NOT NULL AUTO_INCREMENT,
                        `nickname` varchar(40) NOT NULL,
                        `email` varchar(200) NOT NULL,
                        `password` varchar(200) NOT NULL,
                        `enabled` bit(1) NOT NULL,
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `user_id_UNIQUE` (`user_id`),
                        UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'hongil','hong@gmail.com','1google',_binary '\0'),(2,'test','test@naver.com','$2a$10$IAvlX8Fy8b0Msyqm5CUEmOECftR3kiztweWh64grRE/MVBoouTPf2',_binary ''),(3,'ttt2','ttt2@naver.com','$2a$10$8rr1rHGLk5UoYAGcc0rrBewRlg559EaRT/JytxPayhdjgBMo72zcW',_binary ''),(4,'tet','tet@naver.com','$2a$10$o3nGu1.PzWhBlMABmV.f5u1Gw2cfIncGgiGkShdWGXMEuwreodBsm',_binary ''),(5,'오상진','oh@test.com','$2a$10$iRkS0h1xtsIIwaeKSbUZ5uEwJDuNLuzepKocjse/bvavWJvrZR7QO',_binary ''),(6,'now','now@test.com','$2a$10$KA1hPW4UmKyupEZP9wxbAe3vs81JCA0vuP.qZQT6Fgv5at49.2MfG',_binary ''),(7,'yes@naver.com','yes@naver.com','$2a$10$KFIkGUhoDVteofMO/K.2Qu8y5KcDmfSSPfsXI9fvh38MuzRL9Uh9e',_binary ''),(8,'te','tfefeftt@ne.com','$2a$10$jffhYLvaAlnyGRyVMxAh4OUmNDYdme2waTcIdN/P9B64vF4yu2DcK',_binary ''),(9,'fo','fo@test.com','$2a$10$0c9t6G2O20fTh8Oq3Fd6XeEJCH5MVb9sej1HaH4mHSB2qjXRl74aC',_binary ''),(10,'aa','aa@naver.com','$2a$10$VeP/ud.2IVnLIfes1yLBuuS4ydwEH6ydWpukg/Os3p0RBFaL83V7a',_binary ''),(12,'tetr','now2@test.com','$2a$10$4GORA2j.d.8qTTdFOjAGlOr2VyOG9CxadzHqB0Co3Q0rB5V8.67x.',_binary ''),(13,'fd','nowefe@test.com','$2a$10$/5Wl.k84ELPyY7.WlgS9Nu8amOdSBwWnP95Stxy6zUQK0PzZhmz.2',_binary ''),(14,'ef','dsf@dasfhj.com','$2a$10$acTvpkRzgoVr/ThbHmHVDerbIma9nfQeEierlC1QmYuaVa6SA1BVq',_binary ''),(15,'해피라잎','fd@naver.com','$2a$10$K4eaYCM7xt4VrNFue2I9BOl2TaAah/BU6DvmQoLJ/ZyJ/HTlYsjRS',_binary '');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
                             `user_id` int NOT NULL,
                             `role_id` int NOT NULL,
                             PRIMARY KEY (`user_id`,`role_id`),
                             KEY `role_id` (`role_id`),
                             CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
                             CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(12,1),(13,1),(14,1),(15,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-11 17:08:02
=======
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema baechoo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema baechoo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `baechoo` DEFAULT CHARACTER SET utf8mb4 ;
USE `baechoo` ;

-- -----------------------------------------------------
-- Table `baechoo`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baechoo`.`user` ;

CREATE TABLE IF NOT EXISTS `baechoo`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(40) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `baechoo`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baechoo`.`product` ;

CREATE TABLE IF NOT EXISTS `baechoo`.`product` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `price` INT NOT NULL DEFAULT 0,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `picture_url` VARCHAR(255) NOT NULL,
  `upload_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `views` INT NOT NULL DEFAULT 0,
  `on_sale` TINYINT NOT NULL DEFAULT 1,
  `like_count` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`product_id`, `user_id`),
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC) VISIBLE,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `product_fk1`
    FOREIGN KEY (`user_id`)
    REFERENCES `baechoo`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `baechoo`.`like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baechoo`.`like` ;

CREATE TABLE IF NOT EXISTS `baechoo`.`like` (
  `like_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`like_id`, `user_id`, `product_id`),
  UNIQUE INDEX `like_id_UNIQUE` (`like_id` ASC) VISIBLE,
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `like_fk2_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `like_fk1`
    FOREIGN KEY (`user_id`)
    REFERENCES `baechoo`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `like_fk2`
    FOREIGN KEY (`product_id`)
    REFERENCES `baechoo`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `baechoo`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baechoo`.`order` ;

CREATE TABLE IF NOT EXISTS `baechoo`.`order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `user_id`, `product_id`),
  INDEX `order_fk1_idx` (`user_id` ASC) VISIBLE,
  INDEX `order_fk2_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `order_fk1`
    FOREIGN KEY (`user_id`)
    REFERENCES `baechoo`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `order_fk2`
    FOREIGN KEY (`product_id`)
    REFERENCES `baechoo`.`product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

