UPDATE "Library".books SET genre = 'фантастика' where book_id = 5;

UPDATE "Library".borrowed_books SET status = 'returned', return_date = CURRENT_DATE where status = 'borrowed';