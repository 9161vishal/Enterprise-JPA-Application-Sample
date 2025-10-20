CREATE TABLE IF NOT EXISTS public.categories
(
    id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 ),
    name character varying(255),
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.categories
    OWNER to postgres;