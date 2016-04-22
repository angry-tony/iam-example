-- DROP DATABASE iam;
CREATE DATABASE iam
  CHARACTER SET UTF8
  COLLATE UTF8_GENERAL_CI;

DROP USER 'iam'@'localhost';
CREATE USER 'iam'@'localhost'
  IDENTIFIED BY 'iam';
GRANT ALL PRIVILEGES ON *.* TO 'iam'@'localhost';
FLUSH PRIVILEGES;

USE iam;

DROP TABLE IF EXISTS iam.CONTACT_US;

CREATE TABLE IF NOT EXISTS iam.CONTACT_US (
  id           INT(11) NOT NULL AUTO_INCREMENT,
  name         VARCHAR(255)     DEFAULT NULL,
  email        VARCHAR(255)     DEFAULT NULL,
  telephone    VARCHAR(255)     DEFAULT NULL,
  subject      TEXT,
  message      TEXT,
  registration TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ID)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS iam.REQ;

CREATE TABLE IF NOT EXISTS iam.REQ (
  id           INT(11) NOT NULL AUTO_INCREMENT,
  name         VARCHAR(255)     DEFAULT NULL,
  email        VARCHAR(255)     DEFAULT NULL,
  telephone    VARCHAR(255)     DEFAULT NULL,
  type         VARCHAR(255),
  customer     TEXT,
  subject      TEXT,
  message      TEXT,
  registration TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ID)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS iam.DOWNLOAD;

