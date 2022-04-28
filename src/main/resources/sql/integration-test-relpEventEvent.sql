DROP TABLE IF EXISTS relp_Event_Event;

CREATE TABLE relp_Event_Event(
  past_event_id INTEGER NOT NULL,
  future_event_id INTEGER NOT NULL
);

INSERT INTO relp_Event_Event VALUES (1,2);
INSERT INTO relp_Event_Event VALUES (2,3);