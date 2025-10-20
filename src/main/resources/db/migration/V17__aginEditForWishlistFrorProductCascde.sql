ALTER TABLE public.wishlist DROP CONSTRAINT IF EXISTS fk_product;

ALTER TABLE public.wishlist
ADD CONSTRAINT fk_product_cascade
FOREIGN KEY (product_id)
REFERENCES public.products (id)
ON UPDATE CASCADE
ON DELETE CASCADE;