SELECT id, name, type_id, expired_date, price
FROM product
WHERE expired_date BETWEEN '2019-06-01' AND '2019-06-30';