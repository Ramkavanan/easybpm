drop index ${db.tables.prefix}ACT_IDX_EXEC_BUSKEY on ${db.tables.prefix}ACT_RU_EXECUTION;
drop index ${db.tables.prefix}ACT_IDX_TASK_CREATE on ${db.tables.prefix}ACT_RU_TASK;
drop index ${db.tables.prefix}ACT_IDX_IDENT_LNK_USER on ${db.tables.prefix}ACT_RU_IDENTITYLINK;
drop index ${db.tables.prefix}ACT_IDX_IDENT_LNK_GROUP on ${db.tables.prefix}ACT_RU_IDENTITYLINK;
drop index ${db.tables.prefix}ACT_IDX_VARIABLE_TASK_ID on ${db.tables.prefix}ACT_RU_VARIABLE;

alter table ${db.tables.prefix}ACT_GE_BYTEARRAY 
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_BYTEARR_DEPL;

alter table ${db.tables.prefix}ACT_RU_EXECUTION
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_EXE_PROCINST;

alter table ${db.tables.prefix}ACT_RU_EXECUTION 
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_EXE_PARENT;

alter table ${db.tables.prefix}ACT_RU_EXECUTION 
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_EXE_SUPER;
    
alter table ${db.tables.prefix}ACT_RU_EXECUTION 
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_EXE_PROCDEF;

alter table ${db.tables.prefix}ACT_RU_IDENTITYLINK
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_TSKASS_TASK;

alter table ${db.tables.prefix}ACT_RU_IDENTITYLINK
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_ATHRZ_PROCEDEF;
    
alter table ${db.tables.prefix}ACT_RU_TASK
	drop FOREIGN KEY ${db.tables.prefix}ACT_FK_TASK_EXE;

alter table ${db.tables.prefix}ACT_RU_TASK
	drop FOREIGN KEY ${db.tables.prefix}ACT_FK_TASK_PROCINST;
	
alter table ${db.tables.prefix}ACT_RU_TASK
	drop FOREIGN KEY ${db.tables.prefix}ACT_FK_TASK_PROCDEF;
    
alter table ${db.tables.prefix}ACT_RU_VARIABLE
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_VAR_EXE;
    
alter table ${db.tables.prefix}ACT_RU_VARIABLE
	drop FOREIGN KEY ${db.tables.prefix}ACT_FK_VAR_PROCINST;    

alter table ${db.tables.prefix}ACT_RU_VARIABLE
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_VAR_BYTEARRAY;

alter table ${db.tables.prefix}ACT_RU_JOB
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_JOB_EXCEPTION;
    
alter table ${db.tables.prefix}ACT_RU_EVENT_SUBSCR
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_EVENT_EXEC;

alter table ${db.tables.prefix}ACT_RE_MODEL 
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_MODEL_SOURCE;

alter table ${db.tables.prefix}ACT_RE_MODEL 
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_MODEL_SOURCE_EXTRA;
    
alter table ${db.tables.prefix}ACT_RE_MODEL 
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_MODEL_DEPLOYMENT;    
    
drop index ${db.tables.prefix}ACT_IDX_ATHRZ_PROCEDEF on ${db.tables.prefix}ACT_RU_IDENTITYLINK;
drop index ${db.tables.prefix}ACT_IDX_EVENT_SUBSCR_CONFIG_ on ${db.tables.prefix}ACT_RU_EVENT_SUBSCR;
    
drop table if exists ${db.tables.prefix}ACT_GE_PROPERTY;
drop table if exists ${db.tables.prefix}ACT_RU_VARIABLE;
drop table if exists ${db.tables.prefix}ACT_GE_BYTEARRAY;
drop table if exists ${db.tables.prefix}ACT_RE_DEPLOYMENT;
drop table if exists ${db.tables.prefix}ACT_RE_MODEL;
drop table if exists ${db.tables.prefix}ACT_RU_IDENTITYLINK;
drop table if exists ${db.tables.prefix}ACT_RU_TASK;
drop table if exists ${db.tables.prefix}ACT_RE_PROCDEF;
drop table if exists ${db.tables.prefix}ACT_RU_EXECUTION;
drop table if exists ${db.tables.prefix}ACT_RU_JOB; 
drop table if exists ${db.tables.prefix}ACT_RU_EVENT_SUBSCR;