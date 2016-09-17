create table ${db.tables.prefix}ACT_ID_GROUP (
    ID_ varchar(64),
    REV_ integer,
    NAME_ varchar(255),
    TYPE_ varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_ID_MEMBERSHIP (
    USER_ID_ varchar(64),
    GROUP_ID_ varchar(64),
    primary key (USER_ID_, GROUP_ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_ID_USER (
    ID_ varchar(64),
    REV_ integer,
    FIRST_ varchar(255),
    LAST_ varchar(255),
    EMAIL_ varchar(255),
    PWD_ varchar(255),
    PICTURE_ID_ varchar(64),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_ID_INFO (
    ID_ varchar(64),
    REV_ integer,
    USER_ID_ varchar(64),
    TYPE_ varchar(64),
    KEY_ varchar(255),
    VALUE_ varchar(255),
    PASSWORD_ LONGTEXT,
    PARENT_ID_ varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

alter table ${db.tables.prefix}ACT_ID_MEMBERSHIP 
    add constraint ${db.tables.prefix}ACT_FK_MEMB_GROUP 
    foreign key (GROUP_ID_) 
    references ${db.tables.prefix}ACT_ID_GROUP (ID_);

alter table ${db.tables.prefix}ACT_ID_MEMBERSHIP 
    add constraint ${db.tables.prefix}ACT_FK_MEMB_USER 
    foreign key (USER_ID_) 
    references ${db.tables.prefix}ACT_ID_USER (ID_);

CREATE TABLE QRTZ_BLOB_TRIGGERS (
  TRIGGER_NAME varbinary(490) NOT NULL DEFAULT '',
  TRIGGER_GROUP varbinary(490) NOT NULL DEFAULT '',
  BLOB_DATA mediumblob,
  PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP),
  KEY QRTZ_BLOB_index (TRIGGER_NAME,TRIGGER_GROUP)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE QRTZ_CALENDARS (
  CALENDAR_NAME varchar(200) NOT NULL,
  CALENDAR mediumblob,
  PRIMARY KEY (CALENDAR_NAME)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE QRTZ_CHAIN_JOBS (
  TRIGGER_NAME varchar(250) NOT NULL,
  JOB_NAME varchar(500) DEFAULT NULL,
  IS_CONCURRENCY bit(1) DEFAULT NULL,
  PRIMARY KEY (TRIGGER_NAME)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE QRTZ_CRON_TRIGGERS (
  TRIGGER_NAME varbinary(490) NOT NULL DEFAULT '',
  TRIGGER_GROUP varbinary(490) NOT NULL DEFAULT '',
  CRON_EXPRESSION varchar(120) NOT NULL,
  TIME_ZONE_ID varchar(80) NOT NULL,
  PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP),
  KEY QRTZ_CRON_index (TRIGGER_NAME,TRIGGER_GROUP)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE QRTZ_FIRED_TRIGGERS (
  ENTRY_ID varchar(95) NOT NULL,
  TRIGGER_NAME varchar(200) NOT NULL,
  TRIGGER_GROUP varchar(200) NOT NULL,
  IS_VOLATILE varchar(1) NOT NULL,
  INSTANCE_NAME varchar(200) NOT NULL,
  FIRED_TIME bigint(20) NOT NULL,
  PRIORITY int(11) DEFAULT NULL,
  STATE varchar(16) NOT NULL,
  JOB_NAME varchar(200) DEFAULT '',
  JOB_GROUP varchar(200) DEFAULT '',
  IS_STATEFUL varchar(1) DEFAULT '',
  REQUESTS_RECOVERY varchar(1) DEFAULT '',
  PRIMARY KEY (ENTRY_ID),
  KEY IDX_INSTANCE_NAME (INSTANCE_NAME)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE QRTZ_JOB_DETAILS (
  JOB_NAME varbinary(490) NOT NULL DEFAULT '',
  JOB_GROUP varbinary(490) NOT NULL DEFAULT '',
  DESCRIPTION varchar(250) DEFAULT NULL,
  JOB_CLASS_NAME varchar(250) NOT NULL,
  IS_DURABLE varchar(1) NOT NULL,
  IS_VOLATILE varchar(1) NOT NULL,
  IS_STATEFUL varchar(1) NOT NULL,
  REQUESTS_RECOVERY varchar(1) NOT NULL,
  JOB_DATA mediumblob,
  TENANTID varchar(255) DEFAULT NULL,
  PRIMARY KEY (JOB_NAME,JOB_GROUP)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE QRTZ_LOCKS (
  LOCK_NAME varchar(40) NOT NULL,
  PRIMARY KEY (LOCK_NAME)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE QRTZ_SCHEDULER_STATE (
  INSTANCE_NAME varchar(200) NOT NULL,
  LAST_CHECKIN_TIME bigint(20) NOT NULL,
  CHECKIN_INTERVAL bigint(20) NOT NULL,
  PRIMARY KEY (INSTANCE_NAME)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE QRTZ_SIMPLE_TRIGGERS (
  TRIGGER_NAME varbinary(490) NOT NULL DEFAULT '',
  TRIGGER_GROUP varbinary(490) NOT NULL DEFAULT '',
  REPEAT_COUNT bigint(20) NOT NULL,
  REPEAT_INTERVAL bigint(20) NOT NULL,
  TIMES_TRIGGERED bigint(20) NOT NULL,
  PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP),
  KEY QRTZ_SIMPLE_index (TRIGGER_NAME,TRIGGER_GROUP)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE QRTZ_TRIGGERS (
  TRIGGER_NAME varbinary(490) NOT NULL DEFAULT '',
  TRIGGER_GROUP varbinary(490) NOT NULL DEFAULT '',
  JOB_NAME varbinary(490) DEFAULT NULL,
  JOB_GROUP varbinary(490) DEFAULT NULL,
  IS_VOLATILE varchar(1) DEFAULT '',
  DESCRIPTION varchar(250) DEFAULT '',
  NEXT_FIRE_TIME bigint(20) DEFAULT NULL,
  PREV_FIRE_TIME bigint(20) DEFAULT NULL,
  PRIORITY int(11) DEFAULT NULL,
  TRIGGER_STATE varchar(16) NOT NULL,
  TRIGGER_TYPE varchar(8) NOT NULL,
  START_TIME bigint(20) DEFAULT NULL,
  END_TIME bigint(20) DEFAULT NULL,
  CALENDAR_NAME varchar(200) DEFAULT NULL,
  MISFIRE_INSTR smallint(6) DEFAULT NULL,
  JOB_DATA mediumblob,
  PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP),
  KEY QRTZ_TRIGGERS_index (JOB_NAME,JOB_GROUP),
  KEY IDX_FIRE_TIME (NEXT_FIRE_TIME),
  KEY IDX_TRIGGER_STATE (TRIGGER_STATE),
  KEY IDX_TRIG_STATE_NEXT_FIRE_TIME (TRIGGER_STATE,NEXT_FIRE_TIME),
  KEY IDX_TRIGGER_NAME (TRIGGER_NAME),
  KEY IDX_TRIGGER_GROOUP (TRIGGER_GROUP),
  KEY IDX_TRIG_NAME_TRIG_GROUP (TRIGGER_NAME,TRIGGER_GROUP),
  KEY IDX_JOB_GROUP (JOB_GROUP)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE QRTZ_TRIGGER_LISTENERS (
  TRIGGER_NAME varbinary(333) NOT NULL DEFAULT '',
  TRIGGER_GROUP varbinary(333) NOT NULL DEFAULT '',
  TRIGGER_LISTENER varbinary(333) NOT NULL DEFAULT '',
  PRIMARY KEY (TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_LISTENER),
  KEY QRTZ_TRIGGERS_LISTENERS_index (TRIGGER_NAME,TRIGGER_GROUP)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS (
  TRIGGER_GROUP varchar(200) NOT NULL,
  PRIMARY KEY (TRIGGER_GROUP)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE `QRTZ_JOB_LISTENERS` (
  `JOB_NAME` varbinary(333) NOT NULL DEFAULT '',
  `JOB_GROUP` varbinary(333) NOT NULL DEFAULT '',
  `JOB_LISTENER` varbinary(333) NOT NULL DEFAULT '',
  PRIMARY KEY (`JOB_NAME`,`JOB_GROUP`,`JOB_LISTENER`),
  KEY `QRTZ_JOB_LISTENERS_index` (`JOB_NAME`,`JOB_GROUP`)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

insert into QRTZ_LOCKS (LOCK_NAME) values('CALENDAR_ACCESS');
insert into QRTZ_LOCKS (LOCK_NAME) values('JOB_ACCESS');
insert into QRTZ_LOCKS (LOCK_NAME) values('MISFIRE_ACCESS');
insert into QRTZ_LOCKS (LOCK_NAME) values('STATE_ACCESS');
insert into QRTZ_LOCKS (LOCK_NAME) values('TRIGGER_ACCESS');


CREATE TABLE `JAMES_DOMAIN` (
  `DOMAIN_NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`DOMAIN_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE `JAMES_MAILBOX` (
  `MAILBOX_ID` bigint(20) NOT NULL,
  `MAILBOX_HIGHEST_MODSEQ` bigint(20) NOT NULL,
  `MAILBOX_LAST_UID` bigint(20) NOT NULL,
  `MAILBOX_NAME` varchar(200) NOT NULL,
  `MAILBOX_NAMESPACE` varchar(200) NOT NULL,
  `MAILBOX_UID_VALIDITY` bigint(20) NOT NULL,
  `USER_NAME` varchar(200) NOT NULL,
  PRIMARY KEY (`MAILBOX_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE `JAMES_MAIL` (
  `MAILBOX_ID` bigint(20) NOT NULL,
  `MAIL_UID` bigint(20) NOT NULL,
  `MAIL_IS_ANSWERED` bit(1) NOT NULL,
  `MAIL_BODY_START_OCTET` int(11) NOT NULL,
  `MAIL_CONTENT_OCTETS_COUNT` bigint(20) NOT NULL,
  `MAIL_IS_DELETED` bit(1) NOT NULL,
  `MAIL_IS_DRAFT` bit(1) NOT NULL,
  `MAIL_IS_FLAGGED` bit(1) NOT NULL,
  `MAIL_DATE` datetime DEFAULT NULL,
  `MAIL_MIME_TYPE` varchar(200) DEFAULT NULL,
  `MAIL_MODSEQ` bigint(20) DEFAULT NULL,
  `MAIL_IS_RECENT` bit(1) NOT NULL,
  `MAIL_IS_SEEN` bit(1) NOT NULL,
  `MAIL_MIME_SUBTYPE` varchar(200) DEFAULT NULL,
  `MAIL_TEXTUAL_LINE_COUNT` bigint(20) DEFAULT NULL,
  `MAIL_BYTES` longblob NOT NULL,
  `HEADER_BYTES` mediumblob NOT NULL,
  PRIMARY KEY (`MAILBOX_ID`,`MAIL_UID`),
  KEY `I_JMS_MIL_MAIL_IS_DELETED` (`MAIL_IS_DELETED`),
  KEY `I_JMS_MIL_MAIL_IS_RECENT` (`MAIL_IS_RECENT`),
  KEY `I_JMS_MIL_MAIL_IS_SEEN` (`MAIL_IS_SEEN`),
  KEY `I_JMS_MIL_MAIL_MODSEQ` (`MAIL_MODSEQ`),
  CONSTRAINT `JAMES_MAIL_ibfk_1` FOREIGN KEY (`MAILBOX_ID`) REFERENCES `JAMES_MAILBOX` (`MAILBOX_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE `JAMES_MAIL_PROPERTY` (
  `PROPERTY_ID` bigint(20) NOT NULL,
  `PROPERTY_LINE_NUMBER` int(11) NOT NULL,
  `PROPERTY_LOCAL_NAME` varchar(500) NOT NULL,
  `PROPERTY_NAME_SPACE` varchar(500) NOT NULL,
  `PROPERTY_VALUE` varchar(1024) NOT NULL,
  `MAILBOX_ID` bigint(20) DEFAULT NULL,
  `MAIL_UID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`PROPERTY_ID`),
  KEY `MAILBOX_ID` (`MAILBOX_ID`,`MAIL_UID`),
  CONSTRAINT `JAMES_MAIL_PROPERTY_ibfk_1` FOREIGN KEY (`MAILBOX_ID`, `MAIL_UID`) REFERENCES `JAMES_MAIL` (`MAILBOX_ID`, `MAIL_UID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE `JAMES_MAIL_USERFLAG` (
  `USERFLAG_ID` bigint(20) NOT NULL,
  `USERFLAG_NAME` varchar(500) NOT NULL,
  `MAILBOX_ID` bigint(20) DEFAULT NULL,
  `MAIL_UID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`USERFLAG_ID`),
  KEY `MAILBOX_ID` (`MAILBOX_ID`,`MAIL_UID`),
  CONSTRAINT `JAMES_MAIL_USERFLAG_ibfk_1` FOREIGN KEY (`MAILBOX_ID`, `MAIL_UID`) REFERENCES `JAMES_MAIL` (`MAILBOX_ID`, `MAIL_UID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE `JAMES_RECIPIENT_REWRITE` (
  `DOMAIN_NAME` varchar(100) NOT NULL,
  `USER_NAME` varchar(100) NOT NULL,
  `TARGET_ADDRESS` varchar(100) NOT NULL,
  PRIMARY KEY (`DOMAIN_NAME`,`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE `JAMES_SUBSCRIPTION` (
  `SUBSCRIPTION_ID` bigint(20) NOT NULL,
  `MAILBOX_NAME` varchar(100) NOT NULL,
  `USER_NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`SUBSCRIPTION_ID`),
  UNIQUE KEY `U_JMS_PTN_USER_NAME` (`USER_NAME`,`MAILBOX_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE `JAMES_USER` (
  `USER_NAME` varchar(100) NOT NULL,
  `PASSWORD_HASH_ALGORITHM` varchar(100) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

CREATE TABLE `OPENJPA_SEQUENCE_TABLE` (
  `ID` tinyint(4) NOT NULL,
  `SEQUENCE_VALUE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

insert  into `JAMES_DOMAIN`(`DOMAIN_NAME`) values ('mydomain.com');