-- version 1 works if id uniq
DELETE
FROM cities t1
USING cities t2
where t1.name = t2.name
and t1.id > t2.id;

-- version 2 works if id uniq
DELETE
FROM cities
WHERE ID NOT IN
      (
        SELECT MAX(ID)
        FROM cities
        GROUP BY name);