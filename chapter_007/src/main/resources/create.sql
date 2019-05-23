-- CREATE DATABASE items
-- WITH OWNER = "user"
-- ENCODING = 'UTF8'
-- TABLESPACE = pg_default
-- LC_COLLATE = 'Russian_Russia.1251'
-- LC_CTYPE = 'Russian_Russia.1251'
-- CONNECTION LIMIT = -1;
-- GRANT ALL ON DATABASE tracker TO "user";
-- REVOKE ALL ON DATABASE tracker FROM public;

DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS item;

CREATE TABLE item(
 item_id serial PRIMARY KEY,
 item_name text NOT NULL,
 item_desc text,
 item_created timestamp NOT NULL
);

CREATE TABLE comments(
 comment_id serial PRIMARY KEY,
 comment_name text NOT NULL,
 item_id int NOT NULL REFERENCES item (item_id) ON DELETE CASCADE
);
