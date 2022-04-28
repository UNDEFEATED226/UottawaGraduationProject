DROP TABLE IF EXISTS relp_Member_PartnershipScope3;

CREATE TABLE relp_Member_PartnershipScope3(
  member_id INTEGER NOT NULL,
  scope_id INTEGER NOT NULL
);

INSERT INTO relp_Member_PartnershipScope3 VALUES (2,4);
INSERT INTO relp_Member_PartnershipScope3 VALUES (1,2);