CREATE TABLE IF NOT EXISTS iam.DOWNLOAD (
  id           INT(11)      NOT NULL AUTO_INCREMENT,
  name         VARCHAR(255) NOT NULL,
  email        VARCHAR(255) NOT NULL,
  company      VARCHAR(255) NOT NULL,
  token        VARCHAR(255) NOT NULL,
  type         VARCHAR(50)  NOT NULL,
  version      VARCHAR(50)  NOT NULL,
  downcount    INT(3)                DEFAULT 0,
  registration TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY (token),
  PRIMARY KEY (ID)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS iam.AUTHORITIES;
DROP TABLE IF EXISTS iam.USER;
DROP TABLE IF EXISTS iam.USER_LEVEL;

CREATE TABLE IF NOT EXISTS iam.USER_LEVEL (
  LEVEL    SMALLINT    NOT NULL,
  LEVEL_NM VARCHAR(30) NOT NULL,
  PRIMARY KEY (LEVEL)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

INSERT INTO iam.USER_LEVEL (LEVEL, LEVEL_NM) VALUES (1, '1등급');
INSERT INTO iam.USER_LEVEL (LEVEL, LEVEL_NM) VALUES (2, '2등급');
INSERT INTO iam.USER_LEVEL (LEVEL, LEVEL_NM) VALUES (3, '3등급');
INSERT INTO iam.USER_LEVEL (LEVEL, LEVEL_NM) VALUES (4, '4등급');
INSERT INTO iam.USER_LEVEL (LEVEL, LEVEL_NM) VALUES (5, '5등급');


CREATE TABLE IF NOT EXISTS iam.USER (
  ID           BIGINT(20)  NOT NULL AUTO_INCREMENT
  COMMENT 'User iD',
  EMAIL        VARCHAR(255)         DEFAULT NULL
  COMMENT 'E-mail',
  PASSWD       VARCHAR(255)         DEFAULT NULL
  COMMENT '비밀번호',
  ENABLED      BOOLEAN     NOT NULL DEFAULT FALSE,
  NM           VARCHAR(255)         DEFAULT NULL
  COMMENT '이름(Full Name)',
  DESCRIPTION  LONGTEXT COMMENT '비고',
  REG_DT       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '등록일',
  UPD_DT       TIMESTAMP   NOT NULL DEFAULT 0
  COMMENT '수정일',
  LEVEL        SMALLINT(6) NOT NULL DEFAULT '5'
  COMMENT '등급',
  FIRST_NAME   VARCHAR(255)         DEFAULT NULL
  COMMENT '이름',
  LAST_NAME    VARCHAR(255)         DEFAULT NULL
  COMMENT '성',
  ORGANIZATION VARCHAR(255)         DEFAULT NULL
  COMMENT '회',
  OFFICE_PHONE VARCHAR(255)         DEFAULT NULL
  COMMENT '회사 연락처',
  COUNTRY      CHAR(2)              DEFAULT NULL
  COMMENT '국가코드',
  ADDRESS1     LONGTEXT COMMENT '주소1',
  ADDRESS2     LONGTEXT COMMENT '주소2',
  CITY         VARCHAR(255)         DEFAULT NULL
  COMMENT '도시',
  STATE        VARCHAR(255)         DEFAULT NULL
  COMMENT '행정구역',
  ZIP          VARCHAR(255)         DEFAULT NULL
  COMMENT '우편번호',
  PHONE        VARCHAR(255)         DEFAULT NULL
  COMMENT '연락처',
  TAXID        VARCHAR(255)         DEFAULT NULL
  COMMENT '택스ID',
  FOREIGN KEY (LEVEL) REFERENCES iam.USER_LEVEL (LEVEL),
  PRIMARY KEY (ID),
  UNIQUE KEY (EMAIL)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

INSERT INTO iam.USER (ID, EMAIL, PASSWD, NM, DESCRIPTION, UPD_DT, ENABLED, LEVEL, COUNTRY)
VALUES (1, 'support@iam.co.kr', 'MEVd1+d7s2DoZt8mgx+1kg==', '시스템 관리자', NULL, CURRENT_TIMESTAMP, 1, 1, 'KR');

INSERT INTO iam.USER (ID, EMAIL, PASSWD, NM, DESCRIPTION, UPD_DT, ENABLED, LEVEL, COUNTRY)
VALUES (2, 'demo@iam.co.kr', 'MEVd1+d7s2DoZt8mgx+1kg==', '데모', NULL, CURRENT_TIMESTAMP, 1, 1, 'KR');


CREATE TABLE IF NOT EXISTS iam.AUTHORITIES (
  ID           BIGINT       NOT NULL AUTO_INCREMENT,
  USER_ID      BIGINT       NOT NULL,
  AUTHORITY    VARCHAR(100) NOT NULL DEFAULT 'ROLE_USER',
  AUTHORITY_NM VARCHAR(100) NOT NULL DEFAULT '사용자',
  PRIMARY KEY (ID),
  FOREIGN KEY (USER_ID) REFERENCES iam.USER (ID)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

INSERT INTO iam.AUTHORITIES (USER_ID, AUTHORITY, AUTHORITY_NM) VALUES (1, 'ROLE_ADMIN', '관리자');
INSERT INTO iam.AUTHORITIES (USER_ID, AUTHORITY, AUTHORITY_NM) VALUES (2, 'ROLE_USER', '사용자');


CREATE TABLE IF NOT EXISTS iam.REGISTE (
  id           INT(11)      NOT NULL AUTO_INCREMENT,
  user_id      BIGINT       NOT NULL,
  token        VARCHAR(255) NOT NULL,
  registration TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY (token),
  PRIMARY KEY (ID)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS iam.LICENSE;

CREATE TABLE iam.LICENSE (
  ID                  INT(11)      NOT NULL AUTO_INCREMENT,
  SERVER_ID           VARCHAR(256) COMMENT '사용자 맥 어드레스',
  USER_ID             BIGINT       NOT NULL
  COMMENT '사용자 아이디',
  HOLDER              VARCHAR(256) NOT NULL
  COMMENT '사용자 이름',
  ISSUE_DATE          TIMESTAMP             DEFAULT 0
  COMMENT '라이센스 시작일',
  EXPIRE_DATE         TIMESTAMP             DEFAULT 0
  COMMENT '라이센스 종료일',
  MAX_NODE            INT                   DEFAULT 1
  COMMENT '노드 수',
  DETAIL              LONGTEXT COMMENT '세부 사항 json',
  LICENSE_KEY         LONGTEXT COMMENT '라이센스 KEY',
  PRODUCT_ID          INT(11)      NOT NULL
  COMMENT '제품 ID',
  PURCHASE_HISTORY_ID INT(11)               DEFAULT NULL
  COMMENT '구매내역 ID',
  REG_DT              TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ID),
  UNIQUE KEY (PURCHASE_HISTORY_ID)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS iam.PRODUCT_INTERCEPTION;

CREATE TABLE iam.PRODUCT_INTERCEPTION (
  ID      INT(11) NOT NULL AUTO_INCREMENT,
  COUNTRY CHAR(2) COMMENT '판매 제한 국가',
  REG_DT  TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ID)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '제품군';

INSERT INTO iam.PRODUCT_INTERCEPTION (ID, COUNTRY) VALUES (1, 'KR');

DROP TABLE IF EXISTS iam.PRODUCT_FAMILY;

CREATE TABLE iam.PRODUCT_FAMILY (
  ID           INT(11)   NOT NULL AUTO_INCREMENT,
  VERSION      VARCHAR(15)        DEFAULT NULL
  COMMENT '제품 버전',
  NAME         VARCHAR(255)       DEFAULT NULL
  COMMENT '제품명',
  FAMILY       VARCHAR(255)       DEFAULT NULL
  COMMENT '제품군',
  LICENSE_TYPE VARCHAR(15)        DEFAULT 'TRIAL'
  COMMENT '라이센스 종류(SUBSCRIPTION, OEM, COMMERCIAL, TRIAL)',
  LINK         VARCHAR(255)       DEFAULT NULL
  COMMENT '다운로드 링크',
  REG_DT       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ID)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8
  COMMENT = '제품군';

INSERT INTO iam.PRODUCT_FAMILY VALUES
  (1, '2.0.0', 'Flamingo2', 'SUBSCRIPTION', 'SUBSCRIPTION', '', '2015-06-23 11:50:30'),
  (2, '2.0.0', 'Flamingo2', 'TRIAL', 'TRIAL', '', '2015-06-23 11:50:30'),
  (3, '2.0.0', 'Flamingo2', 'OEM', 'OEM', '', '2015-06-23 11:50:30'),
  (4, '2.0.0', 'Flamingo2', 'COMMERCIAL', 'COMMERCIAL', '', '2015-06-23 11:50:30'),
  (5, '1.0.0', 'Bahama', 'SUBSCRIPTION', 'SUBSCRIPTION', '', '2015-06-23 02:50:30'),
  (6, '1.0.0', 'Bahama', 'TRIAL', 'TRIAL', '', '2015-06-23 02:50:30'),
  (7, '1.0.0', 'Bahama', 'OEM', 'OEM', '', '2015-06-23 02:50:30'),
  (8, '1.0.0', 'Bahama', 'COMMERCIAL', 'COMMERCIAL', '', '2015-06-23 02:50:30'),
  (9, '2.0.0', 'Hawq', 'SUBSCRIPTION', 'SUBSCRIPTION', '', '2015-06-23 02:50:30'),
  (10, '2.0.0', 'Hawq', 'TRIAL', 'TRIAL', '', '2015-06-23 02:50:30'),
  (11, '2.0.0', 'Hawq', 'OEM', 'OEM', '', '2015-06-23 02:50:30'),
  (12, '2.0.0', 'Hawq', 'COMMERCIAL', 'COMMERCIAL', '', '2015-06-23 02:50:30');

DROP TABLE IF EXISTS iam.PRODUCT_BUILD;

CREATE TABLE iam.PRODUCT_BUILD (
  ID                INT(11)   NOT NULL AUTO_INCREMENT
  COMMENT 'ID',
  PRODUCT_FAMILY_ID INT(11)            DEFAULT NULL
  COMMENT 'PRODUCT_FAMILY_ID',
  BUILD_VERSION     VARCHAR(15)        DEFAULT NULL
  COMMENT '빌드 버전',
  BUILD_LINK        VARCHAR(255)       DEFAULT NULL
  COMMENT '빌드 링크',
  REG_DT            TIMESTAMP NULL     DEFAULT CURRENT_TIMESTAMP
  COMMENT '등록일',
  PRIMARY KEY (ID)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 49
  DEFAULT CHARSET = utf8;

INSERT INTO iam.PRODUCT_BUILD
VALUES (1, 1, '2.0.1', '', '2015-06-23 02:50:30'), (2, 1, '2.0.2', '', '2015-06-23 02:50:30'),
  (3, 1, '2.0.3', '', '2015-06-23 02:50:30'), (4, 1, '2.0.4', '', '2015-06-23 02:50:30'),
  (5, 2, '2.0.1', '', '2015-06-23 02:50:30'), (6, 2, '2.0.2', '', '2015-06-23 02:50:30'),
  (7, 2, '2.0.3', '', '2015-06-23 02:50:30'), (8, 2, '2.0.4', '', '2015-06-23 02:50:30'),
  (9, 3, '2.0.1', '', '2015-06-23 02:50:30'), (10, 3, '2.0.2', '', '2015-06-23 02:50:30'),
  (11, 3, '2.0.3', '', '2015-06-23 02:50:30'), (12, 3, '2.0.4', '', '2015-06-23 02:50:30'),
  (13, 4, '2.0.1', '', '2015-06-23 02:50:30'), (14, 4, '2.0.2', '', '2015-06-23 02:50:30'),
  (15, 4, '2.0.3', '', '2015-06-23 02:50:30'), (16, 4, '2.0.4', '', '2015-06-23 02:50:30'),
  (17, 5, '1.0.1', '', '2015-06-23 02:50:30'), (18, 5, '1.0.2', '', '2015-06-23 02:50:30'),
  (19, 5, '1.0.3', '', '2015-06-23 02:50:30'), (20, 5, '1.0.4', '', '2015-06-23 02:50:30'),
  (21, 6, '1.0.1', '', '2015-06-23 02:50:30'), (22, 6, '1.0.2', '', '2015-06-23 02:50:30'),
  (23, 6, '1.0.3', '', '2015-06-23 02:50:30'), (24, 6, '1.0.4', '', '2015-06-23 02:50:30'),
  (25, 7, '1.0.1', '', '2015-06-23 02:50:30'), (26, 7, '1.0.2', '', '2015-06-23 02:50:30'),
  (27, 7, '1.0.3', '', '2015-06-23 02:50:30'), (28, 7, '1.0.4', '', '2015-06-23 02:50:30'),
  (29, 8, '1.0.1', '', '2015-06-23 02:50:30'), (30, 8, '1.0.2', '', '2015-06-23 02:50:30'),
  (31, 8, '1.0.3', '', '2015-06-23 02:50:30'), (32, 8, '1.0.4', '', '2015-06-23 02:50:30'),
  (33, 9, '2.0.1', '', '2015-06-23 02:50:30'), (34, 9, '2.0.2', '', '2015-06-23 02:50:30'),
  (35, 9, '2.0.3', '', '2015-06-23 02:50:30'), (36, 9, '2.0.4', '', '2015-06-23 02:50:30'),
  (37, 10, '2.0.1', '', '2015-06-23 02:50:30'), (38, 10, '2.0.2', '', '2015-06-23 02:50:30'),
  (39, 10, '2.0.3', '', '2015-06-23 02:50:30'), (40, 10, '2.0.4', '', '2015-06-23 02:50:30'),
  (41, 11, '2.0.1', '', '2015-06-23 02:50:30'), (42, 11, '2.0.2', '', '2015-06-23 02:50:30'),
  (43, 11, '2.0.3', '', '2015-06-23 02:50:30'), (44, 11, '2.0.4', '', '2015-06-23 02:50:30'),
  (45, 12, '2.0.1', '', '2015-06-23 02:50:30'), (46, 12, ';.0.2', '', '2015-06-23 02:50:30'),
  (47, 12, ';.0.3', '', '2015-06-23 02:50:30'), (48, 12, ';.0.4', '', '2015-06-23 02:50:30');


DROP TABLE IF EXISTS iam.PRODUCT_FEATURES;

CREATE TABLE iam.PRODUCT_FEATURES (
  ID                INT(11) NOT NULL AUTO_INCREMENT,
  PRODUCT_FAMILY_ID INT(11) NOT NULL
  COMMENT '제품군 아이디',
  FEATURE           VARCHAR(255) COMMENT '제품기능',
  REG_DT            TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ID)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '제품기능';


INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (1, 'MONITORING');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (1, 'WORKFLOW');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (1, 'HIVE');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (1, 'PIG');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (1, 'HDFSBROWSER');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (1, 'ALGORITHM');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (1, 'HAWQ');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (1, 'BATCH');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (1, 'RSTUDIO');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (1, 'VISUALIZATION');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (1, 'ARCHIVE');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (1, 'TERMINAL');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (1, 'MANAGEMENT');

INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (2, 'MONITORING');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (2, 'WORKFLOW');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (2, 'HIVE');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (2, 'PIG');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (2, 'HDFSBROWSER');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (2, 'ALGORITHM');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (2, 'HAWQ');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (2, 'BATCH');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (2, 'RSTUDIO');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (2, 'VISUALIZATION');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (2, 'ARCHIVE');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (2, 'TERMINAL');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (2, 'MANAGEMENT');

INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (3, 'MONITORING');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (3, 'WORKFLOW');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (3, 'HIVE');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (3, 'PIG');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (3, 'HDFSBROWSER');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (3, 'ALGORITHM');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (3, 'HAWQ');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (3, 'BATCH');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (3, 'RSTUDIO');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (3, 'VISUALIZATION');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (3, 'ARCHIVE');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (3, 'TERMINAL');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (3, 'MANAGEMENT');

INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (4, 'MONITORING');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (4, 'WORKFLOW');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (4, 'HIVE');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (4, 'PIG');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (4, 'HDFSBROWSER');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (4, 'ALGORITHM');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (4, 'HAWQ');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (4, 'BATCH');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (4, 'RSTUDIO');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (4, 'VISUALIZATION');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (4, 'ARCHIVE');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (4, 'TERMINAL');
INSERT INTO iam.PRODUCT_FEATURES (PRODUCT_FAMILY_ID, FEATURE) VALUES (4, 'MANAGEMENT');


DROP TABLE IF EXISTS iam.PRODUCT;

CREATE TABLE iam.PRODUCT (
  ID                INT(11) NOT NULL AUTO_INCREMENT,
  PRODUCT_FAMILY_ID INT(11) NOT NULL
  COMMENT '제품군 아이디',
  DAYS              INT              DEFAULT 0
  COMMENT '기간',
  COUNTRY           CHAR(2) COMMENT '국가코드',
  MAX_NODE          INT              DEFAULT 10
  COMMENT '노드수',
  PRICE             BIGINT           DEFAULT 0
  COMMENT '가격',
  REG_DT            TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ID)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '제품';


-- Trial, 1 node , 15 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (2, 15, 'XX', 1, 0);

-- OEM, 9999 node , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (3, 365, 'XX', 9999, 8000);

-- Commercial, 9999 node , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (4, 365, 'XX', 9999, 9000);


-- subscription, 10 node , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (1, 365, 'XX', 10, 5000);

-- subscription, 20 node , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (1, 365, 'XX', 20, 10000);

-- subscription, 30 node , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (1, 365, 'XX', 30, 15000);

-- subscription, 40 node , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (1, 365, 'XX', 40, 20000);

-- subscription, 50 node , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (1, 365, 'XX', 50, 25000);

-- subscription, 50 node+ , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (1, 365, 'XX', 0, 50000);


-- subscription, 10 node , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (1, 365, 'KR', 10, 26400);

-- subscription, 20 node , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (1, 365, 'KR', 20, 35000);

-- subscription, 30 node , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (1, 365, 'KR', 30, 44000);

-- subscription, 40 node , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (1, 365, 'KR', 40, 52900);

-- subscription, 50 node , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (1, 365, 'KR', 50, 61700);

-- subscription, 50 node+ , 365 days
INSERT INTO iam.PRODUCT (PRODUCT_FAMILY_ID, DAYS, COUNTRY, MAX_NODE, PRICE) VALUES (1, 365, 'KR', 0, 123500);


DROP TABLE IF EXISTS iam.PURCHASE_HISTORY;

CREATE TABLE iam.PURCHASE_HISTORY (
  ID                     INT(11)      NOT NULL AUTO_INCREMENT,
  USER_ID                BIGINT       NOT NULL
  COMMENT '사용자 ID',
  IDENTIFIER             VARCHAR(255),
  RESELLER               BOOLEAN               DEFAULT FALSE,
  PRODUCT_ID             INT(11)      NOT NULL
  COMMENT '제품 ID',
  PURCHASE_YN            CHAR(1)               DEFAULT 'N'
  COMMENT '결제여부',
  PURCHASE_DATE          TIMESTAMP             DEFAULT CURRENT_TIMESTAMP
  COMMENT '구매시간',
  USER_EMAIL             VARCHAR(255) NOT NULL,
  USER_FIRST_NAME        VARCHAR(255),
  USER_LAST_NAME         VARCHAR(255),
  USER_PHONE             VARCHAR(255),
  TECH_EMAIL             VARCHAR(255),
  TECH_FIRST_NAME        VARCHAR(255),
  TECH_LAST_NAME         VARCHAR(255),
  TECH_PHONE             VARCHAR(255),
  BILL_EMAIL             VARCHAR(255),
  BILL_FIRST_NAME        VARCHAR(255),
  BILL_LAST_NAME         VARCHAR(255),
  BILL_PHONE             VARCHAR(255),
  RESELLER_COMP_NAME     VARCHAR(255),
  RESELLER_COMP_COUNTRY  CHAR(2),
  RESELLER_COMP_ADDRESS1 VARCHAR(255),
  RESELLER_COMP_ADDRESS2 VARCHAR(255),
  RESELLER_COMP_CITY     VARCHAR(255),
  RESELLER_COMP_STATE    VARCHAR(255),
  RESELLER_COMP_ZIP      VARCHAR(255),
  ENDUSER_COMP_NAME      VARCHAR(255),
  ENDUSER_COMP_COUNTRY   CHAR(2),
  ENDUSER_COMP_ADDRESS1  VARCHAR(255),
  ENDUSER_COMP_ADDRESS2  VARCHAR(255),
  ENDUSER_COMP_CITY      VARCHAR(255),
  ENDUSER_COMP_STATE     VARCHAR(255),
  ENDUSER_COMP_ZIP       VARCHAR(255),
  NOTE                   LONGTEXT COMMENT '주문 요청 사항',
  DETAIL                 LONGTEXT COMMENT '주문 세부 사항 json 스트링',
  PRIMARY KEY (ID)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT = '구매내역';

DROP TABLE IF EXISTS iam.REFUND;

CREATE TABLE iam.REFUND (
  ID                  INT(11) NOT NULL AUTO_INCREMENT,
  PURCHASE_HISTORY_ID INT(11) NOT NULL
  COMMENT '구매 ID',
  REASON              VARCHAR(256) COMMENT '환불 사유',
  APPROVAL_YN         CHAR(1)          DEFAULT 'N'
  COMMENT '승인여부',
  APPROVAL_DATE       TIMESTAMP        DEFAULT 0
  COMMENT '승인날짜',
  REQ_DATE            TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
  COMMENT '요청날짜',
  PRIMARY KEY (ID)
)
  ENGINE = INNODB
  DEFAULT CHARSET = UTF8
  COMMENT = '환불내역';

DROP TABLE IF EXISTS iam.PAYMENT_HISTORY;

CREATE TABLE IF NOT EXISTS iam.PAYMENT_HISTORY (
  ID                  INT(11) NOT NULL AUTO_INCREMENT,
  USER_ID             BIGINT  NOT NULL
  COMMENT '결제자 ID',
  AMOUNT              VARCHAR(255) COMMENT '결제액',
  FEE                 VARCHAR(255) COMMENT '페이팔 이용대금',
  IDENTIFIER          VARCHAR(255) COMMENT '구매내역 IDENTIFIER',
  PURCHASE_HISTORY_ID INT(11) COMMENT '구매내역 ID',
  TRANSATION_ID       VARCHAR(255) COMMENT '트랜잭션 ID',
  ITEM_NAME           VARCHAR(255) COMMENT '아이템명',
  PAY_LOG             LONGTEXT COMMENT 'IPN 메세지 원본 JSON',
  ERR_LOG             LONGTEXT COMMENT '결제 에러 로그',
  PAYMENT_DATE        TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
  COMMENT '결제일',
  PRIMARY KEY (ID)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS iam.COUNTRY;

CREATE TABLE iam.COUNTRY (
  CODE         VARCHAR(8) NOT NULL
  COMMENT '국가코드',
  COUNTRY_NAME VARCHAR(150) DEFAULT NULL
  COMMENT '국가명',
  PRIMARY KEY (CODE)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO COUNTRY
VALUES ('AD', 'Andorra'), ('AE', 'United Arab Emirates'), ('AF', 'Afghanistan'), ('AG', 'Antigua and Barbuda'),
  ('AI', 'Anguilla'), ('AL', 'Albania'), ('AM', 'Armenia'), ('AO', 'Angola'), ('AQ', 'Antarctica'), ('AR', 'Argentina'),
  ('AS', 'American Samoa'), ('AT', 'Austria'), ('AU', 'Australia'), ('AW', 'Aruba'), ('AX', 'Åland Islands'),
  ('AZ', 'Azerbaijan'), ('BA', 'Bosnia and Herzegovina'), ('BB', 'Barbados'), ('BD', 'Bangladesh'), ('BE', 'Belgium'),
  ('BF', 'Burkina Faso'), ('BG', 'Bulgaria'), ('BH', 'Bahrain'), ('BI', 'Burundi'), ('BJ', 'Benin'),
  ('BL', 'Saint Barthélemy'), ('BM', 'Bermuda'), ('BN', 'Brunei Darussalam'), ('BO', 'Bolivia, Plurinational State of'),
  ('BQ', 'Bonaire, Sint Eustatius and Saba'), ('BR', 'Brazil'), ('BS', 'Bahamas'), ('BT', 'Bhutan'),
  ('BV', 'Bouvet Island'), ('BW', 'Botswana'), ('BY', 'Belarus'), ('BZ', 'Belize'), ('CA', 'Canada'),
  ('CC', 'Cocos (Keeling) Islands'), ('CD', 'Congo, the Democratic Republic of the'),
  ('CF', 'Central African Republic'), ('CG', 'Congo'), ('CH', 'Switzerland'), ('CI', 'Côte d\'Ivoire'),
  ('CK', 'Cook Islands'), ('CL', 'Chile'), ('CM', 'Cameroon'), ('CN', 'China'), ('CO', 'Colombia'),
  ('CR', 'Costa Rica'), ('CU', 'Cuba'), ('CV', 'Cape Verde'), ('CW', 'Curaçao'), ('CX', 'Christmas Island'),
  ('CY', 'Cyprus'), ('CZ', 'Czech Republic'), ('DE', 'Germany'), ('DJ', 'Djibouti'), ('DK', 'Denmark'),
  ('DM', 'Dominica'), ('DO', 'Dominican Republic'), ('DZ', 'Algeria'), ('EC', 'Ecuador'), ('EE', 'Estonia'),
  ('EG', 'Egypt'), ('EH', 'Western Sahara'), ('ER', 'Eritrea'), ('ES', 'Spain'), ('ET', 'Ethiopia'), ('FI', 'Finland'),
  ('FJ', 'Fiji'), ('FK', 'Falkland Islands (Malvinas)'), ('FM', 'Micronesia, Federated States of'),
  ('FO', 'Faroe Islands'), ('FR', 'France'), ('GA', 'Gabon'), ('GB', 'United Kingdom'), ('GD', 'Grenada'),
  ('GE', 'Georgia'), ('GF', 'French Guiana'), ('GG', 'Guernsey'), ('GH', 'Ghana'), ('GI', 'Gibraltar'),
  ('GL', 'Greenland'), ('GM', 'Gambia'), ('GN', 'Guinea'), ('GP', 'Guadeloupe'), ('GQ', 'Equatorial Guinea'),
  ('GR', 'Greece'), ('GS', 'South Georgia and the South Sandwich Islands'), ('GT', 'Guatemala'), ('GU', 'Guam'),
  ('GW', 'Guinea-Bissau'), ('GY', 'Guyana'), ('HK', 'Hong Kong'), ('HM', 'Heard Island and McDonald Islands'),
  ('HN', 'Honduras'), ('HR', 'Croatia'), ('HT', 'Haiti'), ('HU', 'Hungary'), ('ID', 'Indonesia'), ('IE', 'Ireland'),
  ('IL', 'Israel'), ('IM', 'Isle of Man'), ('IN', 'India'), ('IO', 'British Indian Ocean Territory'), ('IQ', 'Iraq'),
  ('IR', 'Iran, Islamic Republic of'), ('IS', 'Iceland'), ('IT', 'Italy'), ('JE', 'Jersey'), ('JM', 'Jamaica'),
  ('JO', 'Jordan'), ('JP', 'Japan'), ('KE', 'Kenya'), ('KG', 'Kyrgyzstan'), ('KH', 'Cambodia'), ('KI', 'Kiribati'),
  ('KM', 'Comoros'), ('KN', 'Saint Kitts and Nevis'), ('KP', 'Korea, Democratic People\'s Republic of'),
  ('KR', 'Korea, Republic of'), ('KW', 'Kuwait'), ('KY', 'Cayman Islands'), ('KZ', 'Kazakhstan'),
  ('LA', 'Lao People\'s Democratic Republic'), ('LB', 'Lebanon'), ('LC', 'Saint Lucia'), ('LI', 'Liechtenstein'),
  ('LK', 'Sri Lanka'), ('LR', 'Liberia'), ('LS', 'Lesotho'), ('LT', 'Lithuania'), ('LU', 'Luxembourg'),
  ('LV', 'Latvia'), ('LY', 'Libya'), ('MA', 'Morocco'), ('MC', 'Monaco'), ('MD', 'Moldova, Republic of'),
  ('ME', 'Montenegro'), ('MF', 'Saint Martin (French part)'), ('MG', 'Madagascar'), ('MH', 'Marshall Islands'),
  ('MK', 'Macedonia, the former Yugoslav Republic of'), ('ML', 'Mali'), ('MM', 'Myanmar'), ('MN', 'Mongolia'),
  ('MO', 'Macao'), ('MP', 'Northern Mariana Islands'), ('MQ', 'Martinique'), ('MR', 'Mauritania'), ('MS', 'Montserrat'),
  ('MT', 'Malta'), ('MU', 'Mauritius'), ('MV', 'Maldives'), ('MW', 'Malawi'), ('MX', 'Mexico'), ('MY', 'Malaysia'),
  ('MZ', 'Mozambique'), ('NA', 'Namibia'), ('NC', 'New Caledonia'), ('NE', 'Niger'), ('NF', 'Norfolk Island'),
  ('NG', 'Nigeria'), ('NI', 'Nicaragua'), ('NL', 'Netherlands'), ('NO', 'Norway'), ('NP', 'Nepal'), ('NR', 'Nauru'),
  ('NU', 'Niue'), ('NZ', 'New Zealand'), ('OM', 'Oman'), ('PA', 'Panama'), ('PE', 'Peru'), ('PF', 'French Polynesia'),
  ('PG', 'Papua New Guinea'), ('PH', 'Philippines'), ('PK', 'Pakistan'), ('PL', 'Poland'),
  ('PM', 'Saint Pierre and Miquelon'), ('PN', 'Pitcairn'), ('PR', 'Puerto Rico'),
  ('PS', 'Palestinian Territory, Occupied'), ('PT', 'Portugal'), ('PW', 'Palau'), ('PY', 'Paraguay'), ('QA', 'Qatar'),
  ('RE', 'Réunion'), ('RO', 'Romania'), ('RS', 'Serbia'), ('RU', 'Russian Federation'), ('RW', 'Rwanda'),
  ('SA', 'Saudi Arabia'), ('SB', 'Solomon Islands'), ('SC', 'Seychelles'), ('SD', 'Sudan'), ('SE', 'Sweden'),
  ('SG', 'Singapore'), ('SH', 'Saint Helena, Ascension and Tristan da Cunha'), ('SI', 'Slovenia'),
  ('SJ', 'Svalbard and Jan Mayen'), ('SK', 'Slovakia'), ('SL', 'Sierra Leone'), ('SM', 'San Marino'), ('SN', 'Senegal'),
  ('SO', 'Somalia'), ('SR', 'Suriname'), ('SS', 'South Sudan'), ('ST', 'Sao Tome and Principe'), ('SV', 'El Salvador'),
  ('SX', 'Sint Maarten (Dutch part)'), ('SY', 'Syrian Arab Republic'), ('SZ', 'Swaziland'),
  ('TC', 'Turks and Caicos Islands'), ('TD', 'Chad'), ('TF', 'French Southern Territories'), ('TG', 'Togo'),
  ('TH', 'Thailand'), ('TJ', 'Tajikistan'), ('TK', 'Tokelau'), ('TL', 'Timor-Leste'), ('TM', 'Turkmenistan'),
  ('TN', 'Tunisia'), ('TO', 'Tonga'), ('TR', 'Turkey'), ('TT', 'Trinidad and Tobago'), ('TV', 'Tuvalu'),
  ('TW', 'Taiwan, Province of China'), ('TZ', 'Tanzania, United Republic of'), ('UA', 'Ukraine'), ('UG', 'Uganda'),
  ('UM', 'United States Minor Outlying Islands'), ('US', 'United States'), ('UY', 'Uruguay'), ('UZ', 'Uzbekistan'),
  ('VA', 'Holy See (Vatican City State)'), ('VC', 'Saint Vincent and the Grenadines'),
  ('VE', 'Venezuela, Bolivarian Republic of'), ('VG', 'Virgin Islands, British'), ('VI', 'Virgin Islands, U.S.'),
  ('VN', 'Viet Nam'), ('VU', 'Vanuatu'), ('WF', 'Wallis and Futuna'), ('WS', 'Samoa'), ('YE', 'Yemen'),
  ('YT', 'Mayotte'), ('ZA', 'South Africa'), ('ZM', 'Zambia'), ('ZW', 'Zimbabwe');

DROP FUNCTION IF EXISTS iam.compare_now;

DELIMITER //

CREATE FUNCTION iam.compare_now(compare_time TIMESTAMP)
  RETURNS VARCHAR(20)

  BEGIN

    DECLARE income_level VARCHAR(20);

    IF compare_time <= now()
    THEN
      SET income_level = 'EXPIRED';

    ELSE
      SET income_level = 'ACTIVE';

    END IF;

    RETURN income_level;

  END;
//


DELIMITER ;


DROP TABLE IF EXISTS iam.MANAGEMENT_GROUP;

CREATE TABLE iam.MANAGEMENT_GROUP (
  id                     BIGINT NOT NULL AUTO_INCREMENT,
  user_id                BIGINT NOT NULL,
  group_name             VARCHAR(255),
  group_key              VARCHAR(255),
  group_secret           VARCHAR(255),
  group_jwt_secret       VARCHAR(255),
  session_token_lifetime INTEGER         DEFAULT 3600,
  scope_check_lifetime   INTEGER         DEFAULT 3600,
  description            LONGTEXT,
  reg_dt                 TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
  upd_dt                 TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS iam.OAUTH_USER;

CREATE TABLE iam.OAUTH_USER (
  id                     BIGINT       NOT NULL AUTO_INCREMENT,
  group_id               BIGINT       NOT NULL,
  user_name              VARCHAR(255) NOT NULL,
  user_password          VARCHAR(255) NOT NULL,
  level                  SMALLINT     NOT NULL DEFAULT 5,
  additional_information LONGTEXT,
  reg_dt                 TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
  upd_dt                 TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS iam.OAUTH_CLIENT;

CREATE TABLE iam.OAUTH_CLIENT (
  id                      BIGINT NOT NULL AUTO_INCREMENT,
  group_id                BIGINT NOT NULL,
  name                    VARCHAR(255),
  description             LONGTEXT,
  client_key              VARCHAR(255),
  client_secret           VARCHAR(255),
  client_jwt_secret       VARCHAR(255),
  client_trust            VARCHAR(255) COMMENT 'trust/3th_party',
  client_type             VARCHAR(255) COMMENT 'public/confidential',
  active_client           CHAR(1),
  authorized_grant_types  VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  refresh_token_validity  CHAR(1),
  additional_information  LONGTEXT,
  code_lifetime           INTEGER         DEFAULT 3600,
  refresh_token_lifetime  INTEGER         DEFAULT 3600,
  access_token_lifetime   INTEGER         DEFAULT 3600,
  jwt_token_lifetime      INTEGER         DEFAULT 3600,
  reg_dt                  TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
  upd_dt                  TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS iam.OAUTH_SCOPES;

CREATE TABLE iam.OAUTH_SCOPES (
  id                     BIGINT NOT NULL AUTO_INCREMENT,
  group_id               BIGINT NOT NULL,
  name                   VARCHAR(255),
  description            LONGTEXT,
  additional_information LONGTEXT,
  reg_dt                 TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
  upd_dt                 TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS iam.OAUTH_CLIENT_SCOPES;

CREATE TABLE iam.OAUTH_CLIENT_SCOPES (
  id        BIGINT NOT NULL AUTO_INCREMENT,
  client_id BIGINT NOT NULL,
  scope_id  BIGINT NOT NULL,
  reg_dt    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
  upd_dt    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS iam.OAUTH_ACCESS_TOKEN;

CREATE TABLE iam.OAUTH_ACCESS_TOKEN (
  id                     BIGINT   NOT NULL AUTO_INCREMENT,
  type                   VARCHAR(255) COMMENT 'client/user',
  scopes                 LONGTEXT,
  token                  LONGTEXT NOT NULL,
  oauth_user_id          BIGINT,
  group_id               BIGINT   NOT NULL,
  client_id              BIGINT   NOT NULL,
  refresh_token          LONGTEXT,
  additional_information LONGTEXT,
  reg_dt                 TIMESTAMP         DEFAULT CURRENT_TIMESTAMP,
  upd_dt                 TIMESTAMP         DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS iam.OAUTH_CODE;

CREATE TABLE iam.OAUTH_CODE (
  id            BIGINT   NOT NULL AUTO_INCREMENT,
  group_id      BIGINT   NOT NULL,
  client_id     BIGINT   NOT NULL,
  oauth_user_id BIGINT   NOT NULL,
  code          LONGTEXT NOT NULL,
  scopes        LONGTEXT,
  reg_dt        TIMESTAMP         DEFAULT CURRENT_TIMESTAMP,
  upd_dt        TIMESTAMP         DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;



