CREATE DATABASE items
WITH OWNER = postgres
ENCODING = 'UTF8'
TABLESPACE = pg_default
LC_COLLATE = 'Russian_Russia.1251'
LC_CTYPE = 'Russian_Russia.1251'
CONNECTION LIMIT = -1;

DROP TABLE IF EXISTS state CASCADE;
DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS attachs;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS role_rules;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS rules;
DROP TABLE IF EXISTS users;

CREATE TABLE role(
  role_id serial PRIMARY KEY,
  role_name VARCHAR (255) UNIQUE NOT NULL
);

CREATE TABLE rules (
  rule_id serial PRIMARY KEY,
  rule_name VARCHAR (255) UNIQUE NOT NULL
);

CREATE TABLE role_rules(
  role_id int REFERENCES role (role_id) ON UPDATE CASCADE ON DELETE CASCADE,
  rule_id int REFERENCES rules (rule_id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT role_rules_pkey PRIMARY KEY (role_id, rule_id)
);

CREATE TABLE users(
 user_id serial PRIMARY KEY,
 user_name VARCHAR (255) UNIQUE NOT NULL,
 role_id int NOT NULL REFERENCES role (role_id) ON DELETE CASCADE
);



CREATE TABLE category(
 category_id serial PRIMARY KEY,
 category_name VARCHAR (255) UNIQUE NOT NULL
);

CREATE TABLE state(
 state_id serial PRIMARY KEY,
 state_name VARCHAR (255) UNIQUE NOT NULL
);

CREATE TABLE item(
 item_id serial PRIMARY KEY,
 item_name text UNIQUE NOT NULL,
 category_id int REFERENCES category (category_id),
 state_id int REFERENCES state (state_id),
 user_id int NOT NULL REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE comments(
 comment_id serial PRIMARY KEY,
 comment_name text UNIQUE NOT NULL,
 item_id int NOT NULL REFERENCES item (item_id) ON DELETE CASCADE
);

CREATE TABLE attachs(
 attach_id serial PRIMARY KEY,
 attach_name text UNIQUE NOT NULL,
 item_id int NOT NULL REFERENCES item (item_id) ON DELETE CASCADE
);

INSERT INTO role (role_id, role_name)
VALUES (1, 'user'),
       (2, 'admin');

INSERT INTO rules (rule_id, rule_name)
VALUES (1, 'user access'),
       (2, 'full access');

INSERT INTO role_rules (role_id, rule_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO users (user_id, user_name, role_id)
VALUES (1, 'ivan_ivanov', 1),
  (2,'petr_ivanov', 1),
  (3, 'alex_borsh', 1),
  (4, 'admin', 2);

INSERT INTO category (category_id, category_name)
VALUES (1, 'error'),
       (2, 'access');

INSERT INTO state (state_id, state_name)
VALUES (1, 'new'),
       (2, 'in process'),
       (3, 'done');

INSERT INTO item (item_id, item_name, category_id, state_id, user_id)
VALUES (1, 'need access', 2, 1, 1),
       (2, 'button doesnt work', 1, 2, 2),
       (3, 'error on login form', 1, 3, 2),
       (4, 'wrong sum', 1, 1, 3);

INSERT INTO comments (comment_id, comment_name, item_id)
VALUES (1, 'need aprove from manager', 1),
       (2, 'print attached', 2);

INSERT INTO attachs (attach_id, attach_name, item_id)
VALUES (1, 'access_request.png', 1),
       (2, 'print_screen_error.png', 2);
