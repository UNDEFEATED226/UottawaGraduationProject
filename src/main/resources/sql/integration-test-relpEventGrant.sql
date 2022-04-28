DROP TABLE IF EXISTS relp_Event_Grant;

CREATE TABLE relp_Event_Grant(
  event_id INTEGER NOT NULL,
  grant_id INTEGER NOT NULL
);

INSERT INTO relp_Event_Grant VALUES (1,1);
INSERT INTO relp_Event_Grant VALUES (1,2);