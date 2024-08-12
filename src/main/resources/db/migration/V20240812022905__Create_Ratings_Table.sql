CREATE TABLE Ratings
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    score    INT NOT NULL,
    movie_id INT NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES Movies (id)
);