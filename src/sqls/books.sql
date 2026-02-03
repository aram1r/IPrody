CREATE SCHEMA IF NOT EXISTS "Library";

CREATE SEQUENCE IF NOT EXISTS "Library".books_book_id_seq;

CREATE TABLE IF NOT EXISTS "Library".books
(
    book_id integer NOT NULL DEFAULT nextval('"Library".books_book_id_seq'::regclass),
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    author character varying(255) COLLATE pg_catalog."default" NOT NULL,
    published_year integer,
    genre character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT books_pkey PRIMARY KEY (book_id),
    CONSTRAINT published_year CHECK (published_year > 0)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS "Library".books
    OWNER to postgres;

CREATE INDEX IF NOT EXISTS book_name
    ON "Library".books USING btree
        (title COLLATE pg_catalog."default" ASC NULLS LAST)
    INCLUDE(title)
    WITH (fillfactor=100, deduplicate_items=True)
    TABLESPACE pg_default;