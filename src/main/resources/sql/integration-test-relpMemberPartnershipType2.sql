DROP TABLE IF EXISTS relp_Member_PartnershipType2;

CREATE TABLE relp_Member_PartnershipType2(
  member_id INTEGER NOT NULL,
  type_id INTEGER NOT NULL
);

INSERT INTO relp_Member_PartnershipType2 VALUES (1,5);
INSERT INTO relp_Member_PartnershipType2 VALUES (1,2);