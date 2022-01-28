-- TRUNCATE planets;
-- TRUNCATE TABLE stars;

INSERT INTO star(name)
VALUES ('Sol');
INSERT INTO star(name)
VALUES ('Kepler-90');

INSERT INTO planet(name, star_id)
VALUES ('Mercury', 1);
INSERT INTO planet(name, star_id)
VALUES ('Venus', 1);
INSERT INTO planet(name, star_id)
VALUES ('Earth', 1);
INSERT INTO planet(name, star_id)
VALUES ('Mars', 1);
INSERT INTO planet(name, star_id)
VALUES ('Jupiter', 1);
INSERT INTO planet(name, star_id)
VALUES ('Saturn', 1);
INSERT INTO planet(name, star_id)
VALUES ('Uranus', 1);
INSERT INTO planet(name, star_id)
VALUES ('Neptune', 1);

INSERT INTO planet(name, star_id)
VALUES ('Kepler-90b', 2);
INSERT INTO planet(name, star_id)
VALUES ('Kepler-90c', 2);
INSERT INTO planet(name, star_id)
VALUES ('Kepler-90i', 2);
INSERT INTO planet(name, star_id)
VALUES ('Kepler-90d', 2);
INSERT INTO planet(name, star_id)
VALUES ('Kepler-90e', 2);
INSERT INTO planet(name, star_id)
VALUES ('Kepler-90f', 2);
INSERT INTO planet(name, star_id)
VALUES ('Kepler-90g', 2);
INSERT INTO planet(name, star_id)
VALUES ('Kepler-90h', 2);