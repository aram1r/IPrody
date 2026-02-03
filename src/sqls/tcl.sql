BEGIN TRANSACTION;
INSERT INTO "Library".books (book_id, title, author, published_year, genre) VALUES
    (6,'Преступление и наказание', 'Фёдор Достоевский', 1866, 'роман');
INSERT INTO "Library".borrowed_books (borrow_id, book_id, reader_id, borrow_date, return_date, status) VALUES
    (8,6, 1, '2023-11-01', NULL,         'borrowed');
ROLLBACK;

BEGIN TRANSACTION ;
INSERT INTO "Library".books (book_id, title, author, published_year, genre) VALUES
    (6,'Преступление и наказание', 'Фёдор Достоевский', 1866, 'роман');
INSERT INTO "Library".borrowed_books (borrow_id, book_id, reader_id, borrow_date, return_date, status) VALUES
    (8,6, 1, '2023-11-01', NULL,         'borrowed');
COMMIT;