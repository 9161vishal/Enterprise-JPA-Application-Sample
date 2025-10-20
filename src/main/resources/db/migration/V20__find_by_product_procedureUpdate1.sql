CREATE OR REPLACE FUNCTION find_product_by_price(
  min_price NUMERIC(10,2),
  max_price NUMERIC(10,2)
)

RETURNS TABLE (
  id BIGINT,
  name VARCHAR,
  price NUMERIC,
  category_id SMALLINT
)
LANGUAGE plpgsql
AS $$

BEGIN
  RETURN QUERY
  SELECT id, name, price, category_id
  FROM product
  WHERE price BETWEEN min_price AND max_price
  ORDER BY name;
END;
$$;