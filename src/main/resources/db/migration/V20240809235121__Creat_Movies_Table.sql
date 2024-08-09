CREATE TABLE Movies
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    `year`    VARCHAR(4),
    runtime   INT,
    director  VARCHAR(255),
    actors    VARCHAR(1024),
    plot      TEXT,
    posterUrl VARCHAR(1024)
);