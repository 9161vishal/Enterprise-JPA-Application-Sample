-- Table: public.profiles

-- DROP TABLE IF EXISTS public.profiles;

CREATE TABLE IF NOT EXISTS public.profiles
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    bio character varying COLLATE pg_catalog."default",
    phone_number bigint,
    date_of_birth date,
    loyalty_points integer DEFAULT 0,
    CONSTRAINT profiles_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.profiles
    OWNER to postgres;