
CREATE OR REPLACE FUNCTION findProductByPrice(
	minPrice NUMERIC(10,2),
	maxPrice NUMERIC(10,2)
)
RETURNS TABLE(id BIGINT, name VARCHAR, price NUMERIC)


LANGUAGE plpgsql
AS $$

BEGIN
	SELECT id,name,price,category_id
	FROM products
	WHERE price BETWEEN minPrice AND maxPrice
	ORDER BY p.name;
END ;
$$;
