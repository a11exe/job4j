SELECT car_id, car_name, body_name, transmission_name, engine_name
FROM car
  LEFT JOIN body on car.body_id = body.body_id
  LEFT JOIN transmission on car.transmission_id = transmission.transmission_id
  LEFT JOIN engine on car.engine_id = engine.engine_id;