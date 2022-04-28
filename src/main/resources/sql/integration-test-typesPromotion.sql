DROP TABLE IF EXISTS types_Promotion;

CREATE TABLE types_Promotion(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name_en varchar(255) DEFAULT NULL
);

INSERT INTO types_Promotion VALUES (1,'None');
INSERT INTO types_Promotion VALUES (2,'Linkedin');