DROP TABLE IF EXISTS relp_Member_FuturePromotion;

CREATE TABLE relp_Member_IndicatorFormat(
  member_id INTEGER NOT NULL,
  format_id INTEGER NOT NULL
);

INSERT INTO relp_Member_IndicatorFormat VALUES (1,2);
INSERT INTO relp_Member_IndicatorFormat VALUES (1,3);