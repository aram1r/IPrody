TRUNCATE TABLE "Library".books RESTART IDENTITY CASCADE;
TRUNCATE TABLE "Library".readers RESTART IDENTITY CASCADE;
TRUNCATE TABLE "Library".borrowed_books RESTART IDENTITY CASCADE;


INSERT INTO "Library".books (book_id, title, author, published_year, genre)
VALUES (1, 'Мастер и маргарита', 'Булгаков', 1967, 'роман'),
       (2, 'Преступление и наказание', 'Достоевский', 1866, 'роман'),
       (3, 'Война и мир', 'Толстой', 1869, 'роман'),
       (4, '1984', 'Оруэлл', 1949, 'антиутопия'),
       (5, 'Маленький принц', 'Экзюпери', 1943, 'сказка');

INSERT INTO "Library".readers (reader_id, name, email, phone)
VALUES  (1, 'Иванов Иван Иванович', 'ivanov.ivan@example.com', '+79011234567'),
        (2, 'Петрова Екатерина Сергеевна', 'petrova.e.s@gmail.com', '+79022345678'),
        (3, 'Сидоров Алексей Викторович', 'sidorov_a@mail.ru', '+79033456789'),
        (4, 'Козлова Ольга Николаевна', 'kozlova_on@yandex.ru', '+79044567890');

INSERT INTO "Library".borrowed_books (borrow_id, book_id, reader_id, borrow_date, return_date, status)
VALUES (1,1, 1, '2023-10-01', '2023-10-15', 'returned'),
       (2,2, 1, '2023-11-01', NULL,         'borrowed'),
       (3,3, 2, '2023-11-05', '2023-11-20', 'returned'),
       (4,4, 3, '2023-12-01', NULL,         'borrowed'),
       (5,5, 4, '2023-12-10', NULL,         'borrowed'),
       (6,2, 1, '2023-11-01', NULL,         'borrowed'),
       (7,2, 1, '2023-11-01', NULL,         'borrowed');