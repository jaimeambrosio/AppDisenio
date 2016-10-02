-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema diseniodb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `diseniodb` ;

-- -----------------------------------------------------
-- Schema diseniodb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `diseniodb` DEFAULT CHARACTER SET utf8 ;
USE `diseniodb` ;

-- -----------------------------------------------------
-- Table `diseniodb`.`carrera`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diseniodb`.`carrera` (
  `codCarrera` INT(11) NOT NULL AUTO_INCREMENT,
  `nombreCarrera` VARCHAR(150) NULL DEFAULT NULL,
  `descripcion` VARCHAR(300) NULL DEFAULT NULL,
  PRIMARY KEY (`codCarrera`))
ENGINE = InnoDB
AUTO_INCREMENT = 41
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `diseniodb`.`curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diseniodb`.`curso` (
  `codCurso` VARCHAR(10) NOT NULL,
  `nombreCurso` VARCHAR(100) NULL DEFAULT NULL,
  `nivel` INT(11) NULL DEFAULT NULL,
  `creditos` INT(11) NULL DEFAULT NULL,
  `flgActivo` TINYINT(1) NULL DEFAULT NULL,
  `codCarrera` INT(11) NOT NULL,
  PRIMARY KEY (`codCurso`),
  INDEX `fk_Curso_Carrera_idx` (`codCarrera` ASC),
  CONSTRAINT `fk_Curso_Carrera`
    FOREIGN KEY (`codCarrera`)
    REFERENCES `diseniodb`.`carrera` (`codCarrera`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `diseniodb`.`distrito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diseniodb`.`distrito` (
  `codDistrito` INT(11) NOT NULL AUTO_INCREMENT,
  `nombreDistrito` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`codDistrito`))
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `diseniodb`.`tipoproducto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diseniodb`.`tipoproducto` (
  `codTipoProducto` INT(11) NOT NULL AUTO_INCREMENT,
  `nombreTipoProd` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`codTipoProducto`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `diseniodb`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diseniodb`.`producto` (
  `codProducto` VARCHAR(20) NOT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `precio` DOUBLE NULL DEFAULT NULL,
  `fechaRegistro` DATE NULL DEFAULT NULL,
  `flgActivo` TINYINT(1) NULL DEFAULT NULL,
  `codTipoProducto` INT(11) NOT NULL,
  PRIMARY KEY (`codProducto`),
  INDEX `fk_Producto_TipoProducto1_idx` (`codTipoProducto` ASC),
  CONSTRAINT `fk_Producto_TipoProducto1`
    FOREIGN KEY (`codTipoProducto`)
    REFERENCES `diseniodb`.`tipoproducto` (`codTipoProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `diseniodb`.`sede`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diseniodb`.`sede` (
  `codSede` VARCHAR(10) NOT NULL,
  `direccion` VARCHAR(300) NULL DEFAULT NULL,
  `descripcion` VARCHAR(500) NULL DEFAULT NULL,
  `flgActivo` TINYINT(1) NULL DEFAULT NULL,
  `codDistrito` INT(11) NOT NULL,
  PRIMARY KEY (`codSede`),
  INDEX `fk_Sede_Distrito1_idx` (`codDistrito` ASC),
  CONSTRAINT `fk_Sede_Distrito1`
    FOREIGN KEY (`codDistrito`)
    REFERENCES `diseniodb`.`distrito` (`codDistrito`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `diseniodb`.`tipousuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diseniodb`.`tipousuario` (
  `codTipoUsuario` INT(11) NOT NULL,
  `nombreTipoUsuario` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`codTipoUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `diseniodb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diseniodb`.`usuario` (
  `codUsuario` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `apellido` VARCHAR(45) NULL DEFAULT NULL,
  `dni` VARCHAR(45) NULL DEFAULT NULL,
  `fechaNacimiento` DATE NULL DEFAULT NULL,
  `sexo` TINYINT(1) NULL DEFAULT NULL,
  `numCelular` VARCHAR(45) NULL DEFAULT NULL,
  `correo` VARCHAR(100) NOT NULL,
  `contrasenia` VARCHAR(100) NOT NULL,
  `flgActivo` TINYINT(1) NULL DEFAULT NULL,
  `fechaHoraBloqueo` DATETIME NULL DEFAULT NULL,
  `codTipoUsuario` INT(11) NOT NULL,
  `cantErrorIngreso` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`codUsuario`),
  INDEX `fk_Usuario_TipoUsuario1_idx` (`codTipoUsuario` ASC),
  CONSTRAINT `fk_Usuario_TipoUsuario1`
    FOREIGN KEY (`codTipoUsuario`)
    REFERENCES `diseniodb`.`tipousuario` (`codTipoUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `diseniodb`.`Alumno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diseniodb`.`Alumno` (
  `colegioProcedencia` VARCHAR(100) NULL,
  `codAlumno` VARCHAR(15) NOT NULL,
  `codCarrera` INT(11) NOT NULL,
  `codSede` VARCHAR(10) NOT NULL,
  INDEX `fk_Alumno_usuario1_idx` (`codAlumno` ASC),
  PRIMARY KEY (`codAlumno`),
  INDEX `fk_Alumno_carrera1_idx` (`codCarrera` ASC),
  INDEX `fk_Alumno_sede1_idx` (`codSede` ASC),
  CONSTRAINT `fk_Alumno_usuario1`
    FOREIGN KEY (`codAlumno`)
    REFERENCES `diseniodb`.`usuario` (`codUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumno_carrera1`
    FOREIGN KEY (`codCarrera`)
    REFERENCES `diseniodb`.`carrera` (`codCarrera`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumno_sede1`
    FOREIGN KEY (`codSede`)
    REFERENCES `diseniodb`.`sede` (`codSede`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diseniodb`.`docente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `diseniodb`.`docente` (
  `isTiempoCompleto` TINYINT(1) NULL,
  `codDocente` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`codDocente`),
  CONSTRAINT `fk_docente_usuario1`
    FOREIGN KEY (`codDocente`)
    REFERENCES `diseniodb`.`usuario` (`codUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
