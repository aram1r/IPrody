CREATE SCHEMA IF NOT EXISTS "Library";

CREATE SEQUENCE IF NOT EXISTS "Library".readers_reader_id_seq;

CREATE TABLE IF NOT EXISTS "Library".readers
(
    reader_id integer NOT NULL DEFAULT nextval('"Library".readers_reader_id_seq'::regclass),
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone character varying(15) COLLATE pg_catalog."default",
    CONSTRAINT readers_pkey PRIMARY KEY (reader_id),
    CONSTRAINT email UNIQUE (email),
    CONSTRAINT phone UNIQUE (phone)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS "Library".readers
    OWNER to postgres;