DROP TABLE IF EXISTS relp_Grant_Topic;

CREATE TABLE relp_Grant_Topic(
  grant_id INTEGER NOT NULL,
  theme_id INTEGER NOT NULL
);

INSERT INTO relp_Grant_Topic VALUES (1,2);
INSERT INTO relp_Grant_Topic VALUES (2,2);