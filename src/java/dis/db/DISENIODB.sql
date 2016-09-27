-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema DISENIODB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `DISENIODB` ;

-- -----------------------------------------------------
-- Schema DISENIODB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DISENIODB` DEFAULT CHARACTER SET utf8 ;
USE `DISENIODB` ;

-- -----------------------------------------------------
-- Table `DISENIODB`.`TipoUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DISENIODB`.`TipoUsuario` (
  `codTipoUsuario` INT NOT NULL,
  `nombreTipoUsuario` VARCHAR(45) NULL,
  PRIMARY KEY (`codTipoUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DISENIODB`.`Carrera`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DISENIODB`.`Carrera` (
  `codCarrera` INT NOT NULL,
  `nombreCarrera` VARCHAR(150) NULL,
  `descripcion` VARCHAR(300) NULL,
  PRIMARY KEY (`codCarrera`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DISENIODB`.`Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DISENIODB`.`Curso` (
  `codCurso` VARCHAR(10) NOT NULL,
  `nombreCurso` VARCHAR(100) NULL,
  `nivel` INT NULL,
  `creditos` INT NULL,
  `flgActivo` TINYINT(1) NULL,
  `codCarrera` INT NOT NULL,
  PRIMARY KEY (`codCurso`),
  INDEX `fk_Curso_Carrera_idx` (`codCarrera` ASC),
  CONSTRAINT `fk_Curso_Carrera`
    FOREIGN KEY (`codCarrera`)
    REFERENCES `DISENIODB`.`Carrera` (`codCarrera`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DISENIODB`.`Distrito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DISENIODB`.`Distrito` (
  `codDistrito` INT NOT NULL AUTO_INCREMENT,
  `nombreDistrito` VARCHAR(100) NULL,
  PRIMARY KEY (`codDistrito`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DISENIODB`.`Sede`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DISENIODB`.`Sede` (
  `codSede` VARCHAR(10) NOT NULL,
  `direccion` VARCHAR(300) NULL,
  `descripcion` VARCHAR(500) NULL,
  `flgActivo` TINYINT(1) NULL,
  `codDistrito` INT NOT NULL,
  PRIMARY KEY (`codSede`),
  INDEX `fk_Sede_Distrito1_idx` (`codDistrito` ASC),
  CONSTRAINT `fk_Sede_Distrito1`
    FOREIGN KEY (`codDistrito`)
    REFERENCES `DISENIODB`.`Distrito` (`codDistrito`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DISENIODB`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DISENIODB`.`Usuario` (
  `codUsuario` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `dni` VARCHAR(45) NULL,
  `fechaNacimiento` DATE NULL,
  `sexo` TINYINT(1) NULL,
  `numCelular` VARCHAR(45) NULL,
  `correo` VARCHAR(100) NULL,
  `flgActivo` TINYINT(1) NULL,
  `colegioProcedencia` VARCHAR(100) NULL,
 `fechaHoraBloqueo` DATE NULL,
  `codTipoUsuario` INT NOT NULL,
  PRIMARY KEY (`codUsuario`),
  INDEX `fk_Usuario_TipoUsuario1_idx` (`codTipoUsuario` ASC),
  CONSTRAINT `fk_Usuario_TipoUsuario1`
    FOREIGN KEY (`codTipoUsuario`)
    REFERENCES `DISENIODB`.`TipoUsuario` (`codTipoUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DISENIODB`.`TipoProducto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DISENIODB`.`TipoProducto` (
  `codTipoProducto` INT NOT NULL,
  `nombreTipoProd` VARCHAR(100) NULL,
  PRIMARY KEY (`codTipoProducto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DISENIODB`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DISENIODB`.`Producto` (
  `codProducto` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `precio` DOUBLE NULL,
  `fechaRegistro` DATE NULL,
  `flgActivo` TINYINT(1) NULL,
  `codTipoProducto` INT NOT NULL,
  PRIMARY KEY (`codProducto`),
  INDEX `fk_Producto_TipoProducto1_idx` (`codTipoProducto` ASC),
  CONSTRAINT `fk_Producto_TipoProducto1`
    FOREIGN KEY (`codTipoProducto`)
    REFERENCES `DISENIODB`.`TipoProducto` (`codTipoProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
