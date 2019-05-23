DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS type;

CREATE TABLE type (
  id serial PRIMARY KEY,
  name VARCHAR (255) UNIQUE NOT NULL
);

CREATE TABLE product(
  id serial PRIMARY KEY,
  name VARCHAR (255) UNIQUE NOT NULL,
  type_id int NOT NULL REFERENCES type (id),
  expired_date date,
  price NUMERIC(15,2)
);

INSERT INTO type (id, name)
VALUES (1, 'cheese'),
       (2, 'milk'),
       (3, 'toys'),
       (4, 'fruit'),
       (5, 'ice cream');

INSERT INTO product (id, name, type_id, expired_date, price)
VALUES (1, 'soft cheese', 1, '2019-05-31', 1200.58),
       (2, 'hard cheese', 1, '2109-12-12', 2500.12),
       (3, 'blue cheese', 1, '2020-01-05', 3500.50),
       (4, 'milk non fat', 2, '2019-06-02', 50.00),
       (5, 'milk high fat', 2, '2019-06-15', 80.00),
       (6, 'chocolat milk', 2, '2019-06-15', 90.30),
       (7, 'orange', 4, '2019-10-15', 190.30),
       (8, 'banan', 4, '2019-08-03', 5.99),
       (9, 'car', 3, NULL, 289.99),
       (10, 'ball', 3, NULL, 49.99),
       (11, 'fruit ice cream', 5, '2019-05-30', 39.99),
       (12, 'chocolat ice cream', 5, '2019-06-10', 49.99);