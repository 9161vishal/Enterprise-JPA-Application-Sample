CREATE TABLE IF NOT EXISTS public.wishlist
(
    user_id bigint NOT NULL,
    product_id bigint NOT NULL,
    CONSTRAINT wishlist_pkey PRIMARY KEY (user_id, product_id),
    CONSTRAINT fk6p7qhvy1bfkri13u29x6pu8au FOREIGN KEY (product_id)
        REFERENCES public.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fktrd6335blsefl2gxpb8lr0gr7 FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.wishlist
    OWNER to postgres;