CREATE TABLE cities_temp (LIKE cities);

-- step 2
INSERT INTO cities_temp(name, id)
     SELECT
         DISTINCT ON (name) name,
         id
     FROM cities;
-- step 3
 DROP TABLE cities;

-- step 4
ALTER TABLE cities_temp
     RENAME TO cities;
