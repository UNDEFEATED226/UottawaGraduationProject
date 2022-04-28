DROP TABLE IF EXISTS types_Event;

CREATE TABLE types_Event(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  type_en varchar(255) DEFAULT NULL,
  type_fr varchar(255) DEFAULT NULL
);

INSERT INTO types_Event VALUES (1,'Meeting','Réunion');
INSERT INTO types_Event VALUES (2,'Workshop','Atelier');