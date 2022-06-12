INSERT INTO author (author_name)
VALUES ('Mark Twain'),
       ('Charles Dickens'),
       ('George Orwell'),
       ('Stephen King'),
       ('Joanne Rowling'),

       ('Hayley Scrivenor'),
       ('Ann Cleeves'),
       ('Lucinda Riley');

INSERT INTO genre (genre_name)
VALUES ('Adventure'),
       ('Classics'),
       ('Fantasy'),
       ('Crime');

INSERT INTO book (title, author_id, genre_id, amount, price)
VALUES ('Adventures of Tow Sawyer', 1, 1, 5, 1000),
       ('Adventures of Huckleberry Finn', 1, 1, 3, 1000),
       ('Oliver Twist', 2, 1, 3, 1000),
       ('Misery', 4, 1, 3, 1000),

       ('1984', 3, 2, 3, 1500),
       ('Animal Farm', 3, 2, 3, 1500),
       ('Homage to Catalonia', 3, 2, 3, 1500),

       ('The Shining', 4, 3, 3, 2000),
       ('The Stand', 4, 3, 3, 2000),

       ('Harry Potter and the Philosophers Stone', 5, 3, 3, 2500),
       ('Harry Potter and the Chamber of Secrets', 5, 3, 3, 2500),
       ('Harry Potter and the Prisoner of Azkaban', 5, 3, 3, 2500),
       ('Harry Potter and the Goblet of Fire', 5, 3, 3, 2500),
       ('Harry Potter and the Order of the Phoenix', 5, 3, 3, 2500),
       ('Harry Potter and the Half-Blood Prince', 5, 3, 3, 2500),
       ('Harry Potter and the Deathly Hallows', 5, 3, 3, 2500),
       ('Dirt Town', 6, 4, 3, 2500),

       ('Detective Sergeant', 6, 4, 3, 3000),
       ('The Sleeping and the Dead', 7, 4, 3, 3000),
       ('The Long Call', 7, 4, 3, 3000),
       ('The Murders at Fleat House', 8, 4, 3, 3000),
       ('Detective Inspector', 8, 4, 3, 3000);

INSERT INTO student (student_name)
VALUES ('roma'),
       ('ayan');

INSERT INTO borrowed (student_id, book_id, title, author_id, genre_id, borrowed_amount, price)
VALUES (1, 1, 'Adventures of Tow Sawyer', 1, 1, 3, 1000),
       (1, 2, 'Adventures of Huckleberry Finn', 1, 1, 2, 1000),
       (1, 3, 'Oliver Twist', 2, 1, 1, 1000),
       (1, 4, 'Misery', 4, 1, 1, 1000),

       (1, 5, '1984', 3, 2, 2, 1500),
       (2, 6, 'Animal Farm', 3, 2, 3, 1500),
       (2, 7, 'Homage to Catalonia', 3, 2, 3, 1500),

       (2, 8, 'The Shining', 4, 3, 3, 2000),
       (2, 9, 'The Stand', 4, 3, 3, 2000);
