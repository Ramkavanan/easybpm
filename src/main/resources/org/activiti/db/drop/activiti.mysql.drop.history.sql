drop index ${db.tables.prefix}ACT_IDX_HI_PRO_INST_END on ${db.tables.prefix}ACT_HI_PROCINST;
drop index ${db.tables.prefix}ACT_IDX_HI_PRO_I_BUSKEY on ${db.tables.prefix}ACT_HI_PROCINST;
drop index ${db.tables.prefix}ACT_IDX_HI_${db.tables.prefix}ACT_INST_START on ${db.tables.prefix}ACT_HI_ACTINST;
drop index ${db.tables.prefix}ACT_IDX_HI_${db.tables.prefix}ACT_INST_END on ${db.tables.prefix}ACT_HI_ACTINST;
drop index ${db.tables.prefix}ACT_IDX_HI_DETAIL_PROC_INST on ${db.tables.prefix}ACT_HI_DETAIL;
drop index ${db.tables.prefix}ACT_IDX_HI_DETAIL_${db.tables.prefix}ACT_INST on ${db.tables.prefix}ACT_HI_DETAIL;
drop index ${db.tables.prefix}ACT_IDX_HI_DETAIL_TIME on ${db.tables.prefix}ACT_HI_DETAIL;
drop index ${db.tables.prefix}ACT_IDX_HI_DETAIL_NAME on ${db.tables.prefix}ACT_HI_DETAIL;
drop index ${db.tables.prefix}ACT_IDX_HI_DETAIL_TASK_ID on ${db.tables.prefix}ACT_HI_DETAIL;
drop index ${db.tables.prefix}ACT_IDX_HI_PROCVAR_PROC_INST on ${db.tables.prefix}ACT_HI_VARINST;
drop index ${db.tables.prefix}ACT_IDX_HI_PROCVAR_NAME_TYPE on ${db.tables.prefix}ACT_HI_VARINST;
drop index ${db.tables.prefix}ACT_IDX_HI_${db.tables.prefix}ACT_INST_PROCINST on ${db.tables.prefix}ACT_HI_ACTINST;

drop table if exists ${db.tables.prefix}ACT_HI_PROCINST;
drop table if exists ${db.tables.prefix}ACT_HI_ACTINST;
drop table if exists ${db.tables.prefix}ACT_HI_VARINST;
drop table if exists ${db.tables.prefix}ACT_HI_TASKINST;
drop table if exists ${db.tables.prefix}ACT_HI_DETAIL;
drop table if exists ${db.tables.prefix}ACT_HI_COMMENT;
drop table if exists ${db.tables.prefix}ACT_HI_ATTACHMENT;
drop table if exists ${db.tables.prefix}ACT_HI_IDENTITYLINK;
drop table if exists ${db.tables.prefix}ACT_HI_IDENTITYLINK_DETAIL;
 