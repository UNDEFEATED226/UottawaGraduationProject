DROP TABLE IF EXISTS types_Topic;

CREATE TABLE types_Topic(
  theme_id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name_en varchar(255) DEFAULT NULL,
  name_fr varchar(255) DEFAULT NULL
);

INSERT INTO types_Topic VALUES (1,'Live long','Vivre longtemps');
INSERT INTO types_Topic VALUES (2,'Live well','Bien vivre');