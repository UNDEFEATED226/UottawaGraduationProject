DROP TABLE IF EXISTS types_FrequencyIndicator;

CREATE TABLE types_FrequencyIndicator(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  frequency varchar(255) DEFAULT NULL
);

INSERT INTO types_FrequencyIndicator VALUES (1,'Every 6 months');
INSERT INTO types_FrequencyIndicator VALUES (2,'1 year');