DROP TABLE IF EXISTS types_PartnershipScope;

CREATE TABLE types_PartnershipScope(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  scope_en varchar(255) DEFAULT NULL,
  scope_fr varchar(255) DEFAULT NULL
);

INSERT INTO types_PartnershipScope VALUES (1,'Local','Local');
INSERT INTO types_PartnershipScope VALUES (2,'Provincial','Provincial');