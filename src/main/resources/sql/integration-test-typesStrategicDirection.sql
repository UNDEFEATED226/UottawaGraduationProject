DROP TABLE IF EXISTS types_StrategicDirection;

CREATE TABLE types_StrategicDirection(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL
);

INSERT INTO types_StrategicDirection VALUES (1,'Taking care');
INSERT INTO types_StrategicDirection VALUES (2,'Vulnerability');