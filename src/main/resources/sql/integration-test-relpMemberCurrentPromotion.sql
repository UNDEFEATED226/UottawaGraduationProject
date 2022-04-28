DROP TABLE IF EXISTS relp_Member_CurrentPromotion;

CREATE TABLE relp_Member_CurrentPromotion(
  member_id INTEGER NOT NULL,
  promotion_id INTEGER NOT NULL
);

INSERT INTO relp_Member_CurrentPromotion VALUES (2,2);
INSERT INTO relp_Member_CurrentPromotion VALUES (5,1);