-- Table: public.profiles

-- DROP TABLE IF EXISTS public.profiles;

CREATE TABLE IF NOT EXISTS public.profiles
(
    id bigint NOT NULL,
    bio character varying(255) COLLATE pg_catalog."default",
    date_of_birth date,
    loyalty_points integer,
    phone_number bigint,
    CONSTRAINT profiles_pkey PRIMARY KEY (id),
    CONSTRAINT fk55e5d3sfwkob62wtprm633alk FOREIGN KEY (id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.profiles
    OWNER to postgres;