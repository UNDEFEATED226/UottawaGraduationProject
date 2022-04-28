DROP TABLE IF EXISTS relp_Member_FuturePromotion;

CREATE TABLE relp_Member_FuturePromotion(
  member_id INTEGER NOT NULL,
  promotion_id INTEGER NOT NULL
);

INSERT INTO relp_Member_FuturePromotion VALUES (1,8);
INSERT INTO relp_Member_FuturePromotion VALUES (2,8);