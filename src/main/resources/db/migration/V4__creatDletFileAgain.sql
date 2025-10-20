CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character(30) COLLATE pg_catalog."default" NOT NULL,
    email character(30) COLLATE pg_catalog."default" NOT NULL,
    password character(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;
    
CREATE TABLE IF NOT EXISTS public.addresses
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    street character(1) COLLATE pg_catalog."default" NOT NULL,
    city character(1) COLLATE pg_catalog."default" NOT NULL,
    zip character(1) COLLATE pg_catalog."default" NOT NULL,
    users_id bigint NOT NULL,
    CONSTRAINT addresses_pkey PRIMARY KEY (id),
    CONSTRAINT addresses_users_id_fkey FOREIGN KEY (users_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.addresses
    OWNER to postgres;
-- Index: fki_users_id_fkey

-- DROP INDEX IF EXISTS public.fki_users_id_fkey;

CREATE INDEX IF NOT EXISTS fki_users_id_fkey
    ON public.addresses USING btree
    (users_id ASC NULLS LAST)
    TABLESPACE pg_default;