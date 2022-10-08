CREATE DATABASE IF NOT EXISTS bancoDB CHARACTER SET latin1 COLLATE latin1_swedish_ci;

USE bancoDB;

CREATE TABLE IF NOT EXISTS `bancoDB`.`clientes` (
  `cliente_id` VARCHAR(255) NOT NULL,
  `contrasenia` VARCHAR(255) NULL DEFAULT NULL,
  `estado` BIT(1) NULL DEFAULT NULL,
  `id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_nhdbltv7dnhp65s6wjb411goq` (`cliente_id` ASC) VISIBLE)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb3;


CREATE TABLE IF NOT EXISTS `bancoDB`.`cuentas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `estado` BIT(1) NULL DEFAULT NULL,
  `num_cuenta` VARCHAR(255) NOT NULL,
  `saldo_inicial` DOUBLE NULL DEFAULT NULL,
  `tipo` VARCHAR(255) NULL DEFAULT NULL,
  `cliente_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_p7d8dh3pdg1en313rho2cwdne` (`num_cuenta` ASC) VISIBLE,
  INDEX `FK65yk2321jpusl3fk96lqehrli` (`cliente_id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


CREATE TABLE IF NOT EXISTS `bancoDB`.`movimientos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL DEFAULT NULL,
  `num_cuenta` BIGINT NULL DEFAULT NULL,
  `saldo` DOUBLE NULL DEFAULT NULL,
  `tipo_movimiento` VARCHAR(255) NULL DEFAULT NULL,
  `valor` DOUBLE NULL DEFAULT NULL,
  `cuenta_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK4moe88hxuohcysas5h70mdc09` (`cuenta_id` ASC) VISIBLE)
ENGINE = MyISAM
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


CREATE TABLE IF NOT EXISTS `bancoDB`.`personas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `direccion` VARCHAR(255) NULL DEFAULT NULL,
  `edad` INT NULL DEFAULT NULL,
  `genero` VARCHAR(255) NULL DEFAULT NULL,
  `identificacion` VARCHAR(255) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  `telefono` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;

INSERT INTO `personas` VALUES (1,'Dirección 1',34,'Masculino','123654789','Axel Cuateta','2461265184'),
(2,'Dirección 2',20,'Femenino','123654789','Estela Gonzalez','2461265184'),
(3,'Dirección 3',20,'Femenino','123654789','Eva García','2461265184'),
(4,'Dirección 4',50,'Masculino','123654789','Reynaldo Xochicale','2461265184');

INSERT INTO `clientes` VALUES ('11111111','password',_binary '',1),
('22222222','password',_binary '',2),
('33333333','password',_binary '',3),
('44444444','password',_binary '',4);

INSERT INTO `cuentas` VALUES (1,_binary '','11111111',1000,'Corriente',1),
(2,_binary '','22222222',5000,'Corriente',2),
(3,_binary '','33333333',6000,'Ahorro',3),
(4,_binary '','44444444',6500,'Ahorro',4);

INSERT INTO `movimientos` VALUES (1,'2022-10-07',111111,500,'Retiro',-500,1),
(2,'2022-10-07',222222,4400,'Retiro',-600,2),
(3,'2022-10-07',333333,6100,'Deposito',100,3),
(4,'2022-10-07',444444,6600,'Deposito',100,4);



