SELECT body.body_id, body_name, 'body' AS part
FROM body
  LEFT JOIN car on body.body_id = car.body_id
WHERE car.car_name is NULL

UNION ALL

SELECT engine.engine_id, engine_name, 'engine' AS part
FROM engine
  LEFT JOIN car on engine.engine_id = car.engine_id
WHERE car.car_name is NULL

UNION ALL

SELECT transmission.transmission_id, transmission_name, 'transmission' AS part
FROM transmission
  LEFT JOIN car on transmission.transmission_id = car.transmission_id
WHERE car.car_name is NULL;