DROP TABLE IF EXISTS types_Affiliation;

CREATE TABLE types_Affiliation(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL
);

INSERT INTO types_Affiliation VALUES (1,'name1');
INSERT INTO types_Affiliation VALUES (2,'name2');