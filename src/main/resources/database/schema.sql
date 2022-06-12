CREATE TABLE author
(
    author_id   INT PRIMARY KEY AUTO_INCREMENT,
    author_name VARCHAR(50)
);

CREATE TABLE genre
(
    genre_id   INT PRIMARY KEY AUTO_INCREMENT,
    genre_name VARCHAR(50)
);

CREATE TABLE book
(
    book_id   INT PRIMARY KEY AUTO_INCREMENT,
    title     VARCHAR(50),
    author_id INT,
    genre_id  INT,
    amount    INT,
    price INT,
    FOREIGN KEY (author_id) REFERENCES author (author_id) ON DELETE SET NULL,
    FOREIGN KEY (genre_id) REFERENCES genre (genre_id) ON DELETE SET NULL
);

CREATE TABLE student
(
    student_id   INT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(50)
);

CREATE TABLE borrowed
(
    borrowed_id     INT PRIMARY KEY AUTO_INCREMENT,

    book_id         INT,
    title           VARCHAR(50),
    borrowed_amount INT,
    price INT,

    author_id       INT,
    genre_id        INT,
    student_id      INT,

    FOREIGN KEY (author_id) REFERENCES author (author_id) ON DELETE SET NULL,
    FOREIGN KEY (genre_id) REFERENCES genre (genre_id) ON DELETE SET NULL,
    FOREIGN KEY (student_id) REFERENCES student (student_id) ON DELETE SET NULL
);

CREATE TABLE analysisauthor
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    count INT
);

CREATE TABLE analysisbook
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    count INT
);

CREATE TABLE analysisgenre
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    count INT
);