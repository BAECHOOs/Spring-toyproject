
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;



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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;



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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

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

