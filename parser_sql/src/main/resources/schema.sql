create table vacancies (
   id serial primary key not null,
   name varchar(2000),
   text text,
   link varchar(2000),
--    posted timestamp
);

create table log (
   id serial primary key not null,
   event varchar(2000),
   msg text,
   time timestamp
);
