DROP TABLE IF EXISTS types_GrantSource;

CREATE TABLE types_GrantSource(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  type_en varchar(255) DEFAULT NULL,
  type_fr varchar(255) DEFAULT NULL
);

INSERT INTO types_GrantSource VALUES (1,'Foundation','Fondation');
INSERT INTO types_GrantSource VALUES (2,'Donor','Donateur');