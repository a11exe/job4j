DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS body;
DROP TABLE IF EXISTS engine;
DROP TABLE IF EXISTS transmission;

CREATE TABLE transmission (
  transmission_id serial PRIMARY KEY,
  transmission_name VARCHAR (255) UNIQUE NOT NULL
);

CREATE TABLE body (
  body_id serial PRIMARY KEY,
  body_name VARCHAR (255) UNIQUE NOT NULL
);

CREATE TABLE engine (
  engine_id serial PRIMARY KEY,
  engine_name VARCHAR (255) UNIQUE NOT NULL
);

CREATE TABLE car(
  car_id serial PRIMARY KEY,
  car_name VARCHAR (255) UNIQUE NOT NULL,
  transmission_id int NOT NULL REFERENCES transmission (transmission_id),
  body_id int NOT NULL REFERENCES body (body_id),
  engine_id int NOT NULL REFERENCES engine (engine_id)
);

INSERT INTO transmission (transmission_id, transmission_name)
VALUES (1, '4 gears auto'),
       (2, '4 gears manual'),
       (3, '5 gears auto'),
       (4, '6 gears auto'),
       (5, '7 gear auto');

INSERT INTO body (body_id, body_name)
VALUES (1, 'sedan'),
       (2, 'SUV'),
       (3, 'targo'),
       (4, 'wagon'),
       (5, 'coupe'),
       (6, 'pick up');

INSERT INTO engine (engine_id, engine_name)
VALUES (1, '400'),
       (2, '150'),
       (3, '210'),
       (4, '500'),
       (5, '800');

INSERT INTO car (car_id, car_name, transmission_id, body_id, engine_id)
VALUES (1, 'Hyandai i40', 4, 1, 2),
       (2, 'Ford explorer', 2, 2, 4),
       (3, 'Toyota corolla', 4, 1, 3),
       (4, 'BMW 3', 2, 1, 3),
       (5, 'Chevrolet Camaro', 5, 5, 4);