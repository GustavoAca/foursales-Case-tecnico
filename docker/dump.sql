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
INSERT INTO `FLYWAY_SCHEMA_HISTORY` VALUES (1,'1.1.0.0','script inicial','SQL','comum/V1_1_0_0__script_inicial.sql',-1161630986,'foursales','2025-02-23 16:04:21',714,1),(2,'1.1.0.1','insert inicial','SQL','mysql/V1_1_0_1__insert_inicial.sql',1577024205,'foursales','2025-02-23 16:04:21',6,1),(3,'1.1.0.2','criando usuario','SQL','comum/V1_1_0_2__criando_usuario.sql',0,'foursales','2025-02-23 16:04:21',2,1);
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
INSERT INTO `categorias` VALUES (_binary '*`}\ïŸï´ŒBG/›[','Categoria de casa e cozinha','Casa e Cozinha','2025-02-23 16:04:21','2025-02-23 16:04:21','FOURSALES','FOURSALES'),(_binary '*a\ïŸï´ŒBG/›[','Categoria de eletrÃ´nicos','EletrÃ´nicos','2025-02-23 16:04:21','2025-02-23 16:04:21','FOURSALES','FOURSALES'),(_binary ';K—\è\Ê\ÈFÖ±ÿ\ìXG\Ó\ó','Categoia focada em roupas','Roupas','2025-02-23 13:05:47','2025-02-23 13:05:47','galasdalas501@gmail.com','galasdalas501@gmail.com'),(_binary 'Àk+i\ïžï´ŒBG/›[','Categoria de moda e vestuÃ¡rio','Moda e VestuÃ¡rio','2025-02-23 16:04:21','2025-02-23 16:04:21','FOURSALES','FOURSALES');
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
INSERT INTO `pedidos` VALUES (_binary '7(é¼©A€ds*E\á—',_binary '[8W>Q@\æ±?\nh\ô','CONFIRMADO',63.00,'2025-02-23 13:06:36','2025-02-23 13:35:32','galasdalas501@gmail.com','galasdalas501@gmail.com');
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
INSERT INTO `produtos` VALUES (_binary '\à\É}\ï§ï´ŒBG/›[',_binary '‹‰¾\ï¥ï´ŒBG/›[','Moto G54','Celular motorola moto G54, 256GB de armazenamento',1050.00,4,'2025-02-23 16:04:21','2025-02-23 13:52:55','FOURSALES','galasdalas501@gmail.com'),(_binary '\à\Ï\ï§ï´ŒBG/›[',_binary '‹‘\â\ï¥ï´ŒBG/›[','Cortina blackout','Cortina 1,20mx0,6m',10.00,10,'2025-02-23 16:04:21','2025-02-23 16:04:21','FOURSALES','FOURSALES'),(_binary '}\â%TA„½\Z½L¸r',_binary '4¸\ã\÷¸EU‹hŠÏ¨‡3„','Roupas Femininas','Subcategoia focada em roupas',10.00,10,'2025-02-23 13:06:18','2025-02-23 13:06:18','galasdalas501@gmail.com','galasdalas501@gmail.com');
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
INSERT INTO `produtos_pedidos` VALUES (_binary 'Ã—¶]Í²K¶\âk\ë\ÐD3',_binary '\à\É}\ï§ï´ŒBG/›[',_binary '7(é¼©A€ds*E\á—',3,10.50,1,'2025-02-23 14:04:59','2025-02-23 14:04:59','galasdalas501@gmail.com','galasdalas501@gmail.com');
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
INSERT INTO `subcategorias` VALUES (_binary '4¸\ã\÷¸EU‹hŠÏ¨‡3„',_binary ';K—\è\Ê\ÈFÖ±ÿ\ìXG\Ó\ó','Subcategoia focada em roupas','Roupas Femininas','2025-02-23 13:06:03','2025-02-23 13:06:03','galasdalas501@gmail.com','galasdalas501@gmail.com'),(_binary '‹‰¾\ï¥ï´ŒBG/›[',_binary '*`}\ïŸï´ŒBG/›[','Subcategoria de casa e cozinha, utensÃ­lios de cozinha','UtensÃ­lios de cozinha','2025-02-23 16:04:21','2025-02-23 16:04:21','FOURSALES','FOURSALES'),(_binary '‹‘\â\ï¥ï´ŒBG/›[',_binary '*`}\ïŸï´ŒBG/›[','Subcategoria de casa e cozinha, decoraÃ§Ã£o','DecoraÃ§Ã£o','2025-02-23 16:04:21','2025-02-23 16:04:21','FOURSALES','FOURSALES'),(_binary '‹“´\ï¥ï´ŒBG/›[',_binary '*a\ïŸï´ŒBG/›[','Subcategoria de eletronicos, smartphones','Smartphones','2025-02-23 16:04:21','2025-02-23 16:04:21','FOURSALES','FOURSALES'),(_binary '‹•\Z\ï¥ï´ŒBG/›[',_binary '*a\ïŸï´ŒBG/›[','Subcategoria de eletronicos, televisores','Televisores','2025-02-23 16:04:21','2025-02-23 16:04:21','FOURSALES','FOURSALES'),(_binary '‹–C\ï¥ï´ŒBG/›[',_binary 'Àk+i\ïžï´ŒBG/›[','Subcategoria de moda e vestuÃ¡rio','Roupas Masculinas','2025-02-23 16:04:21','2025-02-23 16:04:21','FOURSALES','FOURSALES'),(_binary '‹—m\ï¥ï´ŒBG/›[',_binary 'Àk+i\ïžï´ŒBG/›[','Subcategoria de moda e vestuÃ¡rio','Roupas Femininas','2025-02-23 16:04:21','2025-02-23 16:04:21','FOURSALES','FOURSALES');
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
INSERT INTO `usuarios` VALUES (_binary '$E\Í\Ö\Ü)CX„\n`#}7','galasdalas1@gmail.com','$2a$10$dgtnHjBWoaATTyU2aFCkmuydyZo73Nf4dicFLkzo7f0BSUJzPAEKO','ROLE_USER','Gustavo User',0,'2025-02-23 14:27:16','2025-02-23 14:27:16','foursales','foursales'),(_binary '[8W>Q@\æ±?\nh\ô','galasdalas501@gmail.com','$2a$10$gACPrsCsY.7vnURkJplobOiDFDD9P2eDJDFSsWpGE2SkgM0GjFe/C','ROLE_ADMIN','Gustavo',4,'2025-02-23 13:05:12','2025-02-23 13:35:32','foursales','galasdalas501@gmail.com');
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

-- Dump completed on 2025-02-23 18:18:53
