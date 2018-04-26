create table dusdict_base_domain_group (
domain_group  varchar2(64) not null,
domain_group_seqno  number(9) not null,
business_define  varchar2(256) null,
remark  varchar2(256) null,
busi_date  varchar2(8) ,
tms  timestamp ,
vno  number(8) );

alter table dusdict_base_domain_group add  PRIMARY KEY (domain_group);

create table dusdict_base_domain (domain_name  varchar2(64) not null,
domain_chn_name  varchar2(64) not null,
business_define  varchar2(256) ,
domain_seqno  number(9) not null,
domain_group  varchar2(64) not null,
data_type  varchar2(64) not null,
data_format  varchar2(64) not null,
data_scope  varchar2(64) ,
java_type  varchar2(64) ,
oracle_type  varchar2(64) ,
jdbc_type  varchar2(64) ,
remark  varchar2(256) ,
busi_date  varchar2(8) ,
tms  timestamp ,
vno  number(8) );

alter table dusdict_base_domain add  PRIMARY KEY (domain_seqno, domain_name);

CREATE TABLE DUSDICT_BASE_FIELD
(
  SEQ_NO       NUMBER(9)                        NOT NULL,
  CHN_NAME     VARCHAR2(64 CHAR)                NOT NULL,
  ENG_NAME     VARCHAR2(64 CHAR)                NOT NULL,
  ABBREVIATE   VARCHAR2(32 CHAR)                NOT NULL,
  SOURCE       VARCHAR2(64 CHAR)                NOT NULL,
  FIELD_GROUP  VARCHAR2(32 CHAR)                NOT NULL,
  BEGINS       VARCHAR2(32 CHAR),
  REMARK       VARCHAR2(256 CHAR),
  BUSI_DATE    VARCHAR2(16 CHAR),
  TMS          TIMESTAMP(6)                     NOT NULL,
  VNO          NUMBER(9)                        NOT NULL
);

ALTER TABLE DUSDICT_BASE_FIELD ADD PRIMARY KEY (SEQ_NO);
CREATE INDEX IDX1_DICT_BASE_FIELD ON DUSDICT_BASE_FIELD (CHN_NAME, ENG_NAME);

CREATE TABLE DUSDICT_BASE_CODE
(
  DOMAIN_SEQNO     NUMBER(9)                    NOT NULL,
  DOMAIN_NAME      VARCHAR2(64 CHAR)            NOT NULL,
  CODE             VARCHAR2(8 CHAR)             NOT NULL,
  CODE_NAME        VARCHAR2(64 CHAR)            NOT NULL,
  CODE_ENGLISH     VARCHAR2(64 CHAR),
  BUSINESS_DEFINE  VARCHAR2(256 CHAR),
  JAVA_TYPE        VARCHAR2(64 CHAR)            NOT NULL,
  JAVA_NAME        VARCHAR2(64 CHAR)            NOT NULL,
  REMARK           VARCHAR2(256 CHAR),
  BUSI_DATE        VARCHAR2(8 CHAR),
  TMS              TIMESTAMP(6),
  VNO              NUMBER(8)
);

ALTER TABLE DUTENGXIAO.DUSDICT_BASE_CODE ADD  PRIMARY KEY (DOMAIN_SEQNO, CODE);


create table DusDict_field (
seq_no  number(9) not null,
eng_name  varchar2(64) not null,
chn_name  varchar2(64) not null,
eng_full_name  varchar2(64) not null,
business_define  varchar2(256) ,
source  varchar2(64) not null,
data_type  varchar2(32) ,
data_format  varchar2(32) not null,
domain_name  varchar2(32) not null,
domain_seqno  number(9) not null,
domain_group  varchar2(32) not null,
java_type  varchar2(32) not null,
java_name  varchar2(32) not null,
oracle_type  varchar2(32) not null,
jdbc_type  varchar2(32) ,
submitter  varchar2(32) ,
submit_time  varchar2(32) ,
confirmer  varchar2(32) ,
confirm_time  varchar2(32) ,
remark  varchar2(32) ,
busi_date  varchar2(32) ,
tms  timestamp ,
vno  number(9) );

alter table DUSDICT_FIELD  add PRIMARY KEY (seq_no);
