create table log (
   id serial primary key not null,
   event varchar(2000),
   msg text,
   time timestamp
);
