-- Oppretter sekvens for bruk i ID-generering
CREATE SEQUENCE IF NOT EXISTS SEQ_USER START WITH 2 INCREMENT BY 1;

-- Oppretter tabell for brukere
CREATE TABLE IF NOT EXISTS "USER" (
                                      id INT PRIMARY KEY,
                                      email VARCHAR(50) NOT NULL,
                                      password VARCHAR(30) NOT NULL,
                                      type VARCHAR(50) NOT NULL
);

-- Setter inn en eksempelbruker
INSERT INTO "USER" VALUES (1, 'per@yahoo.no', 'password', 'STUDENT');

COMMIT;