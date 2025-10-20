-- Drop old foreign key constraints
ALTER TABLE IF EXISTS public.wishlist DROP CONSTRAINT IF EXISTS fk_product;
ALTER TABLE IF EXISTS public.wishlist DROP CONSTRAINT IF EXISTS fk_user;

-- Re-add with CASCADE rules
ALTER TABLE public.wishlist
ADD CONSTRAINT fk_product
FOREIGN KEY (product_id)
REFERENCES public.products (id)
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE public.wishlist
ADD CONSTRAINT fk_user
FOREIGN KEY (user_id)
REFERENCES public.users (id)
ON UPDATE CASCADE
ON DELETE CASCADE;


