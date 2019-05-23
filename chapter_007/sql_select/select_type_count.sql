SELECT COUNT(product.id) AS id, type.name AS name
FROM product LEFT JOIN type ON product.type_id = type.id
GROUP BY type.name;