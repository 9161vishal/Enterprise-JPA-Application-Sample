-- ================================================
-- V1__create_tables.sql
-- PostgreSQL schema creation for Flyway
-- ================================================

-- USERS TABLE
CREATE TABLE IF NOT EXISTS public.users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name CHAR(30) NOT NULL,
    email CHAR(30) NOT NULL,
    password CHAR(30) NOT NULL
);

-- CATEGORIES TABLE
CREATE TABLE IF NOT EXISTS public.categories (
    id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- PRODUCTS TABLE
CREATE TABLE IF NOT EXISTS public.products (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    category_id SMALLINT,
    CONSTRAINT fk_category_id FOREIGN KEY (category_id)
        REFERENCES public.categories (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- ADDRESSES TABLE
CREATE TABLE IF NOT EXISTS public.addresses (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    zip VARCHAR(255) NOT NULL,
    state VARCHAR(255),
    users_id BIGINT NOT NULL,
    CONSTRAINT fk_addresses_users FOREIGN KEY (users_id)
        REFERENCES public.users (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE INDEX IF NOT EXISTS idx_addresses_users_id ON public.addresses (users_id);

-- PROFILES TABLE
CREATE TABLE IF NOT EXISTS public.profiles (
    id BIGINT PRIMARY KEY,
    bio VARCHAR,
    phone_number BIGINT,
    date_of_birth DATE,
    loyalty_points INTEGER DEFAULT 0,
    CONSTRAINT fk_profiles_user FOREIGN KEY (id)
        REFERENCES public.users (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE INDEX IF NOT EXISTS idx_profiles_user_id ON public.profiles (id);

-- TAGS TABLE
CREATE TABLE IF NOT EXISTS public.tags (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR
);

-- USER_TAG TABLE
CREATE TABLE IF NOT EXISTS public.user_tag (
    user_id BIGINT NOT NULL,
    tag_id BIGINT,
    PRIMARY KEY (user_id, tag_id),
    CONSTRAINT fk_user_tag_user FOREIGN KEY (user_id)
        REFERENCES public.users (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_user_tag_tag FOREIGN KEY (tag_id)
        REFERENCES public.tags (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE INDEX IF NOT EXISTS idx_user_tag_user_id ON public.user_tag (user_id);
CREATE INDEX IF NOT EXISTS idx_user_tag_tag_id ON public.user_tag (tag_id);
