create table ${db.tables.prefix}ACT_GE_PROPERTY (
    NAME_ varchar(64),
    VALUE_ varchar(300),
    REV_ integer,
    primary key (NAME_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

insert into ${db.tables.prefix}ACT_GE_PROPERTY
values ('schema.version', '5.12-SNAPSHOT', 1);

insert into ${db.tables.prefix}ACT_GE_PROPERTY
values ('schema.history', 'create(5.12-SNAPSHOT)', 1);

insert into ${db.tables.prefix}ACT_GE_PROPERTY
values ('next.dbid', '1', 1);

create table ${db.tables.prefix}ACT_GE_BYTEARRAY (
    ID_ varchar(64),
    REV_ integer,
    NAME_ varchar(255),
    DEPLOYMENT_ID_ varchar(64),
    BYTES_ LONGTEXT,
    JSON_STRING_ LONGTEXT,
    SVG_STRING_ LONGTEXT,
    GENERATED_ TINYINT,
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_RE_DEPLOYMENT (
    ID_ varchar(64),
    NAME_ varchar(255),
    CATEGORY_ varchar(255),
    DEPLOY_TIME_ timestamp,
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_RE_MODEL (
    ID_ varchar(64) not null,
    REV_ integer,
    NAME_ varchar(255),
    KEY_ varchar(255),
    CATEGORY_ varchar(255),
    CREATE_TIME_ timestamp null,
    LAST_UPDATE_TIME_ timestamp null,
    VERSION_ integer,
    META_INFO_ varchar(4000),
    DEPLOYMENT_ID_ varchar(64),
    EDITOR_SOURCE_VALUE_ID_ varchar(64),
    EDITOR_SOURCE_EXTRA_VALUE_ID_ varchar(64),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_RU_EXECUTION (
    ID_ varchar(64),
    REV_ integer,
    PROC_INST_ID_ varchar(64),
    BUSINESS_KEY_ varchar(255),
    PARENT_ID_ varchar(64),
    PROC_DEF_ID_ varchar(64),
    SUPER_EXEC_ varchar(64),
    ACT_ID_ varchar(255),
    IS_ACTIVE_ TINYINT,
    IS_CONCURRENT_ TINYINT,
    IS_SCOPE_ TINYINT,
    IS_EVENT_SCOPE_ TINYINT,
    SUSPENSION_STATE_ integer,
    CACHED_ENT_STATE_ integer,
    primary key (ID_),
    unique ${db.tables.prefix}ACT_UNIQ_RU_BUS_KEY (PROC_DEF_ID_, BUSINESS_KEY_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_RU_JOB (
    ID_ varchar(64) NOT NULL,
	REV_ integer,
    TYPE_ varchar(255) NOT NULL,
    LOCK_EXP_TIME_ timestamp NULL,
    LOCK_OWNER_ varchar(255),
    EXCLUSIVE_ boolean,
    EXECUTION_ID_ varchar(64),
    PROCESS_INSTANCE_ID_ varchar(64),
    PROC_DEF_ID_ varchar(64),
    RETRIES_ integer,
    EXCEPTION_STACK_ID_ varchar(64),
    EXCEPTION_MSG_ varchar(4000),
    DUEDATE_ timestamp NULL,
    REPEAT_ varchar(255),
    HANDLER_TYPE_ varchar(255),
    HANDLER_CFG_ varchar(4000),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table if not exists ${db.tables.prefix}ACT_RE_PROCDEF (
    ID_ varchar(64) not null,
    REV_ integer,
    CATEGORY_ varchar(255),
    NAME_ varchar(255),
    KEY_ varchar(255) not null,
    VERSION_ integer not null,
    DEPLOYMENT_ID_ varchar(64),
    RESOURCE_NAME_ varchar(4000),
    DGRM_RESOURCE_NAME_ varchar(4000),
    DESCRIPTION_ varchar(4000),
    HAS_START_FORM_KEY_ TINYINT,
    SUSPENSION_STATE_ integer,
    IS_ACTIVE_VERSION_ TINYINT,
    CLASSIFICATION_ID_ varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_RU_TASK (
    ID_ varchar(64),
    REV_ integer,
    EXECUTION_ID_ varchar(64),
    PROC_INST_ID_ varchar(64),
    PROC_DEF_ID_ varchar(64),
    NAME_ varchar(255),
    PARENT_TASK_ID_ varchar(64),
    DESCRIPTION_ varchar(4000),
    TASK_DEF_KEY_ varchar(255),
    OWNER_ varchar(255),
    ASSIGNEE_ varchar(255),
    DELEGATION_ varchar(64),
    PRIORITY_ integer,
    CREATE_TIME_ timestamp,
    DUE_DATE_ datetime,
    SUSPENSION_STATE_ integer,
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_RU_IDENTITYLINK (
    ID_ varchar(64),
    REV_ integer,
    GROUP_ID_ varchar(255),
    TYPE_ varchar(255),
    USER_ID_ varchar(255),
    TASK_ID_ varchar(64),
    PROC_DEF_ID_ varchar(64),    
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_RU_VARIABLE (
    ID_ varchar(64) not null,
    REV_ integer,
    TYPE_ varchar(255) not null,
    NAME_ varchar(255) not null,
    EXECUTION_ID_ varchar(64),
	  PROC_INST_ID_ varchar(64),
    TASK_ID_ varchar(64),
    BYTEARRAY_ID_ varchar(64),
    DOUBLE_ double,
    LONG_ bigint,
    TEXT_ varchar(4000),
    TEXT2_ varchar(4000),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_RU_EVENT_SUBSCR (
    ID_ varchar(64) not null,
    REV_ integer,
    EVENT_TYPE_ varchar(255) not null,
    EVENT_NAME_ varchar(255),
    EXECUTION_ID_ varchar(64),
    PROC_INST_ID_ varchar(64),
    ACTIVITY_ID_ varchar(64),
    CONFIGURATION_ varchar(255),
    CREATED_ timestamp not null,
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}RE_CUSTOM_OPERATION_FUNCTION (
    ID_ varchar(64) not null,
    NAME_ varchar(255) not null,
    CALL_FUNCTION_ varchar(255),
    JS_FUNCTION_ LONGTEXT,
    HTML_PROPERTY_ LONGTEXT,
    DESCRIPTION_ LONGTEXT,
    PICTURE_BYTEARRAY_ID_ varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}RE_PROCESS_TABLE_DETAILS (
 	ID_ INT(100) not null AUTO_INCREMENT,
    PROC_INST_ID_ varchar(64),
    TABLE_NAME_  varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create index ${db.tables.prefix}ACT_IDX_EXEC_BUSKEY on ${db.tables.prefix}ACT_RU_EXECUTION(BUSINESS_KEY_);
create index ${db.tables.prefix}ACT_IDX_TASK_CREATE on ${db.tables.prefix}ACT_RU_TASK(CREATE_TIME_);
create index ${db.tables.prefix}ACT_IDX_IDENT_LNK_USER on ${db.tables.prefix}ACT_RU_IDENTITYLINK(USER_ID_);
create index ${db.tables.prefix}ACT_IDX_IDENT_LNK_GROUP on ${db.tables.prefix}ACT_RU_IDENTITYLINK(GROUP_ID_);
create index ${db.tables.prefix}ACT_IDX_EVENT_SUBSCR_CONFIG_ on ${db.tables.prefix}ACT_RU_EVENT_SUBSCR(CONFIGURATION_);
create index ${db.tables.prefix}ACT_IDX_VARIABLE_TASK_ID on ${db.tables.prefix}ACT_RU_VARIABLE(TASK_ID_);
create index ${db.tables.prefix}ACT_IDX_ATHRZ_PROCEDEF on ${db.tables.prefix}ACT_RU_IDENTITYLINK(PROC_DEF_ID_);

alter table ${db.tables.prefix}ACT_GE_BYTEARRAY
    add constraint ${db.tables.prefix}ACT_FK_BYTEARR_DEPL 
    foreign key (DEPLOYMENT_ID_) 
    references ${db.tables.prefix}ACT_RE_DEPLOYMENT (ID_);

alter table ${db.tables.prefix}ACT_RE_PROCDEF
    add constraint ${db.tables.prefix}ACT_UNIQ_PROCDEF
    unique (KEY_,VERSION_);
    
alter table ${db.tables.prefix}ACT_RU_EXECUTION
    add constraint ${db.tables.prefix}ACT_FK_EXE_PROCINST 
    foreign key (PROC_INST_ID_) 
    references ${db.tables.prefix}ACT_RU_EXECUTION (ID_) on delete cascade on update cascade;

alter table ${db.tables.prefix}ACT_RU_EXECUTION
    add constraint ${db.tables.prefix}ACT_FK_EXE_PARENT 
    foreign key (PARENT_ID_) 
    references ${db.tables.prefix}ACT_RU_EXECUTION (ID_);
    
alter table ${db.tables.prefix}ACT_RU_EXECUTION
    add constraint ${db.tables.prefix}ACT_FK_EXE_SUPER 
    foreign key (SUPER_EXEC_) 
    references ${db.tables.prefix}ACT_RU_EXECUTION (ID_);

alter table ${db.tables.prefix}ACT_RU_EXECUTION
    add constraint ${db.tables.prefix}ACT_FK_EXE_PROCDEF 
    foreign key (PROC_DEF_ID_) 
    references ${db.tables.prefix}ACT_RE_PROCDEF (ID_);
    
alter table ${db.tables.prefix}ACT_RU_IDENTITYLINK
    add constraint ${db.tables.prefix}ACT_FK_TSKASS_TASK 
    foreign key (TASK_ID_) 
    references ${db.tables.prefix}ACT_RU_TASK (ID_);
    
alter table ${db.tables.prefix}ACT_RU_IDENTITYLINK
    add constraint ${db.tables.prefix}ACT_FK_ATHRZ_PROCEDEF 
    foreign key (PROC_DEF_ID_) 
    references ${db.tables.prefix}ACT_RE_PROCDEF(ID_);
    
alter table ${db.tables.prefix}ACT_RU_TASK
    add constraint ${db.tables.prefix}ACT_FK_TASK_EXE
    foreign key (EXECUTION_ID_)
    references ${db.tables.prefix}ACT_RU_EXECUTION (ID_);
    
alter table ${db.tables.prefix}ACT_RU_TASK
    add constraint ${db.tables.prefix}ACT_FK_TASK_PROCINST
    foreign key (PROC_INST_ID_)
    references ${db.tables.prefix}ACT_RU_EXECUTION (ID_);
    
alter table ${db.tables.prefix}ACT_RU_TASK
  add constraint ${db.tables.prefix}ACT_FK_TASK_PROCDEF
  foreign key (PROC_DEF_ID_)
  references ${db.tables.prefix}ACT_RE_PROCDEF (ID_);
  
alter table ${db.tables.prefix}ACT_RU_VARIABLE 
    add constraint ${db.tables.prefix}ACT_FK_VAR_EXE 
    foreign key (EXECUTION_ID_) 
    references ${db.tables.prefix}ACT_RU_EXECUTION (ID_);

alter table ${db.tables.prefix}ACT_RU_VARIABLE
    add constraint ${db.tables.prefix}ACT_FK_VAR_PROCINST
    foreign key (PROC_INST_ID_)
    references ${db.tables.prefix}ACT_RU_EXECUTION(ID_);

alter table ${db.tables.prefix}ACT_RU_VARIABLE 
    add constraint ${db.tables.prefix}ACT_FK_VAR_BYTEARRAY 
    foreign key (BYTEARRAY_ID_) 
    references ${db.tables.prefix}ACT_GE_BYTEARRAY (ID_);

alter table ${db.tables.prefix}ACT_RU_JOB 
    add constraint ${db.tables.prefix}ACT_FK_JOB_EXCEPTION 
    foreign key (EXCEPTION_STACK_ID_) 
    references ${db.tables.prefix}ACT_GE_BYTEARRAY (ID_);

alter table ${db.tables.prefix}ACT_RU_EVENT_SUBSCR
    add constraint ${db.tables.prefix}ACT_FK_EVENT_EXEC
    foreign key (EXECUTION_ID_)
    references ${db.tables.prefix}ACT_RU_EXECUTION(ID_);
    
alter table ${db.tables.prefix}ACT_RE_MODEL 
    add constraint ${db.tables.prefix}ACT_FK_MODEL_SOURCE 
    foreign key (EDITOR_SOURCE_VALUE_ID_) 
    references ${db.tables.prefix}ACT_GE_BYTEARRAY (ID_);

alter table ${db.tables.prefix}ACT_RE_MODEL 
    add constraint ${db.tables.prefix}ACT_FK_MODEL_SOURCE_EXTRA 
    foreign key (EDITOR_SOURCE_EXTRA_VALUE_ID_) 
    references ${db.tables.prefix}ACT_GE_BYTEARRAY (ID_);
    
alter table ${db.tables.prefix}ACT_RE_MODEL 
    add constraint ${db.tables.prefix}ACT_FK_MODEL_DEPLOYMENT 
    foreign key (DEPLOYMENT_ID_) 
    references ${db.tables.prefix}ACT_RE_DEPLOYMENT (ID_); 

alter table ${db.tables.prefix}ACT_RU_TASK  ADD `SIGN_OFF_TYPE_` INT(50) NOT NULL DEFAULT '0';

alter table ${db.tables.prefix}ACT_RU_TASK  ADD `START_SCRIPT_NAME_` varchar(64);

alter table ${db.tables.prefix}ACT_RU_TASK  ADD `START_SCRIPT_` LONGTEXT;

alter table ${db.tables.prefix}ACT_RU_TASK  ADD `END_SCRIPT_NAME_` varchar(64);

alter table ${db.tables.prefix}ACT_RU_TASK  ADD `END_SCRIPT_` LONGTEXT;

alter table ${db.tables.prefix}ACT_RU_IDENTITYLINK  ADD `ORDER_` INT(50) NOT NULL DEFAULT '0';

alter table ${db.tables.prefix}ACT_RU_IDENTITYLINK  ADD `GROUP_TYPE_` varchar(64);

alter table ${db.tables.prefix}ACT_RU_IDENTITYLINK  ADD `PARENT_ID_` varchar(64);

alter table ${db.tables.prefix}ACT_RU_IDENTITYLINK  ADD `OPERATION_TYPE_` varchar(64);

alter table ${db.tables.prefix}ACT_RU_IDENTITYLINK  ADD `PROC_INS_ID_` varchar(64);

ALTER TABLE ${db.tables.prefix}ACT_RU_TASK  ADD (MAX_DAYS INT(11) DEFAULT '0',WARNING_DAYS INT(11) DEFAULT '0',DATE_TYPE VARCHAR(50),
	URGE_TIMES INT(11) DEFAULT '0',FREQUENCE_INTERVAL INT(11) DEFAULT '0',UNDEAL_OPERATION VARCHAR(50),LAST_URGED_TIME timestamp NULL,NOTIFICATION_TYPE VARCHAR(50));
	
ALTER TABLE  ${db.tables.prefix}ACT_RU_TASK  ADD (IS_START_NODE_TASK  BIT  DEFAULT 0,IS_DRAFT BIT DEFAULT 0);

ALTER TABLE  ${db.tables.prefix}ACT_RU_TASK  ADD formkey varchar(64); 

ALTER TABLE  ${db.tables.prefix}ACT_RU_TASK  ADD htmlSourceForSubForm LONGTEXT;

ALTER TABLE  ${db.tables.prefix}ACT_RU_TASK  ADD isOpinion varchar(64);

