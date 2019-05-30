DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS person;

CREATE TABLE company
(
  id integer NOT NULL,
  name character varying,
  CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
  id integer NOT NULL,
  name character varying,
  company_id integer,
  CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) VALUES
(1, 'google'),
(2, 'facebook'),
(3, 'amazon'),
(4, 'netflix'),
(5, 'yandex');

INSERT INTO person (id, name, company_id) VALUES
(1, 'alex', 1),
(2, 'bob', 1),
(3, 'michel', 1),
(4, 'tony', 2),
(5, 'marta', 2),
(6, 'irma', 3),
(7, 'antony', 4),
(8, 'kim', 4),
(9, 'juli', 5),
(10, 'arnold', 5);

SELECT person.name, company.name FROM person
  LEFT JOIN company ON person.company_id = company.id
  WHERE company.id <> 5;

SELECT company.name, SUM(1) as persons FROM company
  LEFT JOIN person ON company.id = person.company_id
  GROUP BY (company.name) ORDER BY persons DESC LIMIT 1;