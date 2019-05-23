SELECT name, summ FROM
(SELECT COUNT(product.id) AS summ, type.name AS name
FROM product LEFT JOIN type ON product.type_id = type.id
GROUP BY type.name) AS types
WHERE summ < 10;