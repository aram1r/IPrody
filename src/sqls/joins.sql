SELECT "Library".books.title as title, "Library".books.author as author, "Library".borrowed_books.reader_id as reader_id, "Library".borrowed_books.borrow_date
FROM "Library".books INNER JOIN "Library".borrowed_books on "Library".books.book_id = "Library".borrowed_books.book_id WHERE "Library".borrowed_books.status = 'borrowed';

Select reader.name, COUNT(books.book_id) FROM "Library".readers AS reader
INNER JOIN "Library".borrowed_books AS books ON books.reader_id = reader.reader_id GROUP BY reader.name;

SELECT readers.name, COUNT(borrowed_books.book_id) as count  FROM "Library".readers AS readers INNER JOIN "Library".borrowed_books as borrowed_books
ON readers.reader_id = borrowed_books.reader_id GROUP BY readers.name HAVING COUNT(borrowed_books.book_id)>=2;

SELECT books.title, books.author, bb.borrow_date, bb.return_date FROM "Library".books as books LEFT JOIN "Library".borrowed_books bb on books.book_id = bb.book_id
WHERE books.genre = 'роман';