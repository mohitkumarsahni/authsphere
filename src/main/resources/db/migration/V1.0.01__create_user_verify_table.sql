-- users verify table
CREATE TABLE IF NOT EXISTS USERS_VERIFY (
   ID                       INT             AUTO_INCREMENT,
   EMAIL_ID                 VARCHAR(100)    NOT NULL UNIQUE,
   CONTACT_NUMBER           VARCHAR(15),
   OTP                      VARCHAR(50)     NOT NULL,
   PRIMARY KEY (ID)
);