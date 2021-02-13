SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema baechoo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `baechoo` DEFAULT CHARACTER SET utf8mb4 ;
USE `baechoo` ;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `baechoo`.`user` ;

CREATE TABLE IF NOT EXISTS `baechoo`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(40) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `baechoo`.`role`;
CREATE TABLE `role` (
	`role_id` int NOT NULL,
	`name` varchar(45) NOT NULL,
	PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `role` WRITE;
INSERT INTO `role` VALUES (1,'ROLE_USER');
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `baechoo`.`user_role`;
CREATE TABLE `user_role` (
	`user_id` int NOT NULL,
	`role_id` int NOT NULL,
	PRIMARY KEY (`user_id`,`role_id`),
	KEY `role_id` (`role_id`),
	CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
	CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4;

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
  `purchase_check` TINYINT NOT NULL default 0,
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