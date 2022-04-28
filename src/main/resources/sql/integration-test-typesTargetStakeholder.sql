DROP TABLE IF EXISTS types_TargetStakeholder;

CREATE TABLE types_TargetStakeholder(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name_en varchar(255) DEFAULT NULL,
  name_fr varchar(255) DEFAULT NULL
);

INSERT INTO types_TargetStakeholder VALUES (1,'Academic Personnel','Personnel académique');
INSERT INTO types_TargetStakeholder VALUES (2,'Charity Org','Organisme de bienfaisance');