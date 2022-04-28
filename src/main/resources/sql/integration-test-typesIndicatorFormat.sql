DROP TABLE IF EXISTS types_IndicatorFormat;

CREATE TABLE types_IndicatorFormat(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL
);

INSERT INTO types_IndicatorFormat VALUES (1,'Call');
INSERT INTO types_IndicatorFormat VALUES (2,'Email');