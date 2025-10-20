CREATE TABLE IF NOT EXISTS public.tags
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying COLLATE pg_catalog."default",
    CONSTRAINT tags_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tags
    OWNER to postgres;
    
CREATE TABLE IF NOT EXISTS public.user_tag
(
    user_id bigint NOT NULL,
    tag_id bigint,
    CONSTRAINT user_tag_pk PRIMARY KEY (user_id)
        INCLUDE(tag_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_tag
    OWNER to postgres;