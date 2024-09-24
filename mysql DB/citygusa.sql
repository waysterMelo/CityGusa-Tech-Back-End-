-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: citygusa
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `analise_gusa`
--

DROP TABLE IF EXISTS `analise_gusa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `analise_gusa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` date NOT NULL,
  `cromo` varchar(255) DEFAULT NULL,
  `enxofre` varchar(255) DEFAULT NULL,
  `ferro` varchar(255) DEFAULT NULL,
  `fosforo` varchar(255) DEFAULT NULL,
  `manganes` varchar(255) DEFAULT NULL,
  `produto` varchar(255) DEFAULT NULL,
  `silicio` varchar(255) DEFAULT NULL,
  `titanium` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `analise_gusa`
--

LOCK TABLES `analise_gusa` WRITE;
/*!40000 ALTER TABLE `analise_gusa` DISABLE KEYS */;
INSERT INTO `analise_gusa` VALUES (3,'2024-08-30','0.02','0.111','95','0.25','1.02','GUSA','0.000','0.55'),(4,'2024-08-30','0.02','0.111','95','0.25','1.02','GUSA','0.000','0.55'),(6,'2024-09-10','3.21_','3.213','98','3.21','1.321','GUSA','3.212','3.213'),(7,'2024-09-10','1.321','2.132','98','3.21','3.213','GUSA','3.213','3.213'),(8,'2024-09-11','6.546','5.646','86','5.46','4.654','GUSA','6.446','4.654');
/*!40000 ALTER TABLE `analise_gusa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `analise_minerios`
--

DROP TABLE IF EXISTS `analise_minerios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `analise_minerios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `aluminio` double DEFAULT NULL,
  `created_at` date NOT NULL,
  `fechamento` double DEFAULT NULL,
  `ferro` double DEFAULT NULL,
  `fosforo` double DEFAULT NULL,
  `lote` varchar(255) DEFAULT NULL,
  `manganes` double DEFAULT NULL,
  `minerio` varchar(255) DEFAULT NULL,
  `patio` varchar(255) DEFAULT NULL,
  `ppc` double DEFAULT NULL,
  `silica` double DEFAULT NULL,
  `tonelada` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `analise_minerios`
--

