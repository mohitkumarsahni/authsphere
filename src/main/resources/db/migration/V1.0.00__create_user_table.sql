-- users table
CREATE TABLE IF NOT EXISTS USERS (
   ID                       INT             AUTO_INCREMENT,
   FIRST_NAME               VARCHAR(20)     NOT NULL,
   LAST_NAME                VARCHAR(20),
   DATE_OF_BIRTH            DATE            NOT NULL,
   EMAIL_ID                 VARCHAR(100)    NOT NULL,
   PASSWORD                 VARCHAR(1000)   NOT NULL,
   IS_ACTIVE                BOOLEAN         NOT NULL,
   IS_EMAIL_VALIDATED       BOOLEAN         NOT NULL,
   PRIMARY KEY (ID)
);