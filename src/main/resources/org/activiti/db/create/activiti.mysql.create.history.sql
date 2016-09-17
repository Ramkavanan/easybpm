create table ${db.tables.prefix}ACT_HI_PROCINST (
    ID_ varchar(64) not null,
    PROC_INST_ID_ varchar(64) not null,
    BUSINESS_KEY_ varchar(255),
    PROC_DEF_ID_ varchar(64) not null,
    START_TIME_ datetime not null,
    END_TIME_ datetime,
    DURATION_ bigint,
    START_USER_ID_ varchar(255),
    START_ACT_ID_ varchar(255),
    END_ACT_ID_ varchar(255),
    SUPER_PROCESS_INSTANCE_ID_ varchar(64),
    DELETE_REASON_ varchar(4000),
    primary key (ID_),
    unique (PROC_INST_ID_),
    unique ${db.tables.prefix}ACT_UNIQ_HI_BUS_KEY (PROC_DEF_ID_, BUSINESS_KEY_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_HI_ACTINST (
    ID_ varchar(64) not null,
    PROC_DEF_ID_ varchar(64) not null,
    PROC_INST_ID_ varchar(64) not null,
    EXECUTION_ID_ varchar(64) not null,
    ACT_ID_ varchar(255) not null,
    TASK_ID_ varchar(64),
    CALL_PROC_INST_ID_ varchar(64),
    ACT_NAME_ varchar(255),
    ACT_TYPE_ varchar(255) not null,
    ASSIGNEE_ varchar(64),
    START_TIME_ datetime not null,
    END_TIME_ datetime,
    DURATION_ bigint,
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_HI_TASKINST (
    ID_ varchar(64) not null,
    PROC_DEF_ID_ varchar(64),
    TASK_DEF_KEY_ varchar(255),
    PROC_INST_ID_ varchar(64),
    EXECUTION_ID_ varchar(64),
    NAME_ varchar(255),
    PARENT_TASK_ID_ varchar(64),
    DESCRIPTION_ varchar(4000),
    OWNER_ varchar(255),
    ASSIGNEE_ varchar(255),
    START_TIME_ datetime not null,
    END_TIME_ datetime,
    DURATION_ bigint,
    DELETE_REASON_ varchar(4000),
    PRIORITY_ integer,
    DUE_DATE_ datetime,
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_HI_VARINST (
    ID_ varchar(64) not null,
    PROC_INST_ID_ varchar(64),
    EXECUTION_ID_ varchar(64),
    TASK_ID_ varchar(64),
    NAME_ varchar(255) not null,
    VAR_TYPE_ varchar(100),
    REV_ integer,
    BYTEARRAY_ID_ varchar(64),
    DOUBLE_ double,
    LONG_ bigint,
    TEXT_ varchar(4000),
    TEXT2_ varchar(4000),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_HI_DETAIL (
    ID_ varchar(64) not null,
    TYPE_ varchar(255) not null,
    PROC_INST_ID_ varchar(64),
    EXECUTION_ID_ varchar(64),
    TASK_ID_ varchar(64),
    ACT_INST_ID_ varchar(64),
    NAME_ varchar(255) not null,
    VAR_TYPE_ varchar(255),
    REV_ integer,
    TIME_ datetime not null,
    BYTEARRAY_ID_ varchar(64),
    DOUBLE_ double,
    LONG_ bigint,
    TEXT_ varchar(4000),
    TEXT2_ varchar(4000),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_HI_COMMENT (
    ID_ varchar(64) not null,
    TYPE_ varchar(255),
    TIME_ datetime not null,
    USER_ID_ varchar(255),
    TASK_ID_ varchar(64),
    PROC_INST_ID_ varchar(64),
    ACTION_ varchar(255),
    MESSAGE_ varchar(4000),
    FULL_MSG_ LONGTEXT,
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_HI_ATTACHMENT (
    ID_ varchar(64) not null,
    REV_ integer,
    USER_ID_ varchar(255),
    NAME_ varchar(255),
    DESCRIPTION_ varchar(4000),
    TYPE_ varchar(255),
    TASK_ID_ varchar(64),
    PROC_INST_ID_ varchar(64),
    URL_ varchar(4000),
    CONTENT_ID_ varchar(64),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_HI_IDENTITYLINK (
    ID_ varchar(64),
    REV_ integer,
    GROUP_ID_ varchar(255),
    TYPE_ varchar(255),
    USER_ID_ varchar(255),
    TASK_ID_ varchar(64),
    PROC_DEF_ID_ varchar(64), 
    IDENTITY_ID varchar(64),
    `ORDER_` INT(50) NOT NULL DEFAULT '0',
    `GROUP_TYPE_` varchar(64),
    `EXECUTION_ID` varchar(64),
    `IS_ENDEVENT` varchar(32),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_HI_IDENTITYLINK_DETAIL(
    ID_ varchar(64),
    TASK_ID_ varchar(255),
    USER_ID_ varchar(255),
    IS_READ_ bit(1) DEFAULT NULL,
    PROC_INST_ID_ varchar(64),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_HI_ARCHIVED_PROCESS (
    ID_ varchar(64) not null,
    PROC_INST_ID_ varchar(64) not null,
    PROC_DEF_ID_ varchar(64) not null,
    START_TIME_ datetime not null,
    END_TIME_ datetime,
    START_USER_ID_ varchar(255),
    CLASSIFICATION_ varchar(255),
    PROCESS_NAME_ varchar(255),
    PATH_ LONGTEXT,
    primary key (ID_),
    unique (PROC_INST_ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_HI_ARCHIVED_ADMIN_DETAIL (
    ID_ varchar(64),
    ADMIN_ID_ varchar(255),
    ARCHIVED_PROC_INS_ID_ varchar(64),    
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

create table ${db.tables.prefix}ACT_HI_FORM_FILED_AUDIT (
    ID_ varchar(64) not null,
    PROC_INST_ID_ varchar(64) not null,
    MODIFIED_TIME_ datetime not null,
    MODIFIED_BY_ varchar(255),
    FILED_NAME_ varchar(255),
    CHINESE_FILED_NAME_ varchar(255),
    OLD_VALUE_ varchar(255),
    NEW_VALUE_ varchar(255),
    FORM_ID_ varchar(255),
    TASK_ID_ varchar(255),
    primary key (ID_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

alter table ${db.tables.prefix}ACT_HI_ATTACHMENT ADD `ORIGINAL_NAME_` varchar(255);
alter table ${db.tables.prefix}ACT_HI_DETAIL  ADD `COLUMN_ID_` varchar(255);
alter table ${db.tables.prefix}ACT_HI_PROCINST  ADD `IS_ARCHIVE_` boolean;
alter table ${db.tables.prefix}ACT_HI_PROCINST  ADD `START_NODE_NAME` varchar(255);
alter table ${db.tables.prefix}ACT_HI_TASKINST  ADD `SIGN_OFF_TYPE_` integer;
alter table ${db.tables.prefix}ACT_HI_DETAIL  ADD `FORM_ID_` varchar(255);
alter table ${db.tables.prefix}ACT_HI_FORM_FILED_AUDIT  MODIFY `OLD_VALUE_` varchar(4000);
alter table ${db.tables.prefix}ACT_HI_FORM_FILED_AUDIT  MODIFY `NEW_VALUE_` varchar(4000);
alter table ${db.tables.prefix}ACT_HI_PROCINST  ADD `IS_DRAFT_` boolean;
create index ${db.tables.prefix}ACT_IDX_HI_PRO_INST_END on ${db.tables.prefix}ACT_HI_PROCINST(END_TIME_);
create index ${db.tables.prefix}ACT_IDX_HI_PRO_I_BUSKEY on ${db.tables.prefix}ACT_HI_PROCINST(BUSINESS_KEY_);
create index ${db.tables.prefix}ACT_IDX_HI_${db.tables.prefix}ACT_INST_START on ${db.tables.prefix}ACT_HI_ACTINST(START_TIME_);
create index ${db.tables.prefix}ACT_IDX_HI_${db.tables.prefix}ACT_INST_END on ${db.tables.prefix}ACT_HI_ACTINST(END_TIME_);
create index ${db.tables.prefix}ACT_IDX_HI_DETAIL_PROC_INST on ${db.tables.prefix}ACT_HI_DETAIL(PROC_INST_ID_);
create index ${db.tables.prefix}ACT_IDX_HI_DETAIL_${db.tables.prefix}ACT_INST on ${db.tables.prefix}ACT_HI_DETAIL(ACT_INST_ID_);
create index ${db.tables.prefix}ACT_IDX_HI_DETAIL_TIME on ${db.tables.prefix}ACT_HI_DETAIL(TIME_);
create index ${db.tables.prefix}ACT_IDX_HI_DETAIL_NAME on ${db.tables.prefix}ACT_HI_DETAIL(NAME_);
create index ${db.tables.prefix}ACT_IDX_HI_DETAIL_TASK_ID on ${db.tables.prefix}ACT_HI_DETAIL(TASK_ID_);
create index ${db.tables.prefix}ACT_IDX_HI_PROCVAR_PROC_INST on ${db.tables.prefix}ACT_HI_VARINST(PROC_INST_ID_);
create index ${db.tables.prefix}ACT_IDX_HI_PROCVAR_NAME_TYPE on ${db.tables.prefix}ACT_HI_VARINST(NAME_, VAR_TYPE_);
create index ${db.tables.prefix}ACT_IDX_HI_${db.tables.prefix}ACT_INST_PROCINST on ${db.tables.prefix}ACT_HI_ACTINST(PROC_INST_ID_, ACT_ID_);
ALTER TABLE  ${db.tables.prefix}ACT_HI_TASKINST  ADD (IS_START_NODE_TASK  BIT  DEFAULT 0,IS_DRAFT BIT DEFAULT 0);
ALTER TABLE  ${db.tables.prefix}ACT_HI_TASKINST  ADD `TASK_TYPE` varchar(255);
alter table ${db.tables.prefix}ACT_HI_PROCINST  ADD `START_NODE_ASSIGNEE` varchar(255);
