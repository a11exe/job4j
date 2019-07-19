DROP TABLE IF EXISTS cities;

CREATE TABLE cities (
   id serial primary key not null,
   name varchar(2000)
);

INSERT INTO cities (id, name) VALUES
(1, 'Москва'),
(2, 'Москва'),
(3, 'Волгоград'),
(4, 'СПб'),
(5, 'Казань'),
(6, 'Волгоград');