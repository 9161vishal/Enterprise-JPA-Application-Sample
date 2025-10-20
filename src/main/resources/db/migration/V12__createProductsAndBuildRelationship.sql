CREATE TABLE IF NOT EXISTS public.products
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    name character varying(255),
    price numeric(10, 2),
    category_id smallint,
    PRIMARY KEY (id),
    CONSTRAINT fk_category_id FOREIGN KEY (category_id)
        REFERENCES public.categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS public.products
    OWNER to postgres;