DROP TABLE IF EXISTS relp_Grant_Member;

CREATE TABLE relp_Grant_Member(
  member_id INTEGER NOT NULL,
  grant_id INTEGER NOT NULL
);

INSERT INTO relp_Grant_Member VALUES (1,2);
INSERT INTO relp_Grant_Member VALUES (2,3);