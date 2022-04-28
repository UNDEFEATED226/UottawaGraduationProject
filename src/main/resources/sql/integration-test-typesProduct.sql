DROP TABLE IF EXISTS types_Product;

CREATE TABLE types_Product(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  type_en varchar(255) DEFAULT NULL,
  type_fr varchar(255) DEFAULT NULL
);

INSERT INTO types_Product VALUES (1,'Pub_Journal Articles','Pub_Articles de revue');
INSERT INTO types_Product VALUES (2,'Pub_Journal Issues','Pub_Numéros de revue');