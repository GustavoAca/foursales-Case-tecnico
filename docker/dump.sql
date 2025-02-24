-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: localhost    Database: foursales
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `FLYWAY_SCHEMA_HISTORY`
--

DROP TABLE IF EXISTS `FLYWAY_SCHEMA_HISTORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FLYWAY_SCHEMA_HISTORY` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `FLYWAY_SCHEMA_HISTORY_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FLYWAY_SCHEMA_HISTORY`
--

LOCK TABLES `FLYWAY_SCHEMA_HISTORY` WRITE;
/*!40000 ALTER TABLE `FLYWAY_SCHEMA_HISTORY` DISABLE KEYS */;
INSERT INTO `FLYWAY_SCHEMA_HISTORY` VALUES (1,'1.1.0.0','script inicial','SQL','comum/V1_1_0_0__script_inicial.sql',425523230,'foursales','2025-02-24 12:56:04',531,1),(2,'1.1.0.1','insert inicial','SQL','mysql/V1_1_0_1__insert_inicial.sql',-270603862,'foursales','2025-02-24 12:58:13',15,1);
/*!40000 ALTER TABLE `FLYWAY_SCHEMA_HISTORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id` binary(16) NOT NULL,
  `descricao` varchar(250) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `data_de_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_de_modificacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `criado_por` varchar(100) DEFAULT NULL,
  `modificado_por` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (_binary '*ç`}\ÔüÔ¥åBG/õ[','Categoria de casa e cozinha','Casa e Cozinha','2025-02-24 12:58:13','2025-02-24 12:58:13','FOURSALES','FOURSALES'),(_binary '*ça\ÔüÔ¥åBG/õ[','Categoria de eletr√¥nicos','Eletr√¥nicos','2025-02-24 12:58:13','2025-02-24 12:58:13','FOURSALES','FOURSALES'),(_binary '¿k+i\ÔûÔ¥åBG/õ[','Categoria de moda e vestu√°rio','Moda e Vestu√°rio','2025-02-24 12:58:13','2025-02-24 12:58:13','FOURSALES','FOURSALES');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamentos`
--

DROP TABLE IF EXISTS `pagamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamentos` (
  `id` binary(16) NOT NULL,
  `pedido_id` binary(16) NOT NULL,
  `data_de_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_de_modificacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `criado_por` varchar(100) DEFAULT NULL,
  `modificado_por` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_pagamentos_pedido_id` (`pedido_id`),
  CONSTRAINT `pagamentos_ibfk_1` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamentos`
--

LOCK TABLES `pagamentos` WRITE;
/*!40000 ALTER TABLE `pagamentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `id` binary(16) NOT NULL,
  `usuario_id` binary(16) NOT NULL,
  `status` varchar(30) NOT NULL,
  `valor_total` decimal(10,2) NOT NULL,
  `data_de_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_de_modificacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `criado_por` varchar(100) DEFAULT NULL,
  `modificado_por` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_pedidos_usuario_id` (`usuario_id`),
  KEY `idx_pedidos_usuario_id_valor_total` (`usuario_id`,`valor_total`),
  KEY `idx_pedidos_data_de_criacao_valor_total` (`data_de_criacao`,`valor_total`),
  CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produtos` (
  `id` binary(16) NOT NULL,
  `subcategoria_id` binary(16) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `descricao` varchar(250) NOT NULL,
  `preco` decimal(10,2) NOT NULL,
  `quantidade_em_estoque` int NOT NULL,
  `data_de_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_de_modificacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `criado_por` varchar(100) DEFAULT NULL,
  `modificado_por` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `subcategoria_id` (`subcategoria_id`),
  KEY `idx_produtos_quantidade_em_estoque` (`quantidade_em_estoque`),
  CONSTRAINT `produtos_ibfk_1` FOREIGN KEY (`subcategoria_id`) REFERENCES `subcategorias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (_binary '\‡\…}\ÔßÔ¥åBG/õ[',_binary 'çãâæ\Ô•Ô¥åBG/õ[','Moto G54','Celular motorola moto G54, 256GB de armazenamento',1050.00,10,'2025-02-24 12:58:13','2025-02-24 12:58:13','FOURSALES','FOURSALES'),(_binary '\‡\œ\ÔßÔ¥åBG/õ[',_binary 'çãë\‚\Ô•Ô¥åBG/õ[','Cortina blackout','Cortina 1,20mx0,6m',10.00,10,'2025-02-24 12:58:13','2025-02-24 12:58:13','FOURSALES','FOURSALES');
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos_pedidos`
--

DROP TABLE IF EXISTS `produtos_pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produtos_pedidos` (
  `id` binary(16) NOT NULL,
  `produto_id` binary(16) NOT NULL,
  `pedido_id` binary(16) NOT NULL,
  `quantidade` int NOT NULL,
  `preco` decimal(10,2) NOT NULL,
  `estoque_disponivel` tinyint(1) DEFAULT NULL,
  `data_de_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_de_modificacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `criado_por` varchar(100) DEFAULT NULL,
  `modificado_por` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_produtos_pedidos_pedido_id` (`pedido_id`),
  KEY `idx_produtos_pedidos_produto_id` (`produto_id`),
  CONSTRAINT `produtos_pedidos_ibfk_1` FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`id`),
  CONSTRAINT `produtos_pedidos_ibfk_2` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos_pedidos`
--

LOCK TABLES `produtos_pedidos` WRITE;
/*!40000 ALTER TABLE `produtos_pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `produtos_pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategorias`
--

DROP TABLE IF EXISTS `subcategorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcategorias` (
  `id` binary(16) NOT NULL,
  `categoria_id` binary(16) NOT NULL,
  `descricao` varchar(250) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `data_de_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_de_modificacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `criado_por` varchar(100) DEFAULT NULL,
  `modificado_por` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `categoria_id` (`categoria_id`),
  KEY `idx_sucategorias_categoria_id` (`id`),
  CONSTRAINT `subcategorias_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategorias`
--

LOCK TABLES `subcategorias` WRITE;
/*!40000 ALTER TABLE `subcategorias` DISABLE KEYS */;
INSERT INTO `subcategorias` VALUES (_binary 'çãâæ\Ô•Ô¥åBG/õ[',_binary '*ç`}\ÔüÔ¥åBG/õ[','Subcategoria de casa e cozinha, utens√≠lios de cozinha','Utens√≠lios de cozinha','2025-02-24 12:58:13','2025-02-24 12:58:13','FOURSALES','FOURSALES'),(_binary 'çãë\‚\Ô•Ô¥åBG/õ[',_binary '*ç`}\ÔüÔ¥åBG/õ[','Subcategoria de casa e cozinha, decora√ß√£o','Decora√ß√£o','2025-02-24 12:58:13','2025-02-24 12:58:13','FOURSALES','FOURSALES'),(_binary 'çãì¥\Ô•Ô¥åBG/õ[',_binary '*ça\ÔüÔ¥åBG/õ[','Subcategoria de eletronicos, smartphones','Smartphones','2025-02-24 12:58:13','2025-02-24 12:58:13','FOURSALES','FOURSALES'),(_binary 'çãï\Z\Ô•Ô¥åBG/õ[',_binary '*ça\ÔüÔ¥åBG/õ[','Subcategoria de eletronicos, televisores','Televisores','2025-02-24 12:58:13','2025-02-24 12:58:13','FOURSALES','FOURSALES'),(_binary 'çãñC\Ô•Ô¥åBG/õ[',_binary '¿k+i\ÔûÔ¥åBG/õ[','Subcategoria de moda e vestu√°rio','Roupas Masculinas','2025-02-24 12:58:13','2025-02-24 12:58:13','FOURSALES','FOURSALES'),(_binary 'çãóm\Ô•Ô¥åBG/õ[',_binary '¿k+i\ÔûÔ¥åBG/õ[','Subcategoria de moda e vestu√°rio','Roupas Femininas','2025-02-24 12:58:13','2025-02-24 12:58:13','FOURSALES','FOURSALES');
/*!40000 ALTER TABLE `subcategorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` binary(16) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `perfil` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `total_de_compras_realizadas` int NOT NULL,
  `data_de_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_de_modificacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `criado_por` varchar(100) DEFAULT NULL,
  `modificado_por` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_usuario_email` (`email`),
  KEY `idx_usuarios_total_de_compras_realizadas` (`total_de_compras_realizadas`),
  KEY `idx_usuarios_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (_binary '\0£ΩªˇàH2¢aô»¶\ÔW','galasdalas1@gmail.com','$2a$10$Pl335iY8.kFtoDzGZwNZvezyBKazOv0cjINrMBVWDcTjx3daXgglS','ROLE_ADMIN','Gustavo Admin',0,'2025-02-24 12:58:13','2025-02-24 12:58:13','FOURSALES','FOURSALES'),(_binary '\¬K\ˆ+HŸÑ©u§\÷\‹<','galasdalas2@gmail.com','$2a$10$V8EOtbXPSVF7drEJhEXGi.bSK0DgZ/orlADNcTW.IVjiT2W5o.R12','ROLE_USER','Gustavo User',0,'2025-02-24 12:58:13','2025-02-24 12:58:13','FOURSALES','FOURSALES');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-24 12:58:42
