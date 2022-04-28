DROP TABLE IF EXISTS relp_Member_PartnershipScopeFuture;

CREATE TABLE relp_Member_PartnershipScopeFuture(
  member_id INTEGER NOT NULL,
  scope_id INTEGER NOT NULL
);

INSERT INTO relp_Member_PartnershipScopeFuture VALUES (1,5);
INSERT INTO relp_Member_PartnershipScopeFuture VALUES (1,2);