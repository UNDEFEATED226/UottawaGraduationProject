DROP TABLE IF EXISTS types_Faculty;

CREATE TABLE types_Faculty(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name_en varchar(255) DEFAULT NULL,
  name_fr varchar(255) DEFAULT NULL
);

INSERT INTO types_Faculty VALUES (1,'Fac. Arts','Fac. Arts');
INSERT INTO types_Faculty VALUES (2,'Fac. Education','Fac. Éducation');