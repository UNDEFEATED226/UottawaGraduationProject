DROP TABLE IF EXISTS relp_Event_Member;

CREATE TABLE relp_Event_Member(
  event_id INTEGER NOT NULL,
  member_id INTEGER NOT NULL
);

INSERT INTO relp_Event_Member VALUES (1,14);
INSERT INTO relp_Event_Member VALUES (1,60);