CREATE TABLE main_Events (
  id INTEGER NOT NULL identity,
  name_en varchar(255) DEFAULT NULL,
  name_fr varchar(255) DEFAULT NULL,
  start_date DATE,
  end_date DATE,
  notes varchar(255) DEFAULT NULL,
  type INTEGER DEFAULT NULL,
  PRIMARY KEY(id)
)
;

INSERT INTO main_Events VALUES (1,'hello','boujour','2020-09-11','2020-09-11','note1',1);
INSERT INTO main_Events VALUES (2,'thanks','merci','2020-02-07','2020-02-07','note2',8);