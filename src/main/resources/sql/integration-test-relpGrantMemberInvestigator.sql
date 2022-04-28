DROP TABLE IF EXISTS relp_Grant_MemberInvestigator;

CREATE TABLE relp_Grant_MemberInvestigator(
  member_id INTEGER NOT NULL,
  grant_id INTEGER NOT NULL
);

INSERT INTO relp_Grant_MemberInvestigator VALUES (14,1);
INSERT INTO relp_Grant_MemberInvestigator VALUES (14,2);