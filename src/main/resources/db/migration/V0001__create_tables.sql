CREATE TABLE star
(
    id   SERIAL       NOT NULL,
    name VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE planet
(
    id      SERIAL       NOT NULL,
    name    VARCHAR(255) NOT NULL,
    star_id INT,

    PRIMARY KEY (id),
    FOREIGN KEY (star_id)
        REFERENCES star (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);