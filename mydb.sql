CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actor` (
  `idActor` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `anoNacimiento` int DEFAULT NULL,
  `premioOscar` tinyint DEFAULT NULL,
  PRIMARY KEY (`idActor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (1,'Tom','Hanks',1956,1),(2,'Robert','Downey Jr.',1965,1),(3,'Will','Smith',1968,1),(4,'Leonardo','Di Caprio',1974,1),(5,'Brad','Pitt',1963,1);
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genero` (
  `idGenero` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (1,'Comedia'),(2,'Acción'),(3,'Drama'),(4,'Terror'),(5,'Ciencia Ficción');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `streaming`
--

DROP TABLE IF EXISTS `streaming`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `streaming` (
  `idStreaming` int NOT NULL AUTO_INCREMENT,
  `nombreServicio` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idStreaming`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `streaming`
--

LOCK TABLES `streaming` WRITE;
/*!40000 ALTER TABLE `streaming` DISABLE KEYS */;
INSERT INTO `streaming` VALUES (1,'Netflix'),(2,'Amazon Prime Video'),(3,'Disney+'),(4,'HBO Max');
/*!40000 ALTER TABLE `streaming` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelicula`
--

DROP TABLE IF EXISTS `pelicula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pelicula` (
  `idPelicula` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `director` varchar(100) NOT NULL,
  `anoPublicacion` int NOT NULL,
  `rating` decimal(3,1) NOT NULL,
  `boxOffice` decimal(10,2) NOT NULL,
  `duracion` varchar(45) NOT NULL,
  `premioOscar` tinyint(1) NOT NULL,
  `idGenero` int NOT NULL,
  `idStreaming` int NOT NULL,
  PRIMARY KEY (`idPelicula`),
  KEY `fk_pelicula_genero_idx` (`idGenero`),
  KEY `fk_pelicula_streaming_idx` (`idStreaming`),
  CONSTRAINT `fk_pelicula_genero` FOREIGN KEY (`idGenero`) REFERENCES `genero` (`idGenero`),
  CONSTRAINT `fk_pelicula_streaming` FOREIGN KEY (`idStreaming`) REFERENCES `streaming` (`idStreaming`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelicula`
--

LOCK TABLES `pelicula` WRITE;
/*!40000 ALTER TABLE `pelicula` DISABLE KEYS */;
INSERT INTO `pelicula` VALUES (1,'Iron Man','Jon Favreau',2008,7.9,585.80,'126 min',0,2,3),(2,'Forrest Gump','Robert Zemeckis',1994,8.8,678.20,'142 min',1,3,1),(3,'I Am Legend','Francis Lawrence',2007,7.2,585.30,'101 min',0,5,4);
/*!40000 ALTER TABLE `pelicula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `protagonistas`
--

DROP TABLE IF EXISTS `protagonistas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `protagonistas` (
  `idPelicula` int NOT NULL,
  `idActor` int NOT NULL,
  `personaje` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPelicula`,`idActor`),
  KEY `fk_protagonistas_actor_idx` (`idActor`),
  CONSTRAINT `fk_protagonistas_actor` FOREIGN KEY (`idActor`) REFERENCES `actor` (`idActor`),
  CONSTRAINT `fk_protagonistas_pelicula` FOREIGN KEY (`idPelicula`) REFERENCES `pelicula` (`idPelicula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `protagonistas`
--

LOCK TABLES `protagonistas` WRITE;
/*!40000 ALTER TABLE `protagonistas` DISABLE KEYS */;
INSERT INTO `protagonistas` VALUES (1,2,'Tony Stark'),(2,1,'Forrest Gump'),(3,3,'Robert Neville');
/*!40000 ALTER TABLE `protagonistas` ENABLE KEYS */;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-22 16:53:31