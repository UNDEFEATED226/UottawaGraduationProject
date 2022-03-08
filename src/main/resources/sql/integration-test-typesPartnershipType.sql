DROP TABLE IF EXISTS types_PartnershipType;

CREATE TABLE types_PartnershipType(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  type_en varchar(255) DEFAULT NULL,
  type_fr varchar(255) DEFAULT NULL
);

INSERT INTO types_PartnershipType VALUES (1,'hello','boujour');
INSERT INTO types_PartnershipType VALUES (2,'thanks','merci');