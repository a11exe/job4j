create table items (
   item_id serial primary key not null,
   item_name varchar(2000),
   item_desc text,
   item_created timestamp
);

create table comments (
  comment_id serial primary key not null,
  comment_name varchar(2000),
  item_id int NOT NULL REFERENCES items (item_id) ON DELETE CASCADE
);

