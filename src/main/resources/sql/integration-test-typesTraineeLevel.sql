DROP TABLE IF EXISTS types_TraineeLevel;

CREATE TABLE types_TraineeLevel(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  level_en varchar(255) DEFAULT NULL,
  level_fr varchar(255) DEFAULT NULL
);

INSERT INTO types_TraineeLevel VALUES (1,'hello','boujour');
INSERT INTO types_TraineeLevel VALUES (2,'thanks','merci');