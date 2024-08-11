CREATE TABLE MovieGenres
(
    movie_id INT NOT NULL,
    genre_id INT NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES Movies (id),
    FOREIGN KEY (genre_id) REFERENCES Genres (id)
);
