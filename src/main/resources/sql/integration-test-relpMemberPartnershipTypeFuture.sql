DROP TABLE IF EXISTS relp_Member_PartnershipTypeFuture;

CREATE TABLE relp_Member_PartnershipTypeFuture(
  member_id INTEGER NOT NULL,
  type_id INTEGER NOT NULL
);

INSERT INTO relp_Member_PartnershipTypeFuture VALUES (2,2);
INSERT INTO relp_Member_PartnershipTypeFuture VALUES (2,5);