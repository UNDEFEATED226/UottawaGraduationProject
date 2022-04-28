DROP TABLE IF EXISTS types_memberCat;

CREATE TABLE types_memberCat(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  cat_en varchar(255) DEFAULT NULL,
  cat_fr varchar(255) DEFAULT NULL
);

INSERT INTO types_memberCat VALUES (1,'Principal Researcher','Chercheur(euse) principal€');
INSERT INTO types_memberCat VALUES (2,'Visiting Researcher','Chercheur(euse) invité€');