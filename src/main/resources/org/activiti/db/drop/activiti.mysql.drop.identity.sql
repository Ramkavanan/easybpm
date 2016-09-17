alter table ${db.tables.prefix}ACT_ID_MEMBERSHIP 
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_MEMB_GROUP;
    
alter table ${db.tables.prefix}ACT_ID_MEMBERSHIP 
    drop FOREIGN KEY ${db.tables.prefix}ACT_FK_MEMB_USER;

drop table if exists ${db.tables.prefix}ACT_ID_INFO;
drop table if exists ${db.tables.prefix}ACT_ID_MEMBERSHIP;
drop table if exists ${db.tables.prefix}ACT_ID_GROUP;
drop table if exists ${db.tables.prefix}ACT_ID_USER;
