CREATE SEQUENCE SEQ_USER START WITH 2;
CREATE TABLE USER (ID INT PRIMARY KEY, EMAIL VARCHAR (50) ,PASSWORD VARCHAR (30) ,TYPE VARCHAR (50)  );
INSERT INTO user VALUES (1,'per@yahoo.no', 'password', 'STUDENT' );
COMMIT;