-- ('RECEIVEFILE','REQUESTINSTRUCTION','SAMPLETABLEFORREQINSTRUCTIONINNEWUI','PUBLISHFILE');
-- ('REQUESTINSTRUCTION','ReceivedFile','PublishedFile','RequestInstr');
-- ('');
-- admin;
insert  into `ETEC_MODULE`(`module_id`,`created_by`,`created_time`,`description`,`is_public`,`is_system_module`,`module_order`,`name`,`updated_by`,`updated_time`) values ('8a10d4594430be1a014430f1f44d001d','admin','2014-07-31 15:01:04','Office Module',true,true,7,'Office Module','null','2014-07-31 15:01:04');



insert into `ETEC_RE_TABLE`(`id`,`chinese_name`,`created_by`,`created_time`,`description`,`table_name`,`sql_source`) values ('8a10d4594209dcea01420d0cad73000c','receivefile','admin','2014-07-31 15:01:04','receivefile','RECEIVEFILE','null
 ************************************************************************************* 
ALTER TABLE `RECEIVEFILE`  CHANGE `NUMBER` `NUMBER` VARCHAR (250)  DEFAULT NULL 
 ************************************************************************************* 
 
');



insert  into `ETEC_RE_TABLE_COLUMNS`(`id`,`auto_generation_id`,`chinese_name`,`default_value`,`id_number`,`is_unique_key`,`length`,`name`,`type`,`table_id`) values ('8a10d4594209dcea01420d0cb38c000e','','MODIFIEDTIME','NULL',0,false,'0','MODIFIEDTIME','DATETIME','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d0cb38d0016','','CREATEDTIME','NULL',0,false,'0','CREATEDTIME','DATETIME','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174bc9001f','','docTitle','NULL',0,false,'250','DOCTITLE','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174c210025','','processingTime','NULL',0,false,'0','PROCESSINGTIME','DATETIME','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174c1f0020','','secretlevel','NULL',0,false,'250','SECRETLEVEL','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d0cb38c0014','','EXECUTION_ID','NOTNULL',0,false,'100','EXECUTION_ID','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d0cb38b000d','','documentType','NULL',1,false,'250','DOCUMENTTYPE','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174c200023','','BureauOffOpinion','NULL',0,false,'250','BUREAUOFFOPINION','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d0cb38c0010','','ID','NOTNULL',0,true,'100','ID','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d0cb38c0011','','TASK_ID','NULL',0,false,'100','TASK_ID','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174c22002c','','receivedate','NULL',0,false,'0','RECEIVEDATE','DATE','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d0cb38d0015','','PROC_INST_ID','NOTNULL',0,false,'100','PROC_INST_ID','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174c22002b','','docFileNo','NULL',0,false,'250','DOCFILENO','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174c210026','','OriginalDocTime','NULL',0,false,'0','ORIGINALDOCTIME','DATETIME','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174c210029','','leaderAssignOpinion','NULL',0,false,'250','LEADERASSIGNOPINION','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174c210028','','generalOffOpinion','NULL',0,false,'250','GENERALOFFOPINION','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d0cb38c000f','','IS_DELETE','0',0,false,'1','IS_DELETE','TINYINT','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174c1f0022','','docFromOrg','NULL',0,false,'250','DOCFROMORG','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174c210027','','isBureauOffDeal','NULL',0,false,'250','ISBUREAUOFFDEAL','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174c200024','','urgency','NULL',0,false,'250','URGENCY','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d0cb38c0012','','CREATEDBY','NULL',0,false,'100','CREATEDBY','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174c21002a','{DOCUMENTTYPE}|*3','number','NULL',30,false,'250','NUMBER','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d0cb38c0013','','MODIFIEDBY','NULL',0,false,'100','MODIFIEDBY','VARCHAR','8a10d4594209dcea01420d0cad73000c'),('8a10d4594209dcea01420d174c1f0021','','receiveAttahments','NULL',0,false,'250','RECEIVEATTAHMENTS','VARCHAR','8a10d4594209dcea01420d0cad73000c');



insert into `ETEC_RE_TABLE`(`id`,`chinese_name`,`created_by`,`created_time`,`description`,`table_name`,`sql_source`) values ('8a10d45942311fe301423203be38007f','requestinstruction','admin','2014-07-31 15:01:04','requestinstruction','REQUESTINSTRUCTION','null
 ************************************************************************************* 
ALTER TABLE `REQUESTINSTRUCTION`  ADD COLUMN `MOBILENO` VARCHAR (100)  DEFAULT NULL , ADD COLUMN `REQUESTATTACHEMENT` VARCHAR (250)  DEFAULT NULL , ADD COLUMN `MAINCONTENT` LONGTEXT, ADD COLUMN `ALLOPINION` VARCHAR (250)  DEFAULT NULL , ADD COLUMN `CREATOR` VARCHAR (250)  DEFAULT NULL , ADD COLUMN `DEALDEPARTMENT` VARCHAR (250)  DEFAULT NULL , ADD COLUMN `REQUESTORGANIZER` VARCHAR (250)  DEFAULT NULL , ADD COLUMN `AFFAIRE` VARCHAR (250)  DEFAULT NULL 
 ************************************************************************************* 
 
');



insert  into `ETEC_RE_TABLE_COLUMNS`(`id`,`auto_generation_id`,`chinese_name`,`default_value`,`id_number`,`is_unique_key`,`length`,`name`,`type`,`table_id`) values ('8a10d45942311fe301423203c2010086','','IS_DELETE','0',0,false,'1','IS_DELETE','TINYINT','8a10d45942311fe301423203be38007f'),('8a10d45942311fe30142320702630090','','mainContent','NULL',0,false,'0','MAINCONTENT','LONGTEXT','8a10d45942311fe301423203be38007f'),('8a10d45942311fe301423203c2010081','','TASK_ID','NULL',0,false,'100','TASK_ID','VARCHAR','8a10d45942311fe301423203be38007f'),('8a10d45942311fe301423203c2010082','','MODIFIEDBY','NULL',0,false,'100','MODIFIEDBY','VARCHAR','8a10d45942311fe301423203be38007f'),('8a10d45942311fe301423203c2010088','','leadersuggestion','NULL',0,false,'250','LEADERSUGGESTION','VARCHAR','8a10d45942311fe301423203be38007f'),('8a10d45942311fe3014232070262008e','','mobileNo','NULL',0,false,'100','MOBILENO','VARCHAR','8a10d45942311fe301423203be38007f'),('8a10d45942311fe30142320702630092','','dealDepartment','NULL',0,false,'250','DEALDEPARTMENT','VARCHAR','8a10d45942311fe301423203be38007f'),('8a10d45942311fe301423203c2000080','','CREATEDBY','NULL',0,false,'100','CREATEDBY','VARCHAR','8a10d45942311fe301423203be38007f'),('8a10d45942311fe301423203c2010083','','ID','NOTNULL',0,true,'100','ID','VARCHAR','8a10d45942311fe301423203be38007f'),('8a10d45942311fe301423203c2010087','','PROC_INST_ID','NOTNULL',0,false,'100','PROC_INST_ID','VARCHAR','8a10d45942311fe301423203be38007f'),('8a10d45942311fe30142320702630094','','affaire','NULL',0,false,'250','AFFAIRE','VARCHAR','8a10d45942311fe301423203be38007f'),('8a10d45942311fe301423203c2010084','','MODIFIEDTIME','NULL',0,false,'0','MODIFIEDTIME','DATETIME','8a10d45942311fe301423203be38007f'),('8a10d45942311fe301423203c2010085','','CREATEDTIME','NULL',0,false,'0','CREATEDTIME','DATETIME','8a10d45942311fe301423203be38007f'),('8a10d45942311fe30142320702630093','','creatorName','NULL',0,false,'250','CREATOR','VARCHAR','8a10d45942311fe301423203be38007f'),('8a10d45942311fe301423203c2020089','','EXECUTION_ID','NOTNULL',0,false,'100','EXECUTION_ID','VARCHAR','8a10d45942311fe301423203be38007f'),('8a10d45942311fe30142320702630095','','requestOrganizer','NULL',0,false,'250','REQUESTORGANIZER','VARCHAR','8a10d45942311fe301423203be38007f'),('8a10d45942311fe30142320702630091','','allOpinion','NULL',0,false,'250','ALLOPINION','VARCHAR','8a10d45942311fe301423203be38007f'),('8a10d45942311fe3014232070263008f','','requestattachement','NULL',0,false,'250','REQUESTATTACHEMENT','VARCHAR','8a10d45942311fe301423203be38007f');



insert into `ETEC_RE_TABLE`(`id`,`chinese_name`,`created_by`,`created_time`,`description`,`table_name`,`sql_source`) values ('8a10d459446c869001446d01b975002f','','admin','2014-07-31 15:01:04','','SAMPLETABLEFORREQINSTRUCTIONINNEWUI','CREATE TABLE `SAMPLETABLEFORREQINSTRUCTIONINNEWUI`(`TASK_ID` VARCHAR (100)  DEFAULT NULL ,`ID` VARCHAR (100) NOT NULL, `MODIFIEDBY` VARCHAR (100)  DEFAULT NULL ,`MODIFIEDTIME` DATETIME,`CREATEDTIME` DATETIME,`CREATEDBY` VARCHAR (100)  DEFAULT NULL ,`PROC_INST_ID` VARCHAR (100) NOT NULL, `NAME` VARCHAR (100)  DEFAULT NULL ,`EXECUTION_ID` VARCHAR (100) NOT NULL, `IS_DELETE` TINYINT (1)  DEFAULT \'0\' , PRIMARY KEY (ID))');



insert  into `ETEC_RE_TABLE_COLUMNS`(`id`,`auto_generation_id`,`chinese_name`,`default_value`,`id_number`,`is_unique_key`,`length`,`name`,`type`,`table_id`) values ('8a10d459446c869001446d01ba240038','','EXECUTION_ID','NOTNULL',0,false,'100','EXECUTION_ID','VARCHAR','8a10d459446c869001446d01b975002f'),('8a10d459446c869001446d01ba230031','','ID','NOTNULL',0,true,'100','ID','VARCHAR','8a10d459446c869001446d01b975002f'),('8a10d459446c869001446d01ba240034','','CREATEDTIME','NULL',0,false,'0','CREATEDTIME','DATETIME','8a10d459446c869001446d01b975002f'),('8a10d459446c869001446d01ba230030','','TASK_ID','NULL',0,false,'100','TASK_ID','VARCHAR','8a10d459446c869001446d01b975002f'),('8a10d459446c869001446d01ba250039','','IS_DELETE','0',0,false,'1','IS_DELETE','TINYINT','8a10d459446c869001446d01b975002f'),('8a10d459446c869001446d01ba240037','','name','NULL',0,false,'100','NAME','VARCHAR','8a10d459446c869001446d01b975002f'),('8a10d459446c869001446d01ba240036','','PROC_INST_ID','NOTNULL',0,false,'100','PROC_INST_ID','VARCHAR','8a10d459446c869001446d01b975002f'),('8a10d459446c869001446d01ba240033','','MODIFIEDTIME','NULL',0,false,'0','MODIFIEDTIME','DATETIME','8a10d459446c869001446d01b975002f'),('8a10d459446c869001446d01ba240035','','CREATEDBY','NULL',0,false,'100','CREATEDBY','VARCHAR','8a10d459446c869001446d01b975002f'),('8a10d459446c869001446d01ba230032','','MODIFIEDBY','NULL',0,false,'100','MODIFIEDBY','VARCHAR','8a10d459446c869001446d01b975002f');



insert into `ETEC_RE_TABLE`(`id`,`chinese_name`,`created_by`,`created_time`,`description`,`table_name`,`sql_source`) values ('ff80818141e4ae000141e4b6b3060000','publishfile','admin','2014-07-31 15:01:04','','PUBLISHFILE','null
 ************************************************************************************* 
ALTER TABLE `PUBLISHFILE`  CHANGE `REG3` `REG3` VARCHAR (100)  DEFAULT NULL , ADD COLUMN `AUTOGENID` VARCHAR (100)  DEFAULT NULL 
 ************************************************************************************* 
 
');



insert  into `ETEC_RE_TABLE_COLUMNS`(`id`,`auto_generation_id`,`chinese_name`,`default_value`,`id_number`,`is_unique_key`,`length`,`name`,`type`,`table_id`) values ('ff80818141e4ae000141e4bac5c60010','','Creator','NULL',0,false,'200','CREATOR','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4bac5c70012','','SubjectKeywords','NULL',0,false,'255','SUBJECTKEYWORDS','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4bac5c70014','','Title','NULL',0,false,'255','TITLE','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4d019f80020','','isOfficerCheck','NULL',0,false,'100','ISOFFICERCHECK','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4b6b3f00005','','EXECUTION_ID','NOTNULL',0,false,'100','EXECUTION_ID','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4b7ed27000b','','reg2','NULL',0,false,'100','REG2','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4b6b3f00009','','PROC_INST_ID','NOTNULL',0,false,'100','PROC_INST_ID','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4b6b3ef0001','','CREATEDBY','NULL',0,false,'100','CREATEDBY','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4b7ed27000d','','reg1','NULL',0,false,'100','REG1','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4b6b3f00004','','TASK_ID','NULL',0,false,'100','TASK_ID','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4bac5c60011','','chargeLeaderOpinion','NULL',0,false,'255','CHARGELEADEROPINION','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4bac5c70013','','leaderOpinion','NULL',0,false,'255','LEADEROPINION','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4bac5c70015','','publishfileAttachment','NULL',0,false,'255','PUBLISHFILEATTACHMENT','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4bac5c70016','','cctouser','NULL',0,false,'255','CCTOUSER','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4bac5c70018','','sendto','NULL',0,false,'255','SENDTO','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4b6b3f00007','','publishorg','NULL',0,false,'250','PUBLISHORG','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('8a10d4594309ddbf01430a9b789e0091','{EZTC}|*3','AUTOGENID','NULL',136,false,'100','AUTOGENID','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4b6b3f0000a','','MODIFIEDBY','NULL',0,false,'100','MODIFIEDBY','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4b6b3f00003','','ID','NOTNULL',0,true,'100','ID','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4b7ed27000c','','reg3','NULL',13,false,'100','REG3','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4d019f8001f','','isComplete','NULL',0,false,'100','ISCOMPLETE','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4bac5c70017','','createtime','NULL',0,false,'0','CREATETIME','DATE','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4b6b3f00006','','MODIFIEDTIME','NULL',0,false,'0','MODIFIEDTIME','DATETIME','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4bac5c6000f','','cctodep','NULL',0,false,'255','CCTODEP','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4b6b3f00002','','IS_DELETE','0',0,false,'1','IS_DELETE','TINYINT','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4bac5c5000e','','urgency','NULL',0,false,'100','URGENCY','VARCHAR','ff80818141e4ae000141e4b6b3060000'),('ff80818141e4ae000141e4b6b3f00008','','CREATEDTIME','NULL',0,false,'0','CREATEDTIME','DATETIME','ff80818141e4ae000141e4b6b3060000');



-- MySQL dump 10.13  Distrib 5.5.30, for Win64 (x86)
--
-- Host: localhost    Database: EASYBPM_MASTER
-- ------------------------------------------------------
-- Server version	5.5.30-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `RECEIVEFILE`
--

DROP TABLE IF EXISTS `RECEIVEFILE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RECEIVEFILE` (
  `PROCESSINGTIME` datetime DEFAULT NULL,
  `ORIGINALDOCTIME` datetime DEFAULT NULL,
  `IS_DELETE` tinyint(1) DEFAULT '0',
  `CREATEDBY` varchar(100) DEFAULT NULL,
  `NUMBER` varchar(250) DEFAULT NULL,
  `LEADERASSIGNOPINION` varchar(250) DEFAULT NULL,
  `ID` varchar(100) NOT NULL,
  `RECEIVEDATE` date DEFAULT NULL,
  `DOCFILENO` varchar(250) DEFAULT NULL,
  `RECEIVEATTAHMENTS` varchar(250) DEFAULT NULL,
  `DOCFROMORG` varchar(250) DEFAULT NULL,
  `DOCUMENTTYPE` varchar(250) DEFAULT NULL,
  `PROC_INST_ID` varchar(100) NOT NULL,
  `BUREAUOFFOPINION` varchar(250) DEFAULT NULL,
  `SECRETLEVEL` varchar(250) DEFAULT NULL,
  `URGENCY` varchar(250) DEFAULT NULL,
  `MODIFIEDBY` varchar(100) DEFAULT NULL,
  `DOCTITLE` varchar(250) DEFAULT NULL,
  `TASK_ID` varchar(100) DEFAULT NULL,
  `EXECUTION_ID` varchar(100) NOT NULL,
  `ISBUREAUOFFDEAL` varchar(250) DEFAULT NULL,
  `CREATEDTIME` datetime DEFAULT NULL,
  `GENERALOFFOPINION` varchar(250) DEFAULT NULL,
  `MODIFIEDTIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RECEIVEFILE`
--

LOCK TABLES `RECEIVEFILE` WRITE;
/*!40000 ALTER TABLE `RECEIVEFILE` DISABLE KEYS */;
INSERT INTO `RECEIVEFILE` VALUES (NULL,NULL,0,'usera',NULL,NULL,'04444d1e-c9c2-4ae1-bda9-453a868d7cc6','2013-12-09',NULL,'NULL',NULL,'ddd','141420',NULL,'normal','Normal','usera','sss','141509','141420','true','2013-12-09 05:52:30',NULL,'2013-12-09 06:02:56'),(NULL,NULL,0,'autouser','ETECNULL001',NULL,'0a82a741-7b44-46ab-93f4-27589f08b182',NULL,NULL,'NULL',NULL,NULL,'171418',NULL,'normal','normal','autouser',NULL,'171421','171418',NULL,'2013-12-19 08:57:19',NULL,'2013-12-19 08:57:19'),('2014-03-25 12:00:00','2014-03-19 12:00:00',0,'admin','DOCUMENTTYPE023','NULL','0b4942aa-64b1-45b1-b794-d9d8865c4d8a','2014-03-03','NULL','\\NULL\\','NULL','aaaaaa','26668','NULL','secret','high','admin','test','26760','26668','true','2014-03-03 06:43:58','test','2014-03-03 06:45:01'),('2013-12-23 12:00:00','2013-12-05 00:00:00',0,'usera','123',NULL,'0bc15b98-41c4-48e6-9338-8b8e4e9d67d6','2013-12-04','23s','NULL','NETWORK DEPARTMENT','office documents','128722',NULL,'top_secret','High','usera','new documents','128978','128722','yes','2013-12-04 05:57:38','test','2013-12-04 06:00:06'),(NULL,NULL,0,'admin','ETECNULL012',NULL,'0deb502d-4cd4-4388-8322-99ecb90a7f1a',NULL,NULL,'D:eazytecmastersourceeasybpm	argetpm-1.0\resources/admin/3_20131227073100_Form.png',NULL,NULL,'196345','xcv','normal','normal','admin',NULL,'196348','196345','121212','2013-12-27 07:31:01',NULL,'2013-12-27 07:31:01'),(NULL,NULL,0,'admin','ETECtest020','NULL','16bc7e02-9224-4ae3-9ea7-358ed191a22c','2014-02-24','NULL','\\NULL\\','NULL','test','15587','NULL','normal','normal','admin','test','15862','15587','NULL','2014-02-24 14:07:06','test ','2014-02-24 14:09:57'),(NULL,NULL,0,'admin','DOCUMENTTYPE024','NULL','195298ca-00d8-4dd7-9481-ea3cccfc0701',NULL,'NULL','\'NULL\'','NULL','NULL','42113','NULL','normal','normal','admin','NULL','42116','42113','NULL','2014-03-10 09:14:36','NULL','2014-03-10 09:14:36'),(NULL,NULL,0,'admin',NULL,NULL,'1fe8ed2d-1ffc-4299-9973-51fe275d1223','2013-11-11',NULL,'NULL',NULL,'sd','87574',NULL,'normal','Normal','admin','sd','87577','87574',NULL,'2013-11-11 08:09:09',NULL,'2013-11-11 08:09:09'),(NULL,'2014-02-13 12:00:00',0,'admin','ETECtest019','NULL','230f3738-3704-4f2f-b669-f5346748172e','2014-02-01','NULL','\'NULL\'','NULL','test','15479','NULL','top secret','high','admin','testtest','15482','15479','NULL','2014-02-24 14:03:36','NULL','2014-02-24 14:03:36'),(NULL,NULL,0,'admin','ETECaaaa017','NULL','3399bc51-cb47-4913-a10f-ba8dc16cf4bb',NULL,'NULL','\'NULL\'','NULL','aaaa','10878','NULL','normal','normal','admin','fefefef','10881','10878','NULL','2014-02-21 01:35:40','NULL','2014-02-21 01:35:40'),(NULL,NULL,0,'admin','ETECtest018','NULL','38c2cbe9-6ce2-4105-a7df-b5ff50989990','2014-02-13','NULL','\'NULL\'','NULL','test','11728','NULL','top secret','high','admin','aaa','11731','11728','NULL','2014-02-21 08:30:40','NULL','2014-02-21 08:30:40'),(NULL,NULL,0,'admin','ETECyhy021','NULL','3b14d42c-24a6-4831-a801-c030c98039e3','2014-02-25','NULL','\\NULL\\','NULL','yhy','16435','NULL','normal','normal','admin','yhy','16527','16435','NULL','2014-02-25 01:18:57','NULL','2014-02-25 01:24:27'),(NULL,'2014-02-26 12:00:00',0,'admin','ETECadmin014','NULL','429c0348-5cec-4f8b-a655-0a5cb2881250','2014-02-07','NULL','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/admin/1_20140217011900_text.txt','NULL','admin','370','NULL','normal','normal','admin','admin','373','370','NULL','2014-02-17 01:19:00','NULL','2014-02-17 01:19:00'),(NULL,NULL,0,'fg','DOCUMENTTYPE027','NULL','50d91ec1-ac81-4455-a200-9897f53dde89','2014-04-11','e','D:\\eazytec\\master\\source\\easybpm\\target\\bpm-1.0\\resources/fg/1_20140410083525_Chrysanthemum.jpg','宣传部','fe','74370','NULL','secret','high','fg','rr','74468','74370','NULL','2014-04-09 09:42:57','pass','2014-04-10 08:35:25'),(NULL,NULL,0,'ar','DOCUMENTTYPE026','NULL','6456a1cf-975a-46ce-92aa-4d19efc2545d','2014-04-03','NULL','\\NULL\\','NULL','aaaa','68148','NULL','top secret','normal','ar','test','68244','68148','NULL','2014-04-03 14:18:00','NULL','2014-04-03 14:31:35'),(NULL,NULL,0,'admin','DOCUMENTTYPE029','NULL','645c64ec-f230-457e-b6be-4f3998a57a87',NULL,'NULL','\'NULL\'','NULL','tesrt','94892','NULL','normal','normal','admin','asd','94895','94892','NULL','2014-04-23 07:22:03','NULL','2014-04-23 07:22:03'),(NULL,NULL,0,'james',NULL,NULL,'695f996d-b7f6-45a8-9486-6464d27ff983','2013-12-11',NULL,'NULL','HR Department','test','146518',NULL,'normal','Normal','james','title','146895','146518',NULL,'2013-12-11 07:20:21',NULL,'2013-12-11 07:24:55'),(NULL,NULL,0,'james','ETECtest002',NULL,'69ba8012-d20b-4717-9884-386792ac5cbb',NULL,NULL,'NULL',NULL,'test','173542',NULL,'normal','normal','james',NULL,'173545','173542',NULL,'2013-12-19 13:42:51',NULL,'2013-12-19 13:42:51'),(NULL,NULL,0,'james','ETECdfdf009',NULL,'71ff0588-73e6-4cde-914e-81a025c0f581','2013-12-20',NULL,'NULL',NULL,'dfdf','179707',NULL,'normal','normal','admin','dffdg','179797','179707',NULL,'2013-12-20 15:22:46',NULL,'2013-12-20 15:23:41'),(NULL,NULL,0,'autouser','ETECaa011',NULL,'7d3548fd-a69c-4977-85b7-a995aa007e93',NULL,NULL,'NULL','后勤部','aa','182121',NULL,'normal','normal','autouser','f','182124','182121',NULL,'2013-12-23 05:44:16',NULL,'2013-12-23 05:44:16'),(NULL,NULL,0,'admin','ETECtesttest015','NULL','875cef18-150b-482e-aecf-d48117e21358',NULL,'NULL','\'NULL\'','NULL','testtest','7613','NULL','secret','high','admin','testtest','7616','7613','NULL','2014-02-19 02:24:14','NULL','2014-02-19 02:24:14'),('2013-12-06 12:00:00','2013-12-05 00:00:00',0,'usera',NULL,NULL,'8871059e-0341-4a8c-a885-1da4df61282a','2013-12-06','asd','NULL',NULL,'test this','134368',NULL,'secret','High','usera','test','134460','134368','true','2013-12-06 06:15:15',NULL,'2013-12-06 06:15:54'),(NULL,'2014-04-18 12:00:00',0,'ff','DOCUMENTTYPE028','ed ','891223e8-5780-4ba5-a0bc-b9b41f390fe4','2014-04-11','tt','\\NULL\\','NULL','test','77584','e','top secret','high','gf','ftst','78866','77584','NULL','2014-04-10 08:25:29','d','2014-04-11 02:01:35'),(NULL,NULL,0,'admin',NULL,NULL,'89e05132-4b13-4dc7-8089-50120f09599f','2013-11-09',NULL,'NULL',NULL,'etst','86209',NULL,'normal','Normal','admin','test','86213','86209',NULL,'2013-11-09 12:57:04',NULL,'2013-11-09 12:57:04'),('2013-12-31 12:00:00','2013-12-24 12:00:00',0,'testuser','ETECdocumenttype005',NULL,'8d235b2e-5b83-45d2-8c5c-142aad74ad9f',NULL,'N001','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/testuser/1_20131220012114_WebPageReader.cs','后勤部','documenttype','175267',NULL,'normal','high','testuser','documenttitle','175270','175267',NULL,'2013-12-20 01:21:14',NULL,'2013-12-20 01:21:14'),(NULL,'2014-05-20 12:00:00',0,'zhuqa','DOCUMENTTYPE030','NULL','8ecbbc06-80ce-4b3e-b8f1-dbc0432e5810','2014-05-13','三','\'NULL\'','宣传部','类型','109872','NULL','secret','high','zhuqa','标题','109875','109872','NULL','2014-05-04 07:27:45','NULL','2014-05-04 07:27:45'),(NULL,NULL,0,'admin','ETECtest008',NULL,'96bae498-9c19-45f4-b2d1-04d3d8d8ab4a','2013-12-20',NULL,'NULL',NULL,'test','179216',NULL,'normal','normal','james','test','179312','179216','true','2013-12-20 13:58:32',NULL,'2013-12-20 15:21:56'),('2014-02-20 12:00:00','2014-02-15 12:00:00',0,'admin','ETECaaaa016','NULL','98a68607-0df6-4768-8248-e691dbc3815d','2014-02-06','sdssds','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/admin/1_20140219065041_aaa','NULL','aaaa','7819','NULL','normal','normal','admin','dfdfdfd','7822','7819','NULL','2014-02-19 06:50:42','NULL','2014-02-19 06:50:42'),(NULL,NULL,0,'admin',NULL,NULL,'98c0b772-d967-4a52-9b11-6d4768c6901f','2013-12-06',NULL,'NULL',NULL,'test','135001',NULL,'normal','Normal','admin','tstse','135090','135001','true','2013-12-06 06:29:40',NULL,'2013-12-06 06:30:36'),('2013-12-02 12:00:00',NULL,0,'srinivas','ETECtesst006',NULL,'9f8a5f88-fc53-41f1-8ef6-0d11a038911b','2013-12-20',NULL,'NULL',NULL,'tesst','176440',NULL,'secret','high','srinivas','aassa','176709','176440','true','2013-12-20 06:19:48','sasasasa','2013-12-20 06:32:10'),(NULL,NULL,0,'admin',NULL,NULL,'a7689192-e7b9-40eb-912c-0a74d97de7a0',NULL,NULL,'NULL',NULL,'asdsd','168203',NULL,'normal','Normal','admin',NULL,NULL,'168203',NULL,'2013-12-18 15:21:32',NULL,'2013-12-18 15:21:32'),(NULL,NULL,0,'user','ETECssddsds010',NULL,'a8713da7-4007-446c-9b8d-ba95c9ca2112','2013-12-04',NULL,'D:eazytecmastersourceeasybpm	argetpm-1.0\resources/user/1_20131220153702_06murali-vijay.jpg',NULL,'ssddsds','180189',NULL,'normal','normal','user','fggfgf','180192','180189',NULL,'2013-12-20 15:37:02',NULL,'2013-12-20 15:37:02'),(NULL,NULL,0,'admin','DOCUMENTTYPE022','NULL','b18e71f7-8567-426e-8bb9-4a78a7342d01','2014-02-26','NULL','\\NULL\\','NULL','testnumber','19542','NULL','normal','normal','admin','testtest','19817','19542','NULL','2014-02-26 06:43:44','NULL','2014-02-26 06:46:04'),(NULL,NULL,0,'james','ETECtest004',NULL,'bad145ee-b7e2-44cf-a86d-919c01676ad5',NULL,NULL,'NULL',NULL,'test','175066',NULL,'normal','normal','james','test','175069','175066','true','2013-12-19 15:15:06',NULL,'2013-12-19 15:15:06'),(NULL,NULL,0,'james','ETECNULL003',NULL,'cb94dd24-c167-474c-a4dc-1d4c553192f9','2013-12-19',NULL,'NULL',NULL,'ss','173639',NULL,'normal','normal','james','sss','173728','173639',NULL,'2013-12-19 13:49:35','sdff','2013-12-19 13:50:26'),(NULL,NULL,0,'admin','DOCUMENTTYPE025','NULL','d0f9d931-11c8-4498-ae24-3a040ed68ab9','2014-04-02','NULL','\\NULL\\','NULL','sdsdsd','65920','NULL','normal','normal','admin','dadadad','66017','65920','NULL','2014-04-02 11:24:48','NULL','2014-04-02 11:25:34'),(NULL,NULL,0,'admin','ETECdfg007',NULL,'f1ded63b-23ff-4c07-bcf0-1ead1f347df6','2013-12-20',NULL,'NULL',NULL,'dfg','178647',NULL,'normal','normal','james','fdgdfg','178736','178647','true','2013-12-20 10:24:50',NULL,'2013-12-20 10:29:52'),(NULL,NULL,0,'admin','ETECNULL013',NULL,'f3ac4daf-09b1-4167-a1fa-5b2a061da8ef',NULL,NULL,'D:eazytecmastersourceeasybpm	argetpm-1.0\resources/admin/2_20131227073255_Form.png',NULL,NULL,'196406',NULL,'normal','normal','admin',NULL,'196409','196406',NULL,'2013-12-27 07:32:55',NULL,'2013-12-27 07:32:55'),(NULL,NULL,0,'james',NULL,NULL,'ff5a878a-c84a-4729-96d5-641bff3b82c3',NULL,NULL,'NULL',NULL,'ETECsad001','168332',NULL,'normal','normal','james',NULL,'168335','168332',NULL,'2013-12-18 15:54:36',NULL,'2013-12-18 15:54:36');
/*!40000 ALTER TABLE `RECEIVEFILE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REQUESTINSTRUCTION`
--

DROP TABLE IF EXISTS `REQUESTINSTRUCTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REQUESTINSTRUCTION` (
  `MODIFIEDBY` varchar(100) DEFAULT NULL,
  `CREATEDBY` varchar(100) DEFAULT NULL,
  `DEALDEPARTMENT` varchar(250) DEFAULT NULL,
  `REQUESTORGANIZER` varchar(250) DEFAULT NULL,
  `ALLOPINION` varchar(250) DEFAULT NULL,
  `ID` varchar(100) NOT NULL,
  `MOBILENO` varchar(100) DEFAULT NULL,
  `MODIFIEDTIME` datetime DEFAULT NULL,
  `PROC_INST_ID` varchar(100) NOT NULL,
  `IS_DELETE` tinyint(1) DEFAULT '0',
  `EXECUTION_ID` varchar(100) NOT NULL,
  `LEADERSUGGESTION` varchar(250) DEFAULT NULL,
  `REQUESTATTACHEMENT` varchar(250) DEFAULT NULL,
  `CREATOR` varchar(250) DEFAULT NULL,
  `MAINCONTENT` longtext,
  `CREATEDTIME` datetime DEFAULT NULL,
  `AFFAIRE` varchar(250) DEFAULT NULL,
  `TASK_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REQUESTINSTRUCTION`
--

LOCK TABLES `REQUESTINSTRUCTION` WRITE;
/*!40000 ALTER TABLE `REQUESTINSTRUCTION` DISABLE KEYS */;
INSERT INTO `REQUESTINSTRUCTION` VALUES ('karthick','karthick','Organization','karth ick','test','054190fd-57fa-483b-86fd-db67961d5302','72000201901','2014-06-19 11:32:56','148529',0,'148529','test','D:\\eazytec\\master\\source\\easybpm\\target\\bpm-1.0\\resources/karthick/6_20140619113256_(Phase8)API_Document_Help.pdf','karthick',NULL,'2014-06-19 11:27:25','sdsd','148532'),('usera','usera',NULL,NULL,NULL,'0c38efde-591e-4f0e-b398-18912d112f12',NULL,'2013-12-16 11:26:10','155665',0,'155665',NULL,'NULL',NULL,'NULL','2013-12-16 11:26:10',NULL,NULL),('usera','usera',NULL,NULL,NULL,'145eb98c-97b0-406c-9271-b5726d39847b',NULL,'2013-12-16 10:54:08','155378',0,'155378',NULL,'NULL',NULL,'NULL','2013-12-16 10:54:08',NULL,NULL),('ljw','ljw','Organization','NULL','NULL','19dc612f-7156-4ec5-bd87-0b1e789e20a9','NULL','2014-03-19 05:51:46','19927',0,'19927','NULL','\'NULL\'','ljw','<h1><span style=\"color:#00FFFF;\"><span style=\"font-size: 72px;\"><u><em><strong>我们e想法是</strong></em></u></span></span></h1>\r\n','2014-03-19 05:51:46','NULL','19930'),('usera','usera',NULL,NULL,NULL,'1ad663c5-6575-48ad-800a-88ece12b8582',NULL,'2013-12-16 11:10:34','155520',0,'155520',NULL,'NULL',NULL,'NULL','2013-12-16 11:10:34',NULL,NULL),('admin','admin','Organization','kenny yang','ergf','1ce6cfa9-83ab-440e-8835-dc35ccc828e7','NULL','2014-06-25 06:09:58','153382',0,'153382','asd','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/admin/1_20140625060958_pic8.png','admin',NULL,'2014-06-25 06:09:58','sdfc','153385'),('admin','admin','sales','Exelanz Admin,Exelanz James','dffdfd ','2096aa39-88e7-4c7f-aa4d-53e6274dcb90','1234567890','2014-02-19 07:41:35','7825',0,'7825','dsdsds','\\NULL\\','admin','<p>dsdsds dsdsd</p>\r\n','2014-02-19 06:53:07','sdsds','7995'),('usera','usera',NULL,NULL,NULL,'21cc6080-a570-4804-9f7c-eda07bb36d18',NULL,'2013-12-16 12:38:33','156164',0,'156164',NULL,'NULL',NULL,'NULL','2013-12-16 12:38:33',NULL,NULL),('usera','usera',NULL,NULL,NULL,'2fa615c9-498c-4631-a0d5-f6de9efe4604',NULL,'2013-12-16 08:59:50','155037',0,'155037',NULL,'NULL',NULL,'NULL','2013-12-16 08:59:50',NULL,NULL),('usera','usera',NULL,NULL,NULL,'38e432f9-223d-4c99-9e88-995d034da7bd',NULL,'2013-12-16 11:15:42','155591',0,'155591',NULL,'NULL',NULL,'NULL','2013-12-16 11:15:42',NULL,NULL),('admin','admin','sales','Development Admin','d','3bfd49e7-870d-4601-9c0a-68a6f895f982','9884778888','2014-02-19 09:42:07','8839',0,'8839','asdf','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/admin/1_20140219094207_karthi (1).txt','admin',NULL,'2014-02-19 09:42:07','dsfdfdf\r\ndsfds\r\n\r\ndsfdsf','8842'),('TestUser1','TestUser1',NULL,NULL,NULL,'4534544d-1f01-4f0b-a474-3efff90dd5ec',NULL,'2013-12-18 13:44:04','166548',0,'166548',NULL,'NULL',NULL,'NULL','2013-12-18 13:44:04','cvbcvb','166551'),('TestUser1','TestUser1',NULL,NULL,NULL,'45d94a63-09e5-4e3d-bb42-a75b7db41cf4',NULL,'2013-12-17 13:39:20','160527',0,'160527',NULL,'NULL',NULL,'NULL','2013-12-17 13:39:20',NULL,NULL),('ljw','ljw','宣传部','NULL','NULL','45f70daf-3b03-49cf-bb5f-814056cb8c7c','123456789098','2014-04-25 07:41:18','99889',0,'99889','NULL','\'NULL\'','ljw','\'NULL\'','2014-04-25 07:41:18','NULL','99892'),('admin','admin','sales','Exelanz Admin','NULL','5024d29b-5a49-4b48-af1f-c728dd125785','NULL','2014-02-22 07:11:43','13150',0,'13150','gfhgf','\'NULL\'','admin','<p>eee</p>\r\n','2014-02-22 07:11:43','hfghgfh','13153'),('usera','usera',NULL,NULL,NULL,'507dad1f-82a1-4d55-b514-a22a79cb7038',NULL,'2013-12-16 12:41:41','156238',0,'156238',NULL,'NULL',NULL,'NULL','2013-12-16 12:41:41',NULL,NULL),('james','james',NULL,NULL,NULL,'515f7f04-d6b2-4239-b55b-fabb44227c79',NULL,'2013-12-20 05:49:16','175371',0,'175371',NULL,'NULL',NULL,'\'<p>ertt</p><br />\'','2013-12-20 05:49:16','retret','175374'),('testuser','testuser',NULL,'test user',NULL,'5268a6b6-3cb2-42df-adad-d8e0482a1bcc',NULL,'2013-12-23 06:19:56','182665',0,'182665','test','D:\\eazytec\\master\\source\\easybpm\\target\\bpm-1.0\\resources/testuser/2_20131223061955_license.xml',NULL,NULL,'2013-12-23 06:18:02','test','182731'),('admin','admin','sales','Exelanz Admin,srini vas','dsffffffffff  ','54358273-0afb-4ae5-8276-6e2ed28ce83a','NULL','2014-03-03 07:13:11','27149',0,'27149','ssddddddddddddd','\\NULL\\','admin','<p>dffffffffffffffffffffffffffffff</p>\r\n','2014-03-03 07:10:17','sddddddddddddd','27313'),('james','james',NULL,NULL,NULL,'5aefc88c-da97-4ce5-b833-d62de00459f8',NULL,'2013-12-19 14:31:00','173903',0,'173903','sd','NULL',NULL,'NULL','2013-12-19 14:30:07','dfdf','173960'),('admin','admin','karthi_dep','Exelanz Admin','NULL','5d23cea8-3bce-4092-9ac0-ab0084d4af95','SS','2014-02-21 08:13:40','11571',0,'11571','SD','\\NULL\\','SS','<p>asas</p>\r\n\r\n<p>asas</p>\r\n\r\n<p>asas</p>\r\n\r\n<p>asas</p>\r\n','2014-02-21 08:08:43','SD','11647'),('james','admin','internal sales','Exelanz Admin','s ','60d7836f-8f90-4d8c-868d-dd16ed4a44a6','s','2014-02-24 05:39:46','12136',0,'12136','s','\\NULL\\','admin','<p>sd</p>\r\n\r\n<p>sd</p>\r\n\r\n<p>sd</p>\r\n','2014-02-21 10:05:06','s','12212'),('admin','admin','sales','Exelanz Admin','ssss','631a8d37-5f3f-4c8c-8ec5-b1be81ea27b7','12345678965','2014-02-22 06:45:58','12865',0,'12865','dddd','\'NULL\'','admin','<p>this is the test message which is writing to check the cke editor</p>\r\n','2014-02-22 06:45:58','affair','12868'),('LJW','LJW',NULL,'AR Srinivas',NULL,'676d4191-bc64-46d2-82a3-20b3fa6ad1c7',NULL,'2013-12-20 08:00:32','177253',0,'177253',NULL,'NULL',NULL,'NULL','2013-12-20 08:00:32','fdfd','177256'),('admin','admin','sales','Exelanz Admin','My Test Local','699d480d-bd89-4311-ac18-2905a17ad552','8122','2014-02-24 14:42:44','16055',0,'16055','Test','\'NULL\'','admin','<p>Data Set</p>\r\n','2014-02-24 14:42:44','AffairValue','16058'),('TestUser1','TestUser1',NULL,NULL,NULL,'69b439b5-1bf0-4a1f-8cec-5ba27d01b2a9',NULL,'2013-12-18 12:23:13','166047',0,'166047',NULL,NULL,NULL,'NULL','2013-12-18 12:23:13','bvcbcvb','166050'),('usera','usera',NULL,NULL,NULL,'6d2dcf07-e680-4f7e-b2fb-31085bdf0da1',NULL,'2013-12-06 07:02:20','135628',0,'135628',NULL,'NULL',NULL,'NULL','2013-12-06 07:02:20',NULL,'135631'),('ljw','ljw','宣传部','L Dejor','NULL','6e01c0a1-a60b-46a5-9589-b884cf72294c','NULL','2014-06-06 02:02:11','138247',0,'138247','we','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/ljw/1_20140606020211_Some bugs solve methods.docx','ljw',NULL,'2014-06-06 02:02:11','we','138250'),('usera','usera',NULL,NULL,NULL,'75745619-aee6-4d8b-bf3f-0d22ab026ffd',NULL,'2013-12-16 09:31:07','155114',0,'155114',NULL,'NULL',NULL,'NULL','2013-12-16 09:31:07',NULL,NULL),('srinivas','srinivas',NULL,NULL,NULL,'75a456af-0eac-4faf-8db4-a7039b3cbb33',NULL,'2013-12-17 05:40:25','157021',0,'157021',NULL,'NULL',NULL,'NULL','2013-12-17 05:40:25',NULL,NULL),('user','user',NULL,'Tomcat User',NULL,'7d0aaa70-eadf-4345-ab5e-7838dc866bb2',NULL,'2013-12-20 15:34:41','180052',0,'180052',NULL,'NULL',NULL,'NULL','2013-12-20 15:32:50','dsdsdsds','180121'),('zhuqa','zhuqa','Organization','NULL','NULL','7d37862e-70e4-4446-8137-9bb112d631ba','1333','2014-06-05 08:48:48','137983',0,'137983','NULL','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/zhuqa/3_20140605084847_444.jpg','zhuqa',NULL,'2014-06-05 08:48:48','NULL','137986'),('admin','admin',NULL,NULL,NULL,'804b3880-ec81-46cc-a995-678963276554',NULL,'2014-02-24 13:43:23','12951',0,'12951',NULL,NULL,NULL,'<p>dfdsfgdgfdg</p>\r\n','2014-02-22 06:59:07',NULL,'12954'),('usera','usera',NULL,NULL,NULL,'886eacb1-b0e8-4fa7-b818-f7e6c3780364',NULL,'2013-12-06 05:41:54','133423',0,'133423',NULL,'NULL',NULL,'NULL','2013-12-06 05:41:54',NULL,'133426'),('usera','usera',NULL,NULL,NULL,'89902c3e-bcbc-43e0-987c-350d9e2521ed',NULL,'2013-12-16 09:55:21','155221',0,'155221',NULL,'NULL',NULL,'NULL','2013-12-16 09:55:21',NULL,NULL),('usera','usera',NULL,NULL,NULL,'8d8b9fd9-023e-4411-a5de-9f69a55ec327',NULL,'2013-12-16 07:30:09','154963',0,'154963',NULL,'NULL',NULL,'NULL','2013-12-16 07:30:09',NULL,NULL),('admin','admin','sales','NULL','NULL','8ee20e31-5a9a-46e1-8a7d-802e252001d2','NULL','2014-03-10 09:15:03','42173',0,'42173','NULL','\'NULL\'','admin','\'NULL\'','2014-03-10 09:15:03','NULL','42176'),('Kuser4','Kuser4','Organization','kenny yang','ee','911a9869-6fa5-44db-859f-41b6f6995634','NULL','2014-06-25 07:18:08','153880',0,'153880','rrr','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/Kuser4/1_20140625071808_pic8.png','Kuser4',NULL,'2014-06-25 07:18:08','eeee','153883'),('admin','james',NULL,'Exelanz James',NULL,'91c6e444-0f0a-412f-8812-13aba4a3a5fd',NULL,'2013-12-20 08:43:03','177359',0,'177359',NULL,'NULL',NULL,'NULL','2013-12-20 08:41:07','test','177486'),('admin','admin','sales','Exelanz Admin','aaa','9295f61a-aef6-410f-805f-551bc7f4ea9a','aaa','2014-02-21 09:17:15','11860',0,'11860','aaa','\'NULL\'','admin','<p>aaaa</p>\r\n','2014-02-21 09:17:15','aaa','11863'),('admin','admin',NULL,NULL,NULL,'93407431-0fa6-4a3f-ab41-d3927ad07a63',NULL,'2013-11-13 11:19:22','93981',0,'93981',NULL,'NULL',NULL,'NULL','2013-11-13 11:19:22',NULL,'93984'),('TestUser1','TestUser1',NULL,NULL,NULL,'9fa213de-cc64-42ef-aa10-924a6caa5869',NULL,'2013-12-18 13:25:08','166229',0,'166229',NULL,NULL,NULL,'NULL','2013-12-18 13:25:08','cdfs','166232'),('admin','admin','sales','Exelanz Admin','NULL','a8930585-05f8-4ddd-b6ba-8e02b60701ce','11111111111','2014-02-19 02:26:16','7712',0,'7712','testtest','\'NULL\'','admin','<p>testtest</p>\r\n','2014-02-19 02:26:16','testtest','7715'),('admin','admin','sales','Exelanz Admin','df','a8c445af-8501-4faa-8e14-01d5b0b24712','NULL','2014-02-19 09:54:29','8968',0,'8968','dsf','\'NULL\'','admin','<p>aaaaaaaa</p>\r\n\r\n<p>aaaaaa</p>\r\n\r\n<p>aaaa</p>\r\n','2014-02-19 09:54:29','sdf','8971'),('srinivas','LJW',NULL,NULL,NULL,'ac0ae978-b9a4-4517-acbf-5d11b67e6b72',NULL,'2013-12-20 05:58:35','175401',0,'175401','test','NULL',NULL,'\\<p>test</p><br />\\','2013-12-20 05:46:37','test','175550'),('admin','admin','Organization','Exelanz Admin','NULL','ad749bdb-2722-48c6-aa36-abad7e0e345b','NULL','2014-05-16 14:44:39','121442',0,'121442','dfdf','\'NULL\'','admin','\'NULL\'','2014-05-16 14:44:39','dfdf','121445'),('admin','admin','sales','Exelanz Admin','dfgdf','b5f2599b-7fdb-4247-9f00-176a9c42799f','NULL','2014-02-24 15:30:45','16352',0,'16352','dfgfd','\'NULL\'','admin','<p>dfgfd</p>\r\n','2014-02-24 15:30:45','dfg','16355'),('admin','admin','sales','Exelanz Admin','NULL','b9094bbe-abf8-41de-9d10-071c82e416a0','NULL','2014-02-20 06:19:30','171',0,'171','NULL','\\NULL\\','admin','\\NULL\\','2014-02-14 15:35:28','asdds','247'),('james','james',NULL,NULL,NULL,'b9a6c7e6-b9f0-431c-8b4a-b003c5724575',NULL,'2013-12-19 14:11:28','173839',0,'173839','sdsd','NULL',NULL,'NULL','2013-12-19 14:11:28','sdsd','173842'),('admin','admin',NULL,NULL,NULL,'bbb6062d-29d2-46d5-91f4-d469d3750375',NULL,'2014-02-22 07:00:39','12991',0,'12991',NULL,NULL,NULL,'<p>sdsadsafddsf</p>\r\n','2014-02-22 07:00:39',NULL,'12994'),('usera','usera',NULL,NULL,NULL,'bd41d5d6-595e-40ae-aef7-e7f048963bdb',NULL,'2013-12-16 11:56:45','155848',0,'155848',NULL,'NULL',NULL,'NULL','2013-12-16 11:56:45',NULL,NULL),('usera','usera',NULL,NULL,NULL,'bf1250f1-fce5-41e8-a44f-bf16176592dc',NULL,'2013-12-16 13:30:33','156415',0,'156415',NULL,'NULL',NULL,'NULL','2013-12-16 13:30:33',NULL,NULL),('admin','admin','Organization','ExelanzAdmin','NULL','c17d05de-a4c3-487d-ac1c-8a329b414e61','NULL','2014-05-16 06:17:40','120900',0,'120900','NULL','\'NULL\'','admin','\'NULL\'','2014-05-16 06:17:40','dsad','120903'),('usera','usera',NULL,NULL,NULL,'c3c659be-1d40-4da6-851e-62b125814505',NULL,'2013-12-16 10:41:53','155295',0,'155295',NULL,'NULL',NULL,'NULL','2013-12-16 10:41:53',NULL,NULL),('admin','admin',NULL,'Exelanz James',NULL,'c8451cf5-5083-4278-b706-a3eeee4352b8',NULL,'2013-12-20 15:29:17','179981',0,'179981',NULL,'NULL',NULL,'NULL','2013-12-20 15:29:17','sdff','179984'),('TestUser1','TestUser1',NULL,NULL,NULL,'cb2d3a94-485a-4c60-b454-36ed524bba5f',NULL,'2013-12-18 13:31:15','166338',0,'166338',NULL,NULL,NULL,'NULL','2013-12-18 13:31:15','cvxv','166341'),('usera','usera',NULL,NULL,NULL,'db1989fe-ae70-4aba-9140-efec97565ec1',NULL,'2013-12-16 12:37:02','156090',0,'156090',NULL,'NULL',NULL,'NULL','2013-12-16 12:37:02',NULL,NULL),('yanghaiyun','yadmin',NULL,'杨 海云',NULL,'db61a36d-4fc4-4f34-92a6-6a9daf67c40d','11111111111','2013-12-23 06:14:19','182455',0,'182455','test','NULL',NULL,'\\<p>测试测试</p><br />\\','2013-12-23 06:11:27','测试','182596'),('usera','usera',NULL,NULL,NULL,'dd5a56f5-00d4-4fa7-a718-dc5d6d263327',NULL,'2013-12-18 15:16:07','167861',0,'167861','aaaa','NULL',NULL,'\\<p>sdsddsdssdds</p><br />\\','2013-12-18 15:07:59','test','167929'),('yhy','admin','Organization','test user','NULL','e05bec23-07b5-4a40-ab05-0c45231315c1','NULL','2014-03-05 01:39:22','33106',0,'33106','NULL','\\NULL\\','admin','<p>fffff</p>\r\n','2014-03-05 01:37:50','ffff','33258'),('kenny','kenny','NULL','NULL','NULL','e13532f9-73eb-467c-8fd9-f799ebe952b9','ww','2014-06-25 02:28:03','153182',0,'153182','ww','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/kenny/3_20140625022803_66.txt','www',NULL,'2014-06-25 02:28:03','ww','153185'),('admin','admin','sales','Exelanz Admin','NULL','e55903ab-0dc6-4482-aa56-c6627c4727e5','NULL','2014-02-22 07:07:01','13058',0,'13058','sdds','\'NULL\'','admin','<p><em>sddddddddddddddddddddddddddddd</em> <strong>sdddddddddddddddddddddddddddddddddddddddf</strong> sdddddd</p>\r\n\r\n<p><u>sdfsf</u></p>\r\n\r\n<p><span style=\"color:#A52A2A;\">dfdfdfd</span></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><span style=\"background-color:#FFD700;\">dfdfdf</span></p>\r\n\r\n<p><span style=\"font-size:20px;\"><span style=\"font-family: verdana,geneva,sans-serif;\">dfdfdf</span></span></p>\r\n\r\n<p>dfdfd</p>\r\n\r\n<p>&nbsp;</p>\r\n','2014-02-22 07:07:01','sdsd','13061'),('fg','fg','行政人事部','f g','NULL','e5cdb8d8-b079-4d60-a54e-488a09adb6c8','NULL','2014-04-11 02:57:29','79042',0,'79042','rrf','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/fg/2_20140411025729_Desert.jpg','fg',NULL,'2014-04-11 02:57:29','ff','79045'),('admin','admin','sales','Exelanz Admin','oppinion','e94279a0-dfc4-4939-8606-a0983f53f72e','NULL','2014-02-24 14:35:28','15952',0,'15952','tesdt','\'NULL\'','admin','<p>bbbbbbb</p>\r\n','2014-02-24 14:35:28','aaaa','15955'),('usera','usera',NULL,NULL,NULL,'e9879884-4ec8-48ae-a2ec-a77ed5ce5d0c',NULL,'2013-12-16 11:03:18','155449',0,'155449',NULL,'NULL',NULL,'NULL','2013-12-16 11:03:18',NULL,NULL),('usera','usera',NULL,'Kevin Pietersen',NULL,'ecc17dc4-91c4-460e-8abf-1c675dd9f147','dsdssdsd','2013-12-23 05:57:02','182290',0,'182290','ssddssd','NULL',NULL,'\\<p>dfdf</p><br />\\','2013-12-23 05:54:38','aaaaa','182362'),('admin','admin','sales','Exelanz Admin,DevOne Admin,Development Admin','dfdfd ','ef650709-b54f-4a58-9b96-d4977256eea5','NULL','2014-03-07 07:01:57','39125',0,'39125','rtedf','\\NULL\\','admin','<p>fddddddddddd</p>\r\n\r\n<p>fgfgfg</p>\r\n\r\n<p>fgf</p>\r\n\r\n<p>gfg</p>\r\n\r\n<p>fgf</p>\r\n\r\n<p>gf</p>\r\n\r\n<p>gfgf</p>\r\n','2014-03-07 07:00:14','dfdfdf','39203'),('usera','usera',NULL,NULL,NULL,'f591eefc-4b26-4c80-bc70-cad48c65a238',NULL,'2013-12-06 07:00:48','135574',0,'135574',NULL,'NULL',NULL,'NULL','2013-12-06 07:00:48',NULL,'135577');
/*!40000 ALTER TABLE `REQUESTINSTRUCTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SAMPLETABLEFORREQINSTRUCTIONINNEWUI`
--

DROP TABLE IF EXISTS `SAMPLETABLEFORREQINSTRUCTIONINNEWUI`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SAMPLETABLEFORREQINSTRUCTIONINNEWUI` (
  `TASK_ID` varchar(100) DEFAULT NULL,
  `ID` varchar(100) NOT NULL,
  `MODIFIEDBY` varchar(100) DEFAULT NULL,
  `MODIFIEDTIME` datetime DEFAULT NULL,
  `CREATEDTIME` datetime DEFAULT NULL,
  `CREATEDBY` varchar(100) DEFAULT NULL,
  `PROC_INST_ID` varchar(100) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `EXECUTION_ID` varchar(100) NOT NULL,
  `IS_DELETE` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SAMPLETABLEFORREQINSTRUCTIONINNEWUI`
--

LOCK TABLES `SAMPLETABLEFORREQINSTRUCTIONINNEWUI` WRITE;
/*!40000 ALTER TABLE `SAMPLETABLEFORREQINSTRUCTIONINNEWUI` DISABLE KEYS */;
/*!40000 ALTER TABLE `SAMPLETABLEFORREQINSTRUCTIONINNEWUI` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PUBLISHFILE`
--

DROP TABLE IF EXISTS `PUBLISHFILE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PUBLISHFILE` (
  `SENDTO` varchar(255) DEFAULT NULL,
  `CREATETIME` date DEFAULT NULL,
  `CCTODEP` varchar(255) DEFAULT NULL,
  `CREATOR` varchar(200) DEFAULT NULL,
  `EXECUTION_ID` varchar(100) NOT NULL,
  `REG1` varchar(100) DEFAULT NULL,
  `MODIFIEDTIME` datetime DEFAULT NULL,
  `ID` varchar(100) NOT NULL,
  `IS_DELETE` tinyint(1) DEFAULT '0',
  `PROC_INST_ID` varchar(100) NOT NULL,
  `REG3` varchar(100) DEFAULT NULL,
  `SUBJECTKEYWORDS` varchar(255) DEFAULT NULL,
  `TASK_ID` varchar(100) DEFAULT NULL,
  `CHARGELEADEROPINION` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `PUBLISHFILEATTACHMENT` varchar(255) DEFAULT NULL,
  `CREATEDTIME` datetime DEFAULT NULL,
  `REG2` varchar(100) DEFAULT NULL,
  `ISOFFICERCHECK` varchar(100) DEFAULT NULL,
  `PUBLISHORG` varchar(250) DEFAULT NULL,
  `ISCOMPLETE` varchar(100) DEFAULT NULL,
  `URGENCY` varchar(100) DEFAULT NULL,
  `CREATEDBY` varchar(100) DEFAULT NULL,
  `LEADEROPINION` varchar(255) DEFAULT NULL,
  `AUTOGENID` varchar(100) DEFAULT NULL,
  `CCTOUSER` varchar(255) DEFAULT NULL,
  `MODIFIEDBY` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PUBLISHFILE`
--

LOCK TABLES `PUBLISHFILE` WRITE;
/*!40000 ALTER TABLE `PUBLISHFILE` DISABLE KEYS */;
INSERT INTO `PUBLISHFILE` VALUES ('Exelanz James',NULL,'NULL','admin','41','NULL','2014-02-14 15:20:57','014c4e9b-5370-4a4a-ab36-e3ff0960186d',0,'41','NULL','NULL','44','NULL','test','\'NULL\'','2014-02-14 15:20:57','NULL','NULL','test','NULL','Normal','admin','NULL','EZTC021','NULL','admin'),('Exelanz James','2013-12-19',NULL,'james','174137',NULL,'2013-12-19 15:09:20','01a48752-e05b-4bd2-a7f7-6377b79c09ae',0,'174137',NULL,NULL,'174550',NULL,'test','NULL','2013-12-19 15:06:28',NULL,NULL,'dsffd',NULL,'Normal','james','op','EZTC003',NULL,'james'),('Exelanz Admin',NULL,NULL,NULL,'116509',NULL,'2013-12-23 12:17:56','03465b98-882a-44eb-9eab-bf72181f99bc',0,'116509',NULL,NULL,'116583',NULL,'test','NULL','2013-11-25 10:17:03',NULL,NULL,NULL,NULL,'normal','admin',NULL,NULL,'james','admin'),('Exelanz James','2013-12-20',NULL,'james','177047',NULL,'2013-12-20 07:05:04','0385076c-48c4-46f9-885c-3fec9da28bc3',0,'177047',NULL,NULL,'177050',NULL,'test','NULL','2013-12-20 07:05:04',NULL,NULL,'test',NULL,'Normal','james',NULL,'EZTC007','Tomcat User','james'),('admin','2013-10-23',NULL,'admin','1041',NULL,'2013-10-23 16:10:39','056f0b3a-8c8a-40a0-9c4c-fab59b628320',0,'1041',NULL,NULL,NULL,NULL,'test','NULL','2013-10-23 16:10:39',NULL,NULL,'Etec',NULL,'normal','admin',NULL,NULL,NULL,'admin'),('Exelanz James',NULL,'HR Department',NULL,'134282',NULL,'2013-12-06 06:12:56','08e5e993-53ed-4f4d-a247-f1507a5c34d9',0,'134282',NULL,NULL,'134285',NULL,'fgdfg','NULL','2013-12-06 06:12:56',NULL,NULL,NULL,NULL,'normal','james',NULL,NULL,NULL,'james'),('Exelanz Admin',NULL,NULL,NULL,'145070',NULL,'2013-12-10 11:55:38','09f5fb9e-5252-4d3e-9625-d6e4dcbb44e9',0,'145070',NULL,NULL,'145284',',Charge leader opinion','test','NULL','2013-12-10 11:48:54',NULL,NULL,'Test','true','normal','admin',',test approval',NULL,NULL,'admin'),('f g,fang gao',NULL,'gf','NULL','73026','dd','2014-04-09 09:10:37','0a1c5100-db48-4b21-bd67-d848fd6dbf24',0,'73026','dd','NULL','74138','NULL','NULL','\\NULL\\','2014-04-09 06:45:19','d','NULL','22 gf','NULL','high','gf','NULL','EZTC080','NULL','gf'),('admin',NULL,NULL,NULL,'70836',NULL,'2013-11-05 06:19:35','0e61344c-2f7a-4cc0-b8a0-73331830aff9',0,'70836',NULL,NULL,'70928',NULL,'dsf','NULL','2013-11-05 06:18:47',NULL,'false',NULL,NULL,'normal','admin',NULL,NULL,NULL,'admin'),('Exelanz Admin',NULL,'NULL',NULL,'131036',NULL,'2014-05-27 11:18:46','0e9d2d09-3322-4683-9153-1a056cb77cec',0,'131036',NULL,'NULL','131181',NULL,NULL,'\'NULL\'','2014-05-27 11:18:46',NULL,'NULL',NULL,NULL,'high','admin','NULL',NULL,'Exelanz Admin','admin'),('Exelanz Admin','2014-02-21','NULL','admin','11401','NULL','2014-02-21 07:41:49','0eaa35bc-ef66-4f58-9313-cc7599bd6d7f',0,'11401','NULL','NULL','11404','NULL','aaaaa','\'NULL\'','2014-02-21 07:41:49','NULL','NULL','aaaaaaa','NULL','Normal','admin','NULL','EZTC030','NULL','admin'),('qa f',NULL,'NULL','gf','98164','NULL','2014-04-23 10:01:13','0ee3f11b-2c6e-4b53-8456-dd52309b3e6e',0,'98164','EZTC102','NULL','98396','f','ff','\\NULL\\','2014-04-23 09:59:52','NULL','true','ffe','true','Normal','gf','g','EZTC102','qa f','gf'),('Exelanz James',NULL,NULL,NULL,'130448',NULL,'2013-12-05 06:41:48','11529372-e846-43cb-9cb6-a0ad36862cba',0,'130448',NULL,NULL,'130769',NULL,'test','NULL','2013-12-05 05:51:27',NULL,NULL,NULL,'true','normal','james',NULL,NULL,'user','james'),('yang haiyun','2014-03-10','NULL','testuser','41080','NULL','2014-03-10 01:14:45','127ee807-370e-4dbe-850c-84aa3b69bf9f',0,'41080','NULL','NULL','41083','NULL','test','\'NULL\'','2014-03-10 01:14:45','NULL','NULL','test','NULL','Normal','testuser','NULL','EZTC033','NULL','testuser'),('Exelanz Admin',NULL,'NULL','admin','135709','NULL','2014-05-30 10:53:01','1660b712-ae2a-4255-b11e-cb5ff4076678',0,'135709','NULL','sdfgfgsdf','135712','NULL','dsaasdfasf','\'NULL\'','2014-05-30 10:53:01','NULL','NULL','fgfgfgdfg','NULL','Normal','admin','NULL','EZTC126','Exelanz Admin','admin'),('NULL',NULL,'NULL','w','72765','w','2014-04-09 06:39:37','168c8f9f-13d6-4977-a5fc-fa904ba60191',0,'72765','w','NULL','72768','NULL','w','\'NULL\'','2014-04-09 06:39:37','w','NULL','dd','NULL','Normal','gf','NULL','EZTC080','fang gao','gf'),('yang haiyun','2014-03-10','NULL','testuser','40965','NULL','2014-03-10 01:06:58','181541c1-a6e6-471a-9b41-6d14bafc6ec6',0,'40965','NULL','NULL','40968','NULL','testuser','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/testuser/1_20140310010658_','2014-03-10 01:06:58','NULL','NULL','testuser','NULL','Normal','testuser','NULL','EZTC032','NULL','testuser'),('Exelanz Admin',NULL,'NULL','karthick','117302','NULL','2014-05-14 08:01:45','1846dac5-0ca0-4e87-bdd8-37a786f28571',0,'117302','NULL','NULL','117305','NULL','test','\'NULL\'','2014-05-14 08:01:45','NULL','NULL','test','NULL','Normal','karthick','NULL','EZTC114','Tomcat User','karthick'),('Exelanz Admin',NULL,'NULL','admin','93245','NULL','2014-04-22 15:59:20','184c0407-2f91-406b-bd33-8048fde1e057',0,'93245','EZTC100','NULL','93477','NULL','test','\\NULL\\','2014-04-22 15:57:56','NULL','true','test','true','Normal','admin','NULL','EZTC100','Exelanz Admin','admin'),('Kevin Pietersen',NULL,NULL,NULL,'153105',NULL,'2013-12-13 12:27:17','1be692f1-bd14-4402-a2d4-be9092482f03',0,'153105',NULL,NULL,'153630',NULL,'sss','NULL','2013-12-13 09:54:22',NULL,NULL,NULL,'true','normal','usera',NULL,NULL,NULL,'usera'),('NULL',NULL,'NULL','NULL','32287','22','2014-03-21 07:09:09','1c0624c6-a917-4d95-8cce-071b4649bbf3',0,'32287','2','NULL','32512','NULL','NULL','\\NULL\\','2014-03-21 07:06:14','2','NULL','fg','NULL','Normal','fg','NULL','EZTC070','NULL','gf'),('admin',NULL,NULL,NULL,'71691',NULL,'2013-11-05 06:33:02','1c14d586-14b8-4076-9e4b-6872c9f2b354',0,'71691',NULL,NULL,'71783',NULL,'gf','NULL','2013-11-05 06:32:32',NULL,NULL,NULL,NULL,'normal','lee',NULL,NULL,NULL,'lee'),('Exelanz Admin','2013-12-23',NULL,'admin','186656',NULL,'2013-12-23 13:30:56','1cd8f741-d510-4a23-b9de-c043809cc44c',0,'186656',NULL,'te','186861',NULL,'test','NULL','2013-12-23 13:27:35',NULL,NULL,'test',NULL,'Normal','admin',NULL,'EZTC017',NULL,'admin'),(NULL,NULL,NULL,NULL,'172101',NULL,'2013-12-19 10:53:42','1f01d6e6-bc32-4fb5-a5db-102d68b74639',0,'172101','EZTC013',NULL,'172104',NULL,NULL,'NULL','2013-12-19 10:53:42',NULL,NULL,NULL,NULL,'Normal','james',NULL,NULL,NULL,'james'),('q a',NULL,'NULL','a','75484','NULL','2014-04-23 07:46:36','1f244a70-338f-4c5b-87c4-8da898dff7ba',0,'75484','EZTC085','NULL','76094','NULL','jjk','\\NULL\\','2014-04-10 06:51:40','NULL','NULL','ff','NULL','high','a','NULL','EZTC085','f g','a'),('杨 海云',NULL,'NULL','admin','140083','NULL','2014-06-09 01:07:35','1febf7f9-cdf7-4a98-a143-45df2b62f12d',0,'140083','NULL','NULL','140086','NULL','测试','\'NULL\'','2014-06-09 01:07:35','NULL','NULL','测试','NULL','Normal','admin','NULL','EZTC129','杨 海云','admin'),('Exelanz James',NULL,'HR Department',NULL,'134465',NULL,'2013-12-06 06:17:01','21fc08cd-f5a5-4fa2-a88c-7c13f2a2692f',0,'134465',NULL,NULL,'134468',NULL,'dfdf','NULL','2013-12-06 06:17:01',NULL,NULL,NULL,NULL,'normal','james',NULL,NULL,'user','james'),('q a,Exelanz Admin,Development Admin,a fa',NULL,'NULL','NULL','95813','NULL','2014-04-23 07:56:17','23058b60-c958-4668-9123-ad8d9058499b',0,'95813','NULL','NULL','95816','NULL','NULL','\'NULL\'','2014-04-23 07:56:17','NULL','NULL','NULL','NULL','Normal','c','NULL','EZTC099','qa f','c'),('flow user','2013-12-23',NULL,'flowuser','180781',NULL,'2013-12-23 01:47:12','235c175f-a598-4f09-a4f3-380be3dedf1c',0,'180781',NULL,NULL,'180987',NULL,'a','NULL','2013-12-23 01:46:00',NULL,NULL,'num',NULL,'Normal','flowuser',NULL,'EZTC010',NULL,'flowuser'),('Exelanz Admin',NULL,NULL,NULL,'116669',NULL,'2013-11-25 10:31:05','251dd58e-39ce-434b-8965-51858aafbd8f',0,'116669',NULL,NULL,'116672',NULL,'teee','NULL','2013-11-25 10:31:05',NULL,NULL,NULL,NULL,'normal','admin',NULL,NULL,'james','admin'),('L Dejor','2014-03-19','NULL','ljw','18055','NULL','2014-03-19 03:24:07','25860db7-37ef-4f4d-a7bf-00ff85fb530b',0,'18055','NULL','雨水','18058','NULL','今天下雨','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/ljw/2_20140319032406_背景图片.jpg','2014-03-19 03:24:07','NULL','NULL','测试','NULL','Normal','ljw','NULL','EZTC039','Mr 克里斯','ljw'),('NULL',NULL,'NULL','NULL','21148','322323','2014-05-06 08:22:21','25f91147-3a3f-4d1b-a07f-f152894db327',0,'21148','2222','NULL','21261','NULL','NULL','\\NULL\\','2014-03-19 06:24:13','2222','NULL','gf','NULL','Normal','gf','NULL','EZTC047','NULL','c'),('李 俊伟',NULL,'NULL','ljw','181065','NULL','2014-07-17 07:55:58','27030005-9488-43b4-9368-32f51c4c7ede',0,'181065','NULL','asd','181068','NULL','dds','\'NULL\'','2014-07-17 07:55:58','NULL','NULL','das','NULL','Normal','ljw','NULL','EZTC136','L Dejor','ljw'),('fang gao',NULL,'NULL','NULL','63947','d','2014-04-02 07:21:16','281bb79d-e65f-4ee9-8007-fe0ea9e82cd8',0,'63947','d','NULL','64057','NULL','NULL','\\NULL\\','2014-04-02 03:21:56','d','NULL','gf','NULL','Normal','gf','NULL','EZTC076','NULL','gf'),('auto user',NULL,NULL,NULL,'150242',NULL,'2013-12-12 07:34:45','2b21e4d2-6bf9-4e28-975a-7d1acef95376',0,'150242',NULL,NULL,'150323',NULL,'aut1','NULL','2013-12-12 07:33:14',NULL,'true','aut',NULL,'normal','autouser','同意',NULL,NULL,'autouser'),('fang gao',NULL,'gf','NULL','64111','trt','2014-04-02 07:18:31','2dd73b18-8ba1-4b8b-a8d5-4b758ea9efbd',0,'64111','ry','NULL','64795','NULL','NULL','\\NULL\\','2014-04-02 05:39:17','jijh','NULL','gf','NULL','Normal','gf','NULL','EZTC077','NULL','gf'),('admin',NULL,NULL,NULL,'78057',NULL,'2013-11-06 10:44:50','2e62f303-9b70-4cd9-b1fb-5d5156a2e2bc',0,'78057',NULL,NULL,'78248',NULL,'test','NULL','2013-11-06 10:42:29',NULL,NULL,NULL,NULL,'high','admin',NULL,NULL,NULL,'admin'),('NULL',NULL,'NULL','NULL','86414','NULL','2014-04-17 09:31:59','2fc86f2b-5322-489f-b066-ab152291e5fc',0,'86414','NULL','NULL','86664','NULL','NULL','\\NULL\\','2014-04-17 09:29:45','NULL','NULL','NULL','NULL','Normal','admin','NULL','EZTC095','qa f','admin'),('NULL',NULL,'NULL','NULL','97795','NULL','2014-04-23 09:02:24','32b89e04-4398-4b3f-8009-02dd011fed32',0,'97795','NULL','NULL','97798','NULL','NULL','\'NULL\'','2014-04-23 09:02:24','NULL','NULL','NULL','NULL','Normal','ff','NULL','EZTC101','q a','ff'),('杨 海云',NULL,'A组','admin','140331','NULL','2014-06-09 01:21:57','32d6ab9d-a10d-4398-87c3-bd026a36738e',0,'140331','NULL','测试','140334','NULL','测试','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/admin/1_20140609012157_myhtml.png','2014-06-09 01:21:57','NULL','NULL','测试','NULL','Normal','admin','NULL','EZTC130','杨 海云','admin'),('yang haiyun','2014-02-19','NULL','testuser','8306','NULL','2014-02-19 07:47:36','34935144-f57b-4e5d-8e3e-498132282ac3',0,'8306','NULL','NULL','8309','NULL','testtest','\'NULL\'','2014-02-19 07:47:36','NULL','NULL','testtest','NULL','Normal','testuser','NULL','EZTC027','NULL','testuser'),('admin',NULL,NULL,NULL,'78364',NULL,'2013-11-06 10:59:52','373fd715-e305-46ce-b2b0-d12bcabfa73e',0,'78364',NULL,NULL,'78555',NULL,'aaaa','NULL','2013-11-06 10:57:18',NULL,NULL,NULL,'vcbm','normal','admin',NULL,NULL,NULL,'admin'),('NULL',NULL,'NULL','NULL','84859','NULL','2014-04-17 02:38:13','3c20c7a6-31c2-4b5d-b44e-67886cf15b5c',0,'84859','NULL','NULL','84862','NULL','NULL','\'NULL\'','2014-04-17 02:38:13','NULL','NULL','NULL','NULL','Normal','gf','NULL','EZTC092','q a','gf'),('Exelanz Admin',NULL,'NULL','admin','260','NULL','2014-02-17 01:16:23','3ce1fb6f-8749-4e14-b4af-60c346dcf932',0,'260','NULL','NULL','263','NULL','admin','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/admin/1_20140217011622_text.txt','2014-02-17 01:16:23','NULL','NULL','admin','NULL','Normal','admin','NULL','EZTC022','NULL','admin'),('srini vas','2013-12-26','Organization','srinivas','193272',NULL,'2013-12-26 12:34:03','3d23502b-f853-4861-b976-e1ac8817d2f0',0,'193272',NULL,NULL,'193275',NULL,'aaaa','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/srinivas/1_20131226123403_act.jpg','2013-12-26 12:34:03',NULL,NULL,'sdsdsdds',NULL,'Normal','srinivas',NULL,'EZTC019',NULL,'srinivas'),('NULL',NULL,'NULL','NULL','29139','www','2014-03-21 05:22:42','3d25fd40-1ffd-4854-b773-6ea5773966fa',0,'29139','w','NULL','30289','NULL','NULL','\\NULL\\','2014-03-21 02:45:39','w','NULL','gf','NULL','Normal','gf','NULL','EZTC061','NULL','gf'),('yang haiyun','2014-02-19','NULL','testuser','8418','NULL','2014-02-19 07:57:05','3e9043a8-a567-4df3-9c24-a62d98e75a69',0,'8418','NULL','NULL','8421','NULL','tes','\'NULL\'','2014-02-19 07:57:05','NULL','NULL','tes','NULL','Normal','testuser','NULL','EZTC028','NULL','testuser'),('杨 海云',NULL,'后勤部',NULL,'148264',NULL,'2013-12-12 06:01:58','428872e8-343f-4d2e-a5bc-958fae556a78',0,'148264',NULL,NULL,'148669','同意 ','testtest','NULL','2013-12-12 05:36:14',NULL,NULL,'test','true','normal','autouser',',a',NULL,'flowuser','flowuser'),('auto user',NULL,NULL,NULL,'156698',NULL,'2013-12-17 01:15:58','43a71e2c-07ce-4d61-b41e-51cf7b060366',0,'156698',NULL,NULL,'156701',NULL,'testuser','NULL','2013-12-17 01:15:58',NULL,NULL,'aa',NULL,'Normal','testuser',NULL,NULL,NULL,'testuser'),('Exelanz Admin','2014-02-21','NULL','admin','11201','NULL','2014-02-21 07:29:18','45c2b405-911c-4a69-b750-e9f88c259ca7',0,'11201','NULL','NULL','11204','NULL','she','\'NULL\'','2014-02-21 07:29:18','NULL','NULL','she','NULL','Normal','admin','NULL','EZTC029','NULL','admin'),('Kevin Pietersen,new user',NULL,'后勤部',NULL,'128174','aaaaa','2013-12-04 05:52:54','463c83d7-781d-483a-a2c1-d90a9cb8f94f',0,'128174',NULL,'test','128636','test ','test','NULL','2013-12-04 05:28:32',NULL,'yes','dm','yes','high','usera','accept',NULL,'HARISH','usera'),('朱 乾安',NULL,'软件一部',NULL,'148157',NULL,'2013-12-12 06:38:54','469b7577-cd2b-4eee-b692-868f20e3837e',0,'148157',NULL,'发文，发文，','148838','thanks2三 ,我的意见','新发文','NULL','2013-12-12 02:19:46',NULL,NULL,'南京办事处','true','normal','admin',NULL,NULL,'yadmin','zhuqa'),('fang gao',NULL,'NULL','NULL','33382','7','2014-03-25 08:02:34','48841707-b9b6-4734-8db7-a1232e4dd32f',0,'33382','7','NULL','33496','NULL','NULL','\\NULL\\','2014-03-22 03:39:30','7','NULL','gf','NULL','Normal','gf','NULL','EZTC072','NULL','gf'),('Exelanz James','2013-12-19',NULL,'james','169239',NULL,'2013-12-19 07:05:36','48e0d662-6571-4ae2-801b-2df98ddcc15d',0,'169239','EZTC008','test','169924',',ftghfgh',NULL,'NULL','2013-12-19 06:43:28',NULL,'true','test','true','Normal','james',NULL,NULL,NULL,'james'),('杨 海云','2014-03-12','NULL','yhy','3173','NULL','2014-03-12 05:27:20','49f3d0fc-517a-44fd-bfd8-d56a65f791c0',0,'3173','NULL','NULL','3280','NULL','t','\\NULL\\','2014-03-12 05:26:40','NULL','true','t','NULL','Normal','yhy','同意','EZTC036','NULL','yhy'),('NULL',NULL,'NULL','NULL','59418','NULL','2014-03-31 06:58:47','4a0d7870-9590-4370-944f-f0cc89efe461',0,'59418','NULL','NULL','59421','NULL','NULL','\'NULL\'','2014-03-31 06:58:47','NULL','NULL','NULL','NULL','Normal','gf','NULL','EZTC074','NULL','gf'),('auto user',NULL,NULL,NULL,'149120',NULL,'2013-12-12 06:19:46','4a132f48-c8aa-47ce-9ea4-edb410a4dbd7',0,'149120',NULL,NULL,'149123',NULL,'f','\'[object File]\'','2013-12-12 06:19:46',NULL,NULL,'f',NULL,'normal','autouser',NULL,NULL,NULL,'autouser'),('NULL',NULL,'NULL','NULL','98047','NULL','2014-04-23 09:49:13','4a435389-ca3b-4f4d-bf78-ec8bef3201eb',0,'98047','NULL','NULL','98050','NULL','NULL','\'NULL\'','2014-04-23 09:49:13','NULL','NULL','NULL','NULL','Normal','gf','NULL','EZTC109','q a','gf'),('Exelanz Admin','2013-12-20',NULL,'user','179507',NULL,'2013-12-20 15:22:17','4b70eba5-0add-4f7e-9a66-3331eb244b23',0,'179507',NULL,NULL,'179510',NULL,'dssddsds','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/user/1_20131220152217_06murali-vijay.jpg','2013-12-20 15:22:17',NULL,NULL,'fddffd',NULL,'Normal','user',NULL,'EZTC008','Tomcat User','user'),('K UserThree',NULL,'NULL','kenny','168688','444','2014-07-08 07:12:03','4b84083b-ed64-41ba-8cdb-6b79c509b4e1',0,'168688','44444','key words','168851','NULL','title','\'NULL\'','2014-07-08 07:12:03','444','NULL','tt kenny','NULL','high','Kuser4','NULL','EZTC135','亓 佳','Kuser4'),('admin',NULL,'Organization',NULL,'73773',NULL,'2013-11-05 08:56:28','4dd506c8-1547-48d1-b71b-70ffdea8a50b',0,'73773',NULL,NULL,'73777',NULL,'dfgdfd','NULL','2013-11-05 08:56:28',NULL,NULL,NULL,NULL,'normal','admin',NULL,NULL,NULL,'admin'),('Exelanz Admin',NULL,'NULL','ar','92968','NULL','2014-04-22 15:49:59','4ddd09cc-5965-4405-9c37-e24dbadc55af',0,'92968','NULL','NULL','93082','NULL','test','\\NULL\\','2014-04-22 15:49:06','NULL','true','test','NULL','Normal','ar','NULL','EZTC099','Development Admin','admin'),('Tomcat User',NULL,'FACILITIES',NULL,'133879',NULL,'2013-12-06 06:03:14','4e1dfbfe-abed-434a-a9bf-735bce119e70',0,'133879',NULL,'ssss','134100','d ','new til','NULL','2013-12-06 06:00:46',NULL,NULL,NULL,'false','normal','usera',NULL,NULL,'usera','user'),('NULL',NULL,'NULL','NULL','27494','1','2014-03-20 09:06:16','4e9c36d4-b33d-41c1-9821-57e78e0d3e7b',0,'27494','1','NULL','27686','NULL','NULL','\\NULL\\','2014-03-20 09:02:55','1','NULL','gf','NULL','Normal','gf','NULL','EZTC058','NULL','d'),('Exelanz James',NULL,NULL,NULL,'144791',NULL,'2013-12-10 11:08:35','54e096dd-cea7-46a5-bf9d-7b2089921659',0,'144791',NULL,NULL,'144946',',test','test','NULL','2013-12-10 11:06:43',NULL,'true','test','false','normal','james',NULL,NULL,NULL,'james'),('NULL',NULL,'gf','NULL','19504','222222222222','2014-03-19 06:02:43','55360591-c063-4f86-ad3a-4b75b178659a',0,'19504','2222222222222222','NULL','19995','NULL','NULL','\\NULL\\','2014-03-19 05:30:57','111111111111','NULL','gf gf gf','NULL','Normal','gf','NULL','EZTC042','NULL','gf'),('Exelanz Admin',NULL,'NULL','admin','81605','NULL','2014-04-23 08:31:53','5633a5d2-c5ab-4ce2-ad69-2e0e20afc8b5',0,'81605','EZTC091','sfdfdf','97059','NULL','sdsdsdsd','\\NULL\\','2014-04-15 12:14:33','NULL','NULL','dssdsdsd','NULL','Normal','admin','NULL','EZTC091','Exelanz Admin','admin'),('NULL',NULL,'NULL','karthick','136105','NULL','2014-06-02 04:32:50','5785383e-123c-4f87-af2c-27b4f1f5ca65',0,'136105','NULL','NULL','136108','NULL','NULL','\'NULL\'','2014-06-02 04:32:50','NULL','NULL','NULL','NULL','Normal','karthick','NULL','EZTC127','NULL','karthick'),('Exelanz James',NULL,'NULL',NULL,'131036','NULL','2014-05-27 11:21:20','599beddc-4a43-4072-9f11-e9dc4f904e1a',0,'131036','EZTC121','NULL','131477','NULL',NULL,'\'NULL\'','2014-05-27 11:21:20','NULL',NULL,NULL,'NULL','high','admin','NULL',NULL,'Exelanz Admin','admin'),('NULL',NULL,'NULL','NULL','28262','NULL','2014-03-20 11:10:46','5a742b61-b70a-4b94-847f-fc05be5100cb',0,'28262','NULL','NULL','28265','NULL','NULL','\'NULL\'','2014-03-20 11:10:46','NULL','NULL','NULL','NULL','Normal','admin','NULL','EZTC060','NULL','admin'),('NULL',NULL,'NULL','NULL','20912','1112346','2014-03-19 06:30:27','5aae7822-2f29-4f85-b7e5-105a80812f98',0,'20912','66788','NULL','21487','NULL','NULL','\\NULL\\','2014-03-19 06:09:23','yguhgtyu','NULL','gf gf','NULL','Normal','gf','NULL','EZTC046','NULL','gf'),('admin',NULL,NULL,NULL,'73233',NULL,'2013-11-05 08:38:07','5b15b9bf-c32d-427d-a18f-bbbbb2657393',0,'73233',NULL,NULL,'73366',NULL,'aaaa','NULL','2013-11-05 08:36:40',NULL,'true',NULL,'false','normal','admin',NULL,NULL,NULL,'admin'),(NULL,NULL,NULL,NULL,'165844',NULL,'2013-12-18 12:07:00','5c86d128-1f11-4f2b-bcc4-8874447d808c',0,'165844',NULL,NULL,NULL,NULL,NULL,'NULL','2013-12-18 12:07:00',NULL,NULL,NULL,NULL,'Normal','TestUser1',NULL,NULL,NULL,'TestUser1'),('auto user','2013-12-23',NULL,'testuser','183307',NULL,'2013-12-23 07:16:15','5c99846f-7257-41a7-9e90-b97915dfc708',0,'183307',NULL,NULL,'183310',NULL,'aaa','NULL','2013-12-23 07:16:15',NULL,NULL,'aaa',NULL,'Normal','testuser',NULL,'EZTC016',NULL,'testuser'),('f g',NULL,'NULL','fg','79145','NULL','2014-04-23 02:56:53','5ca5deaf-e048-4734-9cc3-5f7a1aa6f9eb',0,'79145','NULL','s','79257','NULL','s','\\NULL\\','2014-04-11 03:00:29','NULL','NULL','s','NULL','Normal','fg','NULL','EZTC088','a fa','ff'),('Exelanz Admin',NULL,'NULL','admin','116347','NULL','2014-05-13 12:21:23','60368ec3-0766-4062-91cf-2de05f7c5cbf',0,'116347','NULL','NULL','116350','NULL','ddd','\'NULL\'','2014-05-13 12:21:23','NULL','NULL','ddd','NULL','Normal','admin','NULL','EZTC112','Exelanz Admin','admin'),('杨 海云',NULL,'后勤部',NULL,'148761',NULL,'2013-12-12 06:13:20','60bce5fd-ce67-4d66-9652-3301ecb16bf0',0,'148761',NULL,NULL,'149008',',同意','title','NULL','2013-12-12 06:07:59',NULL,'true','ge','true','normal','autouser',NULL,NULL,NULL,'flowuser'),('l i,LEE JW',NULL,NULL,NULL,'144139','1123','2013-12-10 07:47:24','614709f8-198f-4a58-a14d-6383490e43fe',0,'144139','asd','会wer辅导费ewe qwe','144359','的方式e温柔 ','准备开会','NULL','2013-12-10 06:48:30','221',NULL,'会议召集',NULL,'normal','reiz',NULL,NULL,'lee','reiz'),('K UserThree',NULL,'NULL','kenny','168688','444','2014-07-08 07:02:36','61d3b11a-a27a-4b50-85f4-33703542884c',0,'168688','44444','key words','168691','NULL','title','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/kenny/2_20140708070236_1.png','2014-07-08 07:02:36','444','NULL','tt','NULL','high','kenny','NULL','EZTC134','亓 佳','kenny'),('NULL',NULL,'NULL','NULL','31084','NULL','2014-03-21 05:30:31','6226f271-80a3-4d1b-a9c7-fce798d1b447',0,'31084','NULL','NULL','31087','NULL','NULL','\'NULL\'','2014-03-21 05:30:31','NULL','NULL','NULL','NULL','Normal','gf','NULL','EZTC065','NULL','gf'),('test user','2013-12-23',NULL,'testuser','182803',NULL,'2013-12-23 06:26:16','637dac9b-dbce-4fe4-989f-50ee904d6b87',0,'182803',NULL,NULL,'182906',NULL,'a','NULL','2013-12-23 06:25:26',NULL,NULL,'a',NULL,'Normal','testuser',NULL,'EZTC015',NULL,'testuser'),('杨 海云',NULL,NULL,NULL,'150085',NULL,'2013-12-12 07:29:14','63e43739-4a34-4343-ac3a-683a1e95c77e',0,'150085',NULL,NULL,'150163',NULL,'teset2','NULL','2013-12-12 07:27:08',NULL,NULL,'test1',NULL,'normal','testuser','同意',NULL,NULL,'yanghaiyun'),('Exelanz Admin',NULL,NULL,NULL,'116749',NULL,'2013-11-25 10:47:06','63eb01f6-85f0-4a4b-8341-677cd9676251',0,'116749',NULL,NULL,'116752',NULL,'qq','NULL','2013-11-25 10:47:06',NULL,NULL,NULL,NULL,'normal','admin',NULL,NULL,'kart123','admin'),('NULL',NULL,'NULL','NULL','22904','NULL','2014-03-19 09:22:12','645aa73f-ed58-4b51-b45c-9d08ee27b342',0,'22904','NULL','NULL','22907','NULL','NULL','\'NULL\'','2014-03-19 09:22:12','NULL','NULL','NULL','NULL','Normal','ff','NULL','EZTC049','NULL','ff'),('admin',NULL,NULL,NULL,'73940',NULL,'2013-11-05 09:07:49','646e611d-fe8b-4e2c-9ef3-4642427e06ab',0,'73940',NULL,NULL,'73944',NULL,'sadsd','NULL','2013-11-05 09:07:49',NULL,NULL,NULL,NULL,'normal','admin',NULL,NULL,'james','admin'),('Exelanz Admin',NULL,'NULL','admin','117806','NULL','2014-05-14 15:42:11','660fd7f3-d152-4687-8001-ef6885ae0dc5',0,'117806','NULL','NULL','118174','NULL','test','\\NULL\\','2014-05-14 15:13:47','NULL','NULL','test','NULL','Normal','admin','NULL','EZTC115','Development Admin','james'),('NULL',NULL,'NULL','NULL','16382','2222222222','2014-03-18 07:33:33','69b238d0-f917-4bf3-a637-e91e08453c37',0,'16382','222222222222222','NULL','16628','NULL','NULL','\\NULL\\','2014-03-18 06:55:05','22222222222','NULL','a','NULL','Normal','a','NULL','EZTC040','NULL','a'),('f g',NULL,'NULL','fg','118809','NULL','2014-05-15 03:02:20','69e64130-a596-4980-9b3a-674e329badd1',0,'118809','NULL','NULL','118927','NULL','tgrrgtg','\\NULL\\','2014-05-15 03:01:29','NULL','NULL','jjhreww','NULL','Normal','fg','NULL','EZTC116','qa f','fg'),('NULL',NULL,'NULL','NULL','25296','11','2014-03-21 02:44:27','6c52339e-d644-4a36-accb-ae0c1fcb734f',0,'25296','11','NULL','29019','NULL','NULL','\\NULL\\','2014-03-20 03:37:31','11','NULL','gf','NULL','Normal','gf','NULL','EZTC054','NULL','gf'),('NULL',NULL,'NULL','NULL','26953','1','2014-03-20 09:01:30','6cdb3a14-a2a0-444b-a09b-62b072f23d38',0,'26953','1','NULL','27403','NULL','NULL','\\NULL\\','2014-03-20 08:55:11','1','NULL','gf','NULL','Normal','gf','NULL','EZTC057','NULL','d'),('auto user','2013-12-23',NULL,'autouser','181503',NULL,'2013-12-23 01:57:41','6f7ace7e-b9e2-4d9d-bb2a-6d24cfb8d452',0,'181503',NULL,NULL,'181506',NULL,'aaa','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/autouser/1_20131223015740_WebPageReader.cs','2013-12-23 01:57:41',NULL,NULL,'aaa',NULL,'Normal','autouser',NULL,'EZTC012',NULL,'autouser'),('Kevin Pietersen','2013-12-23','NETWORK DEPARTMENT','LJW','182001',NULL,'2013-12-23 05:26:52','7078084f-acbf-4b3f-99ce-a0d935b3e286',0,'182001',NULL,NULL,'182004',NULL,'test new','NULL','2013-12-23 05:26:52',NULL,NULL,'ssssss',NULL,'high','LJW',NULL,'EZTC014',NULL,'LJW'),('杨 海云','2013-12-23',NULL,'autouser','181808',NULL,'2013-12-23 02:58:51','71a63e16-ab83-449f-8ee8-4cf2853149e8',0,'181808',NULL,'啊','181811',NULL,'啊','NULL','2013-12-23 02:58:51',NULL,NULL,'啊',NULL,'Normal','autouser',NULL,'EZTC013',NULL,'autouser'),('AR Srinivas','2013-12-19','Organization','srinivas','174856',NULL,'2013-12-19 15:14:54','73516929-44c6-4b58-abb7-b34427efd786',0,'174856','EZTC004','dsdsds','175060',NULL,'assasa','NULL','2013-12-19 15:11:05',NULL,'true','assasasa',NULL,'high','srinivas',NULL,'EZTC004',NULL,'srinivas'),('NULL',NULL,'NULL','NULL','21895','111','2014-03-20 03:26:29','73963e09-8944-43b5-aa0b-a61f907695b9',0,'21895','111','NULL','22271','NULL','NULL','\\NULL\\','2014-03-19 07:45:40','11','NULL','gf','NULL','Normal','gf','NULL','EZTC049','NULL','gf'),('杨 海云','2013-12-23',NULL,'autouser','180370',NULL,'2013-12-23 01:45:08','76e0765c-3e72-4cdf-9aef-1b43bbd2b517',0,'180370','EZTC009',NULL,'180678',NULL,'testnumber','NULL','2013-12-23 01:37:34',NULL,NULL,'number','true','Normal','autouser',NULL,'EZTC009',NULL,'flowuser'),('NULL',NULL,'NULL','NULL','63240','NULL','2014-04-01 08:32:52','7870b84e-d62b-46c2-8082-fbc7bcfd582a',0,'63240','NULL','NULL','63243','NULL','NULL','\'NULL\'','2014-04-01 08:32:52','NULL','NULL','NULL','NULL','Normal','gf','NULL','EZTC075','NULL','gf'),('NULL',NULL,'NULL','NULL','85856','NULL','2014-04-17 09:55:52','78b674a3-626e-4075-ade6-18a5c9462167',0,'85856','NULL','NULL','87569','NULL','NULL','\\NULL\\','2014-04-17 09:05:20','NULL','NULL','NULL','NULL','Normal','gf','NULL','EZTC094','fang gao','admin'),('Kevin Pietersen','2013-12-18',NULL,'usera','167000',NULL,'2013-12-18 14:01:32','78d13ac0-b0b7-48ad-bee3-2395c4468006',0,'167000',NULL,NULL,'167003',NULL,'test','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/usera/1_20131218140132_test.html','2013-12-18 14:01:32',NULL,NULL,NULL,NULL,'Normal','usera',NULL,NULL,'Exelanz James','usera'),('NULL','2014-03-10','NULL','admin','41929','NULL','2014-03-10 09:03:48','79d9d5f7-4ae8-4446-bf3b-55eb6dbc4730',0,'41929','NULL','NULL','41932','NULL','NULL','\'NULL\'','2014-03-10 09:03:48','NULL','NULL','NULL','NULL','Normal','admin','NULL','EZTC034','NULL','admin'),('kenny yang',NULL,'kuser1','222','118401','11','2014-05-15 02:41:00','7b056625-1946-47f3-b147-5cc8a7fdad62',0,'118401','22','22','118640','33','332','\\NULL\\','2014-05-15 02:37:24','11','33','yxptest kenny','44','Normal','kenny','33','EZTC116','kenny yang','Kuser2'),('杨 海云','2014-03-19','NULL','zhangs','17498','NULL','2014-03-19 01:24:32','7ba30558-8200-47f4-be18-2c447c9769f4',0,'17498','NULL','NULL','17605','NULL','fefefefe','D:\\eazytec\\master\\source\\easybpm\\target\\bpm-1.0\\resources/yhy/1_20140319012432_yuyu.jsp','2014-03-19 01:20:34','NULL','NULL','feeeeffe','NULL','Normal','zhangs','同意','EZTC038','NULL','yhy'),('karth ick',NULL,'NULL','karthick','125501','NULL','2014-05-21 12:33:26','7d866d4f-6c21-4f13-acde-de84b5c3b1f0',0,'125501','NULL','NULL','125504','NULL','test','\'NULL\'','2014-05-21 12:33:26','NULL','NULL','tet','NULL','Normal','karthick','NULL','EZTC118','test per','karthick'),('Tomcat User',NULL,'FACILITIES',NULL,'127132',NULL,'2013-12-03 10:31:31','7fa8b629-c133-4b8f-b1ee-584822b06c69',0,'127132',NULL,NULL,'127500',NULL,'aaaa','NULL','2013-12-03 10:23:42',NULL,'yes','aaa','no','normal','user',NULL,NULL,'admin','user'),('朱 乾安',NULL,'NULL','zhuqa','134123','NULL','2014-05-29 07:37:46','811d7084-08d6-4a4c-9a40-2af995ca55db',0,'134123','NULL','哈哈','134126','NULL','南京同志','\\NULL\\','2014-05-29 07:36:28','NULL','NULL','Nanjing ','NULL','high','zhuqa','NULL','EZTC125','朱 小宝','zhuqa'),('Exelanz Admin',NULL,'NULL',NULL,'117302',NULL,'2014-05-21 15:56:52','81fda958-2e22-4f03-a027-94517917dd0c',0,'117302',NULL,'NULL','117420',NULL,NULL,'\'NULL\'','2014-05-21 15:56:52',NULL,'NULL',NULL,NULL,'Normal','karthick','NULL',NULL,'Tomcat User','karthick'),('Kevin Pietersen',NULL,'NETWORK DEPARTMENT',NULL,'167283',NULL,'2013-12-18 14:31:49','8283e653-f724-47d5-b25e-5ed57049f0b1',0,'167283',NULL,NULL,'167286','sss','test title','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/usera/1_20131218143149_06murali-vijay.jpg','2013-12-18 14:31:49',NULL,NULL,'publish ',NULL,'Normal','usera','aaa',NULL,'QA test','usera'),('fang gao',NULL,'NULL','gf','74605','NULL','2014-04-10 06:33:43','82b0f178-0ad7-4f00-a226-2c07ca084346',0,'74605','EZTC084','1','74944','NULL','d','\\NULL\\','2014-04-10 05:52:57','NULL','NULL','ff','NULL','Normal','gf','NULL','EZTC084','fang gao','gf'),('Kevin Pietersen',NULL,NULL,NULL,'150672',NULL,'2013-12-12 09:10:52','8315b30b-4ead-4fce-b953-b042ac31b1c9',0,'150672',NULL,NULL,'150750',NULL,'test','NULL','2013-12-12 09:09:50',NULL,NULL,NULL,NULL,'normal','usera','test aa',NULL,NULL,'usera'),('NULL',NULL,'NULL','NULL','32117','NULL','2014-03-21 06:30:13','83fb8a4c-461a-4ee4-acb0-558804a8008d',0,'32117','NULL','NULL','32120','NULL','NULL','\'NULL\'','2014-03-21 06:30:13','NULL','NULL','NULL','NULL','Normal','fg','NULL','EZTC069','NULL','fg'),('Exelanz Admin',NULL,'NULL','admin','127660','NULL','2014-05-22 14:51:43','855b348d-792a-4f51-8167-d681ec541690',0,'127660','NULL','NULL','127663','NULL','dfdf','\'NULL\'','2014-05-22 14:51:43','NULL','NULL','dfdf','NULL','Normal','admin','NULL','EZTC120','Development Admin','admin'),('Exelanz Admin',NULL,NULL,NULL,'127579',NULL,'2013-12-05 06:52:10','855e0fb7-8753-4b44-ab26-440bbd75597c',0,'127579',NULL,NULL,'130981',NULL,'test','\\[object File]\\','2013-12-05 06:45:13',NULL,NULL,NULL,NULL,'normal','james',',dfgfg',NULL,NULL,'james'),('f g',NULL,'gf','NULL','79817','d','2014-04-11 08:31:56','883e766f-d2c3-4d9b-b006-c9d9c91d6817',0,'79817','d','NULL','80256','NULL','NULL','\\NULL\\','2014-04-11 08:29:04','d','NULL','d gf','NULL','Normal','gf','NULL','EZTC089','fang gao','gf'),('fang gao',NULL,'gf','NULL','84981','d','2014-04-23 07:42:47','8db17420-d9ea-47d9-83da-7d011f08e5d8',0,'84981','d','NULL','95362','NULL','NULL','\\NULL\\','2014-04-17 03:02:43','d','NULL','fg','NULL','Normal','fg','NULL','EZTC093','a fa','gf'),('Kevin Pietersen','2013-12-13','FACILITIES','usera','166498',NULL,'2013-12-18 14:03:31','8f22dfde-21fd-4edc-b6a2-3b8fe06359e3',0,'166498',NULL,'qqq','166985','ssss ','new file','NULL','2013-12-18 13:45:15',NULL,NULL,'test org','true','high','usera','ddd',NULL,'user f','usera'),(NULL,'2013-12-18',NULL,'usera','168326',NULL,'2013-12-18 15:55:02','9105c0e7-591d-4797-8712-4c565b6c59e0',0,'168326','EZTC006',NULL,'168329',NULL,NULL,'NULL','2013-12-18 15:55:02',NULL,NULL,NULL,NULL,'Normal','usera',NULL,NULL,NULL,'usera'),('Exelanz Admin',NULL,'NULL','james','131036','NULL','2014-05-27 11:09:22','9117b4da-4bf5-4010-bc6f-28fa9e8cf7fe',0,'131036','NULL','NULL','131039','NULL','test mail','\'NULL\'','2014-05-27 11:09:22','NULL','NULL','my org','NULL','high','james','NULL','EZTC121','Exelanz Admin','james'),('auto user','2013-12-19','后勤部','testuser','169755',NULL,'2013-12-19 08:07:56','917b9235-36bd-4da8-a9dd-1f26f641c305',0,'169755','EZTC010','test','170993',NULL,'test','NULL','2013-12-19 07:07:23',NULL,NULL,'test杨海云','true','Normal','testuser',NULL,NULL,'杨 海云,y hy','testuser'),('NULL',NULL,'NULL','NULL','27806','11','2014-03-21 02:38:30','95da196f-63c2-4e3c-9123-0e35f8f64e59',0,'27806','11','NULL','28605','NULL','NULL','\\NULL\\','2014-03-20 09:07:07','11','NULL','gf','NULL','Normal','gf','NULL','EZTC059','NULL','gf'),('Kevin Pietersen',NULL,NULL,NULL,'167435',NULL,'2013-12-18 14:42:02','97032ed9-881f-4e7a-a0e4-21a0551e6f21',0,'167435',NULL,'aaa','167737',',fdfdfddf','ssss','NULL','2013-12-18 14:38:13',NULL,NULL,'aaaa','true','Normal','usera',',ssads',NULL,'Exelanz Admin','usera'),('NULL',NULL,'NULL','NULL','31218','2','2014-03-21 07:40:32','979d54f5-fffa-4455-a237-c512e8cd2f47',0,'31218','i','NULL','31806','NULL','NULL','\\NULL\\','2014-03-21 05:32:55','u','NULL','gf','NULL','Normal','gf','NULL','EZTC066','NULL','d'),('NULL',NULL,'NULL','NULL','22782','ss','2014-03-20 03:24:08','994190ca-a6a9-4e8a-b3b3-21d4a42c7e27',0,'22782','ss','NULL','24863','NULL','NULL','\\NULL\\','2014-03-19 09:18:29','ss','NULL','fg','NULL','Normal','fg','NULL','EZTC050','NULL','gf'),('NULL',NULL,'NULL','NULL','31921','NULL','2014-03-21 06:29:17','99b216b0-fb58-4ae9-b346-599295f61cd3',0,'31921','NULL','NULL','31924','NULL','NULL','\'NULL\'','2014-03-21 06:29:17','NULL','NULL','NULL','NULL','Normal','fg','NULL','EZTC068','NULL','fg'),('admin',NULL,NULL,NULL,'76614',NULL,'2013-11-05 14:37:22','99e582b5-d9d1-4c6f-8308-45dccc4c081e',0,'76614',NULL,NULL,'76618',NULL,'fffff','NULL','2013-11-05 14:37:22',NULL,NULL,NULL,NULL,'normal','admin',NULL,NULL,NULL,'admin'),('NULL',NULL,'NULL','NULL','97678','NULL','2014-04-23 09:00:05','9b12d657-da77-4b02-870d-77a464450eab',0,'97678','NULL','NULL','97681','NULL','NULL','\'NULL\'','2014-04-23 09:00:05','NULL','NULL','ffe','NULL','Normal','c','NULL','EZTC100','q c','c'),('NULL',NULL,'NULL','NULL','23302','111','2014-03-20 03:22:26','9b1904d4-f69a-4bbc-a5fd-5c4b38f4d413',0,'23302','111','NULL','23519','NULL','NULL','\\NULL\\','2014-03-20 02:04:11','111','NULL','ff','NULL','Normal','ff','NULL','EZTC051','NULL','gf'),('杨 海云',NULL,NULL,NULL,'149738',NULL,'2013-12-12 07:19:25','a0f9f1b1-28f0-4ac7-8882-e76dc9f6aff0',0,'149738',NULL,NULL,'149914',NULL,'test1','NULL','2013-12-12 07:17:41',NULL,NULL,'test1',NULL,'normal','testuser','同意',NULL,NULL,'yanghaiyun'),('Kevin Pietersen',NULL,'FACILITIES',NULL,'133473',NULL,'2013-12-06 05:53:37','a24a6e28-e5d7-4fba-a2f8-6216e5845743',0,'133473',NULL,NULL,'133796',NULL,'new title','NULL','2013-12-06 05:45:46',NULL,'true','test','true','high','usera','test',NULL,'srinivas','usera'),('杨 海云',NULL,'NULL','yhy','145489','NULL','2014-06-16 01:27:59','a4ac2614-f34d-4c99-b787-c799c1fcdcc3',0,'145489','NULL','NULL','145492','NULL','ffffff','\'NULL\'','2014-06-16 01:27:59','NULL','NULL','feefefefef','NULL','Normal','yhy','NULL','EZTC132','杨 海云','yhy'),('q c,q a',NULL,'NULL','NULL','96066','NULL','2014-04-23 08:02:20','a4d9838f-dbdd-4679-badd-b90f0dfbaf15',0,'96066','NULL','NULL','96069','NULL','NULL','\'NULL\'','2014-04-23 08:02:20','NULL','NULL','NULL','NULL','Normal','c','NULL','EZTC104','q a','c'),('admin',NULL,NULL,NULL,'71425',NULL,'2013-11-05 06:29:57','a6054e74-b730-4be1-8904-13d1407f4ef6',0,'71425',NULL,NULL,'71557',NULL,'test','NULL','2013-11-05 06:29:24',NULL,'true',NULL,NULL,'normal','lee',NULL,NULL,NULL,'lee'),('zhang san','2014-03-12','NULL','yhy','2323','NULL','2014-03-12 05:48:28','a87acb2f-7ca4-4616-ab56-95d3ec059a01',0,'2323','EZTC035','NULL','3104','NULL','title','\\NULL\\','2014-03-12 05:19:56','NULL','true','publishtitle','true','Normal','yhy','同意','EZTC035','NULL','zhangs'),('Kevin Pietersen',NULL,NULL,NULL,'167887',NULL,'2013-12-18 15:10:49','a8d0f829-58ee-4cc3-ab5c-d1eab3668484',0,'167887',NULL,NULL,'168027','sssssssss','testtest','NULL','2013-12-18 15:09:54',NULL,NULL,'test',NULL,'high','usera','aaaaaaa',NULL,NULL,'usera'),('L Dejor','2014-03-19','NULL','ljw','21615','NULL','2014-03-19 07:01:29','a91262f6-13e1-428d-a290-e2b9751e918a',0,'21615','NULL','NULL','21618','NULL','ada','\'NULL\'','2014-03-19 07:01:29','NULL','NULL','qwe','NULL','Normal','ljw','NULL','EZTC046','NULL','ljw'),('杨 海云','2013-12-23',NULL,'flowuser','181095',NULL,'2013-12-23 01:55:46','a98f72c8-43ce-440f-bd13-3e65a94eefeb',0,'181095','EZTC011',NULL,'181401','同意','op','NULL','2013-12-23 01:50:29',NULL,NULL,'op','true','Normal','flowuser','同意','EZTC011',NULL,'autouser'),('Exelanz Admin','2014-02-24','Organization','admin','14245','NULL','2014-02-24 05:36:21','abfda3b8-b266-4d88-b1de-888b8414cfa6',0,'14245','NULL','Data Set to user','14248','NULL','WElcome List','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/admin/1_20140224053621_checttest.jsp','2014-02-24 05:36:21','NULL','NULL','RamaKavanana','NULL','high','admin','NULL','EZTC031','Exelanz Admin','admin'),('Exelanz James','2013-12-19',NULL,'james','173111',NULL,'2013-12-19 12:50:17','acf322fd-7978-4685-8f40-a6566a1cc081',0,'173111','EZTC001',NULL,'173417','charge','test tite;','NULL','2013-12-19 12:45:05',NULL,NULL,'test','true','Normal','james','leader','EZTC001','Tomcat User','james'),('Exelanz James',NULL,'Organization',NULL,'134641',NULL,'2013-12-06 06:20:56','ad9868d3-bbbc-46b6-b4bf-227aa5788a1f',0,'134641',NULL,NULL,'134644',NULL,'ghg','NULL','2013-12-06 06:20:56',NULL,NULL,NULL,NULL,'normal','james',NULL,NULL,NULL,'james'),('karth ick',NULL,'NULL','karthick','142478','NULL','2014-06-16 13:47:22','af8850f7-436b-4843-bdb9-1f42674e4833',0,'142478','NULL','NULL','142481','NULL','Testing','\\NULL\\','2014-06-09 13:08:33','NULL','NULL','Test','NULL','Normal','karthick','NULL','EZTC131','karth ick','karthick'),('Exelanz Admin','2014-02-18','NULL','admin','6478','NULL','2014-02-18 11:33:16','b0485d83-6a7e-4334-b185-ea7e00973e76',0,'6478','NULL','NULL','6806','NULL','ssss','\\NULL\\','2014-02-18 10:43:23','NULL','dssssssssssssssssssssssssss','aaaa','NULL','high','admin','dadaaaaaaaaaaaaaaaaaaaaaaaaaaaa sddss','EZTC025','Exelanz Admin,apirole apirole,g f,Exelanz James,李 俊伟','admin'),('Exelanz Admin',NULL,NULL,NULL,'116589',NULL,'2013-11-25 10:22:51','b1a23b41-52c1-4164-8055-734842fc2986',0,'116589',NULL,NULL,'116592',NULL,'testtt','NULL','2013-11-25 10:22:51',NULL,NULL,NULL,NULL,'normal','admin',NULL,NULL,'james','admin'),('admin',NULL,NULL,NULL,'100793',NULL,'2013-11-18 06:55:59','b1da40aa-e48c-404e-a74c-da5d2025934f',0,'100793',NULL,NULL,'100925',NULL,'test','NULL','2013-11-18 06:53:01',NULL,NULL,NULL,NULL,'normal','admin',',fghfgh,fghh',NULL,NULL,'admin'),('f g',NULL,'gf','NULL','40889','s','2014-03-24 09:39:22','b9eaac88-e470-40d8-a5f4-96ba2a58a389',0,'40889','s','NULL','41185','NULL','NULL','\\NULL\\','2014-03-24 09:36:40','s','NULL','gf','NULL','Normal','gf','NULL','EZTC073','NULL','b'),('q a,a fa,Exelanz Admin',NULL,'NULL','NULL','66150','NULL','2014-04-02 12:39:25','bb41493e-890e-4bcb-9b35-47771d9d54ed',0,'66150','NULL','NULL','66260','NULL','NULL','\\NULL\\','2014-04-02 11:45:23','NULL','NULL','admin','NULL','Normal','admin','NULL','EZTC078','NULL','admin'),('NULL',NULL,'NULL','NULL','29497','11','2014-03-21 05:22:59','bbbbdefd-77e2-41eb-b48f-828e7675ecc2',0,'29497','11','NULL','30364','NULL','NULL','\\NULL\\','2014-03-21 03:01:10','11','NULL','gf','NULL','Normal','gf','NULL','EZTC062','NULL','gf'),('admin',NULL,NULL,NULL,'70473',NULL,'2013-11-05 06:13:37','bc9639e7-c0af-497d-bf35-7679488a722c',0,'70473',NULL,NULL,'70565',NULL,'test','NULL','2013-11-05 06:12:55',NULL,'true',NULL,NULL,'normal','admin',NULL,NULL,NULL,'admin'),('NULL',NULL,'NULL','NULL','18448','1111111111111111','2014-03-19 05:30:05','be9c09ae-d4a0-4c82-b8a1-2895eb82ebe5',0,'18448','222222222222222','NULL','19404','NULL','NULL','\\NULL\\','2014-03-19 03:16:59','3333333333333','NULL','gf gf gf gf','NULL','Normal','gf','NULL','EZTC041','NULL','gf'),('NULL',NULL,'NULL','NULL','73150','NULL','2014-04-09 06:46:06','bf40efd9-e8f0-45d3-b9a9-1f99f9e0bfd8',0,'73150','NULL','NULL','73153','NULL','NULL','\'NULL\'','2014-04-09 06:46:06','NULL','NULL','ee','NULL','Normal','gf','NULL','EZTC081','fang gao','gf'),('admin',NULL,NULL,NULL,'73025',NULL,'2013-11-05 08:25:21','c18a6c2f-1808-4254-a7ed-8cba8c6c615d',0,'73025',NULL,NULL,'73158',NULL,'test','NULL','2013-11-05 08:22:25',NULL,'true','org','false','normal','admin',NULL,NULL,NULL,'admin'),('fang gao',NULL,'Organization','ff','76101','f','2014-04-23 06:35:40','c2213ff9-39c4-46fd-a788-3ea1c60c72c8',0,'76101','EZTC086','NULL','76780','NULL','feg','\\NULL\\','2014-04-10 07:06:34','f','NULL','ff','NULL','Normal','ff','NULL','EZTC086','f g','ff'),('Exelanz Admin',NULL,'NULL','user','90186','NULL','2014-04-18 12:49:24','c23f277a-604e-4f77-aaae-78b550869e1e',0,'90186','NULL','NULL','90189','NULL','test','\'NULL\'','2014-04-18 12:49:24','NULL','NULL','test','NULL','high','user','NULL','EZTC098','Development Admin','user'),('admin',NULL,NULL,NULL,'78636',NULL,'2013-11-06 11:05:38','c3893258-7696-47fb-bbc3-9812b44aaf50',0,'78636',NULL,NULL,'78813',NULL,'test','NULL','2013-11-06 11:02:34',NULL,'true',NULL,'false','normal','admin',NULL,NULL,'admins','admin'),('NULL',NULL,'Organization','NULL','15835','2222222222276','2014-03-18 06:22:49','c4b22445-06aa-42cd-962b-0f869f241ac5',0,'15835','14444444444448755','NULL','16103','NULL','ui','\\NULL\\','2014-03-18 05:18:53','11111111111111111111111','NULL','gftyu','NULL','Normal','gf','ok','EZTC039','Exelanz James','gf'),('auto user','2013-12-19','后勤部','autouser','171091',NULL,'2013-12-19 08:16:23','c57a3f62-6f33-4fbb-896d-4ef74f9964f9',0,'171091','EZTC012','test 2','171304',NULL,'test 2','NULL','2013-12-19 08:11:41',NULL,NULL,'test 2',NULL,'Normal','autouser',',同意',NULL,'杨 海云,y hy','autouser'),('Exelanz Admin',NULL,'NULL','kenny','117138','NULL','2014-05-14 07:52:14','c6d3bd54-f26e-4af0-9bc4-404597ca475d',0,'117138','NULL','NULL','117141','NULL','test','\'NULL\'','2014-05-14 07:52:14','NULL','NULL','test','NULL','Normal','kenny','NULL','EZTC113','Development Admin','kenny'),('Exelanz Admin','2014-02-19','NULL','admin','7501','NULL','2014-02-19 02:21:09','c6e983f9-a56b-4164-810d-79f67d62a53d',0,'7501','NULL','NULL','7504','NULL','testtest','\'NULL\'','2014-02-19 02:21:09','NULL','NULL','testtest','NULL','Normal','admin','NULL','EZTC026','NULL','admin'),('Exelanz James',NULL,'HR Department',NULL,'134193',NULL,'2013-12-06 06:08:41','c70a1759-99bb-4166-830d-16e9014af3c1',0,'134193',NULL,NULL,'134196',NULL,'co check','NULL','2013-12-06 06:08:41',NULL,NULL,NULL,NULL,'normal','james',NULL,NULL,'user','james'),('杨 海云','2013-12-27',NULL,'testuser','193718',NULL,'2013-12-27 01:16:19','c7991c90-2bc5-4253-b805-0818ef4ec710',0,'193718',NULL,NULL,'193822',NULL,'pulish','NULL','2013-12-27 01:13:01',NULL,NULL,'publish file',NULL,'Normal','testuser',NULL,'EZTC020',NULL,'yanghaiyun'),('Exelanz Admin',NULL,'NULL','ar','110275','NULL','2014-05-05 11:06:07','c9c7929e-d406-4c53-9e91-906b40b6197d',0,'110275','NULL','sds','110278','NULL','dssd','\'NULL\'','2014-05-05 11:06:07','NULL','NULL','sdsdsd','NULL','Normal','ar','NULL','EZTC111','Development Admin','ar'),('yang haiyun','2014-02-18','NULL','admin','4206','NULL','2014-02-18 02:53:35','ca2a203f-aa4b-46c1-a6ff-abde582d0a11',0,'4206','NULL','NULL','4418','NULL','test——test','\\NULL\\','2014-02-18 02:52:02','NULL','NULL','test——test','NULL','Normal','admin','NULL','EZTC023','NULL','yhy'),('kenny yang',NULL,'NULL','3','157257','3','2014-06-28 08:44:18','cb2990fa-a05a-489a-a0c6-35f631971b4e',0,'157257','3','3','157260','NULL','3','\'NULL\'','2014-06-28 08:44:18','3','NULL','333','NULL','Normal','kenny','NULL','EZTC133','kenny yang','kenny'),('AR Srinivas','2013-12-19',NULL,'srinivas','174107',NULL,'2013-12-19 15:10:21','cb421396-ed18-4cfc-bd40-949b723327fd',0,'174107','EZTC002',NULL,'174683','sdssd','new title','NULL','2013-12-19 15:06:25',NULL,NULL,'new publish','true','Normal','srinivas','test','EZTC002','Tomcat User','srinivas'),('NULL',NULL,'NULL','NULL','20002','222222222','2014-03-19 05:59:00','cc296712-c492-4774-8c36-ba57cda7946c',0,'20002','22222222222','NULL','20624','NULL','NULL','\\NULL\\','2014-03-19 05:49:22','333333333','NULL','gf gf gf','NULL','Normal','gf','NULL','EZTC043','NULL','gf'),('NULL',NULL,'gf','NULL','33251','u','2014-04-02 07:20:38','cc7539b6-1082-4bb2-9676-22df65030c3f',0,'33251','ik','NULL','65444','NULL','NULL','\\NULL\\','2014-03-22 03:35:14','j','NULL','gf','NULL','Normal','gf','NULL','EZTC071','NULL','gf'),('user f',NULL,NULL,NULL,'136449',NULL,'2013-12-06 13:14:08','cccbf82f-1e1b-4b66-94ab-b85abf968aac',0,'136449',NULL,NULL,'136452',NULL,'test','\'[object File]\'','2013-12-06 13:14:08',NULL,NULL,NULL,NULL,'normal','userf',NULL,NULL,'srinivas','userf'),('杨 海云',NULL,NULL,NULL,'149481',NULL,'2013-12-12 07:07:28','cefa095a-5654-427f-b9c9-451fbf8eab2e',0,'149481',NULL,NULL,'149559',NULL,'test','NULL','2013-12-12 06:50:04',NULL,'true','test',NULL,'normal','autouser','同意',NULL,NULL,'yanghaiyun'),('admins',NULL,NULL,NULL,'86003',NULL,'2013-11-09 12:53:28','cf47c3c6-0f6e-433f-9a41-c0972dbd85b9',0,'86003',NULL,NULL,'86137',NULL,'test','NULL','2013-11-09 12:47:28',NULL,'true',NULL,'true','normal','admin',NULL,NULL,NULL,'admin'),('fang gao',NULL,'Organization','ff','76787','NULL','2014-04-10 07:22:27','d0828cb1-a0c1-49f9-8c18-1168b1b61b08',0,'76787','EZTC087','ee','77125','NULL','f','\\NULL\\','2014-04-10 07:19:22','NULL','f','f','NULL','Normal','ff','ff ','EZTC087','f g','gf'),('NULL',NULL,'NULL','NULL','25594','11','2014-03-20 06:54:34','d2a7cb34-7196-4515-a222-bc48d18038d6',0,'25594','11','NULL','25819','NULL','NULL','\\NULL\\','2014-03-20 06:18:23','11','NULL','gf','NULL','Normal','gf','NULL','EZTC055','NULL','ff'),('NULL',NULL,'NULL','NULL','97923','NULL','2014-04-23 09:34:56','d2d8893f-c846-4cbf-ac3b-eaa8313eb9f4',0,'97923','NULL','NULL','97926','NULL','NULL','\'NULL\'','2014-04-23 09:34:56','NULL','NULL','NULL','NULL','Normal','gf','NULL','EZTC102','q a','gf'),('Exelanz James','2013-12-19',NULL,'james','170402',NULL,'2013-12-19 07:24:44','d386d426-e2e6-433b-902d-ba094b7401c5',0,'170402','EZTC011',NULL,'170602','charge','sdfdf','NULL','2013-12-19 07:21:12',NULL,NULL,'sdfdsf',NULL,'Normal','james','leader ,opinion',NULL,NULL,'james'),('admin',NULL,NULL,NULL,'71916',NULL,'2013-11-05 06:34:19','d4f2ed95-a1aa-46c1-bcfe-8f8179e35e3f',0,'71916',NULL,NULL,'72008',NULL,'test','NULL','2013-11-05 06:33:58',NULL,'true',NULL,NULL,'normal','lee',NULL,NULL,NULL,'lee'),('Exelanz Admin','2014-02-18','NULL','admin','6209','NULL','2014-02-18 09:40:33','d53f29a8-55fa-4a97-a0c3-472b31b2da1d',0,'6209','NULL','NULL','6212','NULL','tes','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/admin/1_20140218094033_agency.png','2014-02-18 09:40:33','NULL','NULL','test','NULL','Normal','admin','NULL','EZTC024','NULL','admin'),('Kevin Pietersen',NULL,NULL,NULL,'153765',NULL,'2013-12-13 13:22:05','d81c9e92-d4bb-4d4b-a84a-d94f321ead54',0,'153765',NULL,NULL,'153913',NULL,'aaa','NULL','2013-12-13 13:16:00',NULL,NULL,NULL,NULL,'normal','usera',NULL,NULL,NULL,'usera'),('NULL',NULL,'gf','NULL','30763','11`','2014-03-21 05:29:29','d81ce1f4-a3fc-44cf-979a-68d0596e5a71',0,'30763','6','NULL','30988','NULL','NULL','\\NULL\\','2014-03-21 05:23:51','44`','NULL','gf','NULL','Normal','gf','NULL','EZTC064','NULL','e'),('NULL',NULL,'NULL','NULL','15644','NULL','2014-03-18 03:36:38','dbe85790-57b5-4c10-a66e-c9cff897885b',1,'15644','NULL','NULL','15647','NULL','NULL','\'NULL\'','2014-03-18 03:36:38','NULL','NULL','NULL','NULL','Normal','gf','NULL','EZTC038','NULL','gf'),('Exelanz Admin','2013-12-24',NULL,'admin','190629',NULL,'2013-12-24 12:14:27','dd01d62e-4a48-433c-9c69-4d7d6374c0ce',0,'190629',NULL,NULL,'190632',NULL,'test','NULL','2013-12-24 12:14:27',NULL,NULL,'test',NULL,'Normal','admin',NULL,'EZTC018',NULL,'admin'),('NULL',NULL,'NULL','NULL','86770','NULL','2014-04-17 09:38:05','e0ab12f0-bc6b-42e9-adef-8985e453f46e',0,'86770','NULL','NULL','86998','NULL','NULL','\\NULL\\','2014-04-17 09:35:12','NULL','NULL','NULL','NULL','Normal','gf','NULL','EZTC096','fang gao','gf'),('NULL',NULL,'NULL','NULL','26301','111','2014-03-20 08:54:35','e0ce43f1-63af-4acb-9300-6b07d1aa5157',0,'26301','134','NULL','26824','NULL','NULL','\\NULL\\','2014-03-20 07:01:07','123','NULL','gf','NULL','Normal','gf','NULL','EZTC056','NULL','gf'),('豆 芽',NULL,'NULL','yhy','139373','NULL','2014-06-06 03:29:27','e5885cc4-210b-4952-a74b-ba1841a9b5bc',0,'139373','NULL','fefe','139376','NULL','fefe','\'NULL\'','2014-06-06 03:29:27','NULL','NULL','fefefe','NULL','Normal','yhy','NULL','EZTC128','豆 芽','yhy'),('Kevin Pietersen',NULL,'FACILITIES','usera','135191',NULL,'2013-12-06 06:44:56','e82dd7a2-4293-4f75-b6eb-e8e1412bda46',0,'135191',NULL,'ssss','135372',NULL,'aaaa bbb','NULL','2013-12-06 06:40:00',NULL,'true',NULL,'true','high','usera',NULL,NULL,'user','usera'),('Exelanz Admin',NULL,'NULL',NULL,'131036',NULL,'2014-05-27 11:20:06','e8349948-61b2-4d60-a546-8e30adbc8b05',0,'131036',NULL,'NULL','131335','NULL',NULL,'\'NULL\'','2014-05-27 11:20:06',NULL,NULL,NULL,NULL,'high','admin','NULL',NULL,'Exelanz Admin','admin'),('admin',NULL,NULL,NULL,'78877',NULL,'2013-11-06 11:19:10','e8c613c6-3113-45b6-b8a3-6838796e05c7',0,'78877',NULL,NULL,'79010',NULL,'test','NULL','2013-11-06 11:17:59',NULL,'true',NULL,'true','normal','admin',NULL,NULL,NULL,'admin'),('NULL',NULL,'NULL','NULL','31535','23','2014-03-21 06:27:12','e8d23a96-d038-49ea-aeb6-eb45736a476b',0,'31535','33','NULL','31760','NULL','NULL','\\NULL\\','2014-03-21 05:47:53','33','NULL','ff','NULL','Normal','ff','NULL','EZTC067','NULL','gf'),('Kevin Pietersen',NULL,NULL,NULL,'153988',NULL,'2013-12-13 15:33:47','e91fd14b-92b4-4e54-af27-0623f1ba1556',0,'153988',NULL,'aaaaaaa','154220',NULL,'test title','NULL','2013-12-13 15:29:59',NULL,NULL,NULL,'true','Normal','usera',NULL,NULL,NULL,'usera'),('NULL',NULL,'NULL','NULL','70951','NULL','2014-04-08 06:32:35','ea128107-30ba-4513-80d3-2e996e5b525c',0,'70951','NULL','NULL','70954','NULL','NULL','\'NULL\'','2014-04-08 06:32:35','NULL','NULL','sdsd','NULL','Normal','admin','NULL','EZTC079','NULL','admin'),('test user',NULL,NULL,NULL,'154302',NULL,'2013-12-16 01:13:24','ecde8a61-a5e9-4b8b-a7fd-b3da58e76168',0,'154302',NULL,NULL,'154305',NULL,'titletest','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/testuser/1_20131216011323_QA BPM Phase 5 Test Case_yanghy.xls','2013-12-16 01:13:24',NULL,NULL,NULL,NULL,'Normal','testuser',NULL,NULL,NULL,'testuser'),('auto user',NULL,NULL,NULL,'150484',NULL,'2013-12-12 07:37:39','ef6377c2-d459-4ef7-a243-1cf6ab45743c',0,'150484',NULL,NULL,'150487',NULL,'yanghaiyun','NULL','2013-12-12 07:37:39',NULL,NULL,'yanghaiyun',NULL,'normal','yanghaiyun',NULL,NULL,NULL,'yanghaiyun'),('Exelanz James',NULL,NULL,NULL,'146082',NULL,'2013-12-11 07:14:17','ef74e78c-c1a9-4521-93fc-f4c0fedfa9a7',0,'146082',NULL,NULL,'146410',',charge leader opinion','Test','NULL','2013-12-11 07:09:18',NULL,NULL,NULL,'true','normal','james',',leader opinion',NULL,'user','james'),('NULL',NULL,'NULL','NULL','21270','1111','2014-03-20 01:40:58','f0173dfb-b717-4117-b350-5c16dc043ba7',0,'21270','111','NULL','21383','NULL','NULL','\\NULL\\','2014-03-19 06:27:06','11','NULL','ff','NULL','Normal','ff','NULL','EZTC048','NULL','gf'),('NULL',NULL,'NULL','NULL','29748','11','2014-03-21 05:23:12','f038c81d-5fce-47b3-84fb-4e9dc9ff417b',0,'29748','11','dd','30439','dd','dd','\\NULL\\','2014-03-21 03:07:37','11','NULL','gf','d','Normal','gf','dd','EZTC063','NULL','gf'),('Exelanz Admin',NULL,'NULL','admin','97179','NULL','2014-05-13 02:18:41','f03cfe71-b746-449a-b2f7-3c2b03a28d84',0,'97179','EZTC101','NULL','97636','rew ','fe','\\NULL\\','2014-04-23 08:33:07','NULL','NULL','ffe','ttr','Normal','admin','fe ','EZTC101','Exelanz James','admin'),('NULL',NULL,'NULL','NULL','95938','NULL','2014-04-23 07:57:07','f167d517-1e27-4e46-8e04-2858b61bebd6',0,'95938','NULL','NULL','95941','NULL','NULL','\'NULL\'','2014-04-23 07:57:07','NULL','NULL','NULL','NULL','Normal','c','NULL','EZTC100','q a','c'),('Kevin Pietersen,Exelanz Admin,new user,aduser aduser,QA test,b localsales,one admin','2013-12-19',NULL,'usera','168521','123','2013-12-19 05:22:18','f2e9d1a6-d795-41e1-a6ee-8f03a5f33128',0,'168521','EZTC007','123','168524',NULL,'test','NULL','2013-12-19 05:22:18',NULL,NULL,'aa',NULL,'high','usera',NULL,NULL,NULL,'usera'),('Exelanz Admin',NULL,NULL,NULL,'116388',NULL,'2013-12-23 10:36:08','f39b900f-9873-4b8e-a5a0-88b1f411a2f3',0,'116388',NULL,NULL,'116491',NULL,'test','NULL','2013-11-25 10:11:04',NULL,NULL,'org',NULL,'normal','admin',NULL,NULL,'james','admin'),(NULL,'2013-12-19',NULL,'testuser','169649',NULL,'2013-12-19 06:55:32','f6378bfa-e742-441c-8d2c-922e8641f5af',0,'169649','EZTC009',NULL,'169652',NULL,NULL,'NULL','2013-12-19 06:55:32',NULL,NULL,NULL,NULL,'Normal','testuser',NULL,NULL,NULL,'testuser'),('admin',NULL,NULL,NULL,'77853',NULL,'2013-11-06 10:39:58','f65828ac-90d1-41a2-baa3-5ac5a6e0cbd9',0,'77853',NULL,NULL,'77985',NULL,'test','NULL','2013-11-06 10:34:52',NULL,'true',NULL,'false','normal','admin',NULL,NULL,NULL,'admin'),('豆 芽',NULL,'NULL','zhang','81239','NULL','2014-04-15 02:08:59','f7020ca1-e706-49b7-8302-655aa356ea94',0,'81239','NULL','fefefe','81242','NULL','dedede','D:eazytecmastersourceeasybpm	argetpm-1.0\resources/zhang/1_20140415020859_','2014-04-15 02:08:59','NULL','NULL','fefefe','NULL','Normal','zhang','NULL','EZTC090','豆 芽','zhang'),('zhang san','2014-03-13','NULL','yhy','6013','NULL','2014-03-13 01:05:49','f731d87a-489b-4ee9-a7d8-20a8a93f4cd8',0,'6013','NULL','NULL','6228','NULL','标题','\\NULL\\','2014-03-13 01:02:59','NULL','NULL','标题','NULL','Normal','yhy','NULL','EZTC037','NULL','gf'),('NULL',NULL,'NULL','NULL','87110','NULL','2014-04-17 09:48:50','f8615b2a-63db-46da-991d-41d6c9a6c47f',0,'87110','NULL','NULL','87335','NULL','NULL','\\NULL\\','2014-04-17 09:42:27','NULL','NULL','NULL','NULL','Normal','gf','NULL','EZTC097','f g','admin'),('AR Srinivas','2013-12-20','Organization','srinivas','175614','123','2013-12-20 06:14:40','f9852eee-2249-4fd5-af10-725a96af1583',0,'175614','EZTC005','test','176055',NULL,'test title','NULL','2013-12-20 06:03:10','321',NULL,'new org','true','high','srinivas','sasasa','EZTC005','Tomcat User,new user','srinivas'),('NULL',NULL,'NULL','NULL','93960','NULL','2014-04-23 05:49:48','fa28b47f-7ff4-4aa9-97d4-510d5ebd7786',0,'93960','NULL','NULL','94070','NULL','NULL','\\NULL\\','2014-04-23 05:49:17','NULL','NULL','NULL','NULL','Normal','a','NULL','EZTC099','q a','a'),('AR Srinivas','2013-12-20',NULL,'srinivas','176128',NULL,'2013-12-20 06:17:51','fc2bd936-e1cb-4563-b3ec-48278f693a87',0,'176128','EZTC006',NULL,'176334',NULL,'saadsdsds','NULL','2013-12-20 06:15:50',NULL,'true','ssasaassasa','tr','high','srinivas','fdfdfd','EZTC006',NULL,'srinivas'),('admin',NULL,NULL,NULL,'72145',NULL,'2013-11-05 06:38:23','fccc0550-9985-4747-a151-22ac91aff0b6',0,'72145',NULL,NULL,'72237',NULL,'test','NULL','2013-11-05 06:37:47',NULL,'true',NULL,NULL,'normal','admin',NULL,NULL,NULL,'admin'),('f g,fang gao,a fa','2014-04-09','组织部','gf','73550','NULL','2014-04-09 09:40:41','fffaf8ed-41bc-4b0f-a78c-ae09f8765586',0,'73550','NULL','NULL','73661','NULL','测试规范','\\NULL\\','2014-04-09 08:43:29','NULL','NULL','质量部门','NULL','Normal','gf','NULL','EZTC080','Exelanz Admin','gf');
/*!40000 ALTER TABLE `PUBLISHFILE` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-31 15:01:06




insert into `ETEC_MODULE_TABLES`(`table_id`,`module_id`) values ('8a10d4594209dcea01420d0cad73000c','8a10d4594430be1a014430f1f44d001d'),('8a10d45942311fe301423203be38007f','8a10d4594430be1a014430f1f44d001d'),('8a10d459446c869001446d01b975002f','8a10d4594430be1a014430f1f44d001d'),('ff80818141e4ae000141e4b6b3060000','8a10d4594430be1a014430f1f44d001d');



insert into `ETEC_RE_FORM`(`id`,`is_active`,`created_by`,`created_time`,`english_name`,`form_name`,`html_source`,`is_delete`,`is_jsp_form`,`print_template`,`is_public`,`revision`,`table_name`,`is_template`,`version`,`xml_source`,`table_id`) values ('8a10d45945890c63014597cffb5f0180',true,'admin','2014-07-31 15:01:07','jvwlab1392969681532','REQUESTINSTRUCTION','<form action="#" enctype="multipart/form-data" id="REQUESTINSTRUCTION" imagepath="/resources/admin/B@31f2013.png" method="post" name="REQUESTINSTRUCTION" style="min-height:530px;width:1371.4285714285716px;height:1078px;background-image:url(/resources/admin/B@31f2013.png);background-repeat: no-repeat;" table="8a10d45942311fe301423203be38007f">
<div id="form_di">
<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><textarea column="8a10d45942311fe301423203c2010088" id="augq8j1387286720263_leaderSugg" name="leaderSugg" style="top: 144px; left: 235px; position: absolute; width: 400px;" table="8a10d45942311fe301423203be38007f"></textarea></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><textarea class="mandatory" column="8a10d45942311fe30142320702630094" id="f51uw1390833428249_affaire" name="affaire" style="top: 265px; left: 237px; position: absolute; width: 400px;" table="8a10d45942311fe301423203be38007f"></textarea></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><input class="file-upload-control" column="8a10d45942311fe3014232070263008f" id="augq8j1387286720263_reqAttachment" multiple="multiple" name="reqAttachment" style="top: 365px; left: 229px; position: absolute;" table="8a10d45942311fe301423203be38007f" type="file"></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><input class="displayNone user-list-hidden" column="8a10d45942311fe30142320702630095" id="augq8j1387286720263_reqorg" name="reqorg" style="top:537px;left:3px;position:absolute;" userlistrandom="180557233" type="hidden"><input class="undefined" column="8a10d45942311fe30142320702630095" id="augq8j1387286720263_reqorgFullName" name="reqorg_FullName" onclick="showUserSelection(\'User\', \'multi\' , \'augq8j1387286720263_reqorg\',document.getElementById(\'augq8j1387286720263_reqorg\'), \'\');" selecttype="multi" style="position: absolute; left: 228px; width: 150px; top: 524px;" table="8a10d45942311fe301423203be38007f" userlistrandom="180557233" type="userlist"></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><img class="richtextbox" column="8a10d45942311fe30142320702630090" id="9d0wq1392392178050_maincontent" name="maincontent" src="/scripts/ckeditor/plugins/richtextbox/images/cke_richtextbox.png" style="top: 649px; left: 26px; position: absolute;" table="8a10d45942311fe301423203be38007f" height="300" width="755"></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><input column="8a10d45942311fe3014232070262008e" id="jvwlab1392969681532_mobileNo" name="mobileNo" style="top: 1026px; left: 677px; position: absolute; width: 120px;" table="8a10d45942311fe301423203be38007f" type="text"></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><input column="8a10d45942311fe30142320702630093" id="augq8j1387286720263_creator" name="creator" style="top: 1027px; left: 113px; position: absolute;" table="8a10d45942311fe301423203be38007f" type="text"><input column="8a10d45942311fe30142320702630091" id="augq8j1387286720263_allOpinion" name="allOpinion" style="top: 459px; left: 527px; position: absolute; width: 250px; height: 50px;" table="8a10d45942311fe301423203be38007f" type="text"></p>

<p><input column="8a10d45942311fe30142320702630092" id="augq8j1387286720263_dealDept" name="dealDept" onclick="showDepartmentSelection(\'Department\', \'single\' , this.id, this, \'\');" selecttype="single" style="top: 440px; left: 229px; width: 150px; position: absolute;" table="8a10d45942311fe301423203be38007f" type="departmentlist"></p>
</div>
</form>

<p>&nbsp;</p>
',false,false,'',false,933,'REQUESTINSTRUCTION',false,2,'<?xml version=\'1.0\' encoding=\'utf-8\'?><form initiator = \'REQUESTINSTRUCTION\' id=\'REQUESTINSTRUCTION\' name=\'REQUESTINSTRUCTION\' action=\'#\' label=\'REQUESTINSTRUCTION\' tableName=\'REQUESTINSTRUCTION\' tableId=\'8a10d45942311fe301423203be38007f\'  method=\'post\'><extensionElements><formProperty type= \'file\' id=\'REQUESTINSTRUCTION_reqAttachment\' name=\'reqAttachment\' isSubForm=\'false\'  fieldClass=\'file-upload-control\'  table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe3014232070263008f\' optionType=\'null\'></formProperty><formProperty type= \'hidden\' id=\'REQUESTINSTRUCTION_reqorg\' name=\'reqorg\' isSubForm=\'false\'  fieldClass=\'displayNone user-list-hidden\'  table=\'null\' column=\'8a10d45942311fe30142320702630095\' optionType=\'null\'></formProperty><formProperty type= \'userlist\' id=\'REQUESTINSTRUCTION_reqorg_FullName\' name=\'reqorg_FullName\' isSubForm=\'false\'  fieldClass=\'undefined\'  onclick= "showUserSelection(\'User\', \'multi\' , \'augq8j1387286720263_reqorg\',document.getElementById(\'augq8j1387286720263_reqorg\'), \'\');" table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe30142320702630095\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'REQUESTINSTRUCTION_mobileNo\' name=\'mobileNo\' isSubForm=\'false\'  table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe3014232070262008e\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'REQUESTINSTRUCTION_creator\' name=\'creator\' isSubForm=\'false\'  table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe30142320702630093\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'REQUESTINSTRUCTION_allOpinion\' name=\'allOpinion\' isSubForm=\'false\'  table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe30142320702630091\' optionType=\'null\'></formProperty><formProperty type= \'departmentlist\' id=\'REQUESTINSTRUCTION_dealDept\' name=\'dealDept\' isSubForm=\'false\'  onclick= "showDepartmentSelection(\'Department\', \'single\' , this.id, this, \'\');" table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe30142320702630092\' optionType=\'null\'></formProperty><formProperty type= \'textarea\' id=\'REQUESTINSTRUCTION_leaderSugg\' name=\'leaderSugg\' isSubForm=\'false\' rows=\'\' cols=\'\'  table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe301423203c2010088\'  ></formProperty><formProperty type= \'textarea\' id=\'REQUESTINSTRUCTION_affaire\' name=\'affaire\' isSubForm=\'false\' rows=\'\' cols=\'\'  table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe30142320702630094\'  ></formProperty><formProperty  type= \'img\' id=\'REQUESTINSTRUCTION_maincontent\' name=\'maincontent\'  class= \'richtextbox\' isSubForm=\'false\' table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe30142320702630090\'></formProperty></extensionElements></form>','8a10d45942311fe301423203be38007f'),('8a10d4594430be1a014430f4f1900024',true,'admin','2014-07-31 15:01:07','mfutx1392390897002','ReceivedFile','<form action="#" enctype="multipart/form-data" id="ReceivedFile" method="post" name="ReceivedFile" style="min-height:655px;width:1371.4285714285716px" table="8a10d4594209dcea01420d0cad73000c">
<div id="form_div">
<p>&nbsp;</p>

<p><span style="font-size:20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong>RECEIVE DOCUMENT</strong></span></p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

<table height="800" border="1" cellpadding="1" cellspacing="1" width="1345">
	<tbody>
		<tr>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;">
			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">&nbsp;</span></strong></span></span></p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">DocumentType&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></strong></span></span></p>
			</td>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 60px;"><strong><span style="font-size:14px;"><span style="font-family: arial,helvetica,sans-serif;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></strong><input column="8a10d4594209dcea01420d0cb38b000d" id="sk99tl1386857057560_documentType" name="documentType" style="top: 120px; left: 384px; position: absolute; width: 200px;" table="8a10d4594209dcea01420d0cad73000c" type="text"><strong><span style="font-size:14px;"><span style="font-family: arial,helvetica,sans-serif;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></strong></td>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 60px;">
			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">Number </span></strong></span></span></p>
			</td>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;"><strong><span style="font-size:14px;"><span style="font-family: arial,helvetica,sans-serif;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></strong><input column="8a10d4594209dcea01420d174c21002a" id="sk99tl1386857057560_number" name="number" style="top: 128px; left: 1004px; position: absolute; width: 200px;" table="8a10d4594209dcea01420d0cad73000c" type="text"><strong><span style="font-size:14px;"><span style="font-family: arial,helvetica,sans-serif;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></strong></td>
		</tr>
		<tr>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;">
			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">Receive Date</span></strong></span></span></p>
			</td>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 60px;">&nbsp;<input class="datepicker" column="8a10d4594209dcea01420d174c22002c" id="sk99tl1386857057560_receiveDate" name="receiveDate" style="top: 190px; left: 383px; position: absolute; width: 200px;" table="8a10d4594209dcea01420d0cad73000c" type="date"></td>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 60px;">
			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">Urgency</span></strong></span></span></p>
			</td>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;"><select column="8a10d4594209dcea01420d174c200024" datadictionary="" datadictionarylabel="" id="sk99tl1386857057560_urgency" name="urgency" optiontype="static" size="1" style="top: 188px; left: 1006px; position: absolute; width: 200px;" table="8a10d4594209dcea01420d0cad73000c"><option selected="selected" value="normal">normal</option><option value="high">high</option></select></td>
		</tr>
		<tr>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;">
			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">Document File No</span></strong></span></span></p>
			</td>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 60px;"><input column="8a10d4594209dcea01420d174c22002b" id="sk99tl1386857057560_docFileNo" name="docFileNo" style="top: 247px; left: 385px; position: absolute; width: 200px;" table="8a10d4594209dcea01420d0cad73000c" type="text"></td>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 60px;">
			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">Secret Level&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></strong></span></span></p>
			</td>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;"><select column="8a10d4594209dcea01420d174c1f0020" datadictionary="" datadictionarylabel="" id="sk99tl1386857057560_secretLevel" name="secretLevel" optiontype="static" size="1" style="top: 249px; left: 1005px; position: absolute; width: 200px;" table="8a10d4594209dcea01420d0cad73000c"><option selected="selected" value="normal">normal</option><option value="secret">secret</option><option value="top secret">top secret</option></select></td>
		</tr>
		<tr>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;">
			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">Original Document Date</span></strong></span></span></p>
			</td>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;">&nbsp; <input class="datetimepicker" column="8a10d4594209dcea01420d174c210026" id="sk99tl1386857057560_originalDocumentDate" name="originalDocumentDate" style="top: 303px; left: 382px; position: absolute; width: 200px;" table="8a10d4594209dcea01420d0cad73000c" type="date"></td>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 60px;">
			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">Processing&nbsp;Time</span></strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span></p>
			</td>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;"><input class="datetimepicker" column="8a10d4594209dcea01420d174c210025" id="sk99tl1386857057560_processingTime" name="processingTime" style="top: 306px; left: 1005px; position: absolute; width: 200px;" table="8a10d4594209dcea01420d0cad73000c" type="date"></td>
		</tr>
		<tr>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;">
			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">Document From Organization</span></strong></span></span></p>
			</td>
			<td colspan="3" style="height: 50px; background-color: rgb(238, 248, 254);"><input column="8a10d4594209dcea01420d174c1f0022" id="sk99tl1386857057560_docFormOrg" name="docFormOrg" onclick="showDepartmentSelection(\'Department\', \'single\' , this.id, this, \'\');" selecttype="single" style="top: 368px; left: 381px; position: absolute; width: 400px;" table="8a10d4594209dcea01420d0cad73000c" type="departmentlist"></td>
		</tr>
		<tr>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;">
			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">Document Title</span></strong></span></span></p>
			</td>
			<td colspan="3" style="height: 50px; background-color: rgb(238, 248, 254);"><input column="8a10d4594209dcea01420d174bc9001f" id="sk99tl1386857057560_docTitle" name="docTitle" style="top: 423px; left: 380px; position: absolute; width: 400px;" table="8a10d4594209dcea01420d0cad73000c" type="text"></td>
		</tr>
		<tr>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;">
			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">General office Opinion</span></strong></span></span></p>

			<p><br>
			&nbsp;</p>
			</td>
			<td colspan="3" style="height: 50px; background-color: rgb(238, 248, 254);">
			<p>&nbsp;</p>

			<p><textarea column="8a10d4594209dcea01420d174c210028" id="sk99tl1386857057560_generalOffOpinion" name="generalOffOpinion" style="top: 479px; left: 378px; position: absolute; width: 400px;" table="8a10d4594209dcea01420d0cad73000c"></textarea></p>

			<p>&nbsp;</p>
			</td>
		</tr>
		<tr>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;">
			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">Leader Assign Opinion</span></strong></span></span></p>

			<p>&nbsp;</p>
			</td>
			<td colspan="3" style="height: 50px; background-color: rgb(238, 248, 254);">
			<p>&nbsp;</p>

			<p><textarea column="8a10d4594209dcea01420d174c210029" id="sk99tl1386857057560_leaderAssinOp" name="leaderAssinOp" style="top: 566px; left: 377px; position: absolute; width: 400px;" table="8a10d4594209dcea01420d0cad73000c"></textarea></p>

			<p>&nbsp;</p>
			</td>
		</tr>
		<tr>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;">
			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">Bureau Office Opinion</span></strong></span></span></p>

			<p>&nbsp;</p>
			</td>
			<td colspan="3" style="height: 50px; background-color: rgb(238, 248, 254);">
			<p>&nbsp;</p>

			<p><textarea column="8a10d4594209dcea01420d174c200023" id="sk99tl1386857057560_bureauOffOpinion" name="bureauOffOpinion" style="top: 651px; left: 375px; position: absolute; width: 400px;" table="8a10d4594209dcea01420d0cad73000c"></textarea></p>

			<p>&nbsp;</p>
			</td>
		</tr>
		<tr>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;">
			<p>&nbsp;</p>

			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">Is Bureau Office Deal</span></strong></span></span></p>
			</td>
			<td colspan="3" style="height: 50px; background-color: rgb(238, 248, 254);"><input column="8a10d4594209dcea01420d174c210027" id="sk99tl1386857057560_isBureauOffDeal" name="isBureauOffDeal" style="top: 763px; left: 377px; position: absolute; width: 200px;" table="8a10d4594209dcea01420d0cad73000c" type="text"></td>
		</tr>
		<tr>
			<td style="height: 40px; background-color: rgb(238, 248, 254); width: 200px;">
			<p>&nbsp;</p>

			<p>&nbsp;</p>

			<p><span style="color:#4488bc;"><span style="font-size: 16px;"><strong><span style="font-family: arial,helvetica,sans-serif;">File Attachments</span></strong></span></span></p>
			</td>
			<td colspan="3" style="height: 50px; background-color: rgb(238, 248, 254);">
			<p>&nbsp;</p>

			<p><input class="file-upload-control" column="8a10d4594209dcea01420d174c1f0021" id="sk99tl1386857057560_receiveAttachment" multiple="multiple" name="receiveAttachment" style="top: 837px; left: 378px; position: absolute; width: 200px;" table="8a10d4594209dcea01420d0cad73000c" type="file"></p>
			</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>
<script>setDefaultValue(\'ReceivedFile\');</script></div>
</form>

<p>&nbsp;</p>
',false,false,'documentType:${documentType} <br/>
number:${number} <br/>
receiveDate:${receiveDate} <br/>
urgency:${urgency} <br/>
docFileNo:${docFileNo} <br/>
secretLevel:${secretLevel} <br/>
originalDocumentDate:${originalDocumentDate} <br/>
processingTime:${processingTime} <br/>
docFormOrg:${docFormOrg} <br/>
docTitle:${docTitle} <br/>
generalOffOpinion:${generalOffOpinion} <br/>
leaderAssinOp:${leaderAssinOp} <br/>
bureauOffOpinion:${bureauOffOpinion} <br/>
isBureauOffDeal:${isBureauOffDeal} <br/>
receiveAttachment:${receiveAttachment} <br/>',false,2469,'RECEIVEFILE',false,1,'<?xml version=\'1.0\' encoding=\'utf-8\'?><form initiator = \'ReceivedFile\' id=\'ReceivedFile\' name=\'ReceivedFile\' action=\'#\' label=\'ReceivedFile\' tableName=\'RECEIVEFILE\' tableId=\'8a10d4594209dcea01420d0cad73000c\'  method=\'post\'><extensionElements><formProperty type= \'text\' id=\'ReceivedFile_documentType\' name=\'documentType\' isSubForm=\'false\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d0cb38b000d\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'ReceivedFile_number\' name=\'number\' isSubForm=\'false\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174c21002a\' optionType=\'null\'></formProperty><formProperty type= \'date\' id=\'ReceivedFile_receiveDate\' name=\'receiveDate\' isSubForm=\'false\'  fieldClass=\'datepicker\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174c22002c\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'ReceivedFile_docFileNo\' name=\'docFileNo\' isSubForm=\'false\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174c22002b\' optionType=\'null\'></formProperty><formProperty type= \'date\' id=\'ReceivedFile_originalDocumentDate\' name=\'originalDocumentDate\' isSubForm=\'false\'  fieldClass=\'datetimepicker\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174c210026\' optionType=\'null\'></formProperty><formProperty type= \'date\' id=\'ReceivedFile_processingTime\' name=\'processingTime\' isSubForm=\'false\'  fieldClass=\'datetimepicker\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174c210025\' optionType=\'null\'></formProperty><formProperty type= \'departmentlist\' id=\'ReceivedFile_docFormOrg\' name=\'docFormOrg\' isSubForm=\'false\'  onclick= "showDepartmentSelection(\'Department\', \'single\' , this.id, this, \'\');" table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174c1f0022\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'ReceivedFile_docTitle\' name=\'docTitle\' isSubForm=\'false\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174bc9001f\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'ReceivedFile_isBureauOffDeal\' name=\'isBureauOffDeal\' isSubForm=\'false\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174c210027\' optionType=\'null\'></formProperty><formProperty type= \'file\' id=\'ReceivedFile_receiveAttachment\' name=\'receiveAttachment\' isSubForm=\'false\'  fieldClass=\'file-upload-control\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174c1f0021\' optionType=\'null\'></formProperty><formProperty type= \'select\' id=\'ReceivedFile_urgency\' name=\'urgency\' isSubForm=\'false\'  value=\'high\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174c200024\' optionType=\'static\' dataDictionary=\'\'><formProperty type= \'option\' value=\'normal\' label=\'normal\'></formProperty><formProperty type= \'option\' value=\'high\' label=\'high\'></formProperty></formProperty><formProperty type= \'select\' id=\'ReceivedFile_secretLevel\' name=\'secretLevel\' isSubForm=\'false\'  value=\'top secret\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174c1f0020\' optionType=\'static\' dataDictionary=\'\'><formProperty type= \'option\' value=\'normal\' label=\'normal\'></formProperty><formProperty type= \'option\' value=\'secret\' label=\'secret\'></formProperty><formProperty type= \'option\' value=\'top secret\' label=\'top secret\'></formProperty></formProperty><formProperty type= \'textarea\' id=\'ReceivedFile_generalOffOpinion\' name=\'generalOffOpinion\' isSubForm=\'false\' rows=\'\' cols=\'\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174c210028\'  ></formProperty><formProperty type= \'textarea\' id=\'ReceivedFile_leaderAssinOp\' name=\'leaderAssinOp\' isSubForm=\'false\' rows=\'\' cols=\'\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174c210029\'  ></formProperty><formProperty type= \'textarea\' id=\'ReceivedFile_bureauOffOpinion\' name=\'bureauOffOpinion\' isSubForm=\'false\' rows=\'\' cols=\'\'  table=\'8a10d4594209dcea01420d0cad73000c\' column=\'8a10d4594209dcea01420d174c200023\'  ></formProperty></extensionElements></form>','8a10d4594209dcea01420d0cad73000c'),('8a10d45945d19ae90145d210f90f0014',true,'admin','2014-07-31 15:01:07','6sndlv1392390843493','PublishedFile','<style type="text/css">table{	border-color: #FF0000;}
</style>
<script>
	$(function() {
     $("#datepicker").datepicker();
   });
</script>
<form action="#" enctype="multipart/form-data" id="PublishedFile" method="post" name="PublishedFile" style="min-height:655px;width:1359.2857142857144px" table="ff80818141e4ae000141e4b6b3060000">
<div id="form_div">
<p>&nbsp;</p>

<p><span style="font-size:14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:20px;"><span style="color: rgb(247, 41, 0);"><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Publish File</strong></span></span></span></p>

<table style="color:" cellpadding="1" cellspacing="1" border="2" height="800" width="1345">
	<tbody>
		<tr>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); height: 40px; vertical-align: middle; width: 200px;">
			<p><span style="font-size:16px;"><span style="font-family: tahoma,geneva,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong>Publish Organization</strong></span></span></span></p>
			</td>
			<td colspan="3" style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0);"><span style="font-family:trebuchet ms,helvetica,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong><span style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></strong></span><input column="ff80818141e4ae000141e4b6b3f00007" id="odovbe1386916423323_publishOrg" name="publishOrg" style="top: 82px; left: 225px; position: absolute;width:400px;" table="ff80818141e4ae000141e4b6b3060000" type="text"><span style="color: rgb(51, 153, 204);"><strong><span style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></strong></span></span></td>
		</tr>
		<tr>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); height: 40px; vertical-align: middle; width: 200px;">
			<p><span style="font-size:16px;"><span style="font-family: tahoma,geneva,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong>Register No</strong></span></span></span></p>
			</td>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); height: 30px; vertical-align: middle; width: 400px;"><span style="font-family:trebuchet ms,helvetica,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong><span style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></strong></span></span><input column="ff80818141e4ae000141e4b7ed27000c" id="4sry61390574622672_reg3" name="reg3" style="top: 143px; left: 601px; position: absolute; width: 140px;" table="ff80818141e4ae000141e4b6b3060000" type="text"><span style="font-family:trebuchet ms,helvetica,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong><span style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></strong></span></span><input column="ff80818141e4ae000141e4b7ed27000b" id="4sry61390574622672_reg2" name="reg2" style="top: 143px; left: 413px; position: absolute; width:140px;" table="ff80818141e4ae000141e4b6b3060000" type="text"><input column="ff80818141e4ae000141e4b7ed27000d" id="4sry61390574622672_reg1" name="reg1" style="top: 141px; left: 225px; position: absolute; width: 140px;" table="ff80818141e4ae000141e4b6b3060000" type="text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); height: 30px; width: 250px; vertical-align: middle;">
			<p><span style="font-family:trebuchet ms,helvetica,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong><span style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="font-size:16px;">Urgency &nbsp; </span></span></strong></span></span></p>
			</td>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); height: 30px; vertical-align: middle; width: 300px;"><span style="font-family:trebuchet ms,helvetica,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong><span style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></strong></span><select column="ff80818141e4ae000141e4bac5c5000e" datadictionary="" datadictionarylabel="" id="odovbe1386916423323_urgency" name="urgency" optiontype="static" size="1" style="top: 138px; left: 1074px; position: absolute;" table="ff80818141e4ae000141e4b6b3060000"><option value="Normal">Normal</option><option value="high">high</option></select></span></td>
		</tr>
		<tr>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); vertical-align: middle; height: 40px; width: 200px;"><span style="font-size:16px;"><span style="font-family: tahoma,geneva,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong>Creator&amp;Date</strong></span></span></span></td>
			<td colspan="3" style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0);"><input column="ff80818141e4ae000141e4bac5c60010" id="odovbe1386916423323_creator" name="creator" style="top: 198px; left: 226px; position: absolute; width: 200px;" table="ff80818141e4ae000141e4b6b3060000" type="text"><input placeholder="select date" type="text" id="datepicker" style="top: 194px; left: 471px; position: absolute; width: 200px;"/></td>
		</tr>
		<tr>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); vertical-align: middle; height: 40px; width: 200px;"><span style="font-size:16px;"><span style="font-family: tahoma,geneva,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong>Title</strong></span></span></span></td>
			<td colspan="3" style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0);"><input column="ff80818141e4ae000141e4bac5c70014" id="odovbe1386916423323_title" name="title" style="top: 256px; left: 227px; position: absolute; width: 400px;" table="ff80818141e4ae000141e4b6b3060000" type="text"></td>
		</tr>
		<tr>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); vertical-align: middle; height: 40px; width: 200px;"><span style="font-size:16px;"><span style="font-family: tahoma,geneva,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong>Subject Keywords</strong></span></span></span></td>
			<td colspan="3" style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0);"><input column="ff80818141e4ae000141e4bac5c70012" id="odovbe1386916423323_subKeyWords" name="subKeyWords" style="top: 312px; left: 226px; position: absolute; width: 400px;" table="ff80818141e4ae000141e4b6b3060000" type="text"></td>
		</tr>
		<tr>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); vertical-align: middle; height: 40px; width: 200px;"><span style="font-size:16px;"><span style="font-family: tahoma,geneva,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong>Send To</strong></span></span></span></td>
			<td colspan="3" style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0);"><input class="displayNone user-list-hidden" column="ff80818141e4ae000141e4bac5c70018" id="odovbe1386916423323_sendto" name="sendto" style="top:277px;left:187px;position:absolute;" userlistrandom="398560785" type="hidden"><input class="mandatory" column="ff80818141e4ae000141e4bac5c70018" id="odovbe1386916423323_sendtoFullName" name="sendto_FullName" onclick="showUserSelection(\'User\', \'multi\' , \'odovbe1386916423323_sendto\',document.getElementById(\'odovbe1386916423323_sendto\'), \'\');" selecttype="multi" style="position: absolute; left: 226px; top: 365px; width: 400px;" table="ff80818141e4ae000141e4b6b3060000" userlistrandom="398560785" type="userlist"></td>
		</tr>
		<tr>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); vertical-align: middle; height: 40px; width: 200px;"><span style="font-size:16px;"><span style="font-family: tahoma,geneva,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong>CC to Users</strong></span></span></span></td>
			<td colspan="3" style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0);"><input class="displayNone user-list-hidden" column="ff80818141e4ae000141e4bac5c70016" id="6sndlv1392390843493_ccToUser" name="ccToUser" style="top:317px;left:187px;position:absolute;" userlistrandom="113512745" type="hidden"><input class="mandatory" column="ff80818141e4ae000141e4bac5c70016" id="6sndlv1392390843493_ccToUserFullName" name="ccToUser_FullName" onclick="showUserSelection(\'User\', \'multi\' , \'6sndlv1392390843493_ccToUser\',document.getElementById(\'6sndlv1392390843493_ccToUser\'), \'\');" selecttype="multi" style="position: absolute; left: 231px; top: 424px; width: 400px;" table="ff80818141e4ae000141e4b6b3060000" userlistrandom="113512745" type="userlist"></td>
		</tr>
		<tr>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); vertical-align: middle; height: 40px; width: 200px;"><span style="font-size:16px;"><span style="font-family: tahoma,geneva,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong>CC to Department</strong></span></span></span></td>
			<td colspan="3" style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0);"><input column="ff80818141e4ae000141e4bac5c6000f" id="odovbe1386916423323_ccToDepartment" name="ccToDepartment" onclick="showDepartmentSelection(\'Department\', \'single\' , this.id, this, \'\');" selecttype="single" style="top: 483px; left: 222px; position: absolute; width: 400px;" table="ff80818141e4ae000141e4b6b3060000" type="departmentlist"></td>
		</tr>
		<tr>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); vertical-align: middle; height: 40px; width: 200px;">
			<p>&nbsp;</p>

			<p><span style="font-size:16px;"><span style="font-family: tahoma,geneva,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong>Leader\'s Opinion</strong></span></span></span></p>
			</td>
			<td colspan="3" style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0);"><textarea column="ff80818141e4ae000141e4bac5c70013" id="odovbe1386916423323_LeaderOpinion" name="LeaderOpinion" style="top: 539px; left: 227px; position: absolute; width: 400px;" table="ff80818141e4ae000141e4b6b3060000"></textarea></td>
		</tr>
		<tr>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); vertical-align: middle; height: 40px; width: 200px;">
			<p>&nbsp;</p>

			<p><span style="font-size:16px;"><span style="font-family: tahoma,geneva,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong>Charge Leader\'s Opinion</strong></span></span></span></p>
			</td>
			<td colspan="3" style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0);"><textarea column="ff80818141e4ae000141e4bac5c60011" id="odovbe1386916423323_chargeLeadOpinion" name="chargeLeadOpinion" style="top: 613px; left: 231px; position: absolute; width: 400px;" table="ff80818141e4ae000141e4b6b3060000"></textarea></td>
		</tr>
		<tr>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); vertical-align: middle; height: 40px; width: 200px;"><span style="font-size:16px;"><span style="font-family: tahoma,geneva,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong>Send To Officer Check </strong></span></span></span></td>
			<td colspan="3" style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0);"><input column="ff80818141e4ae000141e4d019f80020" id="odovbe1386916423323_isOfficerCheck" name="isOfficerCheck" style="top: 697px; left: 225px; position: absolute; width: 400px;" table="ff80818141e4ae000141e4b6b3060000" type="text"></td>
		</tr>
		<tr>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); vertical-align: middle; height: 40px; width: 200px;"><span style="font-size:16px;"><span style="font-family: tahoma,geneva,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong>Complete </strong></span></span></span></td>
			<td colspan="3" style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0);"><input column="ff80818141e4ae000141e4d019f8001f" id="odovbe1386916423323_isComplete" name="isComplete" style="top: 756px; left: 223px; position: absolute; width: 400px;" table="ff80818141e4ae000141e4b6b3060000" type="text"></td>
		</tr>
		<tr>
			<td style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0); vertical-align: middle; height: 40px; width: 200px;">
			<p><span style="font-size:16px;"><span style="font-family: tahoma,geneva,sans-serif;"><span style="color: rgb(51, 153, 204);"><strong>File Attachments</strong></span></span></span></p>
			</td>
			<td colspan="3" style="background-color: rgb(238, 248, 255); border-color: rgb(247, 41, 0);"><input class="file-upload-control" column="ff80818141e4ae000141e4bac5c70015" id="odovbe1386916423323_publishAttachment" multiple="multiple" name="publishAttachment" style="top: 812px; left: 223px; position: absolute;" table="ff80818141e4ae000141e4b6b3060000" type="file"></td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<p>&nbsp;&nbsp;&nbsp; <input column="8a10d4594309ddbf01430a9b789e0091" id="odovbe1386916423323_autoGenId" name="autoGenId" style="top: 859px; left: 16px; position: absolute;" table="ff80818141e4ae000141e4b6b3060000" type="text"></p>
</div>
</form>
',false,false,'<html lang="en">
	<body id="default_body" class="eazybpmBody">	
		<div style="background: none repeat scroll 0px 0px white; border-radius: 0px 3px 3px; min-height: 570px; padding: 10px;">
			<div class="row-fluid">
			   <div class="span12">
					<div class="row-fluid">
						<div class="widget">
							<div class="widget-body">
								<form class="form-horizontal no-margin well">
								<h5 class="text-info">Auto_AUTOFORMGEN</h5>
								<hr>
								<div class="control-group">
									<label style="float: left; text-align: right; width: 160px;">TIMESTAMP</label>
									<div class="controls">
										${TIMESTAMP}
									 </div>
				 				</div>
								<div class="control-group">
									<label style="float: left; text-align: right; width: 160px;">INT</label>
									<div class="controls">
										${INT}
				 					</div>
				 				</div>
								<div class="control-group">
									<label style="float: left; text-align: right; width: 160px;">VARCHAER</label>
									<div class="controls">
										${VARCHAER}
				 					</div>
				 				</div>
								<div class="control-group">
									<label style="float: left; text-align: right; width: 160px;">BOOLEAN</label>
									<div class="controls">
										${BOOLEAN}
									 </div>
				 				</div>
								<div class="control-group">
									<label style="float: left; text-align: right; width: 160px;">MEDINT</label>
									<div class="controls">
										${MEDINT}
									</div>
								</div>
								<div class="control-group">
									<label style="float: left; text-align: right; width: 160px;">FLOAT</label>
									<div class="controls">
										${FLOAT}
									</div>
								</div>
					            <div class="control-group">
									<label style="float: left; text-align: right; width: 160px;">TINYINT</label>
									<div class="controls">
										${TINYINT}
									</div>
								</div>
					            <div class="control-group">
									<label style="float: left; text-align: right; width: 160px;">SMALLINT</label>
									<div class="controls">
										${SMALLINT}
									</div>
								</div>
								<div class="control-group">
									<label style="float: left; text-align: right; width: 160px;">DOUBLE</label>
									<div class="controls">
										${DOUBLE}
									</div>
								</div> 
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</body> 
</html>',false,1149,'PUBLISHFILE',false,5,'<?xml version=\'1.0\' encoding=\'utf-8\'?><form initiator = \'PublishedFile\' id=\'PublishedFile\' name=\'PublishedFile\' action=\'#\' label=\'PublishedFile\' tableName=\'PUBLISHFILE\' tableId=\'ff80818141e4ae000141e4b6b3060000\'  method=\'post\'><extensionElements><formProperty type= \'text\' id=\'PublishedFile_publishOrg\' name=\'publishOrg\' isSubForm=\'false\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4b6b3f00007\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'PublishedFile_reg3\' name=\'reg3\' isSubForm=\'false\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4b7ed27000c\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'PublishedFile_reg2\' name=\'reg2\' isSubForm=\'false\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4b7ed27000b\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'PublishedFile_reg1\' name=\'reg1\' isSubForm=\'false\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4b7ed27000d\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'PublishedFile_creator\' name=\'creator\' isSubForm=\'false\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4bac5c60010\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'PublishedFile_title\' name=\'title\' isSubForm=\'false\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4bac5c70014\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'PublishedFile_subKeyWords\' name=\'subKeyWords\' isSubForm=\'false\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4bac5c70012\' optionType=\'null\'></formProperty><formProperty type= \'hidden\' id=\'PublishedFile_sendto\' name=\'sendto\' isSubForm=\'false\'  fieldClass=\'displayNone user-list-hidden\'  table=\'null\' column=\'ff80818141e4ae000141e4bac5c70018\' optionType=\'null\'></formProperty><formProperty type= \'userlist\' id=\'PublishedFile_sendto_FullName\' name=\'sendto_FullName\' isSubForm=\'false\'  fieldClass=\'undefined\'  onclick= "showUserSelection(\'User\', \'multi\' , \'odovbe1386916423323_sendto\',document.getElementById(\'odovbe1386916423323_sendto\'), \'\');" table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4bac5c70018\' optionType=\'null\'></formProperty><formProperty type= \'hidden\' id=\'PublishedFile_ccToUser\' name=\'ccToUser\' isSubForm=\'false\'  fieldClass=\'displayNone user-list-hidden\'  table=\'null\' column=\'ff80818141e4ae000141e4bac5c70016\' optionType=\'null\'></formProperty><formProperty type= \'userlist\' id=\'PublishedFile_ccToUser_FullName\' name=\'ccToUser_FullName\' isSubForm=\'false\'  fieldClass=\'mandatory\'  onclick= "showUserSelection(\'User\', \'multi\' , \'6sndlv1392390843493_ccToUser\',document.getElementById(\'6sndlv1392390843493_ccToUser\'), \'\');" table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4bac5c70016\' optionType=\'null\'></formProperty><formProperty type= \'departmentlist\' id=\'PublishedFile_ccToDepartment\' name=\'ccToDepartment\' isSubForm=\'false\'  onclick= "showDepartmentSelection(\'Department\', \'single\' , this.id, this, \'\');" table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4bac5c6000f\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'PublishedFile_isOfficerCheck\' name=\'isOfficerCheck\' isSubForm=\'false\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4d019f80020\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'PublishedFile_isComplete\' name=\'isComplete\' isSubForm=\'false\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4d019f8001f\' optionType=\'null\'></formProperty><formProperty type= \'file\' id=\'PublishedFile_publishAttachment\' name=\'publishAttachment\' isSubForm=\'false\'  fieldClass=\'file-upload-control\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4bac5c70015\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'PublishedFile_autoGenId\' name=\'autoGenId\' isSubForm=\'false\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'8a10d4594309ddbf01430a9b789e0091\' optionType=\'null\'></formProperty><formProperty type= \'select\' id=\'PublishedFile_urgency\' name=\'urgency\' isSubForm=\'false\'  value=\'high\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4bac5c5000e\' optionType=\'static\' dataDictionary=\'\'><formProperty type= \'option\' value=\'Normal\' label=\'Normal\'></formProperty><formProperty type= \'option\' value=\'high\' label=\'high\'></formProperty></formProperty><formProperty type= \'textarea\' id=\'PublishedFile_LeaderOpinion\' name=\'LeaderOpinion\' isSubForm=\'false\' rows=\'\' cols=\'\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4bac5c70013\'  ></formProperty><formProperty type= \'textarea\' id=\'PublishedFile_chargeLeadOpinion\' name=\'chargeLeadOpinion\' isSubForm=\'false\' rows=\'\' cols=\'\'  table=\'ff80818141e4ae000141e4b6b3060000\' column=\'ff80818141e4ae000141e4bac5c60011\'  ></formProperty></extensionElements></form>','ff80818141e4ae000141e4b6b3060000'),('8a10d45945890c63014597d160a50182',true,'admin','2014-07-31 15:01:07','9d0wq1392392178050','RequestInstr','<form action="#" enctype="multipart/form-data" id="RequestInstr" imagepath="/resources/admin/B@31f2013.png" method="post" name="RequestInstr" style="min-height:530px;width:1371.4285714285716px;height:1078px;background-image:url(/resources/admin/B@31f2013.png);background-repeat: no-repeat;" table="8a10d45942311fe301423203be38007f">
<div id="form_di">
<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><textarea column="8a10d45942311fe301423203c2010088" id="augq8j1387286720263_leaderSugg" name="leaderSugg" style="top: 159px; left: 229px; position: absolute; width: 400px;" table="8a10d45942311fe301423203be38007f"></textarea></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><textarea class="mandatory" column="8a10d45942311fe30142320702630094" id="f51uw1390833428249_affaire" name="affaire" style="top: 270px; left: 229px; position: absolute; width: 400px;" table="8a10d45942311fe301423203be38007f"></textarea></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><input class="file-upload-control" column="8a10d45942311fe3014232070263008f" id="augq8j1387286720263_reqAttachment" multiple="multiple" name="reqAttachment" style="top: 374px; left: 227px; position: absolute;" table="8a10d45942311fe301423203be38007f" type="file"></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><input class="displayNone user-list-hidden" column="8a10d45942311fe30142320702630095" id="augq8j1387286720263_reqorg" name="reqorg" style="top:537px;left:3px;position:absolute;" userlistrandom="180557233" type="hidden"><input class="undefined" column="8a10d45942311fe30142320702630095" id="augq8j1387286720263_reqorgFullName" name="reqorg_FullName" onclick="showUserSelection(\'User\', \'multi\' , \'augq8j1387286720263_reqorg\',document.getElementById(\'augq8j1387286720263_reqorg\'), \'\');" selecttype="multi" style="position: absolute; left: 230px; width: 150px; top: 509px;" table="8a10d45942311fe301423203be38007f" userlistrandom="180557233" type="userlist"></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><img class="richtextbox" column="8a10d45942311fe30142320702630090" id="9d0wq1392392178050_maincontent" name="maincontent" src="/scripts/ckeditor/plugins/richtextbox/images/cke_richtextbox.png" style="top: 644px; left: 30px; position: absolute;" table="8a10d45942311fe301423203be38007f" height="350" width="755"></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><input column="8a10d45942311fe3014232070262008e" id="9d0wq1392392178050_mobileNo" name="mobileNo" style="top: 1025px; left: 674px; position: absolute; width: 120px;" table="8a10d45942311fe301423203be38007f" type="text"></p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><input column="8a10d45942311fe30142320702630093" id="augq8j1387286720263_creator" name="creator" style="top: 1025px; left: 111px; position: absolute;" table="8a10d45942311fe301423203be38007f" type="text"><input column="8a10d45942311fe30142320702630091" id="augq8j1387286720263_allOpinion" name="allOpinion" style="top: 446px; left: 537px; position: absolute; width: 250px; height: 50px;" table="8a10d45942311fe301423203be38007f" type="text"></p>

<p><input column="8a10d45942311fe30142320702630092" id="augq8j1387286720263_dealDept" name="dealDept" onclick="showDepartmentSelection(\'Department\', \'single\' , this.id, this, \'\');" selecttype="single" style="top: 448px; left: 230px; width: 150px; position: absolute;" table="8a10d45942311fe301423203be38007f" type="departmentlist"></p>
</div>
&nbsp;<script type="text/javascript">function setCKEditorCSS(){ if(document.getElementById("cke_9d0wq1392392178050_maincontent") != undefined){ document.getElementById("cke_9d0wq1392392178050_maincontent").style = "top: 644px; left: 26px; position: absolute; width:755px";} else {   setTimeout(function(){setCKEditorCSS();});}}setCKEditorCSS();</script></form>

<p>&nbsp;</p>
',false,false,'leaderSugg:${leaderSugg} <br/>
affaire:${affaire}  <br/>
reqAttachment:${reqAttachment}  <br/>
reqorg:${reqorg}  <br/>
reqorg_FullName:${reqorg_FullName}  <br/>
maincontent:${maincontent} <br/>
mobileNo:${mobileNo}  <br/>
creator:${creator}  <br/>
allOpinion:${allOpinion}  <br/>
dealDept:${dealDept}  <br/>',false,1071,'REQUESTINSTRUCTION',false,4,'<?xml version=\'1.0\' encoding=\'utf-8\'?><form initiator = \'RequestInstr\' id=\'RequestInstr\' name=\'RequestInstr\' action=\'#\' label=\'RequestInstr\' tableName=\'REQUESTINSTRUCTION\' tableId=\'8a10d45942311fe301423203be38007f\'  method=\'post\'><extensionElements><formProperty type= \'file\' id=\'RequestInstr_reqAttachment\' name=\'reqAttachment\' isSubForm=\'false\'  fieldClass=\'file-upload-control\'  table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe3014232070263008f\' optionType=\'null\'></formProperty><formProperty type= \'hidden\' id=\'RequestInstr_reqorg\' name=\'reqorg\' isSubForm=\'false\'  fieldClass=\'displayNone user-list-hidden\'  table=\'null\' column=\'8a10d45942311fe30142320702630095\' optionType=\'null\'></formProperty><formProperty type= \'userlist\' id=\'RequestInstr_reqorg_FullName\' name=\'reqorg_FullName\' isSubForm=\'false\'  fieldClass=\'undefined\'  onclick= "showUserSelection(\'User\', \'multi\' , \'augq8j1387286720263_reqorg\',document.getElementById(\'augq8j1387286720263_reqorg\'), \'\');" table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe30142320702630095\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'RequestInstr_mobileNo\' name=\'mobileNo\' isSubForm=\'false\'  table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe3014232070262008e\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'RequestInstr_creator\' name=\'creator\' isSubForm=\'false\'  table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe30142320702630093\' optionType=\'null\'></formProperty><formProperty type= \'text\' id=\'RequestInstr_allOpinion\' name=\'allOpinion\' isSubForm=\'false\'  table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe30142320702630091\' optionType=\'null\'></formProperty><formProperty type= \'departmentlist\' id=\'RequestInstr_dealDept\' name=\'dealDept\' isSubForm=\'false\'  onclick= "showDepartmentSelection(\'Department\', \'single\' , this.id, this, \'\');" table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe30142320702630092\' optionType=\'null\'></formProperty><formProperty type= \'textarea\' id=\'RequestInstr_leaderSugg\' name=\'leaderSugg\' isSubForm=\'false\' rows=\'\' cols=\'\'  table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe301423203c2010088\'  ></formProperty><formProperty type= \'textarea\' id=\'RequestInstr_affaire\' name=\'affaire\' isSubForm=\'false\' rows=\'\' cols=\'\'  table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe30142320702630094\'  ></formProperty><formProperty  type= \'img\' id=\'RequestInstr_maincontent\' name=\'maincontent\'  class= \'richtextbox\' isSubForm=\'false\' table=\'8a10d45942311fe301423203be38007f\' column=\'8a10d45942311fe30142320702630090\'></formProperty></extensionElements></form>','8a10d45942311fe301423203be38007f');



insert into `ETEC_MODULE_FORMS`(`from_id`,`module_id`) values ('8a10d45945890c63014597cffb5f0180','8a10d4594430be1a014430f1f44d001d'),('8a10d4594430be1a014430f4f1900024','8a10d4594430be1a014430f1f44d001d'),('8a10d45945d19ae90145d210f90f0014','8a10d4594430be1a014430f1f44d001d'),('8a10d45945890c63014597d160a50182','8a10d4594430be1a014430f1f44d001d');