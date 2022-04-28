DROP TABLE IF EXISTS relp_Event_Topic;

CREATE TABLE relp_Event_Topic(
  event_id INTEGER NOT NULL,
  theme_id INTEGER NOT NULL
);

INSERT INTO relp_Event_Topic VALUES (1,2);
INSERT INTO relp_Event_Topic VALUES (2,2);