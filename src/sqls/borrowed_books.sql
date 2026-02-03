CREATE SCHEMA IF NOT EXISTS "Library";

CREATE SEQUENCE IF NOT EXISTS "Library".borrowed_books_borrow_id_seq;

CREATE TABLE IF NOT EXISTS "Library".borrowed_books
(
    borrow_id integer NOT NULL DEFAULT nextval('"Library".borrowed_books_borrow_id_seq'::regclass),
    book_id integer NOT NULL,
    reader_id integer NOT NULL,
    borrow_date date NOT NULL,
    return_date date,
    status character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT borrowed_books_pkey PRIMARY KEY (borrow_id),
    CONSTRAINT book_id FOREIGN KEY (book_id)
        REFERENCES "Library".books (book_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT reader_id FOREIGN KEY (reader_id)
        REFERENCES "Library".readers (reader_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS "Library".borrowed_books
    OWNER to postgres;

CREATE INDEX IF NOT EXISTS readers_borrowed_books
    ON "Library".borrowed_books USING btree
        (reader_id ASC NULLS LAST)
    INCLUDE(reader_id)
    WITH (fillfactor=100)
    TABLESPACE pg_default;