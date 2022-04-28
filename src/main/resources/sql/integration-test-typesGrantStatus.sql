DROP TABLE IF EXISTS types_GrantStatus;

CREATE TABLE types_GrantStatus(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  status_en varchar(255) DEFAULT NULL,
  status_fr varchar(255) DEFAULT NULL
);

INSERT INTO types_GrantStatus VALUES (1,'Drafted','Rédigé');
INSERT INTO types_GrantStatus VALUES (2,'Submitted','Soumis');