DROP TABLE IF EXISTS relp_Event_Product;

CREATE TABLE relp_Event_Product(
  event_id INTEGER NOT NULL,
  product_id INTEGER NOT NULL
);

INSERT INTO relp_Event_Product VALUES (3,2);
INSERT INTO relp_Event_Product VALUES (5,1);