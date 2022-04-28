DROP TABLE IF EXISTS relp_Member_StrategicDirection;

CREATE TABLE relp_Member_StrategicDirection(
  member_id INTEGER NOT NULL,
  direction_id INTEGER NOT NULL
);

INSERT INTO relp_Member_StrategicDirection VALUES (1,2);
INSERT INTO relp_Member_StrategicDirection VALUES (1,3);