LOCK TABLES `analise_minerios` WRITE;
/*!40000 ALTER TABLE `analise_minerios` DISABLE KEYS */;
INSERT INTO `analise_minerios` VALUES (1,4.65,'2024-09-18',107.76,56.45,4.654,'359',6.54,'Extrativa','95',6.54,4.65,0.006);
/*!40000 ALTER TABLE `analise_minerios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cadastro_minerios`
--

DROP TABLE IF EXISTS `cadastro_minerios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cadastro_minerios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` date NOT NULL,
  `frete` double DEFAULT NULL,
  `lote` varchar(255) DEFAULT NULL,
  `minerio` varchar(255) DEFAULT NULL,
  `patio` varchar(255) DEFAULT NULL,
  `transportador` varchar(255) DEFAULT NULL,
  `valor_tonelada` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cadastro_minerios`
--

LOCK TABLES `cadastro_minerios` WRITE;
/*!40000 ALTER TABLE `cadastro_minerios` DISABLE KEYS */;
INSERT INTO `cadastro_minerios` VALUES (1,'2024-09-20',150,'Lote123','Minério de Ferro','Patio A','Transportadora XYZ',350.75),(2,'2024-09-20',150,'Lote123','Minério de Ferro','Patio A','Transportadora XYZ',350.75),(3,'2024-09-20',150,'Lote123','Minério de Ferro','Patio A','Transportadora XYZ',350.75),(4,'2024-09-20',150,'Lote123','Minério de Ferro','Patio A','Transportadora XYZ',350.75),(5,'2024-09-20',13.21,'321231','extrativa','32231','Transportadora XYZ',312.13),(6,'2024-09-20',45.8,'359','bassari','d','tita',322),(7,'2024-09-23',45,'359','bassari','d','trans n',320),(8,'2024-09-23',150,'Lote123','Minério de Ferro','Patio A','Transportadora XYZ',350.75),(9,'2024-09-23',150,'Lote123','Minério de Ferro','Patio A','Transportadora XYZ',350.75);
/*!40000 ALTER TABLE `cadastro_minerios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargas_leito_de_fusao`
--

DROP TABLE IF EXISTS `cargas_leito_de_fusao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargas_leito_de_fusao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bauxita` int DEFAULT NULL,
  `calcareo` int DEFAULT NULL,
  `coque` int DEFAULT NULL,
  `data_atual` varchar(255) NOT NULL,
  `horas` varchar(255) DEFAULT NULL,
  `numero_da_carga` int DEFAULT NULL,
  `porcentagem` double DEFAULT NULL,
  `quantidade` double DEFAULT NULL,
  `secas` int DEFAULT NULL,
  `sucata_aco` int DEFAULT NULL,
  `sucata_gusa` int DEFAULT NULL,
  `tipo_minerio` varchar(255) DEFAULT NULL,
  `total_cargas` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargas_leito_de_fusao`
--

LOCK TABLES `cargas_leito_de_fusao` WRITE;
/*!40000 ALTER TABLE `cargas_leito_de_fusao` DISABLE KEYS */;
/*!40000 ALTER TABLE `cargas_leito_de_fusao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `controle_de_corridas`
--

DROP TABLE IF EXISTS `controle_de_corridas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `controle_de_corridas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `carga_fundida_ate` int DEFAULT NULL,
  `carga_fundida_de` int DEFAULT NULL,
  `carvao_kg` int DEFAULT NULL,
  `carvao_metros` decimal(38,2) DEFAULT NULL,
  `conchas` int DEFAULT NULL,
  `created_at` date NOT NULL,
  `escoria_fim` varchar(255) DEFAULT NULL,
  `escoria_inicio` varchar(255) DEFAULT NULL,
  `fe_gusa_kg` int DEFAULT NULL,
  `ferro` decimal(10,2) DEFAULT NULL,
  `fosforo` double DEFAULT NULL,
  `gusa_minuto` decimal(38,3) DEFAULT NULL,
  `hora_fim` varchar(255) DEFAULT NULL,
  `hora_inicio` varchar(255) DEFAULT NULL,
  `manganes` double DEFAULT NULL,
  `minutos` int DEFAULT NULL,
  `quantidade` int DEFAULT NULL,
  `real_tn` decimal(10,2) NOT NULL,
  `ritmo` double DEFAULT NULL,
  `silica` double DEFAULT NULL,
  `silicio_real` double DEFAULT NULL,
  `silicio_visual` double DEFAULT NULL,
  `sopradores1` int DEFAULT NULL,
  `sopradores2` int DEFAULT NULL,
  `sopradores3` int DEFAULT NULL,
  `sopradores4` int DEFAULT NULL,
  `sopradores5` int DEFAULT NULL,
  `temperatura` decimal(38,0) DEFAULT NULL,
  `tempo_corrida_minutos` int DEFAULT NULL,
  `tipo_escoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `controle_de_corridas`
--

LOCK TABLES `controle_de_corridas` WRITE;
/*!40000 ALTER TABLE `controle_de_corridas` DISABLE KEYS */;
INSERT INTO `controle_de_corridas` VALUES (1,45,1,987,2.54,5,'2024-08-29','00:00','00:00',60,2.70,0.465,0.011,'2024-08-29T12:37','2024-08-29T10:37',0.65,120,45,11.54,138.48,0.646,0.65,0.65,100,100,100,100,100,1300,1040,'verde'),(2,10,5,978,3.25,5,'2024-08-29','13:00','12:00',60,0.36,0.465,0.110,'2024-08-29T12:47','2024-08-29T10:47',0.65,120,6,11.54,138.48,0.654,0.64,0,100,100,100,100,100,1300,105,'verde-clara'),(3,25,2,978,3.54,5,'2024-08-30','00:00','00:00',60,1.44,0.21,0.012,'2024-08-30T10:11','2024-08-30T08:11',0.21,120,24,11.54,138.48,0.21,0.12,1.22,100,100,100,100,100,1300,987,'cinza'),(4,20,1,987,3.58,5,'2024-09-19','00:00','00:00',60,1.20,5.606,0.516,'2024-09-19T12:56','2024-09-19T10:56',0.65,120,20,54.21,650.52,0.564,0.65,0.65,100,100,100,100,100,1300,105,'cinza');
/*!40000 ALTER TABLE `controle_de_corridas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_registry` date DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,'admin','321');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-24  8:56:25
