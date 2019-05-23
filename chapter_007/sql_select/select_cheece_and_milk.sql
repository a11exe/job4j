SELECT product.id, product.name, type_id, expired_date, price, type.name
FROM product LEFT JOIN type ON product.type_id = type.id
WHERE type.name = 'cheese' OR type.name = 'milk';