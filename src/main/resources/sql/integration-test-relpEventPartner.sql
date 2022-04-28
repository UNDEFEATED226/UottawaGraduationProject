DROP TABLE IF EXISTS relp_Event_Partner;

CREATE TABLE relp_Event_Partner(
  event_id INTEGER NOT NULL,
  partner_id INTEGER NOT NULL
);

INSERT INTO relp_Event_Partner VALUES (4,8);
INSERT INTO relp_Event_Partner VALUES (1,2);