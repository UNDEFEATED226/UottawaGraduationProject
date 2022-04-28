DROP TABLE IF EXISTS relp_Member_Affiliation;

CREATE TABLE relp_Member_Affiliation(
  member_id INTEGER NOT NULL,
  affiliation_id INTEGER NOT NULL
);

INSERT INTO relp_Member_Affiliation VALUES (1,5);
INSERT INTO relp_Member_Affiliation VALUES (2,1);