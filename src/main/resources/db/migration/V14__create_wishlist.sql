CREATE TABLE IF NOT EXISTS public.wishlist
(
    user_id bigint NOT NULL,
    product_id bigint NOT NULL,
    CONSTRAINT wishlist_pk PRIMARY KEY (user_id)
        INCLUDE(product_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.wishlist
    OWNER to postgres;